
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
 *   AirCompanyService 
 * 
 */
   
  
public class AirCompanyService extends AbstractServiceLon<AirCompany>{

    private static final String SQLINSERT ="INSERT INTO air_company(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE air_company SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE air_company SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM air_company_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM air_company_view";
    private static final String SQLKEYS = "SELECT air_company_pkey FROM air_company_view";
    private static final String SQLIDBYPKEY = "SELECT id from air_company WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from air_company WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM air_company WHERE id = $1 returning id";
    private static final String TABLENAME ="air_company";
    
    
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
    
    private static String sql00 = "SELECT air_company.id as air_company_id,
air_company.pkey as air_company_pkey,
air_company.pname as air_company_pname 
  FROM 
  air_company ; 
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

        
    dcModel.put("dc", "airCompany");

//ID 
    names.add("id");

    sortMapFields.put("id","air_company_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("air_company.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("air_company.air_company_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "air_company_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("air_company.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "air_company_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

//ON RELATION laCompania    
            applyOtm(otm,"planes","plane","laCompania"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"fligths","fligth","planes",null,"laCompania"); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"fligthInstances","fligthInstance","fligths","theFligth",null,"laCompania"); 
        

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
        jsa.add(r.getLong("air_company_id") );
        jsa.add(r.getString("air_company_pkey") );
        jsa.add(r.getString("air_company_pname") );
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
                m_.put("airCompany_id","Long");
            }
            
    //pkey       
            m_.put("airCompany_pkey","String"); 
            
    //pname       
            m_.put("airCompany_pname","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"air_company_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"air_company_pkey", nc++); 
    //pname       
            sToCell(r, row,"air_company_pname", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE air_company_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final AirCompany dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final AirCompany dc0, final Tuple t){
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
    public AirCompany doFrom(final Row r){
        final AirCompany airCompany = new AirCompany();
         airCompany.setId(r.getLong("air_company_id"));
         
                airCompany.setPkey(  r.getString("air_company_pkey"));
         
                airCompany.setPname(  r.getString("air_company_pname"));  
        return airCompany;
    }
    
    @Override
    public AirCompany doFromJson(final JsonObject js){
        AirCompany airCompany = new AirCompany();
        airCompany.setId(js.getLong("id"));
        
                airCompany.setPkey(js.getString("pkey"));
        airCompany.setPname(js.getString("pname"));
        return airCompany;
    }

    @Override
    public JsonObject toJson(final AirCompany o) {        
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
       slcb.doInLongCondition("id", "air_company_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "air_company_pkey");
    slcb.doEqInPSimple( "pkey", "air_company_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "air_company_pname");
    slcb.doEqInPSimple( "pname", "air_company_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"air_company");
        sz0.addField("COUNT(air_company.id) as c_air_company_id").addName("count");
        
        
        return sz0;
    }
}
    
        