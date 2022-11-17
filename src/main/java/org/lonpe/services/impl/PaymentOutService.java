
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
 *   PaymentOutService 
 * 
 */
  
public class PaymentOutService extends AbstractServiceLon<PaymentOut>{

    private static final String SQLINSERT ="INSERT INTO payment_out(pkey,payment_out_form_id,comercial_document_out_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_out SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_out SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_out_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_out_view";
    private static final String SQLKEYS = "SELECT payment_out_pkey FROM payment_out_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_out WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_out WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM payment_out WHERE id = $1 returning id";
    private static final String TABLENAME ="payment_out";
    

    public PaymentOutService() {
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
    private static String sql00 = "SELECT payment_out.id as payment_out_id,
payment_out.pkey as payment_out_pkey,
payment_out_form.id as payment_out_form_id,payment_out_form.pkey as payment_out_form_pkey,
monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_out_type.id as payment_out_type_id, payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as payment_out_type_pname,
comercial_document_out.id as comercial_document_out_id,comercial_document_out.pkey as comercial_document_out_pkey,comercial_document_out.pname as comercial_document_out_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname 
  FROM 
  payment_out,
  payment_out_form as payment_out_form,
  monetary_account as monetary_account,
  payment_out_type as payment_out_type,
  comercial_document_out as comercial_document_out,
  contract_out as contract,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  user_lon as user_autor,
  comercial_document_type_out as comercial_document_type  
 WHERE 
 payment_out.payment_out_form_id = payment_out_form.id
 AND payment_out_form.monetary_account_id = monetary_account.id
 AND payment_out_form.payment_out_type_id = payment_out_type.id
 AND payment_out.comercial_document_out_id = comercial_document_out.id
 AND comercial_document_out.contract_id = contract.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
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
        
    dcModel.put("dc", "paymentOut");

//ID 
    names.add("id");

    sortMapFields.put("id","payment_out_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","payment_out");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("payment_out.payment_out_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  paymentOutForm
    doFieldMT0("payment_out","paymentOutForm", "payment_out_form");  

    final JsonObject paymentOutForm =  doMto("paymentOutForm","paymentOutForm");        

    mto.add(paymentOutForm);
        

    //1  payment_out_form  -- payment_out_form_id
    final ZtatUnitInfoLon zPaymentOutForm = new ZtatUnitInfoLon("payment_out_form_id", "paymentOutForm",  "payment_out_form","null","payment_out_form");
    mz1.put("zPaymentOutForm", zPaymentOutForm);    

//(1)  comercialDocumentOut
    doFieldMT0("payment_out","comercialDocumentOut", "comercial_document_out");  

    final JsonObject comercialDocumentOut =  doMto("comercialDocumentOut","comercialDocumentOut");        
   
    names.add("comercialDocumentOut_pname");
    sortMapFields.put( "comercialDocumentOut_pname", "comercial_document_out_pname");                
    comercialDocumentOut.put("pc","pname");          

    mto.add(comercialDocumentOut);
        

    //1  comercial_document_out  -- comercial_document_out_id
    final ZtatUnitInfoLon zComercialDocumentOut = new ZtatUnitInfoLon("comercial_document_out_id", "comercialDocumentOut",  "comercial_document_out","pname","comercial_document_out");
    mz1.put("zComercialDocumentOut", zComercialDocumentOut);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  monetaryAccount   monetaryAccount  
    names.add("monetaryAccount_id");          
    names.add("monetaryAccount_pkey");

    final JsonObject monetaryAccountFromPaymentOutForm =   doMto2("monetaryAccount","monetaryAccount","paymentOutForm");        
   
    names.add("monetaryAccount_pname");           
    sortMapFields.put( "monetaryAccount_pname", "monetary_account_pname");  
    monetaryAccountFromPaymentOutForm.put("pc","pname");    
         
    mto2.add(monetaryAccountFromPaymentOutForm);        

    final ZtatUnitInfoLon2 zMonetaryAccountFromPaymentOutForm = new ZtatUnitInfoLon2(zPaymentOutForm, "monetary_account_id", "monetaryAccount",  "monetary_account","pname","monetary_account");
    mz2.put("zMonetaryAccountFromPaymentOutForm",zMonetaryAccountFromPaymentOutForm);

//(2)  paymentOutType   paymentOutType  
    names.add("paymentOutType_id");          
    names.add("paymentOutType_pkey");

    final JsonObject paymentOutTypeFromPaymentOutForm =   doMto2("paymentOutType","paymentOutType","paymentOutForm");        
   
    names.add("paymentOutType_pname");           
    sortMapFields.put( "paymentOutType_pname", "payment_out_type_pname");  
    paymentOutTypeFromPaymentOutForm.put("pc","pname");    
         
    mto2.add(paymentOutTypeFromPaymentOutForm);        

    final ZtatUnitInfoLon2 zPaymentOutTypeFromPaymentOutForm = new ZtatUnitInfoLon2(zPaymentOutForm, "payment_out_type_id", "paymentOutType",  "payment_out_type","pname","payment_out_type");
    mz2.put("zPaymentOutTypeFromPaymentOutForm",zPaymentOutTypeFromPaymentOutForm);

//(2)  contract   contract  
    names.add("contract_id");          
    names.add("contract_pkey");

    final JsonObject contractFromComercialDocumentOut =   doMto2("contract","contractOut","comercialDocumentOut");        
   
    names.add("contract_pname");           
    sortMapFields.put( "contract_pname", "contract_pname");  
    contractFromComercialDocumentOut.put("pc","pname");    
         
    mto2.add(contractFromComercialDocumentOut);        

    final ZtatUnitInfoLon2 zContractFromComercialDocumentOut = new ZtatUnitInfoLon2(zComercialDocumentOut, "contract_id", "contract",  "contract_out","pname","contract");
    mz2.put("zContractFromComercialDocumentOut",zContractFromComercialDocumentOut);

//(2)  userAutor   userAutor  
    names.add("userAutor_id");          
    names.add("userAutor_pkey");

    final JsonObject userAutorFromComercialDocumentOut =   doMto2("userAutor","userLon","comercialDocumentOut");        
   
    names.add("userAutor_pname");           
    sortMapFields.put( "userAutor_pname", "user_autor_pname");  
    userAutorFromComercialDocumentOut.put("pc","pname");    
         
    mto2.add(userAutorFromComercialDocumentOut);        

    final ZtatUnitInfoLon2 zUserAutorFromComercialDocumentOut = new ZtatUnitInfoLon2(zComercialDocumentOut, "user_autor_id", "userAutor",  "user_lon","pname","user_autor");
    mz2.put("zUserAutorFromComercialDocumentOut",zUserAutorFromComercialDocumentOut);

//(2)  comercialDocumentType   comercialDocumentType  
    names.add("comercialDocumentType_id");          
    names.add("comercialDocumentType_pkey");

    final JsonObject comercialDocumentTypeFromComercialDocumentOut =   doMto2("comercialDocumentType","comercialDocumentTypeOut","comercialDocumentOut");        
   
    names.add("comercialDocumentType_pname");           
    sortMapFields.put( "comercialDocumentType_pname", "comercial_document_type_pname");  
    comercialDocumentTypeFromComercialDocumentOut.put("pc","pname");    
         
    mto2.add(comercialDocumentTypeFromComercialDocumentOut);        

    final ZtatUnitInfoLon2 zComercialDocumentTypeFromComercialDocumentOut = new ZtatUnitInfoLon2(zComercialDocumentOut, "comercial_document_type_id", "comercialDocumentType",  "comercial_document_type_out","pname","comercial_document_type");
    mz2.put("zComercialDocumentTypeFromComercialDocumentOut",zComercialDocumentTypeFromComercialDocumentOut);

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           

//(3)   departamentBaseTimePeriod   
    names.add("departamentBaseTimePeriod_id");          
    names.add("departamentBaseTimePeriod_pkey");

    final JsonObject departamentBaseTimePeriodFromContractFromComercialDocumentOut =   doMto2("departamentBaseTimePeriod","departamentBaseTimePeriod","contract");        
         
    mto3.add(departamentBaseTimePeriodFromContractFromComercialDocumentOut);        

     
    final ZtatUnitInfoLon3 zDepartamentBaseTimePeriodFromContractFromComercialDocumentOut = new ZtatUnitInfoLon3(zContractFromComercialDocumentOut, "departament_base_time_period_id", "departamentBaseTimePeriod",  "departament_base_time_period","null","departament_base_time_period");
    mz3.put("zDepartamentBaseTimePeriodFromContractFromComercialDocumentOut",zDepartamentBaseTimePeriodFromContractFromComercialDocumentOut);    

//(3)   thirdPerson   
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPersonFromContractFromComercialDocumentOut =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");            
    sortMapFields.put( "thirdPerson_pname", "third_person_pname"); 
    thirdPersonFromContractFromComercialDocumentOut.put("pc","pname");     
         
    mto3.add(thirdPersonFromContractFromComercialDocumentOut);        

     
    final ZtatUnitInfoLon3 zThirdPersonFromContractFromComercialDocumentOut = new ZtatUnitInfoLon3(zContractFromComercialDocumentOut, "third_person_id", "thirdPerson",  "third_person","pname","third_person");
    mz3.put("zThirdPersonFromContractFromComercialDocumentOut",zThirdPersonFromContractFromComercialDocumentOut);    

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
        jsa.add(r.getLong("payment_out_id") );       
        jsa.add(r.getString("payment_out_pkey") );
    jsa.add(r.getLong("payment_out_form_id"));
    jsa.add(r.getString("payment_out_form_pkey"));   
    
    jsa.add(r.getLong("comercial_document_out_id"));
    jsa.add(r.getString("comercial_document_out_pkey"));   
    
        
    jsa.add(r.getString("comercial_document_out_pname"));
    jsa.add(r.getLong("monetary_account_id"));
    jsa.add(r.getString("monetary_account_pkey"));
    

    jsa.add(r.getString("monetary_account_pname"));
    
    jsa.add(r.getLong("payment_out_type_id"));
    jsa.add(r.getString("payment_out_type_pkey"));
    

    jsa.add(r.getString("payment_out_type_pname"));
    
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
        m.put("paymentOut_id",LONG);
    }        
//pkey    
    m.put("paymentOut_pkey",STRING);          
    if(level<1){
        return m;    
    }       
// paymentOutForm   paymentOutForm
    if(withIds){
        m.put("paymentOutForm_id",LONG);                       
    }
    m.put("paymentOutForm_pkey",STRING);      
// comercialDocumentOut   comercialDocumentOut
    if(withIds){
        m.put("comercialDocumentOut_id",LONG);                       
    }
    m.put("comercialDocumentOut_pkey",STRING);     
    m.put("comercialDocumentOut_pname",STRING);  
//[2] monetaryAccount --   monetaryAccount
    if(withIds){
        m.put("monetaryAccount_id",LONG);              
    }              
    m.put("monetaryAccount_pkey",STRING);  
        
    m.put("monetaryAccount_pname",STRING);  
//[2] paymentOutType --   paymentOutType
    if(withIds){
        m.put("paymentOutType_id",LONG);              
    }              
    m.put("paymentOutType_pkey",STRING);  
        
    m.put("paymentOutType_pname",STRING);  
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
//[3] departamentBaseTimePeriod --   departamentBaseTimePeriod
    if(withIds){
        m.put("departamentBaseTimePeriod_id",LONG);              
    }              
    m.put("departamentBaseTimePeriod_pkey",STRING);  
        
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
        lToCell(r, row,"payment_out_id", nc++); 
    }            //pkey       
            sToCell(r, row,"payment_out_pkey", nc++); 
//paymentOutForm   payment_out_form        
    if(withIds){
        lToCell(r, row,"payment_out_form_id", nc++);
    }
    sToCell(r, row,"payment_out_form_pkey", nc++);
//comercialDocumentOut   comercial_document_out        
    if(withIds){
        lToCell(r, row,"comercial_document_out_id", nc++);
    }
    sToCell(r, row,"comercial_document_out_pkey", nc++);
    sToCell(r, row,"comercial_document_out_pname", nc++);
// monetaryAccount  monetary_account
    if(withIds){
       lToCell(r, row,"monetary_account_id", nc++);
    }

    sToCell(r, row,"monetary_account_pkey", nc++);

    sToCell(r, row,"monetary_account_pname", nc++);
// paymentOutType  payment_out_type
    if(withIds){
       lToCell(r, row,"payment_out_type_id", nc++);
    }

    sToCell(r, row,"payment_out_type_pkey", nc++);

    sToCell(r, row,"payment_out_type_pname", nc++);
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
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE payment_out_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentOut dc0, final Tuple t){
                
    t.addString(dc0.getPkey());   
    if(dc0.getPaymentOutForm()!=null){
       t.addLong(dc0.getPaymentOutForm().getId());
    }   
    if(dc0.getComercialDocumentOut()!=null){
       t.addLong(dc0.getComercialDocumentOut().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final PaymentOut dc0, final Tuple t){
           
//      if(dc0.getPaymentOutForm()!=null){
//            t.addLong(dc0.getPaymentOutForm().getId());
//      }   
//      if(dc0.getComercialDocumentOut()!=null){
//            t.addLong(dc0.getComercialDocumentOut().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("paymentOutForm_id",obj,t);

    fTLong("comercialDocumentOut_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> paymentOutForm = mapParents.get("paymentOutForm");
            final Long paymentOutForm_id = paymentOutForm.get((String)obj.get("paymentOutForm_pkey"));
            obj.put("paymentOutForm_id", paymentOutForm_id);
      
            final Map<String, Long> comercialDocumentOut = mapParents.get("comercialDocumentOut");
            final Long comercialDocumentOut_id = comercialDocumentOut.get((String)obj.get("comercialDocumentOut_pkey"));
            obj.put("comercialDocumentOut_id", comercialDocumentOut_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);     
    fTLong("paymentOutForm_id",js,t);     
    fTLong("comercialDocumentOut_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("paymentOutForm_id",js,t);

            //     fTLong("comercialDocumentOut_id",js,t);
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
    public PaymentOut doFrom(final Row r){
        final PaymentOut paymentOut = new PaymentOut();
         paymentOut.setId(r.getLong("payment_out_id"));         
                paymentOut.setPkey(  r.getString("payment_out_pkey"));              
        final PaymentOutForm paymentOutForm = new PaymentOutForm();
        paymentOutForm.setId(r.getLong("payment_out_form_id"));
        paymentOutForm.setPkey(r.getString("payment_out_form_pkey"));
        
        paymentOut.setPaymentOutForm(paymentOutForm);
        
        final ComercialDocumentOut comercialDocumentOut = new ComercialDocumentOut();
        comercialDocumentOut.setId(r.getLong("comercial_document_out_id"));
        comercialDocumentOut.setPkey(r.getString("comercial_document_out_pkey"));
        
        comercialDocumentOut.setPname(r.getString("comercial_document_out_pname"));
        paymentOut.setComercialDocumentOut(comercialDocumentOut);
        
        final MonetaryAccount monetaryAccount = new MonetaryAccount();
        monetaryAccount.setId(r.getLong("monetary_account_id"));
        monetaryAccount.setPkey(r.getString("monetary_account_pkey"));
        monetaryAccount.setPname(r.getString("monetary_account_pname"));
 
        paymentOutForm.setMonetaryAccount(monetaryAccount); 
        final PaymentOutType paymentOutType = new PaymentOutType();
        paymentOutType.setId(r.getLong("payment_out_type_id"));
        paymentOutType.setPkey(r.getString("payment_out_type_pkey"));
        paymentOutType.setPname(r.getString("payment_out_type_pname"));
 
        paymentOutForm.setPaymentOutType(paymentOutType); 
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
        return paymentOut;
    }
    
    @Override
    public PaymentOut doFromJson(final JsonObject js){
        PaymentOut paymentOut = new PaymentOut();
        paymentOut.setId(js.getLong("id"));
        
                
                paymentOut.setPkey(js.getString("pkey"));        
            paymentOut.setId(js.getLong("paymentOutForm_id"));        
            paymentOut.setId(js.getLong("comercialDocumentOut_id"));
        return paymentOut;
    }

    @Override
    public JsonObject toJson(final PaymentOut o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final PaymentOutForm paymentOutForm = o.getPaymentOutForm();
            if(paymentOutForm!=null){
                jso.put("paymentOutForm_id", paymentOutForm.getId());
                jso.put("paymentOutForm_pkey", paymentOutForm.getPkey());
            }
            

            final ComercialDocumentOut comercialDocumentOut = o.getComercialDocumentOut();
            if(comercialDocumentOut!=null){
                jso.put("comercialDocumentOut_id", comercialDocumentOut.getId());
                jso.put("comercialDocumentOut_pkey", comercialDocumentOut.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "payment_out_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "payment_out_pkey");
    slcb.doEqInPSimple( "pkey", "payment_out_pkey");
        
        slcb.doIlPSimple2( "paymentOutForm_pkey", "payment_out_form_pkey");
        slcb.doEQPSimple2( "paymentOutForm_pkey", "payment_out_form_pkey");
        slcb.doInLongCondition("paymentOutForm_id", "payment_out_form_id");  
//PaymentOutForm undefined        --
        slcb.doIlPSimple2( "comercialDocumentOut_pkey", "comercial_document_out_pkey");
        slcb.doEQPSimple2( "comercialDocumentOut_pkey", "comercial_document_out_pkey");
        slcb.doInLongCondition("comercialDocumentOut_id", "comercial_document_out_id");  
//ComercialDocumentOut 4        --
        slcb.doIlPSimple2( "comercialDocumentOut_pname", "comercial_document_out_pname");    
        
        slcb.doIlPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");
//MonetaryAccount 1
        slcb.doIlPSimple2( "monetaryAccount_pname", "monetary_account_pname"); 
        slcb.doIlPSimple2( "paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doEQPSimple2( "paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doInLongCondition("paymentOutType_id", "payment_out_type_id");
//PaymentOutType 1
        slcb.doIlPSimple2( "paymentOutType_pname", "payment_out_type_pname"); 
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");
//ContractOut 1
        slcb.doIlPSimple2( "contract_pname", "contract_pname"); 
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id"); 
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
//ComercialDocumentTypeOut 2
        slcb.doIlPSimple2( "comercialDocumentType_pname", "comercial_document_type_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"payment_out");
        sz0.addField("COUNT(payment_out.id) as c_payment_out_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zPaymentOutForm"))   ;               
    sz0.applyG1(mz1.get("zComercialDocumentOut"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zMonetaryAccountFromPaymentOutForm"));                           
    sz0.applyG2(mz2.get("zPaymentOutTypeFromPaymentOutForm"));                           
    sz0.applyG2(mz2.get("zContractFromComercialDocumentOut"));                           
    sz0.applyG2(mz2.get("zUserAutorFromComercialDocumentOut"));                           
    sz0.applyG2(mz2.get("zComercialDocumentTypeFromComercialDocumentOut"));                           
//level 3    
        sz0.applyG3(mz3.get("zDepartamentBaseTimePeriodFromContractFromComercialDocumentOut"));               
        sz0.applyG3(mz3.get("zThirdPersonFromContractFromComercialDocumentOut"));               
        return sz0;
    }
}
    
        