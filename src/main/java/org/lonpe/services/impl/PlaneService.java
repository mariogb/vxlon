
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
 *   PlaneService 
 * 
 */
   
  
public class PlaneService extends AbstractServiceLon<Plane>{

    private static final String SQLINSERT ="INSERT INTO plane(pkey,plate,pname,seats,la_compania_id) VALUES ($1,$2,$3,$4,$5) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE plane SET plate = $1,pname = $2,seats = $3 WHERE id = $6 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE plane SET plate = $1,pname = $2,seats = $3 WHERE pkey = $6 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM plane_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM plane_view";
    private static final String SQLKEYS = "SELECT plane_pkey FROM plane_view";
    private static final String SQLIDBYPKEY = "SELECT id from plane WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from plane WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM plane WHERE id = $1 returning id";
    private static final String TABLENAME ="plane";
    
//1
    private static final ZtatUnitInfoLon zLaCompania;
    
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
    
    private static String sql00 = "SELECT plane.id as plane_id,
plane.pkey as plane_pkey,
plane.plate as plane_plate,
plane.pname as plane_pname,
plane.seats as plane_seats,
la_compania.id as la_compania_id,la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname 
  FROM 
  plane,
  air_company as la_compania  
 WHERE 
 plane.la_compania_id = la_compania.id; 
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

        
    dcModel.put("dc", "plane");

//ID 
    names.add("id");

    sortMapFields.put("id","plane_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("plane.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("plane.plane_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "plane_pkey");                   
 
    ps.add(pkey);
 
//plate
    names.add("plate");
    insertMapFields.put("plane.plate","plate");  

//Create property plate       
    final JsonObject plate = ps00a("plate", "String",true);

    sortMapFields.put("plate", "plane_plate");                   
 
    ps.add(plate);
 
//pname
    names.add("pname");
    insertMapFields.put("plane.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "plane_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//seats
    names.add("seats");
    insertMapFields.put("plane.seats","seats");  

//Create property seats       
    final JsonObject seats = ps00a("seats", "Integer",true);

    sortMapFields.put("seats", "plane_seats");               
 
    seats.put("min", 0); 
 
    ps.add(seats);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  laCompania --------------------
    names.add("laCompania_id");      
    insertMapFields.put("plane.la_compania_id","laCompania_id");    
       
    names.add("laCompania_pkey");        
    sortMapFields.put( "laCompania_pkey", "la_compania_pkey");        

    final JsonObject laCompania =  doMto("laCompania","airCompany");        
   
    names.add("laCompania_pname");
    sortMapFields.put( "laCompania_pname", "la_compania_pname");         

    laCompania.put("pc","pname");          

    mto.add(laCompania);
        

    dcModel.put("mto",mto);     
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"fligths","fligth"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"fligthInstances","fligthInstance","fligths","theFligth",null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

        
//1  la_compania  -- la_compania_id
    zLaCompania = new ZtatUnitInfoLon("la_compania_id", "laCompania",  "air_company","pname","la_compania");

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
        jsa.add(r.getLong("plane_id") );
        jsa.add(r.getString("plane_pkey") );
        jsa.add(r.getString("plane_plate") );
        jsa.add(r.getString("plane_pname") );
        jsa.add(r.getInteger("plane_seats") );
        jsa.add(r.getLong("la_compania_id"));
        jsa.add(r.getString("la_compania_pkey"));
        jsa.add(r.getString("la_compania_pname"));
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
                m_.put("plane_id","Long");
            }
            
    //pkey       
            m_.put("plane_pkey","String"); 
            
    //plate       
            m_.put("plane_plate","String"); 
            
    //pname       
            m_.put("plane_pname","String"); 
            
    //seats       
            m_.put("plane_seats","Integer"); 
            
if(level<1){
                return m_;    
            }
            
 // laCompania
if(withIds){
            m_.put("laCompania_id","Long");   
                    
            }

        m_.put("laCompania_pkey","String");   
        

            m_.put("laCompania_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"plane_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"plane_pkey", nc++); 
    //plate       
            sToCell(r, row,"plane_plate", nc++); 
    //pname       
            sToCell(r, row,"plane_pname", nc++); 
    //seats            
            ldToCell(r, row,"plane_seats", nc++); 
 // laCompania
if(withIds){
                    lToCell(r, row,"la_compania_id", nc++);
                 }
sToCell(r, row,"la_compania_pkey", nc++);
sToCell(r, row,"la_compania_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE plane_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Plane dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPlate());
        t.addString(dc0.getPname());
        t.addInteger(dc0.getSeats());
   
            if(dc0.getLaCompania()!=null){
               t.addLong(dc0.getLaCompania().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final Plane dc0, final Tuple t){
                t.addString(dc0.getPlate());
        t.addString(dc0.getPname());
        t.addInteger(dc0.getSeats());
   
//      if(dc0.getLaCompania()!=null){
//            t.addLong(dc0.getLaCompania().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("plate", obj, t);

    fTString("pname", obj, t);

    fTInteger("seats", obj, t);

    fTLong("laCompania_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> laCompania = mapParents.get("laCompania");
            final Long laCompania_id = laCompania.get((String)obj.get("laCompania_pkey"));
            obj.put("laCompania_id", laCompania_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("plate", js, t);

    fTString("pname", js, t);

    fTInteger("seats", js, t);
     
    fTLong("laCompania_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("plate", js, t);
fTString("pname", js, t);
fTInteger("seats", js, t);

            //     fTLong("laCompania_id",js,t);
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
    public Plane doFrom(final Row r){
        final Plane plane = new Plane();
         plane.setId(r.getLong("plane_id"));
         
                plane.setPkey(  r.getString("plane_pkey"));
         
                plane.setPlate(  r.getString("plane_plate"));
         
                plane.setPname(  r.getString("plane_pname"));
         
                plane.setSeats(  r.getInteger("plane_seats"));

        final AirCompany laCompania = new AirCompany();
        laCompania.setId(r.getLong("la_compania_id"));
        laCompania.setPkey(r.getString("la_compania_pkey"));
        laCompania.setPname(r.getString("la_compania_pname"));
        plane.setLaCompania(laCompania);
          
        return plane;
    }
    
    @Override
    public Plane doFromJson(final JsonObject js){
        Plane plane = new Plane();
        plane.setId(js.getLong("id"));
        
                plane.setPkey(js.getString("pkey"));
        plane.setPlate(js.getString("plate"));
        plane.setPname(js.getString("pname"));
        plane.setSeats(js.getInteger("seats"));
        plane.setId(js.getLong("laCompania_id"));
        return plane;
    }

    @Override
    public JsonObject toJson(final Plane o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("plate",  o.getPlate() );
        jso.put("pname",  o.getPname() );
        jso.put("seats",  o.getSeats() );

            final AirCompany laCompania = o.getLaCompania();
            if(laCompania!=null){
                jso.put("laCompania_id", laCompania.getId());
                jso.put("laCompania_pkey", laCompania.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "plane_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "plane_pkey");
    slcb.doEqInPSimple( "pkey", "plane_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "plane_pname");
    slcb.doEqInPSimple( "pname", "plane_pname");               
    slcb.doGEPSimpleInt( "seats", "plane_seats");
    slcb.doLTPSimpleInt( "seats", "plane_seats");                
        
        slcb.doIlPSimple2( "laCompania_pkey", "la_compania_pkey");
        slcb.doEQPSimple2( "laCompania_pkey", "la_compania_pkey");
        slcb.doInLongCondition("laCompania_id", "la_compania_id");  
//AirCompany 1        
        slcb.doIlPSimple2( "laCompania_pname", "la_compania_pname");    

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"plane");
        sz0.addField("COUNT(plane.id) as c_plane_id").addName("count");
        
    sz0.addField("sum(plane.seats) as sum_plane_seats").addName("sum_seats");
        
        
//level 1
             
    sz0.applyG1(zLaCompania);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        