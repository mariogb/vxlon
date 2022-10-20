
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
 *   ProductTypeService 
 * 
 */
   
  
public class ProductTypeService extends AbstractServiceLon<ProductType>{

    private static final String SQLINSERT ="INSERT INTO product_type(pkey,afect_stock,description,fast_key,is_service,pname,taxable,with_serial_number) VALUES ($1,$2,$3,$4,$5,$6,$7,$8) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE product_type SET afect_stock = $1,description = $2,fast_key = $3,is_service = $4,pname = $5,taxable = $6,with_serial_number = $7 WHERE id = $8 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE product_type SET afect_stock = $1,description = $2,fast_key = $3,is_service = $4,pname = $5,taxable = $6,with_serial_number = $7 WHERE pkey = $8 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM product_type_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM product_type_view";
    private static final String SQLKEYS = "SELECT product_type_pkey FROM product_type_view";
    private static final String SQLIDBYPKEY = "SELECT id from product_type WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from product_type WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM product_type WHERE id = $1 returning id";
    private static final String TABLENAME ="product_type";
    
    
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
    
    private static String sql00 = "SELECT product_type.id as product_type_id,
product_type.pkey as product_type_pkey,
product_type.afect_stock as product_type_afect_stock,
product_type.description as product_type_description,
product_type.fast_key as product_type_fast_key,
product_type.is_service as product_type_is_service,
product_type.pname as product_type_pname,
product_type.taxable as product_type_taxable,
product_type.with_serial_number as product_type_with_serial_number 
  FROM 
  product_type ; 
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

        
    dcModel.put("dc", "productType");

//ID 
    names.add("id");

    sortMapFields.put("id","product_type_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("product_type.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("product_type.product_type_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "product_type_pkey");                   
 
    ps.add(pkey);
 
//afectStock
    names.add("afectStock");
    insertMapFields.put("product_type.afect_stock","afectStock");  

//Create property afectStock       
    final JsonObject afectStock = ps00a("afectStock", "Boolean",true);

    sortMapFields.put("afectStock", "product_type_afect_stock");               
 
    ps.add(afectStock);
 
//description
    names.add("description");
    insertMapFields.put("product_type.description","description");  

//Create property description       
    final JsonObject description = ps00a("description", "String",false);
 
    ps.add(description);
 
//fastKey
    names.add("fastKey");
    insertMapFields.put("product_type.fast_key","fastKey");  

//Create property fastKey       
    final JsonObject fastKey = ps00a("fastKey", "String",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//isService
    names.add("isService");
    insertMapFields.put("product_type.is_service","isService");  

//Create property isService       
    final JsonObject isService = ps00a("isService", "Boolean",true);

    sortMapFields.put("isService", "product_type_is_service");               
 
    ps.add(isService);
 
//pname
    names.add("pname");
    insertMapFields.put("product_type.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "product_type_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//taxable
    names.add("taxable");
    insertMapFields.put("product_type.taxable","taxable");  

//Create property taxable       
    final JsonObject taxable = ps00a("taxable", "Boolean",true);

    sortMapFields.put("taxable", "product_type_taxable");               
 
    ps.add(taxable);
 
//withSerialNumber
    names.add("withSerialNumber");
    insertMapFields.put("product_type.with_serial_number","withSerialNumber");  

//Create property withSerialNumber       
    final JsonObject withSerialNumber = ps00a("withSerialNumber", "Boolean",true);

    sortMapFields.put("withSerialNumber", "product_type_with_serial_number");               
 
    ps.add(withSerialNumber);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"products","product"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"invoiceLineIns","invoiceLineIn","products",null,null); 
        

        applyOtm2(otm2,"invoiceLineOuts","invoiceLineOut","products",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

        

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
        jsa.add(r.getLong("product_type_id") );
        jsa.add(r.getString("product_type_pkey") );
        jsa.add(r.getBoolean("product_type_afect_stock") );
        jsa.add(r.getString("product_type_description") );
        jsa.add(r.getString("product_type_fast_key") );
        jsa.add(r.getBoolean("product_type_is_service") );
        jsa.add(r.getString("product_type_pname") );
        jsa.add(r.getBoolean("product_type_taxable") );
        jsa.add(r.getBoolean("product_type_with_serial_number") );
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
                m_.put("productType_id","Long");
            }
            
    //pkey       
            m_.put("productType_pkey","String"); 
            
    //afectStock       
            m_.put("productType_afectStock","Boolean"); 
            
    //description       
            m_.put("productType_description","String"); 
            
    //fastKey       
            m_.put("productType_fastKey","String"); 
            
    //isService       
            m_.put("productType_isService","Boolean"); 
            
    //pname       
            m_.put("productType_pname","String"); 
            
    //taxable       
            m_.put("productType_taxable","Boolean"); 
            
    //withSerialNumber       
            m_.put("productType_withSerialNumber","Boolean"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"product_type_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"product_type_pkey", nc++); 
    //afectStock     
                bToCell(r, row,"product_type_afect_stock", nc++); 
    //description       
            sToCell(r, row,"product_type_description", nc++); 
    //fastKey       
            sToCell(r, row,"product_type_fast_key", nc++); 
    //isService     
                bToCell(r, row,"product_type_is_service", nc++); 
    //pname       
            sToCell(r, row,"product_type_pname", nc++); 
    //taxable     
                bToCell(r, row,"product_type_taxable", nc++); 
    //withSerialNumber     
                bToCell(r, row,"product_type_with_serial_number", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE product_type_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ProductType dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addBoolean(dc0.getAfectStock());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addBoolean(dc0.getIsService());
        t.addString(dc0.getPname());
        t.addBoolean(dc0.getTaxable());
        t.addBoolean(dc0.getWithSerialNumber());
    }

    @Override
    public void fillTupleUpdate(final ProductType dc0, final Tuple t){
                t.addBoolean(dc0.getAfectStock());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addBoolean(dc0.getIsService());
        t.addString(dc0.getPname());
        t.addBoolean(dc0.getTaxable());
        t.addBoolean(dc0.getWithSerialNumber());

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTBoolean("afectStock", obj, t);

    fTString("description", obj, t);

    fTString("fastKey", obj, t);

    fTBoolean("isService", obj, t);

    fTString("pname", obj, t);

    fTBoolean("taxable", obj, t);

    fTBoolean("withSerialNumber", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTBoolean("afectStock", js, t);

    fTString("description", js, t);

    fTString("fastKey", js, t);

    fTBoolean("isService", js, t);

    fTString("pname", js, t);

    fTBoolean("taxable", js, t);

    fTBoolean("withSerialNumber", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTBoolean("afectStock", js, t);
fTString("description", js, t);
fTString("fastKey", js, t);
fTBoolean("isService", js, t);
fTString("pname", js, t);
fTBoolean("taxable", js, t);
fTBoolean("withSerialNumber", js, t);
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
    public ProductType doFrom(final Row r){
        final ProductType productType = new ProductType();
         productType.setId(r.getLong("product_type_id"));
         
                productType.setPkey(  r.getString("product_type_pkey"));
         
                productType.setAfectStock(  r.getBoolean("product_type_afect_stock"));
         
                productType.setDescription(  r.getString("product_type_description"));
         
                productType.setFastKey(  r.getString("product_type_fast_key"));
         
                productType.setIsService(  r.getBoolean("product_type_is_service"));
         
                productType.setPname(  r.getString("product_type_pname"));
         
                productType.setTaxable(  r.getBoolean("product_type_taxable"));
         
                productType.setWithSerialNumber(  r.getBoolean("product_type_with_serial_number"));  
        return productType;
    }
    
    @Override
    public ProductType doFromJson(final JsonObject js){
        ProductType productType = new ProductType();
        productType.setId(js.getLong("id"));
        
                productType.setPkey(js.getString("pkey"));
        productType.setAfectStock(js.getBoolean("afectStock"));
        productType.setDescription(js.getString("description"));
        productType.setFastKey(js.getString("fastKey"));
        productType.setIsService(js.getBoolean("isService"));
        productType.setPname(js.getString("pname"));
        productType.setTaxable(js.getBoolean("taxable"));
        productType.setWithSerialNumber(js.getBoolean("withSerialNumber"));
        return productType;
    }

    @Override
    public JsonObject toJson(final ProductType o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("afectStock",  o.getAfectStock() );
        jso.put("description",  o.getDescription() );
        jso.put("fastKey",  o.getFastKey() );
        jso.put("isService",  o.getIsService() );
        jso.put("pname",  o.getPname() );
        jso.put("taxable",  o.getTaxable() );
        jso.put("withSerialNumber",  o.getWithSerialNumber() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "product_type_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "product_type_pkey");
    slcb.doEqInPSimple( "pkey", "product_type_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "product_type_pname");
    slcb.doEqInPSimple( "pname", "product_type_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "product_type_fast_key");
    slcb.doEqInPSimple( "fastKey", "product_type_fast_key");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"product_type");
        sz0.addField("COUNT(product_type.id) as c_product_type_id").addName("count");
        
        
        return sz0;
    }
}
    
        