/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.services;

import io.vertx.core.AsyncResult;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import org.lonpe.model.AbstractDcLon;
import org.lonpe.services.impl.DcMapForServices;

/**
 *
 * @author l5
 */
public class DBLon0 {

    private final PgPool client;
    private final DcMapForServices dcMapForServices;

    public DBLon0(PgPool client, DcMapForServices dcMapForServices) {
        this.client = client;
        this.dcMapForServices = dcMapForServices;
    }

    public void store00(String dc, AbstractDcLon dc0) {
        System.out.println("Hacer store " + dc0.toString());
        final IServiceLon s = dcMapForServices.getServiceFor(dc);
        final String sql_ = s.getSqlIdByPkey();
        System.out.println("s.getSqlIdByPkey() " + sql_ + " " + dc0.getPkey());
        client.preparedQuery(sql_).execute(Tuple.of(dc0.getPkey()), (AsyncResult<RowSet<Row>> event0) -> {
            if (event0.failed()) {
                System.out.println("No se ejecuto query [\n" + sql_ + "\n]\n" + event0.cause().getMessage());
                System.out.println("");
                return;
            }
            
            final RowSet<Row> result = event0.result();
            Long id0 = null;
            String sql0 = null;
            final Tuple tuple = Tuple.tuple();
            if (result.size() == 1) {
                id0 = result.iterator().next().getLong(0);
                
            }
            if (id0 == null) {
                s.fillTupleInsert(dc0, tuple);
                sql0 = s.getSqlInsert();
            } else {
                // dc0.setId(id0);
                s.fillTupleUpdate(dc0, tuple);
                tuple.addLong(id0);
                sql0 = s.getSqlIUpdate();
            }
            final String sql00 = sql0;
            final Long id00 = id0;
            client.preparedQuery(sql00).execute(tuple, (AsyncResult<RowSet<Row>> event1) -> {
                if (event1.failed()) {
                    System.out.println("No se ejecuto query [\n" + sql00 + "\n]\n" + event1.cause().getMessage());
                    System.out.println("");
                    return;
                }
                
                final RowSet<Row> result1 = event1.result();
                if (result1.size() == 1) {
                    final Long aLong = result1.iterator().next().getLong(0);
                    System.out.println("xxxx " + id00 + " --- " + aLong);
                } else {
                    System.out.println("Algo ocurrio se esperaba solo uno");
                }
            });
        });

    }

    
    
    
}
