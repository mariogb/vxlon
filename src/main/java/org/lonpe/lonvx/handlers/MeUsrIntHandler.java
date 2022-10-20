/*
 * Copyright (C) 2022 mgb
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.lonpe.lonvx.handlers;

import com.hazelcast.map.IMap;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.lonpe.lonvx.ctes.CteLon;
import org.lonpe.model.MeUsrInterface;
import org.lonpe.services.impl.MeUsrInterfaceService;

/**
 *
 * @author mgb
 */
public class MeUsrIntHandler implements Handler<RoutingContext> {

    protected final IMap<String, MeUsrInterface> mapMUI;
    MeUsrInterfaceService serviceLon;

    public MeUsrIntHandler(IMap<String, MeUsrInterface> mapMUI, MeUsrInterfaceService serviceLon) {
        this.mapMUI = mapMUI;
        this.serviceLon = serviceLon;
    }

    @Override
    public void handle(RoutingContext rctx) {

        rctx.request().bodyHandler((var bff) -> {
            final HttpServerResponse response = rctx.response();
            final JsonObject jso = bff.toJsonObject();
            if (jso.isEmpty()) {
                response.setStatusCode(500).end(new JsonObject().put("nodata", "1").toBuffer());
                return;
            }

            final String pkey = jso.getString("pkey");

            if (pkey == null || pkey.isBlank() || pkey.isEmpty()) {
                response.setStatusCode(500).end(new JsonObject().put("nopkeydata", "1").toBuffer());
                return;
            }
            
            MeUsrInterface meUsrInterface = serviceLon.doFromJson(jso);
            
            mapMUI.put(meUsrInterface.getPkey(), meUsrInterface);
            
            response.putHeader("content-type", "application/json").end(jso.toBuffer());
                        
            

        });

    }

}
