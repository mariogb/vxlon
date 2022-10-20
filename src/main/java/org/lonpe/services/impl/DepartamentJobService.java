
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
 *   DepartamentJobService 
 * 
 */
   
  
public class DepartamentJobService extends AbstractServiceLon<DepartamentJob>{

    private static final String SQLINSERT ="INSERT INTO departament_job(pkey,description,fast_key,nhoras,pname,departament_id) VALUES ($1,$2,$3,$4,$5,$6) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE departament_job SET description = $1,fast_key = $2,nhoras = $3,pname = $4 WHERE id = $7 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE departament_job SET description = $1,fast_key = $2,nhoras = $3,pname = $4 WHERE pkey = $7 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM departament_job_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM departament_job_view";
    private static final String SQLKEYS = "SELECT departament_job_pkey FROM departament_job_view";
    private static final String SQLIDBYPKEY = "SELECT id from departament_job WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from departament_job WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM departament_job WHERE id = $1 returning id";
    private static final String TABLENAME ="departament_job";
    
//1
    private static final ZtatUnitInfoLon zDepartament;
    
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
    
    private static String sql00 = "SELECT departament_job.id as departament_job_id,
departament_job.pkey as departament_job_pkey,
departament_job.description as departament_job_description,
departament_job.fast_key as departament_job_fast_key,
departament_job.nhoras as departament_job_nhoras,
departament_job.pname as departament_job_pname,
departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname 
  FROM 
  departament_job,
  departament as departament  
 WHERE 
 departament_job.departament_id = departament.id; 
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

        
    dcModel.put("dc", "departamentJob");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_job_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("departament_job.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job.departament_job_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "departament_job_pkey");                   
 
    ps.add(pkey);
 
//description
    names.add("description");
    insertMapFields.put("departament_job.description","description");  

//Create property description       
    final JsonObject description = ps00a("description", "String",false);
 
    ps.add(description);
 
//fastKey
    names.add("fastKey");
    insertMapFields.put("departament_job.fast_key","fastKey");  

//Create property fastKey       
    final JsonObject fastKey = ps00a("fastKey", "String",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//nhoras
    names.add("nhoras");
    insertMapFields.put("departament_job.nhoras","nhoras");  

//Create property nhoras       
    final JsonObject nhoras = ps00a("nhoras", "Integer",true);

    sortMapFields.put("nhoras", "departament_job_nhoras");               
 
    ps.add(nhoras);
 
//pname
    names.add("pname");
    insertMapFields.put("departament_job.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job.departament_job_uidx_pname","pname");  

// IS Unique     
    pname.put("uq",true);                    

    sortMapFields.put("pname", "departament_job_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  departament --------------------
    names.add("departament_id");      
    insertMapFields.put("departament_job.departament_id","departament_id");    
       
    names.add("departament_pkey");        
    sortMapFields.put( "departament_pkey", "departament_pkey");        

    final JsonObject departament =  doMto("departament","departament");        
   
    names.add("departament_pname");
    sortMapFields.put( "departament_pname", "departament_pname");         

    departament.put("pc","pname");          

    mto.add(departament);
        

    dcModel.put("mto",mto);     
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"departamentJobInstances","departamentJobInstance"); 
                

        applyOtm(otm,"programJobs","programJob"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"appointmens","appointment","departamentJobInstances",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

        
//1  departament  -- departament_id
    zDepartament = new ZtatUnitInfoLon("departament_id", "departament",  "departament","pname","departament");

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
        jsa.add(r.getLong("departament_job_id") );
        jsa.add(r.getString("departament_job_pkey") );
        jsa.add(r.getString("departament_job_description") );
        jsa.add(r.getString("departament_job_fast_key") );
        jsa.add(r.getInteger("departament_job_nhoras") );
        jsa.add(r.getString("departament_job_pname") );
        jsa.add(r.getLong("departament_id"));
        jsa.add(r.getString("departament_pkey"));
        jsa.add(r.getString("departament_pname"));
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
                m_.put("departamentJob_id","Long");
            }
            
    //pkey       
            m_.put("departamentJob_pkey","String"); 
            
    //description       
            m_.put("departamentJob_description","String"); 
            
    //fastKey       
            m_.put("departamentJob_fastKey","String"); 
            
    //nhoras       
            m_.put("departamentJob_nhoras","Integer"); 
            
    //pname       
            m_.put("departamentJob_pname","String"); 
            
if(level<1){
                return m_;    
            }
            
 // departament
if(withIds){
            m_.put("departament_id","Long");   
                    
            }

        m_.put("departament_pkey","String");   
        

            m_.put("departament_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"departament_job_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"departament_job_pkey", nc++); 
    //description       
            sToCell(r, row,"departament_job_description", nc++); 
    //fastKey       
            sToCell(r, row,"departament_job_fast_key", nc++); 
    //nhoras            
            ldToCell(r, row,"departament_job_nhoras", nc++); 
    //pname       
            sToCell(r, row,"departament_job_pname", nc++); 
 // departament
if(withIds){
                    lToCell(r, row,"departament_id", nc++);
                 }
sToCell(r, row,"departament_pkey", nc++);
sToCell(r, row,"departament_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE departament_job_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final DepartamentJob dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addInteger(dc0.getNhoras());
        t.addString(dc0.getPname());
   
            if(dc0.getDepartament()!=null){
               t.addLong(dc0.getDepartament().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final DepartamentJob dc0, final Tuple t){
                t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addInteger(dc0.getNhoras());
        t.addString(dc0.getPname());
   
//      if(dc0.getDepartament()!=null){
//            t.addLong(dc0.getDepartament().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("description", obj, t);

    fTString("fastKey", obj, t);

    fTInteger("nhoras", obj, t);

    fTString("pname", obj, t);

    fTLong("departament_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> departament = mapParents.get("departament");
            final Long departament_id = departament.get((String)obj.get("departament_pkey"));
            obj.put("departament_id", departament_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("description", js, t);

    fTString("fastKey", js, t);

    fTInteger("nhoras", js, t);

    fTString("pname", js, t);
     
    fTLong("departament_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("description", js, t);
fTString("fastKey", js, t);
fTInteger("nhoras", js, t);
fTString("pname", js, t);

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
    public DepartamentJob doFrom(final Row r){
        final DepartamentJob departamentJob = new DepartamentJob();
         departamentJob.setId(r.getLong("departament_job_id"));
         
                departamentJob.setPkey(  r.getString("departament_job_pkey"));
         
                departamentJob.setDescription(  r.getString("departament_job_description"));
         
                departamentJob.setFastKey(  r.getString("departament_job_fast_key"));
         
                departamentJob.setNhoras(  r.getInteger("departament_job_nhoras"));
         
                departamentJob.setPname(  r.getString("departament_job_pname"));

        final Departament departament = new Departament();
        departament.setId(r.getLong("departament_id"));
        departament.setPkey(r.getString("departament_pkey"));
        departament.setPname(r.getString("departament_pname"));
        departamentJob.setDepartament(departament);
          
        return departamentJob;
    }
    
    @Override
    public DepartamentJob doFromJson(final JsonObject js){
        DepartamentJob departamentJob = new DepartamentJob();
        departamentJob.setId(js.getLong("id"));
        
                departamentJob.setPkey(js.getString("pkey"));
        departamentJob.setDescription(js.getString("description"));
        departamentJob.setFastKey(js.getString("fastKey"));
        departamentJob.setNhoras(js.getInteger("nhoras"));
        departamentJob.setPname(js.getString("pname"));
        departamentJob.setId(js.getLong("departament_id"));
        return departamentJob;
    }

    @Override
    public JsonObject toJson(final DepartamentJob o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("description",  o.getDescription() );
        jso.put("fastKey",  o.getFastKey() );
        jso.put("nhoras",  o.getNhoras() );
        jso.put("pname",  o.getPname() );

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
       slcb.doInLongCondition("id", "departament_job_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "departament_job_pkey");
    slcb.doEqInPSimple( "pkey", "departament_job_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "departament_job_pname");
    slcb.doEqInPSimple( "pname", "departament_job_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "departament_job_fast_key");
    slcb.doEqInPSimple( "fastKey", "departament_job_fast_key");                       
    slcb.doGEPSimpleInt( "nhoras", "departament_job_nhoras");
    slcb.doLTPSimpleInt( "nhoras", "departament_job_nhoras");                
        
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");  
//Departament 3        
        slcb.doIlPSimple2( "departament_pname", "departament_pname");    

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"departament_job");
        sz0.addField("COUNT(departament_job.id) as c_departament_job_id").addName("count");
        
    sz0.addField("sum(departament_job.nhoras) as sum_departament_job_nhoras").addName("sum_nhoras");
        
        
//level 1
             
    sz0.applyG1(zDepartament);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        