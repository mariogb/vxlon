/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgException;
import org.lonpe.model.IDcLon;
import org.lonpe.services.IServiceLon;

/**
 *
 * @author l5
 */
public final class UtilFns {

    public static void doError(final IServiceLon<IDcLon> serviceFor, final HttpServerResponse response, PgException pgErr) {

        System.out.println(pgErr.getMessage());
//        final JsonObject jEr = new JsonObject(err);
        final JsonObject r2 = new JsonObject();
        r2.put("ERROR", pgErr.getErrorMessage());
        r2.put("type", "PgEx");
        r2.put("code", pgErr.getCode());
        r2.put("detail", pgErr.getDetail());
        r2.put("table", pgErr.getTable());
        r2.put("constraint", pgErr.getConstraint());
        r2.put("column", pgErr.getColumn());

        response.putHeader("content-type", "application/json").setStatusCode(500).end(r2.toBuffer());
    }
}
