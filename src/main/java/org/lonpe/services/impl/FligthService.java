
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
import static org.lonpe.lonvx.ctes.CteLon.*;



import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon2;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon3;

/**
 *   FligthService 
 * 
 */
  
public class FligthService extends AbstractServiceLon<Fligth>{

    private static final String SQLINSERT ="INSERT INTO fligth(pkey,pname,from_airport_id,to_airport_id,plane_id) VALUES ($1,$2,$3,$4,$5) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE fligth SET pname = $1 WHERE id = $8 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE fligth SET pname = $1 WHERE pkey = $8 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM fligth_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM fligth_view";
    private static final String SQLKEYS = "SELECT fligth_pkey FROM fligth_view";
    private static final String SQLIDBYPKEY = "SELECT id from fligth WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from fligth WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM fligth WHERE id = $1 returning id";
    private static final String TABLENAME ="fligth";
    

    public FligthService() {
        init0();
    }

    
    private static final Map<String, ZtatUnitInfoLon> mz1 = new HashMap<>(6);                       
    private static final Map<String, ZtatUnitInfoLon2> mz2 = new HashMap<>(6);                       

    @Override
    public String getTableName(){
        return TABLENAME;
    }

    @Override
    public String getSqlDelete(){
        return SQLDELETE;
    }

    @Override
    public String getSqlKeyIn() {
        return SQLLKEYIN;
    }

/**    
    private static String sql00 = "SELECT fligth.id as fligth_id,
fligth.pkey as fligth_pkey,
fligth.pname as fligth_pname,
from_airport.id as from_airport_id,from_airport.pkey as from_airport_pkey,from_airport.pname as from_airport_pname,
to_airport.id as to_airport_id,to_airport.pkey as to_airport_pkey,to_airport.pname as to_airport_pname,
plane.id as plane_id,plane.pkey as plane_pkey,plane.pname as plane_pname,
la_compania.id as la_compania_id, la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname 
  FROM 
  fligth,
  airport as from_airport,
  airport as to_airport,
  plane as plane,
  air_company as la_compania  
 WHERE 
 fligth.from_airport_id = from_airport.id
 AND fligth.to_airport_id = to_airport.id
 AND fligth.plane_id = plane.id
 AND plane.la_compania_id = la_compania.id"
*/

    @Override
    public String getSqlKeys(){
        return SQLKEYS;
    }

    @Override
    public String getSqlCount(){
        return SQLCOUNT;
    }
    @Override
    public String getSqlIdByPkey() {
        return SQLIDBYPKEY;
    }

    /**
     * sql select property alias field names
     */
    private final LinkedHashSet<String> names =  new LinkedHashSet<>();;
    
    /**
     * Map field insert/update to property 
     */
    private final HashMap<String,String> insertMapFields = new HashMap<>(); 
    
    /**
    * Map property to field order 
    */
    private final HashMap<String, String> sortMapFields = new  HashMap<>();

    private final JsonObject dcModel  = new JsonObject();
    
    private void init0(){
        
    dcModel.put("dc", "fligth");

//ID 
    names.add("id");

    sortMapFields.put("id","fligth_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","fligth");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("fligth.fligth_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","fligth");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  fromAirport
    doFieldMT0("fligth","fromAirport", "from_airport");  

    final JsonObject fromAirport =  doMto("fromAirport","airport");        
   
    names.add("fromAirport_pname");
    sortMapFields.put( "fromAirport_pname", "from_airport_pname");                
    fromAirport.put("pc","pname");          

    mto.add(fromAirport);
        

    //1  from_airport  -- from_airport_id
    final ZtatUnitInfoLon zFromAirport = new ZtatUnitInfoLon("from_airport_id", "fromAirport",  "airport","pname","from_airport");
    mz1.put("zFromAirport", zFromAirport);    

//(1)  toAirport
    doFieldMT0("fligth","toAirport", "to_airport");  

    final JsonObject toAirport =  doMto("toAirport","airport");        
   
    names.add("toAirport_pname");
    sortMapFields.put( "toAirport_pname", "to_airport_pname");                
    toAirport.put("pc","pname");          

    mto.add(toAirport);
        

    //1  to_airport  -- to_airport_id
    final ZtatUnitInfoLon zToAirport = new ZtatUnitInfoLon("to_airport_id", "toAirport",  "airport","pname","to_airport");
    mz1.put("zToAirport", zToAirport);    

//(1)  plane
    doFieldMT0("fligth","plane", "plane");  

    final JsonObject plane =  doMto("plane","plane");        
   
    names.add("plane_pname");
    sortMapFields.put( "plane_pname", "plane_pname");                
    plane.put("pc","pname");          

    mto.add(plane);
        

    //1  plane  -- plane_id
    final ZtatUnitInfoLon zPlane = new ZtatUnitInfoLon("plane_id", "plane",  "plane","pname","plane");
    mz1.put("zPlane", zPlane);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  laCompania   laCompania  
    names.add("laCompania_id");          
    names.add("laCompania_pkey");

    final JsonObject laCompaniaFromPlane =   doMto2("laCompania","airCompany","plane");        
   
    names.add("laCompania_pname");           
    sortMapFields.put( "laCompania_pname", "la_compania_pname");  
    laCompaniaFromPlane.put("pc","pname");    
         
    mto2.add(laCompaniaFromPlane);        

    final ZtatUnitInfoLon2 zLaCompaniaFromPlane = new ZtatUnitInfoLon2(zPlane, "la_compania_id", "laCompania",  "air_company","pname","la_compania");
    mz2.put("zLaCompaniaFromPlane",zLaCompaniaFromPlane);

    dcModel.put("mto2",mto2);    
        
        final JsonArray otm = new JsonArray();

//ON RELATION theFligth    
            applyOtm(otm,"fligthInstances","fligthInstance","theFligth"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  
        
    }        
    @Override
    public LinkedHashSet<String> getNames() {
        return names;        
    }

    @Override
    public  HashMap<String, String> getInsertMapFields(){
        return insertMapFields;
    }

    @Override
    public HashMap<String, String> getSortMapFields(){
        return sortMapFields;
    }

    @Override
    public JsonObject elModelo(){
        return  dcModel;
    }

    @Override
    public JsonArray toJsonArray(final Row r){
        final JsonArray jsa = new JsonArray();
        jsa.add(r.getLong("fligth_id") );       
        jsa.add(r.getString("fligth_pkey") );       
        jsa.add(r.getString("fligth_pname") );
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
    public int fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        return fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m = new LinkedHashMap<>();
    
    if(withIds){
        m.put("fligth_id",LONG);
    }        
//pkey    
    m.put("fligth_pkey",STRING);              
//pname    
    m.put("fligth_pname",STRING);          
    if(level<1){
        return m;    
    }       
// fromAirport   fromAirport
    if(withIds){
        m.put("fromAirport_id",LONG);                       
    }
    m.put("fromAirport_pkey",STRING);     
    m.put("fromAirport_pname",STRING);   
// toAirport   toAirport
    if(withIds){
        m.put("toAirport_id",LONG);                       
    }
    m.put("toAirport_pkey",STRING);     
    m.put("toAirport_pname",STRING);   
// plane   plane
    if(withIds){
        m.put("plane_id",LONG);                       
    }
    m.put("plane_pkey",STRING);     
    m.put("plane_pname",STRING);  
//[2] laCompania --   laCompania
    if(withIds){
        m.put("laCompania_id",LONG);              
    }              
    m.put("laCompania_pkey",STRING);  
        
    m.put("laCompania_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"fligth_id", nc++); 
    }            //pkey       
            sToCell(r, row,"fligth_pkey", nc++);     //pname       
            sToCell(r, row,"fligth_pname", nc++); 
//fromAirport   from_airport        
    if(withIds){
        lToCell(r, row,"from_airport_id", nc++);
    }
    sToCell(r, row,"from_airport_pkey", nc++);
    sToCell(r, row,"from_airport_pname", nc++);
//toAirport   to_airport        
    if(withIds){
        lToCell(r, row,"to_airport_id", nc++);
    }
    sToCell(r, row,"to_airport_pkey", nc++);
    sToCell(r, row,"to_airport_pname", nc++);
//plane   plane        
    if(withIds){
        lToCell(r, row,"plane_id", nc++);
    }
    sToCell(r, row,"plane_pkey", nc++);
    sToCell(r, row,"plane_pname", nc++);
// laCompania  la_compania
    if(withIds){
       lToCell(r, row,"la_compania_id", nc++);
    }

    sToCell(r, row,"la_compania_pkey", nc++);

    sToCell(r, row,"la_compania_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE fligth_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Fligth dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());   
    if(dc0.getFromAirport()!=null){
       t.addLong(dc0.getFromAirport().getId());
    }   
    if(dc0.getToAirport()!=null){
       t.addLong(dc0.getToAirport().getId());
    }   
    if(dc0.getPlane()!=null){
       t.addLong(dc0.getPlane().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final Fligth dc0, final Tuple t){
        
    t.addString(dc0.getPname());   
//      if(dc0.getFromAirport()!=null){
//            t.addLong(dc0.getFromAirport().getId());
//      }   
//      if(dc0.getToAirport()!=null){
//            t.addLong(dc0.getToAirport().getId());
//      }   
//      if(dc0.getPlane()!=null){
//            t.addLong(dc0.getPlane().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);

    fTLong("fromAirport_id",obj,t);

    fTLong("toAirport_id",obj,t);

    fTLong("plane_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> fromAirport = mapParents.get("fromAirport");
            final Long fromAirport_id = fromAirport.get((String)obj.get("fromAirport_pkey"));
            obj.put("fromAirport_id", fromAirport_id);
      
            final Map<String, Long> toAirport = mapParents.get("toAirport");
            final Long toAirport_id = toAirport.get((String)obj.get("toAirport_pkey"));
            obj.put("toAirport_id", toAirport_id);
      
            final Map<String, Long> plane = mapParents.get("plane");
            final Long plane_id = plane.get((String)obj.get("plane_pkey"));
            obj.put("plane_id", plane_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("pname", js, t);     
    fTLong("fromAirport_id",js,t);     
    fTLong("toAirport_id",js,t);     
    fTLong("plane_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);

            //     fTLong("fromAirport_id",js,t);

            //     fTLong("toAirport_id",js,t);

            //     fTLong("plane_id",js,t);
fTLong("id",js,t);
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
    public Fligth doFrom(final Row r){
        final Fligth fligth = new Fligth();
         fligth.setId(r.getLong("fligth_id"));         
                fligth.setPkey(  r.getString("fligth_pkey"));                       
                fligth.setPname(  r.getString("fligth_pname"));              
        final Airport fromAirport = new Airport();
        fromAirport.setId(r.getLong("from_airport_id"));
        fromAirport.setPkey(r.getString("from_airport_pkey"));
        
        fromAirport.setPname(r.getString("from_airport_pname"));
        fligth.setFromAirport(fromAirport);
        
        final Airport toAirport = new Airport();
        toAirport.setId(r.getLong("to_airport_id"));
        toAirport.setPkey(r.getString("to_airport_pkey"));
        
        toAirport.setPname(r.getString("to_airport_pname"));
        fligth.setToAirport(toAirport);
        
        final Plane plane = new Plane();
        plane.setId(r.getLong("plane_id"));
        plane.setPkey(r.getString("plane_pkey"));
        
        plane.setPname(r.getString("plane_pname"));
        fligth.setPlane(plane);
        
        final AirCompany laCompania = new AirCompany();
        laCompania.setId(r.getLong("la_compania_id"));
        laCompania.setPkey(r.getString("la_compania_pkey"));
        laCompania.setPname(r.getString("la_compania_pname"));
 
        plane.setLaCompania(laCompania);   
        return fligth;
    }
    
    @Override
    public Fligth doFromJson(final JsonObject js){
        Fligth fligth = new Fligth();
        fligth.setId(js.getLong("id"));
        
                
                fligth.setPkey(js.getString("pkey"));        
                fligth.setPname(js.getString("pname"));        
            fligth.setId(js.getLong("fromAirport_id"));        
            fligth.setId(js.getLong("toAirport_id"));        
            fligth.setId(js.getLong("plane_id"));
        return fligth;
    }

    @Override
    public JsonObject toJson(final Fligth o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );

            final Airport fromAirport = o.getFromAirport();
            if(fromAirport!=null){
                jso.put("fromAirport_id", fromAirport.getId());
                jso.put("fromAirport_pkey", fromAirport.getPkey());
            }
            

            final Airport toAirport = o.getToAirport();
            if(toAirport!=null){
                jso.put("toAirport_id", toAirport.getId());
                jso.put("toAirport_pkey", toAirport.getPkey());
            }
            

            final Plane plane = o.getPlane();
            if(plane!=null){
                jso.put("plane_id", plane.getId());
                jso.put("plane_pkey", plane.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "fligth_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "fligth_pkey");
    slcb.doEqInPSimple( "pkey", "fligth_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "fligth_pname");
    slcb.doEqInPSimple( "pname", "fligth_pname");            
        
        slcb.doIlPSimple2( "fromAirport_pkey", "from_airport_pkey");
        slcb.doEQPSimple2( "fromAirport_pkey", "from_airport_pkey");
        slcb.doInLongCondition("fromAirport_id", "from_airport_id");  
//Airport 1        --
        slcb.doIlPSimple2( "fromAirport_pname", "from_airport_pname");    
        
        slcb.doIlPSimple2( "toAirport_pkey", "to_airport_pkey");
        slcb.doEQPSimple2( "toAirport_pkey", "to_airport_pkey");
        slcb.doInLongCondition("toAirport_id", "to_airport_id");  
//Airport 1        --
        slcb.doIlPSimple2( "toAirport_pname", "to_airport_pname");    
        
        slcb.doIlPSimple2( "plane_pkey", "plane_pkey");
        slcb.doEQPSimple2( "plane_pkey", "plane_pkey");
        slcb.doInLongCondition("plane_id", "plane_id");  
//Plane 2        --
        slcb.doIlPSimple2( "plane_pname", "plane_pname");    
        
        slcb.doIlPSimple2( "laCompania_pkey", "la_compania_pkey");
        slcb.doEQPSimple2( "laCompania_pkey", "la_compania_pkey");
        slcb.doInLongCondition("laCompania_id", "la_compania_id");
//AirCompany 1
        slcb.doIlPSimple2( "laCompania_pname", "la_compania_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"fligth");
        sz0.addField("COUNT(fligth.id) as c_fligth_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zFromAirport"))   ;               
    sz0.applyG1(mz1.get("zToAirport"))   ;               
    sz0.applyG1(mz1.get("zPlane"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zLaCompaniaFromPlane"));                           
//level 3    
        return sz0;
    }
}
    
        