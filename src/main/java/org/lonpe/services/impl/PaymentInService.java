
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
 *   PaymentInService 
 * 
 */
  
public class PaymentInService extends AbstractServiceLon<PaymentIn>{

    private static final String SQLINSERT ="INSERT INTO payment_in(pkey,payment_in_form_id,comercial_document_in_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_in SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_in SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_in_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_in_view";
    private static final String SQLKEYS = "SELECT payment_in_pkey FROM payment_in_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_in WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_in WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM payment_in WHERE id = $1 returning id";
    private static final String TABLENAME ="payment_in";
    

    public PaymentInService() {
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
    private static String sql00 = "SELECT payment_in.id as payment_in_id,
payment_in.pkey as payment_in_pkey,
payment_in_form.id as payment_in_form_id,payment_in_form.pkey as payment_in_form_pkey,
monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_in_type.id as payment_in_type_id, payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname,
comercial_document_in.id as comercial_document_in_id,comercial_document_in.pkey as comercial_document_in_pkey,comercial_document_in.pname as comercial_document_in_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname 
  FROM 
  payment_in,
  payment_in_form as payment_in_form,
  monetary_account as monetary_account,
  payment_in_type as payment_in_type,
  comercial_document_in as comercial_document_in,
  contract_in as contract,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  user_lon as user_autor,
  comercial_document_type_in as comercial_document_type  
 WHERE 
 payment_in.payment_in_form_id = payment_in_form.id
 AND payment_in_form.monetary_account_id = monetary_account.id
 AND payment_in_form.payment_in_type_id = payment_in_type.id
 AND payment_in.comercial_document_in_id = comercial_document_in.id
 AND comercial_document_in.contract_id = contract.id
 AND contract.program_base_time_period_id = program_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND comercial_document_in.user_autor_id = user_autor.id
 AND comercial_document_in.comercial_document_type_id = comercial_document_type.id"
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
        
    dcModel.put("dc", "paymentIn");

//ID 
    names.add("id");

    sortMapFields.put("id","payment_in_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","payment_in");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("payment_in.payment_in_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  paymentInForm
    doFieldMT0("payment_in","paymentInForm", "payment_in_form");  

    final JsonObject paymentInForm =  doMto("paymentInForm","paymentInForm");        

    mto.add(paymentInForm);
        

    //1  payment_in_form  -- payment_in_form_id
    final ZtatUnitInfoLon zPaymentInForm = new ZtatUnitInfoLon("payment_in_form_id", "paymentInForm",  "payment_in_form","null","payment_in_form");
    mz1.put("zPaymentInForm", zPaymentInForm);    

//(1)  comercialDocumentIn
    doFieldMT0("payment_in","comercialDocumentIn", "comercial_document_in");  

    final JsonObject comercialDocumentIn =  doMto("comercialDocumentIn","comercialDocumentIn");        
   
    names.add("comercialDocumentIn_pname");
    sortMapFields.put( "comercialDocumentIn_pname", "comercial_document_in_pname");                
    comercialDocumentIn.put("pc","pname");          

    mto.add(comercialDocumentIn);
        

    //1  comercial_document_in  -- comercial_document_in_id
    final ZtatUnitInfoLon zComercialDocumentIn = new ZtatUnitInfoLon("comercial_document_in_id", "comercialDocumentIn",  "comercial_document_in","pname","comercial_document_in");
    mz1.put("zComercialDocumentIn", zComercialDocumentIn);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  monetaryAccount   monetaryAccount  
    names.add("monetaryAccount_id");          
    names.add("monetaryAccount_pkey");

    final JsonObject monetaryAccountFromPaymentInForm =   doMto2("monetaryAccount","monetaryAccount","paymentInForm");        
   
    names.add("monetaryAccount_pname");           
    sortMapFields.put( "monetaryAccount_pname", "monetary_account_pname");  
    monetaryAccountFromPaymentInForm.put("pc","pname");    
         
    mto2.add(monetaryAccountFromPaymentInForm);        

    final ZtatUnitInfoLon2 zMonetaryAccountFromPaymentInForm = new ZtatUnitInfoLon2(zPaymentInForm, "monetary_account_id", "monetaryAccount",  "monetary_account","pname","monetary_account");
    mz2.put("zMonetaryAccountFromPaymentInForm",zMonetaryAccountFromPaymentInForm);

//(2)  paymentInType   paymentInType  
    names.add("paymentInType_id");          
    names.add("paymentInType_pkey");

    final JsonObject paymentInTypeFromPaymentInForm =   doMto2("paymentInType","paymentInType","paymentInForm");        
   
    names.add("paymentInType_pname");           
    sortMapFields.put( "paymentInType_pname", "payment_in_type_pname");  
    paymentInTypeFromPaymentInForm.put("pc","pname");    
         
    mto2.add(paymentInTypeFromPaymentInForm);        

    final ZtatUnitInfoLon2 zPaymentInTypeFromPaymentInForm = new ZtatUnitInfoLon2(zPaymentInForm, "payment_in_type_id", "paymentInType",  "payment_in_type","pname","payment_in_type");
    mz2.put("zPaymentInTypeFromPaymentInForm",zPaymentInTypeFromPaymentInForm);

//(2)  contract   contract  
    names.add("contract_id");          
    names.add("contract_pkey");

    final JsonObject contractFromComercialDocumentIn =   doMto2("contract","contractIn","comercialDocumentIn");        
   
    names.add("contract_pname");           
    sortMapFields.put( "contract_pname", "contract_pname");  
    contractFromComercialDocumentIn.put("pc","pname");    
         
    mto2.add(contractFromComercialDocumentIn);        

    final ZtatUnitInfoLon2 zContractFromComercialDocumentIn = new ZtatUnitInfoLon2(zComercialDocumentIn, "contract_id", "contract",  "contract_in","pname","contract");
    mz2.put("zContractFromComercialDocumentIn",zContractFromComercialDocumentIn);

//(2)  userAutor   userAutor  
    names.add("userAutor_id");          
    names.add("userAutor_pkey");

    final JsonObject userAutorFromComercialDocumentIn =   doMto2("userAutor","userLon","comercialDocumentIn");        
   
    names.add("userAutor_pname");           
    sortMapFields.put( "userAutor_pname", "user_autor_pname");  
    userAutorFromComercialDocumentIn.put("pc","pname");    
         
    mto2.add(userAutorFromComercialDocumentIn);        

    final ZtatUnitInfoLon2 zUserAutorFromComercialDocumentIn = new ZtatUnitInfoLon2(zComercialDocumentIn, "user_autor_id", "userAutor",  "user_lon","pname","user_autor");
    mz2.put("zUserAutorFromComercialDocumentIn",zUserAutorFromComercialDocumentIn);

//(2)  comercialDocumentType   comercialDocumentType  
    names.add("comercialDocumentType_id");          
    names.add("comercialDocumentType_pkey");

    final JsonObject comercialDocumentTypeFromComercialDocumentIn =   doMto2("comercialDocumentType","comercialDocumentTypeIn","comercialDocumentIn");        
   
    names.add("comercialDocumentType_pname");           
    sortMapFields.put( "comercialDocumentType_pname", "comercial_document_type_pname");  
    comercialDocumentTypeFromComercialDocumentIn.put("pc","pname");    
         
    mto2.add(comercialDocumentTypeFromComercialDocumentIn);        

    final ZtatUnitInfoLon2 zComercialDocumentTypeFromComercialDocumentIn = new ZtatUnitInfoLon2(zComercialDocumentIn, "comercial_document_type_id", "comercialDocumentType",  "comercial_document_type_in","pname","comercial_document_type");
    mz2.put("zComercialDocumentTypeFromComercialDocumentIn",zComercialDocumentTypeFromComercialDocumentIn);

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           

//(3)   programBaseTimePeriod   
    names.add("programBaseTimePeriod_id");          
    names.add("programBaseTimePeriod_pkey");

    final JsonObject programBaseTimePeriodFromContractFromComercialDocumentIn =   doMto2("programBaseTimePeriod","programBaseTimePeriod","contract");        
         
    mto3.add(programBaseTimePeriodFromContractFromComercialDocumentIn);        

     
    final ZtatUnitInfoLon3 zProgramBaseTimePeriodFromContractFromComercialDocumentIn = new ZtatUnitInfoLon3(zContractFromComercialDocumentIn, "program_base_time_period_id", "programBaseTimePeriod",  "program_base_time_period","null","program_base_time_period");
    mz3.put("zProgramBaseTimePeriodFromContractFromComercialDocumentIn",zProgramBaseTimePeriodFromContractFromComercialDocumentIn);    

//(3)   thirdPerson   
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPersonFromContractFromComercialDocumentIn =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");            
    sortMapFields.put( "thirdPerson_pname", "third_person_pname"); 
    thirdPersonFromContractFromComercialDocumentIn.put("pc","pname");     
         
    mto3.add(thirdPersonFromContractFromComercialDocumentIn);        

     
    final ZtatUnitInfoLon3 zThirdPersonFromContractFromComercialDocumentIn = new ZtatUnitInfoLon3(zContractFromComercialDocumentIn, "third_person_id", "thirdPerson",  "third_person","pname","third_person");
    mz3.put("zThirdPersonFromContractFromComercialDocumentIn",zThirdPersonFromContractFromComercialDocumentIn);    

    dcModel.put("mto3",mto3);       
        
        
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
        jsa.add(r.getLong("payment_in_id") );       
        jsa.add(r.getString("payment_in_pkey") );
    jsa.add(r.getLong("payment_in_form_id"));
    jsa.add(r.getString("payment_in_form_pkey"));   
    
    jsa.add(r.getLong("comercial_document_in_id"));
    jsa.add(r.getString("comercial_document_in_pkey"));   
    
        
    jsa.add(r.getString("comercial_document_in_pname"));
    jsa.add(r.getLong("monetary_account_id"));
    jsa.add(r.getString("monetary_account_pkey"));
    

    jsa.add(r.getString("monetary_account_pname"));
    
    jsa.add(r.getLong("payment_in_type_id"));
    jsa.add(r.getString("payment_in_type_pkey"));
    

    jsa.add(r.getString("payment_in_type_pname"));
    
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
        m.put("paymentIn_id",LONG);
    }        
//pkey    
    m.put("paymentIn_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// paymentInForm   paymentInForm
    if(withIds){
        m.put("paymentInForm_id",LONG);                       
    }
    m.put("paymentInForm_pkey",STRING);      
// comercialDocumentIn   comercialDocumentIn
    if(withIds){
        m.put("comercialDocumentIn_id",LONG);                       
    }
    m.put("comercialDocumentIn_pkey",STRING);     
    m.put("comercialDocumentIn_pname",STRING);  
//[2] monetaryAccount --   monetaryAccount
    if(withIds){
        m.put("monetaryAccount_id",LONG);              
    }              
    m.put("monetaryAccount_pkey",STRING);  
        
    m.put("monetaryAccount_pname",STRING);  
//[2] paymentInType --   paymentInType
    if(withIds){
        m.put("paymentInType_id",LONG);              
    }              
    m.put("paymentInType_pkey",STRING);  
        
    m.put("paymentInType_pname",STRING);  
//[2] contract --   contract
    if(withIds){
        m.put("contract_id",LONG);              
    }              
    m.put("contract_pkey",STRING);  
        
    m.put("contract_pname",STRING);  
//[2] userAutor --   userAutor
    if(withIds){
        m.put("userAutor_id",LONG);              
    }              
    m.put("userAutor_pkey",STRING);  
        
    m.put("userAutor_pname",STRING);  
//[2] comercialDocumentType --   comercialDocumentType
    if(withIds){
        m.put("comercialDocumentType_id",LONG);              
    }              
    m.put("comercialDocumentType_pkey",STRING);  
        
    m.put("comercialDocumentType_pname",STRING);  
//[3] programBaseTimePeriod --   programBaseTimePeriod
    if(withIds){
        m.put("programBaseTimePeriod_id",LONG);              
    }              
    m.put("programBaseTimePeriod_pkey",STRING);  
        
//[3] thirdPerson --   thirdPerson
    if(withIds){
        m.put("thirdPerson_id",LONG);              
    }              
    m.put("thirdPerson_pkey",STRING);  
        
    m.put("thirdPerson_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"payment_in_id", nc++); 
    }            //pkey       
            sToCell(r, row,"payment_in_pkey", nc++); 
//paymentInForm   payment_in_form        
    if(withIds){
        lToCell(r, row,"payment_in_form_id", nc++);
    }
    sToCell(r, row,"payment_in_form_pkey", nc++);
//comercialDocumentIn   comercial_document_in        
    if(withIds){
        lToCell(r, row,"comercial_document_in_id", nc++);
    }
    sToCell(r, row,"comercial_document_in_pkey", nc++);
    sToCell(r, row,"comercial_document_in_pname", nc++);
// monetaryAccount  monetary_account
    if(withIds){
       lToCell(r, row,"monetary_account_id", nc++);
    }

    sToCell(r, row,"monetary_account_pkey", nc++);

    sToCell(r, row,"monetary_account_pname", nc++);
// paymentInType  payment_in_type
    if(withIds){
       lToCell(r, row,"payment_in_type_id", nc++);
    }

    sToCell(r, row,"payment_in_type_pkey", nc++);

    sToCell(r, row,"payment_in_type_pname", nc++);
// contract  contract
    if(withIds){
       lToCell(r, row,"contract_id", nc++);
    }

    sToCell(r, row,"contract_pkey", nc++);

    sToCell(r, row,"contract_pname", nc++);
// userAutor  user_autor
    if(withIds){
       lToCell(r, row,"user_autor_id", nc++);
    }

    sToCell(r, row,"user_autor_pkey", nc++);

    sToCell(r, row,"user_autor_pname", nc++);
// comercialDocumentType  comercial_document_type
    if(withIds){
       lToCell(r, row,"comercial_document_type_id", nc++);
    }

    sToCell(r, row,"comercial_document_type_pkey", nc++);

    sToCell(r, row,"comercial_document_type_pname", nc++);
// programBaseTimePeriod  program_base_time_period
    if(withIds){
       lToCell(r, row,"program_base_time_period_id", nc++);
    }

    sToCell(r, row,"program_base_time_period_pkey", nc++);
// thirdPerson  third_person
    if(withIds){
       lToCell(r, row,"third_person_id", nc++);
    }

    sToCell(r, row,"third_person_pkey", nc++);

    sToCell(r, row,"third_person_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE payment_in_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentIn dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getPaymentInForm()!=null){
       t.addLong(dc0.getPaymentInForm().getId());
    }   
    if(dc0.getComercialDocumentIn()!=null){
       t.addLong(dc0.getComercialDocumentIn().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final PaymentIn dc0, final Tuple t){
           
//      if(dc0.getPaymentInForm()!=null){
//            t.addLong(dc0.getPaymentInForm().getId());
//      }   
//      if(dc0.getComercialDocumentIn()!=null){
//            t.addLong(dc0.getComercialDocumentIn().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("paymentInForm_id",obj,t);

    fTLong("comercialDocumentIn_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> paymentInForm = mapParents.get("paymentInForm");
            final Long paymentInForm_id = paymentInForm.get((String)obj.get("paymentInForm_pkey"));
            obj.put("paymentInForm_id", paymentInForm_id);
      
            final Map<String, Long> comercialDocumentIn = mapParents.get("comercialDocumentIn");
            final Long comercialDocumentIn_id = comercialDocumentIn.get((String)obj.get("comercialDocumentIn_pkey"));
            obj.put("comercialDocumentIn_id", comercialDocumentIn_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("paymentInForm_id",js,t);     
    fTLong("comercialDocumentIn_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("paymentInForm_id",js,t);

            //     fTLong("comercialDocumentIn_id",js,t);
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
    public PaymentIn doFrom(final Row r){
        final PaymentIn paymentIn = new PaymentIn();
         paymentIn.setId(r.getLong("payment_in_id"));         
                paymentIn.setPkey(  r.getString("payment_in_pkey"));              
        final PaymentInForm paymentInForm = new PaymentInForm();
        paymentInForm.setId(r.getLong("payment_in_form_id"));
        paymentInForm.setPkey(r.getString("payment_in_form_pkey"));
        
        paymentIn.setPaymentInForm(paymentInForm);
        
        final ComercialDocumentIn comercialDocumentIn = new ComercialDocumentIn();
        comercialDocumentIn.setId(r.getLong("comercial_document_in_id"));
        comercialDocumentIn.setPkey(r.getString("comercial_document_in_pkey"));
        
        comercialDocumentIn.setPname(r.getString("comercial_document_in_pname"));
        paymentIn.setComercialDocumentIn(comercialDocumentIn);
        
        final MonetaryAccount monetaryAccount = new MonetaryAccount();
        monetaryAccount.setId(r.getLong("monetary_account_id"));
        monetaryAccount.setPkey(r.getString("monetary_account_pkey"));
        monetaryAccount.setPname(r.getString("monetary_account_pname"));
 
        paymentInForm.setMonetaryAccount(monetaryAccount); 
        final PaymentInType paymentInType = new PaymentInType();
        paymentInType.setId(r.getLong("payment_in_type_id"));
        paymentInType.setPkey(r.getString("payment_in_type_pkey"));
        paymentInType.setPname(r.getString("payment_in_type_pname"));
 
        paymentInForm.setPaymentInType(paymentInType); 
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
        return paymentIn;
    }
    
    @Override
    public PaymentIn doFromJson(final JsonObject js){
        PaymentIn paymentIn = new PaymentIn();
        paymentIn.setId(js.getLong("id"));
        
                
                paymentIn.setPkey(js.getString("pkey"));        
            paymentIn.setId(js.getLong("paymentInForm_id"));        
            paymentIn.setId(js.getLong("comercialDocumentIn_id"));
        return paymentIn;
    }

    @Override
    public JsonObject toJson(final PaymentIn o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final PaymentInForm paymentInForm = o.getPaymentInForm();
            if(paymentInForm!=null){
                jso.put("paymentInForm_id", paymentInForm.getId());
                jso.put("paymentInForm_pkey", paymentInForm.getPkey());
            }
            

            final ComercialDocumentIn comercialDocumentIn = o.getComercialDocumentIn();
            if(comercialDocumentIn!=null){
                jso.put("comercialDocumentIn_id", comercialDocumentIn.getId());
                jso.put("comercialDocumentIn_pkey", comercialDocumentIn.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "payment_in_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "payment_in_pkey");
    slcb.doEqInPSimple( "pkey", "payment_in_pkey");
        
        slcb.doIlPSimple2( "paymentInForm_pkey", "payment_in_form_pkey");
        slcb.doEQPSimple2( "paymentInForm_pkey", "payment_in_form_pkey");
        slcb.doInLongCondition("paymentInForm_id", "payment_in_form_id");  
//PaymentInForm undefined        --
        slcb.doIlPSimple2( "comercialDocumentIn_pkey", "comercial_document_in_pkey");
        slcb.doEQPSimple2( "comercialDocumentIn_pkey", "comercial_document_in_pkey");
        slcb.doInLongCondition("comercialDocumentIn_id", "comercial_document_in_id");  
//ComercialDocumentIn 4        --
        slcb.doIlPSimple2( "comercialDocumentIn_pname", "comercial_document_in_pname");    
        
        slcb.doIlPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");
//MonetaryAccount 1
        slcb.doIlPSimple2( "monetaryAccount_pname", "monetary_account_pname"); 
        slcb.doIlPSimple2( "paymentInType_pkey", "payment_in_type_pkey");
        slcb.doEQPSimple2( "paymentInType_pkey", "payment_in_type_pkey");
        slcb.doInLongCondition("paymentInType_id", "payment_in_type_id");
//PaymentInType 1
        slcb.doIlPSimple2( "paymentInType_pname", "payment_in_type_pname"); 
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");
//ContractIn 1
        slcb.doIlPSimple2( "contract_pname", "contract_pname"); 
        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id"); 
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id"); 
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

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"payment_in");
        sz0.addField("COUNT(payment_in.id) as c_payment_in_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zPaymentInForm"))   ;               
    sz0.applyG1(mz1.get("zComercialDocumentIn"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zMonetaryAccountFromPaymentInForm"));                           
    sz0.applyG2(mz2.get("zPaymentInTypeFromPaymentInForm"));                           
    sz0.applyG2(mz2.get("zContractFromComercialDocumentIn"));                           
    sz0.applyG2(mz2.get("zUserAutorFromComercialDocumentIn"));                           
    sz0.applyG2(mz2.get("zComercialDocumentTypeFromComercialDocumentIn"));                           
//level 3    
        sz0.applyG3(mz3.get("zProgramBaseTimePeriodFromContractFromComercialDocumentIn"));               
        sz0.applyG3(mz3.get("zThirdPersonFromContractFromComercialDocumentIn"));               
        return sz0;
    }
}
    
        