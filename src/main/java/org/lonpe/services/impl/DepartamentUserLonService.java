
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
 *   DepartamentUserLonService 
 * 
 */
  
public class DepartamentUserLonService extends AbstractServiceLon<DepartamentUserLon>{

    private static final String SQLINSERT ="INSERT INTO departament_user_lon(pkey,departament_id,user_lon_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE departament_user_lon SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE departament_user_lon SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM departament_user_lon_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM departament_user_lon_view";
    private static final String SQLKEYS = "SELECT departament_user_lon_pkey FROM departament_user_lon_view";
    private static final String SQLIDBYPKEY = "SELECT id from departament_user_lon WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from departament_user_lon WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM departament_user_lon WHERE id = $1 returning id";
    private static final String TABLENAME ="departament_user_lon";
    

    public DepartamentUserLonService() {
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
    private static String sql00 = "SELECT departament_user_lon.id as departament_user_lon_id,
departament_user_lon.pkey as departament_user_lon_pkey,
departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname 
  FROM 
  departament_user_lon,
  departament as departament,
  user_lon as user_lon  
 WHERE 
 departament_user_lon.departament_id = departament.id
 AND departament_user_lon.user_lon_id = user_lon.id"
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
        
    dcModel.put("dc", "departamentUserLon");

//ID 
    names.add("id");

    sortMapFields.put("id","departament_user_lon_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","departament_user_lon");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("departament_user_lon.departament_user_lon_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  departament
    doFieldMT0("departament_user_lon","departament", "departament");  

    final JsonObject departament =  doMto("departament","departament");        
   
    names.add("departament_pname");
    sortMapFields.put( "departament_pname", "departament_pname");                
    departament.put("pc","pname");          

    mto.add(departament);
        

    //1  departament  -- departament_id
    final ZtatUnitInfoLon zDepartament = new ZtatUnitInfoLon("departament_id", "departament",  "departament","pname","departament");
    mz1.put("zDepartament", zDepartament);    

//(1)  userLon
    doFieldMT0("departament_user_lon","userLon", "user_lon");  

    final JsonObject userLon =  doMto("userLon","userLon");        
   
    names.add("userLon_pname");
    sortMapFields.put( "userLon_pname", "user_lon_pname");                
    userLon.put("pc","pname");          

    mto.add(userLon);
        

    //1  user_lon  -- user_lon_id
    final ZtatUnitInfoLon zUserLon = new ZtatUnitInfoLon("user_lon_id", "userLon",  "user_lon","pname","user_lon");
    mz1.put("zUserLon", zUserLon);    

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
        jsa.add(r.getLong("departament_user_lon_id") );       
        jsa.add(r.getString("departament_user_lon_pkey") );
    jsa.add(r.getLong("departament_id"));
    jsa.add(r.getString("departament_pkey"));   
    
        
    jsa.add(r.getString("departament_pname"));
    jsa.add(r.getLong("user_lon_id"));
    jsa.add(r.getString("user_lon_pkey"));   
    
        
    jsa.add(r.getString("user_lon_pname"));
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
        m.put("departamentUserLon_id",LONG);
    }        
//pkey    
    m.put("departamentUserLon_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// departament   departament
    if(withIds){
        m.put("departament_id",LONG);                       
    }
    m.put("departament_pkey",STRING);     
    m.put("departament_pname",STRING);   
// userLon   userLon
    if(withIds){
        m.put("userLon_id",LONG);                       
    }
    m.put("userLon_pkey",STRING);     
    m.put("userLon_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"departament_user_lon_id", nc++); 
    }            //pkey       
            sToCell(r, row,"departament_user_lon_pkey", nc++); 
//departament   departament        
    if(withIds){
        lToCell(r, row,"departament_id", nc++);
    }
    sToCell(r, row,"departament_pkey", nc++);
    sToCell(r, row,"departament_pname", nc++);
//userLon   user_lon        
    if(withIds){
        lToCell(r, row,"user_lon_id", nc++);
    }
    sToCell(r, row,"user_lon_pkey", nc++);
    sToCell(r, row,"user_lon_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE departament_user_lon_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final DepartamentUserLon dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getDepartament()!=null){
       t.addLong(dc0.getDepartament().getId());
    }   
    if(dc0.getUserLon()!=null){
       t.addLong(dc0.getUserLon().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final DepartamentUserLon dc0, final Tuple t){
           
//      if(dc0.getDepartament()!=null){
//            t.addLong(dc0.getDepartament().getId());
//      }   
//      if(dc0.getUserLon()!=null){
//            t.addLong(dc0.getUserLon().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("departament_id",obj,t);

    fTLong("userLon_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> departament = mapParents.get("departament");
            final Long departament_id = departament.get((String)obj.get("departament_pkey"));
            obj.put("departament_id", departament_id);
      
            final Map<String, Long> userLon = mapParents.get("userLon");
            final Long userLon_id = userLon.get((String)obj.get("userLon_pkey"));
            obj.put("userLon_id", userLon_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("departament_id",js,t);     
    fTLong("userLon_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("departament_id",js,t);

            //     fTLong("userLon_id",js,t);
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
    public DepartamentUserLon doFrom(final Row r){
        final DepartamentUserLon departamentUserLon = new DepartamentUserLon();
         departamentUserLon.setId(r.getLong("departament_user_lon_id"));         
                departamentUserLon.setPkey(  r.getString("departament_user_lon_pkey"));              
        final Departament departament = new Departament();
        departament.setId(r.getLong("departament_id"));
        departament.setPkey(r.getString("departament_pkey"));
        
        departament.setPname(r.getString("departament_pname"));
        departamentUserLon.setDepartament(departament);
        
        final UserLon userLon = new UserLon();
        userLon.setId(r.getLong("user_lon_id"));
        userLon.setPkey(r.getString("user_lon_pkey"));
        
        userLon.setPname(r.getString("user_lon_pname"));
        departamentUserLon.setUserLon(userLon);
          
        return departamentUserLon;
    }
    
    @Override
    public DepartamentUserLon doFromJson(final JsonObject js){
        DepartamentUserLon departamentUserLon = new DepartamentUserLon();
        departamentUserLon.setId(js.getLong("id"));
        
                
                departamentUserLon.setPkey(js.getString("pkey"));        
            departamentUserLon.setId(js.getLong("departament_id"));        
            departamentUserLon.setId(js.getLong("userLon_id"));
        return departamentUserLon;
    }

    @Override
    public JsonObject toJson(final DepartamentUserLon o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final Departament departament = o.getDepartament();
            if(departament!=null){
                jso.put("departament_id", departament.getId());
                jso.put("departament_pkey", departament.getPkey());
            }
            

            final UserLon userLon = o.getUserLon();
            if(userLon!=null){
                jso.put("userLon_id", userLon.getId());
                jso.put("userLon_pkey", userLon.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "departament_user_lon_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "departament_user_lon_pkey");
    slcb.doEqInPSimple( "pkey", "departament_user_lon_pkey");
        
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");  
//Departament 3        --
        slcb.doIlPSimple2( "departament_pname", "departament_pname");    
        
        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");  
//UserLon 4        --
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"departament_user_lon");
        sz0.addField("COUNT(departament_user_lon.id) as c_departament_user_lon_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zDepartament"))   ;               
    sz0.applyG1(mz1.get("zUserLon"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        