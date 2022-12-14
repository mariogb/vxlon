
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
 *   ProgramJobService 
 * 
 */
  
public class ProgramJobService extends AbstractServiceLon<ProgramJob>{

    private static final String SQLINSERT ="INSERT INTO program_job(pkey,description,fast_key,nhoras,pname,program_id,departament_job_id) VALUES ($1,$2,$3,$4,$5,$6,$7) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE program_job SET description = $1,fast_key = $2,nhoras = $3,pname = $4 WHERE id = $9 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE program_job SET description = $1,fast_key = $2,nhoras = $3,pname = $4 WHERE pkey = $9 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM program_job_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM program_job_view";
    private static final String SQLKEYS = "SELECT program_job_pkey FROM program_job_view";
    private static final String SQLIDBYPKEY = "SELECT id from program_job WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from program_job WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM program_job WHERE id = $1 returning id";
    private static final String TABLENAME ="program_job";
    

    public ProgramJobService() {
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
    private static String sql00 = "SELECT program_job.id as program_job_id,
program_job.pkey as program_job_pkey,
program_job.description as program_job_description,
program_job.fast_key as program_job_fast_key,
program_job.nhoras as program_job_nhoras,
program_job.pname as program_job_pname,
program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,
departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname 
  FROM 
  program_job,
  program as program,
  departament_job as departament_job,
  departament as departament  
 WHERE 
 program_job.program_id = program.id
 AND program_job.departament_job_id = departament_job.id
 AND departament_job.departament_id = departament.id"
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
        
    dcModel.put("dc", "programJob");

//ID 
    names.add("id");

    sortMapFields.put("id","program_job_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","program_job");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("program_job.program_job_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//description
    doField("description","description","program_job");               

//Create property description       
    final JsonObject description = psString("description",false);
 
    ps.add(description);
 
//fastKey
    doField("fastKey","fast_key","program_job");               

//Create property fastKey       
    final JsonObject fastKey = psString("fastKey",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//nhoras
    doFieldSort("nhoras","nhoras","program_job");               

//Create property nhoras       
    final JsonObject nhoras = psInteger("nhoras",true);
 
    ps.add(nhoras);
 
//pname
    doFieldSort("pname","pname","program_job");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("program_job.program_job_uidx_pname","pname");  

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

//(1)  program
    doFieldMT0("program_job","program", "program");  

    final JsonObject program =  doMto("program","program");        
   
    names.add("program_pname");
    sortMapFields.put( "program_pname", "program_pname");                
    program.put("pc","pname");          

    mto.add(program);
        

    //1  program  -- program_id
    final ZtatUnitInfoLon zProgram = new ZtatUnitInfoLon("program_id", "program",  "program","pname","program");
    mz1.put("zProgram", zProgram);    

//(1)  departamentJob
    doFieldMT0("program_job","departamentJob", "departament_job");  

    final JsonObject departamentJob =  doMto("departamentJob","departamentJob");        
   
    names.add("departamentJob_pname");
    sortMapFields.put( "departamentJob_pname", "departament_job_pname");                
    departamentJob.put("pc","pname");          

    mto.add(departamentJob);
        

    //1  departament_job  -- departament_job_id
    final ZtatUnitInfoLon zDepartamentJob = new ZtatUnitInfoLon("departament_job_id", "departamentJob",  "departament_job","pname","departament_job");
    mz1.put("zDepartamentJob", zDepartamentJob);    

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

    dcModel.put("mto2",mto2);    
        
        
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
        jsa.add(r.getLong("program_job_id") );       
        jsa.add(r.getString("program_job_pkey") );       
        jsa.add(r.getString("program_job_description") );       
        jsa.add(r.getString("program_job_fast_key") );       
        jsa.add(r.getInteger("program_job_nhoras") );       
        jsa.add(r.getString("program_job_pname") );
    jsa.add(r.getLong("program_id"));
    jsa.add(r.getString("program_pkey"));   
    
        
    jsa.add(r.getString("program_pname"));
    jsa.add(r.getLong("departament_job_id"));
    jsa.add(r.getString("departament_job_pkey"));   
    
        
    jsa.add(r.getString("departament_job_pname"));
    jsa.add(r.getLong("departament_id"));
    jsa.add(r.getString("departament_pkey"));
    

    jsa.add(r.getString("departament_pname"));
    
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
        m.put("programJob_id",LONG);
    }        
//pkey    
    m.put("programJob_pkey",STRING);              
//description    
    m.put("programJob_description",STRING);              
//fastKey    
    m.put("programJob_fastKey",STRING);              
//nhoras    
    m.put("programJob_nhoras",INTEGER);              
//pname    
    m.put("programJob_pname",STRING);          
    if(level<1){
        return m;    
    }       
// program   program
    if(withIds){
        m.put("program_id",LONG);                       
    }
    m.put("program_pkey",STRING);     
    m.put("program_pname",STRING);   
// departamentJob   departamentJob
    if(withIds){
        m.put("departamentJob_id",LONG);                       
    }
    m.put("departamentJob_pkey",STRING);     
    m.put("departamentJob_pname",STRING);  
//[2] departament --   departament
    if(withIds){
        m.put("departament_id",LONG);              
    }              
    m.put("departament_pkey",STRING);  
        
    m.put("departament_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"program_job_id", nc++); 
    }            //pkey       
            sToCell(r, row,"program_job_pkey", nc++);     //description       
            sToCell(r, row,"program_job_description", nc++);     //fastKey       
            sToCell(r, row,"program_job_fast_key", nc++);     //nhoras            
            ldToCell(r, row,"program_job_nhoras", nc++);     //pname       
            sToCell(r, row,"program_job_pname", nc++); 
//program   program        
    if(withIds){
        lToCell(r, row,"program_id", nc++);
    }
    sToCell(r, row,"program_pkey", nc++);
    sToCell(r, row,"program_pname", nc++);
//departamentJob   departament_job        
    if(withIds){
        lToCell(r, row,"departament_job_id", nc++);
    }
    sToCell(r, row,"departament_job_pkey", nc++);
    sToCell(r, row,"departament_job_pname", nc++);
// departament  departament
    if(withIds){
       lToCell(r, row,"departament_id", nc++);
    }

    sToCell(r, row,"departament_pkey", nc++);

    sToCell(r, row,"departament_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE program_job_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ProgramJob dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getDescription());        
    t.addString(dc0.getFastKey());        
    t.addInteger(dc0.getNhoras());        
    t.addString(dc0.getPname());   
    if(dc0.getProgram()!=null){
       t.addLong(dc0.getProgram().getId());
    }   
    if(dc0.getDepartamentJob()!=null){
       t.addLong(dc0.getDepartamentJob().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final ProgramJob dc0, final Tuple t){
        
    t.addString(dc0.getDescription());
    t.addString(dc0.getFastKey());
    t.addInteger(dc0.getNhoras());
    t.addString(dc0.getPname());   
//      if(dc0.getProgram()!=null){
//            t.addLong(dc0.getProgram().getId());
//      }   
//      if(dc0.getDepartamentJob()!=null){
//            t.addLong(dc0.getDepartamentJob().getId());
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

    fTLong("program_id",obj,t);

    fTLong("departamentJob_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> program = mapParents.get("program");
            final Long program_id = program.get((String)obj.get("program_pkey"));
            obj.put("program_id", program_id);
      
            final Map<String, Long> departamentJob = mapParents.get("departamentJob");
            final Long departamentJob_id = departamentJob.get((String)obj.get("departamentJob_pkey"));
            obj.put("departamentJob_id", departamentJob_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("description", js, t);
    fTString("fastKey", js, t);
    fTInteger("nhoras", js, t);
    fTString("pname", js, t);     
    fTLong("program_id",js,t);     
    fTLong("departamentJob_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("description", js, t);
fTString("fastKey", js, t);
fTInteger("nhoras", js, t);
fTString("pname", js, t);

            //     fTLong("program_id",js,t);

            //     fTLong("departamentJob_id",js,t);
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
    public ProgramJob doFrom(final Row r){
        final ProgramJob programJob = new ProgramJob();
         programJob.setId(r.getLong("program_job_id"));         
                programJob.setPkey(  r.getString("program_job_pkey"));                       
                programJob.setDescription(  r.getString("program_job_description"));                       
                programJob.setFastKey(  r.getString("program_job_fast_key"));                       
                programJob.setNhoras(  r.getInteger("program_job_nhoras"));                       
                programJob.setPname(  r.getString("program_job_pname"));              
        final Program program = new Program();
        program.setId(r.getLong("program_id"));
        program.setPkey(r.getString("program_pkey"));
        
        program.setPname(r.getString("program_pname"));
        programJob.setProgram(program);
        
        final DepartamentJob departamentJob = new DepartamentJob();
        departamentJob.setId(r.getLong("departament_job_id"));
        departamentJob.setPkey(r.getString("departament_job_pkey"));
        
        departamentJob.setPname(r.getString("departament_job_pname"));
        programJob.setDepartamentJob(departamentJob);
        
        final Departament departament = new Departament();
        departament.setId(r.getLong("departament_id"));
        departament.setPkey(r.getString("departament_pkey"));
        departament.setPname(r.getString("departament_pname"));
 
        departamentJob.setDepartament(departament);   
        return programJob;
    }
    
    @Override
    public ProgramJob doFromJson(final JsonObject js){
        ProgramJob programJob = new ProgramJob();
        programJob.setId(js.getLong("id"));
        
                
                programJob.setPkey(js.getString("pkey"));        
                programJob.setDescription(js.getString("description"));        
                programJob.setFastKey(js.getString("fastKey"));        
                programJob.setNhoras(js.getInteger("nhoras"));        
                programJob.setPname(js.getString("pname"));        
            programJob.setId(js.getLong("program_id"));        
            programJob.setId(js.getLong("departamentJob_id"));
        return programJob;
    }

    @Override
    public JsonObject toJson(final ProgramJob o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("description",  o.getDescription() );
        jso.put("fastKey",  o.getFastKey() );
        jso.put("nhoras",  o.getNhoras() );
        jso.put("pname",  o.getPname() );

            final Program program = o.getProgram();
            if(program!=null){
                jso.put("program_id", program.getId());
                jso.put("program_pkey", program.getPkey());
            }
            

            final DepartamentJob departamentJob = o.getDepartamentJob();
            if(departamentJob!=null){
                jso.put("departamentJob_id", departamentJob.getId());
                jso.put("departamentJob_pkey", departamentJob.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "program_job_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "program_job_pkey");
    slcb.doEqInPSimple( "pkey", "program_job_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "program_job_pname");
    slcb.doEqInPSimple( "pname", "program_job_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "program_job_fast_key");
    slcb.doEqInPSimple( "fastKey", "program_job_fast_key");                       
    slcb.doGEPSimpleInt( "nhoras", "program_job_nhoras");
    slcb.doLTPSimpleInt( "nhoras", "program_job_nhoras");                
        
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");  
//Program 3        --
        slcb.doIlPSimple2( "program_pname", "program_pname");    
        
        slcb.doIlPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doEQPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doInLongCondition("departamentJob_id", "departament_job_id");  
//DepartamentJob 4        --
        slcb.doIlPSimple2( "departamentJob_pname", "departament_job_pname");    
        
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
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"program_job");
        sz0.addField("COUNT(program_job.id) as c_program_job_id").addName("count");
        
    sz0.addField("sum(program_job.nhoras) as sum_program_job_nhoras").addName("sum_nhoras"); 
        
//level 1
             
    sz0.applyG1(mz1.get("zProgram"))   ;               
    sz0.applyG1(mz1.get("zDepartamentJob"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zDepartamentFromDepartamentJob"));                           
//level 3    
        return sz0;
    }
}
    
        