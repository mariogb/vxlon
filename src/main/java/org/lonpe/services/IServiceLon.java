/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.services;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.lonpe.lonvx.sqlbuilders.SqlZtatBuilder;
import org.lonpe.model.AbstractDcLon;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
import java.util.Set;

/**
 *
 * @author l5
 */
public interface IServiceLon<IDcLon> {

    public void populateParentsIds(final Map<String, Object> obj, final Map<String, Map<String, Long>> mapParents);

    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t);

    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple);

    public String getSqlInsert();

    public String getSqlIUpdate();

    public String getSqlIUpdatePkey();

    public String getSqlView();

    public String getSqlKeys();

    public String getSqlCount();

    public String getTableName();

    public Set<String> getNames();

    public String getSqlByKey();

    public void fillTupleInsert(JsonObject js, Tuple t);

    public void fillTupleUpdate(JsonObject js, Tuple t);

    public void fillTupleInsert(IDcLon dc0, Tuple t);

    public void fillTupleUpdate(IDcLon dc0, Tuple t);

    public JsonArray toJsonArray(Row r);

    public IDcLon doFrom(Row r);

    public IDcLon doFromJson(JsonObject js);

    public JsonObject toJson(IDcLon o);

    public JsonObject elModelo();

    public String getSqlIdByPkey();

    public int fillXRow(Row r, XSSFRow row, int nc, boolean withIds);

    public Map<String, String> lXRowH(final boolean withIds, final int level);

    public Map<String, String> getInsertMapFields();

    public Map<String, String> getSortMapFields();

    public SqlZtatBuilder doZtat(MultiMap params);

    public abstract String getSqlKeyIn();

    public abstract String getSqlDelete();

}
