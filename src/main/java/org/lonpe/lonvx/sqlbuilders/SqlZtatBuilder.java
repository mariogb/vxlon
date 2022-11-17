package org.lonpe.lonvx.sqlbuilders;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.vertx.core.MultiMap;

/**
 *
 * @author l5
 */
public class SqlZtatBuilder {

    private final MultiMap params;
    private final String table1;
    private static final String SQLEQUAL = " = ";
    private static final String ENDIXPKEY = "_pkey";
    private static final String ENDIXID = "_id";
    private static final String COMA = ",";

    private final Set<String> fields = new LinkedHashSet<>();
    private final Set<String> gFields = new LinkedHashSet<>();

    private final Set<String> names = new LinkedHashSet<>();

    private final Set<String> tables = new LinkedHashSet<>();

    private final Set<String> relations = new LinkedHashSet<>();
    private final Set<String> condiciones = new LinkedHashSet<>();

    public SqlZtatBuilder(MultiMap params, String table1) {
        this.params = params;
        this.table1 = table1;
        tables.add(table1);
    }

    public SqlZtatBuilder addTable(final String t) {
        tables.add(t);
        return this;
    }

    public SqlZtatBuilder addField(final String f) {
        fields.add(f);
        return this;
    }

    public SqlZtatBuilder addGfield(final String f) {
        gFields.add(f);
        return this;
    }

    public SqlZtatBuilder addName(final String f) {
        getNames().add(f);
        return this;
    }

    public SqlZtatBuilder addRelation(final String r) {
        relations.add(r);
        return this;
    }

    public SqlZtatBuilder addCondition(final String c) {
        condiciones.add(c);
        return this;
    }

    private void addToConditionsAsForIds(final List<String> list0, final String pXXSqlName) {
        if (list0 != null && !list0.isEmpty()) {
            final long count = list0.stream().count();
            if (count > 0 && count < 101) {
                final String losIds = list0.stream().filter(n -> n.length() > 0 && n.matches("[0-9]+"))
                        .map(Long::parseLong)
                        .map(n -> n.toString())
                        .collect(Collectors.joining(COMA));
                condiciones.add(pXXSqlName + ".id IN (" + losIds + ")");
            }
        }
    }

    private void addToConditionsAsForPkeys(final List<String> list0, final String pXXSqlName) {
        if (list0 != null && !list0.isEmpty()) {
            final long count = list0.stream().count();
            if (count > 0 && count < 101) {
                final String losPkeys = list0.stream()
                        .map(n -> n.trim().replace("'", "").replace("--", "__")).filter(n -> n.length() > 0)
                        .collect(Collectors.joining(COMA));
                condiciones.add(pXXSqlName + ".pkey IN (" + losPkeys + ")");
            }
        }
    }

    private void applyFields00(final String pxxxSqlName, final String dcxxx, final String dcxxx_pn) {
        fields.add(pxxxSqlName + ".id as " + pxxxSqlName + ENDIXID);
        gFields.add(pxxxSqlName + ".id");
        getNames().add(dcxxx + ENDIXID);

        fields.add(pxxxSqlName + ".pkey as " + pxxxSqlName + ENDIXPKEY);
        gFields.add(pxxxSqlName + ".pkey");
        getNames().add(dcxxx + ENDIXPKEY);

        if (dcxxx_pn != null && !"null".equals(dcxxx_pn)) {

            fields.add(pxxxSqlName + "." + dcxxx_pn + " as " + pxxxSqlName + "_" + dcxxx_pn);
            gFields.add(pxxxSqlName + "." + dcxxx_pn);
            getNames().add(dcxxx + "_" + dcxxx_pn);
        }
    }

    private void applyFirstRelation(final String fld1, final String t2, final String t2Alias) {
        tables.add(t2 + " as " + t2Alias);
        relations.add(table1 + "." + fld1 + SQLEQUAL + t2Alias + ".id");
    }

    private void applySecondRelation(final String fld2, final String t3, final String p2SqlName, final String p3SqlName) {
        tables.add(t3 + " as " + p3SqlName);
        relations.add(p2SqlName + "." + fld2 + SQLEQUAL + p3SqlName + ".id");
    }

    public void applyG1(final ZtatUnitInfoLon z) {
        applyG100("xID", z.getForeingTableField(), z.getDc(), z.getTable(), z.getAlias(), z.getDcPc());
    }

    private void applyG100(final String xEndix, final String fld1, final String dc2, final String t2, final String t2Alias, final String dc2_pn) {

        final List<String> gBy0 = params.getAll("gBy_" + dc2);
        final String xEndixFinal = "xID".equals(xEndix) ? ENDIXID : ENDIXPKEY;

        final List<String> list0 = params.getAll(dc2 + xEndixFinal);

        if ((gBy0 != null && !gBy0.isEmpty()) || (list0 != null && !list0.isEmpty())) {

            applyFirstRelation(fld1, t2, t2Alias);

            applyFields00(t2Alias, dc2, dc2_pn);

            if ("xID".equals(xEndix)) {
                addToConditionsAsForIds(list0, t2Alias);
            } else {
                addToConditionsAsForPkeys(list0, t2Alias);
            }

        }
    }

    public void applyG2(final ZtatUnitInfoLon2 z2) {
        applyG2001("xID", z2);
    }

    private void applyG2001(final String xEndix, final ZtatUnitInfoLon2 z2) {

        final String dc3 = z2.getDc();
        

        final List<String> gBy0 = params.getAll("gBy_" + dc3);
        

        final String xEndixFinal = "xID".equals(xEndix) ? ENDIXID : ENDIXPKEY;

        final List<String> list0 = params.getAll(dc3 + xEndixFinal);
        if (gBy0.size() > 0 || list0.size() > 0) {

            final ZtatUnitInfoLon z1 = z2.getZtatUnitInfoLon();
            final String fld1 = z1.getForeingTableField();
            final String t2 = z1.getTable();
            final String t2Alias = z1.getAlias();

            final String fld2 = z2.getForeingTableField();

            final String t3 = z2.getTable();
            final String t3Alias = z2.getAlias();
            final String dc3Pn = z2.getDcPc();

            applyFirstRelation(fld1, t2, t2Alias);

            applySecondRelation(fld2, t3, t2Alias, t3Alias);

            applyFields00(t3Alias, dc3, dc3Pn);

            if ("xID".equals(xEndix)) {
                addToConditionsAsForIds(list0, t3Alias);
            } else {
                addToConditionsAsForPkeys(list0, t3Alias);
            }

        }

        /*
        final String xEndix, String fld1, String t2, String t2Alias,
            String fld2, String dc3, String t3, String t3Alias,String dc3Pn
        
         */
    }

    public void applyG3(final ZtatUnitInfoLon3 z3) {
        applyG3001("xID", z3);

    }

    private void applyG3001(final String xEndix, final ZtatUnitInfoLon3 z3) {

        final String dc4 = z3.getDc();

        final List<String> gBy0 = params.getAll("gBy_" + dc4);
        final List<String> list0 = params.getAll(dc4 + ENDIXID);

        if (gBy0.size() > 0 || list0.size() > 0) {

            final ZtatUnitInfoLon2 z2 = z3.getZtatUnitInfoLon2();
            final ZtatUnitInfoLon z1 = z2.getZtatUnitInfoLon();
            final String fld1 = z1.getForeingTableField();
            final String t2 = z1.getTable();
            final String t2Alias = z1.getAlias();

            final String fld2 = z2.getForeingTableField();

            final String t3Alias = z2.getAlias();

            final String fld3 = z3.getForeingTableField();

            final String t4 = z3.getTable();
            final String t4Alias = z3.getAlias();
            final String dc4Pn = z3.getDcPc();

            applyFirstRelation(fld1, t2, t2Alias);
            applySecondRelation(fld2, z2.getTable(), t2Alias, t3Alias);
            tables.add(t4 + " as " + t4Alias);
            relations.add(t3Alias + "." + fld3 + SQLEQUAL + t4Alias + ".id");
            applyFields00(t4Alias, dc4, dc4Pn);

            if ("xID".equals(xEndix)) {
                addToConditionsAsForIds(list0, t4Alias);
            } else {
                addToConditionsAsForPkeys(list0, t4Alias);
            }

        }
    }

    public String buildSQL() {

        final StringBuilder sb = new StringBuilder();

        sb.append("SELECT ");

        sb.append(fields.stream().collect(Collectors.joining(", ")));
        sb.append(" FROM ");
        sb.append(tables.stream().collect(Collectors.joining(", ")));
        final boolean hasRelations = !relations.isEmpty();
        if (hasRelations) {
            sb.append(" WHERE ");
            sb.append(relations.stream().collect(Collectors.joining(" AND  ")));
        }

        if (!condiciones.isEmpty()) {
            if (hasRelations) {
                sb.append(" AND ");
            } else {
                sb.append(" WHERE ");
            }
            sb.append(condiciones.stream().collect(Collectors.joining(" AND ")));
        }
        if (!gFields.isEmpty()) {
            sb.append(" GROUP BY ");
            sb.append(gFields.stream().collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }

    /**
     * @return the names
     */
    public Set<String> getNames() {
        return names;
    }
}
