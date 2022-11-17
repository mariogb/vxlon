
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
 *   DepartamentBaseTimePeriodService 
 * 
 */
  
public class DepartamentBaseTimePeriodService extends AbstractServiceLon<DepartamentBaseTimePeriod>{

    private static final String SQLINSERT ="INSERT INTO departament_base_time_period(pkey,base_time_period_id,departament_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE departament_base_time_period SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE departament_base_time_period SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM departament_base_time_period_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM departament_base_time_period_view";
    private static final String SQLKEYS = "SELECT departament_base_time_period_pkey FROM departament_base_time_period_view";
    private static final String SQLIDBYPKEY = "SELECT id from departament_base_time_period WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from departament_base_time_period WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM departament_base_time_period WHERE id = $1 returning id";
    private static final String TABLENAME ="departament_base_time_period";
    

    public DepartamentBaseTimePeriodService() {
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
    private static String sql00 = "SELECT departament_base_time_period.id as departament_base_time_period_id,
departament_base_time_period.pkey as departament_base_time_period_pkey,
base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname,
departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname 
  FROM 
  departament_base_time_period,
  base_time_period as base_time_period,
  base as base,
  time_period as time_period,
  departament as departament  
 WHERE 
 departament_base_time_period.base_time_period_id = base_time_period.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id
 AND departament_base_time_period.departament_id = departament.id"
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
        
    dcModel.put("dc", "departamentBaseTimePeriod");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_base_time_period_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","departament_base_time_period");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_base_time_period.departament_base_time_period_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  baseTimePeriod
    doFieldMT0("departament_base_time_period","baseTimePeriod", "base_time_period");  

    final JsonObject baseTimePeriod =  doMto("baseTimePeriod","baseTimePeriod");        

    mto.add(baseTimePeriod);
        

    //1  base_time_period  -- base_time_period_id
    final ZtatUnitInfoLon zBaseTimePeriod = new ZtatUnitInfoLon("base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");
    mz1.put("zBaseTimePeriod", zBaseTimePeriod);    

//(1)  departament
    doFieldMT0("departament_base_time_period","departament", "departament");  

    final JsonObject departament =  doMto("departament","departament");        
   
    names.add("departament_pname");
    sortMapFields.put( "departament_pname", "departament_pname");                
    departament.put("pc","pname");          

    mto.add(departament);
        

    //1  departament  -- departament_id
    final ZtatUnitInfoLon zDepartament = new ZtatUnitInfoLon("departament_id", "departament",  "departament","pname","departament");
    mz1.put("zDepartament", zDepartament);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  base   base  
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject baseFromBaseTimePeriod =   doMto2("base","base","baseTimePeriod");        
   
    names.add("base_pname");           
    sortMapFields.put( "base_pname", "base_pname");  
    baseFromBaseTimePeriod.put("pc","pname");    
         
    mto2.add(baseFromBaseTimePeriod);        

    final ZtatUnitInfoLon2 zBaseFromBaseTimePeriod = new ZtatUnitInfoLon2(zBaseTimePeriod, "base_id", "base",  "base","pname","base");
    mz2.put("zBaseFromBaseTimePeriod",zBaseFromBaseTimePeriod);

//(2)  timePeriod   timePeriod  
    names.add("timePeriod_id");          
    names.add("timePeriod_pkey");

    final JsonObject timePeriodFromBaseTimePeriod =   doMto2("timePeriod","timePeriod","baseTimePeriod");        
   
    names.add("timePeriod_pname");           
    sortMapFields.put( "timePeriod_pname", "time_period_pname");  
    timePeriodFromBaseTimePeriod.put("pc","pname");    
         
    mto2.add(timePeriodFromBaseTimePeriod);        

    final ZtatUnitInfoLon2 zTimePeriodFromBaseTimePeriod = new ZtatUnitInfoLon2(zBaseTimePeriod, "time_period_id", "timePeriod",  "time_period","pname","time_period");
    mz2.put("zTimePeriodFromBaseTimePeriod",zTimePeriodFromBaseTimePeriod);

    dcModel.put("mto2",mto2);    
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"contracts","contractOut"); 
                

        applyOtm(otm,"departamentJobInstances","departamentJobInstance"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"appointments","appointment","contracts",null,null); 
        

        applyOtm2(otm2,"comercialDocuments","comercialDocumentOut","contracts","contract",null); 
        

        applyOtm2(otm2,"appointmens","appointment","departamentJobInstances",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"invoiceLines","invoiceLineIn","comercialDocuments","comercialDocument","contract",null); 
        

        applyOtm3(otm3,"payments","paymentOut","comercialDocuments",null,"contract",null); 
        

/** OTM 3  ON MODEL**/
        dcModel.put("otm3",otm3);
        
        
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
        jsa.add(r.getLong("departament_base_time_period_id") );       
        jsa.add(r.getString("departament_base_time_period_pkey") );
    jsa.add(r.getLong("base_time_period_id"));
    jsa.add(r.getString("base_time_period_pkey"));   
    
    jsa.add(r.getLong("departament_id"));
    jsa.add(r.getString("departament_pkey"));   
    
        
    jsa.add(r.getString("departament_pname"));
    jsa.add(r.getLong("base_id"));
    jsa.add(r.getString("base_pkey"));
    

    jsa.add(r.getString("base_pname"));
    
    jsa.add(r.getLong("time_period_id"));
    jsa.add(r.getString("time_period_pkey"));
    

    jsa.add(r.getString("time_period_pname"));
    
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
        m.put("departamentBaseTimePeriod_id",LONG);
    }        
//pkey    
    m.put("departamentBaseTimePeriod_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// baseTimePeriod   baseTimePeriod
    if(withIds){
        m.put("baseTimePeriod_id",LONG);                       
    }
    m.put("baseTimePeriod_pkey",STRING);      
// departament   departament
    if(withIds){
        m.put("departament_id",LONG);                       
    }
    m.put("departament_pkey",STRING);     
    m.put("departament_pname",STRING);  
//[2] base --   base
    if(withIds){
        m.put("base_id",LONG);              
    }              
    m.put("base_pkey",STRING);  
        
    m.put("base_pname",STRING);  
//[2] timePeriod --   timePeriod
    if(withIds){
        m.put("timePeriod_id",LONG);              
    }              
    m.put("timePeriod_pkey",STRING);  
        
    m.put("timePeriod_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"departament_base_time_period_id", nc++); 
    }            //pkey       
            sToCell(r, row,"departament_base_time_period_pkey", nc++); 
//baseTimePeriod   base_time_period        
    if(withIds){
        lToCell(r, row,"base_time_period_id", nc++);
    }
    sToCell(r, row,"base_time_period_pkey", nc++);
//departament   departament        
    if(withIds){
        lToCell(r, row,"departament_id", nc++);
    }
    sToCell(r, row,"departament_pkey", nc++);
    sToCell(r, row,"departament_pname", nc++);
// base  base
    if(withIds){
       lToCell(r, row,"base_id", nc++);
    }

    sToCell(r, row,"base_pkey", nc++);

    sToCell(r, row,"base_pname", nc++);
// timePeriod  time_period
    if(withIds){
       lToCell(r, row,"time_period_id", nc++);
    }

    sToCell(r, row,"time_period_pkey", nc++);

    sToCell(r, row,"time_period_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE departament_base_time_period_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final DepartamentBaseTimePeriod dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getBaseTimePeriod()!=null){
       t.addLong(dc0.getBaseTimePeriod().getId());
    }   
    if(dc0.getDepartament()!=null){
       t.addLong(dc0.getDepartament().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final DepartamentBaseTimePeriod dc0, final Tuple t){
           
//      if(dc0.getBaseTimePeriod()!=null){
//            t.addLong(dc0.getBaseTimePeriod().getId());
//      }   
//      if(dc0.getDepartament()!=null){
//            t.addLong(dc0.getDepartament().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("baseTimePeriod_id",obj,t);

    fTLong("departament_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> baseTimePeriod = mapParents.get("baseTimePeriod");
            final Long baseTimePeriod_id = baseTimePeriod.get((String)obj.get("baseTimePeriod_pkey"));
            obj.put("baseTimePeriod_id", baseTimePeriod_id);
      
            final Map<String, Long> departament = mapParents.get("departament");
            final Long departament_id = departament.get((String)obj.get("departament_pkey"));
            obj.put("departament_id", departament_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("baseTimePeriod_id",js,t);     
    fTLong("departament_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("baseTimePeriod_id",js,t);

            //     fTLong("departament_id",js,t);
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
    public DepartamentBaseTimePeriod doFrom(final Row r){
        final DepartamentBaseTimePeriod departamentBaseTimePeriod = new DepartamentBaseTimePeriod();
         departamentBaseTimePeriod.setId(r.getLong("departament_base_time_period_id"));         
                departamentBaseTimePeriod.setPkey(  r.getString("departament_base_time_period_pkey"));              
        final BaseTimePeriod baseTimePeriod = new BaseTimePeriod();
        baseTimePeriod.setId(r.getLong("base_time_period_id"));
        baseTimePeriod.setPkey(r.getString("base_time_period_pkey"));
        
        departamentBaseTimePeriod.setBaseTimePeriod(baseTimePeriod);
        
        final Departament departament = new Departament();
        departament.setId(r.getLong("departament_id"));
        departament.setPkey(r.getString("departament_pkey"));
        
        departament.setPname(r.getString("departament_pname"));
        departamentBaseTimePeriod.setDepartament(departament);
        
        final Base base = new Base();
        base.setId(r.getLong("base_id"));
        base.setPkey(r.getString("base_pkey"));
        base.setPname(r.getString("base_pname"));
 
        baseTimePeriod.setBase(base); 
        final TimePeriod timePeriod = new TimePeriod();
        timePeriod.setId(r.getLong("time_period_id"));
        timePeriod.setPkey(r.getString("time_period_pkey"));
        timePeriod.setPname(r.getString("time_period_pname"));
 
        baseTimePeriod.setTimePeriod(timePeriod);   
        return departamentBaseTimePeriod;
    }
    
    @Override
    public DepartamentBaseTimePeriod doFromJson(final JsonObject js){
        DepartamentBaseTimePeriod departamentBaseTimePeriod = new DepartamentBaseTimePeriod();
        departamentBaseTimePeriod.setId(js.getLong("id"));
        
                
                departamentBaseTimePeriod.setPkey(js.getString("pkey"));        
            departamentBaseTimePeriod.setId(js.getLong("baseTimePeriod_id"));        
            departamentBaseTimePeriod.setId(js.getLong("departament_id"));
        return departamentBaseTimePeriod;
    }

    @Override
    public JsonObject toJson(final DepartamentBaseTimePeriod o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final BaseTimePeriod baseTimePeriod = o.getBaseTimePeriod();
            if(baseTimePeriod!=null){
                jso.put("baseTimePeriod_id", baseTimePeriod.getId());
                jso.put("baseTimePeriod_pkey", baseTimePeriod.getPkey());
            }
            

            final Departament departament = o.getDepartament();
            if(departament!=null){
                jso.put("departament_id", departament.getId());
                jso.put("departament_pkey", departament.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "departament_base_time_period_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "departament_base_time_period_pkey");
    slcb.doEqInPSimple( "pkey", "departament_base_time_period_pkey");
        
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");  
//BaseTimePeriod undefined        --
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");  
//Departament 3        --
        slcb.doIlPSimple2( "departament_pname", "departament_pname");    
        
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");
//Base 1
        slcb.doIlPSimple2( "base_pname", "base_pname"); 
        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");
//TimePeriod 3
        slcb.doIlPSimple2( "timePeriod_pname", "time_period_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"departament_base_time_period");
        sz0.addField("COUNT(departament_base_time_period.id) as c_departament_base_time_period_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zBaseTimePeriod"))   ;               
    sz0.applyG1(mz1.get("zDepartament"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zBaseFromBaseTimePeriod"));                           
    sz0.applyG2(mz2.get("zTimePeriodFromBaseTimePeriod"));                           
//level 3    
        return sz0;
    }
}
    
        