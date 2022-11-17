/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import com.hazelcast.core.HazelcastInstance;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Tuple;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.lonpe.model.IDcLon;
import org.lonpe.services.AbstractServiceLon;
import org.lonpe.services.ConditionInfo;
import org.lonpe.services.IServiceLon;
import org.lonpe.services.impl.DcMapForServices;

/**
 *
 * @author l5
 */
public abstract class AbstractGenericList {

    protected static final Logger log = LoggerFactory.getLogger(GenericListHandler.class);
    private static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_PURPLE = "\u001B[35m";
    protected static final String ANSI_CYAN = "\u001B[36m";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final Map<String, JsonArray> mapNames = new HashMap<>();
    protected static final String EQ_PKEY = "eq_pkey";
    protected final DcMapForServices dcMapForServices;
    protected final ExcelUtilLon eu;
    //private final Map<String, String> msgui = new HashMap<>();
    protected final HazelcastInstance h;
    protected boolean examina = false;

    /**
     *
     */
    AbstractGenericList(HazelcastInstance h, DcMapForServices dcMapForServices) {

        this.h = h;
        this.dcMapForServices = dcMapForServices;
        this.eu = new ExcelUtilLon(h);

    }

    protected void ml(Object o) {
        log.info(ANSI_CYAN + "## \t " + o + ANSI_RESET);
    }

    protected void ml(Object o, Object o2) {
        log.info(ANSI_PURPLE + "##  >" + o + " , " + o2 + " " + ANSI_RESET);
    }

    protected void examine(HttpServerRequest request) {
        MultiMap params = request.params();
        ml("QUERY " + request.query());
        params.forEach((Map.Entry<String, String> p) -> {
            ml("pk ", p.getKey());
            ml("pv", p.getValue());
        });
        params.names().forEach((String t) -> {
            ml("----> parametro: ", t);
            params.getAll(t).stream().forEach((String ve) -> ml("\t " + ve));
        });
    }

    protected JsonArray getNames(final IServiceLon<IDcLon> serviceLon) {
        final String clzz = serviceLon.getClass().getName();
        final JsonArray jsa = mapNames.get(clzz);
        if (jsa != null) {
            return jsa;
        }
        final JsonArray jsn = new JsonArray();
        serviceLon.getNames().stream().forEach(s -> jsn.add(s));
        mapNames.put(clzz, jsn);
        return jsn;
    }

    protected void handleAsExcelTemplateCase(final XSSFWorkbook ww, final RoutingContext rctx) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ww.write(baos);
            ww.close();
            Buffer buff = Buffer.buffer(baos.toByteArray());
            rctx.attachment("excelTemplate").end(buff);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            rctx.response().setStatusCode(500).end(new JsonObject().put("ERROR", ex.getMessage()).toBuffer());
        }
    }

    protected void filterByMemebership(JsonObject u0, final MultiMap params, final String jwtKey, final String mbKey) {
        final JsonArray b = u0.getJsonArray(jwtKey);
        if (b == null || b.size() == 0) {
            params.set(mbKey, "___");
            return;
        }
        List<String> listPkey2 = null;
        final List<String> listPkey = params.getAll(mbKey);
        if (listPkey != null) {
            listPkey2 = listPkey.stream().filter(b::contains).collect(Collectors.toList());
            ml("\t 1)  " + mbKey, "l_Pkey2" + listPkey2);
        }
        if (listPkey2 == null || listPkey2.isEmpty()) {
            listPkey2 = b.stream().map(n -> n.toString()).collect(Collectors.toList());
            ml("\t 2)  " + mbKey, "l_Pkey2" + listPkey2);
        }
        params.set(mbKey, listPkey2);
    }

    protected void applyFiltersToMemeberships(final String dc, final RoutingContext rctx) {
        final MultiMap params = rctx.queryParams();
        final JsonObject u0 = rctx.get("u0");
        final String typeLon = u0.getString("typeLon");
        if (!"ADM".equals(typeLon)) {
            switch (dc) {
                case "departament":
                    filterByMemebership(u0, params, "d", EQ_PKEY);
                    break;
                case "base":
                    filterByMemebership(u0, params, "b", EQ_PKEY);
                    break;
                case "program":
                    filterByMemebership(u0, params, "p", EQ_PKEY);
                    break;
                default:
                    filterByMemebership(u0, params, "d", "eq_departament_pkey");
                    filterByMemebership(u0, params, "b", "eq_base_pkey");
                    filterByMemebership(u0, params, "p", "eq_program_pkey");
                    break;
            }
            if ("AGENT".equals(typeLon) || "THIRD".equals(typeLon)) {
                final String pkey = u0.getString("pkey");
                final String searchCriteria = "userLon".equals(dc) ? EQ_PKEY : "eq_userLon_pkey";
                params.set(searchCriteria, pkey);
            }
        }
    }

    protected String buildSqlString(final AbstractServiceLon serviceFor, final MultiMap params, final Tuple tuple) {
        final ConditionInfo ci = serviceFor.doCondiciones(params, tuple);
        final Set<String> conditions = ci.getCondiciones();
        final String sqlConds = conditions.stream().collect(Collectors.joining(" AND "));
        final String sql0 = serviceFor.getSqlView();
        final String sqlEx = sqlConds.length() > 0 ? sql0 + " WHERE " + sqlConds : sql0;
        final String the_order = ci.getOrden();
        return sqlEx + (" " + the_order + " offset " + ci.getOffset() + " limit " + ci.getMax());
    }

}
