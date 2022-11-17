
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







/**
 *   BaseService 
 * 
 */
  
public class BaseService extends AbstractServiceLon<Base>{

    private static final String SQLINSERT ="INSERT INTO base(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE base SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE base SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM base_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM base_view";
    private static final String SQLKEYS = "SELECT base_pkey FROM base_view";
    private static final String SQLIDBYPKEY = "SELECT id from base WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from base WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM base WHERE id = $1 returning id";
    private static final String TABLENAME ="base";
    

    public BaseService() {
        init0();
    }

    

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
    private static String sql00 = "SELECT base.id as base_id,
base.pkey as base_pkey,
base.pname as base_pname 
  FROM 
  base "
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
        
    dcModel.put("dc", "base");

//ID 
    names.add("id");

    sortMapFields.put("id","base_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","base");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("base.base_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","base");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"baseTimePeriods","baseTimePeriod"); 
                

        applyOtm(otm,"workSpaceGroups","workSpaceGroup"); 
                

        applyOtm(otm,"baseUserLons","baseUserLon"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"departamentBaseTimePeriods","departamentBaseTimePeriod","baseTimePeriods",null,null); 
        

        applyOtm2(otm2,"programBaseTimePeriods","programBaseTimePeriod","baseTimePeriods",null,null); 
        

        applyOtm2(otm2,"workSpaces","workSpace","workSpaceGroups",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"contracts","contractOut","departamentBaseTimePeriods",null,null,null); 
        

        applyOtm3(otm3,"departamentJobInstances","departamentJobInstance","departamentBaseTimePeriods",null,null,null); 
        

        applyOtm3(otm3,"contracts","contractIn","programBaseTimePeriods",null,null,null); 
        

        applyOtm3(otm3,"appointments","appointment","workSpaces",null,null,null); 
        

        applyOtm3(otm3,"stockRack","stockRack","workSpaces",null,null,null); 
        

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
        jsa.add(r.getLong("base_id") );       
        jsa.add(r.getString("base_pkey") );       
        jsa.add(r.getString("base_pname") );
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
        m.put("base_id",LONG);
    }        
//pkey    
    m.put("base_pkey",STRING);              
//pname    
    m.put("base_pname",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"base_id", nc++); 
    }            //pkey       
            sToCell(r, row,"base_pkey", nc++);     //pname       
            sToCell(r, row,"base_pname", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE base_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Base dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final Base dc0, final Tuple t){
        
    t.addString(dc0.getPname());
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("pname", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
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
    public Base doFrom(final Row r){
        final Base base = new Base();
         base.setId(r.getLong("base_id"));         
                base.setPkey(  r.getString("base_pkey"));                       
                base.setPname(  r.getString("base_pname"));                
        return base;
    }
    
    @Override
    public Base doFromJson(final JsonObject js){
        Base base = new Base();
        base.setId(js.getLong("id"));
        
                
                base.setPkey(js.getString("pkey"));        
                base.setPname(js.getString("pname"));
        return base;
    }

    @Override
    public JsonObject toJson(final Base o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "base_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "base_pkey");
    slcb.doEqInPSimple( "pkey", "base_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "base_pname");
    slcb.doEqInPSimple( "pname", "base_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"base");
        sz0.addField("COUNT(base.id) as c_base_id").addName("count");
        
        
        return sz0;
    }
}
    
        