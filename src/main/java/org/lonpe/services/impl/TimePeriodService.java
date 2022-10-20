
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
    
    private static String sql00 = "SELECT time_period.id as time_period_id,
time_period.pkey as time_period_pkey,
time_period.begin_date as time_period_begin_date,
time_period.end_date as time_period_end_date,
time_period.pname as time_period_pname,
time_period.type_lon as time_period_type_lon 
  FROM 
  time_period ; 
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

        
    dcModel.put("dc", "timePeriod");

//ID 
    names.add("id");

    sortMapFields.put("id","time_period_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("time_period.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("time_period.time_period_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "time_period_pkey");                   
 
    ps.add(pkey);
 
//beginDate
    names.add("beginDate");
    insertMapFields.put("time_period.begin_date","beginDate");  

//Create property beginDate       
    final JsonObject beginDate = ps00a("beginDate", "LocalDate",true);

    sortMapFields.put("beginDate", "time_period_begin_date");               
 
    ps.add(beginDate);
 
//endDate
    names.add("endDate");
    insertMapFields.put("time_period.end_date","endDate");  

//Create property endDate       
    final JsonObject endDate = ps00a("endDate", "LocalDate",true);

    sortMapFields.put("endDate", "time_period_end_date");               
 
    ps.add(endDate);
 
//pname
    names.add("pname");
    insertMapFields.put("time_period.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "time_period_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//typeLon
    names.add("typeLon");
    insertMapFields.put("time_period.type_lon","typeLon");  

//Create property typeLon       
    final JsonObject typeLon = ps00a("typeLon", "String",true);

    sortMapFields.put("typeLon", "time_period_type_lon");                   

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
    public void fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m_ = new LinkedHashMap<>();
    if(withIds){
                m_.put("timePeriod_id","Long");
            }
            
    //pkey       
            m_.put("timePeriod_pkey","String"); 
            
    //beginDate       
            m_.put("timePeriod_beginDate","LocalDate"); 
            
    //endDate       
            m_.put("timePeriod_endDate","LocalDate"); 
            
    //pname       
            m_.put("timePeriod_pname","String"); 
            
    //typeLon       
            m_.put("timePeriod_typeLon","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"time_period_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"time_period_pkey", nc++); 
    //beginDate            
            ldToCell(r, row,"time_period_begin_date", nc++); 
    //endDate            
            ldToCell(r, row,"time_period_end_date", nc++); 
    //pname       
            sToCell(r, row,"time_period_pname", nc++); 
    //typeLon       
            sToCell(r, row,"time_period_type_lon", nc++); 
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
    
        