
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
 *   PaymentInFormService 
 * 
 */
   
  
public class PaymentInFormService extends AbstractServiceLon<PaymentInForm>{

    private static final String SQLINSERT ="INSERT INTO payment_in_form(pkey,monetary_account_id,payment_in_type_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_in_form SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_in_form SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_in_form_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_in_form_view";
    private static final String SQLKEYS = "SELECT payment_in_form_pkey FROM payment_in_form_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_in_form WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_in_form WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM payment_in_form WHERE id = $1 returning id";
    private static final String TABLENAME ="payment_in_form";
    
//1
    private static final ZtatUnitInfoLon zMonetaryAccount;

//1
    private static final ZtatUnitInfoLon zPaymentInType;
    
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
    
    private static String sql00 = "SELECT payment_in_form.id as payment_in_form_id,
payment_in_form.pkey as payment_in_form_pkey,
monetary_account.id as monetary_account_id,monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_in_type.id as payment_in_type_id,payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname 
  FROM 
  payment_in_form,
  monetary_account as monetary_account,
  payment_in_type as payment_in_type  
 WHERE 
 payment_in_form.monetary_account_id = monetary_account.id
 AND payment_in_form.payment_in_type_id = payment_in_type.id; 
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

        
    dcModel.put("dc", "paymentInForm");

//ID 
    names.add("id");

    sortMapFields.put("id","payment_in_form_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("payment_in_form.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("payment_in_form.payment_in_form_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "payment_in_form_pkey");                   
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  monetaryAccount --------------------
    names.add("monetaryAccount_id");      
    insertMapFields.put("payment_in_form.monetary_account_id","monetaryAccount_id");    
       
    names.add("monetaryAccount_pkey");        
    sortMapFields.put( "monetaryAccount_pkey", "monetary_account_pkey");        

    final JsonObject monetaryAccount =  doMto("monetaryAccount","monetaryAccount");        
   
    names.add("monetaryAccount_pname");
    sortMapFields.put( "monetaryAccount_pname", "monetary_account_pname");         

    monetaryAccount.put("pc","pname");          

    mto.add(monetaryAccount);
        

//(1)  paymentInType --------------------
    names.add("paymentInType_id");      
    insertMapFields.put("payment_in_form.payment_in_type_id","paymentInType_id");    
       
    names.add("paymentInType_pkey");        
    sortMapFields.put( "paymentInType_pkey", "payment_in_type_pkey");        

    final JsonObject paymentInType =  doMto("paymentInType","paymentInType");        
   
    names.add("paymentInType_pname");
    sortMapFields.put( "paymentInType_pname", "payment_in_type_pname");         

    paymentInType.put("pc","pname");          

    mto.add(paymentInType);
        

    dcModel.put("mto",mto);     
        

        
//1  monetary_account  -- monetary_account_id
    zMonetaryAccount = new ZtatUnitInfoLon("monetary_account_id", "monetaryAccount",  "monetary_account","pname","monetary_account");

//1  payment_in_type  -- payment_in_type_id
    zPaymentInType = new ZtatUnitInfoLon("payment_in_type_id", "paymentInType",  "payment_in_type","pname","payment_in_type");

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
        jsa.add(r.getLong("payment_in_form_id") );
        jsa.add(r.getString("payment_in_form_pkey") );
        jsa.add(r.getLong("monetary_account_id"));
        jsa.add(r.getString("monetary_account_pkey"));
        jsa.add(r.getString("monetary_account_pname"));
        jsa.add(r.getLong("payment_in_type_id"));
        jsa.add(r.getString("payment_in_type_pkey"));
        jsa.add(r.getString("payment_in_type_pname"));
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
                m_.put("paymentInForm_id","Long");
            }
            
    //pkey       
            m_.put("paymentInForm_pkey","String"); 
            
if(level<1){
                return m_;    
            }
            
 // monetaryAccount
if(withIds){
            m_.put("monetaryAccount_id","Long");   
                    
            }

        m_.put("monetaryAccount_pkey","String");   
        

            m_.put("monetaryAccount_pname","String");   
            
 // paymentInType
if(withIds){
            m_.put("paymentInType_id","Long");   
                    
            }

        m_.put("paymentInType_pkey","String");   
        

            m_.put("paymentInType_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"payment_in_form_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"payment_in_form_pkey", nc++); 
 // monetaryAccount
if(withIds){
                    lToCell(r, row,"monetary_account_id", nc++);
                 }
sToCell(r, row,"monetary_account_pkey", nc++);
sToCell(r, row,"monetary_account_pname", nc++);
 // paymentInType
if(withIds){
                    lToCell(r, row,"payment_in_type_id", nc++);
                 }
sToCell(r, row,"payment_in_type_pkey", nc++);
sToCell(r, row,"payment_in_type_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE payment_in_form_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentInForm dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
            if(dc0.getMonetaryAccount()!=null){
               t.addLong(dc0.getMonetaryAccount().getId());
            }
   
            if(dc0.getPaymentInType()!=null){
               t.addLong(dc0.getPaymentInType().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final PaymentInForm dc0, final Tuple t){
           
//      if(dc0.getMonetaryAccount()!=null){
//            t.addLong(dc0.getMonetaryAccount().getId());
//      }
   
//      if(dc0.getPaymentInType()!=null){
//            t.addLong(dc0.getPaymentInType().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("monetaryAccount_id",obj,t);

    fTLong("paymentInType_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> monetaryAccount = mapParents.get("monetaryAccount");
            final Long monetaryAccount_id = monetaryAccount.get((String)obj.get("monetaryAccount_pkey"));
            obj.put("monetaryAccount_id", monetaryAccount_id);
      
            final Map<String, Long> paymentInType = mapParents.get("paymentInType");
            final Long paymentInType_id = paymentInType.get((String)obj.get("paymentInType_pkey"));
            obj.put("paymentInType_id", paymentInType_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
     
    fTLong("monetaryAccount_id",js,t);
     
    fTLong("paymentInType_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("monetaryAccount_id",js,t);

            //     fTLong("paymentInType_id",js,t);
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
    public PaymentInForm doFrom(final Row r){
        final PaymentInForm paymentInForm = new PaymentInForm();
         paymentInForm.setId(r.getLong("payment_in_form_id"));
         
                paymentInForm.setPkey(  r.getString("payment_in_form_pkey"));

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
          
        return paymentInForm;
    }
    
    @Override
    public PaymentInForm doFromJson(final JsonObject js){
        PaymentInForm paymentInForm = new PaymentInForm();
        paymentInForm.setId(js.getLong("id"));
        
                paymentInForm.setPkey(js.getString("pkey"));
        paymentInForm.setId(js.getLong("monetaryAccount_id"));
        paymentInForm.setId(js.getLong("paymentInType_id"));
        return paymentInForm;
    }

    @Override
    public JsonObject toJson(final PaymentInForm o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final MonetaryAccount monetaryAccount = o.getMonetaryAccount();
            if(monetaryAccount!=null){
                jso.put("monetaryAccount_id", monetaryAccount.getId());
                jso.put("monetaryAccount_pkey", monetaryAccount.getPkey());
            }
            

            final PaymentInType paymentInType = o.getPaymentInType();
            if(paymentInType!=null){
                jso.put("paymentInType_id", paymentInType.getId());
                jso.put("paymentInType_pkey", paymentInType.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "payment_in_form_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "payment_in_form_pkey");
    slcb.doEqInPSimple( "pkey", "payment_in_form_pkey");
        
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

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"payment_in_form");
        sz0.addField("COUNT(payment_in_form.id) as c_payment_in_form_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zMonetaryAccount);               
    sz0.applyG1(zPaymentInType);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        