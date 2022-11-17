/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import java.time.LocalDateTime;
import java.util.TimeZone;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;

/**
 *
 * @author l5
 */
public class WebSocketLonHandler implements Handler<ServerWebSocket> {

    private final JWTAuth provider;
    private final Vertx vertx;

    protected static final Logger log = LoggerFactory.getLogger(WebSocketLonHandler.class);

    public WebSocketLonHandler(JWTAuth provider, Vertx vertx) {
        this.provider = provider;
        this.vertx = vertx;
    }

    private final String FROMUID = "from_uid";

    private void handleBuffToPublish(final Buffer e, final String claveCanal,
            final Long uid, final String username,
            EventBus eb, final MessageConsumer<JsonObject> consumer) {

        // JsonObject toJsonObject = e.toString();
        try {
            final JsonObject recibido = e.toJsonObject();

            final String laAccion = recibido.getString(ACTION);
            if (laAccion == null) {
                log.info("Necesito accion para ejecutar");
                return;
            }
            if ("END".equals(laAccion) || CLOSE.equals(laAccion)) {
                log.info("---LO SACAMOS");
                consumer.unregister();
            }

            if (!laAccion.equals("POS")) {
                log.info("---> en Socket " + username + "---> laAccion " + laAccion);
            }

            final JsonObject payload = recibido.getJsonObject("payload");

            if (payload == null) {
                log.info("Necesito payload para ejecutar");
                return;
            }
            payload.put(FROMUID, uid);
            payload.put("from_username", username);
            payload.put("date", LocalDateTime.now().toString());
            payload.put("timezone", TimeZone.getDefault().getID());
            final JsonObject publishObj = new JsonObject()
                    .put(ACTION, laAccion)
                    .put("payload", payload);
            eb.publish(claveCanal, publishObj);

        } catch (Exception ex) {
            log.error("Datos recibidos no buenos para convertir a json !!!", ex);

        }

    }

    private static final String ACTION = "action";
    private static final String CLOSE = "CLOSE";

    private void handleMsg(Message<JsonObject> msg, final Long uid, final String username,
            final ServerWebSocket sws0) {
        final JsonObject body = msg.body();
        final String action = body.getString(ACTION);
        final JsonObject payload = body.getJsonObject("payload");

        final JsonObject r = new JsonObject().put(ACTION, action);
        if (!action.equals("POS")) {
            log.info("-EN consumer-" + username + "--> action " + action);
        }

        switch (action) {
            case "CHAT":
                r.put("payload", payload);
                break;
            case "POS":
                r.put("payload", payload);
                break;

            case "END":
                r.put("payload", payload);
                break;
            case CLOSE:
                r.put("payload", payload);
                break;

            case "SUBSCRIPTION":
                break;
            case "sendT1Candidate":
            case "sendWRTDescription":
                final boolean isToUsr = payload.getString("to_uid").equals(uid.toString());
                if (!isToUsr/* !payload.getString("to_uid").equals(uid.toString()) */) {
                    return;
                }
                r.put("payload", payload);
                break;

            // case "sendWRTDescription":
            //
            // if (!isToUsr/*!payload.getString("to_uid").equals(uid.toString())*/) {
            // return;
            // }
            // r.put("payload", payload);
            // break;
            case "sendWRTDescription2_to1":
            case "sendT2Candidate":
            case "sendTT2Candidate":
                if (!payload.getString("answer_to").equals(uid.toString())) {
                    return;
                }
                r.put("payload", payload);
                break;

            // case "sendT2Candidate":
            // if (!payload.getString("answer_to").equals(uid.toString())) {
            // return;
            // }
            // r.put("payload", payload);
            // break;
            //
            // case "sendTT2Candidate":
            // if (!payload.getString("answer_to").equals(uid.toString())) {
            // return;
            // }
            // r.put("payload", payload);
            // break;
            default:
                return;

        }

        sws0.writeTextMessage(r.toString());

    }

    @Override
    public void handle(ServerWebSocket sws0) {

        final String path = sws0.path();
        if (!path.startsWith("/wrtc/")) {
            log.error("PATH no permitido para socket: " + path);
            sws0.close();
        }

        String[] path_split = path.split("/");
        final int n = path_split.length;
//        for (int i = 0; i < n; i++) {
//            System.out.println(i + "----" + path_split[i]);
//        }

        final String claveCanal = path_split[1] + "/" + path_split[2];// Se concatena con el path

        log.info("Clave de Canal  " + claveCanal);

        final JsonObject jwtJs = new JsonObject().put("token", path_split[n - 1]);
        log.info("json web token JsonObject to authenticate:" + jwtJs.toString());

        provider.authenticate(jwtJs, (AsyncResult<User> res) -> {
            if (res.failed()) {
                log.info("CLOSED en " + claveCanal + "\n--" + res.cause().getMessage());
                sws0.close();
                return;
            }

            final User result = res.result();

            final JsonObject attributes = result.attributes();
            log.info("----result.attributes()");
            log.info("\t" + result.attributes());
            log.info("----result.principal()");
            log.info("\t" + result.principal());
            final JsonObject accObject = attributes.getJsonObject("accessToken");
            final String username = accObject.getString("username");
            final Long uid = accObject.getLong("id");

            if (path.startsWith("/wrtc/")) {

                final EventBus eb = vertx.eventBus();

                final MessageConsumer<JsonObject> consumer = eb.consumer(claveCanal, (Message<JsonObject> msg) -> {
                    handleMsg(msg, uid, username, sws0);
                });

                sws0.closeHandler((Void e) -> {
                    eb.publish(claveCanal, new JsonObject().put(ACTION, CLOSE).put("payload",
                            new JsonObject().put(FROMUID, uid)));
                    log.info("Close en " + consumer.address());
                    consumer.unregister();
                });

                sws0.endHandler((Void e) -> {
                    eb.publish(claveCanal, new JsonObject().put(ACTION, "END").put("payload",
                            new JsonObject().put(FROMUID, uid)));
                    log.info("end en " + consumer.address());
                    consumer.unregister();
                });

                sws0.handler((Buffer e) -> {
                    handleBuffToPublish(e, claveCanal, uid, username, eb, consumer);
                });

            }
        });

    }

}
