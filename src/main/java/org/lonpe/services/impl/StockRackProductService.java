
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
 *   StockRackProductService 
 * 
 */
  
public class StockRackProductService extends AbstractServiceLon<StockRackProduct>{

    private static final String SQLINSERT ="INSERT INTO stock_rack_product(pkey,pname,quantity,serial_number,stock_rack_id,product_id) VALUES ($1,$2,$3,$4,$5,$6) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE stock_rack_product SET pname = $1,quantity = $2,serial_number = $3 WHERE id = $8 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE stock_rack_product SET pname = $1,quantity = $2,serial_number = $3 WHERE pkey = $8 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM stock_rack_product_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM stock_rack_product_view";
    private static final String SQLKEYS = "SELECT stock_rack_product_pkey FROM stock_rack_product_view";
    private static final String SQLIDBYPKEY = "SELECT id from stock_rack_product WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from stock_rack_product WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM stock_rack_product WHERE id = $1 returning id";
    private static final String TABLENAME ="stock_rack_product";
    

    public StockRackProductService() {
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
    private static String sql00 = "SELECT stock_rack_product.id as stock_rack_product_id,
stock_rack_product.pkey as stock_rack_product_pkey,
stock_rack_product.pname as stock_rack_product_pname,
stock_rack_product.quantity as stock_rack_product_quantity,
stock_rack_product.serial_number as stock_rack_product_serial_number,
stock_rack.id as stock_rack_id,stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname 
  FROM 
  stock_rack_product,
  stock_rack as stock_rack,
  work_space as work_space,
  work_space_group as work_space_group,
  product as product,
  product_type as product_type  
 WHERE 
 stock_rack_product.stock_rack_id = stock_rack.id
 AND stock_rack.work_space_id = work_space.id
 AND work_space.work_space_group_id = work_space_group.id
 AND stock_rack_product.product_id = product.id
 AND product.product_type_id = product_type.id"
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
        
    dcModel.put("dc", "stockRackProduct");

//ID 
    names.add("id");

    sortMapFields.put("id","stock_rack_product_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","stock_rack_product");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("stock_rack_product.stock_rack_product_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","stock_rack_product");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//quantity
    doFieldSort("quantity","quantity","stock_rack_product");               

//Create property quantity       
    final JsonObject quantity = psLong("quantity",true);
 
    ps.add(quantity);
 
//serialNumber
    doField("serialNumber","serial_number","stock_rack_product");               

//Create property serialNumber       
    final JsonObject serialNumber = psString("serialNumber",false);
 
    ps.add(serialNumber);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  stockRack
    doFieldMT0("stock_rack_product","stockRack", "stock_rack");  

    final JsonObject stockRack =  doMto("stockRack","stockRack");        
   
    names.add("stockRack_pname");
    sortMapFields.put( "stockRack_pname", "stock_rack_pname");                
    stockRack.put("pc","pname");          

    mto.add(stockRack);
        

    //1  stock_rack  -- stock_rack_id
    final ZtatUnitInfoLon zStockRack = new ZtatUnitInfoLon("stock_rack_id", "stockRack",  "stock_rack","pname","stock_rack");
    mz1.put("zStockRack", zStockRack);    

//(1)  product
    doFieldMT0("stock_rack_product","product", "product");  

    final JsonObject product =  doMto("product","product");        
   
    names.add("product_pname");
    sortMapFields.put( "product_pname", "product_pname");                
    product.put("pc","pname");          

    mto.add(product);
        

    //1  product  -- product_id
    final ZtatUnitInfoLon zProduct = new ZtatUnitInfoLon("product_id", "product",  "product","pname","product");
    mz1.put("zProduct", zProduct);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  workSpace   workSpace  
    names.add("workSpace_id");          
    names.add("workSpace_pkey");

    final JsonObject workSpaceFromStockRack =   doMto2("workSpace","workSpace","stockRack");        
   
    names.add("workSpace_pname");           
    sortMapFields.put( "workSpace_pname", "work_space_pname");  
    workSpaceFromStockRack.put("pc","pname");    
         
    mto2.add(workSpaceFromStockRack);        

    final ZtatUnitInfoLon2 zWorkSpaceFromStockRack = new ZtatUnitInfoLon2(zStockRack, "work_space_id", "workSpace",  "work_space","pname","work_space");
    mz2.put("zWorkSpaceFromStockRack",zWorkSpaceFromStockRack);

//(2)  productType   productType  
    names.add("productType_id");          
    names.add("productType_pkey");

    final JsonObject productTypeFromProduct =   doMto2("productType","productType","product");        
   
    names.add("productType_pname");           
    sortMapFields.put( "productType_pname", "product_type_pname");  
    productTypeFromProduct.put("pc","pname");    
         
    mto2.add(productTypeFromProduct);        

    final ZtatUnitInfoLon2 zProductTypeFromProduct = new ZtatUnitInfoLon2(zProduct, "product_type_id", "productType",  "product_type","pname","product_type");
    mz2.put("zProductTypeFromProduct",zProductTypeFromProduct);

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           

//(3)   workSpaceGroup   
    names.add("workSpaceGroup_id");          
    names.add("workSpaceGroup_pkey");

    final JsonObject workSpaceGroupFromWorkSpaceFromStockRack =   doMto2("workSpaceGroup","workSpaceGroup","workSpace");        
   
    names.add("workSpaceGroup_pname");            
    sortMapFields.put( "workSpaceGroup_pname", "work_space_group_pname"); 
    workSpaceGroupFromWorkSpaceFromStockRack.put("pc","pname");     
         
    mto3.add(workSpaceGroupFromWorkSpaceFromStockRack);        

     
    final ZtatUnitInfoLon3 zWorkSpaceGroupFromWorkSpaceFromStockRack = new ZtatUnitInfoLon3(zWorkSpaceFromStockRack, "work_space_group_id", "workSpaceGroup",  "work_space_group","pname","work_space_group");
    mz3.put("zWorkSpaceGroupFromWorkSpaceFromStockRack",zWorkSpaceGroupFromWorkSpaceFromStockRack);    

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"InvoiceLineIns","invoiceLineIn"); 
                

        applyOtm(otm,"InvoiceLineOuts","invoiceLineOut"); 
                

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
        jsa.add(r.getLong("stock_rack_product_id") );       
        jsa.add(r.getString("stock_rack_product_pkey") );       
        jsa.add(r.getString("stock_rack_product_pname") );       
        jsa.add(r.getLong("stock_rack_product_quantity") );       
        jsa.add(r.getString("stock_rack_product_serial_number") );
    jsa.add(r.getLong("stock_rack_id"));
    jsa.add(r.getString("stock_rack_pkey"));   
    
        
    jsa.add(r.getString("stock_rack_pname"));
    jsa.add(r.getLong("product_id"));
    jsa.add(r.getString("product_pkey"));   
    
        
    jsa.add(r.getString("product_pname"));
    jsa.add(r.getLong("work_space_id"));
    jsa.add(r.getString("work_space_pkey"));
    

    jsa.add(r.getString("work_space_pname"));
    
    jsa.add(r.getLong("product_type_id"));
    jsa.add(r.getString("product_type_pkey"));
    

    jsa.add(r.getString("product_type_pname"));
    
    jsa.add(r.getLong("work_space_group_id"));
    jsa.add(r.getString("work_space_group_pkey"));
    

    jsa.add(r.getString("work_space_group_pname"));
    
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
        m.put("stockRackProduct_id",LONG);
    }        
//pkey    
    m.put("stockRackProduct_pkey",STRING);              
//pname    
    m.put("stockRackProduct_pname",STRING);              
//quantity    
    m.put("stockRackProduct_quantity",LONG);              
//serialNumber    
    m.put("stockRackProduct_serialNumber",STRING);          
    if(level<1){
        return m;    
    }       
// stockRack   stockRack
    if(withIds){
        m.put("stockRack_id",LONG);                       
    }
    m.put("stockRack_pkey",STRING);     
    m.put("stockRack_pname",STRING);   
// product   product
    if(withIds){
        m.put("product_id",LONG);                       
    }
    m.put("product_pkey",STRING);     
    m.put("product_pname",STRING);  
//[2] workSpace --   workSpace
    if(withIds){
        m.put("workSpace_id",LONG);              
    }              
    m.put("workSpace_pkey",STRING);  
        
    m.put("workSpace_pname",STRING);  
//[2] productType --   productType
    if(withIds){
        m.put("productType_id",LONG);              
    }              
    m.put("productType_pkey",STRING);  
        
    m.put("productType_pname",STRING);  
//[3] workSpaceGroup --   workSpaceGroup
    if(withIds){
        m.put("workSpaceGroup_id",LONG);              
    }              
    m.put("workSpaceGroup_pkey",STRING);  
        
    m.put("workSpaceGroup_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"stock_rack_product_id", nc++); 
    }            //pkey       
            sToCell(r, row,"stock_rack_product_pkey", nc++);     //pname       
            sToCell(r, row,"stock_rack_product_pname", nc++);     //quantity            
            ldToCell(r, row,"stock_rack_product_quantity", nc++);     //serialNumber       
            sToCell(r, row,"stock_rack_product_serial_number", nc++); 
//stockRack   stock_rack        
    if(withIds){
        lToCell(r, row,"stock_rack_id", nc++);
    }
    sToCell(r, row,"stock_rack_pkey", nc++);
    sToCell(r, row,"stock_rack_pname", nc++);
//product   product        
    if(withIds){
        lToCell(r, row,"product_id", nc++);
    }
    sToCell(r, row,"product_pkey", nc++);
    sToCell(r, row,"product_pname", nc++);
// workSpace  work_space
    if(withIds){
       lToCell(r, row,"work_space_id", nc++);
    }

    sToCell(r, row,"work_space_pkey", nc++);

    sToCell(r, row,"work_space_pname", nc++);
// productType  product_type
    if(withIds){
       lToCell(r, row,"product_type_id", nc++);
    }

    sToCell(r, row,"product_type_pkey", nc++);

    sToCell(r, row,"product_type_pname", nc++);
// workSpaceGroup  work_space_group
    if(withIds){
       lToCell(r, row,"work_space_group_id", nc++);
    }

    sToCell(r, row,"work_space_group_pkey", nc++);

    sToCell(r, row,"work_space_group_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE stock_rack_product_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final StockRackProduct dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());        
    t.addLong(dc0.getQuantity());        
    t.addString(dc0.getSerialNumber());   
    if(dc0.getStockRack()!=null){
       t.addLong(dc0.getStockRack().getId());
    }   
    if(dc0.getProduct()!=null){
       t.addLong(dc0.getProduct().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final StockRackProduct dc0, final Tuple t){
        
    t.addString(dc0.getPname());
    t.addLong(dc0.getQuantity());
    t.addString(dc0.getSerialNumber());   
//      if(dc0.getStockRack()!=null){
//            t.addLong(dc0.getStockRack().getId());
//      }   
//      if(dc0.getProduct()!=null){
//            t.addLong(dc0.getProduct().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);

    fTLong("quantity", obj, t);

    fTString("serialNumber", obj, t);

    fTLong("stockRack_id",obj,t);

    fTLong("product_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> stockRack = mapParents.get("stockRack");
            final Long stockRack_id = stockRack.get((String)obj.get("stockRack_pkey"));
            obj.put("stockRack_id", stockRack_id);
      
            final Map<String, Long> product = mapParents.get("product");
            final Long product_id = product.get((String)obj.get("product_pkey"));
            obj.put("product_id", product_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("pname", js, t);
    fTLong("quantity", js, t);
    fTString("serialNumber", js, t);     
    fTLong("stockRack_id",js,t);     
    fTLong("product_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);
fTLong("quantity", js, t);
fTString("serialNumber", js, t);

            //     fTLong("stockRack_id",js,t);

            //     fTLong("product_id",js,t);
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
    public StockRackProduct doFrom(final Row r){
        final StockRackProduct stockRackProduct = new StockRackProduct();
         stockRackProduct.setId(r.getLong("stock_rack_product_id"));         
                stockRackProduct.setPkey(  r.getString("stock_rack_product_pkey"));                       
                stockRackProduct.setPname(  r.getString("stock_rack_product_pname"));                       
                stockRackProduct.setQuantity(  r.getLong("stock_rack_product_quantity"));                       
                stockRackProduct.setSerialNumber(  r.getString("stock_rack_product_serial_number"));              
        final StockRack stockRack = new StockRack();
        stockRack.setId(r.getLong("stock_rack_id"));
        stockRack.setPkey(r.getString("stock_rack_pkey"));
        
        stockRack.setPname(r.getString("stock_rack_pname"));
        stockRackProduct.setStockRack(stockRack);
        
        final Product product = new Product();
        product.setId(r.getLong("product_id"));
        product.setPkey(r.getString("product_pkey"));
        
        product.setPname(r.getString("product_pname"));
        stockRackProduct.setProduct(product);
        
        final WorkSpace workSpace = new WorkSpace();
        workSpace.setId(r.getLong("work_space_id"));
        workSpace.setPkey(r.getString("work_space_pkey"));
        workSpace.setPname(r.getString("work_space_pname"));
 
        stockRack.setWorkSpace(workSpace); 
        final ProductType productType = new ProductType();
        productType.setId(r.getLong("product_type_id"));
        productType.setPkey(r.getString("product_type_pkey"));
        productType.setPname(r.getString("product_type_pname"));
 
        product.setProductType(productType);   
        return stockRackProduct;
    }
    
    @Override
    public StockRackProduct doFromJson(final JsonObject js){
        StockRackProduct stockRackProduct = new StockRackProduct();
        stockRackProduct.setId(js.getLong("id"));
        
                
                stockRackProduct.setPkey(js.getString("pkey"));        
                stockRackProduct.setPname(js.getString("pname"));        
                stockRackProduct.setQuantity(js.getLong("quantity"));        
                stockRackProduct.setSerialNumber(js.getString("serialNumber"));        
            stockRackProduct.setId(js.getLong("stockRack_id"));        
            stockRackProduct.setId(js.getLong("product_id"));
        return stockRackProduct;
    }

    @Override
    public JsonObject toJson(final StockRackProduct o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );
        jso.put("quantity",  o.getQuantity() );
        jso.put("serialNumber",  o.getSerialNumber() );

            final StockRack stockRack = o.getStockRack();
            if(stockRack!=null){
                jso.put("stockRack_id", stockRack.getId());
                jso.put("stockRack_pkey", stockRack.getPkey());
            }
            

            final Product product = o.getProduct();
            if(product!=null){
                jso.put("product_id", product.getId());
                jso.put("product_pkey", product.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "stock_rack_product_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "stock_rack_product_pkey");
    slcb.doEqInPSimple( "pkey", "stock_rack_product_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "stock_rack_product_pname");
    slcb.doEqInPSimple( "pname", "stock_rack_product_pname");               
    slcb.doGEPSimpleLong( "quantity", "stock_rack_product_quantity");
    slcb.doLPSimpleLong( "quantity", "stock_rack_product_quantity");                
        
        slcb.doIlPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doEQPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doInLongCondition("stockRack_id", "stock_rack_id");  
//StockRack 2        --
        slcb.doIlPSimple2( "stockRack_pname", "stock_rack_pname");    
        
        slcb.doIlPSimple2( "product_pkey", "product_pkey");
        slcb.doEQPSimple2( "product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");  
//Product 3        --
        slcb.doIlPSimple2( "product_pname", "product_pname");    
        
        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");
//WorkSpace 2
        slcb.doIlPSimple2( "workSpace_pname", "work_space_pname"); 
        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id"); 
        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");
//ProductType 5
        slcb.doIlPSimple2( "productType_pname", "product_type_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"stock_rack_product");
        sz0.addField("COUNT(stock_rack_product.id) as c_stock_rack_product_id").addName("count");
        
    sz0.addField("sum(stock_rack_product.quantity) as sum_stock_rack_product_quantity").addName("sum_quantity"); 
        
//level 1
             
    sz0.applyG1(mz1.get("zStockRack"))   ;               
    sz0.applyG1(mz1.get("zProduct"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zWorkSpaceFromStockRack"));                           
    sz0.applyG2(mz2.get("zProductTypeFromProduct"));                           
//level 3    
        sz0.applyG3(mz3.get("zWorkSpaceGroupFromWorkSpaceFromStockRack"));               
        return sz0;
    }
}
    
        