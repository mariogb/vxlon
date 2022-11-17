/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.lonpe.model.MeUsrInterface;
import org.lonpe.services.DBLon1;
import org.lonpe.services.impl.DcMapForServices;

import com.hazelcast.core.HazelcastInstance;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;
import org.lonpe.model.IDcLon;
import org.lonpe.services.IServiceLon;

/**
 *
 * @author l5
 */
public class GenericSaveHandler extends AbstractGenericSave {

    public GenericSaveHandler(HazelcastInstance h, DcMapForServices dcMapForServices, DBLon1 dBLon1) {
        super(h, dcMapForServices, dBLon1);
    }

    @Override
    public void handle(RoutingContext rctx) {
        final JsonObject u0 = rctx.get("u0");
        final HttpServerRequest request = rctx.request();
        final String dc = request.getParam("dc");
        final IServiceLon<IDcLon> serviceFor = dcMapForServices.getServiceFor(dc);
        if (serviceFor == null) {
            rctx.response().setStatusCode(500).end();
            return;
        }

        rctx.request().bodyHandler((var bff) -> {
            final HttpServerResponse response = rctx.response();
            final JsonObject jso = bff.toJsonObject();
            if (jso.isEmpty()) {
                response.setStatusCode(500).end(new JsonObject().put("nodata", "1").toBuffer());
                return;
            }

            final JsonObject elModelo = serviceFor.elModelo();
            final String typeLon = u0.getString("typeLon");

            final Single<Map<String, Map<String, Object>>> doParents = doParents(elModelo, jso);

            Disposable subscribe = doParents.subscribe((Map<String, Map<String, Object>> mParents) -> {
                doL(mParents);
                VerifyLon.preFillGeneric(elModelo, jso, u0);
                encodesPws(elModelo, jso);
                doFillLon(dc, mParents, jso);
                final JsonArray listVerify = verify(elModelo, jso);
                if (listVerify.size() > 0) {
                    response.setStatusCode(500).end(new JsonObject().put("verifyProblem", listVerify).toBuffer());
                    return;
                }
                log.debug("jso " + jso.encodePrettily());

                final Long id00 = jso.getLong("id");

                final String sqlI = sqlInstruction(id00, serviceFor);

                final Tuple t = Tuple.tuple();
                try {
                    if (id00 == null) {
                        serviceFor.fillTupleInsert(jso, t.getDelegate());
                    } else {
                        serviceFor.fillTupleUpdate(jso, t.getDelegate());
                    }

                } catch (Exception e) {
                    log.error("EEEE RRR LLENANDO el tuple" + e.getMessage(), e);
                    response.setStatusCode(500).end(new JsonObject().put("baddata", "1").toBuffer());
                    return;
                }

                dBLon1.doPreparedQuery(sqlI, t).subscribe(new ConsumLon(dc, id00, response), (Throwable e) -> {
                    response.setStatusCode(500).end(new JsonObject().put("ERROR", e.getMessage()).toBuffer());
                });
            });

        });

    }

    private void doL(Map<String, Map<String, Object>> mParents) {
        final Set<String> keySet = mParents.keySet();
        for (String k : keySet) {
            System.out.println("---> " + k);
            System.out.println("Val \t\t " + mParents.get(k));

        }
    }

    private void doFillLon(final String dc, final Map<String, Map<String, Object>> mParents, final JsonObject jso) {

        if ("comercialDocumentOut".equals(dc)) {
            AtomicLong al = atomicos.get("comercialDocumentOut_folio");
            if (al != null) {
                jso.put("folio", "F-" + al.get());
                jso.put("pname", "Documento comercial de compra ");
            }
            jso.put("status", "PENDENT");

            Map<String, Object> contractOut = mParents.get("contract");
            Object timePeriod_pkey = contractOut.get("timePeriod_key");
            System.out.println("timePeriod_pkey " + timePeriod_pkey);

        }
    }

    private String sqlInstruction(final Long id00, final IServiceLon<IDcLon> s) {
        return id00 == null ? s.getSqlInsert() : s.getSqlIUpdate();
    }

    protected class ConsumLon implements io.reactivex.rxjava3.functions.Consumer<RowSet<Row>> {

        private final String dc;

        private final Long id00;

        private final HttpServerResponse response;

        public ConsumLon(String dc, Long id00, HttpServerResponse response) {
            this.dc = dc;
            this.id00 = id00;
            this.response = response;
        }

        @Override
        public void accept(RowSet<Row> rr) throws Throwable {
            if (rr.size() > 0) {
                final Row r0 = rr.iterator().next();
                final Long id0 = r0.getLong(0);
                final String pkey0 = r0.getString(1);//Taked as key for imap with value returned from database
                final JsonObject jsR = new JsonObject()
                        .put("id", id0)
                        .put("pkey", pkey0);

                //In Memory Map
//                if (dc.equals("meUsrInterface")) {
//                    if (id00 != null) {
//                        mapMUI.remove(pkey0);
//                    }
//                    final MeUsrInterface mm = mapMUI.get(pkey0);
//                    final Long id = mm.getId();
//                    if (id != null) {
//                        jsR.put("MM:meUsrInterface_id", id);
//                    }
//
//                }
                response.putHeader("content-type", "application/json")
                        .end(jsR.toBuffer());
            } else {
                response.setStatusCode(500).end(new JsonObject().put("RESULT ZERO", "1").toBuffer());

            }
        }
    }
//
//    private class QueryResultHandler implements Handler<AsyncResult<RowSet<Row>>> {
//
//        private final IServiceLon<IDcLon> serviceFor;
//        private final HttpServerResponse response;
//        private final String dc;
//        private final Long id00;
//
//        public QueryResultHandler(IServiceLon<IDcLon> serviceFor, HttpServerResponse response, String dc, Long id00) {
//            this.serviceFor = serviceFor;
//            this.response = response;
//            this.dc = dc;
//            this.id00 = id00;
//        }
//
//        @Override
//        public void handle(AsyncResult<RowSet<Row>> result) {
//            if (result.failed()) {
//                final Throwable cause = result.cause();
//                log.error(cause.getMessage());
//                doError(serviceFor, response, cause.getMessage());
//                return;
//            }
//            final RowSet<Row> rr = result.result();
//            if (rr.size() > 0) {
//                final Row r0 = rr.iterator().next();
//                final Long id0 = r0.getLong(0);
//                final String pkey0 = r0.getString(1);//Taked as key for imap with value returned from database
//                final JsonObject jsR = new JsonObject()
//                        .put("id", id0)
//                        .put("pkey", pkey0);
//
//                //In Memory Map
//                if (dc.equals("meUsrInterface")) {
//                    if (id00 != null) {
//                        mapMUI.remove(pkey0);
//                    }
//                    final MeUsrInterface mm = mapMUI.get(pkey0);
//                    Long id = mm.getId();
//                    if (id != null) {
//                        jsR.put("MM:meUsrInterface_id", id);
//                    }
//
//                }
//
//                response.putHeader("content-type", "application/json")
//                        .end(jsR.toBuffer());
//            } else {
//                response.setStatusCode(500).end(new JsonObject().put("RESULT ZERO", "1").toBuffer());
//
//            }
//        }
//    }

    //            if (dc.equals("meUsrInterface")) {
//                MeUsrInterface dc00 = (MeUsrInterface) serviceFor.doFromJson(jso);
//                mapMUI.put(dc00.getPkey(), dc00);
//                JsonObject toJson = serviceFor.toJson(dc00);
//                System.out.println("MeUsrInterface toJson after put in mapMUI");
//                response.putHeader("content-type", "application/json")
//                        .end(toJson.toBuffer());
//                return;
//
//            }
}
