package org.lonpe.lonvx.handlers;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowIterator;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.lonpe.lonvx.ctes.CteLon;
import org.lonpe.model.IDcLon;
import org.lonpe.model.MeUsrInterface;
import org.lonpe.services.DBLon1;
import org.lonpe.services.IServiceLon;
import org.lonpe.services.impl.DcMapForServices;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author mgb
 */
public abstract class AbstractGenericSave implements Handler<RoutingContext> {

    protected static final Logger log = LoggerFactory.getLogger(GenericSaveHandler.class);
    /**
     *
     * final JsonObject jsMsg = new JsonObject().put("", mapMUI)msg);
     *
     */
    protected static final String DETAIL = "detail";
    protected final DcMapForServices dcMapForServices;
    protected final BCryptPasswordEncoder b = new BCryptPasswordEncoder();

    protected final HazelcastInstance h;
    protected final DBLon1 dBLon1;
    protected Map<String, AtomicLong> atomicos = new HashMap<>();

    AbstractGenericSave(HazelcastInstance h, DcMapForServices dcMapForServices, DBLon1 dBLon1) {
        this.dcMapForServices = dcMapForServices;
        this.h = h;

        this.dBLon1 = dBLon1;
        init();

    }

    protected void init() {
        dBLon1.doPreparedQuery("select pkey,val from atomiclon", Tuple.tuple()).subscribe(new AtomicLon(), (Throwable t) -> {
            log.error("INIT ATOMICS", t);
        });
    }

    protected void encodesPws(final JsonObject elModelo, JsonObject jso) {
        final JsonArray listPspw = elModelo.getJsonArray("pspw");
        if (listPspw != null) {
            final int npspw = listPspw.size();
            for (int i = 0; i < npspw; i++) {
                final JsonObject p = listPspw.getJsonObject(i);
                final String n = p.getString("n");
                final String v = jso.getString(n);
                jso.put(n, b.encode(v));
            }
        }
    }

    protected Single<Map<String, Map<String, Object>>> doParents(final JsonObject elModelo, JsonObject jso) {
        final JsonArray lMto = elModelo.getJsonArray("mto");
        if (lMto == null || lMto.isEmpty()) {
            final Map<String, Map<String, Object>> m00 = new HashMap<>();
            return Single.just(m00);
        }
        final int nmto = lMto.size();
        final List<Observable<Map<String, Object>>> lo = new ArrayList<>();
        for (int i = 0; i < nmto; i++) {
            final JsonObject p = lMto.getJsonObject(i);
            final String n = p.getString("n");
            final String t = p.getString("t");
            final IServiceLon<IDcLon> serviceForDCParent = dcMapForServices.getServiceFor(t);
            final Long dc1Id = jso.getLong(n + "_id");
            final String sqlF = serviceForDCParent.getSqlView() + " WHERE  " + serviceForDCParent.getTableName() + "_id = $1";
            final Single<Map<String, Object>> doEx = dBLon1.doExecuteForOneResult(sqlF, Tuple.of(dc1Id));
            lo.add(doEx.toObservable());
        }
        final Observable<Map<String, Object>> concat = Observable.concat(lo);
        return concat.toList().map((List<Map<String, Object>> ldM) -> {
            final Map<String, Map<String, Object>> mapParents2 = new HashMap<>();
            for (int i = 0; i < nmto; i++) {
                final JsonObject jsMto = lMto.getJsonObject(i);
                mapParents2.put(jsMto.getString("n"), ldM.get(i));
            }
            return mapParents2;
        });
    }

    protected JsonArray verify(final JsonObject elModelo, JsonObject jso) {
        final JsonArray listPs = elModelo.getJsonArray("ps");

        final JsonArray listVerify = new JsonArray();
        if (listPs != null) {
            final int nps = listPs.size();
            for (int i = 0; i < nps; i++) {
                final JsonObject p = listPs.getJsonObject(i);
                VerifyLon.verifyObj(listVerify, p, jso);
            }
        }
        return listVerify;
    }

    /**
     *
     * Handles error and send details in and end response.
     *
     * @param serviceFor
     * @param response
     * @param err
     */
    protected void doError(final IServiceLon<IDcLon> serviceFor, final HttpServerResponse response, final String err) {
        final JsonObject jEr;
        final JsonObject r2 = new JsonObject();
        if (!err.startsWith("{")) {
            jEr = new JsonObject().put("message", err);
        } else {
            jEr = new JsonObject(err);
            r2.put("ERROR2", err);
        }
        final String code = jEr.getString("code");
        if (code != null) {
            r2.put("code", code);
        }
        final String msg = jEr.getString("message");
        if (msg != null) {
            String key = "x";
            final String detail = jEr.getString(DETAIL);
            if (msg.startsWith("duplicate key")) {
                r2.put("msg", "duplicate_key");
                key = jEr.getString("table") + "." + jEr.getString("constraint");
                if (detail != null) {
                    final String[] split = detail.split("=");
                    if (split.length == 2) {
                        r2.put(DETAIL, split[1]);
                    } else {
                        r2.put(DETAIL, detail);
                    }
                }
            } else if (msg.startsWith("null value in column")) {
                key = jEr.getString("table") + "." + jEr.getString("column");
                r2.put("msg", "null value");
            }
            r2.put("key0", key);
            r2.put("keyTuyo", serviceFor.getInsertMapFields().get(key));
        }
        //duplicate key
        response.putHeader("content-type", "application/json").setStatusCode(500).end(r2.toBuffer());
    }

    private class AtomicLon implements Consumer<RowSet<Row>> {

        @Override
        public void accept(RowSet<Row> result) throws Throwable {

            RowIterator<Row> iterator = result.iterator();
            while (iterator.hasNext()) {
                final Row row = iterator.next();
                AtomicLong al = new AtomicLong();
                al.set(row.getLong("val"));
                atomicos.put(row.getString("pkey"), al);
            }

        }

    }

}
