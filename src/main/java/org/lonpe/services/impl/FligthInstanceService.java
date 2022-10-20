package org.lonpe.services.impl;

import io.vertx.core.MultiMap;
import java.util.Map;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;
import io.vertx.core.json.JsonArray;
import org.lonpe.model.*;
import io.vertx.sqlclient.Row;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.lonpe.lonvx.sqlbuilders.SqlZtatBuilder;
import org.lonpe.services.AbstractServiceLon;
import org.lonpe.services.ConditionInfo;
import org.lonpe.lonvx.sqlbuilders.SqlLonConditionsBuilder;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.time.LocalDateTime;

import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;

/**
 * FligthInstanceService
 *
 */
public class FligthInstanceService extends AbstractServiceLon<FligthInstance> {

    private static final String SQLINSERT = "INSERT INTO fligth_instance(pkey,in_local_date_time,out_local_date_time,pname,the_fligth_id) VALUES ($1,$2,$3,$4,$5) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE fligth_instance SET in_local_date_time = $1,out_local_date_time = $2,pname = $3 WHERE id = $6 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE fligth_instance SET in_local_date_time = $1,out_local_date_time = $2,pname = $3 WHERE pkey = $6 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM fligth_instance_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM fligth_instance_view";
    private static final String SQLKEYS = "SELECT fligth_instance_pkey FROM fligth_instance_view";
    private static final String SQLIDBYPKEY = "SELECT id from fligth_instance WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from fligth_instance WHERE pkey in ($1)";
    private static final String SQLDELETE = "DELETE FROM fligth_instance WHERE id = $1 returning id";
    private static final String TABLENAME = "fligth_instance";

//1
    private static final ZtatUnitInfoLon zTheFligth;

//2
    private static final ZtatUnitInfoLon zFromAirport;

//2
    private static final ZtatUnitInfoLon zToAirport;

//2
    private static final ZtatUnitInfoLon zPlane;

//3
    private static final ZtatUnitInfoLon zLaCompania;

    @Override
    public String getTableName() {
        return TABLENAME;
    }

    @Override
    public String getSqlDelete() {
        return SQLDELETE;
    }

    @Override
    public String getSqlKeyIn() {
        return SQLLKEYIN;
    }

    /**
     *
     * private static String sql00 = "SELECT fligth_instance.id as
     * fligth_instance_id, fligth_instance.pkey as fligth_instance_pkey,
     * fligth_instance.in_local_date_time as fligth_instance_in_local_date_time,
     * fligth_instance.out_local_date_time as
     * fligth_instance_out_local_date_time, fligth_instance.pname as
     * fligth_instance_pname, the_fligth.id as the_fligth_id,the_fligth.pkey as
     * the_fligth_pkey,the_fligth.pname as the_fligth_pname, from_airport.id as
     * from_airport_id, from_airport.pkey as
     * from_airport_pkey,from_airport.pname as from_airport_pname, to_airport.id
     * as to_airport_id, to_airport.pkey as to_airport_pkey,to_airport.pname as
     * to_airport_pname, plane.id as plane_id, plane.pkey as
     * plane_pkey,plane.pname as plane_pname, la_compania.id as la_compania_id,
     * la_compania.pkey as la_compania_pkey,la_compania.pname as
     * la_compania_pname FROM fligth_instance, fligth as the_fligth, airport as
     * from_airport, airport as to_airport, plane as plane, air_company as
     * la_compania WHERE fligth_instance.the_fligth_id = the_fligth.id AND
     * the_fligth.from_airport_id = from_airport.id AND the_fligth.to_airport_id
     * = to_airport.id AND the_fligth.plane_id = plane.id AND
     * plane.la_compania_id = la_compania.id; "
     */
    @Override
    public String getSqlKeys() {
        return SQLKEYS;
    }

    @Override
    public String getSqlCount() {
        return SQLCOUNT;
    }

    @Override
    public String getSqlIdByPkey() {
        return SQLIDBYPKEY;
    }

    /**
     * sql select property alias field names
     */
    private static final LinkedHashSet<String> names;

    /**
     * Map field insert/update to property
     */
    private static final HashMap<String, String> insertMapFields;

    /**
     * Map property to field order
     */
    private static final HashMap<String, String> sortMapFields;

    private static final JsonObject dcModel;

    static {
        names = new LinkedHashSet<>();
        insertMapFields = new HashMap<>();
        sortMapFields = new HashMap<>();

        dcModel = new JsonObject();

        dcModel.put("dc", "fligthInstance");

//ID 
        names.add("id");

        sortMapFields.put("id", "fligth_instance_id");

        final JsonArray ps = new JsonArray();

//pkey
        names.add("pkey");
        insertMapFields.put("fligth_instance.pkey", "pkey");

//Create property pkey       
        final JsonObject pkey = ps00a("pkey", "String", true);

//Used to map error on index to source property because IS Unique
        insertMapFields.put("fligth_instance.fligth_instance_uidx_pkey", "pkey");

// IS Unique     
        pkey.put("uq", true);

        sortMapFields.put("pkey", "fligth_instance_pkey");

        ps.add(pkey);

//inLocalDateTime
        names.add("inLocalDateTime");
        insertMapFields.put("fligth_instance.in_local_date_time", "inLocalDateTime");

//Create property inLocalDateTime       
        final JsonObject inLocalDateTime = ps00a("inLocalDateTime", "LocalDateTime", true);

        sortMapFields.put("inLocalDateTime", "fligth_instance_in_local_date_time");

        ps.add(inLocalDateTime);

//outLocalDateTime
        names.add("outLocalDateTime");
        insertMapFields.put("fligth_instance.out_local_date_time", "outLocalDateTime");

//Create property outLocalDateTime       
        final JsonObject outLocalDateTime = ps00a("outLocalDateTime", "LocalDateTime", true);

        sortMapFields.put("outLocalDateTime", "fligth_instance_out_local_date_time");

        ps.add(outLocalDateTime);

//pname
        names.add("pname");
        insertMapFields.put("fligth_instance.pname", "pname");

//Create property pname       
        final JsonObject pname = ps00a("pname", "String", true);

        sortMapFields.put("pname", "fligth_instance_pname");

//PC
        dcModel.put("pc", "pname");

        ps.add(pname);

//Add ps to model            
        dcModel.put("ps", ps);

        final JsonArray mto = new JsonArray();

//(1)  theFligth --------------------
        names.add("theFligth_id");
        insertMapFields.put("fligth_instance.the_fligth_id", "theFligth_id");

        names.add("theFligth_pkey");
        sortMapFields.put("theFligth_pkey", "the_fligth_pkey");

        final JsonObject theFligth = doMto("theFligth", "fligth");

        names.add("theFligth_pname");
        sortMapFields.put("theFligth_pname", "the_fligth_pname");

        theFligth.put("pc", "pname");

        mto.add(theFligth);

        dcModel.put("mto", mto);

        final JsonArray mto2 = new JsonArray();
//(2)   fromAirport 

        names.add("fromAirport_id");
        names.add("fromAirport_pkey");

        final JsonObject fromAirport = doMto2("fromAirport", "airport", "theFligth");

        names.add("fromAirport_pname");
        fromAirport.put("pc", "pname");

        sortMapFields.put("fromAirport_pname", "from_airport_pname");

        mto2.add(fromAirport);
//(2)   toAirport 

        names.add("toAirport_id");
        names.add("toAirport_pkey");

        final JsonObject toAirport = doMto2("toAirport", "airport", "theFligth");

        names.add("toAirport_pname");
        toAirport.put("pc", "pname");

        sortMapFields.put("toAirport_pname", "to_airport_pname");

        mto2.add(toAirport);
//(2)   plane 

        names.add("plane_id");
        names.add("plane_pkey");

        final JsonObject plane = doMto2("plane", "plane", "theFligth");

        names.add("plane_pname");
        plane.put("pc", "pname");

        sortMapFields.put("plane_pname", "plane_pname");

        mto2.add(plane);

        dcModel.put("mto2", mto2);

        final JsonArray mto3 = new JsonArray();
//(3)   laCompania 

        names.add("laCompania_id");
        names.add("laCompania_pkey");

        final JsonObject laCompania = doMto2("laCompania", "airCompany", "plane");

        names.add("laCompania_pname");
        laCompania.put("pc", "pname");

        sortMapFields.put("laCompania_pname", "la_compania_pname");

        mto3.add(laCompania);

        dcModel.put("mto3", mto3);

//1  the_fligth  -- the_fligth_id
        zTheFligth = new ZtatUnitInfoLon("the_fligth_id", "theFligth", "fligth", "pname", "the_fligth");

//2    
        zFromAirport = new ZtatUnitInfoLon("from_airport_id", "fromAirport", "airport", "pname", "from_airport");

//2    
        zToAirport = new ZtatUnitInfoLon("to_airport_id", "toAirport", "airport", "pname", "to_airport");

//2    
        zPlane = new ZtatUnitInfoLon("plane_id", "plane", "plane", "pname", "plane");

//3
        zLaCompania = new ZtatUnitInfoLon("la_compania_id", "laCompania", "air_company", "pname", "la_compania");

    }

    @Override
    public LinkedHashSet<String> getNames() {
        return names;
    }

    @Override
    public HashMap<String, String> getInsertMapFields() {
        return insertMapFields;
    }

    @Override
    public HashMap<String, String> getSortMapFields() {
        return sortMapFields;
    }

    @Override
    public JsonObject elModelo() {
        return dcModel;
    }

    @Override
    public JsonArray toJsonArray(final Row r) {
        final JsonArray jsa = new JsonArray();
        jsa.add(r.getLong("fligth_instance_id"));
        jsa.add(r.getString("fligth_instance_pkey"));
        jsa.add(r.getLocalDate("fligth_instance_in_local_date_time").toString()); // undefined
        jsa.add(r.getLocalDate("fligth_instance_out_local_date_time").toString()); // undefined
        jsa.add(r.getString("fligth_instance_pname"));
        jsa.add(r.getLong("the_fligth_id"));
        jsa.add(r.getString("the_fligth_pkey"));
        jsa.add(r.getString("the_fligth_pname"));
        jsa.add(r.getLong("from_airport_id"));
        jsa.add(r.getString("from_airport_pkey"));
        jsa.add(r.getString("from_airport_pname"));
        jsa.add(r.getLong("to_airport_id"));
        jsa.add(r.getString("to_airport_pkey"));
        jsa.add(r.getString("to_airport_pname"));
        jsa.add(r.getLong("plane_id"));
        jsa.add(r.getString("plane_pkey"));
        jsa.add(r.getString("plane_pname"));
        jsa.add(r.getLong("la_compania_id"));
        jsa.add(r.getString("la_compania_pkey"));
        jsa.add(r.getString("la_compania_pname"));
        return jsa;
    }

    @Override
    public void fillXRow(final Row r, final XSSFRow row, int nc, boolean withIds) {
        fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String, String> lXRowH(final boolean withIds, final int level) {

        final LinkedHashMap<String, String> m_ = new LinkedHashMap<>();
        if (withIds) {
            m_.put("fligthInstance_id", "Long");
        }

        //pkey       
        m_.put("fligthInstance_pkey", "String");

        //inLocalDateTime       
        m_.put("fligthInstance_inLocalDateTime", "LocalDateTime");

        //outLocalDateTime       
        m_.put("fligthInstance_outLocalDateTime", "LocalDateTime");

        //pname       
        m_.put("fligthInstance_pname", "String");

        if (level < 1) {
            return m_;
        }

        // theFligth
        if (withIds) {
            m_.put("theFligth_id", "Long");

        }

        m_.put("theFligth_pkey", "String");

        m_.put("theFligth_pname", "String");

//[2] fromAirport  
        if (level > 1) {
            if (withIds) {
                m_.put("fromAirport_id", "Long");
            }

            m_.put("fromAirport_pkey", "String");

            m_.put("fromAirport_pname", "String");

        }
//[2] toAirport  

        if (level > 1) {
            if (withIds) {
                m_.put("toAirport_id", "Long");
            }

            m_.put("toAirport_pkey", "String");

            m_.put("toAirport_pname", "String");

        }
//[2] plane  

        if (level > 1) {
            if (withIds) {
                m_.put("plane_id", "Long");
            }

            m_.put("plane_pkey", "String");

            m_.put("plane_pname", "String");

        }
//[3] laCompania  

        if (level > 2) {
            if (withIds) {
                m_.put("laCompania_id", "Long");
            }

            m_.put("laCompania_pkey", "String");

            m_.put("laCompania_pname", "String");

        }

        return m_;

    }

    private void fillXRow0(final Row r, final XSSFRow row, int nc, boolean withIds) {
        if (withIds) {
            lToCell(r, row, "fligth_instance_id", nc++);
        }

        //pkey       
        sToCell(r, row, "fligth_instance_pkey", nc++);
        //inLocalDateTime            
        ldtToCell(r, row, "fligth_instance_in_local_date_time", nc++);
        //outLocalDateTime            
        ldtToCell(r, row, "fligth_instance_out_local_date_time", nc++);
        //pname       
        sToCell(r, row, "fligth_instance_pname", nc++);
        // theFligth
        if (withIds) {
            lToCell(r, row, "the_fligth_id", nc++);
        }
        sToCell(r, row, "the_fligth_pkey", nc++);
        sToCell(r, row, "the_fligth_pname", nc++);
// fromAirport
        if (withIds) {
            lToCell(r, row, "from_airport_id", nc++);
        }
        sToCell(r, row, "from_airport_pkey", nc++);
        sToCell(r, row, "from_airport_pname", nc++);
// toAirport
        if (withIds) {
            lToCell(r, row, "to_airport_id", nc++);
        }
        sToCell(r, row, "to_airport_pkey", nc++);
        sToCell(r, row, "to_airport_pname", nc++);
// plane
        if (withIds) {
            lToCell(r, row, "plane_id", nc++);
        }
        sToCell(r, row, "plane_pkey", nc++);
        sToCell(r, row, "plane_pname", nc++);
// laCompania
        if (withIds) {
            lToCell(r, row, "la_compania_id", nc++);
        }
        sToCell(r, row, "la_compania_pkey", nc++);
        sToCell(r, row, "la_compania_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW + " WHERE fligth_instance_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final FligthInstance dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getInLocalDateTime());
        t.addLocalDateTime(dc0.getOutLocalDateTime());
        t.addString(dc0.getPname());

        if (dc0.getTheFligth() != null) {
            t.addLong(dc0.getTheFligth().getId());
        }
    }

    @Override
    public void fillTupleUpdate(final FligthInstance dc0, final Tuple t) {
        t.addLocalDateTime(dc0.getInLocalDateTime());
        t.addLocalDateTime(dc0.getOutLocalDateTime());
        t.addString(dc0.getPname());

//      if(dc0.getTheFligth()!=null){
//            t.addLong(dc0.getTheFligth().getId());
//      }
        t.addLong(dc0.getId());

    }

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {

        fTString("pkey", obj, t);

        fTLocalDateTime("inLocalDateTime", obj, t);

        fTLocalDateTime("outLocalDateTime", obj, t);

        fTString("pname", obj, t);

        fTLong("theFligth_id", obj, t);
    }

    @Override
    public void populateParentsIds(final Map<String, Object> obj, final Map<String, Map<String, Long>> mapParents) {

        final Map<String, Long> theFligth = mapParents.get("theFligth");
        final Long theFligth_id = theFligth.get((String) obj.get("theFligth_pkey"));
        obj.put("theFligth_id", theFligth_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js, final Tuple t) {

        fTString("pkey", js, t);

        fTLocalDateTime("inLocalDateTime", js, t);

        fTLocalDateTime("outLocalDateTime", js, t);

        fTString("pname", js, t);

        fTLong("theFligth_id", js, t);
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTLocalDateTime("inLocalDateTime", js, t);
        fTLocalDateTime("outLocalDateTime", js, t);
        fTString("pname", js, t);

        //     fTLong("theFligth_id",js,t);
        fTLong("id", js, t);
    }

    @Override
    public String getSqlIUpdate() {
        return SQLUPDATE;
    }

    @Override
    public String getSqlIUpdatePkey() {
        return SQLUPDATEPKEY;
    }

    @Override
    public FligthInstance doFrom(final Row r) {
        final FligthInstance fligthInstance = new FligthInstance();
        fligthInstance.setId(r.getLong("fligth_instance_id"));

        fligthInstance.setPkey(r.getString("fligth_instance_pkey"));

        fligthInstance.setInLocalDateTime(r.getLocalDateTime("fligth_instance_in_local_date_time"));

        fligthInstance.setOutLocalDateTime(r.getLocalDateTime("fligth_instance_out_local_date_time"));

        fligthInstance.setPname(r.getString("fligth_instance_pname"));

        final Fligth theFligth = new Fligth();
        theFligth.setId(r.getLong("the_fligth_id"));
        theFligth.setPkey(r.getString("the_fligth_pkey"));
        theFligth.setPname(r.getString("the_fligth_pname"));
        fligthInstance.setTheFligth(theFligth);

        final Airport fromAirport = new Airport();
        fromAirport.setId(r.getLong("from_airport_id"));
        fromAirport.setPkey(r.getString("from_airport_pkey"));
        fromAirport.setPname(r.getString("from_airport_pname"));
        theFligth.setFromAirport(fromAirport);

        final Airport toAirport = new Airport();
        toAirport.setId(r.getLong("to_airport_id"));
        toAirport.setPkey(r.getString("to_airport_pkey"));
        toAirport.setPname(r.getString("to_airport_pname"));
        theFligth.setToAirport(toAirport);

        final Plane plane = new Plane();
        plane.setId(r.getLong("plane_id"));
        plane.setPkey(r.getString("plane_pkey"));
        plane.setPname(r.getString("plane_pname"));
        theFligth.setPlane(plane);
        return fligthInstance;
    }

    @Override
    public FligthInstance doFromJson(final JsonObject js) {
        FligthInstance fligthInstance = new FligthInstance();
        fligthInstance.setId(js.getLong("id"));

        fligthInstance.setPkey(js.getString("pkey"));
        fligthInstance.setInLocalDateTime(LocalDateTime.parse(js.getString("inLocalDateTime")));
        fligthInstance.setOutLocalDateTime(LocalDateTime.parse(js.getString("outLocalDateTime")));
        fligthInstance.setPname(js.getString("pname"));
        fligthInstance.setId(js.getLong("theFligth_id"));
        return fligthInstance;
    }

    @Override
    public JsonObject toJson(final FligthInstance o) {
        final JsonObject jso = new JsonObject();
        jso.put("id", o.getId());
        jso.put("pkey", o.getPkey());
        jso.put("inLocalDateTime", o.getInLocalDateTime());
        jso.put("outLocalDateTime", o.getOutLocalDateTime());
        jso.put("pname", o.getPname());

        final Fligth theFligth = o.getTheFligth();
        if (theFligth != null) {
            jso.put("theFligth_id", theFligth.getId());
            jso.put("theFligth_pkey", theFligth.getPkey());
        }

        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple) {

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, tuple);

        //Check Id      
        slcb.doInLongCondition("id", "fligth_instance_id");
        //*---PKEY ---       
        slcb.doIlPSimple2("pkey", "fligth_instance_pkey");
        slcb.doEqInPSimple("pkey", "fligth_instance_pkey");
//*---PC ---" + pname
        slcb.doIlPSimple2("pname", "fligth_instance_pname");
        slcb.doEqInPSimple("pname", "fligth_instance_pname");

        slcb.doIlPSimple2("theFligth_pkey", "the_fligth_pkey");
        slcb.doEQPSimple2("theFligth_pkey", "the_fligth_pkey");
        slcb.doInLongCondition("theFligth_id", "the_fligth_id");
//Fligth 1        
        slcb.doIlPSimple2("theFligth_pname", "the_fligth_pname");

        slcb.doIlPSimple2("fromAirport_pkey", "from_airport_pkey");
        slcb.doEQPSimple2("fromAirport_pkey", "from_airport_pkey");
        slcb.doInLongCondition("fromAirport_id", "from_airport_id");//Airport 1
        slcb.doIlPSimple2("fromAirport_pname", "from_airport_pname");

        slcb.doIlPSimple2("toAirport_pkey", "to_airport_pkey");
        slcb.doEQPSimple2("toAirport_pkey", "to_airport_pkey");
        slcb.doInLongCondition("toAirport_id", "to_airport_id");//Airport 1
        slcb.doIlPSimple2("toAirport_pname", "to_airport_pname");

        slcb.doIlPSimple2("plane_pkey", "plane_pkey");
        slcb.doEQPSimple2("plane_pkey", "plane_pkey");
        slcb.doInLongCondition("plane_id", "plane_id");//Plane 2
        slcb.doIlPSimple2("plane_pname", "plane_pname");
        slcb.doIlPSimple2("laCompania_pkey", "la_compania_pkey");
        slcb.doEQPSimple2("laCompania_pkey", "la_compania_pkey");
        slcb.doInLongCondition("laCompania_id", "la_compania_id");

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }

    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params, "fligth_instance");
        sz0.addField("COUNT(fligth_instance.id) as c_fligth_instance_id").addName("count");

//level 1
        sz0.applyG1(zTheFligth);
        //level 2

        sz0.applyG2(zTheFligth, zFromAirport);
        sz0.applyG2(zTheFligth, zToAirport);
        sz0.applyG2(zTheFligth, zPlane);
        //level 3

        sz0.applyG3(zTheFligth, zPlane, zLaCompania);
        return sz0;
    }
}
