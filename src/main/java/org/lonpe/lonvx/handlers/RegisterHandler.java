package org.lonpe.lonvx.handlers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;

/**
 *
 * @author l5
 */
public class RegisterHandler implements Handler<RoutingContext> {

    PgPool client;

    public RegisterHandler(PgPool client) {
        this.client = client;
    }

    private static final String USER_LON_TBL = "user_lon";

    private JsonObject verifyUserData(JsonObject user0) {
        JsonObject r = new JsonObject();
        if (user0 == null) {
            r.put("ERROR", "NO_USER_DATA_PRESENT");
            return r;
        }
        final String username = user0.getString("username");
        if (username == null || username.length() < 6) {
            r.put("ERROR", "BAD_USERNAME");
            return r;
        }

        final String password = user0.getString("password");

        if (password == null || password.length() < 6) {
            r.put("ERROR", "BAD_PASSWD");
            return r;
        }
        final String password2 = user0.getString("password2");
        if (password2 == null || password2.length() < 6) {
            r.put("ERROR", "BAD_PASSWD2");
            return r;
        }

        if (!password.equals(password2)) {
            r.put("ERROR", "PASSWORD_MISMATCH");

            return r;
        }

        return r;
    }

    @Override
    public void handle(RoutingContext rctx) {
        rctx.request().bodyHandler((Buffer bff) -> {
            final HttpServerResponse response = rctx.response();
            final JsonObject dataRecived = bff.toJsonObject();

            final JsonObject user0 = dataRecived.getJsonObject("user");
            if (user0 == null) {
                response.setStatusCode(500).end("NOUSRDATA");
                return;
            }

            final JsonObject r = verifyUserData(user0);
            if (r.containsKey("ERROR")) {
                response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(r.encode());
                return;
            }

            System.out.println("----------->" + r);

            final Future<SqlConnection> connection = client.getConnection();
            connection.onSuccess((sqlC) -> {
                System.out.println("------dddd22222----->" + r);
                final String un = user0.getString("username");
                final String email = user0.getString("email");

                // user0.put("pname",un);
                sqlC.preparedQuery("SELECT username, email from user_lon where username = $1 OR email = $2 limit 2")
                        .execute(Tuple.of(un, email), new RegistUserExecutor(response, sqlC, user0));
            });

            connection.onFailure((err) -> {
                System.out.println("rrrr2" + err);
                response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(err.getMessage());

            });

        });

    }
    private static final String SQLINSERT = "INSERT INTO "
            + USER_LON_TBL
            + " (id,pname,password_expired,type_lon,username,account_locked,account_expired,enabled,email,password,pkey) VALUES ((select nextval('seq_" + USER_LON_TBL + "')),$1,$2,$3,$4,$5,$6,$7,$8,$9,$10) returning *";

    private static Tuple doTuple(final JsonObject user0) {
        Tuple tuple = Tuple.tuple();
        tuple.addString(user0.getString("pname"));
        tuple.addBoolean(false);

        tuple.addString("AGENT");
        tuple.addString(user0.getString("username"));
        tuple.addBoolean(false);
        tuple.addBoolean(false);

        tuple.addBoolean(true);
        tuple.addString(user0.getString("email"));
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        tuple.addString(b.encode(user0.getString("password")));
        tuple.addString(user0.getString("username"));//pkey

        return tuple;
    }

    private class RegistUserExecutor implements Handler<AsyncResult<RowSet<Row>>> {

        HttpServerResponse response;
        SqlConnection sqlC;
        JsonObject user0;

        public RegistUserExecutor(HttpServerResponse response, SqlConnection sqlC, JsonObject user0) {
            this.response = response;
            this.sqlC = sqlC;
            this.user0 = user0;
        }

        @Override
        public void handle(AsyncResult<RowSet<Row>> ar) {
            if (ar.failed()) {
                response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(ar.cause().getMessage());
                sqlC.close();
                return;
            }

            final JsonObject r = new JsonObject();
            final RowSet<Row> result = ar.result();
            if (result.size() > 0) {
                sqlC.close();
                r.put("ERROR", "USER EXISTS");
                response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(r.toString());
                return;
            }

            sqlC.preparedQuery(SQLINSERT).execute(doTuple(user0), (AsyncResult<RowSet<Row>> ar3) -> {
                sqlC.close();

                if (ar3.succeeded()) {
                    final RowSet<Row> result1 = ar3.result();
                    System.out.println("rrr " + result1);
                    System.out.println("columnsNames " + result1.columnsNames());

                    r.put("GOOD", 1);
                    response.setStatusCode(200).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(r.toString());
                } else {
                    String message = ar3.cause().getMessage();
                    System.out.println("message " + message);
                    JsonObject jsonObject = new JsonObject(message);
                    r.put("ERROR", jsonObject);
                    response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(r.encode());
                }

            });

        }

    }
}
