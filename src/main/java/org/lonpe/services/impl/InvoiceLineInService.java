
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

import java.time.LocalDateTime;
import java.math.BigDecimal;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;

/**
 *   InvoiceLineInService 
 * 
 */
   
  
public class InvoiceLineInService extends AbstractServiceLon<InvoiceLineIn>{

    private static final String SQLINSERT ="INSERT INTO invoice_line_in(pkey,ask_quantity,created_date,descount,invoice_date,orden,status,supply_date,supply_quantity,tax_porcent,total,total_cost,unit_cost,comercial_document_id,product_id,stock_rack_product_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE invoice_line_in SET ask_quantity = $1,created_date = $2,descount = $3,invoice_date = $4,orden = $5,status = $6,supply_date = $7,supply_quantity = $8,tax_porcent = $9,total = $10,total_cost = $11,unit_cost = $12 WHERE id = $19 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE invoice_line_in SET ask_quantity = $1,created_date = $2,descount = $3,invoice_date = $4,orden = $5,status = $6,supply_date = $7,supply_quantity = $8,tax_porcent = $9,total = $10,total_cost = $11,unit_cost = $12 WHERE pkey = $19 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM invoice_line_in_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM invoice_line_in_view";
    private static final String SQLKEYS = "SELECT invoice_line_in_pkey FROM invoice_line_in_view";
    private static final String SQLIDBYPKEY = "SELECT id from invoice_line_in WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from invoice_line_in WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM invoice_line_in WHERE id = $1 returning id";
    private static final String TABLENAME ="invoice_line_in";
    
//1
    private static final ZtatUnitInfoLon zComercialDocument;

//1
    private static final ZtatUnitInfoLon zProduct;

//1
    private static final ZtatUnitInfoLon zStockRackProduct;

//2
    private static final ZtatUnitInfoLon zContract;

//2
    private static final ZtatUnitInfoLon zUserAutor;

//2
    private static final ZtatUnitInfoLon zComercialDocumentType;

//2
    private static final ZtatUnitInfoLon zProductType;

//2
    private static final ZtatUnitInfoLon zStockRack;

//3
    private static final ZtatUnitInfoLon zDepartamentBaseTimePeriod;

//3
    private static final ZtatUnitInfoLon zThirdPerson;

//3
    private static final ZtatUnitInfoLon zWorkSpace;
    
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
    
    private static String sql00 = "SELECT invoice_line_in.id as invoice_line_in_id,
invoice_line_in.pkey as invoice_line_in_pkey,
invoice_line_in.ask_quantity as invoice_line_in_ask_quantity,
invoice_line_in.created_date as invoice_line_in_created_date,
invoice_line_in.descount as invoice_line_in_descount,
invoice_line_in.invoice_date as invoice_line_in_invoice_date,
invoice_line_in.orden as invoice_line_in_orden,
invoice_line_in.status as invoice_line_in_status,
invoice_line_in.supply_date as invoice_line_in_supply_date,
invoice_line_in.supply_quantity as invoice_line_in_supply_quantity,
invoice_line_in.tax_porcent as invoice_line_in_tax_porcent,
invoice_line_in.total as invoice_line_in_total,
invoice_line_in.total_cost as invoice_line_in_total_cost,
invoice_line_in.unit_cost as invoice_line_in_unit_cost,
comercial_document.id as comercial_document_id,comercial_document.pkey as comercial_document_pkey,comercial_document.pname as comercial_document_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,
stock_rack.id as stock_rack_id, stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname 
  FROM 
  invoice_line_in,
  comercial_document_out as comercial_document,
  product as product,
  stock_rack_product as stock_rack_product,
  contract_out as contract,
  user_lon as user_autor,
  comercial_document_type_out as comercial_document_type,
  product_type as product_type,
  stock_rack as stock_rack,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  work_space as work_space  
 WHERE 
 invoice_line_in.comercial_document_id = comercial_document.id
 AND invoice_line_in.product_id = product.id
 AND invoice_line_in.stock_rack_product_id = stock_rack_product.id
 AND comercial_document.contract_id = contract.id
 AND comercial_document.user_autor_id = user_autor.id
 AND comercial_document.comercial_document_type_id = comercial_document_type.id
 AND product.product_type_id = product_type.id
 AND stock_rack_product.stock_rack_id = stock_rack.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND stock_rack.work_space_id = work_space.id; 
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

        
    dcModel.put("dc", "invoiceLineIn");

//ID 
    names.add("id");

    sortMapFields.put("id","invoice_line_in_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("invoice_line_in.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("invoice_line_in.invoice_line_in_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "invoice_line_in_pkey");                   
 
    ps.add(pkey);
 
//askQuantity
    names.add("askQuantity");
    insertMapFields.put("invoice_line_in.ask_quantity","askQuantity");  

//Create property askQuantity       
    final JsonObject askQuantity = ps00a("askQuantity", "BigDecimal",true);

    sortMapFields.put("askQuantity", "invoice_line_in_ask_quantity");               
 
    ps.add(askQuantity);
 
//createdDate
    names.add("createdDate");
    insertMapFields.put("invoice_line_in.created_date","createdDate");  

//Create property createdDate       
    final JsonObject createdDate = ps00a("createdDate", "LocalDateTime",true);

    sortMapFields.put("createdDate", "invoice_line_in_created_date");               
 
//Set by system
    createdDate.put("setBySys","now");  
 
    ps.add(createdDate);
 
//descount
    names.add("descount");
    insertMapFields.put("invoice_line_in.descount","descount");  

//Create property descount       
    final JsonObject descount = ps00a("descount", "BigDecimal",true);

    sortMapFields.put("descount", "invoice_line_in_descount");               
 
    ps.add(descount);
 
//invoiceDate
    names.add("invoiceDate");
    insertMapFields.put("invoice_line_in.invoice_date","invoiceDate");  

//Create property invoiceDate       
    final JsonObject invoiceDate = ps00a("invoiceDate", "LocalDateTime",false);

    sortMapFields.put("invoiceDate", "invoice_line_in_invoice_date");               
 
    ps.add(invoiceDate);
 
//orden
    names.add("orden");
    insertMapFields.put("invoice_line_in.orden","orden");  

//Create property orden       
    final JsonObject orden = ps00a("orden", "Integer",true);

    sortMapFields.put("orden", "invoice_line_in_orden");               
 
    ps.add(orden);
 
//status
    names.add("status");
    insertMapFields.put("invoice_line_in.status","status");  

//Create property status       
    final JsonObject status = ps00a("status", "String",true);

    final JsonArray statusInList = new JsonArray();
                statusInList.add("PENDENT"); 
statusInList.add("SUPPLIED"); 
statusInList.add("CANCEL"); 
    status.put("inList",statusInList );                
 
    ps.add(status);
 
//supplyDate
    names.add("supplyDate");
    insertMapFields.put("invoice_line_in.supply_date","supplyDate");  

//Create property supplyDate       
    final JsonObject supplyDate = ps00a("supplyDate", "LocalDateTime",false);

    sortMapFields.put("supplyDate", "invoice_line_in_supply_date");               
 
    ps.add(supplyDate);
 
//supplyQuantity
    names.add("supplyQuantity");
    insertMapFields.put("invoice_line_in.supply_quantity","supplyQuantity");  

//Create property supplyQuantity       
    final JsonObject supplyQuantity = ps00a("supplyQuantity", "BigDecimal",true);

    sortMapFields.put("supplyQuantity", "invoice_line_in_supply_quantity");               
 
    ps.add(supplyQuantity);
 
//taxPorcent
    names.add("taxPorcent");
    insertMapFields.put("invoice_line_in.tax_porcent","taxPorcent");  

//Create property taxPorcent       
    final JsonObject taxPorcent = ps00a("taxPorcent", "BigDecimal",true);

    sortMapFields.put("taxPorcent", "invoice_line_in_tax_porcent");               
 
    ps.add(taxPorcent);
 
//total
    names.add("total");
    insertMapFields.put("invoice_line_in.total","total");  

//Create property total       
    final JsonObject total = ps00a("total", "BigDecimal",true);

    sortMapFields.put("total", "invoice_line_in_total");               
 
    ps.add(total);
 
//totalCost
    names.add("totalCost");
    insertMapFields.put("invoice_line_in.total_cost","totalCost");  

//Create property totalCost       
    final JsonObject totalCost = ps00a("totalCost", "BigDecimal",true);

    sortMapFields.put("totalCost", "invoice_line_in_total_cost");               
 
    ps.add(totalCost);
 
//unitCost
    names.add("unitCost");
    insertMapFields.put("invoice_line_in.unit_cost","unitCost");  

//Create property unitCost       
    final JsonObject unitCost = ps00a("unitCost", "BigDecimal",true);

    sortMapFields.put("unitCost", "invoice_line_in_unit_cost");               
 
    ps.add(unitCost);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  comercialDocument --------------------
    names.add("comercialDocument_id");      
    insertMapFields.put("invoice_line_in.comercial_document_id","comercialDocument_id");    
       
    names.add("comercialDocument_pkey");        
    sortMapFields.put( "comercialDocument_pkey", "comercial_document_pkey");        

    final JsonObject comercialDocument =  doMto("comercialDocument","comercialDocumentOut");        
   
    names.add("comercialDocument_pname");
    sortMapFields.put( "comercialDocument_pname", "comercial_document_pname");         

    comercialDocument.put("pc","pname");          

    mto.add(comercialDocument);
        

//(1)  product --------------------
    names.add("product_id");      
    insertMapFields.put("invoice_line_in.product_id","product_id");    
       
    names.add("product_pkey");        
    sortMapFields.put( "product_pkey", "product_pkey");        

    final JsonObject product =  doMto("product","product");        
   
    names.add("product_pname");
    sortMapFields.put( "product_pname", "product_pname");         

    product.put("pc","pname");          

    mto.add(product);
        

//(1)  stockRackProduct --------------------
    names.add("stockRackProduct_id");      
    insertMapFields.put("invoice_line_in.stock_rack_product_id","stockRackProduct_id");    
       
    names.add("stockRackProduct_pkey");        
    sortMapFields.put( "stockRackProduct_pkey", "stock_rack_product_pkey");        

    final JsonObject stockRackProduct =  doMto("stockRackProduct","stockRackProduct");        
   
    names.add("stockRackProduct_pname");
    sortMapFields.put( "stockRackProduct_pname", "stock_rack_product_pname");         

    stockRackProduct.put("pc","pname");          

    mto.add(stockRackProduct);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   contract 
        
    names.add("contract_id");          
    names.add("contract_pkey");

    final JsonObject contract =   doMto2("contract","contractOut","comercialDocument");        
   
    names.add("contract_pname");  
    contract.put("pc","pname");             
   
    sortMapFields.put( "contract_pname", "contract_pname");            
         
    mto2.add(contract);        
//(2)   userAutor 
        
    names.add("userAutor_id");          
    names.add("userAutor_pkey");

    final JsonObject userAutor =   doMto2("userAutor","userLon","comercialDocument");        
   
    names.add("userAutor_pname");  
    userAutor.put("pc","pname");             
   
    sortMapFields.put( "userAutor_pname", "user_autor_pname");            
         
    mto2.add(userAutor);        
//(2)   comercialDocumentType 
        
    names.add("comercialDocumentType_id");          
    names.add("comercialDocumentType_pkey");

    final JsonObject comercialDocumentType =   doMto2("comercialDocumentType","comercialDocumentTypeOut","comercialDocument");        
   
    names.add("comercialDocumentType_pname");  
    comercialDocumentType.put("pc","pname");             
   
    sortMapFields.put( "comercialDocumentType_pname", "comercial_document_type_pname");            
         
    mto2.add(comercialDocumentType);        
//(2)   productType 
        
    names.add("productType_id");          
    names.add("productType_pkey");

    final JsonObject productType =   doMto2("productType","productType","product");        
   
    names.add("productType_pname");  
    productType.put("pc","pname");             
   
    sortMapFields.put( "productType_pname", "product_type_pname");            
         
    mto2.add(productType);        
//(2)   stockRack 
        
    names.add("stockRack_id");          
    names.add("stockRack_pkey");

    final JsonObject stockRack =   doMto2("stockRack","stockRack","stockRackProduct");        
   
    names.add("stockRack_pname");  
    stockRack.put("pc","pname");             
   
    sortMapFields.put( "stockRack_pname", "stock_rack_pname");            
         
    mto2.add(stockRack);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   departamentBaseTimePeriod 
        
    names.add("departamentBaseTimePeriod_id");          
    names.add("departamentBaseTimePeriod_pkey");

    final JsonObject departamentBaseTimePeriod =   doMto2("departamentBaseTimePeriod","departamentBaseTimePeriod","contract");        
         
    mto3.add(departamentBaseTimePeriod);        
//(3)   thirdPerson 
        
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPerson =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");  
    thirdPerson.put("pc","pname");             
   
    sortMapFields.put( "thirdPerson_pname", "third_person_pname");            
         
    mto3.add(thirdPerson);        
//(3)   workSpace 
        
    names.add("workSpace_id");          
    names.add("workSpace_pkey");

    final JsonObject workSpace =   doMto2("workSpace","workSpace","stockRack");        
   
    names.add("workSpace_pname");  
    workSpace.put("pc","pname");             
   
    sortMapFields.put( "workSpace_pname", "work_space_pname");            
         
    mto3.add(workSpace);        

    dcModel.put("mto3",mto3);       
        

        
//1  comercial_document  -- comercial_document_id
    zComercialDocument = new ZtatUnitInfoLon("comercial_document_id", "comercialDocument",  "comercial_document_out","pname","comercial_document");

//1  product  -- product_id
    zProduct = new ZtatUnitInfoLon("product_id", "product",  "product","pname","product");

//1  stock_rack_product  -- stock_rack_product_id
    zStockRackProduct = new ZtatUnitInfoLon("stock_rack_product_id", "stockRackProduct",  "stock_rack_product","pname","stock_rack_product");

//2    
    zContract = new ZtatUnitInfoLon("contract_id", "contract",  "contract_out","pname","contract");

//2    
    zUserAutor = new ZtatUnitInfoLon("user_autor_id", "userAutor",  "user_lon","pname","user_autor");

//2    
    zComercialDocumentType = new ZtatUnitInfoLon("comercial_document_type_id", "comercialDocumentType",  "comercial_document_type_out","pname","comercial_document_type");

//2    
    zProductType = new ZtatUnitInfoLon("product_type_id", "productType",  "product_type","pname","product_type");

//2    
    zStockRack = new ZtatUnitInfoLon("stock_rack_id", "stockRack",  "stock_rack","pname","stock_rack");

//3
    zDepartamentBaseTimePeriod = new ZtatUnitInfoLon("departament_base_time_period_id", "departamentBaseTimePeriod",  "departament_base_time_period","null","departament_base_time_period");

//3
    zThirdPerson = new ZtatUnitInfoLon("third_person_id", "thirdPerson",  "third_person","pname","third_person");

//3
    zWorkSpace = new ZtatUnitInfoLon("work_space_id", "workSpace",  "work_space","pname","work_space");

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
        jsa.add(r.getLong("invoice_line_in_id") );
        jsa.add(r.getString("invoice_line_in_pkey") );
        jsa.add(r.getBigDecimal("invoice_line_in_ask_quantity") );
        jsa.add(r.getLocalDate("invoice_line_in_created_date").toString() ); // undefined
        jsa.add(r.getBigDecimal("invoice_line_in_descount") );
 asMaybeNullLocalDate(r,"invoice_line_in_invoice_date",jsa); //true
        jsa.add(r.getInteger("invoice_line_in_orden") );
        jsa.add(r.getString("invoice_line_in_status") );
 asMaybeNullLocalDate(r,"invoice_line_in_supply_date",jsa); //true
        jsa.add(r.getBigDecimal("invoice_line_in_supply_quantity") );
        jsa.add(r.getBigDecimal("invoice_line_in_tax_porcent") );
        jsa.add(r.getBigDecimal("invoice_line_in_total") );
        jsa.add(r.getBigDecimal("invoice_line_in_total_cost") );
        jsa.add(r.getBigDecimal("invoice_line_in_unit_cost") );
        jsa.add(r.getLong("comercial_document_id"));
        jsa.add(r.getString("comercial_document_pkey"));
        jsa.add(r.getString("comercial_document_pname"));
        jsa.add(r.getLong("product_id"));
        jsa.add(r.getString("product_pkey"));
        jsa.add(r.getString("product_pname"));
        jsa.add(r.getLong("stock_rack_product_id"));
        jsa.add(r.getString("stock_rack_product_pkey"));
        jsa.add(r.getString("stock_rack_product_pname"));
        jsa.add(r.getLong("contract_id"));
        jsa.add(r.getString("contract_pkey"));
        jsa.add(r.getString("contract_pname"));
        jsa.add(r.getLong("user_autor_id"));
        jsa.add(r.getString("user_autor_pkey"));
        jsa.add(r.getString("user_autor_pname"));
        jsa.add(r.getLong("comercial_document_type_id"));
        jsa.add(r.getString("comercial_document_type_pkey"));
        jsa.add(r.getString("comercial_document_type_pname"));
        jsa.add(r.getLong("product_type_id"));
        jsa.add(r.getString("product_type_pkey"));
        jsa.add(r.getString("product_type_pname"));
        jsa.add(r.getLong("stock_rack_id"));
        jsa.add(r.getString("stock_rack_pkey"));
        jsa.add(r.getString("stock_rack_pname"));
        jsa.add(r.getLong("departament_base_time_period_id"));
        jsa.add(r.getString("departament_base_time_period_pkey"));
        jsa.add(r.getLong("third_person_id"));
        jsa.add(r.getString("third_person_pkey"));
        jsa.add(r.getString("third_person_pname"));
        jsa.add(r.getLong("work_space_id"));
        jsa.add(r.getString("work_space_pkey"));
        jsa.add(r.getString("work_space_pname"));
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
                m_.put("invoiceLineIn_id","Long");
            }
            
    //pkey       
            m_.put("invoiceLineIn_pkey","String"); 
            
    //askQuantity       
            m_.put("invoiceLineIn_askQuantity","BigDecimal"); 
            
    //createdDate       
            m_.put("invoiceLineIn_createdDate","LocalDateTime"); 
            
    //descount       
            m_.put("invoiceLineIn_descount","BigDecimal"); 
            
    //invoiceDate       
            m_.put("invoiceLineIn_invoiceDate","LocalDateTime"); 
            
    //orden       
            m_.put("invoiceLineIn_orden","Integer"); 
            
    //status       
            m_.put("invoiceLineIn_status","String"); 
            
    //supplyDate       
            m_.put("invoiceLineIn_supplyDate","LocalDateTime"); 
            
    //supplyQuantity       
            m_.put("invoiceLineIn_supplyQuantity","BigDecimal"); 
            
    //taxPorcent       
            m_.put("invoiceLineIn_taxPorcent","BigDecimal"); 
            
    //total       
            m_.put("invoiceLineIn_total","BigDecimal"); 
            
    //totalCost       
            m_.put("invoiceLineIn_totalCost","BigDecimal"); 
            
    //unitCost       
            m_.put("invoiceLineIn_unitCost","BigDecimal"); 
            
if(level<1){
                return m_;    
            }
            
 // comercialDocument
if(withIds){
            m_.put("comercialDocument_id","Long");   
                    
            }

        m_.put("comercialDocument_pkey","String");   
        

            m_.put("comercialDocument_pname","String");   
            
 // product
if(withIds){
            m_.put("product_id","Long");   
                    
            }

        m_.put("product_pkey","String");   
        

            m_.put("product_pname","String");   
            
 // stockRackProduct
if(withIds){
            m_.put("stockRackProduct_id","Long");   
                    
            }

        m_.put("stockRackProduct_pkey","String");   
        

            m_.put("stockRackProduct_pname","String");   
            
//[2] contract  

        if(level>1){
            if(withIds){
               m_.put("contract_id","Long");              
            }      
        
        m_.put("contract_pkey","String");  

            m_.put("contract_pname","String");    
 
                      }             
//[2] userAutor  

        if(level>1){
            if(withIds){
               m_.put("userAutor_id","Long");              
            }      
        
        m_.put("userAutor_pkey","String");  

            m_.put("userAutor_pname","String");    
 
                      }             
//[2] comercialDocumentType  

        if(level>1){
            if(withIds){
               m_.put("comercialDocumentType_id","Long");              
            }      
        
        m_.put("comercialDocumentType_pkey","String");  

            m_.put("comercialDocumentType_pname","String");    
 
                      }             
//[2] productType  

        if(level>1){
            if(withIds){
               m_.put("productType_id","Long");              
            }      
        
        m_.put("productType_pkey","String");  

            m_.put("productType_pname","String");    
 
                      }             
//[2] stockRack  

        if(level>1){
            if(withIds){
               m_.put("stockRack_id","Long");              
            }      
        
        m_.put("stockRack_pkey","String");  

            m_.put("stockRack_pname","String");    
 
                      }             
//[3] departamentBaseTimePeriod  

        if(level>2){
            if(withIds){
               m_.put("departamentBaseTimePeriod_id","Long");              
            }      
        
        m_.put("departamentBaseTimePeriod_pkey","String");  
 
                      }             
//[3] thirdPerson  

        if(level>2){
            if(withIds){
               m_.put("thirdPerson_id","Long");              
            }      
        
        m_.put("thirdPerson_pkey","String");  

            m_.put("thirdPerson_pname","String");    
 
                      }             
//[3] workSpace  

        if(level>2){
            if(withIds){
               m_.put("workSpace_id","Long");              
            }      
        
        m_.put("workSpace_pkey","String");  

            m_.put("workSpace_pname","String");    
 
                      }             
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"invoice_line_in_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"invoice_line_in_pkey", nc++); 
    //askQuantity     
            bdToCell(r, row,"invoice_line_in_ask_quantity", nc++); 
    //createdDate            
            ldtToCell(r, row,"invoice_line_in_created_date", nc++); 
    //descount     
            bdToCell(r, row,"invoice_line_in_descount", nc++); 
    //invoiceDate            
            ldtToCell(r, row,"invoice_line_in_invoice_date", nc++); 
    //orden            
            ldToCell(r, row,"invoice_line_in_orden", nc++); 
    //status       
            sToCell(r, row,"invoice_line_in_status", nc++); 
    //supplyDate            
            ldtToCell(r, row,"invoice_line_in_supply_date", nc++); 
    //supplyQuantity     
            bdToCell(r, row,"invoice_line_in_supply_quantity", nc++); 
    //taxPorcent     
            bdToCell(r, row,"invoice_line_in_tax_porcent", nc++); 
    //total     
            bdToCell(r, row,"invoice_line_in_total", nc++); 
    //totalCost     
            bdToCell(r, row,"invoice_line_in_total_cost", nc++); 
    //unitCost     
            bdToCell(r, row,"invoice_line_in_unit_cost", nc++); 
 // comercialDocument
if(withIds){
                    lToCell(r, row,"comercial_document_id", nc++);
                 }
sToCell(r, row,"comercial_document_pkey", nc++);
sToCell(r, row,"comercial_document_pname", nc++);
 // product
if(withIds){
                    lToCell(r, row,"product_id", nc++);
                 }
sToCell(r, row,"product_pkey", nc++);
sToCell(r, row,"product_pname", nc++);
 // stockRackProduct
if(withIds){
                    lToCell(r, row,"stock_rack_product_id", nc++);
                 }
sToCell(r, row,"stock_rack_product_pkey", nc++);
sToCell(r, row,"stock_rack_product_pname", nc++);
// contract
if(withIds){
            lToCell(r, row,"contract_id", nc++);
        }
sToCell(r, row,"contract_pkey", nc++);
sToCell(r, row,"contract_pname", nc++);
// userAutor
if(withIds){
            lToCell(r, row,"user_autor_id", nc++);
        }
sToCell(r, row,"user_autor_pkey", nc++);
sToCell(r, row,"user_autor_pname", nc++);
// comercialDocumentType
if(withIds){
            lToCell(r, row,"comercial_document_type_id", nc++);
        }
sToCell(r, row,"comercial_document_type_pkey", nc++);
sToCell(r, row,"comercial_document_type_pname", nc++);
// productType
if(withIds){
            lToCell(r, row,"product_type_id", nc++);
        }
sToCell(r, row,"product_type_pkey", nc++);
sToCell(r, row,"product_type_pname", nc++);
// stockRack
if(withIds){
            lToCell(r, row,"stock_rack_id", nc++);
        }
sToCell(r, row,"stock_rack_pkey", nc++);
sToCell(r, row,"stock_rack_pname", nc++);
// departamentBaseTimePeriod
if(withIds){
            lToCell(r, row,"departament_base_time_period_id", nc++);
        }
sToCell(r, row,"departament_base_time_period_pkey", nc++);
// thirdPerson
if(withIds){
            lToCell(r, row,"third_person_id", nc++);
        }
sToCell(r, row,"third_person_pkey", nc++);
sToCell(r, row,"third_person_pname", nc++);
// workSpace
if(withIds){
            lToCell(r, row,"work_space_id", nc++);
        }
sToCell(r, row,"work_space_pkey", nc++);
sToCell(r, row,"work_space_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE invoice_line_in_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final InvoiceLineIn dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addBigDecimal(dc0.getAskQuantity());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addBigDecimal(dc0.getDescount());
        t.addLocalDateTime(dc0.getInvoiceDate());
        t.addInteger(dc0.getOrden());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
        t.addBigDecimal(dc0.getSupplyQuantity());
        t.addBigDecimal(dc0.getTaxPorcent());
        t.addBigDecimal(dc0.getTotal());
        t.addBigDecimal(dc0.getTotalCost());
        t.addBigDecimal(dc0.getUnitCost());
   
            if(dc0.getComercialDocument()!=null){
               t.addLong(dc0.getComercialDocument().getId());
            }
   
            if(dc0.getProduct()!=null){
               t.addLong(dc0.getProduct().getId());
            }
   
            if(dc0.getStockRackProduct()!=null){
               t.addLong(dc0.getStockRackProduct().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final InvoiceLineIn dc0, final Tuple t){
                t.addBigDecimal(dc0.getAskQuantity());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addBigDecimal(dc0.getDescount());
        t.addLocalDateTime(dc0.getInvoiceDate());
        t.addInteger(dc0.getOrden());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
        t.addBigDecimal(dc0.getSupplyQuantity());
        t.addBigDecimal(dc0.getTaxPorcent());
        t.addBigDecimal(dc0.getTotal());
        t.addBigDecimal(dc0.getTotalCost());
        t.addBigDecimal(dc0.getUnitCost());
   
//      if(dc0.getComercialDocument()!=null){
//            t.addLong(dc0.getComercialDocument().getId());
//      }
   
//      if(dc0.getProduct()!=null){
//            t.addLong(dc0.getProduct().getId());
//      }
   
//      if(dc0.getStockRackProduct()!=null){
//            t.addLong(dc0.getStockRackProduct().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTBigDecimal("askQuantity", obj, t);

    fTLocalDateTime("createdDate", obj, t);

    fTBigDecimal("descount", obj, t);

    fTLocalDateTime("invoiceDate", obj, t);

    fTInteger("orden", obj, t);

    fTString("status", obj, t);

    fTLocalDateTime("supplyDate", obj, t);

    fTBigDecimal("supplyQuantity", obj, t);

    fTBigDecimal("taxPorcent", obj, t);

    fTBigDecimal("total", obj, t);

    fTBigDecimal("totalCost", obj, t);

    fTBigDecimal("unitCost", obj, t);

    fTLong("comercialDocument_id",obj,t);

    fTLong("product_id",obj,t);

    fTLong("stockRackProduct_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> comercialDocument = mapParents.get("comercialDocument");
            final Long comercialDocument_id = comercialDocument.get((String)obj.get("comercialDocument_pkey"));
            obj.put("comercialDocument_id", comercialDocument_id);
      
            final Map<String, Long> product = mapParents.get("product");
            final Long product_id = product.get((String)obj.get("product_pkey"));
            obj.put("product_id", product_id);
      
            final Map<String, Long> stockRackProduct = mapParents.get("stockRackProduct");
            final Long stockRackProduct_id = stockRackProduct.get((String)obj.get("stockRackProduct_pkey"));
            obj.put("stockRackProduct_id", stockRackProduct_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTBigDecimal("askQuantity", js, t);

    fTLocalDateTime("createdDate", js, t);

    fTBigDecimal("descount", js, t);

    fTLocalDateTime("invoiceDate", js, t);

    fTInteger("orden", js, t);

    fTString("status", js, t);

    fTLocalDateTime("supplyDate", js, t);

    fTBigDecimal("supplyQuantity", js, t);

    fTBigDecimal("taxPorcent", js, t);

    fTBigDecimal("total", js, t);

    fTBigDecimal("totalCost", js, t);

    fTBigDecimal("unitCost", js, t);
     
    fTLong("comercialDocument_id",js,t);
     
    fTLong("product_id",js,t);
     
    fTLong("stockRackProduct_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTBigDecimal("askQuantity", js, t);
fTLocalDateTime("createdDate", js, t);
fTBigDecimal("descount", js, t);
fTLocalDateTime("invoiceDate", js, t);
fTInteger("orden", js, t);
fTString("status", js, t);
fTLocalDateTime("supplyDate", js, t);
fTBigDecimal("supplyQuantity", js, t);
fTBigDecimal("taxPorcent", js, t);
fTBigDecimal("total", js, t);
fTBigDecimal("totalCost", js, t);
fTBigDecimal("unitCost", js, t);

            //     fTLong("comercialDocument_id",js,t);

            //     fTLong("product_id",js,t);

            //     fTLong("stockRackProduct_id",js,t);
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
    public InvoiceLineIn doFrom(final Row r){
        final InvoiceLineIn invoiceLineIn = new InvoiceLineIn();
         invoiceLineIn.setId(r.getLong("invoice_line_in_id"));
         
                invoiceLineIn.setPkey(  r.getString("invoice_line_in_pkey"));
         
                invoiceLineIn.setAskQuantity(  r.getBigDecimal("invoice_line_in_ask_quantity"));
         
                invoiceLineIn.setCreatedDate(  r.getLocalDateTime("invoice_line_in_created_date"));
         
                invoiceLineIn.setDescount(  r.getBigDecimal("invoice_line_in_descount"));
         
                invoiceLineIn.setInvoiceDate(  r.getLocalDateTime("invoice_line_in_invoice_date"));
         
                invoiceLineIn.setOrden(  r.getInteger("invoice_line_in_orden"));
         
                invoiceLineIn.setStatus(  r.getString("invoice_line_in_status"));
         
                invoiceLineIn.setSupplyDate(  r.getLocalDateTime("invoice_line_in_supply_date"));
         
                invoiceLineIn.setSupplyQuantity(  r.getBigDecimal("invoice_line_in_supply_quantity"));
         
                invoiceLineIn.setTaxPorcent(  r.getBigDecimal("invoice_line_in_tax_porcent"));
         
                invoiceLineIn.setTotal(  r.getBigDecimal("invoice_line_in_total"));
         
                invoiceLineIn.setTotalCost(  r.getBigDecimal("invoice_line_in_total_cost"));
         
                invoiceLineIn.setUnitCost(  r.getBigDecimal("invoice_line_in_unit_cost"));

        final ComercialDocumentOut comercialDocument = new ComercialDocumentOut();
        comercialDocument.setId(r.getLong("comercial_document_id"));
        comercialDocument.setPkey(r.getString("comercial_document_pkey"));
        comercialDocument.setPname(r.getString("comercial_document_pname"));
        invoiceLineIn.setComercialDocument(comercialDocument);
        

        final Product product = new Product();
        product.setId(r.getLong("product_id"));
        product.setPkey(r.getString("product_pkey"));
        product.setPname(r.getString("product_pname"));
        invoiceLineIn.setProduct(product);
        

        final StockRackProduct stockRackProduct = new StockRackProduct();
        stockRackProduct.setId(r.getLong("stock_rack_product_id"));
        stockRackProduct.setPkey(r.getString("stock_rack_product_pkey"));
        stockRackProduct.setPname(r.getString("stock_rack_product_pname"));
        invoiceLineIn.setStockRackProduct(stockRackProduct);
        

        final ContractOut contract = new ContractOut();
        contract.setId(r.getLong("contract_id"));
        contract.setPkey(r.getString("contract_pkey"));
        contract.setPname(r.getString("contract_pname"));
 comercialDocument.setContract(contract); 

        final UserLon userAutor = new UserLon();
        userAutor.setId(r.getLong("user_autor_id"));
        userAutor.setPkey(r.getString("user_autor_pkey"));
        userAutor.setPname(r.getString("user_autor_pname"));
 comercialDocument.setUserAutor(userAutor); 

        final ComercialDocumentTypeOut comercialDocumentType = new ComercialDocumentTypeOut();
        comercialDocumentType.setId(r.getLong("comercial_document_type_id"));
        comercialDocumentType.setPkey(r.getString("comercial_document_type_pkey"));
        comercialDocumentType.setPname(r.getString("comercial_document_type_pname"));
 comercialDocument.setComercialDocumentType(comercialDocumentType); 

        final ProductType productType = new ProductType();
        productType.setId(r.getLong("product_type_id"));
        productType.setPkey(r.getString("product_type_pkey"));
        productType.setPname(r.getString("product_type_pname"));
 product.setProductType(productType); 

        final StockRack stockRack = new StockRack();
        stockRack.setId(r.getLong("stock_rack_id"));
        stockRack.setPkey(r.getString("stock_rack_pkey"));
        stockRack.setPname(r.getString("stock_rack_pname"));
 stockRackProduct.setStockRack(stockRack);   
        return invoiceLineIn;
    }
    
    @Override
    public InvoiceLineIn doFromJson(final JsonObject js){
        InvoiceLineIn invoiceLineIn = new InvoiceLineIn();
        invoiceLineIn.setId(js.getLong("id"));
        
                invoiceLineIn.setPkey(js.getString("pkey"));
        invoiceLineIn.setAskQuantity(new BigDecimal(js.getString("askQuantity")));
        invoiceLineIn.setCreatedDate(LocalDateTime.parse(js.getString("createdDate")));
        invoiceLineIn.setDescount(new BigDecimal(js.getString("descount")));
        invoiceLineIn.setInvoiceDate(LocalDateTime.parse(js.getString("invoiceDate")));
        invoiceLineIn.setOrden(js.getInteger("orden"));
        invoiceLineIn.setStatus(js.getString("status"));
        invoiceLineIn.setSupplyDate(LocalDateTime.parse(js.getString("supplyDate")));
        invoiceLineIn.setSupplyQuantity(new BigDecimal(js.getString("supplyQuantity")));
        invoiceLineIn.setTaxPorcent(new BigDecimal(js.getString("taxPorcent")));
        invoiceLineIn.setTotal(new BigDecimal(js.getString("total")));
        invoiceLineIn.setTotalCost(new BigDecimal(js.getString("totalCost")));
        invoiceLineIn.setUnitCost(new BigDecimal(js.getString("unitCost")));
        invoiceLineIn.setId(js.getLong("comercialDocument_id"));
        invoiceLineIn.setId(js.getLong("product_id"));
        invoiceLineIn.setId(js.getLong("stockRackProduct_id"));
        return invoiceLineIn;
    }

    @Override
    public JsonObject toJson(final InvoiceLineIn o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("askQuantity",  o.getAskQuantity() );
        jso.put("createdDate",  o.getCreatedDate() );
        jso.put("descount",  o.getDescount() );
        jso.put("invoiceDate",  o.getInvoiceDate() );
        jso.put("orden",  o.getOrden() );
        jso.put("status",  o.getStatus() );
        jso.put("supplyDate",  o.getSupplyDate() );
        jso.put("supplyQuantity",  o.getSupplyQuantity() );
        jso.put("taxPorcent",  o.getTaxPorcent() );
        jso.put("total",  o.getTotal() );
        jso.put("totalCost",  o.getTotalCost() );
        jso.put("unitCost",  o.getUnitCost() );

            final ComercialDocumentOut comercialDocument = o.getComercialDocument();
            if(comercialDocument!=null){
                jso.put("comercialDocument_id", comercialDocument.getId());
                jso.put("comercialDocument_pkey", comercialDocument.getPkey());
            }
            

            final Product product = o.getProduct();
            if(product!=null){
                jso.put("product_id", product.getId());
                jso.put("product_pkey", product.getPkey());
            }
            

            final StockRackProduct stockRackProduct = o.getStockRackProduct();
            if(stockRackProduct!=null){
                jso.put("stockRackProduct_id", stockRackProduct.getId());
                jso.put("stockRackProduct_pkey", stockRackProduct.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "invoice_line_in_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "invoice_line_in_pkey");
    slcb.doEqInPSimple( "pkey", "invoice_line_in_pkey");   
    slcb.doGEPSimpleInt( "orden", "invoice_line_in_orden");
    slcb.doLTPSimpleInt( "orden", "invoice_line_in_orden");                
    slcb.doEqInPSimple( "status", "invoice_line_in_status");                    
        
        slcb.doIlPSimple2( "comercialDocument_pkey", "comercial_document_pkey");
        slcb.doEQPSimple2( "comercialDocument_pkey", "comercial_document_pkey");
        slcb.doInLongCondition("comercialDocument_id", "comercial_document_id");  
//ComercialDocumentOut 4        
        slcb.doIlPSimple2( "comercialDocument_pname", "comercial_document_pname");    
        slcb.doIlPSimple2( "product_pkey", "product_pkey");
        slcb.doEQPSimple2( "product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");  
//Product 3        
        slcb.doIlPSimple2( "product_pname", "product_pname");    
        slcb.doIlPSimple2( "stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doEQPSimple2( "stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doInLongCondition("stockRackProduct_id", "stock_rack_product_id");  
//StockRackProduct 1        
        slcb.doIlPSimple2( "stockRackProduct_pname", "stock_rack_product_pname");    

        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");//ContractOut 1
        slcb.doIlPSimple2( "contract_pname", "contract_pname"); 
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id"); 
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id"); 

        slcb.doIlPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");//UserLon 4
        slcb.doIlPSimple2( "userAutor_pname", "user_autor_pname"); 

        slcb.doIlPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doEQPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_id");//ComercialDocumentTypeOut 2
        slcb.doIlPSimple2( "comercialDocumentType_pname", "comercial_document_type_pname"); 

        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");//ProductType 5
        slcb.doIlPSimple2( "productType_pname", "product_type_pname"); 

        slcb.doIlPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doEQPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doInLongCondition("stockRack_id", "stock_rack_id");//StockRack 2
        slcb.doIlPSimple2( "stockRack_pname", "stock_rack_pname"); 
        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"invoice_line_in");
        sz0.addField("COUNT(invoice_line_in.id) as c_invoice_line_in_id").addName("count");
        
    sz0.addField("sum(invoice_line_in.ask_quantity) as sum_invoice_line_in_ask_quantity").addName("sum_askQuantity");
        
    sz0.addField("sum(invoice_line_in.descount) as sum_invoice_line_in_descount").addName("sum_descount");
        
    sz0.addField("sum(invoice_line_in.orden) as sum_invoice_line_in_orden").addName("sum_orden");
        
    sz0.addField("sum(invoice_line_in.supply_quantity) as sum_invoice_line_in_supply_quantity").addName("sum_supplyQuantity");
        
    sz0.addField("sum(invoice_line_in.tax_porcent) as sum_invoice_line_in_tax_porcent").addName("sum_taxPorcent");
        
    sz0.addField("sum(invoice_line_in.total) as sum_invoice_line_in_total").addName("sum_total");
        
    sz0.addField("sum(invoice_line_in.total_cost) as sum_invoice_line_in_total_cost").addName("sum_totalCost");
        
    sz0.addField("sum(invoice_line_in.unit_cost) as sum_invoice_line_in_unit_cost").addName("sum_unitCost");
        
        
//level 1
             
    sz0.applyG1(zComercialDocument);               
    sz0.applyG1(zProduct);               
    sz0.applyG1(zStockRackProduct);      
    //level 2
    
    sz0.applyG2(zComercialDocument,zContract);                           
    sz0.applyG2(zComercialDocument,zUserAutor);                           
    sz0.applyG2(zComercialDocument,zComercialDocumentType);                           
    sz0.applyG2(zProduct,zProductType);                           
    sz0.applyG2(zStockRackProduct,zStockRack);                           
    //level 3
    
        sz0.applyG3(zComercialDocument,zContract,zDepartamentBaseTimePeriod);               
        sz0.applyG3(zComercialDocument,zContract,zThirdPerson);               
        sz0.applyG3(zStockRackProduct,zStockRack,zWorkSpace);               
        return sz0;
    }
}
    
        