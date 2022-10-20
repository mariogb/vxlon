
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
 *   UserThirdPersonService 
 * 
 */
   
  
public class UserThirdPersonService extends AbstractServiceLon<UserThirdPerson>{

    private static final String SQLINSERT ="INSERT INTO user_third_person(pkey,user_lon_id,third_person_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE user_third_person SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE user_third_person SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM user_third_person_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM user_third_person_view";
    private static final String SQLKEYS = "SELECT user_third_person_pkey FROM user_third_person_view";
    private static final String SQLIDBYPKEY = "SELECT id from user_third_person WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from user_third_person WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM user_third_person WHERE id = $1 returning id";
    private static final String TABLENAME ="user_third_person";
    
//1
    private static final ZtatUnitInfoLon zUserLon;

//1
    private static final ZtatUnitInfoLon zThirdPerson;
    
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
    
    private static String sql00 = "SELECT user_third_person.id as user_third_person_id,
user_third_person.pkey as user_third_person_pkey,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname,
third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname 
  FROM 
  user_third_person,
  user_lon as user_lon,
  third_person as third_person  
 WHERE 
 user_third_person.user_lon_id = user_lon.id
 AND user_third_person.third_person_id = third_person.id; 
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

        
    dcModel.put("dc", "userThirdPerson");

//ID 
    names.add("id");

    sortMapFields.put("id","user_third_person_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("user_third_person.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("user_third_person.user_third_person_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "user_third_person_pkey");                   
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  userLon --------------------
    names.add("userLon_id");      
    insertMapFields.put("user_third_person.user_lon_id","userLon_id");    
       
    names.add("userLon_pkey");        
    sortMapFields.put( "userLon_pkey", "user_lon_pkey");        

    final JsonObject userLon =  doMto("userLon","userLon");        
   
    names.add("userLon_pname");
    sortMapFields.put( "userLon_pname", "user_lon_pname");         

    userLon.put("pc","pname");          

    mto.add(userLon);
        

//(1)  thirdPerson --------------------
    names.add("thirdPerson_id");      
    insertMapFields.put("user_third_person.third_person_id","thirdPerson_id");    
       
    names.add("thirdPerson_pkey");        
    sortMapFields.put( "thirdPerson_pkey", "third_person_pkey");        

    final JsonObject thirdPerson =  doMto("thirdPerson","thirdPerson");        
   
    names.add("thirdPerson_pname");
    sortMapFields.put( "thirdPerson_pname", "third_person_pname");         

    thirdPerson.put("pc","pname");          

    mto.add(thirdPerson);
        

    dcModel.put("mto",mto);     
        

        
//1  user_lon  -- user_lon_id
    zUserLon = new ZtatUnitInfoLon("user_lon_id", "userLon",  "user_lon","pname","user_lon");

//1  third_person  -- third_person_id
    zThirdPerson = new ZtatUnitInfoLon("third_person_id", "thirdPerson",  "third_person","pname","third_person");

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
        jsa.add(r.getLong("user_third_person_id") );
        jsa.add(r.getString("user_third_person_pkey") );
        jsa.add(r.getLong("user_lon_id"));
        jsa.add(r.getString("user_lon_pkey"));
        jsa.add(r.getString("user_lon_pname"));
        jsa.add(r.getLong("third_person_id"));
        jsa.add(r.getString("third_person_pkey"));
        jsa.add(r.getString("third_person_pname"));
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
                m_.put("userThirdPerson_id","Long");
            }
            
    //pkey       
            m_.put("userThirdPerson_pkey","String"); 
            
if(level<1){
                return m_;    
            }
            
 // userLon
if(withIds){
            m_.put("userLon_id","Long");   
                    
            }

        m_.put("userLon_pkey","String");   
        

            m_.put("userLon_pname","String");   
            
 // thirdPerson
if(withIds){
            m_.put("thirdPerson_id","Long");   
                    
            }

        m_.put("thirdPerson_pkey","String");   
        

            m_.put("thirdPerson_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"user_third_person_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"user_third_person_pkey", nc++); 
 // userLon
if(withIds){
                    lToCell(r, row,"user_lon_id", nc++);
                 }
sToCell(r, row,"user_lon_pkey", nc++);
sToCell(r, row,"user_lon_pname", nc++);
 // thirdPerson
if(withIds){
                    lToCell(r, row,"third_person_id", nc++);
                 }
sToCell(r, row,"third_person_pkey", nc++);
sToCell(r, row,"third_person_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE user_third_person_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final UserThirdPerson dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
            if(dc0.getUserLon()!=null){
               t.addLong(dc0.getUserLon().getId());
            }
   
            if(dc0.getThirdPerson()!=null){
               t.addLong(dc0.getThirdPerson().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final UserThirdPerson dc0, final Tuple t){
           
//      if(dc0.getUserLon()!=null){
//            t.addLong(dc0.getUserLon().getId());
//      }
   
//      if(dc0.getThirdPerson()!=null){
//            t.addLong(dc0.getThirdPerson().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("userLon_id",obj,t);

    fTLong("thirdPerson_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> userLon = mapParents.get("userLon");
            final Long userLon_id = userLon.get((String)obj.get("userLon_pkey"));
            obj.put("userLon_id", userLon_id);
      
            final Map<String, Long> thirdPerson = mapParents.get("thirdPerson");
            final Long thirdPerson_id = thirdPerson.get((String)obj.get("thirdPerson_pkey"));
            obj.put("thirdPerson_id", thirdPerson_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
     
    fTLong("userLon_id",js,t);
     
    fTLong("thirdPerson_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("userLon_id",js,t);

            //     fTLong("thirdPerson_id",js,t);
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
    public UserThirdPerson doFrom(final Row r){
        final UserThirdPerson userThirdPerson = new UserThirdPerson();
         userThirdPerson.setId(r.getLong("user_third_person_id"));
         
                userThirdPerson.setPkey(  r.getString("user_third_person_pkey"));

        final UserLon userLon = new UserLon();
        userLon.setId(r.getLong("user_lon_id"));
        userLon.setPkey(r.getString("user_lon_pkey"));
        userLon.setPname(r.getString("user_lon_pname"));
        userThirdPerson.setUserLon(userLon);
        

        final ThirdPerson thirdPerson = new ThirdPerson();
        thirdPerson.setId(r.getLong("third_person_id"));
        thirdPerson.setPkey(r.getString("third_person_pkey"));
        thirdPerson.setPname(r.getString("third_person_pname"));
        userThirdPerson.setThirdPerson(thirdPerson);
          
        return userThirdPerson;
    }
    
    @Override
    public UserThirdPerson doFromJson(final JsonObject js){
        UserThirdPerson userThirdPerson = new UserThirdPerson();
        userThirdPerson.setId(js.getLong("id"));
        
                userThirdPerson.setPkey(js.getString("pkey"));
        userThirdPerson.setId(js.getLong("userLon_id"));
        userThirdPerson.setId(js.getLong("thirdPerson_id"));
        return userThirdPerson;
    }

    @Override
    public JsonObject toJson(final UserThirdPerson o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final UserLon userLon = o.getUserLon();
            if(userLon!=null){
                jso.put("userLon_id", userLon.getId());
                jso.put("userLon_pkey", userLon.getPkey());
            }
            

            final ThirdPerson thirdPerson = o.getThirdPerson();
            if(thirdPerson!=null){
                jso.put("thirdPerson_id", thirdPerson.getId());
                jso.put("thirdPerson_pkey", thirdPerson.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "user_third_person_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "user_third_person_pkey");
    slcb.doEqInPSimple( "pkey", "user_third_person_pkey");
        
        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");  
//UserLon 4        
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");    
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");  
//ThirdPerson 1        
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");    

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"user_third_person");
        sz0.addField("COUNT(user_third_person.id) as c_user_third_person_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zUserLon);               
    sz0.applyG1(zThirdPerson);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        