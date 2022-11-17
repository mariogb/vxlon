
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
 *   PaymentInTypeService 
 * 
 */
  
public class PaymentInTypeService extends AbstractServiceLon<PaymentInType>{

    private static final String SQLINSERT ="INSERT INTO payment_in_type(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_in_type SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_in_type SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_in_type_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_in_type_view";
    private static final String SQLKEYS = "SELECT payment_in_type_pkey FROM payment_in_type_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_in_type WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_in_type WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM payment_in_type WHERE id = $1 returning id";
    private static final String TABLENAME ="payment_in_type";
    

    public PaymentInTypeService() {
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
    private static String sql00 = "SELECT payment_in_type.id as payment_in_type_id,
payment_in_type.pkey as payment_in_type_pkey,
payment_in_type.pname as payment_in_type_pname 
  FROM 
  payment_in_type "
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
        
    dcModel.put("dc", "paymentInType");

//ID 
    names.add("id");

    sortMapFields.put("id","payment_in_type_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","payment_in_type");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("payment_in_type.payment_in_type_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","payment_in_type");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

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
        jsa.add(r.getLong("payment_in_type_id") );       
        jsa.add(r.getString("payment_in_type_pkey") );       
        jsa.add(r.getString("payment_in_type_pname") );
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
        m.put("paymentInType_id",LONG);
    }        
//pkey    
    m.put("paymentInType_pkey",STRING);              
//pname    
    m.put("paymentInType_pname",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"payment_in_type_id", nc++); 
    }            //pkey       
            sToCell(r, row,"payment_in_type_pkey", nc++);     //pname       
            sToCell(r, row,"payment_in_type_pname", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE payment_in_type_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentInType dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final PaymentInType dc0, final Tuple t){
        
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
    public PaymentInType doFrom(final Row r){
        final PaymentInType paymentInType = new PaymentInType();
         paymentInType.setId(r.getLong("payment_in_type_id"));         
                paymentInType.setPkey(  r.getString("payment_in_type_pkey"));                       
                paymentInType.setPname(  r.getString("payment_in_type_pname"));                
        return paymentInType;
    }
    
    @Override
    public PaymentInType doFromJson(final JsonObject js){
        PaymentInType paymentInType = new PaymentInType();
        paymentInType.setId(js.getLong("id"));
        
                
                paymentInType.setPkey(js.getString("pkey"));        
                paymentInType.setPname(js.getString("pname"));
        return paymentInType;
    }

    @Override
    public JsonObject toJson(final PaymentInType o) {        
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
       slcb.doInLongCondition("id", "payment_in_type_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "payment_in_type_pkey");
    slcb.doEqInPSimple( "pkey", "payment_in_type_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "payment_in_type_pname");
    slcb.doEqInPSimple( "pname", "payment_in_type_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"payment_in_type");
        sz0.addField("COUNT(payment_in_type.id) as c_payment_in_type_id").addName("count");
        
        
        return sz0;
    }
}
    
        