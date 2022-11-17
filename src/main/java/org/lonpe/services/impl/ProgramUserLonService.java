
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
 *   ProgramUserLonService 
 * 
 */
  
public class ProgramUserLonService extends AbstractServiceLon<ProgramUserLon>{

    private static final String SQLINSERT ="INSERT INTO program_user_lon(pkey,program_id,user_lon_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE program_user_lon SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE program_user_lon SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM program_user_lon_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM program_user_lon_view";
    private static final String SQLKEYS = "SELECT program_user_lon_pkey FROM program_user_lon_view";
    private static final String SQLIDBYPKEY = "SELECT id from program_user_lon WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from program_user_lon WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM program_user_lon WHERE id = $1 returning id";
    private static final String TABLENAME ="program_user_lon";
    

    public ProgramUserLonService() {
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
    private static String sql00 = "SELECT program_user_lon.id as program_user_lon_id,
program_user_lon.pkey as program_user_lon_pkey,
program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname 
  FROM 
  program_user_lon,
  program as program,
  user_lon as user_lon  
 WHERE 
 program_user_lon.program_id = program.id
 AND program_user_lon.user_lon_id = user_lon.id"
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
        
    dcModel.put("dc", "programUserLon");

//ID 
    names.add("id");

    sortMapFields.put("id","program_user_lon_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","program_user_lon");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("program_user_lon.program_user_lon_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  program
    doFieldMT0("program_user_lon","program", "program");  

    final JsonObject program =  doMto("program","program");        
   
    names.add("program_pname");
    sortMapFields.put( "program_pname", "program_pname");                
    program.put("pc","pname");          

    mto.add(program);
        

    //1  program  -- program_id
    final ZtatUnitInfoLon zProgram = new ZtatUnitInfoLon("program_id", "program",  "program","pname","program");
    mz1.put("zProgram", zProgram);    

//(1)  userLon
    doFieldMT0("program_user_lon","userLon", "user_lon");  

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
        jsa.add(r.getLong("program_user_lon_id") );       
        jsa.add(r.getString("program_user_lon_pkey") );
    jsa.add(r.getLong("program_id"));
    jsa.add(r.getString("program_pkey"));   
    
        
    jsa.add(r.getString("program_pname"));
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
        m.put("programUserLon_id",LONG);
    }        
//pkey    
    m.put("programUserLon_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// program   program
    if(withIds){
        m.put("program_id",LONG);                       
    }
    m.put("program_pkey",STRING);     
    m.put("program_pname",STRING);   
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
        lToCell(r, row,"program_user_lon_id", nc++); 
    }            //pkey       
            sToCell(r, row,"program_user_lon_pkey", nc++); 
//program   program        
    if(withIds){
        lToCell(r, row,"program_id", nc++);
    }
    sToCell(r, row,"program_pkey", nc++);
    sToCell(r, row,"program_pname", nc++);
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
        return SQLVIEW+ " WHERE program_user_lon_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ProgramUserLon dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getProgram()!=null){
       t.addLong(dc0.getProgram().getId());
    }   
    if(dc0.getUserLon()!=null){
       t.addLong(dc0.getUserLon().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final ProgramUserLon dc0, final Tuple t){
           
//      if(dc0.getProgram()!=null){
//            t.addLong(dc0.getProgram().getId());
//      }   
//      if(dc0.getUserLon()!=null){
//            t.addLong(dc0.getUserLon().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("program_id",obj,t);

    fTLong("userLon_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> program = mapParents.get("program");
            final Long program_id = program.get((String)obj.get("program_pkey"));
            obj.put("program_id", program_id);
      
            final Map<String, Long> userLon = mapParents.get("userLon");
            final Long userLon_id = userLon.get((String)obj.get("userLon_pkey"));
            obj.put("userLon_id", userLon_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("program_id",js,t);     
    fTLong("userLon_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("program_id",js,t);

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
    public ProgramUserLon doFrom(final Row r){
        final ProgramUserLon programUserLon = new ProgramUserLon();
         programUserLon.setId(r.getLong("program_user_lon_id"));         
                programUserLon.setPkey(  r.getString("program_user_lon_pkey"));              
        final Program program = new Program();
        program.setId(r.getLong("program_id"));
        program.setPkey(r.getString("program_pkey"));
        
        program.setPname(r.getString("program_pname"));
        programUserLon.setProgram(program);
        
        final UserLon userLon = new UserLon();
        userLon.setId(r.getLong("user_lon_id"));
        userLon.setPkey(r.getString("user_lon_pkey"));
        
        userLon.setPname(r.getString("user_lon_pname"));
        programUserLon.setUserLon(userLon);
          
        return programUserLon;
    }
    
    @Override
    public ProgramUserLon doFromJson(final JsonObject js){
        ProgramUserLon programUserLon = new ProgramUserLon();
        programUserLon.setId(js.getLong("id"));
        
                
                programUserLon.setPkey(js.getString("pkey"));        
            programUserLon.setId(js.getLong("program_id"));        
            programUserLon.setId(js.getLong("userLon_id"));
        return programUserLon;
    }

    @Override
    public JsonObject toJson(final ProgramUserLon o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final Program program = o.getProgram();
            if(program!=null){
                jso.put("program_id", program.getId());
                jso.put("program_pkey", program.getPkey());
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
       slcb.doInLongCondition("id", "program_user_lon_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "program_user_lon_pkey");
    slcb.doEqInPSimple( "pkey", "program_user_lon_pkey");
        
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");  
//Program 3        --
        slcb.doIlPSimple2( "program_pname", "program_pname");    
        
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
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"program_user_lon");
        sz0.addField("COUNT(program_user_lon.id) as c_program_user_lon_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zProgram"))   ;               
    sz0.applyG1(mz1.get("zUserLon"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        