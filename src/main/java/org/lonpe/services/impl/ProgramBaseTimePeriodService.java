
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
 *   ProgramBaseTimePeriodService 
 * 
 */
   
  
public class ProgramBaseTimePeriodService extends AbstractServiceLon<ProgramBaseTimePeriod>{

    private static final String SQLINSERT ="INSERT INTO program_base_time_period(pkey,base_time_period_id,program_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE program_base_time_period SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE program_base_time_period SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM program_base_time_period_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM program_base_time_period_view";
    private static final String SQLKEYS = "SELECT program_base_time_period_pkey FROM program_base_time_period_view";
    private static final String SQLIDBYPKEY = "SELECT id from program_base_time_period WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from program_base_time_period WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM program_base_time_period WHERE id = $1 returning id";
    private static final String TABLENAME ="program_base_time_period";
    
//1
    private static final ZtatUnitInfoLon zBaseTimePeriod;

//1
    private static final ZtatUnitInfoLon zProgram;

//2
    private static final ZtatUnitInfoLon zBase;

//2
    private static final ZtatUnitInfoLon zTimePeriod;
    
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
    
    private static String sql00 = "SELECT program_base_time_period.id as program_base_time_period_id,
program_base_time_period.pkey as program_base_time_period_pkey,
base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,
program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  program_base_time_period,
  base_time_period as base_time_period,
  program as program,
  base as base,
  time_period as time_period  
 WHERE 
 program_base_time_period.base_time_period_id = base_time_period.id
 AND program_base_time_period.program_id = program.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 
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

        
    dcModel.put("dc", "programBaseTimePeriod");

//ID 
    names.add("id");

    sortMapFields.put("id","program_base_time_period_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("program_base_time_period.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("program_base_time_period.program_base_time_period_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "program_base_time_period_pkey");                   
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  baseTimePeriod --------------------
    names.add("baseTimePeriod_id");      
    insertMapFields.put("program_base_time_period.base_time_period_id","baseTimePeriod_id");    
       
    names.add("baseTimePeriod_pkey");        
    sortMapFields.put( "baseTimePeriod_pkey", "base_time_period_pkey");        

    final JsonObject baseTimePeriod =  doMto("baseTimePeriod","baseTimePeriod");        

    mto.add(baseTimePeriod);
        

//(1)  program --------------------
    names.add("program_id");      
    insertMapFields.put("program_base_time_period.program_id","program_id");    
       
    names.add("program_pkey");        
    sortMapFields.put( "program_pkey", "program_pkey");        

    final JsonObject program =  doMto("program","program");        
   
    names.add("program_pname");
    sortMapFields.put( "program_pname", "program_pname");         

    program.put("pc","pname");          

    mto.add(program);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   base 
        
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject base =   doMto2("base","base","baseTimePeriod");        
   
    names.add("base_pname");  
    base.put("pc","pname");             
   
    sortMapFields.put( "base_pname", "base_pname");            
         
    mto2.add(base);        
//(2)   timePeriod 
        
    names.add("timePeriod_id");          
    names.add("timePeriod_pkey");

    final JsonObject timePeriod =   doMto2("timePeriod","timePeriod","baseTimePeriod");        
   
    names.add("timePeriod_pname");  
    timePeriod.put("pc","pname");             
   
    sortMapFields.put( "timePeriod_pname", "time_period_pname");            
         
    mto2.add(timePeriod);        

    dcModel.put("mto2",mto2);    
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"contracts","contractIn"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"comercialDocuments","comercialDocumentIn","contracts","contract",null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"invoiceLines","invoiceLineOut","comercialDocuments","comercialDocument","contract",null); 
        

        applyOtm3(otm3,"payments","paymentIn","comercialDocuments",null,"contract",null); 
        

/** OTM 3  ON MODEL**/
        dcModel.put("otm3",otm3);
        

        
//1  base_time_period  -- base_time_period_id
    zBaseTimePeriod = new ZtatUnitInfoLon("base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");

//1  program  -- program_id
    zProgram = new ZtatUnitInfoLon("program_id", "program",  "program","pname","program");

//2    
    zBase = new ZtatUnitInfoLon("base_id", "base",  "base","pname","base");

//2    
    zTimePeriod = new ZtatUnitInfoLon("time_period_id", "timePeriod",  "time_period","pname","time_period");

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
        jsa.add(r.getLong("program_base_time_period_id") );
        jsa.add(r.getString("program_base_time_period_pkey") );
        jsa.add(r.getLong("base_time_period_id"));
        jsa.add(r.getString("base_time_period_pkey"));
        jsa.add(r.getLong("program_id"));
        jsa.add(r.getString("program_pkey"));
        jsa.add(r.getString("program_pname"));
        jsa.add(r.getLong("base_id"));
        jsa.add(r.getString("base_pkey"));
        jsa.add(r.getString("base_pname"));
        jsa.add(r.getLong("time_period_id"));
        jsa.add(r.getString("time_period_pkey"));
        jsa.add(r.getString("time_period_pname"));
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
                m_.put("programBaseTimePeriod_id","Long");
            }
            
    //pkey       
            m_.put("programBaseTimePeriod_pkey","String"); 
            
if(level<1){
                return m_;    
            }
            
 // baseTimePeriod
if(withIds){
            m_.put("baseTimePeriod_id","Long");   
                    
            }

        m_.put("baseTimePeriod_pkey","String");   
        
 // program
if(withIds){
            m_.put("program_id","Long");   
                    
            }

        m_.put("program_pkey","String");   
        

            m_.put("program_pname","String");   
            
//[2] base  

        if(level>1){
            if(withIds){
               m_.put("base_id","Long");              
            }      
        
        m_.put("base_pkey","String");  

            m_.put("base_pname","String");    
 
                      }             
//[2] timePeriod  

        if(level>1){
            if(withIds){
               m_.put("timePeriod_id","Long");              
            }      
        
        m_.put("timePeriod_pkey","String");  

            m_.put("timePeriod_pname","String");    
 
                      }             
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"program_base_time_period_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"program_base_time_period_pkey", nc++); 
 // baseTimePeriod
if(withIds){
                    lToCell(r, row,"base_time_period_id", nc++);
                 }
sToCell(r, row,"base_time_period_pkey", nc++);
 // program
if(withIds){
                    lToCell(r, row,"program_id", nc++);
                 }
sToCell(r, row,"program_pkey", nc++);
sToCell(r, row,"program_pname", nc++);
// base
if(withIds){
            lToCell(r, row,"base_id", nc++);
        }
sToCell(r, row,"base_pkey", nc++);
sToCell(r, row,"base_pname", nc++);
// timePeriod
if(withIds){
            lToCell(r, row,"time_period_id", nc++);
        }
sToCell(r, row,"time_period_pkey", nc++);
sToCell(r, row,"time_period_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE program_base_time_period_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ProgramBaseTimePeriod dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
            if(dc0.getBaseTimePeriod()!=null){
               t.addLong(dc0.getBaseTimePeriod().getId());
            }
   
            if(dc0.getProgram()!=null){
               t.addLong(dc0.getProgram().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final ProgramBaseTimePeriod dc0, final Tuple t){
           
//      if(dc0.getBaseTimePeriod()!=null){
//            t.addLong(dc0.getBaseTimePeriod().getId());
//      }
   
//      if(dc0.getProgram()!=null){
//            t.addLong(dc0.getProgram().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("baseTimePeriod_id",obj,t);

    fTLong("program_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> baseTimePeriod = mapParents.get("baseTimePeriod");
            final Long baseTimePeriod_id = baseTimePeriod.get((String)obj.get("baseTimePeriod_pkey"));
            obj.put("baseTimePeriod_id", baseTimePeriod_id);
      
            final Map<String, Long> program = mapParents.get("program");
            final Long program_id = program.get((String)obj.get("program_pkey"));
            obj.put("program_id", program_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
     
    fTLong("baseTimePeriod_id",js,t);
     
    fTLong("program_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("baseTimePeriod_id",js,t);

            //     fTLong("program_id",js,t);
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
    public ProgramBaseTimePeriod doFrom(final Row r){
        final ProgramBaseTimePeriod programBaseTimePeriod = new ProgramBaseTimePeriod();
         programBaseTimePeriod.setId(r.getLong("program_base_time_period_id"));
         
                programBaseTimePeriod.setPkey(  r.getString("program_base_time_period_pkey"));

        final BaseTimePeriod baseTimePeriod = new BaseTimePeriod();
        baseTimePeriod.setId(r.getLong("base_time_period_id"));
        baseTimePeriod.setPkey(r.getString("base_time_period_pkey"));
        
        programBaseTimePeriod.setBaseTimePeriod(baseTimePeriod);
        

        final Program program = new Program();
        program.setId(r.getLong("program_id"));
        program.setPkey(r.getString("program_pkey"));
        program.setPname(r.getString("program_pname"));
        programBaseTimePeriod.setProgram(program);
        

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
        return programBaseTimePeriod;
    }
    
    @Override
    public ProgramBaseTimePeriod doFromJson(final JsonObject js){
        ProgramBaseTimePeriod programBaseTimePeriod = new ProgramBaseTimePeriod();
        programBaseTimePeriod.setId(js.getLong("id"));
        
                programBaseTimePeriod.setPkey(js.getString("pkey"));
        programBaseTimePeriod.setId(js.getLong("baseTimePeriod_id"));
        programBaseTimePeriod.setId(js.getLong("program_id"));
        return programBaseTimePeriod;
    }

    @Override
    public JsonObject toJson(final ProgramBaseTimePeriod o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final BaseTimePeriod baseTimePeriod = o.getBaseTimePeriod();
            if(baseTimePeriod!=null){
                jso.put("baseTimePeriod_id", baseTimePeriod.getId());
                jso.put("baseTimePeriod_pkey", baseTimePeriod.getPkey());
            }
            

            final Program program = o.getProgram();
            if(program!=null){
                jso.put("program_id", program.getId());
                jso.put("program_pkey", program.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "program_base_time_period_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "program_base_time_period_pkey");
    slcb.doEqInPSimple( "pkey", "program_base_time_period_pkey");
        
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");  
//BaseTimePeriod undefined        
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");  
//Program 3        
        slcb.doIlPSimple2( "program_pname", "program_pname");    

        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");//Base 1
        slcb.doIlPSimple2( "base_pname", "base_pname"); 

        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");//TimePeriod 3
        slcb.doIlPSimple2( "timePeriod_pname", "time_period_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"program_base_time_period");
        sz0.addField("COUNT(program_base_time_period.id) as c_program_base_time_period_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zBaseTimePeriod);               
    sz0.applyG1(zProgram);      
    //level 2
    
    sz0.applyG2(zBaseTimePeriod,zBase);                           
    sz0.applyG2(zBaseTimePeriod,zTimePeriod);                           
    //level 3
    
        return sz0;
    }
}
    
        