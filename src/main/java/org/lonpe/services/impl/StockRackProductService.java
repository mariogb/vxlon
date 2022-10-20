
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
    
//1
    private static final ZtatUnitInfoLon zStockRack;

//1
    private static final ZtatUnitInfoLon zProduct;

//2
    private static final ZtatUnitInfoLon zWorkSpace;

//2
    private static final ZtatUnitInfoLon zProductType;

//3
    private static final ZtatUnitInfoLon zWorkSpaceGroup;
    
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
    
    private static String sql00 = "SELECT stock_rack_product.id as stock_rack_product_id,
stock_rack_product.pkey as stock_rack_product_pkey,
stock_rack_product.pname as stock_rack_product_pname,
stock_rack_product.quantity as stock_rack_product_quantity,
stock_rack_product.serial_number as stock_rack_product_serial_number,
stock_rack.id as stock_rack_id,stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname 
  FROM 
  stock_rack_product,
  stock_rack as stock_rack,
  product as product,
  work_space as work_space,
  product_type as product_type,
  work_space_group as work_space_group  
 WHERE 
 stock_rack_product.stock_rack_id = stock_rack.id
 AND stock_rack_product.product_id = product.id
 AND stock_rack.work_space_id = work_space.id
 AND product.product_type_id = product_type.id
 AND work_space.work_space_group_id = work_space_group.id; 
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

        
    dcModel.put("dc", "stockRackProduct");

//ID 
    names.add("id");

    sortMapFields.put("id","stock_rack_product_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("stock_rack_product.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("stock_rack_product.stock_rack_product_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "stock_rack_product_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("stock_rack_product.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "stock_rack_product_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//quantity
    names.add("quantity");
    insertMapFields.put("stock_rack_product.quantity","quantity");  

//Create property quantity       
    final JsonObject quantity = ps00a("quantity", "Long",true);

    sortMapFields.put("quantity", "stock_rack_product_quantity");               
 
    ps.add(quantity);
 
//serialNumber
    names.add("serialNumber");
    insertMapFields.put("stock_rack_product.serial_number","serialNumber");  

//Create property serialNumber       
    final JsonObject serialNumber = ps00a("serialNumber", "String",false);
 
    ps.add(serialNumber);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  stockRack --------------------
    names.add("stockRack_id");      
    insertMapFields.put("stock_rack_product.stock_rack_id","stockRack_id");    
       
    names.add("stockRack_pkey");        
    sortMapFields.put( "stockRack_pkey", "stock_rack_pkey");        

    final JsonObject stockRack =  doMto("stockRack","stockRack");        
   
    names.add("stockRack_pname");
    sortMapFields.put( "stockRack_pname", "stock_rack_pname");         

    stockRack.put("pc","pname");          

    mto.add(stockRack);
        

//(1)  product --------------------
    names.add("product_id");      
    insertMapFields.put("stock_rack_product.product_id","product_id");    
       
    names.add("product_pkey");        
    sortMapFields.put( "product_pkey", "product_pkey");        

    final JsonObject product =  doMto("product","product");        
   
    names.add("product_pname");
    sortMapFields.put( "product_pname", "product_pname");         

    product.put("pc","pname");          

    mto.add(product);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   workSpace 
        
    names.add("workSpace_id");          
    names.add("workSpace_pkey");

    final JsonObject workSpace =   doMto2("workSpace","workSpace","stockRack");        
   
    names.add("workSpace_pname");  
    workSpace.put("pc","pname");             
   
    sortMapFields.put( "workSpace_pname", "work_space_pname");            
         
    mto2.add(workSpace);        
//(2)   productType 
        
    names.add("productType_id");          
    names.add("productType_pkey");

    final JsonObject productType =   doMto2("productType","productType","product");        
   
    names.add("productType_pname");  
    productType.put("pc","pname");             
   
    sortMapFields.put( "productType_pname", "product_type_pname");            
         
    mto2.add(productType);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   workSpaceGroup 
        
    names.add("workSpaceGroup_id");          
    names.add("workSpaceGroup_pkey");

    final JsonObject workSpaceGroup =   doMto2("workSpaceGroup","workSpaceGroup","workSpace");        
   
    names.add("workSpaceGroup_pname");  
    workSpaceGroup.put("pc","pname");             
   
    sortMapFields.put( "workSpaceGroup_pname", "work_space_group_pname");            
         
    mto3.add(workSpaceGroup);        

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"InvoiceLineIns","invoiceLineIn"); 
                

        applyOtm(otm,"InvoiceLineOuts","invoiceLineOut"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

        
//1  stock_rack  -- stock_rack_id
    zStockRack = new ZtatUnitInfoLon("stock_rack_id", "stockRack",  "stock_rack","pname","stock_rack");

//1  product  -- product_id
    zProduct = new ZtatUnitInfoLon("product_id", "product",  "product","pname","product");

//2    
    zWorkSpace = new ZtatUnitInfoLon("work_space_id", "workSpace",  "work_space","pname","work_space");

//2    
    zProductType = new ZtatUnitInfoLon("product_type_id", "productType",  "product_type","pname","product_type");

//3
    zWorkSpaceGroup = new ZtatUnitInfoLon("work_space_group_id", "workSpaceGroup",  "work_space_group","pname","work_space_group");

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
    public void fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m_ = new LinkedHashMap<>();
    if(withIds){
                m_.put("stockRackProduct_id","Long");
            }
            
    //pkey       
            m_.put("stockRackProduct_pkey","String"); 
            
    //pname       
            m_.put("stockRackProduct_pname","String"); 
            
    //quantity       
            m_.put("stockRackProduct_quantity","Long"); 
            
    //serialNumber       
            m_.put("stockRackProduct_serialNumber","String"); 
            
if(level<1){
                return m_;    
            }
            
 // stockRack
if(withIds){
            m_.put("stockRack_id","Long");   
                    
            }

        m_.put("stockRack_pkey","String");   
        

            m_.put("stockRack_pname","String");   
            
 // product
if(withIds){
            m_.put("product_id","Long");   
                    
            }

        m_.put("product_pkey","String");   
        

            m_.put("product_pname","String");   
            
//[2] workSpace  

        if(level>1){
            if(withIds){
               m_.put("workSpace_id","Long");              
            }      
        
        m_.put("workSpace_pkey","String");  

            m_.put("workSpace_pname","String");    
 
                      }             
//[2] productType  

        if(level>1){
            if(withIds){
               m_.put("productType_id","Long");              
            }      
        
        m_.put("productType_pkey","String");  

            m_.put("productType_pname","String");    
 
                      }             
//[3] workSpaceGroup  

        if(level>2){
            if(withIds){
               m_.put("workSpaceGroup_id","Long");              
            }      
        
        m_.put("workSpaceGroup_pkey","String");  

            m_.put("workSpaceGroup_pname","String");    
 
                      }             
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"stock_rack_product_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"stock_rack_product_pkey", nc++); 
    //pname       
            sToCell(r, row,"stock_rack_product_pname", nc++); 
    //quantity            
            ldToCell(r, row,"stock_rack_product_quantity", nc++); 
    //serialNumber       
            sToCell(r, row,"stock_rack_product_serial_number", nc++); 
 // stockRack
if(withIds){
                    lToCell(r, row,"stock_rack_id", nc++);
                 }
sToCell(r, row,"stock_rack_pkey", nc++);
sToCell(r, row,"stock_rack_pname", nc++);
 // product
if(withIds){
                    lToCell(r, row,"product_id", nc++);
                 }
sToCell(r, row,"product_pkey", nc++);
sToCell(r, row,"product_pname", nc++);
// workSpace
if(withIds){
            lToCell(r, row,"work_space_id", nc++);
        }
sToCell(r, row,"work_space_pkey", nc++);
sToCell(r, row,"work_space_pname", nc++);
// productType
if(withIds){
            lToCell(r, row,"product_type_id", nc++);
        }
sToCell(r, row,"product_type_pkey", nc++);
sToCell(r, row,"product_type_pname", nc++);
// workSpaceGroup
if(withIds){
            lToCell(r, row,"work_space_group_id", nc++);
        }
sToCell(r, row,"work_space_group_pkey", nc++);
sToCell(r, row,"work_space_group_pname", nc++);
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
//StockRack 2        
        slcb.doIlPSimple2( "stockRack_pname", "stock_rack_pname");    
        slcb.doIlPSimple2( "product_pkey", "product_pkey");
        slcb.doEQPSimple2( "product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");  
//Product 3        
        slcb.doIlPSimple2( "product_pname", "product_pname");    

        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");//WorkSpace 2
        slcb.doIlPSimple2( "workSpace_pname", "work_space_pname"); 
        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id"); 

        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");//ProductType 5
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
             
    sz0.applyG1(zStockRack);               
    sz0.applyG1(zProduct);      
    //level 2
    
    sz0.applyG2(zStockRack,zWorkSpace);                           
    sz0.applyG2(zProduct,zProductType);                           
    //level 3
    
        sz0.applyG3(zStockRack,zWorkSpace,zWorkSpaceGroup);               
        return sz0;
    }
}
    
        