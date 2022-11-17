
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







/**
 *   AirportService 
 * 
 */
  
public class AirportService extends AbstractServiceLon<Airport>{

    private static final String SQLINSERT ="INSERT INTO airport(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE airport SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE airport SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM airport_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM airport_view";
    private static final String SQLKEYS = "SELECT airport_pkey FROM airport_view";
    private static final String SQLIDBYPKEY = "SELECT id from airport WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from airport WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM airport WHERE id = $1 returning id";
    private static final String TABLENAME ="airport";
    

    public AirportService() {
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
    private static String sql00 = "SELECT airport.id as airport_id,
airport.pkey as airport_pkey,
airport.pname as airport_pname 
  FROM 
  airport "
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
        
    dcModel.put("dc", "airport");

//ID 
    names.add("id");

    sortMapFields.put("id","airport_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","airport");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("airport.airport_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","airport");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

//ON RELATION toAirport    
            applyOtm(otm,"inCommingFligths","fligth","toAirport"); 
                

//ON RELATION fromAirport    
            applyOtm(otm,"outCommingFligths","fligth","fromAirport"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"fligthInstances","fligthInstance","inCommingFligths","theFligth","toAirport"); 
        

        applyOtm2(otm2,"fligthInstances","fligthInstance","outCommingFligths","theFligth","fromAirport"); 
        

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
        jsa.add(r.getLong("airport_id") );       
        jsa.add(r.getString("airport_pkey") );       
        jsa.add(r.getString("airport_pname") );
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
        m.put("airport_id",LONG);
    }        
//pkey    
    m.put("airport_pkey",STRING);              
//pname    
    m.put("airport_pname",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"airport_id", nc++); 
    }            //pkey       
            sToCell(r, row,"airport_pkey", nc++);     //pname       
            sToCell(r, row,"airport_pname", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE airport_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Airport dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final Airport dc0, final Tuple t){
        
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
    public Airport doFrom(final Row r){
        final Airport airport = new Airport();
         airport.setId(r.getLong("airport_id"));         
                airport.setPkey(  r.getString("airport_pkey"));                       
                airport.setPname(  r.getString("airport_pname"));                
        return airport;
    }
    
    @Override
    public Airport doFromJson(final JsonObject js){
        Airport airport = new Airport();
        airport.setId(js.getLong("id"));
        
                
                airport.setPkey(js.getString("pkey"));        
                airport.setPname(js.getString("pname"));
        return airport;
    }

    @Override
    public JsonObject toJson(final Airport o) {        
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
       slcb.doInLongCondition("id", "airport_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "airport_pkey");
    slcb.doEqInPSimple( "pkey", "airport_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "airport_pname");
    slcb.doEqInPSimple( "pname", "airport_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"airport");
        sz0.addField("COUNT(airport.id) as c_airport_id").addName("count");
        
        
        return sz0;
    }
}
    
        