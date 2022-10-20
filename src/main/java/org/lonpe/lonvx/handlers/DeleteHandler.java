/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowIterator;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.lonpe.model.IDcLon;
import org.lonpe.services.AbstractServiceLon;
import org.lonpe.services.DBLon1;
import org.lonpe.services.IServiceLon;
import org.lonpe.services.impl.DcMapForServices;

/**
 *
 * @author l5
 */
public class DeleteHandler implements Handler<RoutingContext> {

    DcMapForServices dcMapForServices;
    DBLon1 dBLon1;

    public DeleteHandler(DBLon1 dBLon1, DcMapForServices dcMapForServices) {
        this.dcMapForServices = dcMapForServices;
        this.dBLon1 = dBLon1;
    }

    @Override
    public void handle(RoutingContext rc) {
        final String dc = rc.request().getParam("dc");
        List<String> listIds = rc.request().params().getAll("id");
        if (listIds == null) {
            rc.response().setStatusCode(500).end("noids");
            return;
        }

        final IServiceLon<IDcLon> serviceFor = dcMapForServices.getServiceFor(dc);
        if (serviceFor == null) {

            rc.response().setStatusCode(500).end("baddc");
            return;
        }

        final List<Tuple> batch = new ArrayList<>();
        listIds.stream().filter((String id0) -> {
            return id0.matches("[0-9]+");
        }).forEach((String id0) -> {
            long id = Long.parseLong(id0);
            batch.add(Tuple.of(id));
        });

        dBLon1.doBatch(serviceFor.getSqlDelete(), batch)
                .subscribe((RowSet<Row> rss) -> {
                    final RowIterator<Row> it = rss.iterator();
                    final JsonObject r = new JsonObject().put("d", new Date().getTime());
                    int i = 0;
                    while (it.hasNext()) {
                        final Row row0 = it.next();
                        r.put("r" + i, row0.getLong(0));
                    }

                    rc.response().end(r.toBuffer());
                }, (Throwable t) -> {
                    if (t instanceof io.vertx.pgclient.PgException) {

                        UtilFns.doError(serviceFor, rc.response(), t.getMessage());
                        return;
                    }

                    rc.response().setStatusCode(500).end(t.getMessage());

                });

    }

}
