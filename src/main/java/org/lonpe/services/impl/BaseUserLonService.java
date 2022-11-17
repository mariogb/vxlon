
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
 *   BaseUserLonService 
 * 
 */
  
public class BaseUserLonService extends AbstractServiceLon<BaseUserLon>{

    private static final String SQLINSERT ="INSERT INTO base_user_lon(pkey,base_id,user_lon_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE base_user_lon SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE base_user_lon SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM base_user_lon_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM base_user_lon_view";
    private static final String SQLKEYS = "SELECT base_user_lon_pkey FROM base_user_lon_view";
    private static final String SQLIDBYPKEY = "SELECT id from base_user_lon WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from base_user_lon WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM base_user_lon WHERE id = $1 returning id";
    private static final String TABLENAME ="base_user_lon";
    

    public BaseUserLonService() {
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
    private static String sql00 = "SELECT base_user_lon.id as base_user_lon_id,
base_user_lon.pkey as base_user_lon_pkey,
base.id as base_id,base.pkey as base_pkey,base.pname as base_pname,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname 
  FROM 
  base_user_lon,
  base as base,
  user_lon as user_lon  
 WHERE 
 base_user_lon.base_id = base.id
 AND base_user_lon.user_lon_id = user_lon.id"
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
        
    dcModel.put("dc", "baseUserLon");

//ID 
    names.add("id");

    sortMapFields.put("id","base_user_lon_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","base_user_lon");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("base_user_lon.base_user_lon_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  base
    doFieldMT0("base_user_lon","base", "base");  

    final JsonObject base =  doMto("base","base");        
   
    names.add("base_pname");
    sortMapFields.put( "base_pname", "base_pname");                
    base.put("pc","pname");          

    mto.add(base);
        

    //1  base  -- base_id
    final ZtatUnitInfoLon zBase = new ZtatUnitInfoLon("base_id", "base",  "base","pname","base");
    mz1.put("zBase", zBase);    

//(1)  userLon
    doFieldMT0("base_user_lon","userLon", "user_lon");  

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
        jsa.add(r.getLong("base_user_lon_id") );       
        jsa.add(r.getString("base_user_lon_pkey") );
    jsa.add(r.getLong("base_id"));
    jsa.add(r.getString("base_pkey"));   
    
        
    jsa.add(r.getString("base_pname"));
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
        m.put("baseUserLon_id",LONG);
    }        
//pkey    
    m.put("baseUserLon_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// base   base
    if(withIds){
        m.put("base_id",LONG);                       
    }
    m.put("base_pkey",STRING);     
    m.put("base_pname",STRING);   
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
        lToCell(r, row,"base_user_lon_id", nc++); 
    }            //pkey       
            sToCell(r, row,"base_user_lon_pkey", nc++); 
//base   base        
    if(withIds){
        lToCell(r, row,"base_id", nc++);
    }
    sToCell(r, row,"base_pkey", nc++);
    sToCell(r, row,"base_pname", nc++);
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
        return SQLVIEW+ " WHERE base_user_lon_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final BaseUserLon dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getBase()!=null){
       t.addLong(dc0.getBase().getId());
    }   
    if(dc0.getUserLon()!=null){
       t.addLong(dc0.getUserLon().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final BaseUserLon dc0, final Tuple t){
           
//      if(dc0.getBase()!=null){
//            t.addLong(dc0.getBase().getId());
//      }   
//      if(dc0.getUserLon()!=null){
//            t.addLong(dc0.getUserLon().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("base_id",obj,t);

    fTLong("userLon_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> base = mapParents.get("base");
            final Long base_id = base.get((String)obj.get("base_pkey"));
            obj.put("base_id", base_id);
      
            final Map<String, Long> userLon = mapParents.get("userLon");
            final Long userLon_id = userLon.get((String)obj.get("userLon_pkey"));
            obj.put("userLon_id", userLon_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("base_id",js,t);     
    fTLong("userLon_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("base_id",js,t);

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
    public BaseUserLon doFrom(final Row r){
        final BaseUserLon baseUserLon = new BaseUserLon();
         baseUserLon.setId(r.getLong("base_user_lon_id"));         
                baseUserLon.setPkey(  r.getString("base_user_lon_pkey"));              
        final Base base = new Base();
        base.setId(r.getLong("base_id"));
        base.setPkey(r.getString("base_pkey"));
        
        base.setPname(r.getString("base_pname"));
        baseUserLon.setBase(base);
        
        final UserLon userLon = new UserLon();
        userLon.setId(r.getLong("user_lon_id"));
        userLon.setPkey(r.getString("user_lon_pkey"));
        
        userLon.setPname(r.getString("user_lon_pname"));
        baseUserLon.setUserLon(userLon);
          
        return baseUserLon;
    }
    
    @Override
    public BaseUserLon doFromJson(final JsonObject js){
        BaseUserLon baseUserLon = new BaseUserLon();
        baseUserLon.setId(js.getLong("id"));
        
                
                baseUserLon.setPkey(js.getString("pkey"));        
            baseUserLon.setId(js.getLong("base_id"));        
            baseUserLon.setId(js.getLong("userLon_id"));
        return baseUserLon;
    }

    @Override
    public JsonObject toJson(final BaseUserLon o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final Base base = o.getBase();
            if(base!=null){
                jso.put("base_id", base.getId());
                jso.put("base_pkey", base.getPkey());
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
       slcb.doInLongCondition("id", "base_user_lon_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "base_user_lon_pkey");
    slcb.doEqInPSimple( "pkey", "base_user_lon_pkey");
        
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");  
//Base 1        --
        slcb.doIlPSimple2( "base_pname", "base_pname");    
        
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
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"base_user_lon");
        sz0.addField("COUNT(base_user_lon.id) as c_base_user_lon_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zBase"))   ;               
    sz0.applyG1(mz1.get("zUserLon"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        