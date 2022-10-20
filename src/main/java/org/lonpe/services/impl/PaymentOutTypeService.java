
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
 *   PaymentOutTypeService 
 * 
 */
   
  
public class PaymentOutTypeService extends AbstractServiceLon<PaymentOutType>{

    private static final String SQLINSERT ="INSERT INTO payment_out_type(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_out_type SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_out_type SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_out_type_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_out_type_view";
    private static final String SQLKEYS = "SELECT payment_out_type_pkey FROM payment_out_type_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_out_type WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_out_type WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM payment_out_type WHERE id = $1 returning id";
    private static final String TABLENAME ="payment_out_type";
    
    
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
    
    private static String sql00 = "SELECT payment_out_type.id as payment_out_type_id,
payment_out_type.pkey as payment_out_type_pkey,
payment_out_type.pname as payment_out_type_pname 
  FROM 
  payment_out_type ; 
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

        
    dcModel.put("dc", "paymentOutType");

//ID 
    names.add("id");

    sortMapFields.put("id","payment_out_type_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("payment_out_type.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("payment_out_type.payment_out_type_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "payment_out_type_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("payment_out_type.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "payment_out_type_pname");                   
  
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
        jsa.add(r.getLong("payment_out_type_id") );
        jsa.add(r.getString("payment_out_type_pkey") );
        jsa.add(r.getString("payment_out_type_pname") );
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
                m_.put("paymentOutType_id","Long");
            }
            
    //pkey       
            m_.put("paymentOutType_pkey","String"); 
            
    //pname       
            m_.put("paymentOutType_pname","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"payment_out_type_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"payment_out_type_pkey", nc++); 
    //pname       
            sToCell(r, row,"payment_out_type_pname", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE payment_out_type_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentOutType dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final PaymentOutType dc0, final Tuple t){
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
    public PaymentOutType doFrom(final Row r){
        final PaymentOutType paymentOutType = new PaymentOutType();
         paymentOutType.setId(r.getLong("payment_out_type_id"));
         
                paymentOutType.setPkey(  r.getString("payment_out_type_pkey"));
         
                paymentOutType.setPname(  r.getString("payment_out_type_pname"));  
        return paymentOutType;
    }
    
    @Override
    public PaymentOutType doFromJson(final JsonObject js){
        PaymentOutType paymentOutType = new PaymentOutType();
        paymentOutType.setId(js.getLong("id"));
        
                paymentOutType.setPkey(js.getString("pkey"));
        paymentOutType.setPname(js.getString("pname"));
        return paymentOutType;
    }

    @Override
    public JsonObject toJson(final PaymentOutType o) {        
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
       slcb.doInLongCondition("id", "payment_out_type_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "payment_out_type_pkey");
    slcb.doEqInPSimple( "pkey", "payment_out_type_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "payment_out_type_pname");
    slcb.doEqInPSimple( "pname", "payment_out_type_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"payment_out_type");
        sz0.addField("COUNT(payment_out_type.id) as c_payment_out_type_id").addName("count");
        
        
        return sz0;
    }
}
    
        