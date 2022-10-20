
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
 *   RoleService 
 * 
 */
   
  
public class RoleService extends AbstractServiceLon<Role>{

    private static final String SQLINSERT ="INSERT INTO role(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE role SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE role SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM role_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM role_view";
    private static final String SQLKEYS = "SELECT role_pkey FROM role_view";
    private static final String SQLIDBYPKEY = "SELECT id from role WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from role WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM role WHERE id = $1 returning id";
    private static final String TABLENAME ="role";
    
    
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
    
    private static String sql00 = "SELECT role.id as role_id,
role.pkey as role_pkey,
role.pname as role_pname 
  FROM 
  role ; 
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

        
    dcModel.put("dc", "role");

//ID 
    names.add("id");

    sortMapFields.put("id","role_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("role.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("role.role_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "role_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("role.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "role_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"userRoles","userRole"); 
                

        applyOtm(otm,"entityPermisionRoles","entityPermisionRole"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

        

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
        jsa.add(r.getLong("role_id") );
        jsa.add(r.getString("role_pkey") );
        jsa.add(r.getString("role_pname") );
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
                m_.put("role_id","Long");
            }
            
    //pkey       
            m_.put("role_pkey","String"); 
            
    //pname       
            m_.put("role_pname","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"role_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"role_pkey", nc++); 
    //pname       
            sToCell(r, row,"role_pname", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE role_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Role dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final Role dc0, final Tuple t){
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
    public Role doFrom(final Row r){
        final Role role = new Role();
         role.setId(r.getLong("role_id"));
         
                role.setPkey(  r.getString("role_pkey"));
         
                role.setPname(  r.getString("role_pname"));  
        return role;
    }
    
    @Override
    public Role doFromJson(final JsonObject js){
        Role role = new Role();
        role.setId(js.getLong("id"));
        
                role.setPkey(js.getString("pkey"));
        role.setPname(js.getString("pname"));
        return role;
    }

    @Override
    public JsonObject toJson(final Role o) {        
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
       slcb.doInLongCondition("id", "role_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "role_pkey");
    slcb.doEqInPSimple( "pkey", "role_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "role_pname");
    slcb.doEqInPSimple( "pname", "role_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"role");
        sz0.addField("COUNT(role.id) as c_role_id").addName("count");
        
        
        return sz0;
    }
}
    
        