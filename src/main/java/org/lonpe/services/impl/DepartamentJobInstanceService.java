
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
 *   DepartamentJobInstanceService 
 * 
 */
  
public class DepartamentJobInstanceService extends AbstractServiceLon<DepartamentJobInstance>{

    private static final String SQLINSERT ="INSERT INTO departament_job_instance(pkey,description,nhoras,pname,departament_job_id,departament_base_time_period_id) VALUES ($1,$2,$3,$4,$5,$6) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE departament_job_instance SET description = $1,nhoras = $2,pname = $3 WHERE id = $8 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE departament_job_instance SET description = $1,nhoras = $2,pname = $3 WHERE pkey = $8 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM departament_job_instance_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM departament_job_instance_view";
    private static final String SQLKEYS = "SELECT departament_job_instance_pkey FROM departament_job_instance_view";
    private static final String SQLIDBYPKEY = "SELECT id from departament_job_instance WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from departament_job_instance WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM departament_job_instance WHERE id = $1 returning id";
    private static final String TABLENAME ="departament_job_instance";
    

    public DepartamentJobInstanceService() {
        init0();
    }

    
    private static final Map<String, ZtatUnitInfoLon> mz1 = new HashMap<>(6);                       
    private static final Map<String, ZtatUnitInfoLon2> mz2 = new HashMap<>(6);                       
    private static final Map<String, ZtatUnitInfoLon3> mz3 = new HashMap<>(6);                       

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
    private static String sql00 = "SELECT departament_job_instance.id as departament_job_instance_id,
departament_job_instance.pkey as departament_job_instance_pkey,
departament_job_instance.description as departament_job_instance_description,
departament_job_instance.nhoras as departament_job_instance_nhoras,
departament_job_instance.pname as departament_job_instance_pname,
departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  departament_job_instance,
  departament_job as departament_job,
  departament as departament,
  departament_base_time_period as departament_base_time_period,
  base_time_period as base_time_period,
  base as base,
  time_period as time_period  
 WHERE 
 departament_job_instance.departament_job_id = departament_job.id
 AND departament_job.departament_id = departament.id
 AND departament_job_instance.departament_base_time_period_id = departament_base_time_period.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id"
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
        
    dcModel.put("dc", "departamentJobInstance");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_job_instance_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","departament_job_instance");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job_instance.departament_job_instance_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//description
    doField("description","description","departament_job_instance");               

//Create property description       
    final JsonObject description = psString("description",false);
 
    ps.add(description);
 
//nhoras
    doFieldSort("nhoras","nhoras","departament_job_instance");               

//Create property nhoras       
    final JsonObject nhoras = psInteger("nhoras",true);
 
    ps.add(nhoras);
 
//pname
    doFieldSort("pname","pname","departament_job_instance");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job_instance.departament_job_instance_uidx_pname","pname");  

//Create property pname       
    final JsonObject pname = psString("pname",true);

// IS Unique     
    pname.put("uq",true);                    
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  departamentJob
    doFieldMT0("departament_job_instance","departamentJob", "departament_job");  

    final JsonObject departamentJob =  doMto("departamentJob","departamentJob");        
   
    names.add("departamentJob_pname");
    sortMapFields.put( "departamentJob_pname", "departament_job_pname");                
    departamentJob.put("pc","pname");          

    mto.add(departamentJob);
        

    //1  departament_job  -- departament_job_id
    final ZtatUnitInfoLon zDepartamentJob = new ZtatUnitInfoLon("departament_job_id", "departamentJob",  "departament_job","pname","departament_job");
    mz1.put("zDepartamentJob", zDepartamentJob);    

//(1)  departamentBaseTimePeriod
    doFieldMT0("departament_job_instance","departamentBaseTimePeriod", "departament_base_time_period");  

    final JsonObject departamentBaseTimePeriod =  doMto("departamentBaseTimePeriod","departamentBaseTimePeriod");        

    mto.add(departamentBaseTimePeriod);
        

    //1  departament_base_time_period  -- departament_base_time_period_id
    final ZtatUnitInfoLon zDepartamentBaseTimePeriod = new ZtatUnitInfoLon("departament_base_time_period_id", "departamentBaseTimePeriod",  "departament_base_time_period","null","departament_base_time_period");
    mz1.put("zDepartamentBaseTimePeriod", zDepartamentBaseTimePeriod);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  departament   departament  
    names.add("departament_id");          
    names.add("departament_pkey");

    final JsonObject departamentFromDepartamentJob =   doMto2("departament","departament","departamentJob");        
   
    names.add("departament_pname");           
    sortMapFields.put( "departament_pname", "departament_pname");  
    departamentFromDepartamentJob.put("pc","pname");    
         
    mto2.add(departamentFromDepartamentJob);        

    final ZtatUnitInfoLon2 zDepartamentFromDepartamentJob = new ZtatUnitInfoLon2(zDepartamentJob, "departament_id", "departament",  "departament","pname","departament");
    mz2.put("zDepartamentFromDepartamentJob",zDepartamentFromDepartamentJob);

//(2)  baseTimePeriod   baseTimePeriod  
    names.add("baseTimePeriod_id");          
    names.add("baseTimePeriod_pkey");

    final JsonObject baseTimePeriodFromDepartamentBaseTimePeriod =   doMto2("baseTimePeriod","baseTimePeriod","departamentBaseTimePeriod");        
         
    mto2.add(baseTimePeriodFromDepartamentBaseTimePeriod);        

    final ZtatUnitInfoLon2 zBaseTimePeriodFromDepartamentBaseTimePeriod = new ZtatUnitInfoLon2(zDepartamentBaseTimePeriod, "base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");
    mz2.put("zBaseTimePeriodFromDepartamentBaseTimePeriod",zBaseTimePeriodFromDepartamentBaseTimePeriod);

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           

//(3)   base   
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject baseFromBaseTimePeriodFromDepartamentBaseTimePeriod =   doMto2("base","base","baseTimePeriod");        
   
    names.add("base_pname");            
    sortMapFields.put( "base_pname", "base_pname"); 
    baseFromBaseTimePeriodFromDepartamentBaseTimePeriod.put("pc","pname");     
         
    mto3.add(baseFromBaseTimePeriodFromDepartamentBaseTimePeriod);        

     
    final ZtatUnitInfoLon3 zBaseFromBaseTimePeriodFromDepartamentBaseTimePeriod = new ZtatUnitInfoLon3(zBaseTimePeriodFromDepartamentBaseTimePeriod, "base_id", "base",  "base","pname","base");
    mz3.put("zBaseFromBaseTimePeriodFromDepartamentBaseTimePeriod",zBaseFromBaseTimePeriodFromDepartamentBaseTimePeriod);    

//(3)   timePeriod   
    names.add("timePeriod_id");          
    names.add("timePeriod_pkey");

    final JsonObject timePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod =   doMto2("timePeriod","timePeriod","baseTimePeriod");        
   
    names.add("timePeriod_pname");            
    sortMapFields.put( "timePeriod_pname", "time_period_pname"); 
    timePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod.put("pc","pname");     
         
    mto3.add(timePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod);        

     
    final ZtatUnitInfoLon3 zTimePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod = new ZtatUnitInfoLon3(zBaseTimePeriodFromDepartamentBaseTimePeriod, "time_period_id", "timePeriod",  "time_period","pname","time_period");
    mz3.put("zTimePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod",zTimePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod);    

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"appointmens","appointment"); 
                

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
        jsa.add(r.getLong("departament_job_instance_id") );       
        jsa.add(r.getString("departament_job_instance_pkey") );       
        jsa.add(r.getString("departament_job_instance_description") );       
        jsa.add(r.getInteger("departament_job_instance_nhoras") );       
        jsa.add(r.getString("departament_job_instance_pname") );
    jsa.add(r.getLong("departament_job_id"));
    jsa.add(r.getString("departament_job_pkey"));   
    
        
    jsa.add(r.getString("departament_job_pname"));
    jsa.add(r.getLong("departament_base_time_period_id"));
    jsa.add(r.getString("departament_base_time_period_pkey"));   
    
    jsa.add(r.getLong("departament_id"));
    jsa.add(r.getString("departament_pkey"));
    

    jsa.add(r.getString("departament_pname"));
    
    jsa.add(r.getLong("base_time_period_id"));
    jsa.add(r.getString("base_time_period_pkey"));
    
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
        m.put("departamentJobInstance_id",LONG);
    }        
//pkey    
    m.put("departamentJobInstance_pkey",STRING);              
//description    
    m.put("departamentJobInstance_description",STRING);              
//nhoras    
    m.put("departamentJobInstance_nhoras",INTEGER);              
//pname    
    m.put("departamentJobInstance_pname",STRING);          
    if(level<1){
        return m;    
    }       
// departamentJob   departamentJob
    if(withIds){
        m.put("departamentJob_id",LONG);                       
    }
    m.put("departamentJob_pkey",STRING);     
    m.put("departamentJob_pname",STRING);   
// departamentBaseTimePeriod   departamentBaseTimePeriod
    if(withIds){
        m.put("departamentBaseTimePeriod_id",LONG);                       
    }
    m.put("departamentBaseTimePeriod_pkey",STRING);     
//[2] departament --   departament
    if(withIds){
        m.put("departament_id",LONG);              
    }              
    m.put("departament_pkey",STRING);  
        
    m.put("departament_pname",STRING);  
//[2] baseTimePeriod --   baseTimePeriod
    if(withIds){
        m.put("baseTimePeriod_id",LONG);              
    }              
    m.put("baseTimePeriod_pkey",STRING);  
        
//[3] base --   base
    if(withIds){
        m.put("base_id",LONG);              
    }              
    m.put("base_pkey",STRING);  
        
    m.put("base_pname",STRING);  
//[3] timePeriod --   timePeriod
    if(withIds){
        m.put("timePeriod_id",LONG);              
    }              
    m.put("timePeriod_pkey",STRING);  
        
    m.put("timePeriod_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"departament_job_instance_id", nc++); 
    }            //pkey       
            sToCell(r, row,"departament_job_instance_pkey", nc++);     //description       
            sToCell(r, row,"departament_job_instance_description", nc++);     //nhoras            
            ldToCell(r, row,"departament_job_instance_nhoras", nc++);     //pname       
            sToCell(r, row,"departament_job_instance_pname", nc++); 
//departamentJob   departament_job        
    if(withIds){
        lToCell(r, row,"departament_job_id", nc++);
    }
    sToCell(r, row,"departament_job_pkey", nc++);
    sToCell(r, row,"departament_job_pname", nc++);
//departamentBaseTimePeriod   departament_base_time_period        
    if(withIds){
        lToCell(r, row,"departament_base_time_period_id", nc++);
    }
    sToCell(r, row,"departament_base_time_period_pkey", nc++);
// departament  departament
    if(withIds){
       lToCell(r, row,"departament_id", nc++);
    }

    sToCell(r, row,"departament_pkey", nc++);

    sToCell(r, row,"departament_pname", nc++);
// baseTimePeriod  base_time_period
    if(withIds){
       lToCell(r, row,"base_time_period_id", nc++);
    }

    sToCell(r, row,"base_time_period_pkey", nc++);
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
        return SQLVIEW+ " WHERE departament_job_instance_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final DepartamentJobInstance dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getDescription());        
    t.addInteger(dc0.getNhoras());        
    t.addString(dc0.getPname());   
    if(dc0.getDepartamentJob()!=null){
       t.addLong(dc0.getDepartamentJob().getId());
    }   
    if(dc0.getDepartamentBaseTimePeriod()!=null){
       t.addLong(dc0.getDepartamentBaseTimePeriod().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final DepartamentJobInstance dc0, final Tuple t){
        
    t.addString(dc0.getDescription());
    t.addInteger(dc0.getNhoras());
    t.addString(dc0.getPname());   
//      if(dc0.getDepartamentJob()!=null){
//            t.addLong(dc0.getDepartamentJob().getId());
//      }   
//      if(dc0.getDepartamentBaseTimePeriod()!=null){
//            t.addLong(dc0.getDepartamentBaseTimePeriod().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("description", obj, t);

    fTInteger("nhoras", obj, t);

    fTString("pname", obj, t);

    fTLong("departamentJob_id",obj,t);

    fTLong("departamentBaseTimePeriod_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> departamentJob = mapParents.get("departamentJob");
            final Long departamentJob_id = departamentJob.get((String)obj.get("departamentJob_pkey"));
            obj.put("departamentJob_id", departamentJob_id);
      
            final Map<String, Long> departamentBaseTimePeriod = mapParents.get("departamentBaseTimePeriod");
            final Long departamentBaseTimePeriod_id = departamentBaseTimePeriod.get((String)obj.get("departamentBaseTimePeriod_pkey"));
            obj.put("departamentBaseTimePeriod_id", departamentBaseTimePeriod_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("description", js, t);
    fTInteger("nhoras", js, t);
    fTString("pname", js, t);     
    fTLong("departamentJob_id",js,t);     
    fTLong("departamentBaseTimePeriod_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("description", js, t);
fTInteger("nhoras", js, t);
fTString("pname", js, t);

            //     fTLong("departamentJob_id",js,t);

            //     fTLong("departamentBaseTimePeriod_id",js,t);
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
    public DepartamentJobInstance doFrom(final Row r){
        final DepartamentJobInstance departamentJobInstance = new DepartamentJobInstance();
         departamentJobInstance.setId(r.getLong("departament_job_instance_id"));         
                departamentJobInstance.setPkey(  r.getString("departament_job_instance_pkey"));                       
                departamentJobInstance.setDescription(  r.getString("departament_job_instance_description"));                       
                departamentJobInstance.setNhoras(  r.getInteger("departament_job_instance_nhoras"));                       
                departamentJobInstance.setPname(  r.getString("departament_job_instance_pname"));              
        final DepartamentJob departamentJob = new DepartamentJob();
        departamentJob.setId(r.getLong("departament_job_id"));
        departamentJob.setPkey(r.getString("departament_job_pkey"));
        
        departamentJob.setPname(r.getString("departament_job_pname"));
        departamentJobInstance.setDepartamentJob(departamentJob);
        
        final DepartamentBaseTimePeriod departamentBaseTimePeriod = new DepartamentBaseTimePeriod();
        departamentBaseTimePeriod.setId(r.getLong("departament_base_time_period_id"));
        departamentBaseTimePeriod.setPkey(r.getString("departament_base_time_period_pkey"));
        
        departamentJobInstance.setDepartamentBaseTimePeriod(departamentBaseTimePeriod);
        
        final Departament departament = new Departament();
        departament.setId(r.getLong("departament_id"));
        departament.setPkey(r.getString("departament_pkey"));
        departament.setPname(r.getString("departament_pname"));
 
        departamentJob.setDepartament(departament); 
        final BaseTimePeriod baseTimePeriod = new BaseTimePeriod();
        baseTimePeriod.setId(r.getLong("base_time_period_id"));
        baseTimePeriod.setPkey(r.getString("base_time_period_pkey"));
         
        departamentBaseTimePeriod.setBaseTimePeriod(baseTimePeriod);   
        return departamentJobInstance;
    }
    
    @Override
    public DepartamentJobInstance doFromJson(final JsonObject js){
        DepartamentJobInstance departamentJobInstance = new DepartamentJobInstance();
        departamentJobInstance.setId(js.getLong("id"));
        
                
                departamentJobInstance.setPkey(js.getString("pkey"));        
                departamentJobInstance.setDescription(js.getString("description"));        
                departamentJobInstance.setNhoras(js.getInteger("nhoras"));        
                departamentJobInstance.setPname(js.getString("pname"));        
            departamentJobInstance.setId(js.getLong("departamentJob_id"));        
            departamentJobInstance.setId(js.getLong("departamentBaseTimePeriod_id"));
        return departamentJobInstance;
    }

    @Override
    public JsonObject toJson(final DepartamentJobInstance o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("description",  o.getDescription() );
        jso.put("nhoras",  o.getNhoras() );
        jso.put("pname",  o.getPname() );

            final DepartamentJob departamentJob = o.getDepartamentJob();
            if(departamentJob!=null){
                jso.put("departamentJob_id", departamentJob.getId());
                jso.put("departamentJob_pkey", departamentJob.getPkey());
            }
            

            final DepartamentBaseTimePeriod departamentBaseTimePeriod = o.getDepartamentBaseTimePeriod();
            if(departamentBaseTimePeriod!=null){
                jso.put("departamentBaseTimePeriod_id", departamentBaseTimePeriod.getId());
                jso.put("departamentBaseTimePeriod_pkey", departamentBaseTimePeriod.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "departament_job_instance_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "departament_job_instance_pkey");
    slcb.doEqInPSimple( "pkey", "departament_job_instance_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "departament_job_instance_pname");
    slcb.doEqInPSimple( "pname", "departament_job_instance_pname");               
    slcb.doGEPSimpleInt( "nhoras", "departament_job_instance_nhoras");
    slcb.doLTPSimpleInt( "nhoras", "departament_job_instance_nhoras");                
        
        slcb.doIlPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doEQPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doInLongCondition("departamentJob_id", "departament_job_id");  
//DepartamentJob 4        --
        slcb.doIlPSimple2( "departamentJob_pname", "departament_job_pname");    
        
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");  
//DepartamentBaseTimePeriod undefined        --
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");
//Departament 3
        slcb.doIlPSimple2( "departament_pname", "departament_pname"); 
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");
//BaseTimePeriod undefined
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id"); 
        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"departament_job_instance");
        sz0.addField("COUNT(departament_job_instance.id) as c_departament_job_instance_id").addName("count");
        
    sz0.addField("sum(departament_job_instance.nhoras) as sum_departament_job_instance_nhoras").addName("sum_nhoras"); 
        
//level 1
             
    sz0.applyG1(mz1.get("zDepartamentJob"))   ;               
    sz0.applyG1(mz1.get("zDepartamentBaseTimePeriod"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zDepartamentFromDepartamentJob"));                           
    sz0.applyG2(mz2.get("zBaseTimePeriodFromDepartamentBaseTimePeriod"));                           
    sz0.applyG2(mz2.get("zDepartamentFromDepartamentBaseTimePeriod"));                           
//level 3    
        sz0.applyG3(mz3.get("zBaseFromBaseTimePeriodFromDepartamentBaseTimePeriod"));               
        sz0.applyG3(mz3.get("zTimePeriodFromBaseTimePeriodFromDepartamentBaseTimePeriod"));               
        return sz0;
    }
}
    
        