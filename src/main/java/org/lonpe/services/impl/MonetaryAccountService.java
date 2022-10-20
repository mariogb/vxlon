
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





/**
 *   MonetaryAccountService 
 * 
 */
   
  
public class MonetaryAccountService extends AbstractServiceLon<MonetaryAccount>{

    private static final String SQLINSERT ="INSERT INTO monetary_account(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE monetary_account SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE monetary_account SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM monetary_account_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM monetary_account_view";
    private static final String SQLKEYS = "SELECT monetary_account_pkey FROM monetary_account_view";
    private static final String SQLIDBYPKEY = "SELECT id from monetary_account WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from monetary_account WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM monetary_account WHERE id = $1 returning id";
    private static final String TABLENAME ="monetary_account";
    
    
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
    
    private static String sql00 = "SELECT monetary_account.id as monetary_account_id,
monetary_account.pkey as monetary_account_pkey,
monetary_account.pname as monetary_account_pname 
  FROM 
  monetary_account ; 
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

        
    dcModel.put("dc", "monetaryAccount");

//ID 
    names.add("id");

    sortMapFields.put("id","monetary_account_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("monetary_account.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("monetary_account.monetary_account_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "monetary_account_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("monetary_account.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "monetary_account_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"paymentOuts","paymentOut"); 
                

        applyOtm(otm,"paymentIns","paymentIn"); 
                

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
        jsa.add(r.getLong("monetary_account_id") );
        jsa.add(r.getString("monetary_account_pkey") );
        jsa.add(r.getString("monetary_account_pname") );
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
                m_.put("monetaryAccount_id","Long");
            }
            
    //pkey       
            m_.put("monetaryAccount_pkey","String"); 
            
    //pname       
            m_.put("monetaryAccount_pname","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"monetary_account_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"monetary_account_pkey", nc++); 
    //pname       
            sToCell(r, row,"monetary_account_pname", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE monetary_account_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final MonetaryAccount dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final MonetaryAccount dc0, final Tuple t){
                t.addString(dc0.getPname());

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("pname", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);
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
    public MonetaryAccount doFrom(final Row r){
        final MonetaryAccount monetaryAccount = new MonetaryAccount();
         monetaryAccount.setId(r.getLong("monetary_account_id"));
         
                monetaryAccount.setPkey(  r.getString("monetary_account_pkey"));
         
                monetaryAccount.setPname(  r.getString("monetary_account_pname"));  
        return monetaryAccount;
    }
    
    @Override
    public MonetaryAccount doFromJson(final JsonObject js){
        MonetaryAccount monetaryAccount = new MonetaryAccount();
        monetaryAccount.setId(js.getLong("id"));
        
                monetaryAccount.setPkey(js.getString("pkey"));
        monetaryAccount.setPname(js.getString("pname"));
        return monetaryAccount;
    }

    @Override
    public JsonObject toJson(final MonetaryAccount o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "monetary_account_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "monetary_account_pkey");
    slcb.doEqInPSimple( "pkey", "monetary_account_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "monetary_account_pname");
    slcb.doEqInPSimple( "pname", "monetary_account_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"monetary_account");
        sz0.addField("COUNT(monetary_account.id) as c_monetary_account_id").addName("count");
        
        
        return sz0;
    }
}
    
        