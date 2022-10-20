
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
 *   ContractInService 
 * 
 */
   
  
public class ContractInService extends AbstractServiceLon<ContractIn>{

    private static final String SQLINSERT ="INSERT INTO contract_in(pkey,pname,program_base_time_period_id,third_person_id) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE contract_in SET pname = $1 WHERE id = $6 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE contract_in SET pname = $1 WHERE pkey = $6 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM contract_in_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM contract_in_view";
    private static final String SQLKEYS = "SELECT contract_in_pkey FROM contract_in_view";
    private static final String SQLIDBYPKEY = "SELECT id from contract_in WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from contract_in WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM contract_in WHERE id = $1 returning id";
    private static final String TABLENAME ="contract_in";
    
//1
    private static final ZtatUnitInfoLon zProgramBaseTimePeriod;

//1
    private static final ZtatUnitInfoLon zThirdPerson;

//2
    private static final ZtatUnitInfoLon zBaseTimePeriod;

//2
    private static final ZtatUnitInfoLon zProgram;

//3
    private static final ZtatUnitInfoLon zBase;

//3
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
    
    private static String sql00 = "SELECT contract_in.id as contract_in_id,
contract_in.pkey as contract_in_pkey,
contract_in.pname as contract_in_pname,
program_base_time_period.id as program_base_time_period_id,program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
program.id as program_id, program.pkey as program_pkey,program.pname as program_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  contract_in,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  base_time_period as base_time_period,
  program as program,
  base as base,
  time_period as time_period  
 WHERE 
 contract_in.program_base_time_period_id = program_base_time_period.id
 AND contract_in.third_person_id = third_person.id
 AND program_base_time_period.base_time_period_id = base_time_period.id
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

        
    dcModel.put("dc", "contractIn");

//ID 
    names.add("id");

    sortMapFields.put("id","contract_in_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("contract_in.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("contract_in.contract_in_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "contract_in_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("contract_in.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "contract_in_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  programBaseTimePeriod --------------------
    names.add("programBaseTimePeriod_id");      
    insertMapFields.put("contract_in.program_base_time_period_id","programBaseTimePeriod_id");    
       
    names.add("programBaseTimePeriod_pkey");        
    sortMapFields.put( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");        

    final JsonObject programBaseTimePeriod =  doMto("programBaseTimePeriod","programBaseTimePeriod");        

    mto.add(programBaseTimePeriod);
        

//(1)  thirdPerson --------------------
    names.add("thirdPerson_id");      
    insertMapFields.put("contract_in.third_person_id","thirdPerson_id");    
       
    names.add("thirdPerson_pkey");        
    sortMapFields.put( "thirdPerson_pkey", "third_person_pkey");        

    final JsonObject thirdPerson =  doMto("thirdPerson","thirdPerson");        
   
    names.add("thirdPerson_pname");
    sortMapFields.put( "thirdPerson_pname", "third_person_pname");         

    thirdPerson.put("pc","pname");          

    mto.add(thirdPerson);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   baseTimePeriod 
        
    names.add("baseTimePeriod_id");          
    names.add("baseTimePeriod_pkey");

    final JsonObject baseTimePeriod =   doMto2("baseTimePeriod","baseTimePeriod","programBaseTimePeriod");        
         
    mto2.add(baseTimePeriod);        
//(2)   program 
        
    names.add("program_id");          
    names.add("program_pkey");

    final JsonObject program =   doMto2("program","program","programBaseTimePeriod");        
   
    names.add("program_pname");  
    program.put("pc","pname");             
   
    sortMapFields.put( "program_pname", "program_pname");            
         
    mto2.add(program);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   base 
        
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject base =   doMto2("base","base","baseTimePeriod");        
   
    names.add("base_pname");  
    base.put("pc","pname");             
   
    sortMapFields.put( "base_pname", "base_pname");            
         
    mto3.add(base);        
//(3)   timePeriod 
        
    names.add("timePeriod_id");          
    names.add("timePeriod_pkey");

    final JsonObject timePeriod =   doMto2("timePeriod","timePeriod","baseTimePeriod");        
   
    names.add("timePeriod_pname");  
    timePeriod.put("pc","pname");             
   
    sortMapFields.put( "timePeriod_pname", "time_period_pname");            
         
    mto3.add(timePeriod);        

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

//ON RELATION contract    
            applyOtm(otm,"comercialDocuments","comercialDocumentIn","contract"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"invoiceLines","invoiceLineOut","comercialDocuments","comercialDocument","contract"); 
        

        applyOtm2(otm2,"payments","paymentIn","comercialDocuments",null,"contract"); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

        
//1  program_base_time_period  -- program_base_time_period_id
    zProgramBaseTimePeriod = new ZtatUnitInfoLon("program_base_time_period_id", "programBaseTimePeriod",  "program_base_time_period","null","program_base_time_period");

//1  third_person  -- third_person_id
    zThirdPerson = new ZtatUnitInfoLon("third_person_id", "thirdPerson",  "third_person","pname","third_person");

//2    
    zBaseTimePeriod = new ZtatUnitInfoLon("base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");

//2    
    zProgram = new ZtatUnitInfoLon("program_id", "program",  "program","pname","program");

//3
    zBase = new ZtatUnitInfoLon("base_id", "base",  "base","pname","base");

//3
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
        jsa.add(r.getLong("contract_in_id") );
        jsa.add(r.getString("contract_in_pkey") );
        jsa.add(r.getString("contract_in_pname") );
        jsa.add(r.getLong("program_base_time_period_id"));
        jsa.add(r.getString("program_base_time_period_pkey"));
        jsa.add(r.getLong("third_person_id"));
        jsa.add(r.getString("third_person_pkey"));
        jsa.add(r.getString("third_person_pname"));
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
                m_.put("contractIn_id","Long");
            }
            
    //pkey       
            m_.put("contractIn_pkey","String"); 
            
    //pname       
            m_.put("contractIn_pname","String"); 
            
if(level<1){
                return m_;    
            }
            
 // programBaseTimePeriod
if(withIds){
            m_.put("programBaseTimePeriod_id","Long");   
                    
            }

        m_.put("programBaseTimePeriod_pkey","String");   
        
 // thirdPerson
if(withIds){
            m_.put("thirdPerson_id","Long");   
                    
            }

        m_.put("thirdPerson_pkey","String");   
        

            m_.put("thirdPerson_pname","String");   
            
//[2] baseTimePeriod  

        if(level>1){
            if(withIds){
               m_.put("baseTimePeriod_id","Long");              
            }      
        
        m_.put("baseTimePeriod_pkey","String");  
 
                      }             
//[2] program  

        if(level>1){
            if(withIds){
               m_.put("program_id","Long");              
            }      
        
        m_.put("program_pkey","String");  

            m_.put("program_pname","String");    
 
                      }             
//[3] base  

        if(level>2){
            if(withIds){
               m_.put("base_id","Long");              
            }      
        
        m_.put("base_pkey","String");  

            m_.put("base_pname","String");    
 
                      }             
//[3] timePeriod  

        if(level>2){
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
        lToCell(r, row,"contract_in_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"contract_in_pkey", nc++); 
    //pname       
            sToCell(r, row,"contract_in_pname", nc++); 
 // programBaseTimePeriod
if(withIds){
                    lToCell(r, row,"program_base_time_period_id", nc++);
                 }
sToCell(r, row,"program_base_time_period_pkey", nc++);
 // thirdPerson
if(withIds){
                    lToCell(r, row,"third_person_id", nc++);
                 }
sToCell(r, row,"third_person_pkey", nc++);
sToCell(r, row,"third_person_pname", nc++);
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
        return SQLVIEW+ " WHERE contract_in_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ContractIn dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
   
            if(dc0.getProgramBaseTimePeriod()!=null){
               t.addLong(dc0.getProgramBaseTimePeriod().getId());
            }
   
            if(dc0.getThirdPerson()!=null){
               t.addLong(dc0.getThirdPerson().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final ContractIn dc0, final Tuple t){
                t.addString(dc0.getPname());
   
//      if(dc0.getProgramBaseTimePeriod()!=null){
//            t.addLong(dc0.getProgramBaseTimePeriod().getId());
//      }
   
//      if(dc0.getThirdPerson()!=null){
//            t.addLong(dc0.getThirdPerson().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);

    fTLong("programBaseTimePeriod_id",obj,t);

    fTLong("thirdPerson_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> programBaseTimePeriod = mapParents.get("programBaseTimePeriod");
            final Long programBaseTimePeriod_id = programBaseTimePeriod.get((String)obj.get("programBaseTimePeriod_pkey"));
            obj.put("programBaseTimePeriod_id", programBaseTimePeriod_id);
      
            final Map<String, Long> thirdPerson = mapParents.get("thirdPerson");
            final Long thirdPerson_id = thirdPerson.get((String)obj.get("thirdPerson_pkey"));
            obj.put("thirdPerson_id", thirdPerson_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("pname", js, t);
     
    fTLong("programBaseTimePeriod_id",js,t);
     
    fTLong("thirdPerson_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);

            //     fTLong("programBaseTimePeriod_id",js,t);

            //     fTLong("thirdPerson_id",js,t);
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
    public ContractIn doFrom(final Row r){
        final ContractIn contractIn = new ContractIn();
         contractIn.setId(r.getLong("contract_in_id"));
         
                contractIn.setPkey(  r.getString("contract_in_pkey"));
         
                contractIn.setPname(  r.getString("contract_in_pname"));

        final ProgramBaseTimePeriod programBaseTimePeriod = new ProgramBaseTimePeriod();
        programBaseTimePeriod.setId(r.getLong("program_base_time_period_id"));
        programBaseTimePeriod.setPkey(r.getString("program_base_time_period_pkey"));
        
        contractIn.setProgramBaseTimePeriod(programBaseTimePeriod);
        

        final ThirdPerson thirdPerson = new ThirdPerson();
        thirdPerson.setId(r.getLong("third_person_id"));
        thirdPerson.setPkey(r.getString("third_person_pkey"));
        thirdPerson.setPname(r.getString("third_person_pname"));
        contractIn.setThirdPerson(thirdPerson);
        

        final BaseTimePeriod baseTimePeriod = new BaseTimePeriod();
        baseTimePeriod.setId(r.getLong("base_time_period_id"));
        baseTimePeriod.setPkey(r.getString("base_time_period_pkey"));
         programBaseTimePeriod.setBaseTimePeriod(baseTimePeriod); 

        final Program program = new Program();
        program.setId(r.getLong("program_id"));
        program.setPkey(r.getString("program_pkey"));
        program.setPname(r.getString("program_pname"));
 programBaseTimePeriod.setProgram(program);   
        return contractIn;
    }
    
    @Override
    public ContractIn doFromJson(final JsonObject js){
        ContractIn contractIn = new ContractIn();
        contractIn.setId(js.getLong("id"));
        
                contractIn.setPkey(js.getString("pkey"));
        contractIn.setPname(js.getString("pname"));
        contractIn.setId(js.getLong("programBaseTimePeriod_id"));
        contractIn.setId(js.getLong("thirdPerson_id"));
        return contractIn;
    }

    @Override
    public JsonObject toJson(final ContractIn o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );

            final ProgramBaseTimePeriod programBaseTimePeriod = o.getProgramBaseTimePeriod();
            if(programBaseTimePeriod!=null){
                jso.put("programBaseTimePeriod_id", programBaseTimePeriod.getId());
                jso.put("programBaseTimePeriod_pkey", programBaseTimePeriod.getPkey());
            }
            

            final ThirdPerson thirdPerson = o.getThirdPerson();
            if(thirdPerson!=null){
                jso.put("thirdPerson_id", thirdPerson.getId());
                jso.put("thirdPerson_pkey", thirdPerson.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "contract_in_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "contract_in_pkey");
    slcb.doEqInPSimple( "pkey", "contract_in_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "contract_in_pname");
    slcb.doEqInPSimple( "pname", "contract_in_pname");            
        
        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");  
//ProgramBaseTimePeriod undefined        
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");  
//ThirdPerson 1        
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");    

        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");//BaseTimePeriod undefined
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id"); 
        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id"); 

        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");//Program 3
        slcb.doIlPSimple2( "program_pname", "program_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"contract_in");
        sz0.addField("COUNT(contract_in.id) as c_contract_in_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zProgramBaseTimePeriod);               
    sz0.applyG1(zThirdPerson);      
    //level 2
    
    sz0.applyG2(zProgramBaseTimePeriod,zBaseTimePeriod);                           
    sz0.applyG2(zProgramBaseTimePeriod,zProgram);                           
    //level 3
    
        sz0.applyG3(zProgramBaseTimePeriod,zBaseTimePeriod,zBase);               
        sz0.applyG3(zProgramBaseTimePeriod,zBaseTimePeriod,zTimePeriod);               
        return sz0;
    }
}
    
        