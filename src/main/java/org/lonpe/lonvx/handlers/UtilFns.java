/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;


import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import org.lonpe.model.IDcLon;
import org.lonpe.services.IServiceLon;

/**
 *
 * @author l5
 */
public class UtilFns {

    public static void doError(final IServiceLon<IDcLon> serviceFor, final HttpServerResponse response, final String err) {

        final JsonObject jEr = new JsonObject(err);
        final JsonObject r2 = new JsonObject();
        if (jEr.containsKey("code")) {
            r2.put("code", jEr.getString("code"));

        }

        if (jEr.containsKey("message")) {
            String msg = jEr.getString("message");
            String key = "x";
            final String detail = jEr.getString("detail");
            if (msg.startsWith("duplicate key")) {
                r2.put("msg", "duplicate_key");
                key = jEr.getString("table") + "." + jEr.getString("constraint");
                if (detail != null) {
                    final String[] split = detail.split("=");
                    if (split.length == 2) {
                        r2.put("detail", split[1]);
                    } else {
                        r2.put("detail", detail);
                    }

                }

            } else if (msg.startsWith("null value in column")) {
                key = jEr.getString("table") + "." + jEr.getString("column");
                r2.put("msg", "null value");
                if (detail != null) {
                    final String[] split = detail.split("=");
                    if (split.length == 2) {
                        r2.put("detail", split[1]);
                    } else {
                        r2.put("detail", detail);
                    }
                }

            }
                
            final Object code = jEr.getValue("code");
            if(code!=null){
                jEr.put("code", code);
            }
            r2.put("key0", key);

            r2.put("keyTuyo", serviceFor.getInsertMapFields().get(key));
        }

        //duplicate key
        response.putHeader("content-type", "application/json").setStatusCode(500).end(r2.toBuffer());
    }
}
