package org.lonpe.lonvx.handlers;

import java.time.LocalDateTime;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 *
 * @author l5
 */
public class VerifyLon {

    private static final String EMPTY = "EMPTY";
    private static final String MIN_ERROR = "MIN_ERROR";
    private static final String MAX_ERROR = "MAX_ERROR";

    public static LocalDateTime now0() {
        return LocalDateTime.now();
    }

    public static Long putCurrentUser(final JsonObject u0) {
        return u0.getLong("id");
    }

    public static void preFillGeneric(final JsonObject elModelo, final JsonObject jso, final JsonObject u0) {
       
        final JsonArray listPs = elModelo.getJsonArray("ps");

        final JsonArray lMto = elModelo.getJsonArray("mto");
        if (lMto != null) {
            final int nmto = lMto.size();
            for (int i = 0; i < nmto; i++) {
                final JsonObject p = lMto.getJsonObject(i);
                final String n = p.getString("n");
                final String setBySys = p.getString("setBySys");
                if ("putCurrentUser".equals(setBySys)) {
                    jso.put(n + "_id", putCurrentUser(u0));
                }
            }
        }

        if (listPs != null) {
            final int nps = listPs.size();
            for (int i = 0; i < nps; i++) {
                final JsonObject p = listPs.getJsonObject(i);
                final String setBySys = listPs.getJsonObject(i).getString("setBySys");
                if ("now".equals(setBySys)) {
                    jso.put(p.getString("n"), now0().toString());
                }
            }

        }

    }

    protected static void verifyLong(final String n, final Long v, final JsonObject p, final JsonArray lVerify) {
        if (v == null) {
            lVerify.add(new JsonObject().put(n, EMPTY));
        } else {
            final Long min = p.getLong("min");
            if (min != null && min > v) {
                lVerify.add(new JsonObject().put(n, MIN_ERROR));
            }
            final Long max = p.getLong("max");
            if (max != null && max <= v) {
                lVerify.add(new JsonObject().put(n, MAX_ERROR));
            }
        }

    }

    protected static void verifyInteger(final String n, final Integer v, final JsonObject p, final JsonArray lVerify) {
        if (v == null) {
            lVerify.add(new JsonObject().put(n, EMPTY));
        } else {
            final Integer min = p.getInteger("min");
            if (min != null && min > v) {
                lVerify.add(new JsonObject().put(n, MIN_ERROR));
            }
            final Integer max = p.getInteger("max");
            if (max != null && max <= v) {

                lVerify.add(new JsonObject().put(n, MAX_ERROR));
            }

        }
    }

    protected static void verifyString(final String n, final String v, final JsonObject p, final JsonArray lVerify) {

        if (v == null || v.trim().length() < 2) {
            lVerify.add(new JsonObject().put(n, EMPTY));
            return;
        }
        final JsonArray inList = p.getJsonArray("inList");
        if (inList != null) {
            final int nInList = inList.size();
            int nB = 0;
            for (int i = 0; i < nInList; i++) {
                if (inList.getString(i).equals(v)) {
                    nB++;
                    break;
                }
            }
            if (nB < 1) {
                lVerify.add(new JsonObject().put(n, "NO_INLIST"));
            }
        }

    }

    public static void verifyObj(final JsonArray lVerify, final JsonObject p, final JsonObject jso) {

        if (p.containsKey("rq") && p.getBoolean("rq")) {
            final String t = p.getString("t");
            final String n = p.getString("n");
            if (null != t) {
                switch (t) {
                    case "String": {
                        final String v = jso.getString(n);
                        verifyString(n, v, p, lVerify);
                        break;
                    }
                    case "Integer": {
                        final Integer v = jso.getInteger(n);
                        verifyInteger(n, v, p, lVerify);
                        break;
                    }
                    case "Long": {
                        final Long v = jso.getLong(n);
                        verifyLong(n, v, p, lVerify);
                        break;
                    }
                    default:
                        break;
                }
            }
        }

    }

}
