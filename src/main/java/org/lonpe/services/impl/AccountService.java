
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







/**
 *   AccountService 
 * 
 */
  
public class AccountService extends AbstractServiceLon<Account>{

    private static final String SQLINSERT ="INSERT INTO account(pkey,description,pname,type) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE account SET description = $1,pname = $2,type = $3 WHERE id = $4 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE account SET description = $1,pname = $2,type = $3 WHERE pkey = $4 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM account_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM account_view";
    private static final String SQLKEYS = "SELECT account_pkey FROM account_view";
    private static final String SQLIDBYPKEY = "SELECT id from account WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from account WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM account WHERE id = $1 returning id";
    private static final String TABLENAME ="account";
    

    public AccountService() {
        init0();
    }

    

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
    private static String sql00 = "SELECT account.id as account_id,
account.pkey as account_pkey,
account.description as account_description,
account.pname as account_pname,
account.type as account_type 
  FROM 
  account "
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
        
    dcModel.put("dc", "account");

//ID 
    names.add("id");

    sortMapFields.put("id","account_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","account");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("account.account_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//description
    doField("description","description","account");               

//Create property description       
    final JsonObject description = psString("description",false);
 
    ps.add(description);
 
//pname
    doFieldSort("pname","pname","account");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//type
    doField("type","type","account");               

//Create property type       
    final JsonObject type = psString("type",true);

    final JsonArray typeInList = new JsonArray();
                typeInList.add("ACTIVE"); 
typeInList.add("PASIVE"); 
    type.put("inList",typeInList );                
 
    ps.add(type);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        
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
        jsa.add(r.getLong("account_id") );       
        jsa.add(r.getString("account_pkey") );       
        jsa.add(r.getString("account_description") );       
        jsa.add(r.getString("account_pname") );       
        jsa.add(r.getString("account_type") );
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
        m.put("account_id",LONG);
    }        
//pkey    
    m.put("account_pkey",STRING);              
//description    
    m.put("account_description",STRING);              
//pname    
    m.put("account_pname",STRING);              
//type    
    m.put("account_type",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"account_id", nc++); 
    }            //pkey       
            sToCell(r, row,"account_pkey", nc++);     //description       
            sToCell(r, row,"account_description", nc++);     //pname       
            sToCell(r, row,"account_pname", nc++);     //type       
            sToCell(r, row,"account_type", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE account_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Account dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getDescription());        
    t.addString(dc0.getPname());        
    t.addString(dc0.getType());
    }

    @Override
    public void fillTupleUpdate(final Account dc0, final Tuple t){
        
    t.addString(dc0.getDescription());
    t.addString(dc0.getPname());
    t.addString(dc0.getType());
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("description", obj, t);

    fTString("pname", obj, t);

    fTString("type", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("description", js, t);
    fTString("pname", js, t);
    fTString("type", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("description", js, t);
fTString("pname", js, t);
fTString("type", js, t);
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
    public Account doFrom(final Row r){
        final Account account = new Account();
         account.setId(r.getLong("account_id"));         
                account.setPkey(  r.getString("account_pkey"));                       
                account.setDescription(  r.getString("account_description"));                       
                account.setPname(  r.getString("account_pname"));                       
                account.setType(  r.getString("account_type"));                
        return account;
    }
    
    @Override
    public Account doFromJson(final JsonObject js){
        Account account = new Account();
        account.setId(js.getLong("id"));
        
                
                account.setPkey(js.getString("pkey"));        
                account.setDescription(js.getString("description"));        
                account.setPname(js.getString("pname"));        
                account.setType(js.getString("type"));
        return account;
    }

    @Override
    public JsonObject toJson(final Account o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("description",  o.getDescription() );
        jso.put("pname",  o.getPname() );
        jso.put("type",  o.getType() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "account_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "account_pkey");
    slcb.doEqInPSimple( "pkey", "account_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "account_pname");
    slcb.doEqInPSimple( "pname", "account_pname");            
    slcb.doEqInPSimple( "type", "account_type");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"account");
        sz0.addField("COUNT(account.id) as c_account_id").addName("count");
        
        
        return sz0;
    }
}
    
        