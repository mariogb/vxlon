/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import com.hazelcast.core.HazelcastInstance;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava3.core.http.HttpHeaders;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowIterator;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;

import java.util.Set;

import java.util.stream.Collectors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.lonpe.model.IDcLon;
import org.lonpe.services.ConditionInfo;
import org.lonpe.services.DBLon1;
import org.lonpe.services.IServiceLon;
import org.lonpe.services.impl.DcMapForServices;

/**
 *
 * @author l5
 */
public class GenericListHandler extends AbstractGenericList implements Handler<RoutingContext> {

    private DBLon1 dBLon1;

    public GenericListHandler(DBLon1 dBLon1, HazelcastInstance h, DcMapForServices dcMapForServices) {
        super(h, dcMapForServices);
        this.dBLon1 = dBLon1;

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
        examine(request);
        //Just get the excel template. and return.
        final String xlsTemplate = request.getParam("xlsTemplate");
        if (xlsTemplate != null && !xlsTemplate.isEmpty()) {
            handleAsExcelTemplateCase(eu.doFile(dc, serviceFor, null, false), rctx);
            return;
        }
        applyFiltersToMemeberships(dc, rctx);
        defaultList(dc, serviceFor, rctx);
    }

    /**
     *
     * @param dc domain class name
     * @param serviceFor
     * @param rctx
     */
    protected final void defaultList(final String dc, final IServiceLon<IDcLon> serviceFor, final RoutingContext rctx) {
        final Tuple tuple = Tuple.tuple();

        final ConditionInfo ci = serviceFor.doCondiciones(rctx.queryParams(), tuple.getDelegate());

        final Set<String> conditions = ci.getCondiciones();
        final String sqlConds = conditions.stream().collect(Collectors.joining(" AND "));
        final String sql0 = serviceFor.getSqlView();

        final String sqlEx = sql0 + doSqlConds00(sqlConds);
        final String the_order = ci.getOrden();
        final String sqlEx2 = sqlEx + (" " + the_order + " offset " + ci.getOffset() + " limit " + ci.getMax());

//        h.getDistributedObjects().stream().forEach((DistributedObject t) -> {
//
//            ml(" HZ Distributed object: ", t.getName());
//        });
        dBLon1.doPreparedQuery(sqlEx2, tuple).doOnError(new Consumer<Throwable>(){
            @Override
            public void accept(Throwable t) throws Throwable {
                System.out.println("sqlEx2 que genera error "+sqlEx2);
                 System.out.println(t.getMessage());
                 rctx.response().setStatusCode(500).end("X");
                 
            }
        
        }).subscribe(new ListConsumerLon(dc, rctx, serviceFor, ci, tuple, sqlConds),new Consumer<Throwable>(){
            @Override
            public void accept(Throwable t) throws Throwable {
                System.out.println("YA debe de estar tirado"+t.getMessage());
            }
        });
      
    }

    private String doSqlConds00(final String sqlConds) {
        return sqlConds.length() > 0 ? " WHERE " + sqlConds : "";
    }

    protected class ListConsumerLon implements Consumer<RowSet<Row>> {

        final RoutingContext rctx;
        final HttpServerRequest request;
        final HttpServerResponse response;
        final IServiceLon<IDcLon> serviceFor;
        final ConditionInfo ci;
        final Tuple tuple;
        final String sqlConds;
        String dc;

        ListConsumerLon(String dc, RoutingContext rctx, IServiceLon<IDcLon> serviceFor, ConditionInfo ci, Tuple tuple, String sqlConds) {
            this.rctx = rctx;
            this.request = rctx.request();
            this.response = rctx.response();
            this.serviceFor = serviceFor;
            this.ci = ci;
            this.tuple = tuple;
            this.sqlConds = sqlConds;
            this.dc = dc;
        }

        @Override
        public void accept(RowSet<Row> result) throws Throwable {
            final String xlsExport = request.getParam("xlsExport");
            if (xlsExport != null && !xlsExport.isEmpty()) {
                final boolean withIds = request.getParam("withIds") != null;
                final XSSFWorkbook ww = eu.doFile(dc, serviceFor, result, withIds);
                handleAsExcelTemplateCase(ww, rctx);
                return;
            }

            final JsonArray names = getNames(serviceFor);
            final JsonArray jaf = new JsonArray();
            final JsonObject jo = new JsonObject();
            result.forEach((Row r) -> jaf.add(serviceFor.toJsonArray(r.getDelegate())));
            jo.put("l", jaf);
            jo.put("names", names);
            jo.put("max", ci.getMax());
            jo.put("offset", ci.getOffset());

            if (!ci.isWithCount()) {
                response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(jo.toBuffer());
                return;
            }

            final String sqlCount = serviceFor.getSqlCount();
            final String sqlToExCount = sqlCount + doSqlConds00(sqlConds);

           dBLon1.doPreparedQuery(sqlToExCount, tuple).subscribe(new TotalConsumerLon(jo, request, response));
        }

    }

    protected class TotalConsumerLon implements Consumer<RowSet<Row>> {

        private final JsonObject jo;
        private final HttpServerRequest request;
        private final HttpServerResponse response;

        TotalConsumerLon(JsonObject jo, HttpServerRequest request, HttpServerResponse res) {
            this.jo = jo;
            this.request = request;
            this.response = res;
        }

        private long doTotal(final RowSet<Row> result) {
            if (result == null || result.size() == 0) {
                return 0l;
            }
            RowIterator<Row> iterator = result.iterator();
            if (iterator.hasNext()) {
                Row row = iterator.next();
                return row.getLong(0);
            }
            return 0l;

        }

        @Override
        public void accept(RowSet<Row> result) throws Throwable {
            jo.put("total", doTotal(result));
            response.putHeader("content-type", "application/json").end(jo.toBuffer());
        }

    }
}


/**
 *
 *
 * // final IMap<String, Account> map = h.getMap("m_pkey_account"); //// //
 * System.out.println("Tamaño " + map.size()); //// //// map.forEach((String k,
 * Account a) -> { //// //// System.out.println("K " + k + " * " +
 * a.toString()); //// }); // // final Account a = new Account(); //
 * a.setPname("bbb"); // a.setDescription("aaa"); // a.setType("PASIVE"); //
 * a.setPkey("P001-" + System.currentTimeMillis()%59); // map.put(a.getPkey(),
 * a);
 *
 *
 *
 *
 */
//
