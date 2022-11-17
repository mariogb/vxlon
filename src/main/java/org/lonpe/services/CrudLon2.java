/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.services;

//import io.vertx.pgclient.PgPool;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.rxjava3.impl.AsyncResultSingle;
import io.vertx.rxjava3.pgclient.PgPool;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;
import io.vertx.sqlclient.PoolOptions;

/**
 *
 * @author l5
 */
public class CrudLon2 {

    PgPool pgPool;

    public CrudLon2(PgConnectOptions pgConnOpts) {
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(25);

        this.pgPool = PgPool.pool(pgConnOpts, poolOptions);

    }

//    private Single<RowSet<Row>> preparedQuery0(String sql, Tuple tuple) throws Throwable{
//
//
//        return AsyncResultSingle.toSingle((Handler<AsyncResult<RowSet<Row>>> handler) ->{
//            
//            handler.handle(Future.succeededFuture(Launcher.executeCommand(sql, args)));
//            
//            Single<RowSet<Row>> execute = pgPool.preparedQuery(sql). execute (tuple);
//           
//          //  if(execute.)
//            
//        });
////
////
////
////        
////        Future<RowSet<Row>> execute = pgPool.preparedQuery(sql).
////                execute(tuple);
////        if(execute.succeeded()){
////            return execute.result();
////        }
////         throw execute.cause();
////        
////
//    }
//    public Single<Map> doList(String sql, Tuple t) {
//       return  preparedQuery0(sql, t).map(new TransformerMap());
//    }
}
