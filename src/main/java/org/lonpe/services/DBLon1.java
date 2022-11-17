/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lonpe.model.AbstractDcLon;
import org.lonpe.services.impl.DcMapForServices;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.pgclient.PgPool;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowIterator;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;
import io.vertx.sqlclient.PoolOptions;
import org.lonpe.model.IDcLon;

/**
 *
 * @author l5
 */
public class DBLon1 {

    private PgPool client;
    private DcMapForServices dcMapForServices;

    /**
     *
     * @param client
     * @param dcMapForServices
     */
    public DBLon1(DcMapForServices dcMapForServices) {
        this.dcMapForServices = dcMapForServices;
    }

    private PgPool doPgPool(final JsonObject config) {
        final PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(config.getInteger("POSTGRES_PORT"))
                .setHost(config.getString("POSTGRES_HOST"))
                .setDatabase(config.getString("POSTGRES_DB"))
                .setUser(config.getString("POSTGRES_USER"))
                .setPassword(config.getString("POSTGRES_PASSWORD"));

// Pool options
        final Integer poolMaxSizeConf = config.getInteger("dbPoolMaxSize");
        final Integer poolMaxSize = poolMaxSizeConf != null ? poolMaxSizeConf : 6;
        final PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(poolMaxSize);
        return PgPool.pool(Vertx.vertx(), connectOptions, poolOptions);

    }

    /**
     *
     * @param config
     * @param dcMapForServices
     */
    public DBLon1(final JsonObject config, DcMapForServices dcMapForServices) {

        this.dcMapForServices = dcMapForServices;
        this.client = doPgPool(config);
    }

    /**
     *
     * @param sql
     * @param t
     * @return
     */
    public Single<Map<String, Object>> doExecuteForOneResult(String sql, Tuple t) {

        return preparedQuery0(sql, t).flatMap((final RowSet<Row> rrs) -> {
            final Map<String, Object> m = new HashMap<>();
            if (rrs.size() > 0) {
                RowIterator<Row> it = rrs.iterator();
                while (it.hasNext()) {
                    m.put("r", it.next());
                }
            }
            return Single.just(m);
        });

    }

    /**
     *
     * @param sql
     * @param t
     * @return
     */
    public Single<RowSet<Row>> doPreparedQuery(final String sql, final Tuple t) {
        return client.preparedQuery(sql).rxExecute(t);
    }

    public Single<RowSet<Row>> doPreparedQuery(final String sql) {
        return client.preparedQuery(sql).rxExecute();
    }

    /**
     *
     * @param dc
     * @param pkey
     * @return
     */
    public IDcLon load00(String dc, String pkey) {
        final Tuple t = Tuple.of(pkey);
        final IServiceLon s = dcMapForServices.getServiceFor(dc);
        final Single<RowSet<Row>> rxExecute = client.preparedQuery(s.getSqlByKey()).rxExecute(t);
        final RowSet<Row> rrs = rxExecute.blockingGet();
        final RowIterator<Row> iterator = rrs.iterator();
        final List<IDcLon> l = new ArrayList<>();
        System.out.println("dc " + dc + " pkey " + pkey);

        //  rrs.spliterator().
        while (iterator.hasNext()) {
            final io.vertx.sqlclient.Row delegate = iterator.next().getDelegate();
            final IDcLon doFrom = (IDcLon) s.doFrom(delegate);
            System.out.println("doFrom " + doFrom != null);
            System.out.println("aaa" + doFrom.getPkey());
            l.add(doFrom);
        }
        System.out.println("ll " + l.size());
        if (l.size() > 0) {
            return (IDcLon) l.get(0);
        }
        return null;
    }

    /**
     *
     * @param dc
     * @param dc0
     */
    public void store00(String dc, AbstractDcLon dc0) {
        System.out.println("Hacer store " + dc0.toString());
        final IServiceLon<IDcLon> s = dcMapForServices.getServiceFor(dc);

        Single<RowSet<Row>> rxEx0 = client.preparedQuery(s.getSqlIdByPkey()).rxExecute(Tuple.of(dc0.getPkey()));

        Single<Long> map = rxEx0.map(new Function<RowSet<Row>, Long>() {
            @Override
            public Long apply(RowSet<Row> r00) throws Throwable {
                if (r00.size() == 1) {
                    return r00.iterator().next().getLong(0);
                }
                return null;
            }
        }).map(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) throws Throwable {
                final String sql0 = t == null ? s.getSqlInsert() : s.getSqlIUpdate();
                final Tuple tuple = Tuple.tuple();
                if (t == null) {
                    s.fillTupleInsert(dc0, tuple.getDelegate());
                } else {
                    s.fillTupleUpdate(dc0, tuple.getDelegate());
                }

                System.out.println("sqlI" + sql0);
                System.out.println("tuple.toString() " + tuple.toString());

                System.out.println("tuple.size() " + tuple.size());

                final Single<RowSet<Row>> rxExecute = client.preparedQuery(sql0).rxExecute(tuple);
                final RowSet<Row> blockingGet = rxExecute.blockingGet();
                System.out.println("XXXX \nXXXX\n blockingGet " + blockingGet.size());

                return t;
            }
        });
        map.blockingGet();
    }

    /**
     *
     * @param dc
     * @return
     */
    public Iterable<String> loadAllKeys00(String dc) {
        System.out.println("DC " + dc);
        final IServiceLon<IDcLon> s = dcMapForServices.getServiceFor(dc);
        final String sql0 = s.getSqlKeys();
        System.out.println("s.getSqlKeys()" + sql0);
        Single<RowSet<Row>> rxExecute = client.preparedQuery(sql0).rxExecute();
        final RowSet<Row> rsr = rxExecute.blockingGet();
        final List<String> l = new ArrayList<>();
        rsr.forEach((Row r) -> {
            System.out.println(">>> " + r.getString(0) + " +" + r.getString(1));
            l.add(r.getString(0));
        });
        return l;

    }

    private Single<io.vertx.rxjava3.sqlclient.RowSet<io.vertx.rxjava3.sqlclient.Row>> preparedQuery0(final String sql, final Tuple tuple) {

        return client.preparedQuery(sql).execute(tuple);

    }

    /**
     *
     * @param sql select instruction with two fields: id and pkey
     * @param t a Tuple to apply on respective sql
     * @return a Map with pkey results as keys and ids ans values
     */
    public Single<Map<String, Long>> doKV(String sql, Tuple t) {
        System.out.println("doKV " + sql);
        return preparedQuery0(sql, t).map((RowSet<Row> t1) -> {
            final Map<String, Long> m = new HashMap<>();
            final RowIterator<Row> iterator = t1.iterator();
            while (iterator.hasNext()) {
                final Row r = iterator.next();

                m.put(r.getString("pkey"), r.getLong("id"));
                System.out.println(r.getString("pkey") + "  --  " + r.getLong("id"));
            }
            return m;
        });
    }

    /**
     *
     * @param sql
     * @param batch
     * @return
     */
    public Single<RowSet<Row>> doBatch(String sql, List<Tuple> batch) {
        return client.preparedQuery(sql).executeBatch(batch);
    }

    protected void doDelete(String sql_delete, Long id) {
        Tuple tuple = Tuple.of(id);
        client.preparedQuery(sql_delete).execute(tuple).subscribe((RowSet<Row> t) -> {
            
            
        }, (Throwable t) -> {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        });

    }

}
