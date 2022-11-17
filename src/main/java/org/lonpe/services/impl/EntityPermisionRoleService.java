
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
 *   EntityPermisionRoleService 
 * 
 */
  
public class EntityPermisionRoleService extends AbstractServiceLon<EntityPermisionRole>{

    private static final String SQLINSERT ="INSERT INTO entity_permision_role(pkey,enabled,nombre,permission,role_id) VALUES ($1,$2,$3,$4,$5) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE entity_permision_role SET enabled = $1,nombre = $2,permission = $3 WHERE id = $6 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE entity_permision_role SET enabled = $1,nombre = $2,permission = $3 WHERE pkey = $6 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM entity_permision_role_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM entity_permision_role_view";
    private static final String SQLKEYS = "SELECT entity_permision_role_pkey FROM entity_permision_role_view";
    private static final String SQLIDBYPKEY = "SELECT id from entity_permision_role WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from entity_permision_role WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM entity_permision_role WHERE id = $1 returning id";
    private static final String TABLENAME ="entity_permision_role";
    

    public EntityPermisionRoleService() {
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
    private static String sql00 = "SELECT entity_permision_role.id as entity_permision_role_id,
entity_permision_role.pkey as entity_permision_role_pkey,
entity_permision_role.enabled as entity_permision_role_enabled,
entity_permision_role.nombre as entity_permision_role_nombre,
entity_permision_role.permission as entity_permision_role_permission,
role.id as role_id,role.pkey as role_pkey,role.pname as role_pname 
  FROM 
  entity_permision_role,
  role as role  
 WHERE 
 entity_permision_role.role_id = role.id"
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
        
    dcModel.put("dc", "entityPermisionRole");

//ID 
    names.add("id");

    sortMapFields.put("id","entity_permision_role_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","entity_permision_role");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("entity_permision_role.entity_permision_role_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//enabled
    doFieldSort("enabled","enabled","entity_permision_role");               

//Create property enabled       
    final JsonObject enabled = psBoolean("enabled",true);
 
    ps.add(enabled);
 
//nombre
    doFieldSort("nombre","nombre","entity_permision_role");               

//Create property nombre       
    final JsonObject nombre = psString("nombre",true);
 
    ps.add(nombre);
 
//permission
    doFieldSort("permission","permission","entity_permision_role");               

//Create property permission       
    final JsonObject permission = psString("permission",true);

    final JsonArray permissionInList = new JsonArray();
                permissionInList.add("BROWSE"); 
permissionInList.add("LIST"); 
permissionInList.add("SAVE"); 
permissionInList.add("UPDATE"); 
permissionInList.add("DEL"); 
permissionInList.add("LZTAT"); 
permissionInList.add("EXXSLX"); 
permissionInList.add("IMPORTEXCEL"); 
permissionInList.add("TEMPLATEEXCEL"); 
    permission.put("inList",permissionInList );                
 
    ps.add(permission);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  role
    doFieldMT0("entity_permision_role","role", "role");  

    final JsonObject role =  doMto("role","role");        
   
    names.add("role_pname");
    sortMapFields.put( "role_pname", "role_pname");                
    role.put("pc","pname");          

    mto.add(role);
        

    //1  role  -- role_id
    final ZtatUnitInfoLon zRole = new ZtatUnitInfoLon("role_id", "role",  "role","pname","role");
    mz1.put("zRole", zRole);    

    dcModel.put("mto",mto);     
        
        
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
        jsa.add(r.getLong("entity_permision_role_id") );       
        jsa.add(r.getString("entity_permision_role_pkey") );       
        jsa.add(r.getBoolean("entity_permision_role_enabled") );       
        jsa.add(r.getString("entity_permision_role_nombre") );       
        jsa.add(r.getString("entity_permision_role_permission") );
    jsa.add(r.getLong("role_id"));
    jsa.add(r.getString("role_pkey"));   
    
        
    jsa.add(r.getString("role_pname"));
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
        m.put("entityPermisionRole_id",LONG);
    }        
//pkey    
    m.put("entityPermisionRole_pkey",STRING);              
//enabled    
    m.put("entityPermisionRole_enabled",BOOLEAN);              
//nombre    
    m.put("entityPermisionRole_nombre",STRING);              
//permission    
    m.put("entityPermisionRole_permission",STRING);          
    if(level<1){
        return m;    
    }       
// role   role
    if(withIds){
        m.put("role_id",LONG);                       
    }
    m.put("role_pkey",STRING);     
    m.put("role_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"entity_permision_role_id", nc++); 
    }            //pkey       
            sToCell(r, row,"entity_permision_role_pkey", nc++);     //enabled     
                bToCell(r, row,"entity_permision_role_enabled", nc++);     //nombre       
            sToCell(r, row,"entity_permision_role_nombre", nc++);     //permission       
            sToCell(r, row,"entity_permision_role_permission", nc++); 
//role   role        
    if(withIds){
        lToCell(r, row,"role_id", nc++);
    }
    sToCell(r, row,"role_pkey", nc++);
    sToCell(r, row,"role_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE entity_permision_role_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final EntityPermisionRole dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addBoolean(dc0.getEnabled());        
    t.addString(dc0.getNombre());        
    t.addString(dc0.getPermission());   
    if(dc0.getRole()!=null){
       t.addLong(dc0.getRole().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final EntityPermisionRole dc0, final Tuple t){
        
    t.addBoolean(dc0.getEnabled());
    t.addString(dc0.getNombre());
    t.addString(dc0.getPermission());   
//      if(dc0.getRole()!=null){
//            t.addLong(dc0.getRole().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTBoolean("enabled", obj, t);

    fTString("nombre", obj, t);

    fTString("permission", obj, t);

    fTLong("role_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> role = mapParents.get("role");
            final Long role_id = role.get((String)obj.get("role_pkey"));
            obj.put("role_id", role_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTBoolean("enabled", js, t);
    fTString("nombre", js, t);
    fTString("permission", js, t);     
    fTLong("role_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTBoolean("enabled", js, t);
fTString("nombre", js, t);
fTString("permission", js, t);

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
    public EntityPermisionRole doFrom(final Row r){
        final EntityPermisionRole entityPermisionRole = new EntityPermisionRole();
         entityPermisionRole.setId(r.getLong("entity_permision_role_id"));         
                entityPermisionRole.setPkey(  r.getString("entity_permision_role_pkey"));                       
                entityPermisionRole.setEnabled(  r.getBoolean("entity_permision_role_enabled"));                       
                entityPermisionRole.setNombre(  r.getString("entity_permision_role_nombre"));                       
                entityPermisionRole.setPermission(  r.getString("entity_permision_role_permission"));              
        final Role role = new Role();
        role.setId(r.getLong("role_id"));
        role.setPkey(r.getString("role_pkey"));
        
        role.setPname(r.getString("role_pname"));
        entityPermisionRole.setRole(role);
          
        return entityPermisionRole;
    }
    
    @Override
    public EntityPermisionRole doFromJson(final JsonObject js){
        EntityPermisionRole entityPermisionRole = new EntityPermisionRole();
        entityPermisionRole.setId(js.getLong("id"));
        
                
                entityPermisionRole.setPkey(js.getString("pkey"));        
                entityPermisionRole.setEnabled(js.getBoolean("enabled"));        
                entityPermisionRole.setNombre(js.getString("nombre"));        
                entityPermisionRole.setPermission(js.getString("permission"));        
            entityPermisionRole.setId(js.getLong("role_id"));
        return entityPermisionRole;
    }

    @Override
    public JsonObject toJson(final EntityPermisionRole o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("enabled",  o.getEnabled() );
        jso.put("nombre",  o.getNombre() );
        jso.put("permission",  o.getPermission() );

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
       slcb.doInLongCondition("id", "entity_permision_role_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "entity_permision_role_pkey");
    slcb.doEqInPSimple( "pkey", "entity_permision_role_pkey");
    slcb.doEqInPSimple( "permission", "entity_permision_role_permission");                    
        
        slcb.doIlPSimple2( "role_pkey", "role_pkey");
        slcb.doEQPSimple2( "role_pkey", "role_pkey");
        slcb.doInLongCondition("role_id", "role_id");  
//Role 1        --
        slcb.doIlPSimple2( "role_pname", "role_pname");    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"entity_permision_role");
        sz0.addField("COUNT(entity_permision_role.id) as c_entity_permision_role_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zRole"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        