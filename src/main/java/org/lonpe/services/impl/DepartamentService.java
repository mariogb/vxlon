
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





/**
 *   DepartamentService 
 * 
 */
   
  
public class DepartamentService extends AbstractServiceLon<Departament>{

    private static final String SQLINSERT ="INSERT INTO departament(pkey,description,fast_key,pname) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE departament SET description = $1,fast_key = $2,pname = $3 WHERE id = $4 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE departament SET description = $1,fast_key = $2,pname = $3 WHERE pkey = $4 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM departament_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM departament_view";
    private static final String SQLKEYS = "SELECT departament_pkey FROM departament_view";
    private static final String SQLIDBYPKEY = "SELECT id from departament WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from departament WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM departament WHERE id = $1 returning id";
    private static final String TABLENAME ="departament";
    
    
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
    
    private static String sql00 = "SELECT departament.id as departament_id,
departament.pkey as departament_pkey,
departament.description as departament_description,
departament.fast_key as departament_fast_key,
departament.pname as departament_pname 
  FROM 
  departament ; 
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

        
    dcModel.put("dc", "departament");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("departament.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament.departament_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "departament_pkey");                   
 
    ps.add(pkey);
 
//description
    names.add("description");
    insertMapFields.put("departament.description","description");  

//Create property description       
    final JsonObject description = ps00a("description", "String",false);
 
    ps.add(description);
 
//fastKey
    names.add("fastKey");
    insertMapFields.put("departament.fast_key","fastKey");  

//Create property fastKey       
    final JsonObject fastKey = ps00a("fastKey", "String",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//pname
    names.add("pname");
    insertMapFields.put("departament.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament.departament_uidx_pname","pname");  

// IS Unique     
    pname.put("uq",true);                    

    sortMapFields.put("pname", "departament_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"departamentBaseTimePeriods","departamentBaseTimePeriod"); 
                

        applyOtm(otm,"departamentUserLons","departamentUserLon"); 
                

        applyOtm(otm,"departamenJobs","departamentJob"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"contracts","contractOut","departamentBaseTimePeriods",null,null); 
        

        applyOtm2(otm2,"departamentJobInstances","departamentJobInstance","departamentBaseTimePeriods",null,null); 
        

        applyOtm2(otm2,"departamentJobInstances","departamentJobInstance","departamenJobs",null,null); 
        

        applyOtm2(otm2,"programJobs","programJob","departamenJobs",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"appointments","appointment","contracts",null,null,null); 
        

        applyOtm3(otm3,"comercialDocuments","comercialDocumentOut","contracts","contract",null,null); 
        

        applyOtm3(otm3,"appointmens","appointment","departamentJobInstances",null,null,null); 
        

        applyOtm3(otm3,"appointmens","appointment","departamentJobInstances",null,null,null); 
        

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
        jsa.add(r.getLong("departament_id") );
        jsa.add(r.getString("departament_pkey") );
        jsa.add(r.getString("departament_description") );
        jsa.add(r.getString("departament_fast_key") );
        jsa.add(r.getString("departament_pname") );
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
                m_.put("departament_id","Long");
            }
            
    //pkey       
            m_.put("departament_pkey","String"); 
            
    //description       
            m_.put("departament_description","String"); 
            
    //fastKey       
            m_.put("departament_fastKey","String"); 
            
    //pname       
            m_.put("departament_pname","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"departament_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"departament_pkey", nc++); 
    //description       
            sToCell(r, row,"departament_description", nc++); 
    //fastKey       
            sToCell(r, row,"departament_fast_key", nc++); 
    //pname       
            sToCell(r, row,"departament_pname", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE departament_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Departament dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final Departament dc0, final Tuple t){
                t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("description", obj, t);

    fTString("fastKey", obj, t);

    fTString("pname", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("description", js, t);

    fTString("fastKey", js, t);

    fTString("pname", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("description", js, t);
fTString("fastKey", js, t);
fTString("pname", js, t);
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
    public Departament doFrom(final Row r){
        final Departament departament = new Departament();
         departament.setId(r.getLong("departament_id"));
         
                departament.setPkey(  r.getString("departament_pkey"));
         
                departament.setDescription(  r.getString("departament_description"));
         
                departament.setFastKey(  r.getString("departament_fast_key"));
         
                departament.setPname(  r.getString("departament_pname"));  
        return departament;
    }
    
    @Override
    public Departament doFromJson(final JsonObject js){
        Departament departament = new Departament();
        departament.setId(js.getLong("id"));
        
                departament.setPkey(js.getString("pkey"));
        departament.setDescription(js.getString("description"));
        departament.setFastKey(js.getString("fastKey"));
        departament.setPname(js.getString("pname"));
        return departament;
    }

    @Override
    public JsonObject toJson(final Departament o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("description",  o.getDescription() );
        jso.put("fastKey",  o.getFastKey() );
        jso.put("pname",  o.getPname() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "departament_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "departament_pkey");
    slcb.doEqInPSimple( "pkey", "departament_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "departament_pname");
    slcb.doEqInPSimple( "pname", "departament_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "departament_fast_key");
    slcb.doEqInPSimple( "fastKey", "departament_fast_key");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"departament");
        sz0.addField("COUNT(departament.id) as c_departament_id").addName("count");
        
        
        return sz0;
    }
}
    
        