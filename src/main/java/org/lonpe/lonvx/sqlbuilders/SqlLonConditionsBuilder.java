package org.lonpe.lonvx.sqlbuilders;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.lonpe.services.ConditionInfo;

import io.vertx.core.MultiMap;
import io.vertx.sqlclient.Tuple;

/**
 *
 * @author l5
 */
public final class SqlLonConditionsBuilder {

    private final MultiMap params;
    private final Tuple tuple;
    private final LinkedHashSet<String> condiciones;
    private Integer numParam = 1;
    private String orden = "id";

    public SqlLonConditionsBuilder(MultiMap params, Tuple tuple) {
        this.condiciones = new LinkedHashSet<>();
        this.params = params;
        this.tuple = tuple;
    }

    public Set<String> getCondiciones() {
        return condiciones;
    }

    public Integer getNumParam() {
        return numParam;
    }

    public ConditionInfo getConditionInfo() {

        final Integer max = doMax(10, 1000);
        final Integer offset = doOffset();
        final boolean withCount = withCount();
        return new ConditionInfo(condiciones, numParam, max, offset, orden, withCount);
    }

    private static final String REGEXNUMS = "[0-9]+";

    private static final String PFX_ORDERBY = " ORDER BY ";

    public void doSQLORDEN(final HashMap<String, String> sorts) {
        final List<String> orderby = params.getAll("orderby");
        final List<String> dir0 = params.getAll("sort");
        final String dir00 = dir0 != null && !dir0.isEmpty() && dir0.get(0).equals("desc") ? "desc" : "asc";
        if (orderby == null || orderby.isEmpty()) {
            orden = PFX_ORDERBY + sorts.get("id") + " " + dir00 + " ";
            return;
        }
        final String orderByFld = sorts.get(orderby.get(0));

        if (orderByFld == null) {
            orden = PFX_ORDERBY + sorts.get("id") + " " + dir00 + " ";
            return;
        }
        orden = PFX_ORDERBY + orderByFld + " " + dir00 + " ";

    }

    public Integer doMax(final Integer maxDefault, final Integer maxmax) {
        final String max0 = params.get("max");

        if (max0 == null || !max0.matches(REGEXNUMS)) {
            return maxDefault;
        }
        final Integer max2 = Integer.parseInt(max0);

        if (max2 < 1) {
            return maxDefault;
        }
        if (max2 > maxmax) {
            return maxmax;
        }
        return max2;
    }

    public Integer doOffset() {
        final String off0 = params.get("offset");
        if (off0 == null || !off0.matches(REGEXNUMS)) {
            return 0;
        }
        final Integer off2 = Integer.parseInt(off0);
        if (off2 < 0) {
            return 0;
        }
        return off2;
    }

    public boolean withCount() {
        return "1".equals(params.get("withCount"));
    }

    public void doIdPSimple(final String tbl) {
        final List<String> id = params.getAll("id");
        if (id != null && !id.isEmpty() && id.get(0).matches(REGEXNUMS)) {

            condiciones.add(tbl + ".id = $" + numParam);
            tuple.addLong(Long.parseLong(id.get(0)));
            numParam++;

        }

    }

    public void doPSimpleLongV2(final String pn, final String tblAlias) {
        final List<String> id = params.getAll(pn);
        if (id != null && !id.isEmpty() && id.get(0).matches(REGEXNUMS)) {

            condiciones.add(tblAlias + " = $" + numParam);
            tuple.addLong(Long.parseLong(id.get(0)));
            numParam++;

        }

    }

    private String doIds(final String pndc) {
        return params.getAll(pndc)
                .stream().filter(n -> n.matches(REGEXNUMS) && n.length() > 0)
                .map(n -> Long.parseLong(n))
                .map(n -> n.toString())
                .collect(Collectors.joining(","));
    }

    public void ver00a(final String pndc, final String tbl) {
        final String c_id0 = pndc + "_id";
        if (params.contains(c_id0)) {
            final String t_id0 = tbl + ".id";
            final String losIds = doIds(pndc);//
            condiciones.add(t_id0 + " IN (" + losIds + ")");
        }
    }

    public void ver00a2(final String pndc, final String tblAliasFld) {
        if (params.contains(pndc)) {
            final String losIds = doIds(pndc);
            condiciones.add(tblAliasFld + " IN (" + losIds + ")");
        }
    }

    public void doInLongCondition(final String pndc, final String tblAliasFld) {
        if (params.contains(pndc)) {
            final String losIds = doIds(pndc);
            if (losIds.length() > 0) {
                condiciones.add(tblAliasFld + " IN (" + losIds + ")");
            }
        }
    }

    public void doEqInPSimple(final String pn, final String tbl_fld) {

        final List<String> il_p = params.getAll("eq_" + pn);

        if (il_p != null && !il_p.isEmpty()) {
            final String inVal = il_p.stream()
                    .filter((String t) -> t.matches("[a-zA-Z0-9_]+"))
                    .map((String t) -> "'" + t + "'")
                    .collect(Collectors.joining(","));

            condiciones.add(tbl_fld + " in (" + inVal + ") ");

        }

    }

    public void doIlPSimple(final String tbl, final String pn,
            final String fld) {

        final List<String> il_p = params.getAll("il_" + pn);
        if (il_p != null && !il_p.isEmpty()) {
            condiciones.add(tbl + "." + fld + " ilike $" + numParam);
            tuple.addString(il_p.get(0) + "%");
            numParam++;
        }

    }

    public void doIlPSimple2(final String pn, final String tblAliasFld) {
        final List<String> il_p = params.getAll("il_" + pn);
        if (il_p==null || il_p.isEmpty()) {
             return;
        }
        condiciones.add(tblAliasFld + " ilike $" + numParam);
        tuple.addString(il_p.get(0) + "%");
        numParam++;

    }

    public void doEQPSimple2(final String pn, final String tblAliasFld) {
        final String il_p = params.get("eq_" + pn);
        if (il_p == null) {
            return;
        }
        condiciones.add(tblAliasFld + " = $" + numParam);
        tuple.addString(il_p);
        numParam++;
    }

    public void doGEPSimpleLong(final String pn, final String tbl_fld) {

        final String gt_p = params.get("ge_" + pn);
        if (gt_p == null || gt_p.isEmpty() || !gt_p.matches(REGEXNUMS)) {
            return;
        }

        //if (gt_p != null && !gt_p.isEmpty() && gt_p.matches(REGEXNUMS)) {
        condiciones.add(tbl_fld + " >= $" + numParam);
        tuple.addLong(Long.valueOf(gt_p));
        numParam++;
        // }

    }

    public void doLPSimpleLong(final String pn, final String tbl_fld) {

        final String lt_p = params.get("lt_" + pn);
        if (lt_p == null || lt_p.isEmpty() || !lt_p.matches(REGEXNUMS)) {
            return;
        }
        //if (lt_p != null && !lt_p.isEmpty() && lt_p.matches(REGEXNUMS)) {
        condiciones.add(tbl_fld + " < $" + numParam);
        tuple.addLong(Long.valueOf(lt_p));
        numParam++;
        //}

    }

    public void doGEPSimpleInt(final String pn, final String tbl_fld) {

        final String gt_p = params.get("ge_" + pn);
        if (gt_p == null || gt_p.isEmpty() || !gt_p.matches(REGEXNUMS)) {
            return;
        }
        //if (gt_p == null || gt_p.isEmpty() || !gt_p.matches(REGEXNUMS)) {
        condiciones.add(tbl_fld + " >= $" + numParam);
        tuple.addInteger(Integer.valueOf(gt_p));
        numParam++;
        //}        
    }

    public void doLTPSimpleInt(final String pn, final String tbl_fld) {

        final String lt_p = params.get("lt_" + pn);
        if (lt_p != null && !lt_p.isEmpty() && lt_p.matches(REGEXNUMS)) {
            condiciones.add(tbl_fld + " < $" + numParam);
            tuple.addInteger(Integer.valueOf(lt_p));
            numParam++;
        }
    }

    public void doGEPSimpleLocalDate(final String pn, final String tbl_fld) {

        final String gt_p = params.get("ge_" + pn);
        if (gt_p == null || gt_p.isEmpty() || !gt_p.matches("[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
            return;
        }
        //if (gt_p != null && !gt_p.isEmpty() && gt_p.matches("[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
        condiciones.add(tbl_fld + " >= $" + numParam);
        tuple.addLocalDate(LocalDate.parse(gt_p));
        numParam++;
        //}

    }

    public void doLPSimpleLocalDate(final String pn, final String tbl_fld) {

        final String lt_p = params.get("lt_" + pn);
        if (lt_p == null || lt_p.isEmpty() || !lt_p.matches("[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
            return;
        }
        //if (lt_p != null && !lt_p.isEmpty() && lt_p.matches("[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
        condiciones.add(tbl_fld + " < $" + numParam);
        tuple.addInteger(Integer.valueOf(lt_p));
        numParam++;
        //}

    }

}
