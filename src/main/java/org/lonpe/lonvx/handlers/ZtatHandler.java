/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import java.util.Set;

import org.lonpe.lonvx.sqlbuilders.SqlZtatBuilder;
import org.lonpe.services.impl.DcMapForServices;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import org.lonpe.model.IDcLon;
import org.lonpe.services.IServiceLon;

/**
 *
 * @author l5
 */
public class ZtatHandler implements Handler<RoutingContext> {

    private final PgPool client;
    private final DcMapForServices dcMapForServices;

    protected static final Logger log = LoggerFactory.getLogger(ZtatHandler.class);

    public ZtatHandler(PgPool client, DcMapForServices dcMapForServices) {
        this.client = client;
        this.dcMapForServices = dcMapForServices;
    }

    @Override
    public void handle(RoutingContext rctx) {
        final HttpServerRequest request = rctx.request();

        final String dc = request.getParam("dc");
        final IServiceLon<IDcLon> serviceFor = dcMapForServices.getServiceFor(dc);
        if (serviceFor == null) {
            rctx.response().setStatusCode(500).end();
            return;
        }

        final MultiMap params = request.params();
        final SqlZtatBuilder szb = serviceFor.doZtat(params);
        final String sqlEx = szb.buildSQL();
        final Set<String> names = szb.getNames();

        System.out.println("ZTAT EXECUTE \t\t" + sqlEx);

        log.info("names " + names);

        client.preparedQuery(sqlEx).execute(Tuple.tuple(), (AsyncResult<RowSet<Row>> ars) -> {
            final JsonObject jo = new JsonObject();
            if (ars.failed()) {
                Throwable cause = ars.cause();
                log.error("FALLA ZTATS ", cause);
                rctx.response().setStatusCode(500).end(jo.put("ERROR", cause.getMessage()).toBuffer());
                return;
            }

            final JsonArray jaf = new JsonArray();
            ars.result().forEach((Row r) -> {
                final int n = r.size();
                final JsonArray a = new JsonArray();
                for (int i = 0; i < n; i++) {
                    a.add(r.getValue(i));
                }
                jaf.add(a);
            });
            jo.put("l", jaf);

            final JsonArray jsn = new JsonArray();
            names.stream().forEach((s) -> {
                jsn.add(s);
            });

            jo.put("names", jsn);

            rctx.response().putHeader("content-type", "application/json").end(jo.toBuffer());
        });

    }

    /*     private void examine(HttpServerRequest request) {
        MultiMap params = request.params();
        System.out.println("QUERYZtat " + request.query());

        params.forEach((Map.Entry<String, String> p) -> {
            System.out.println("---------------");
            System.out.println("[Z][T][A][T][S] p k = " + p.getKey());
            System.out.println("p v = " + p.getValue());

        });

        params.names().forEach((String t) -> {
            System.out.println("----> parametro: " + t);
            params.getAll(t).stream().forEach((String ve) -> {
                System.out.println("v" + ve);
            });
        });

    } */
}
