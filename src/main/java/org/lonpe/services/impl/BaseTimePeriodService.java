
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
 *   BaseTimePeriodService 
 * 
 */
  
public class BaseTimePeriodService extends AbstractServiceLon<BaseTimePeriod>{

    private static final String SQLINSERT ="INSERT INTO base_time_period(pkey,base_id,time_period_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE base_time_period SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE base_time_period SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM base_time_period_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM base_time_period_view";
    private static final String SQLKEYS = "SELECT base_time_period_pkey FROM base_time_period_view";
    private static final String SQLIDBYPKEY = "SELECT id from base_time_period WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from base_time_period WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM base_time_period WHERE id = $1 returning id";
    private static final String TABLENAME ="base_time_period";
    

    public BaseTimePeriodService() {
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
    private static String sql00 = "SELECT base_time_period.id as base_time_period_id,
base_time_period.pkey as base_time_period_pkey,
base.id as base_id,base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id,time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  base_time_period,
  base as base,
  time_period as time_period  
 WHERE 
 base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id"
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
        
    dcModel.put("dc", "baseTimePeriod");

//ID 
    names.add("id");

    sortMapFields.put("id","base_time_period_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","base_time_period");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("base_time_period.base_time_period_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  base
    doFieldMT0("base_time_period","base", "base");  

    final JsonObject base =  doMto("base","base");        
   
    names.add("base_pname");
    sortMapFields.put( "base_pname", "base_pname");                
    base.put("pc","pname");          

    mto.add(base);
        

    //1  base  -- base_id
    final ZtatUnitInfoLon zBase = new ZtatUnitInfoLon("base_id", "base",  "base","pname","base");
    mz1.put("zBase", zBase);    

//(1)  timePeriod
    doFieldMT0("base_time_period","timePeriod", "time_period");  

    final JsonObject timePeriod =  doMto("timePeriod","timePeriod");        
   
    names.add("timePeriod_pname");
    sortMapFields.put( "timePeriod_pname", "time_period_pname");                
    timePeriod.put("pc","pname");          

    mto.add(timePeriod);
        

    //1  time_period  -- time_period_id
    final ZtatUnitInfoLon zTimePeriod = new ZtatUnitInfoLon("time_period_id", "timePeriod",  "time_period","pname","time_period");
    mz1.put("zTimePeriod", zTimePeriod);    

    dcModel.put("mto",mto);     
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"departamentBaseTimePeriods","departamentBaseTimePeriod"); 
                

        applyOtm(otm,"programBaseTimePeriods","programBaseTimePeriod"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"contracts","contractOut","departamentBaseTimePeriods",null,null); 
        

        applyOtm2(otm2,"departamentJobInstances","departamentJobInstance","departamentBaseTimePeriods",null,null); 
        

        applyOtm2(otm2,"contracts","contractIn","programBaseTimePeriods",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"appointments","appointment","contracts",null,null,null); 
        

        applyOtm3(otm3,"comercialDocuments","comercialDocumentOut","contracts","contract",null,null); 
        

        applyOtm3(otm3,"appointmens","appointment","departamentJobInstances",null,null,null); 
        

        applyOtm3(otm3,"comercialDocuments","comercialDocumentIn","contracts","contract",null,null); 
        

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
        jsa.add(r.getLong("base_time_period_id") );       
        jsa.add(r.getString("base_time_period_pkey") );
    jsa.add(r.getLong("base_id"));
    jsa.add(r.getString("base_pkey"));   
    
        
    jsa.add(r.getString("base_pname"));
    jsa.add(r.getLong("time_period_id"));
    jsa.add(r.getString("time_period_pkey"));   
    
        
    jsa.add(r.getString("time_period_pname"));
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
        m.put("baseTimePeriod_id",LONG);
    }        
//pkey    
    m.put("baseTimePeriod_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// base   base
    if(withIds){
        m.put("base_id",LONG);                       
    }
    m.put("base_pkey",STRING);     
    m.put("base_pname",STRING);   
// timePeriod   timePeriod
    if(withIds){
        m.put("timePeriod_id",LONG);                       
    }
    m.put("timePeriod_pkey",STRING);     
    m.put("timePeriod_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"base_time_period_id", nc++); 
    }            //pkey       
            sToCell(r, row,"base_time_period_pkey", nc++); 
//base   base        
    if(withIds){
        lToCell(r, row,"base_id", nc++);
    }
    sToCell(r, row,"base_pkey", nc++);
    sToCell(r, row,"base_pname", nc++);
//timePeriod   time_period        
    if(withIds){
        lToCell(r, row,"time_period_id", nc++);
    }
    sToCell(r, row,"time_period_pkey", nc++);
    sToCell(r, row,"time_period_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE base_time_period_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final BaseTimePeriod dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getBase()!=null){
       t.addLong(dc0.getBase().getId());
    }   
    if(dc0.getTimePeriod()!=null){
       t.addLong(dc0.getTimePeriod().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final BaseTimePeriod dc0, final Tuple t){
           
//      if(dc0.getBase()!=null){
//            t.addLong(dc0.getBase().getId());
//      }   
//      if(dc0.getTimePeriod()!=null){
//            t.addLong(dc0.getTimePeriod().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("base_id",obj,t);

    fTLong("timePeriod_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> base = mapParents.get("base");
            final Long base_id = base.get((String)obj.get("base_pkey"));
            obj.put("base_id", base_id);
      
            final Map<String, Long> timePeriod = mapParents.get("timePeriod");
            final Long timePeriod_id = timePeriod.get((String)obj.get("timePeriod_pkey"));
            obj.put("timePeriod_id", timePeriod_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("base_id",js,t);     
    fTLong("timePeriod_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("base_id",js,t);

            //     fTLong("timePeriod_id",js,t);
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
    public BaseTimePeriod doFrom(final Row r){
        final BaseTimePeriod baseTimePeriod = new BaseTimePeriod();
         baseTimePeriod.setId(r.getLong("base_time_period_id"));         
                baseTimePeriod.setPkey(  r.getString("base_time_period_pkey"));              
        final Base base = new Base();
        base.setId(r.getLong("base_id"));
        base.setPkey(r.getString("base_pkey"));
        
        base.setPname(r.getString("base_pname"));
        baseTimePeriod.setBase(base);
        
        final TimePeriod timePeriod = new TimePeriod();
        timePeriod.setId(r.getLong("time_period_id"));
        timePeriod.setPkey(r.getString("time_period_pkey"));
        
        timePeriod.setPname(r.getString("time_period_pname"));
        baseTimePeriod.setTimePeriod(timePeriod);
          
        return baseTimePeriod;
    }
    
    @Override
    public BaseTimePeriod doFromJson(final JsonObject js){
        BaseTimePeriod baseTimePeriod = new BaseTimePeriod();
        baseTimePeriod.setId(js.getLong("id"));
        
                
                baseTimePeriod.setPkey(js.getString("pkey"));        
            baseTimePeriod.setId(js.getLong("base_id"));        
            baseTimePeriod.setId(js.getLong("timePeriod_id"));
        return baseTimePeriod;
    }

    @Override
    public JsonObject toJson(final BaseTimePeriod o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final Base base = o.getBase();
            if(base!=null){
                jso.put("base_id", base.getId());
                jso.put("base_pkey", base.getPkey());
            }
            

            final TimePeriod timePeriod = o.getTimePeriod();
            if(timePeriod!=null){
                jso.put("timePeriod_id", timePeriod.getId());
                jso.put("timePeriod_pkey", timePeriod.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "base_time_period_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "base_time_period_pkey");
    slcb.doEqInPSimple( "pkey", "base_time_period_pkey");
        
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");  
//Base 1        --
        slcb.doIlPSimple2( "base_pname", "base_pname");    
        
        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");  
//TimePeriod 3        --
        slcb.doIlPSimple2( "timePeriod_pname", "time_period_pname");    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"base_time_period");
        sz0.addField("COUNT(base_time_period.id) as c_base_time_period_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zBase"))   ;               
    sz0.applyG1(mz1.get("zTimePeriod"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        