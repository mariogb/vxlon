
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
 *   ProductService 
 * 
 */
  
public class ProductService extends AbstractServiceLon<Product>{

    private static final String SQLINSERT ="INSERT INTO product(pkey,description,fast_key,pname,sku,product_type_id) VALUES ($1,$2,$3,$4,$5,$6) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE product SET description = $1,fast_key = $2,pname = $3,sku = $4 WHERE id = $7 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE product SET description = $1,fast_key = $2,pname = $3,sku = $4 WHERE pkey = $7 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM product_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM product_view";
    private static final String SQLKEYS = "SELECT product_pkey FROM product_view";
    private static final String SQLIDBYPKEY = "SELECT id from product WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from product WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM product WHERE id = $1 returning id";
    private static final String TABLENAME ="product";
    

    public ProductService() {
        init0();
    }

    
    private static final Map<String, ZtatUnitInfoLon> mz1 = new HashMap<>(6);                       

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
    private static String sql00 = "SELECT product.id as product_id,
product.pkey as product_pkey,
product.description as product_description,
product.fast_key as product_fast_key,
product.pname as product_pname,
product.sku as product_sku,
product_type.id as product_type_id,product_type.pkey as product_type_pkey,product_type.pname as product_type_pname 
  FROM 
  product,
  product_type as product_type  
 WHERE 
 product.product_type_id = product_type.id"
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
        
    dcModel.put("dc", "product");

//ID 
    names.add("id");

    sortMapFields.put("id","product_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","product");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("product.product_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//description
    doField("description","description","product");               

//Create property description       
    final JsonObject description = psString("description",false);
 
    ps.add(description);
 
//fastKey
    doField("fastKey","fast_key","product");               

//Create property fastKey       
    final JsonObject fastKey = psString("fastKey",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//pname
    doFieldSort("pname","pname","product");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//sku
    doField("sku","sku","product");               

//Create property sku       
    final JsonObject sku = psString("sku",false);

// hasIndex 
    sku.put("withIndex",true);  
 
    ps.add(sku);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  productType
    doFieldMT0("product","productType", "product_type");  

    final JsonObject productType =  doMto("productType","productType");        
   
    names.add("productType_pname");
    sortMapFields.put( "productType_pname", "product_type_pname");                
    productType.put("pc","pname");          

    mto.add(productType);
        

    //1  product_type  -- product_type_id
    final ZtatUnitInfoLon zProductType = new ZtatUnitInfoLon("product_type_id", "productType",  "product_type","pname","product_type");
    mz1.put("zProductType", zProductType);    

    dcModel.put("mto",mto);     
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"invoiceLineIns","invoiceLineIn"); 
                

        applyOtm(otm,"invoiceLineOuts","invoiceLineOut"); 
                

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
        jsa.add(r.getLong("product_id") );       
        jsa.add(r.getString("product_pkey") );       
        jsa.add(r.getString("product_description") );       
        jsa.add(r.getString("product_fast_key") );       
        jsa.add(r.getString("product_pname") );       
        jsa.add(r.getString("product_sku") );
    jsa.add(r.getLong("product_type_id"));
    jsa.add(r.getString("product_type_pkey"));   
    
        
    jsa.add(r.getString("product_type_pname"));
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
        m.put("product_id",LONG);
    }        
//pkey    
    m.put("product_pkey",STRING);              
//description    
    m.put("product_description",STRING);              
//fastKey    
    m.put("product_fastKey",STRING);              
//pname    
    m.put("product_pname",STRING);              
//sku    
    m.put("product_sku",STRING);          
    if(level<1){
        return m;    
    }       
// productType   productType
    if(withIds){
        m.put("productType_id",LONG);                       
    }
    m.put("productType_pkey",STRING);     
    m.put("productType_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"product_id", nc++); 
    }            //pkey       
            sToCell(r, row,"product_pkey", nc++);     //description       
            sToCell(r, row,"product_description", nc++);     //fastKey       
            sToCell(r, row,"product_fast_key", nc++);     //pname       
            sToCell(r, row,"product_pname", nc++);     //sku       
            sToCell(r, row,"product_sku", nc++); 
//productType   product_type        
    if(withIds){
        lToCell(r, row,"product_type_id", nc++);
    }
    sToCell(r, row,"product_type_pkey", nc++);
    sToCell(r, row,"product_type_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE product_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Product dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getDescription());        
    t.addString(dc0.getFastKey());        
    t.addString(dc0.getPname());        
    t.addString(dc0.getSku());   
    if(dc0.getProductType()!=null){
       t.addLong(dc0.getProductType().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final Product dc0, final Tuple t){
        
    t.addString(dc0.getDescription());
    t.addString(dc0.getFastKey());
    t.addString(dc0.getPname());
    t.addString(dc0.getSku());   
//      if(dc0.getProductType()!=null){
//            t.addLong(dc0.getProductType().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("description", obj, t);

    fTString("fastKey", obj, t);

    fTString("pname", obj, t);

    fTString("sku", obj, t);

    fTLong("productType_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> productType = mapParents.get("productType");
            final Long productType_id = productType.get((String)obj.get("productType_pkey"));
            obj.put("productType_id", productType_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("description", js, t);
    fTString("fastKey", js, t);
    fTString("pname", js, t);
    fTString("sku", js, t);     
    fTLong("productType_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("description", js, t);
fTString("fastKey", js, t);
fTString("pname", js, t);
fTString("sku", js, t);

            //     fTLong("productType_id",js,t);
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
    public Product doFrom(final Row r){
        final Product product = new Product();
         product.setId(r.getLong("product_id"));         
                product.setPkey(  r.getString("product_pkey"));                       
                product.setDescription(  r.getString("product_description"));                       
                product.setFastKey(  r.getString("product_fast_key"));                       
                product.setPname(  r.getString("product_pname"));                       
                product.setSku(  r.getString("product_sku"));              
        final ProductType productType = new ProductType();
        productType.setId(r.getLong("product_type_id"));
        productType.setPkey(r.getString("product_type_pkey"));
        
        productType.setPname(r.getString("product_type_pname"));
        product.setProductType(productType);
          
        return product;
    }
    
    @Override
    public Product doFromJson(final JsonObject js){
        Product product = new Product();
        product.setId(js.getLong("id"));
        
                
                product.setPkey(js.getString("pkey"));        
                product.setDescription(js.getString("description"));        
                product.setFastKey(js.getString("fastKey"));        
                product.setPname(js.getString("pname"));        
                product.setSku(js.getString("sku"));        
            product.setId(js.getLong("productType_id"));
        return product;
    }

    @Override
    public JsonObject toJson(final Product o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("description",  o.getDescription() );
        jso.put("fastKey",  o.getFastKey() );
        jso.put("pname",  o.getPname() );
        jso.put("sku",  o.getSku() );

            final ProductType productType = o.getProductType();
            if(productType!=null){
                jso.put("productType_id", productType.getId());
                jso.put("productType_pkey", productType.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "product_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "product_pkey");
    slcb.doEqInPSimple( "pkey", "product_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "product_pname");
    slcb.doEqInPSimple( "pname", "product_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "product_fast_key");
    slcb.doEqInPSimple( "fastKey", "product_fast_key");                     
// withIndex true
    slcb.doIlPSimple2( "sku", "product_sku");
    slcb.doEqInPSimple( "sku", "product_sku");                    
        
        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");  
//ProductType 5        --
        slcb.doIlPSimple2( "productType_pname", "product_type_pname");    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"product");
        sz0.addField("COUNT(product.id) as c_product_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(mz1.get("zProductType"))   ;      
//level 2    
//level 3    
        return sz0;
    }
}
    
        