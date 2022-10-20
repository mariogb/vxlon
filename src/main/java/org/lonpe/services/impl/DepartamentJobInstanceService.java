
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



import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;

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
    
//1
    private static final ZtatUnitInfoLon zDepartamentJob;

//1
    private static final ZtatUnitInfoLon zDepartamentBaseTimePeriod;

//2
    private static final ZtatUnitInfoLon zDepartament;

//2
    private static final ZtatUnitInfoLon zBaseTimePeriod;

//3
    private static final ZtatUnitInfoLon zBase;

//3
    private static final ZtatUnitInfoLon zTimePeriod;
    
    @Override
    public  String getTableName(){
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
departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  departament_job_instance,
  departament_job as departament_job,
  departament_base_time_period as departament_base_time_period,
  departament as departament,
  base_time_period as base_time_period,
  base as base,
  time_period as time_period  
 WHERE 
 departament_job_instance.departament_job_id = departament_job.id
 AND departament_job_instance.departament_base_time_period_id = departament_base_time_period.id
 AND departament_job.departament_id = departament.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 
"
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
     
    private static final LinkedHashSet<String> names;
    
    /**
     * Map field insert/update to property 
     */
    private static final HashMap<String,String> insertMapFields; 
    
    /**
    * Map property to field order 
    */
    private static final HashMap<String, String> sortMapFields;

    private static final JsonObject dcModel;
    
    static{
        names = new LinkedHashSet<>();
        insertMapFields = new HashMap<>();
        sortMapFields= new  HashMap<>();

        dcModel = new JsonObject();

        
    dcModel.put("dc", "departamentJobInstance");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_job_instance_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("departament_job_instance.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job_instance.departament_job_instance_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "departament_job_instance_pkey");                   
 
    ps.add(pkey);
 
//description
    names.add("description");
    insertMapFields.put("departament_job_instance.description","description");  

//Create property description       
    final JsonObject description = ps00a("description", "String",false);
 
    ps.add(description);
 
//nhoras
    names.add("nhoras");
    insertMapFields.put("departament_job_instance.nhoras","nhoras");  

//Create property nhoras       
    final JsonObject nhoras = ps00a("nhoras", "Integer",true);

    sortMapFields.put("nhoras", "departament_job_instance_nhoras");               
 
    ps.add(nhoras);
 
//pname
    names.add("pname");
    insertMapFields.put("departament_job_instance.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job_instance.departament_job_instance_uidx_pname","pname");  

// IS Unique     
    pname.put("uq",true);                    

    sortMapFields.put("pname", "departament_job_instance_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  departamentJob --------------------
    names.add("departamentJob_id");      
    insertMapFields.put("departament_job_instance.departament_job_id","departamentJob_id");    
       
    names.add("departamentJob_pkey");        
    sortMapFields.put( "departamentJob_pkey", "departament_job_pkey");        

    final JsonObject departamentJob =  doMto("departamentJob","departamentJob");        
   
    names.add("departamentJob_pname");
    sortMapFields.put( "departamentJob_pname", "departament_job_pname");         

    departamentJob.put("pc","pname");          

    mto.add(departamentJob);
        

//(1)  departamentBaseTimePeriod --------------------
    names.add("departamentBaseTimePeriod_id");      
    insertMapFields.put("departament_job_instance.departament_base_time_period_id","departamentBaseTimePeriod_id");    
       
    names.add("departamentBaseTimePeriod_pkey");        
    sortMapFields.put( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");        

    final JsonObject departamentBaseTimePeriod =  doMto("departamentBaseTimePeriod","departamentBaseTimePeriod");        

    mto.add(departamentBaseTimePeriod);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   departament 
        
    names.add("departament_id");          
    names.add("departament_pkey");

    final JsonObject departament =   doMto2("departament","departament","departamentJob");        
   
    names.add("departament_pname");  
    departament.put("pc","pname");             
   
    sortMapFields.put( "departament_pname", "departament_pname");            
         
    mto2.add(departament);        
//(2)   baseTimePeriod 
        
    names.add("baseTimePeriod_id");          
    names.add("baseTimePeriod_pkey");

    final JsonObject baseTimePeriod =   doMto2("baseTimePeriod","baseTimePeriod","departamentBaseTimePeriod");        
         
    mto2.add(baseTimePeriod);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   base 
        
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject base =   doMto2("base","base","baseTimePeriod");        
   
    names.add("base_pname");  
    base.put("pc","pname");             
   
    sortMapFields.put( "base_pname", "base_pname");            
         
    mto3.add(base);        
//(3)   timePeriod 
        
    names.add("timePeriod_id");          
    names.add("timePeriod_pkey");

    final JsonObject timePeriod =   doMto2("timePeriod","timePeriod","baseTimePeriod");        
   
    names.add("timePeriod_pname");  
    timePeriod.put("pc","pname");             
   
    sortMapFields.put( "timePeriod_pname", "time_period_pname");            
         
    mto3.add(timePeriod);        

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"appointmens","appointment"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

        
//1  departament_job  -- departament_job_id
    zDepartamentJob = new ZtatUnitInfoLon("departament_job_id", "departamentJob",  "departament_job","pname","departament_job");

//1  departament_base_time_period  -- departament_base_time_period_id
    zDepartamentBaseTimePeriod = new ZtatUnitInfoLon("departament_base_time_period_id", "departamentBaseTimePeriod",  "departament_base_time_period","null","departament_base_time_period");

//2    
    zDepartament = new ZtatUnitInfoLon("departament_id", "departament",  "departament","pname","departament");

//2    
    zBaseTimePeriod = new ZtatUnitInfoLon("base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");

//3
    zBase = new ZtatUnitInfoLon("base_id", "base",  "base","pname","base");

//3
    zTimePeriod = new ZtatUnitInfoLon("time_period_id", "timePeriod",  "time_period","pname","time_period");

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
    public void fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m_ = new LinkedHashMap<>();
    if(withIds){
                m_.put("departamentJobInstance_id","Long");
            }
            
    //pkey       
            m_.put("departamentJobInstance_pkey","String"); 
            
    //description       
            m_.put("departamentJobInstance_description","String"); 
            
    //nhoras       
            m_.put("departamentJobInstance_nhoras","Integer"); 
            
    //pname       
            m_.put("departamentJobInstance_pname","String"); 
            
if(level<1){
                return m_;    
            }
            
 // departamentJob
if(withIds){
            m_.put("departamentJob_id","Long");   
                    
            }

        m_.put("departamentJob_pkey","String");   
        

            m_.put("departamentJob_pname","String");   
            
 // departamentBaseTimePeriod
if(withIds){
            m_.put("departamentBaseTimePeriod_id","Long");   
                    
            }

        m_.put("departamentBaseTimePeriod_pkey","String");   
        
//[2] departament  

        if(level>1){
            if(withIds){
               m_.put("departament_id","Long");              
            }      
        
        m_.put("departament_pkey","String");  

            m_.put("departament_pname","String");    
 
                      }             
//[2] baseTimePeriod  

        if(level>1){
            if(withIds){
               m_.put("baseTimePeriod_id","Long");              
            }      
        
        m_.put("baseTimePeriod_pkey","String");  
 
                      }             
//[3] base  

        if(level>2){
            if(withIds){
               m_.put("base_id","Long");              
            }      
        
        m_.put("base_pkey","String");  

            m_.put("base_pname","String");    
 
                      }             
//[3] timePeriod  

        if(level>2){
            if(withIds){
               m_.put("timePeriod_id","Long");              
            }      
        
        m_.put("timePeriod_pkey","String");  

            m_.put("timePeriod_pname","String");    
 
                      }             
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"departament_job_instance_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"departament_job_instance_pkey", nc++); 
    //description       
            sToCell(r, row,"departament_job_instance_description", nc++); 
    //nhoras            
            ldToCell(r, row,"departament_job_instance_nhoras", nc++); 
    //pname       
            sToCell(r, row,"departament_job_instance_pname", nc++); 
 // departamentJob
if(withIds){
                    lToCell(r, row,"departament_job_id", nc++);
                 }
sToCell(r, row,"departament_job_pkey", nc++);
sToCell(r, row,"departament_job_pname", nc++);
 // departamentBaseTimePeriod
if(withIds){
                    lToCell(r, row,"departament_base_time_period_id", nc++);
                 }
sToCell(r, row,"departament_base_time_period_pkey", nc++);
// departament
if(withIds){
            lToCell(r, row,"departament_id", nc++);
        }
sToCell(r, row,"departament_pkey", nc++);
sToCell(r, row,"departament_pname", nc++);
// baseTimePeriod
if(withIds){
            lToCell(r, row,"base_time_period_id", nc++);
        }
sToCell(r, row,"base_time_period_pkey", nc++);
// base
if(withIds){
            lToCell(r, row,"base_id", nc++);
        }
sToCell(r, row,"base_pkey", nc++);
sToCell(r, row,"base_pname", nc++);
// timePeriod
if(withIds){
            lToCell(r, row,"time_period_id", nc++);
        }
sToCell(r, row,"time_period_pkey", nc++);
sToCell(r, row,"time_period_pname", nc++);
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
//DepartamentJob 4        
        slcb.doIlPSimple2( "departamentJob_pname", "departament_job_pname");    
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");  
//DepartamentBaseTimePeriod undefined        

        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");//Departament 3
        slcb.doIlPSimple2( "departament_pname", "departament_pname"); 

        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");//BaseTimePeriod undefined
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
             
    sz0.applyG1(zDepartamentJob);               
    sz0.applyG1(zDepartamentBaseTimePeriod);      
    //level 2
    
    sz0.applyG2(zDepartamentJob,zDepartament);                           
    sz0.applyG2(zDepartamentBaseTimePeriod,zBaseTimePeriod);                           
    //level 3
    
        sz0.applyG3(zDepartamentBaseTimePeriod,zBaseTimePeriod,zBase);               
        sz0.applyG3(zDepartamentBaseTimePeriod,zBaseTimePeriod,zTimePeriod);               
        return sz0;
    }
}
    
        