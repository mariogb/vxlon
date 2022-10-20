
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
 *   PaymentOutFormService 
 * 
 */
   
  
public class PaymentOutFormService extends AbstractServiceLon<PaymentOutForm>{

    private static final String SQLINSERT ="INSERT INTO payment_out_form(pkey,monetary_account_id,payment_out_type_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_out_form SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_out_form SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_out_form_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_out_form_view";
    private static final String SQLKEYS = "SELECT payment_out_form_pkey FROM payment_out_form_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_out_form WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_out_form WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM payment_out_form WHERE id = $1 returning id";
    private static final String TABLENAME ="payment_out_form";
    
//1
    private static final ZtatUnitInfoLon zMonetaryAccount;

//1
    private static final ZtatUnitInfoLon zPaymentOutType;
    
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
    
    private static String sql00 = "SELECT payment_out_form.id as payment_out_form_id,
payment_out_form.pkey as payment_out_form_pkey,
monetary_account.id as monetary_account_id,monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_out_type.id as payment_out_type_id,payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as payment_out_type_pname 
  FROM 
  payment_out_form,
  monetary_account as monetary_account,
  payment_out_type as payment_out_type  
 WHERE 
 payment_out_form.monetary_account_id = monetary_account.id
 AND payment_out_form.payment_out_type_id = payment_out_type.id; 
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

        
    dcModel.put("dc", "paymentOutForm");

//ID 
    names.add("id");

    sortMapFields.put("id","payment_out_form_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("payment_out_form.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("payment_out_form.payment_out_form_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "payment_out_form_pkey");                   
 
    ps.add(pkey);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  monetaryAccount --------------------
    names.add("monetaryAccount_id");      
    insertMapFields.put("payment_out_form.monetary_account_id","monetaryAccount_id");    
       
    names.add("monetaryAccount_pkey");        
    sortMapFields.put( "monetaryAccount_pkey", "monetary_account_pkey");        

    final JsonObject monetaryAccount =  doMto("monetaryAccount","monetaryAccount");        
   
    names.add("monetaryAccount_pname");
    sortMapFields.put( "monetaryAccount_pname", "monetary_account_pname");         

    monetaryAccount.put("pc","pname");          

    mto.add(monetaryAccount);
        

//(1)  paymentOutType --------------------
    names.add("paymentOutType_id");      
    insertMapFields.put("payment_out_form.payment_out_type_id","paymentOutType_id");    
       
    names.add("paymentOutType_pkey");        
    sortMapFields.put( "paymentOutType_pkey", "payment_out_type_pkey");        

    final JsonObject paymentOutType =  doMto("paymentOutType","paymentOutType");        
   
    names.add("paymentOutType_pname");
    sortMapFields.put( "paymentOutType_pname", "payment_out_type_pname");         

    paymentOutType.put("pc","pname");          

    mto.add(paymentOutType);
        

    dcModel.put("mto",mto);     
        

        
//1  monetary_account  -- monetary_account_id
    zMonetaryAccount = new ZtatUnitInfoLon("monetary_account_id", "monetaryAccount",  "monetary_account","pname","monetary_account");

//1  payment_out_type  -- payment_out_type_id
    zPaymentOutType = new ZtatUnitInfoLon("payment_out_type_id", "paymentOutType",  "payment_out_type","pname","payment_out_type");

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
        jsa.add(r.getLong("payment_out_form_id") );
        jsa.add(r.getString("payment_out_form_pkey") );
        jsa.add(r.getLong("monetary_account_id"));
        jsa.add(r.getString("monetary_account_pkey"));
        jsa.add(r.getString("monetary_account_pname"));
        jsa.add(r.getLong("payment_out_type_id"));
        jsa.add(r.getString("payment_out_type_pkey"));
        jsa.add(r.getString("payment_out_type_pname"));
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
                m_.put("paymentOutForm_id","Long");
            }
            
    //pkey       
            m_.put("paymentOutForm_pkey","String"); 
            
if(level<1){
                return m_;    
            }
            
 // monetaryAccount
if(withIds){
            m_.put("monetaryAccount_id","Long");   
                    
            }

        m_.put("monetaryAccount_pkey","String");   
        

            m_.put("monetaryAccount_pname","String");   
            
 // paymentOutType
if(withIds){
            m_.put("paymentOutType_id","Long");   
                    
            }

        m_.put("paymentOutType_pkey","String");   
        

            m_.put("paymentOutType_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"payment_out_form_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"payment_out_form_pkey", nc++); 
 // monetaryAccount
if(withIds){
                    lToCell(r, row,"monetary_account_id", nc++);
                 }
sToCell(r, row,"monetary_account_pkey", nc++);
sToCell(r, row,"monetary_account_pname", nc++);
 // paymentOutType
if(withIds){
                    lToCell(r, row,"payment_out_type_id", nc++);
                 }
sToCell(r, row,"payment_out_type_pkey", nc++);
sToCell(r, row,"payment_out_type_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE payment_out_form_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentOutForm dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
            if(dc0.getMonetaryAccount()!=null){
               t.addLong(dc0.getMonetaryAccount().getId());
            }
   
            if(dc0.getPaymentOutType()!=null){
               t.addLong(dc0.getPaymentOutType().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final PaymentOutForm dc0, final Tuple t){
           
//      if(dc0.getMonetaryAccount()!=null){
//            t.addLong(dc0.getMonetaryAccount().getId());
//      }
   
//      if(dc0.getPaymentOutType()!=null){
//            t.addLong(dc0.getPaymentOutType().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("monetaryAccount_id",obj,t);

    fTLong("paymentOutType_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> monetaryAccount = mapParents.get("monetaryAccount");
            final Long monetaryAccount_id = monetaryAccount.get((String)obj.get("monetaryAccount_pkey"));
            obj.put("monetaryAccount_id", monetaryAccount_id);
      
            final Map<String, Long> paymentOutType = mapParents.get("paymentOutType");
            final Long paymentOutType_id = paymentOutType.get((String)obj.get("paymentOutType_pkey"));
            obj.put("paymentOutType_id", paymentOutType_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
     
    fTLong("monetaryAccount_id",js,t);
     
    fTLong("paymentOutType_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        
            //     fTLong("monetaryAccount_id",js,t);

            //     fTLong("paymentOutType_id",js,t);
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
    public PaymentOutForm doFrom(final Row r){
        final PaymentOutForm paymentOutForm = new PaymentOutForm();
         paymentOutForm.setId(r.getLong("payment_out_form_id"));
         
                paymentOutForm.setPkey(  r.getString("payment_out_form_pkey"));

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
          
        return paymentOutForm;
    }
    
    @Override
    public PaymentOutForm doFromJson(final JsonObject js){
        PaymentOutForm paymentOutForm = new PaymentOutForm();
        paymentOutForm.setId(js.getLong("id"));
        
                paymentOutForm.setPkey(js.getString("pkey"));
        paymentOutForm.setId(js.getLong("monetaryAccount_id"));
        paymentOutForm.setId(js.getLong("paymentOutType_id"));
        return paymentOutForm;
    }

    @Override
    public JsonObject toJson(final PaymentOutForm o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );

            final MonetaryAccount monetaryAccount = o.getMonetaryAccount();
            if(monetaryAccount!=null){
                jso.put("monetaryAccount_id", monetaryAccount.getId());
                jso.put("monetaryAccount_pkey", monetaryAccount.getPkey());
            }
            

            final PaymentOutType paymentOutType = o.getPaymentOutType();
            if(paymentOutType!=null){
                jso.put("paymentOutType_id", paymentOutType.getId());
                jso.put("paymentOutType_pkey", paymentOutType.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "payment_out_form_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "payment_out_form_pkey");
    slcb.doEqInPSimple( "pkey", "payment_out_form_pkey");
        
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

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"payment_out_form");
        sz0.addField("COUNT(payment_out_form.id) as c_payment_out_form_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zMonetaryAccount);               
    sz0.applyG1(zPaymentOutType);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        