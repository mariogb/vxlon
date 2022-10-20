/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.sqlbuilders;

/**
 *
 * @author l5
 */
public class ZtatUnitInfoLon {
    
     private final String foreingTableField;
     private final String dc;
     private final String table;
     private final String dcPc;
     private String alias;
     
     
        

    public ZtatUnitInfoLon(String foreingTableField, String dc, String table, String dcPc, String alias) {
        this.foreingTableField = foreingTableField;
        this.dc = dc;
        this.table = table;
        this.dcPc = dcPc;
        this.alias = alias;
    }

    public String getDc() {
        return dc;
    }

    public String getDcPc() {
        return dcPc;
    }

    public String getForeingTableField() {
        return foreingTableField;
    }

    public String getTable() {
        return table;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
    
}
