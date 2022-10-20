
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
 *   UserRoleService 
 * 
 */
   
  
public class UserRoleService extends AbstractServiceLon<UserRole>{

    private static final String SQLINSERT ="INSERT INTO user_role(pkey,user_lon_id,role_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE user_role SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE user_role SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM user_role_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM user_role_view";
    private static final String SQLKEYS = "SELECT user_role_pkey FROM user_role_view";
    private static final String SQLIDBYPKEY = "SELECT id from user_role WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from user_role WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM user_role WHERE id = $1 returning id";
    private static final String TABLENAME ="user_role";
    
//1
    private static final ZtatUnitInfoLon zUserLon;

//1
    private static final ZtatUnitInfoLon zRole;
    
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
    
    private static String sql00 = "SELECT user_role.id as user_role_id,
user_role.pkey as user_role_pkey,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname,
role.id as role_id,role.pkey as role_pkey,role.pname as role_pname 
  FROM 
  user_role,
  user_lon as user_lon,
  role as role  
 WHERE 
 user_role.user_lon_id = user_lon.id
 AND user_role.role_id = role.id; 
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

        
    dcModel.put("dc", "userRole");

//ID 
    names.add("id");

    sortMapFields.put("id","user_role_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("user_role.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("user_role.user_role_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "user_role_pkey");                   
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  userLon --------------------
    names.add("userLon_id");      
    insertMapFields.put("user_role.user_lon_id","userLon_id");    
       
    names.add("userLon_pkey");        
    sortMapFields.put( "userLon_pkey", "user_lon_pkey");        

    final JsonObject userLon =  doMto("userLon","userLon");        
   
    names.add("userLon_pname");
    sortMapFields.put( "userLon_pname", "user_lon_pname");         

    userLon.put("pc","pname");          

    mto.add(userLon);
        

//(1)  role --------------------
    names.add("role_id");      
    insertMapFields.put("user_role.role_id","role_id");    
       
    names.add("role_pkey");        
    sortMapFields.put( "role_pkey", "role_pkey");        

    final JsonObject role =  doMto("role","role");        
   
    names.add("role_pname");
    sortMapFields.put( "role_pname", "role_pname");         

    role.put("pc","pname");          

    mto.add(role);
        

    dcModel.put("mto",mto);     
        

        
//1  user_lon  -- user_lon_id
    zUserLon = new ZtatUnitInfoLon("user_lon_id", "userLon",  "user_lon","pname","user_lon");

//1  role  -- role_id
    zRole = new ZtatUnitInfoLon("role_id", "role",  "role","pname","role");

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
        jsa.add(r.getLong("user_role_id") );
        jsa.add(r.getString("user_role_pkey") );
        jsa.add(r.getLong("user_lon_id"));
        jsa.add(r.getString("user_lon_pkey"));
        jsa.add(r.getString("user_lon_pname"));
        jsa.add(r.getLong("role_id"));
        jsa.add(r.getString("role_pkey"));
        jsa.add(r.getString("role_pname"));
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
                m_.put("userRole_id","Long");
            }
            
    //pkey       
            m_.put("userRole_pkey","String"); 
            
if(level<1){
                return m_;    
            }
            
 // userLon
if(withIds){
            m_.put("userLon_id","Long");   
                    
            }

        m_.put("userLon_pkey","String");   
        

            m_.put("userLon_pname","String");   
            
 // role
if(withIds){
            m_.put("role_id","Long");   
                    
            }

        m_.put("role_pkey","String");   
        

            m_.put("role_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"user_role_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"user_role_pkey", nc++); 
 // userLon
if(withIds){
                    lToCell(r, row,"user_lon_id", nc++);
                 }
sToCell(r, row,"user_lon_pkey", nc++);
sToCell(r, row,"user_lon_pname", nc++);
 // role
if(withIds){
                    lToCell(r, row,"role_id", nc++);
                 }
sToCell(r, row,"role_pkey", nc++);
sToCell(r, row,"role_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE user_role_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final UserRole dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
            if(dc0.getUserLon()!=null){
               t.addLong(dc0.getUserLon().getId());
            }
   
            if(dc0.getRole()!=null){
               t.addLong(dc0.getRole().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final UserRole dc0, final Tuple t){
           
//      if(dc0.getUserLon()!=null){
//            t.addLong(dc0.getUserLon().getId());
//      }
   
//      if(dc0.getRole()!=null){
//            t.addLong(dc0.getRole().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("userLon_id",obj,t);

    fTLong("role_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> userLon = mapParents.get("userLon");
            final Long userLon_id = userLon.get((String)obj.get("userLon_pkey"));
            obj.put("userLon_id", userLon_id);
      
            final Map<String, Long> role = mapParents.get("role");
            final Long role_id = role.get((String)obj.get("role_pkey"));
            obj.put("role_id", role_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
     
    fTLong("userLon_id",js,t);
     
    fTLong("role_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("userLon_id",js,t);

            //     fTLong("role_id",js,t);
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
    public UserRole doFrom(final Row r){
        final UserRole userRole = new UserRole();
         userRole.setId(r.getLong("user_role_id"));
         
                userRole.setPkey(  r.getString("user_role_pkey"));

        final UserLon userLon = new UserLon();
        userLon.setId(r.getLong("user_lon_id"));
        userLon.setPkey(r.getString("user_lon_pkey"));
        userLon.setPname(r.getString("user_lon_pname"));
        userRole.setUserLon(userLon);
        

        final Role role = new Role();
        role.setId(r.getLong("role_id"));
        role.setPkey(r.getString("role_pkey"));
        role.setPname(r.getString("role_pname"));
        userRole.setRole(role);
          
        return userRole;
    }
    
    @Override
    public UserRole doFromJson(final JsonObject js){
        UserRole userRole = new UserRole();
        userRole.setId(js.getLong("id"));
        
                userRole.setPkey(js.getString("pkey"));
        userRole.setId(js.getLong("userLon_id"));
        userRole.setId(js.getLong("role_id"));
        return userRole;
    }

    @Override
    public JsonObject toJson(final UserRole o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final UserLon userLon = o.getUserLon();
            if(userLon!=null){
                jso.put("userLon_id", userLon.getId());
                jso.put("userLon_pkey", userLon.getPkey());
            }
            

            final Role role = o.getRole();
            if(role!=null){
                jso.put("role_id", role.getId());
                jso.put("role_pkey", role.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "user_role_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "user_role_pkey");
    slcb.doEqInPSimple( "pkey", "user_role_pkey");
        
        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");  
//UserLon 4        
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");    
        slcb.doIlPSimple2( "role_pkey", "role_pkey");
        slcb.doEQPSimple2( "role_pkey", "role_pkey");
        slcb.doInLongCondition("role_id", "role_id");  
//Role 1        
        slcb.doIlPSimple2( "role_pname", "role_pname");    

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"user_role");
        sz0.addField("COUNT(user_role.id) as c_user_role_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zUserLon);               
    sz0.applyG1(zRole);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        