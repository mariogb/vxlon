
package org.lonpe.lonvx.handlers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

/**
 *
 * @author l5
 */
public class LoginHandler implements Handler<RoutingContext> {

    private final PgPool client;
    private final JWTAuth jWTAuth;
    private final JWTOptions jWTOptions;
    private final Buffer badLogin;
    private final BCryptPasswordEncoder b = new BCryptPasswordEncoder();

    private static final Logger log = LoggerFactory.getLogger(LoginHandler.class);
    static final String SQLLOGIN = "SELECT id,pkey,pname,email,type_lon,password FROM user_lon WHERE username = $1";

    static final String SQLMEMBERSHIP = "SELECT base_pkey as pkey,'b' as m FROM base_user_lon_view where user_lon_id = $1 UNION "
            + "SELECT departament_pkey as pkey,'d' as m FROM departament_user_lon_view where user_lon_id = $2 UNION "
            + "SELECT program_pkey,'p' as m FROM  program_user_lon_view where user_lon_id = $3";

    /**
     *
     * @param client
     * @param jWTAuth
     */
    public LoginHandler(PgPool client, JWTAuth jWTAuth) {
        System.out.println("--Iniciando Login Handler");
        this.client = client;
        this.jWTAuth = jWTAuth;
        this.jWTOptions = new JWTOptions()
                .setAlgorithm("HS256")
                .setExpiresInMinutes(180)
                .setIssuer("LONVX");
        badLogin = new JsonObject().put("ERR", "BADLOGIN").toBuffer();
    }

    /**
     *
     * @param rctx
     */
    @Override
    public void handle(RoutingContext rctx) {
        rctx.request().bodyHandler((Buffer bff) -> {
            final JsonObject user0 = bff.toJsonObject();
            final String username = user0.getString("username");
            final String password = user0.getString("password");
            final HttpServerResponse res = rctx.response();
            if (username == null || password == null) {
                res.setStatusCode(400).end();
                return;
            }
            processLogin(rctx, username, password);
        });

    }

    private void doGoodResponse(RoutingContext rctx, JsonObject tk) {
        final String token = jWTAuth.generateToken(tk, jWTOptions);

        final JsonObject respuesta = new JsonObject().put("access_token", token);

        final HttpServerRequest request = rctx.request();
        final SocketAddress remoteAddress = request.remoteAddress();

        final String host = remoteAddress.host();
        respuesta.put("host", host);

        rctx.response().setStatusCode(200)
                .putHeader("content-type", "application/json")
                .setChunked(true)
                .end(respuesta.encode());
    }



    private void processLogin(RoutingContext rctx, String username, String password) {

        final HttpServerResponse res = rctx.response();

        client.preparedQuery(SQLLOGIN).execute(Tuple.of(username), new Handler<AsyncResult<RowSet<Row>>>() {
            @Override
            public void handle(AsyncResult<RowSet<Row>> ar) {
                if (ar.failed()) {
                    
                    log.error("\n ->> \n --->>> FAIL LOGIN\n\t\t  [ " + ar.cause().getMessage() + " ]\n\n");
                    res.setStatusCode(500).end("CONERR");
                    return;
                }
                res.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                final RowSet<Row> result = ar.result();
                if (result.size() != 1) {
                    res.setStatusCode(401).end(badLogin);
                    return;
                }

                final Row r = result.iterator().next();
                final String passwordCrypt = r.getString("password");
                if (!b.matches(password, passwordCrypt)) {
                    res.setStatusCode(401)
                            .end(badLogin);
                    return;
                }

                final JsonObject tk = buildJsonObjectResponse(r, username);
                final String typeLon = tk.getString("typeLon");
                final Long id = tk.getLong("id");
                if (typeLon.equals("ADM")) {
                    doGoodResponse(rctx, tk);
                    return;
                }

                if ("THIRD".equals(typeLon)) {

                }

                final Tuple t2 = Tuple.of(id, id, id);

                client.preparedQuery(SQLMEMBERSHIP).execute(t2, new Handler<AsyncResult<RowSet<Row>>>() {
                    @Override
                    public void handle(AsyncResult<RowSet<Row>> event) {
                        if (event.failed()) {
                            res.setStatusCode(500).end("CONERR2");
                            return;
                        }

                        final RowSet<Row> result1 = event.result();

                        final RowIterator<Row> iterator = result1.iterator();
                        while (iterator.hasNext()) {
                            final Row rr2 = iterator.next();
                            final String mb = rr2.getString("m");
                            if (!tk.containsKey("mb")) {
                                tk.put(mb, new JsonArray());
                            }
                            tk.getJsonArray(mb).add(rr2.getString("pkey"));
                        }
                        doGoodResponse(rctx, tk);
                    }
                });

            }
        });
    }

    private JsonObject buildJsonObjectResponse(final Row r1, final String username) {
        final JsonObject tk = new JsonObject();
        tk.put("username", username);
        tk.put("email", r1.getString("email"));
        tk.put("typeLon", r1.getString("type_lon"));
        tk.put("pkey", r1.getString("pkey"));
        tk.put("id", r1.getLong("id"));
        return tk;

    }

}
