package org.lonpe.lonvx;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLHandshakeException;

import org.lonpe.lonvx.handlers.DeleteHandler;
import org.lonpe.lonvx.handlers.ExcelUploadHandler;
import org.lonpe.lonvx.handlers.GenericListHandler;
import org.lonpe.lonvx.handlers.GenericSaveHandler;
import org.lonpe.lonvx.handlers.LoginHandler;
import org.lonpe.lonvx.handlers.RegisterHandler;
import org.lonpe.lonvx.handlers.WebSocketLonHandler;
import org.lonpe.lonvx.handlers.ZtatHandler;
import org.lonpe.lonvx.hz.HzLonConfig;
import org.lonpe.model.MeUsrInterface;
import org.lonpe.services.DBLon0;
import org.lonpe.services.DBLon1;
import org.lonpe.services.impl.DcMapForServices;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import io.netty.handler.ssl.NotSslRecordException;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import io.vertx.sqlclient.PoolOptions;
import org.lonpe.lonvx.ctes.CteLon;
import org.lonpe.lonvx.handlers.MeUsrIntHandler;
import org.lonpe.services.impl.MeUsrInterfaceService;

public class MainVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(GenericListHandler.class);

    private JsonObject configApp;

    private DcMapForServices dcMapForServices;

    private ConfigRetriever doConfig0() {
        ml("DoConfig");

        final ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path", "config-lon.json"));

        final ConfigStoreOptions sysPropsStore = new ConfigStoreOptions().setType("env");

        final ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStore)
                .addStore(sysPropsStore);

        return ConfigRetriever.create(vertx, options);

    }

    private PgPool doDbPool() {

        final PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(configApp.getInteger("POSTGRES_PORT"))
                .setHost(configApp.getString("POSTGRES_HOST"))
                .setDatabase(configApp.getString("POSTGRES_DB"))
                .setUser(configApp.getString("POSTGRES_USER"))
                .setPassword(configApp.getString("POSTGRES_PASSWORD"));

        // Pool options
        final PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(configApp.getInteger("POSTGRES_POOL_MAXSIZE", 24));

        // Create the client pool
        return PgPool.pool(vertx, connectOptions, poolOptions);
    }

    private JWTAuth provider;

    private HazelcastInstance h;

    private JWTAuth doProvider() {
        final JWTAuthOptions jwtAuthOpts = new JWTAuthOptions()
                .setKeyStore(new KeyStoreOptions()
                        .setPath("ssl/keystore.jceks").setType("jceks")
                        .setPassword("lonpe1"));
        return JWTAuth.create(vertx, jwtAuthOpts);
    }

    private void auth00(RoutingContext rctx) {

        final HttpServerRequest req = rctx.request();
        final String path = req.path();
        final SocketAddress remoteAddress = req.remoteAddress();
        final String origin = req.getHeader("Origin");
        final String referer = req.getHeader("Referer");
        final String conMetodo = req.method().toString();
        ml("ORIGIN " + origin + " REFER " + referer + "\t remoteAddress --> metodo" + remoteAddress + " --> "
                + conMetodo);

        if (origin == null) {

            ml("** \t No tiene origin!!! \n DE :: " + remoteAddress.host() + " [REFER =  " + referer + "  ]");

        }

        if (path.equals("/login") || path.equals("/register") || path.equals("/t")) {
            rctx.next();
            return;
        }

        if (path.startsWith("/wrtc/")) {
            rctx.next();
            return;
        }

        final String elJWT = elToken(req.headers());
        final JsonObject jwt = new JsonObject().put("token", elJWT);

        provider.authenticate(jwt, (AsyncResult<User> r) -> {
            if (!r.succeeded()) {
                ml("-----NO PASO " + r.cause().getMessage());
                rctx.response().setStatusCode(401).end();
                return;
            }
            final User user0 = r.result();
            final JsonObject u0 = user0.principal();
            rctx.put("u0", u0);
            rctx.next();
        });

    }

    private Router doRouter(final DBLon1 dBLon1) {
        ml("ON doRouter");
        final Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders0())
                .allowedMethods(allowedMethods()).maxAgeSeconds(600));
        ml("ON doRouter [CorsHandler OK]");
        router.route().handler((RoutingContext rctx) -> auth00(rctx));
        ml("ON doRouter [AUTH SETUP K]");

        router.get("/t").handler(r -> r.response().end("T"));

        ml("ON doRouter [T ROUTE]");

        router.post("/login").handler(new LoginHandler(dbbPool, provider));

        ml("ON doRouter [LOGIN HANDLER]");

        router.post("/register").handler(new RegisterHandler(dbbPool));

        ml("ON doRouter [REGISTER HANDLER]");
        final IMap<String, MeUsrInterface> mapMUI = h.getMap(CteLon.MUIMAP);
        
        router.get("/init0/l_models").handler(new Handler<RoutingContext>() {
            JsonObject m;
           

            @Override
            public void handle(RoutingContext rctx) {

                final JsonObject u0 = (JsonObject) rctx.get("u0");
                if (u0 == null) {
                    rctx.response().end();
                    return;
                }
                final String typeLon = u0.getString("typeLon");
                if (typeLon == null) {
                    rctx.response().end();
                }
                if (m == null) {
                    m = dcMapForServices.model();
                    log.info("SOLO UNA VEZ APAREZCO. Es cuando se crea el objeto json del modelo.");
                }

                Iterator<MeUsrInterface> iterator = mapMUI.values((Map.Entry<String, MeUsrInterface> entry) -> {
                    final MeUsrInterface mui0 = entry.getValue();
                    return (mui0 != null && mui0.getLevel() == 0);
                }).iterator();

//                final Iterator<Map.Entry<String, MeUsrInterface>> iterator = mapMUI.entrySet().iterator();
                final JsonObject mMUI = new JsonObject();

                while (iterator.hasNext()) {
                    MeUsrInterface value = iterator.next();
                    final JsonObject vJs = new JsonObject().put("list_name", value.getLabel()).put("id", value.getId());
                    mMUI.put(value.getPkey(), vJs);
                }
//                while (iterator.hasNext()) {
//                    final Map.Entry<String, MeUsrInterface> ee = iterator.next();
//                    MeUsrInterface value = ee.getValue();
//                    if (value != null) {
//                        final JsonObject vJs = new JsonObject().put("list_name", value.getLabel()).put("id",
//                                value.getId());
//                        mMUI.put(ee.getKey(), vJs);
//                    }
//
//                }
                m.put("mMUI", mMUI);
                final Buffer mBuff = m.toBuffer();
                rctx.response()
                        .putHeader("content-type", "application/json")
                        .end(mBuff);

            }

        });
        ml("ON doRouter [MODEL HANDLER]");
        router.get("/crud/:dc/l").handler(new GenericListHandler(dBLon1, h, dcMapForServices));
        ml("ON doRouter [REGISTER HANDLER]");

        router.get("/crud/:dc/lztat").handler(new ZtatHandler(dbbPool, dcMapForServices));
        ml("ON doRouter [ZtatHandler]");
        router.put("/crud/:dc/s").handler(new GenericSaveHandler(h, dcMapForServices, dBLon1));
        ml("ON doRouter [GenericSaveHandler]");
        
        
        router.put("/crud/:dc/smap").handler(new MeUsrIntHandler(mapMUI,(MeUsrInterfaceService)dcMapForServices.getServiceFor("meUsrInterface")));
        ml("ON doRouter [MeUsrIntHandler]");
        
        
        router.delete("/crud/:dc/delete").handler(new DeleteHandler(dBLon1, dcMapForServices));
        ml("ON doRouter [DeleteHandler]");
        router.route().handler(BodyHandler.create());
        ml("ON doRouter [BodyHandler]");
        router.post("/crud/:dc/upXls").handler(new ExcelUploadHandler(dBLon1, dcMapForServices));
        ml("ON doRouter [ExcelUploadHandler]");
        ml("ON END doRouter");
        return router;

    }

    // private void initRoutes
    private PgPool dbbPool;

    private HttpServerOptions doHttpServerOptions() {

        final Boolean https0 = configApp.getBoolean("WITH_HTTPS");
        final boolean isHttps = https0 != null ? https0 : true;
        final JksOptions jksOptions = new JksOptions()
                .setPath("certificate.p12")
                .setPassword("lonpe1");

        final HttpServerOptions hso = new HttpServerOptions()
                .setMaxWebSocketFrameSize(1024 * 64)
                .setCompressionSupported(true)
                .setCompressionLevel(2);
        if (isHttps) {
            hso.setSsl(isHttps).setKeyStoreOptions(jksOptions);
        }

        return hso;

    }

    private void initClustered(Promise<Void> startPromise) {
        ml("INICIA initClustered ");
        dcMapForServices = new DcMapForServices();
        ml("INICIADO [MAPA DE SERVICIOS DCMAP]");
        dbbPool = doDbPool();
        ml("INICIADO [Pool]");
        provider = doProvider();
        ml("INICIADO [AuthProvider]");
        final DBLon0 dBLon0 = new DBLon0(dbbPool, dcMapForServices);
        ml("INICIADO [DBLon0]");
        final DBLon1 dBLon1 = new DBLon1(configApp, dcMapForServices);
        ml("INICIADO [DBLon1]");
        final Config hzConfig = HzLonConfig.doConfig(dBLon0, dBLon1);
        ml("INICIADO [hzConfig]");

        final HazelcastClusterManager mgr = new HazelcastClusterManager(hzConfig);

        final VertxOptions vx = new VertxOptions();
        vx.setClusterManager(mgr);
        mlBLUE("\n\n * VertxOptions \n\n" + vx.toJson().encodePrettily() + " \n\n");

        Vertx.clusteredVertx(vx, (AsyncResult<Vertx> e) -> {
            ml("ON CLUSTERED");

            if (e.failed()) {
                ml("ALERT NO clusteredVertx" + e.cause().getMessage());
                log.error("ALERT NO clusteredVertx" + e.cause().getMessage());
                return;
            }

            final Set<HazelcastInstance> allHazelcastInstances = Hazelcast.getAllHazelcastInstances();
            h = allHazelcastInstances.stream().findFirst().get();
            final Router router = doRouter(dBLon1);
            ml("INICIADO [router]");
            final Integer port0 = configApp.getInteger("VXPORT");
            final Integer port = port0 != null ? port0 : 8888;
            ml("EL PORT " + port);
            final HttpServerOptions httpServerOptions = doHttpServerOptions();

            final HttpServer server = vertx.createHttpServer(httpServerOptions)
                    .exceptionHandler(new StartServerErrorHandler());
            server.webSocketHandler(new WebSocketLonHandler(provider, vertx));
            server.requestHandler(router).listen(port, (AsyncResult<HttpServer> http) -> {
                if (http.succeeded()) {
                    startPromise.complete();

                    ml("HTTP" + (httpServerOptions.isSsl() ? "S" : "") + " server started on port " + port);
                } else {
                    startPromise.fail(http.cause());
                }
            });

        });
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        doConfig0().getConfig(ar -> {
            if (ar.failed()) {
                Throwable cause = ar.cause();

                ml("CAN'T LOAD CONFIGURATION " + cause.getMessage());
            } else {
                configApp = ar.result();

                ml("APP CONFIG :: \n" + configApp.encodePrettily());

                initClustered(startPromise);

            }
        });

    }

    private Set<String> allowedHeaders0() {
        final Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");
        allowedHeaders.add("authlon");
        allowedHeaders.add("authorization");
        return allowedHeaders;
    }

    private Set<HttpMethod> allowedMethods() {
        final Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        /*
         * these methods aren't necessary for this sample,
         * but you may need them for your projects
         */
        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.PUT);
        return allowedMethods;
    }

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void ml(Object o) {
        log.info(ANSI_PURPLE + "## \t " + o + " " + ANSI_RESET);
    }

    private void mlBLUE(Object o) {
        System.out.println(ANSI_BLUE + "## \t " + o + " " + ANSI_RESET);
    }

    private String elToken(MultiMap headers) {
        final String h_authorization = headers.get("Authorization");
        if (h_authorization != null) {
            final String[] ha = h_authorization.split(" ");
            if (ha.length == 2) {
                return ha[1];
            }
        }
        return null;
    }

    protected class StartServerErrorHandler implements Handler<Throwable> {

        @Override
        public void handle(Throwable serverError) {
            if (serverError instanceof NotSslRecordException) {
                final NotSslRecordException se = (NotSslRecordException) serverError;
                log.error("!!!NotSslRecordException  !!!!!!!" + se.getMessage());
            } else if (serverError instanceof SSLHandshakeException) {
                final SSLHandshakeException se = (SSLHandshakeException) serverError;
                log.error("!!!! SSLHandshakeException !!!!! " + se.getMessage());
            } else if (serverError instanceof IllegalArgumentException) {
                final IllegalArgumentException siaex = (IllegalArgumentException) serverError;
                log.error("!!! IllegalArgumentException !!!!" + siaex.getMessage());
                log.error("VERIFCAR  WITH_HTTPS . Buscando HTTPS SOBRE HTTP");

            } else {

                log.error("!!! " + serverError.getClass() + ":: " + serverError.getMessage() + " *");
            }
            //
        }
    }

}
