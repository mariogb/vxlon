
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
    

    public DepartamentJobService() {
        init0();
    }

    
    private static final Map<String, ZtatUnitInfoLon> mz1 = new HashMap<>(6);                       

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
 departament_job.departament_id = departament.id"
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
        
    dcModel.put("dc", "departamentJob");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_job_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","departament_job");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job.departament_job_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//description
    doField("description","description","departament_job");               

//Create property description       
    final JsonObject description = psString("description",false);
 
    ps.add(description);
 
//fastKey
    doField("fastKey","fast_key","departament_job");               

//Create property fastKey       
    final JsonObject fastKey = psString("fastKey",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//nhoras
    doFieldSort("nhoras","nhoras","departament_job");               

//Create property nhoras       
    final JsonObject nhoras = psInteger("nhoras",true);
 
    ps.add(nhoras);
 
//pname
    doFieldSort("pname","pname","departament_job");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_job.departament_job_uidx_pname","pname");  

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

//(1)  departament
    doFieldMT0("departament_job","departament", "departament");  

    final JsonObject departament =  doMto("departament","departament");        
   
    names.add("departament_pname");
    sortMapFields.put( "departament_pname", "departament_pname");                
    departament.put("pc","pname");          

    mto.add(departament);
        

    //1  departament  -- departament_id
    final ZtatUnitInfoLon zDepartament = new ZtatUnitInfoLon("departament_id", "departament",  "departament","pname","departament");
    mz1.put("zDepartament", zDepartament);    

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
    public int fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        return fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m = new LinkedHashMap<>();
    
    if(withIds){
        m.put("departamentJob_id",LONG);
    }        
//pkey    
    m.put("departamentJob_pkey",STRING);              
//description    
    m.put("departamentJob_description",STRING);              
//fastKey    
    m.put("departamentJob_fastKey",STRING);              
//nhoras    
    m.put("departamentJob_nhoras",INTEGER);              
//pname    
    m.put("departamentJob_pname",STRING);          
    if(level<1){
        return m;    
    }       
// departament   departament
    if(withIds){
        m.put("departament_id",LONG);                       
    }
    m.put("departament_pkey",STRING);     
    m.put("departament_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"departament_job_id", nc++); 
    }            //pkey       
            sToCell(r, row,"departament_job_pkey", nc++);     //description       
            sToCell(r, row,"departament_job_description", nc++);     //fastKey       
            sToCell(r, row,"departament_job_fast_key", nc++);     //nhoras            
            ldToCell(r, row,"departament_job_nhoras", nc++);     //pname       
            sToCell(r, row,"departament_job_pname", nc++); 
//departament   departament        
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
//Departament 3        --
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
             
    sz0.applyG1(mz1.get("zDepartament"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        