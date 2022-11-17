
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

import java.time.LocalDateTime;

import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon2;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon3;

/**
 *   ComercialDocumentOutService 
 * 
 */
  
public class ComercialDocumentOutService extends AbstractServiceLon<ComercialDocumentOut>{

    private static final String SQLINSERT ="INSERT INTO comercial_document_out(pkey,created_date,document_date,folio,pname,status,supply_date,contract_id,user_autor_id,comercial_document_type_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE comercial_document_out SET created_date = $1,document_date = $2,folio = $3,pname = $4,status = $5,supply_date = $6 WHERE id = $13 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE comercial_document_out SET created_date = $1,document_date = $2,folio = $3,pname = $4,status = $5,supply_date = $6 WHERE pkey = $13 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM comercial_document_out_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM comercial_document_out_view";
    private static final String SQLKEYS = "SELECT comercial_document_out_pkey FROM comercial_document_out_view";
    private static final String SQLIDBYPKEY = "SELECT id from comercial_document_out WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from comercial_document_out WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM comercial_document_out WHERE id = $1 returning id";
    private static final String TABLENAME ="comercial_document_out";
    

    public ComercialDocumentOutService() {
        init0();
    }

    
    private static final Map<String, ZtatUnitInfoLon> mz1 = new HashMap<>(6);                       
    private static final Map<String, ZtatUnitInfoLon2> mz2 = new HashMap<>(6);                       
    private static final Map<String, ZtatUnitInfoLon3> mz3 = new HashMap<>(6);                       

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
    private static String sql00 = "SELECT comercial_document_out.id as comercial_document_out_id,
comercial_document_out.pkey as comercial_document_out_pkey,
comercial_document_out.created_date as comercial_document_out_created_date,
comercial_document_out.document_date as comercial_document_out_document_date,
comercial_document_out.folio as comercial_document_out_folio,
comercial_document_out.pname as comercial_document_out_pname,
comercial_document_out.status as comercial_document_out_status,
comercial_document_out.supply_date as comercial_document_out_supply_date,
contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id,comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname 
  FROM 
  comercial_document_out,
  contract_out as contract,
  departament_base_time_period as departament_base_time_period,
  base_time_period as base_time_period,
  departament as departament,
  third_person as third_person,
  user_lon as user_autor,
  comercial_document_type_out as comercial_document_type  
 WHERE 
 comercial_document_out.contract_id = contract.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND departament_base_time_period.departament_id = departament.id
 AND contract.third_person_id = third_person.id
 AND comercial_document_out.user_autor_id = user_autor.id
 AND comercial_document_out.comercial_document_type_id = comercial_document_type.id"
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
        
    dcModel.put("dc", "comercialDocumentOut");

//ID 
    names.add("id");

    sortMapFields.put("id","comercial_document_out_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","comercial_document_out");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("comercial_document_out.comercial_document_out_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//createdDate
    doFieldSort("createdDate","created_date","comercial_document_out");               

//Create property createdDate       
    final JsonObject createdDate = psLocalDateTime("createdDate",true);
 
//Set by system
    createdDate.put("setBySys","now");  
 
    ps.add(createdDate);
 
//documentDate
    doFieldSort("documentDate","document_date","comercial_document_out");               

//Create property documentDate       
    final JsonObject documentDate = psLocalDateTime("documentDate",false);
 
    ps.add(documentDate);
 
//folio
    doField("folio","folio","comercial_document_out");               

//Create property folio       
    final JsonObject folio = psString("folio",true);
 
    ps.add(folio);
 
//pname
    doFieldSort("pname","pname","comercial_document_out");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//status
    doField("status","status","comercial_document_out");               

//Create property status       
    final JsonObject status = psString("status",true);

    final JsonArray statusInList = new JsonArray();
                statusInList.add("PENDENT"); 
statusInList.add("SUPPLIED"); 
statusInList.add("CANCEL"); 
    status.put("inList",statusInList );                
 
    ps.add(status);
 
//supplyDate
    doFieldSort("supplyDate","supply_date","comercial_document_out");               

//Create property supplyDate       
    final JsonObject supplyDate = psLocalDateTime("supplyDate",false);
 
    ps.add(supplyDate);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  contract
    doFieldMT0("comercial_document_out","contract", "contract");  

    final JsonObject contract =  doMto("contract","contractOut");        
   
    names.add("contract_pname");
    sortMapFields.put( "contract_pname", "contract_pname");                
    contract.put("pc","pname");          

    mto.add(contract);
        

    //1  contract  -- contract_id
    final ZtatUnitInfoLon zContract = new ZtatUnitInfoLon("contract_id", "contract",  "contract_out","pname","contract");
    mz1.put("zContract", zContract);    

//(1)  userAutor
    doFieldMT0("comercial_document_out","userAutor", "user_autor");  

    final JsonObject userAutor =  doMto("userAutor","userLon");        
   
    names.add("userAutor_pname");
    sortMapFields.put( "userAutor_pname", "user_autor_pname");                
    userAutor.put("pc","pname");          

    userAutor.put("setBySys","putCurrentUser");           

    mto.add(userAutor);
        

    //1  user_autor  -- user_autor_id
    final ZtatUnitInfoLon zUserAutor = new ZtatUnitInfoLon("user_autor_id", "userAutor",  "user_lon","pname","user_autor");
    mz1.put("zUserAutor", zUserAutor);    

//(1)  comercialDocumentType
    doFieldMT0("comercial_document_out","comercialDocumentType", "comercial_document_type");  

    final JsonObject comercialDocumentType =  doMto("comercialDocumentType","comercialDocumentTypeOut");        
   
    names.add("comercialDocumentType_pname");
    sortMapFields.put( "comercialDocumentType_pname", "comercial_document_type_pname");                
    comercialDocumentType.put("pc","pname");          

    comercialDocumentType.put("onForm","getAll");           

    mto.add(comercialDocumentType);
        

    //1  comercial_document_type  -- comercial_document_type_id
    final ZtatUnitInfoLon zComercialDocumentType = new ZtatUnitInfoLon("comercial_document_type_id", "comercialDocumentType",  "comercial_document_type_out","pname","comercial_document_type");
    mz1.put("zComercialDocumentType", zComercialDocumentType);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  departamentBaseTimePeriod   departamentBaseTimePeriod  
    names.add("departamentBaseTimePeriod_id");          
    names.add("departamentBaseTimePeriod_pkey");

    final JsonObject departamentBaseTimePeriodFromContract =   doMto2("departamentBaseTimePeriod","departamentBaseTimePeriod","contract");        
         
    mto2.add(departamentBaseTimePeriodFromContract);        

    final ZtatUnitInfoLon2 zDepartamentBaseTimePeriodFromContract = new ZtatUnitInfoLon2(zContract, "departament_base_time_period_id", "departamentBaseTimePeriod",  "departament_base_time_period","null","departament_base_time_period");
    mz2.put("zDepartamentBaseTimePeriodFromContract",zDepartamentBaseTimePeriodFromContract);

//(2)  thirdPerson   thirdPerson  
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPersonFromContract =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");           
    sortMapFields.put( "thirdPerson_pname", "third_person_pname");  
    thirdPersonFromContract.put("pc","pname");    
         
    mto2.add(thirdPersonFromContract);        

    final ZtatUnitInfoLon2 zThirdPersonFromContract = new ZtatUnitInfoLon2(zContract, "third_person_id", "thirdPerson",  "third_person","pname","third_person");
    mz2.put("zThirdPersonFromContract",zThirdPersonFromContract);

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           

//(3)   baseTimePeriod   
    names.add("baseTimePeriod_id");          
    names.add("baseTimePeriod_pkey");

    final JsonObject baseTimePeriodFromDepartamentBaseTimePeriodFromContract =   doMto2("baseTimePeriod","baseTimePeriod","departamentBaseTimePeriod");        
         
    mto3.add(baseTimePeriodFromDepartamentBaseTimePeriodFromContract);        

     
    final ZtatUnitInfoLon3 zBaseTimePeriodFromDepartamentBaseTimePeriodFromContract = new ZtatUnitInfoLon3(zDepartamentBaseTimePeriodFromContract, "base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");
    mz3.put("zBaseTimePeriodFromDepartamentBaseTimePeriodFromContract",zBaseTimePeriodFromDepartamentBaseTimePeriodFromContract);    

//(3)   departament   
    names.add("departament_id");          
    names.add("departament_pkey");

    final JsonObject departamentFromDepartamentBaseTimePeriodFromContract =   doMto2("departament","departament","departamentBaseTimePeriod");        
   
    names.add("departament_pname");            
    sortMapFields.put( "departament_pname", "departament_pname"); 
    departamentFromDepartamentBaseTimePeriodFromContract.put("pc","pname");     
         
    mto3.add(departamentFromDepartamentBaseTimePeriodFromContract);        

     
    final ZtatUnitInfoLon3 zDepartamentFromDepartamentBaseTimePeriodFromContract = new ZtatUnitInfoLon3(zDepartamentBaseTimePeriodFromContract, "departament_id", "departament",  "departament","pname","departament");
    mz3.put("zDepartamentFromDepartamentBaseTimePeriodFromContract",zDepartamentFromDepartamentBaseTimePeriodFromContract);    

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

//ON RELATION comercialDocument    
            applyOtm(otm,"invoiceLines","invoiceLineIn","comercialDocument"); 
                

        applyOtm(otm,"payments","paymentOut"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  
        
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
        jsa.add(r.getLong("comercial_document_out_id") );       
        jsa.add(r.getString("comercial_document_out_pkey") );
        jsa.add(r.getLocalDate("comercial_document_out_created_date").toString() ); // undefined 
        asMaybeNullLocalDate(r,"comercial_document_out_document_date",jsa); //true       
        jsa.add(r.getString("comercial_document_out_folio") );       
        jsa.add(r.getString("comercial_document_out_pname") );       
        jsa.add(r.getString("comercial_document_out_status") ); 
        asMaybeNullLocalDate(r,"comercial_document_out_supply_date",jsa); //true
    jsa.add(r.getLong("contract_id"));
    jsa.add(r.getString("contract_pkey"));   
    
        
    jsa.add(r.getString("contract_pname"));
    jsa.add(r.getLong("user_autor_id"));
    jsa.add(r.getString("user_autor_pkey"));   
    
        
    jsa.add(r.getString("user_autor_pname"));
    jsa.add(r.getLong("comercial_document_type_id"));
    jsa.add(r.getString("comercial_document_type_pkey"));   
    
        
    jsa.add(r.getString("comercial_document_type_pname"));
    jsa.add(r.getLong("departament_base_time_period_id"));
    jsa.add(r.getString("departament_base_time_period_pkey"));
    
    jsa.add(r.getLong("third_person_id"));
    jsa.add(r.getString("third_person_pkey"));
    

    jsa.add(r.getString("third_person_pname"));
    
    jsa.add(r.getLong("base_time_period_id"));
    jsa.add(r.getString("base_time_period_pkey"));
    
    jsa.add(r.getLong("departament_id"));
    jsa.add(r.getString("departament_pkey"));
    

    jsa.add(r.getString("departament_pname"));
    
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
        m.put("comercialDocumentOut_id",LONG);
    }        
//pkey    
    m.put("comercialDocumentOut_pkey",STRING);              
//createdDate    
    m.put("comercialDocumentOut_createdDate",LOCALDATETIME);              
//documentDate    
    m.put("comercialDocumentOut_documentDate",LOCALDATETIME);              
//folio    
    m.put("comercialDocumentOut_folio",STRING);              
//pname    
    m.put("comercialDocumentOut_pname",STRING);              
//status    
    m.put("comercialDocumentOut_status",STRING);              
//supplyDate    
    m.put("comercialDocumentOut_supplyDate",LOCALDATETIME);          
    if(level<1){
        return m;    
    }       
// contract   contract
    if(withIds){
        m.put("contract_id",LONG);                       
    }
    m.put("contract_pkey",STRING);     
    m.put("contract_pname",STRING);   
// userAutor   userAutor
    if(withIds){
        m.put("userAutor_id",LONG);                       
    }
    m.put("userAutor_pkey",STRING);     
    m.put("userAutor_pname",STRING);   
// comercialDocumentType   comercialDocumentType
    if(withIds){
        m.put("comercialDocumentType_id",LONG);                       
    }
    m.put("comercialDocumentType_pkey",STRING);     
    m.put("comercialDocumentType_pname",STRING);  
//[2] departamentBaseTimePeriod --   departamentBaseTimePeriod
    if(withIds){
        m.put("departamentBaseTimePeriod_id",LONG);              
    }              
    m.put("departamentBaseTimePeriod_pkey",STRING);  
        
//[2] thirdPerson --   thirdPerson
    if(withIds){
        m.put("thirdPerson_id",LONG);              
    }              
    m.put("thirdPerson_pkey",STRING);  
        
    m.put("thirdPerson_pname",STRING);  
//[3] baseTimePeriod --   baseTimePeriod
    if(withIds){
        m.put("baseTimePeriod_id",LONG);              
    }              
    m.put("baseTimePeriod_pkey",STRING);  
        
//[3] departament --   departament
    if(withIds){
        m.put("departament_id",LONG);              
    }              
    m.put("departament_pkey",STRING);  
        
    m.put("departament_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"comercial_document_out_id", nc++); 
    }            //pkey       
            sToCell(r, row,"comercial_document_out_pkey", nc++);     //createdDate            
            ldtToCell(r, row,"comercial_document_out_created_date", nc++);     //documentDate            
            ldtToCell(r, row,"comercial_document_out_document_date", nc++);     //folio       
            sToCell(r, row,"comercial_document_out_folio", nc++);     //pname       
            sToCell(r, row,"comercial_document_out_pname", nc++);     //status       
            sToCell(r, row,"comercial_document_out_status", nc++);     //supplyDate            
            ldtToCell(r, row,"comercial_document_out_supply_date", nc++); 
//contract   contract        
    if(withIds){
        lToCell(r, row,"contract_id", nc++);
    }
    sToCell(r, row,"contract_pkey", nc++);
    sToCell(r, row,"contract_pname", nc++);
//userAutor   user_autor        
    if(withIds){
        lToCell(r, row,"user_autor_id", nc++);
    }
    sToCell(r, row,"user_autor_pkey", nc++);
    sToCell(r, row,"user_autor_pname", nc++);
//comercialDocumentType   comercial_document_type        
    if(withIds){
        lToCell(r, row,"comercial_document_type_id", nc++);
    }
    sToCell(r, row,"comercial_document_type_pkey", nc++);
    sToCell(r, row,"comercial_document_type_pname", nc++);
// departamentBaseTimePeriod  departament_base_time_period
    if(withIds){
       lToCell(r, row,"departament_base_time_period_id", nc++);
    }

    sToCell(r, row,"departament_base_time_period_pkey", nc++);
// thirdPerson  third_person
    if(withIds){
       lToCell(r, row,"third_person_id", nc++);
    }

    sToCell(r, row,"third_person_pkey", nc++);

    sToCell(r, row,"third_person_pname", nc++);
// baseTimePeriod  base_time_period
    if(withIds){
       lToCell(r, row,"base_time_period_id", nc++);
    }

    sToCell(r, row,"base_time_period_pkey", nc++);
// departament  departament
    if(withIds){
       lToCell(r, row,"departament_id", nc++);
    }

    sToCell(r, row,"departament_pkey", nc++);

    sToCell(r, row,"departament_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE comercial_document_out_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ComercialDocumentOut dc0, final Tuple t){
                
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
    public void fillTupleUpdate(final ComercialDocumentOut dc0, final Tuple t){
        
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
    public ComercialDocumentOut doFrom(final Row r){
        final ComercialDocumentOut comercialDocumentOut = new ComercialDocumentOut();
         comercialDocumentOut.setId(r.getLong("comercial_document_out_id"));         
                comercialDocumentOut.setPkey(  r.getString("comercial_document_out_pkey"));                       
                comercialDocumentOut.setCreatedDate(  r.getLocalDateTime("comercial_document_out_created_date"));                       
                comercialDocumentOut.setDocumentDate(  r.getLocalDateTime("comercial_document_out_document_date"));                       
                comercialDocumentOut.setFolio(  r.getString("comercial_document_out_folio"));                       
                comercialDocumentOut.setPname(  r.getString("comercial_document_out_pname"));                       
                comercialDocumentOut.setStatus(  r.getString("comercial_document_out_status"));                       
                comercialDocumentOut.setSupplyDate(  r.getLocalDateTime("comercial_document_out_supply_date"));              
        final ContractOut contract = new ContractOut();
        contract.setId(r.getLong("contract_id"));
        contract.setPkey(r.getString("contract_pkey"));
        
        contract.setPname(r.getString("contract_pname"));
        comercialDocumentOut.setContract(contract);
        
        final UserLon userAutor = new UserLon();
        userAutor.setId(r.getLong("user_autor_id"));
        userAutor.setPkey(r.getString("user_autor_pkey"));
        
        userAutor.setPname(r.getString("user_autor_pname"));
        comercialDocumentOut.setUserAutor(userAutor);
        
        final ComercialDocumentTypeOut comercialDocumentType = new ComercialDocumentTypeOut();
        comercialDocumentType.setId(r.getLong("comercial_document_type_id"));
        comercialDocumentType.setPkey(r.getString("comercial_document_type_pkey"));
        
        comercialDocumentType.setPname(r.getString("comercial_document_type_pname"));
        comercialDocumentOut.setComercialDocumentType(comercialDocumentType);
        
        final DepartamentBaseTimePeriod departamentBaseTimePeriod = new DepartamentBaseTimePeriod();
        departamentBaseTimePeriod.setId(r.getLong("departament_base_time_period_id"));
        departamentBaseTimePeriod.setPkey(r.getString("departament_base_time_period_pkey"));
         
        contract.setDepartamentBaseTimePeriod(departamentBaseTimePeriod); 
        final ThirdPerson thirdPerson = new ThirdPerson();
        thirdPerson.setId(r.getLong("third_person_id"));
        thirdPerson.setPkey(r.getString("third_person_pkey"));
        thirdPerson.setPname(r.getString("third_person_pname"));
 
        contract.setThirdPerson(thirdPerson);   
        return comercialDocumentOut;
    }
    
    @Override
    public ComercialDocumentOut doFromJson(final JsonObject js){
        ComercialDocumentOut comercialDocumentOut = new ComercialDocumentOut();
        comercialDocumentOut.setId(js.getLong("id"));
        
                
                comercialDocumentOut.setPkey(js.getString("pkey"));        
                comercialDocumentOut.setCreatedDate(LocalDateTime.parse(js.getString("createdDate")));        
                comercialDocumentOut.setDocumentDate(LocalDateTime.parse(js.getString("documentDate")));        
                comercialDocumentOut.setFolio(js.getString("folio"));        
                comercialDocumentOut.setPname(js.getString("pname"));        
                comercialDocumentOut.setStatus(js.getString("status"));        
                comercialDocumentOut.setSupplyDate(LocalDateTime.parse(js.getString("supplyDate")));        
            comercialDocumentOut.setId(js.getLong("contract_id"));        
            comercialDocumentOut.setId(js.getLong("userAutor_id"));        
            comercialDocumentOut.setId(js.getLong("comercialDocumentType_id"));
        return comercialDocumentOut;
    }

    @Override
    public JsonObject toJson(final ComercialDocumentOut o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("createdDate",  o.getCreatedDate() );
        jso.put("documentDate",  o.getDocumentDate() );
        jso.put("folio",  o.getFolio() );
        jso.put("pname",  o.getPname() );
        jso.put("status",  o.getStatus() );
        jso.put("supplyDate",  o.getSupplyDate() );

            final ContractOut contract = o.getContract();
            if(contract!=null){
                jso.put("contract_id", contract.getId());
                jso.put("contract_pkey", contract.getPkey());
            }
            

            final UserLon userAutor = o.getUserAutor();
            if(userAutor!=null){
                jso.put("userAutor_id", userAutor.getId());
                jso.put("userAutor_pkey", userAutor.getPkey());
            }
            

            final ComercialDocumentTypeOut comercialDocumentType = o.getComercialDocumentType();
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
       slcb.doInLongCondition("id", "comercial_document_out_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "comercial_document_out_pkey");
    slcb.doEqInPSimple( "pkey", "comercial_document_out_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "comercial_document_out_pname");
    slcb.doEqInPSimple( "pname", "comercial_document_out_pname");            
    slcb.doEqInPSimple( "status", "comercial_document_out_status");                    
        
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");  
//ContractOut 1        --
        slcb.doIlPSimple2( "contract_pname", "contract_pname");    
        
        slcb.doIlPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");  
//UserLon 4        --
        slcb.doIlPSimple2( "userAutor_pname", "user_autor_pname");    
        
        slcb.doIlPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doEQPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_id");  
//ComercialDocumentTypeOut 2        --
        slcb.doIlPSimple2( "comercialDocumentType_pname", "comercial_document_type_pname");    
        
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");
//DepartamentBaseTimePeriod undefined
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id"); 
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id"); 
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");
//ThirdPerson 1
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"comercial_document_out");
        sz0.addField("COUNT(comercial_document_out.id) as c_comercial_document_out_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zContract"))   ;               
    sz0.applyG1(mz1.get("zUserAutor"))   ;               
    sz0.applyG1(mz1.get("zComercialDocumentType"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zDepartamentBaseTimePeriodFromContract"));                           
    sz0.applyG2(mz2.get("zThirdPersonFromContract"));                           
//level 3    
        sz0.applyG3(mz3.get("zBaseTimePeriodFromDepartamentBaseTimePeriodFromContract"));               
        sz0.applyG3(mz3.get("zDepartamentFromDepartamentBaseTimePeriodFromContract"));               
        return sz0;
    }
}
    
        