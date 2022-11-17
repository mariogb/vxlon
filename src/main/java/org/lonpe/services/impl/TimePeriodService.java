
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
import java.time.LocalDate;






/**
 *   TimePeriodService 
 * 
 */
  
public class TimePeriodService extends AbstractServiceLon<TimePeriod>{

    private static final String SQLINSERT ="INSERT INTO time_period(pkey,begin_date,end_date,pname,type_lon) VALUES ($1,$2,$3,$4,$5) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE time_period SET begin_date = $1,end_date = $2,pname = $3,type_lon = $4 WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE time_period SET begin_date = $1,end_date = $2,pname = $3,type_lon = $4 WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM time_period_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM time_period_view";
    private static final String SQLKEYS = "SELECT time_period_pkey FROM time_period_view";
    private static final String SQLIDBYPKEY = "SELECT id from time_period WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from time_period WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM time_period WHERE id = $1 returning id";
    private static final String TABLENAME ="time_period";
    

    public TimePeriodService() {
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
    private static String sql00 = "SELECT time_period.id as time_period_id,
time_period.pkey as time_period_pkey,
time_period.begin_date as time_period_begin_date,
time_period.end_date as time_period_end_date,
time_period.pname as time_period_pname,
time_period.type_lon as time_period_type_lon 
  FROM 
  time_period "
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
        
    dcModel.put("dc", "timePeriod");

//ID 
    names.add("id");

    sortMapFields.put("id","time_period_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","time_period");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("time_period.time_period_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//beginDate
    doFieldSort("beginDate","begin_date","time_period");               

//Create property beginDate       
    final JsonObject beginDate = psLocalDate("beginDate",true);
 
    ps.add(beginDate);
 
//endDate
    doFieldSort("endDate","end_date","time_period");               

//Create property endDate       
    final JsonObject endDate = psLocalDate("endDate",true);
 
    ps.add(endDate);
 
//pname
    doFieldSort("pname","pname","time_period");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//typeLon
    doFieldSort("typeLon","type_lon","time_period");               

//Create property typeLon       
    final JsonObject typeLon = psString("typeLon",true);

    final JsonArray typeLonInList = new JsonArray();
                typeLonInList.add("CONFIG"); 
typeLonInList.add("OPERATION"); 
typeLonInList.add("FINISH"); 
    typeLon.put("inList",typeLonInList );                
 
    ps.add(typeLon);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"baseTimePeriods","baseTimePeriod"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"departamentBaseTimePeriods","departamentBaseTimePeriod","baseTimePeriods",null,null); 
        

        applyOtm2(otm2,"programBaseTimePeriods","programBaseTimePeriod","baseTimePeriods",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"contracts","contractOut","departamentBaseTimePeriods",null,null,null); 
        

        applyOtm3(otm3,"departamentJobInstances","departamentJobInstance","departamentBaseTimePeriods",null,null,null); 
        

        applyOtm3(otm3,"contracts","contractIn","programBaseTimePeriods",null,null,null); 
        

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
        jsa.add(r.getLong("time_period_id") );       
        jsa.add(r.getString("time_period_pkey") );
        jsa.add(r.getLocalDate("time_period_begin_date").toString() ); // undefined
        jsa.add(r.getLocalDate("time_period_end_date").toString() ); // undefined       
        jsa.add(r.getString("time_period_pname") );       
        jsa.add(r.getString("time_period_type_lon") );
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
        m.put("timePeriod_id",LONG);
    }        
//pkey    
    m.put("timePeriod_pkey",STRING);              
//beginDate    
    m.put("timePeriod_beginDate",LOCALDATE);              
//endDate    
    m.put("timePeriod_endDate",LOCALDATE);              
//pname    
    m.put("timePeriod_pname",STRING);              
//typeLon    
    m.put("timePeriod_typeLon",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"time_period_id", nc++); 
    }            //pkey       
            sToCell(r, row,"time_period_pkey", nc++);     //beginDate            
            ldToCell(r, row,"time_period_begin_date", nc++);     //endDate            
            ldToCell(r, row,"time_period_end_date", nc++);     //pname       
            sToCell(r, row,"time_period_pname", nc++);     //typeLon       
            sToCell(r, row,"time_period_type_lon", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE time_period_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final TimePeriod dc0, final Tuple t){
                
    t.addString(dc0.getPkey());
    t.addLocalDate( dc0.getBeginDate()  );
    t.addLocalDate( dc0.getEndDate()  );        
    t.addString(dc0.getPname());        
    t.addString(dc0.getTypeLon());
    }

    @Override
    public void fillTupleUpdate(final TimePeriod dc0, final Tuple t){
                
    t.addLocalDate( dc0.getBeginDate()  );        
    t.addLocalDate( dc0.getEndDate()  );
    t.addString(dc0.getPname());
    t.addString(dc0.getTypeLon());
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);
  
    t.addLocalDate((LocalDate)obj.get("beginDate"));
  
    t.addLocalDate((LocalDate)obj.get("endDate"));

    fTString("pname", obj, t);

    fTString("typeLon", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    t.addLocalDate(LocalDate.parse(js.getString("beginDate")));
    t.addLocalDate(LocalDate.parse(js.getString("endDate")));
    fTString("pname", js, t);
    fTString("typeLon", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
                t.addLocalDate(LocalDate.parse(js.getString("beginDate")));
        t.addLocalDate(LocalDate.parse(js.getString("endDate")));
fTString("pname", js, t);
fTString("typeLon", js, t);
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
    public TimePeriod doFrom(final Row r){
        final TimePeriod timePeriod = new TimePeriod();
         timePeriod.setId(r.getLong("time_period_id"));         
                timePeriod.setPkey(  r.getString("time_period_pkey"));                       
                timePeriod.setBeginDate(r.getLocalDate("begin_date"));                       
                timePeriod.setEndDate(r.getLocalDate("end_date"));                       
                timePeriod.setPname(  r.getString("time_period_pname"));                       
                timePeriod.setTypeLon(  r.getString("time_period_type_lon"));                
        return timePeriod;
    }
    
    @Override
    public TimePeriod doFromJson(final JsonObject js){
        TimePeriod timePeriod = new TimePeriod();
        timePeriod.setId(js.getLong("id"));
        
                
                timePeriod.setPkey(js.getString("pkey"));        
                timePeriod.setBeginDate(LocalDate.parse(js.getString("beginDate")));        
                timePeriod.setEndDate(LocalDate.parse(js.getString("endDate")));        
                timePeriod.setPname(js.getString("pname"));        
                timePeriod.setTypeLon(js.getString("typeLon"));
        return timePeriod;
    }

    @Override
    public JsonObject toJson(final TimePeriod o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("beginDate", o.getBeginDate() );
        jso.put("endDate", o.getEndDate() );
        jso.put("pname",  o.getPname() );
        jso.put("typeLon",  o.getTypeLon() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "time_period_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "time_period_pkey");
    slcb.doEqInPSimple( "pkey", "time_period_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "time_period_pname");
    slcb.doEqInPSimple( "pname", "time_period_pname");               
    slcb.doGEPSimpleLocalDate( "beginDate", "time_period_begin_date");
    slcb.doLPSimpleLocalDate( "beginDate", "time_period_begin_date");                   
    slcb.doGEPSimpleLocalDate( "endDate", "time_period_end_date");
    slcb.doLPSimpleLocalDate( "endDate", "time_period_end_date");                
    slcb.doEqInPSimple( "typeLon", "time_period_type_lon");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"time_period");
        sz0.addField("COUNT(time_period.id) as c_time_period_id").addName("count");
        
        
        return sz0;
    }
}
    
        