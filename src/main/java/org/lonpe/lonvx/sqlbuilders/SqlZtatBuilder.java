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
                        .map(n -> Long.parseLong(n))
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
/*
    public void applyG1__(final String fld1, final String dc2, final String t2, final String dc2_pn,final String t2Alias) {
        applyG100("xID", fld1, dc2, t2, dc2_pn,t2Alias);
    }
  */  
    public void applyG1(final  ZtatUnitInfoLon z) {
        applyG100("xID", z.getForeingTableField(), z.getDc(), z.getTable(), z.getAlias(), z.getDcPc());
    }
    
/*
    public void applyG1xEqInPkey__(final String fld1, final String dc2, final String t2, final String t2Alias,final String dc2_pn) {
        applyG100("xPKEY", fld1, dc2, t2,t2Alias, dc2_pn);
    }
*/
    private void applyG100(final String xEndix, final String fld1, final String dc2, final String t2,final String t2Alias, final String dc2_pn) {

        final List<String> gBy0 = params.getAll("gBy_" + dc2);
        final String xEndixFinal = "xID".equals(xEndix) ? ENDIXID : ENDIXPKEY;

        final List<String> list0 = params.getAll(dc2 + xEndixFinal);

        if ((gBy0 != null && !gBy0.isEmpty()) || (list0 != null && !list0.isEmpty())) {

            //final String p2SqlName = t2;

            applyFirstRelation(fld1, t2, t2Alias);

            applyFields00(t2Alias, dc2, dc2_pn);

            if ("xID".equals(xEndix)) {
                addToConditionsAsForIds(list0, t2Alias);
            } else {
                addToConditionsAsForPkeys(list0, t2Alias);
            }

        }
    }

    private void applyG200(final String xEndix, String fld1, String t2, String t2Alias,
            String fld2, String dc3, String t3, String t3Alias,String dc3Pn) {
        final List<String> gBy0 = params.getAll("gBy_" + dc3);

        final String xEndixFinal = "xID".equals(xEndix) ? ENDIXID : ENDIXPKEY;

        final List<String> list0 = params.getAll(dc3 + xEndixFinal);
        if (gBy0 != null || (list0 != null && !list0.isEmpty())) {

           // final String p2SqlName = t2;
            applyFirstRelation(fld1, t2, t2Alias);

            //final String p3SqlName = t3;

            applySecondRelation(fld2, t3, t2Alias, t3Alias);

            applyFields00(t3Alias, dc3, dc3Pn);

            if ("xID".equals(xEndix)) {
                addToConditionsAsForIds(list0, t3Alias);
            } else {
                addToConditionsAsForPkeys(list0, t3Alias);
            }

        }
    }
//
    
    //public void applyG2__(String fld1, /*String dc2,*/ String t2,
      //      String fld2, String dc3, String t3, String dc3Pn) {
       // applyG200("xID", fld1,/* dc2,*/ t2, fld2, dc3, t3, dc3Pn);
   // }
  //  
    public void applyG2(final ZtatUnitInfoLon z1, final ZtatUnitInfoLon z2) {
        applyG200("xID",z1.getForeingTableField(), z1.getTable(), z1.getAlias(),
                z2.getForeingTableField(), z2.getDc(), z2.getTable(),z2.getAlias(), z2.getDcPc());
    }
    

//    public void applyG2xEqInPkey(String fld1, String dc2, String t2,
  //          String fld2, String dc3, String t3, String dc3Pn) {
    //    applyG200("xPKEY", fld1,/* dc2,*/ t2, fld2, dc3, t3, dc3Pn);
    //}

    //G3
    
     public void applyG3(final ZtatUnitInfoLon z1, final ZtatUnitInfoLon z2,
            final ZtatUnitInfoLon z3) {
         applyG3(z1.getForeingTableField(), z1.getTable(), z1.getAlias(),
                z2.getForeingTableField(), z2.getDc(), z2.getTable(),z2.getAlias(),
                z3.getForeingTableField(), z3.getDc(), z3.getTable(), z3.getAlias(), z3.getDcPc()     );
         
     }
    
    /**
     *
     * @param fld1
     * @param dc2
     * @param t2
     * @param fld2
     * @param dc3
     * @param t3
     * @param fld3
     * @param dc4
     * @param t4
     * @param dc4Pn
     */
    public void applyG3(String fld1, String t2,String t2Alias,
            String fld2, String dc3, String t3,String t3Alias,
            String fld3, String dc4, String t4,String t4Alias, String dc4Pn) {
        final List<String> gBy0 = params.getAll("gBy_" + dc4);
        final List<String> list0 = params.getAll(dc4 + ENDIXID);

        if (gBy0 != null || (list0 != null && !list0.isEmpty())) {

           // final String p2SqlName = t2;//

            applyFirstRelation(fld1, t2, t2Alias);

           // final String p3SqlName = t3;

            applySecondRelation(fld2, t3, t2Alias, t3Alias);

         //   final String p4SqlName = dc4;
            tables.add(t4 + " as " + t4Alias);
            relations.add(t3Alias + "." + fld3 + SQLEQUAL + t4Alias + ".id");

            applyFields00(t4Alias, dc4, dc4Pn);



            addToConditionsAsForIds(list0, t4Alias);
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
