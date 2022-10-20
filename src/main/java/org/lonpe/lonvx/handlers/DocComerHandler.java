/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

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
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author l5
 */
public class DocComerHandler implements Handler<RoutingContext> {

    final String DOCUMENT_IN_TBL = "";

    PgPool client;

    public DocComerHandler(PgPool client) {
        this.client = client;
    }

    @Override
    public void handle(RoutingContext rctx) {

        rctx.request().bodyHandler((Buffer bff) -> {
            final HttpServerResponse response = rctx.response();
            final JsonObject doc0 = bff.toJsonObject();
            
            
            doc0.put("pkey","DOCCI:"+System.currentTimeMillis());
            doc0.put("pname", "Documento ");
            doc0.put("date", new Date());

            final String SQLINSERT = "INSERT INTO " + DOCUMENT_IN_TBL + " (id,comercial_type,date,pname,contract_in_id,pkey) VALUES ((select nextval('seq_" + DOCUMENT_IN_TBL + "')),$1,$2,$3,$4,$5) returning *";

            final Future<SqlConnection> connection = client.getConnection();

            connection.onSuccess((sqlConn) -> {
                sqlConn.preparedQuery(SQLINSERT).execute(doTuple(doc0), (AsyncResult<RowSet<Row>> ar3) -> {

                    if (ar3.succeeded()) {
                        final RowSet<Row> result1 = ar3.result();
                        final JsonObject result = new JsonObject();
                        result1.forEach(action -> {
                            result.put("id", action.getLong("id"));
                        });
                        response.setStatusCode(200).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(result.toString());
                    } else {
                        final String message = ar3.cause().getMessage();
                        System.out.println("message " + message);
                        final JsonObject result2 = new JsonObject(message);
                        result2.put("ERROR", message);
                        response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(result2.encode());
                    }

                });

            });

            connection.onFailure((err) -> {
                System.out.println("rrrr2" + err);
                response.setStatusCode(400).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(err.getMessage());

            });

        });

    }

    private static Tuple doTuple(final JsonObject doc0) {
        Tuple tuple = Tuple.tuple();
        tuple.addString(doc0.getString("pname"));
        tuple.addBoolean(false);

        tuple.addString("AGENT");
        tuple.addString(doc0.getString("username"));
        tuple.addBoolean(false);
        tuple.addBoolean(false);

        tuple.addBoolean(true);
        tuple.addString(doc0.getString("email"));
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        tuple.addString(b.encode(doc0.getString("password")));
        tuple.addString(doc0.getString("username"));//pkey

        return tuple;
    }

}
