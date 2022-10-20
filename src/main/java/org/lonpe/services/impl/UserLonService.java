
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
 *   UserLonService 
 * 
 */
   
  
public class UserLonService extends AbstractServiceLon<UserLon>{

    private static final String SQLINSERT ="INSERT INTO user_lon(pkey,email,enabled,password,pname,type_lon,username) VALUES ($1,$2,$3,$4,$5,$6,$7) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE user_lon SET email = $1,enabled = $2,password = $3,pname = $4,type_lon = $5,username = $6 WHERE id = $7 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE user_lon SET email = $1,enabled = $2,password = $3,pname = $4,type_lon = $5,username = $6 WHERE pkey = $7 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM user_lon_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM user_lon_view";
    private static final String SQLKEYS = "SELECT user_lon_pkey FROM user_lon_view";
    private static final String SQLIDBYPKEY = "SELECT id from user_lon WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from user_lon WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM user_lon WHERE id = $1 returning id";
    private static final String TABLENAME ="user_lon";
    
    
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
    
    private static String sql00 = "SELECT user_lon.id as user_lon_id,
user_lon.pkey as user_lon_pkey,
user_lon.email as user_lon_email,
user_lon.enabled as user_lon_enabled,
user_lon.pname as user_lon_pname,
user_lon.type_lon as user_lon_type_lon,
user_lon.username as user_lon_username 
  FROM 
  user_lon ; 
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

        
    dcModel.put("dc", "userLon");

//ID 
    names.add("id");

    sortMapFields.put("id","user_lon_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("user_lon.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("user_lon.user_lon_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "user_lon_pkey");                   
 
    ps.add(pkey);
 
//email
    names.add("email");
    insertMapFields.put("user_lon.email","email");  

//Create property email       
    final JsonObject email = ps00a("email", "String",true);

    sortMapFields.put("email", "user_lon_email");                   

// hasIndex 
    email.put("withIndex",true);  
 
    ps.add(email);
 
//enabled
    names.add("enabled");
    insertMapFields.put("user_lon.enabled","enabled");  

//Create property enabled       
    final JsonObject enabled = ps00a("enabled", "Boolean",true);

    sortMapFields.put("enabled", "user_lon_enabled");               
 
    ps.add(enabled);
 
//pname
    names.add("pname");
    insertMapFields.put("user_lon.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "user_lon_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//typeLon
    names.add("typeLon");
    insertMapFields.put("user_lon.type_lon","typeLon");  

//Create property typeLon       
    final JsonObject typeLon = ps00a("typeLon", "String",true);

    sortMapFields.put("typeLon", "user_lon_type_lon");                   

    final JsonArray typeLonInList = new JsonArray();
                typeLonInList.add("ADM"); 
typeLonInList.add("SUBADM"); 
typeLonInList.add("AGENT"); 
typeLonInList.add("THIRD"); 
    typeLon.put("inList",typeLonInList );                
 
    ps.add(typeLon);
 
//username
    names.add("username");
    insertMapFields.put("user_lon.username","username");  

//Create property username       
    final JsonObject username = ps00a("username", "String",true);

    sortMapFields.put("username", "user_lon_username");                   

// hasIndex 
    username.put("withIndex",true);  
 
    ps.add(username);

//Add ps to model            
    dcModel.put("ps", ps);        
  
    final JsonArray pspw = new JsonArray();
    
//Add password field type
    final JsonObject password = new JsonObject().put("n","password");
    pspw.add(password);      
    dcModel.put("pspw",pspw);   
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"departamentUserLons","departamentUserLon"); 
                

        applyOtm(otm,"programUserLons","programUserLon"); 
                

        applyOtm(otm,"baseUserLons","baseUserLon"); 
                

        applyOtm(otm,"comercialDocumentIns","comercialDocumentIn"); 
                

        applyOtm(otm,"comercialDocumentOuts","comercialDocumentOut"); 
                

        applyOtm(otm,"userRoles","userRole"); 
                

        applyOtm(otm,"userThirdPersons","userThirdPerson"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"invoiceLines","invoiceLineOut","comercialDocumentIns","comercialDocument",null); 
        

        applyOtm2(otm2,"payments","paymentIn","comercialDocumentIns",null,null); 
        

        applyOtm2(otm2,"invoiceLines","invoiceLineIn","comercialDocumentOuts","comercialDocument",null); 
        

        applyOtm2(otm2,"payments","paymentOut","comercialDocumentOuts",null,null); 
        

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
        jsa.add(r.getLong("user_lon_id") );
        jsa.add(r.getString("user_lon_pkey") );
        jsa.add(r.getString("user_lon_email") );
        jsa.add(r.getBoolean("user_lon_enabled") );
        jsa.add(r.getString("user_lon_pname") );
        jsa.add(r.getString("user_lon_type_lon") );
        jsa.add(r.getString("user_lon_username") );
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
                m_.put("userLon_id","Long");
            }
            
    //pkey       
            m_.put("userLon_pkey","String"); 
            
    //email       
            m_.put("userLon_email","String"); 
            
    //enabled       
            m_.put("userLon_enabled","Boolean"); 
            
    //pname       
            m_.put("userLon_pname","String"); 
            
    //typeLon       
            m_.put("userLon_typeLon","String"); 
            
    //username       
            m_.put("userLon_username","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"user_lon_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"user_lon_pkey", nc++); 
    //email       
            sToCell(r, row,"user_lon_email", nc++); 
    //enabled     
                bToCell(r, row,"user_lon_enabled", nc++); 
    //pname       
            sToCell(r, row,"user_lon_pname", nc++); 
    //typeLon       
            sToCell(r, row,"user_lon_type_lon", nc++); 
    //username       
            sToCell(r, row,"user_lon_username", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE user_lon_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final UserLon dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getEmail());
        t.addBoolean(dc0.getEnabled());
        t.addString(dc0.getPassword());
        t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());
        t.addString(dc0.getUsername());
    }

    @Override
    public void fillTupleUpdate(final UserLon dc0, final Tuple t){
                t.addString(dc0.getEmail());
        t.addBoolean(dc0.getEnabled());
        t.addString(dc0.getPassword());
        t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());
        t.addString(dc0.getUsername());

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("email", obj, t);

    fTBoolean("enabled", obj, t);

    fTString("password", obj, t);

    fTString("pname", obj, t);

    fTString("typeLon", obj, t);

    fTString("username", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("email", js, t);

    fTBoolean("enabled", js, t);

    fTString("password", js, t);

    fTString("pname", js, t);

    fTString("typeLon", js, t);

    fTString("username", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("email", js, t);
fTBoolean("enabled", js, t);
fTString("password", js, t);
fTString("pname", js, t);
fTString("typeLon", js, t);
fTString("username", js, t);
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
    public UserLon doFrom(final Row r){
        final UserLon userLon = new UserLon();
         userLon.setId(r.getLong("user_lon_id"));
         
                userLon.setPkey(  r.getString("user_lon_pkey"));
         
                userLon.setEmail(  r.getString("user_lon_email"));
         
                userLon.setEnabled(  r.getBoolean("user_lon_enabled"));
         
                userLon.setPassword(  r.getString("user_lon_password"));
         
                userLon.setPname(  r.getString("user_lon_pname"));
         
                userLon.setTypeLon(  r.getString("user_lon_type_lon"));
         
                userLon.setUsername(  r.getString("user_lon_username"));  
        return userLon;
    }
    
    @Override
    public UserLon doFromJson(final JsonObject js){
        UserLon userLon = new UserLon();
        userLon.setId(js.getLong("id"));
        
                userLon.setPkey(js.getString("pkey"));
        userLon.setEmail(js.getString("email"));
        userLon.setEnabled(js.getBoolean("enabled"));
        userLon.setPassword(js.getString("password"));
        userLon.setPname(js.getString("pname"));
        userLon.setTypeLon(js.getString("typeLon"));
        userLon.setUsername(js.getString("username"));
        return userLon;
    }

    @Override
    public JsonObject toJson(final UserLon o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("email",  o.getEmail() );
        jso.put("enabled",  o.getEnabled() );
        jso.put("pname",  o.getPname() );
        jso.put("typeLon",  o.getTypeLon() );
        jso.put("username",  o.getUsername() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "user_lon_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "user_lon_pkey");
    slcb.doEqInPSimple( "pkey", "user_lon_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "user_lon_pname");
    slcb.doEqInPSimple( "pname", "user_lon_pname");             
// withIndex true
    slcb.doIlPSimple2( "email", "user_lon_email");
    slcb.doEqInPSimple( "email", "user_lon_email");                    
    slcb.doEqInPSimple( "typeLon", "user_lon_type_lon");                     
// withIndex true
    slcb.doIlPSimple2( "username", "user_lon_username");
    slcb.doEqInPSimple( "username", "user_lon_username");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"user_lon");
        sz0.addField("COUNT(user_lon.id) as c_user_lon_id").addName("count");
        
        
        return sz0;
    }
}
    
        