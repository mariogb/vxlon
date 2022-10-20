
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

import java.time.LocalDateTime;

import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;

/**
 *   ComercialDocumentInService 
 * 
 */
   
  
public class ComercialDocumentInService extends AbstractServiceLon<ComercialDocumentIn>{

    private static final String SQLINSERT ="INSERT INTO comercial_document_in(pkey,created_date,document_date,folio,pname,status,supply_date,contract_id,user_autor_id,comercial_document_type_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE comercial_document_in SET created_date = $1,document_date = $2,folio = $3,pname = $4,status = $5,supply_date = $6 WHERE id = $13 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE comercial_document_in SET created_date = $1,document_date = $2,folio = $3,pname = $4,status = $5,supply_date = $6 WHERE pkey = $13 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM comercial_document_in_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM comercial_document_in_view";
    private static final String SQLKEYS = "SELECT comercial_document_in_pkey FROM comercial_document_in_view";
    private static final String SQLIDBYPKEY = "SELECT id from comercial_document_in WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from comercial_document_in WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM comercial_document_in WHERE id = $1 returning id";
    private static final String TABLENAME ="comercial_document_in";
    
//1
    private static final ZtatUnitInfoLon zContract;

//1
    private static final ZtatUnitInfoLon zUserAutor;

//1
    private static final ZtatUnitInfoLon zComercialDocumentType;

//2
    private static final ZtatUnitInfoLon zProgramBaseTimePeriod;

//2
    private static final ZtatUnitInfoLon zThirdPerson;

//3
    private static final ZtatUnitInfoLon zBaseTimePeriod;

//3
    private static final ZtatUnitInfoLon zProgram;
    
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
    
    private static String sql00 = "SELECT comercial_document_in.id as comercial_document_in_id,
comercial_document_in.pkey as comercial_document_in_pkey,
comercial_document_in.created_date as comercial_document_in_created_date,
comercial_document_in.document_date as comercial_document_in_document_date,
comercial_document_in.folio as comercial_document_in_folio,
comercial_document_in.pname as comercial_document_in_pname,
comercial_document_in.status as comercial_document_in_status,
comercial_document_in.supply_date as comercial_document_in_supply_date,
contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id,comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
program.id as program_id, program.pkey as program_pkey,program.pname as program_pname 
  FROM 
  comercial_document_in,
  contract_in as contract,
  user_lon as user_autor,
  comercial_document_type_in as comercial_document_type,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  base_time_period as base_time_period,
  program as program  
 WHERE 
 comercial_document_in.contract_id = contract.id
 AND comercial_document_in.user_autor_id = user_autor.id
 AND comercial_document_in.comercial_document_type_id = comercial_document_type.id
 AND contract.program_base_time_period_id = program_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND program_base_time_period.base_time_period_id = base_time_period.id
 AND program_base_time_period.program_id = program.id; 
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

        
    dcModel.put("dc", "comercialDocumentIn");

//ID 
    names.add("id");

    sortMapFields.put("id","comercial_document_in_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("comercial_document_in.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("comercial_document_in.comercial_document_in_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "comercial_document_in_pkey");                   
 
    ps.add(pkey);
 
//createdDate
    names.add("createdDate");
    insertMapFields.put("comercial_document_in.created_date","createdDate");  

//Create property createdDate       
    final JsonObject createdDate = ps00a("createdDate", "LocalDateTime",true);

    sortMapFields.put("createdDate", "comercial_document_in_created_date");               
 
//Set by system
    createdDate.put("setBySys","now");  
 
    ps.add(createdDate);
 
//documentDate
    names.add("documentDate");
    insertMapFields.put("comercial_document_in.document_date","documentDate");  

//Create property documentDate       
    final JsonObject documentDate = ps00a("documentDate", "LocalDateTime",false);

    sortMapFields.put("documentDate", "comercial_document_in_document_date");               
 
    ps.add(documentDate);
 
//folio
    names.add("folio");
    insertMapFields.put("comercial_document_in.folio","folio");  

//Create property folio       
    final JsonObject folio = ps00a("folio", "String",true);
 
    ps.add(folio);
 
//pname
    names.add("pname");
    insertMapFields.put("comercial_document_in.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "comercial_document_in_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//status
    names.add("status");
    insertMapFields.put("comercial_document_in.status","status");  

//Create property status       
    final JsonObject status = ps00a("status", "String",true);

    final JsonArray statusInList = new JsonArray();
                statusInList.add("PENDENT"); 
statusInList.add("SUPPLIED"); 
statusInList.add("CANCEL"); 
    status.put("inList",statusInList );                
 
    ps.add(status);
 
//supplyDate
    names.add("supplyDate");
    insertMapFields.put("comercial_document_in.supply_date","supplyDate");  

//Create property supplyDate       
    final JsonObject supplyDate = ps00a("supplyDate", "LocalDateTime",false);

    sortMapFields.put("supplyDate", "comercial_document_in_supply_date");               
 
    ps.add(supplyDate);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  contract --------------------
    names.add("contract_id");      
    insertMapFields.put("comercial_document_in.contract_id","contract_id");    
       
    names.add("contract_pkey");        
    sortMapFields.put( "contract_pkey", "contract_pkey");        

    final JsonObject contract =  doMto("contract","contractIn");        
   
    names.add("contract_pname");
    sortMapFields.put( "contract_pname", "contract_pname");         

    contract.put("pc","pname");          

    mto.add(contract);
        

//(1)  userAutor --------------------
    names.add("userAutor_id");      
    insertMapFields.put("comercial_document_in.user_autor_id","userAutor_id");    
       
    names.add("userAutor_pkey");        
    sortMapFields.put( "userAutor_pkey", "user_autor_pkey");        

    final JsonObject userAutor =  doMto("userAutor","userLon");        
   
    names.add("userAutor_pname");
    sortMapFields.put( "userAutor_pname", "user_autor_pname");         

    userAutor.put("pc","pname");          

    userAutor.put("setBySys","putCurrentUser");           

    mto.add(userAutor);
        

//(1)  comercialDocumentType --------------------
    names.add("comercialDocumentType_id");      
    insertMapFields.put("comercial_document_in.comercial_document_type_id","comercialDocumentType_id");    
       
    names.add("comercialDocumentType_pkey");        
    sortMapFields.put( "comercialDocumentType_pkey", "comercial_document_type_pkey");        

    final JsonObject comercialDocumentType =  doMto("comercialDocumentType","comercialDocumentTypeIn");        
   
    names.add("comercialDocumentType_pname");
    sortMapFields.put( "comercialDocumentType_pname", "comercial_document_type_pname");         

    comercialDocumentType.put("pc","pname");          

    comercialDocumentType.put("onForm","getAll");           

    mto.add(comercialDocumentType);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   programBaseTimePeriod 
        
    names.add("programBaseTimePeriod_id");          
    names.add("programBaseTimePeriod_pkey");

    final JsonObject programBaseTimePeriod =   doMto2("programBaseTimePeriod","programBaseTimePeriod","contract");        
         
    mto2.add(programBaseTimePeriod);        
//(2)   thirdPerson 
        
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPerson =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");  
    thirdPerson.put("pc","pname");             
   
    sortMapFields.put( "thirdPerson_pname", "third_person_pname");            
         
    mto2.add(thirdPerson);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   baseTimePeriod 
        
    names.add("baseTimePeriod_id");          
    names.add("baseTimePeriod_pkey");

    final JsonObject baseTimePeriod =   doMto2("baseTimePeriod","baseTimePeriod","programBaseTimePeriod");        
         
    mto3.add(baseTimePeriod);        
//(3)   program 
        
    names.add("program_id");          
    names.add("program_pkey");

    final JsonObject program =   doMto2("program","program","programBaseTimePeriod");        
   
    names.add("program_pname");  
    program.put("pc","pname");             
   
    sortMapFields.put( "program_pname", "program_pname");            
         
    mto3.add(program);        

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

//ON RELATION comercialDocument    
            applyOtm(otm,"invoiceLines","invoiceLineOut","comercialDocument"); 
                

        applyOtm(otm,"payments","paymentIn"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

        
//1  contract  -- contract_id
    zContract = new ZtatUnitInfoLon("contract_id", "contract",  "contract_in","pname","contract");

//1  user_autor  -- user_autor_id
    zUserAutor = new ZtatUnitInfoLon("user_autor_id", "userAutor",  "user_lon","pname","user_autor");

//1  comercial_document_type  -- comercial_document_type_id
    zComercialDocumentType = new ZtatUnitInfoLon("comercial_document_type_id", "comercialDocumentType",  "comercial_document_type_in","pname","comercial_document_type");

//2    
    zProgramBaseTimePeriod = new ZtatUnitInfoLon("program_base_time_period_id", "programBaseTimePeriod",  "program_base_time_period","null","program_base_time_period");

//2    
    zThirdPerson = new ZtatUnitInfoLon("third_person_id", "thirdPerson",  "third_person","pname","third_person");

//3
    zBaseTimePeriod = new ZtatUnitInfoLon("base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");

//3
    zProgram = new ZtatUnitInfoLon("program_id", "program",  "program","pname","program");

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
        jsa.add(r.getLong("comercial_document_in_id") );
        jsa.add(r.getString("comercial_document_in_pkey") );
        jsa.add(r.getLocalDate("comercial_document_in_created_date").toString() ); // undefined
 asMaybeNullLocalDate(r,"comercial_document_in_document_date",jsa); //true
        jsa.add(r.getString("comercial_document_in_folio") );
        jsa.add(r.getString("comercial_document_in_pname") );
        jsa.add(r.getString("comercial_document_in_status") );
 asMaybeNullLocalDate(r,"comercial_document_in_supply_date",jsa); //true
        jsa.add(r.getLong("contract_id"));
        jsa.add(r.getString("contract_pkey"));
        jsa.add(r.getString("contract_pname"));
        jsa.add(r.getLong("user_autor_id"));
        jsa.add(r.getString("user_autor_pkey"));
        jsa.add(r.getString("user_autor_pname"));
        jsa.add(r.getLong("comercial_document_type_id"));
        jsa.add(r.getString("comercial_document_type_pkey"));
        jsa.add(r.getString("comercial_document_type_pname"));
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
                m_.put("comercialDocumentIn_id","Long");
            }
            
    //pkey       
            m_.put("comercialDocumentIn_pkey","String"); 
            
    //createdDate       
            m_.put("comercialDocumentIn_createdDate","LocalDateTime"); 
            
    //documentDate       
            m_.put("comercialDocumentIn_documentDate","LocalDateTime"); 
            
    //folio       
            m_.put("comercialDocumentIn_folio","String"); 
            
    //pname       
            m_.put("comercialDocumentIn_pname","String"); 
            
    //status       
            m_.put("comercialDocumentIn_status","String"); 
            
    //supplyDate       
            m_.put("comercialDocumentIn_supplyDate","LocalDateTime"); 
            
if(level<1){
                return m_;    
            }
            
 // contract
if(withIds){
            m_.put("contract_id","Long");   
                    
            }

        m_.put("contract_pkey","String");   
        

            m_.put("contract_pname","String");   
            
 // userAutor
if(withIds){
            m_.put("userAutor_id","Long");   
                    
            }

        m_.put("userAutor_pkey","String");   
        

            m_.put("userAutor_pname","String");   
            
 // comercialDocumentType
if(withIds){
            m_.put("comercialDocumentType_id","Long");   
                    
            }

        m_.put("comercialDocumentType_pkey","String");   
        

            m_.put("comercialDocumentType_pname","String");   
            
//[2] programBaseTimePeriod  

        if(level>1){
            if(withIds){
               m_.put("programBaseTimePeriod_id","Long");              
            }      
        
        m_.put("programBaseTimePeriod_pkey","String");  
 
                      }             
//[2] thirdPerson  

        if(level>1){
            if(withIds){
               m_.put("thirdPerson_id","Long");              
            }      
        
        m_.put("thirdPerson_pkey","String");  

            m_.put("thirdPerson_pname","String");    
 
                      }             
//[3] baseTimePeriod  

        if(level>2){
            if(withIds){
               m_.put("baseTimePeriod_id","Long");              
            }      
        
        m_.put("baseTimePeriod_pkey","String");  
 
                      }             
//[3] program  

        if(level>2){
            if(withIds){
               m_.put("program_id","Long");              
            }      
        
        m_.put("program_pkey","String");  

            m_.put("program_pname","String");    
 
                      }             
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"comercial_document_in_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"comercial_document_in_pkey", nc++); 
    //createdDate            
            ldtToCell(r, row,"comercial_document_in_created_date", nc++); 
    //documentDate            
            ldtToCell(r, row,"comercial_document_in_document_date", nc++); 
    //folio       
            sToCell(r, row,"comercial_document_in_folio", nc++); 
    //pname       
            sToCell(r, row,"comercial_document_in_pname", nc++); 
    //status       
            sToCell(r, row,"comercial_document_in_status", nc++); 
    //supplyDate            
            ldtToCell(r, row,"comercial_document_in_supply_date", nc++); 
 // contract
if(withIds){
                    lToCell(r, row,"contract_id", nc++);
                 }
sToCell(r, row,"contract_pkey", nc++);
sToCell(r, row,"contract_pname", nc++);
 // userAutor
if(withIds){
                    lToCell(r, row,"user_autor_id", nc++);
                 }
sToCell(r, row,"user_autor_pkey", nc++);
sToCell(r, row,"user_autor_pname", nc++);
 // comercialDocumentType
if(withIds){
                    lToCell(r, row,"comercial_document_type_id", nc++);
                 }
sToCell(r, row,"comercial_document_type_pkey", nc++);
sToCell(r, row,"comercial_document_type_pname", nc++);
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
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE comercial_document_in_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ComercialDocumentIn dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addLocalDateTime(dc0.getDocumentDate());
        t.addString(dc0.getFolio());
        t.addString(dc0.getPname());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
   
            if(dc0.getContract()!=null){
               t.addLong(dc0.getContract().getId());
            }
   
            if(dc0.getUserAutor()!=null){
               t.addLong(dc0.getUserAutor().getId());
            }
   
            if(dc0.getComercialDocumentType()!=null){
               t.addLong(dc0.getComercialDocumentType().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final ComercialDocumentIn dc0, final Tuple t){
                t.addLocalDateTime(dc0.getCreatedDate());
        t.addLocalDateTime(dc0.getDocumentDate());
        t.addString(dc0.getFolio());
        t.addString(dc0.getPname());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
   
//      if(dc0.getContract()!=null){
//            t.addLong(dc0.getContract().getId());
//      }
   
//      if(dc0.getUserAutor()!=null){
//            t.addLong(dc0.getUserAutor().getId());
//      }
   
//      if(dc0.getComercialDocumentType()!=null){
//            t.addLong(dc0.getComercialDocumentType().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLocalDateTime("createdDate", obj, t);

    fTLocalDateTime("documentDate", obj, t);

    fTString("folio", obj, t);

    fTString("pname", obj, t);

    fTString("status", obj, t);

    fTLocalDateTime("supplyDate", obj, t);

    fTLong("contract_id",obj,t);

    fTLong("userAutor_id",obj,t);

    fTLong("comercialDocumentType_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> contract = mapParents.get("contract");
            final Long contract_id = contract.get((String)obj.get("contract_pkey"));
            obj.put("contract_id", contract_id);
      
            final Map<String, Long> userAutor = mapParents.get("userAutor");
            final Long userAutor_id = userAutor.get((String)obj.get("userAutor_pkey"));
            obj.put("userAutor_id", userAutor_id);
      
            final Map<String, Long> comercialDocumentType = mapParents.get("comercialDocumentType");
            final Long comercialDocumentType_id = comercialDocumentType.get((String)obj.get("comercialDocumentType_pkey"));
            obj.put("comercialDocumentType_id", comercialDocumentType_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTLocalDateTime("createdDate", js, t);

    fTLocalDateTime("documentDate", js, t);

    fTString("folio", js, t);

    fTString("pname", js, t);

    fTString("status", js, t);

    fTLocalDateTime("supplyDate", js, t);
     
    fTLong("contract_id",js,t);
     
    fTLong("userAutor_id",js,t);
     
    fTLong("comercialDocumentType_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTLocalDateTime("createdDate", js, t);
fTLocalDateTime("documentDate", js, t);
fTString("folio", js, t);
fTString("pname", js, t);
fTString("status", js, t);
fTLocalDateTime("supplyDate", js, t);

            //     fTLong("contract_id",js,t);

            //     fTLong("userAutor_id",js,t);

            //     fTLong("comercialDocumentType_id",js,t);
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
    public ComercialDocumentIn doFrom(final Row r){
        final ComercialDocumentIn comercialDocumentIn = new ComercialDocumentIn();
         comercialDocumentIn.setId(r.getLong("comercial_document_in_id"));
         
                comercialDocumentIn.setPkey(  r.getString("comercial_document_in_pkey"));
         
                comercialDocumentIn.setCreatedDate(  r.getLocalDateTime("comercial_document_in_created_date"));
         
                comercialDocumentIn.setDocumentDate(  r.getLocalDateTime("comercial_document_in_document_date"));
         
                comercialDocumentIn.setFolio(  r.getString("comercial_document_in_folio"));
         
                comercialDocumentIn.setPname(  r.getString("comercial_document_in_pname"));
         
                comercialDocumentIn.setStatus(  r.getString("comercial_document_in_status"));
         
                comercialDocumentIn.setSupplyDate(  r.getLocalDateTime("comercial_document_in_supply_date"));

        final ContractIn contract = new ContractIn();
        contract.setId(r.getLong("contract_id"));
        contract.setPkey(r.getString("contract_pkey"));
        contract.setPname(r.getString("contract_pname"));
        comercialDocumentIn.setContract(contract);
        

        final UserLon userAutor = new UserLon();
        userAutor.setId(r.getLong("user_autor_id"));
        userAutor.setPkey(r.getString("user_autor_pkey"));
        userAutor.setPname(r.getString("user_autor_pname"));
        comercialDocumentIn.setUserAutor(userAutor);
        

        final ComercialDocumentTypeIn comercialDocumentType = new ComercialDocumentTypeIn();
        comercialDocumentType.setId(r.getLong("comercial_document_type_id"));
        comercialDocumentType.setPkey(r.getString("comercial_document_type_pkey"));
        comercialDocumentType.setPname(r.getString("comercial_document_type_pname"));
        comercialDocumentIn.setComercialDocumentType(comercialDocumentType);
        

        final ProgramBaseTimePeriod programBaseTimePeriod = new ProgramBaseTimePeriod();
        programBaseTimePeriod.setId(r.getLong("program_base_time_period_id"));
        programBaseTimePeriod.setPkey(r.getString("program_base_time_period_pkey"));
         contract.setProgramBaseTimePeriod(programBaseTimePeriod); 

        final ThirdPerson thirdPerson = new ThirdPerson();
        thirdPerson.setId(r.getLong("third_person_id"));
        thirdPerson.setPkey(r.getString("third_person_pkey"));
        thirdPerson.setPname(r.getString("third_person_pname"));
 contract.setThirdPerson(thirdPerson);   
        return comercialDocumentIn;
    }
    
    @Override
    public ComercialDocumentIn doFromJson(final JsonObject js){
        ComercialDocumentIn comercialDocumentIn = new ComercialDocumentIn();
        comercialDocumentIn.setId(js.getLong("id"));
        
                comercialDocumentIn.setPkey(js.getString("pkey"));
        comercialDocumentIn.setCreatedDate(LocalDateTime.parse(js.getString("createdDate")));
        comercialDocumentIn.setDocumentDate(LocalDateTime.parse(js.getString("documentDate")));
        comercialDocumentIn.setFolio(js.getString("folio"));
        comercialDocumentIn.setPname(js.getString("pname"));
        comercialDocumentIn.setStatus(js.getString("status"));
        comercialDocumentIn.setSupplyDate(LocalDateTime.parse(js.getString("supplyDate")));
        comercialDocumentIn.setId(js.getLong("contract_id"));
        comercialDocumentIn.setId(js.getLong("userAutor_id"));
        comercialDocumentIn.setId(js.getLong("comercialDocumentType_id"));
        return comercialDocumentIn;
    }

    @Override
    public JsonObject toJson(final ComercialDocumentIn o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("createdDate",  o.getCreatedDate() );
        jso.put("documentDate",  o.getDocumentDate() );
        jso.put("folio",  o.getFolio() );
        jso.put("pname",  o.getPname() );
        jso.put("status",  o.getStatus() );
        jso.put("supplyDate",  o.getSupplyDate() );

            final ContractIn contract = o.getContract();
            if(contract!=null){
                jso.put("contract_id", contract.getId());
                jso.put("contract_pkey", contract.getPkey());
            }
            

            final UserLon userAutor = o.getUserAutor();
            if(userAutor!=null){
                jso.put("userAutor_id", userAutor.getId());
                jso.put("userAutor_pkey", userAutor.getPkey());
            }
            

            final ComercialDocumentTypeIn comercialDocumentType = o.getComercialDocumentType();
            if(comercialDocumentType!=null){
                jso.put("comercialDocumentType_id", comercialDocumentType.getId());
                jso.put("comercialDocumentType_pkey", comercialDocumentType.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "comercial_document_in_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "comercial_document_in_pkey");
    slcb.doEqInPSimple( "pkey", "comercial_document_in_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "comercial_document_in_pname");
    slcb.doEqInPSimple( "pname", "comercial_document_in_pname");            
    slcb.doEqInPSimple( "status", "comercial_document_in_status");                    
        
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");  
//ContractIn 1        
        slcb.doIlPSimple2( "contract_pname", "contract_pname");    
        slcb.doIlPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");  
//UserLon 4        
        slcb.doIlPSimple2( "userAutor_pname", "user_autor_pname");    
        slcb.doIlPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doEQPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_id");  
//ComercialDocumentTypeIn 2        
        slcb.doIlPSimple2( "comercialDocumentType_pname", "comercial_document_type_pname");    

        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");//ProgramBaseTimePeriod undefined
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id"); 
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id"); 

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");//ThirdPerson 1
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"comercial_document_in");
        sz0.addField("COUNT(comercial_document_in.id) as c_comercial_document_in_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zContract);               
    sz0.applyG1(zUserAutor);               
    sz0.applyG1(zComercialDocumentType);      
    //level 2
    
    sz0.applyG2(zContract,zProgramBaseTimePeriod);                           
    sz0.applyG2(zContract,zThirdPerson);                           
    //level 3
    
        sz0.applyG3(zContract,zProgramBaseTimePeriod,zBaseTimePeriod);               
        sz0.applyG3(zContract,zProgramBaseTimePeriod,zProgram);               
        return sz0;
    }
}
    
        