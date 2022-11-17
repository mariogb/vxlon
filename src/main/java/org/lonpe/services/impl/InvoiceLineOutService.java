
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

import java.time.LocalDateTime;
import java.math.BigDecimal;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon2;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon3;

/**
 *   InvoiceLineOutService 
 * 
 */
  
public class InvoiceLineOutService extends AbstractServiceLon<InvoiceLineOut>{

    private static final String SQLINSERT ="INSERT INTO invoice_line_out(pkey,ask_quantity,created_date,descount,invoice_date,orden,status,supply_date,supply_quantity,tax_porcent,total,total_cost,unit_cost,comercial_document_id,product_id,stock_rack_product_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE invoice_line_out SET ask_quantity = $1,created_date = $2,descount = $3,invoice_date = $4,orden = $5,status = $6,supply_date = $7,supply_quantity = $8,tax_porcent = $9,total = $10,total_cost = $11,unit_cost = $12 WHERE id = $19 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE invoice_line_out SET ask_quantity = $1,created_date = $2,descount = $3,invoice_date = $4,orden = $5,status = $6,supply_date = $7,supply_quantity = $8,tax_porcent = $9,total = $10,total_cost = $11,unit_cost = $12 WHERE pkey = $19 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM invoice_line_out_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM invoice_line_out_view";
    private static final String SQLKEYS = "SELECT invoice_line_out_pkey FROM invoice_line_out_view";
    private static final String SQLIDBYPKEY = "SELECT id from invoice_line_out WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from invoice_line_out WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM invoice_line_out WHERE id = $1 returning id";
    private static final String TABLENAME ="invoice_line_out";
    

    public InvoiceLineOutService() {
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
    private static String sql00 = "SELECT invoice_line_out.id as invoice_line_out_id,
invoice_line_out.pkey as invoice_line_out_pkey,
invoice_line_out.ask_quantity as invoice_line_out_ask_quantity,
invoice_line_out.created_date as invoice_line_out_created_date,
invoice_line_out.descount as invoice_line_out_descount,
invoice_line_out.invoice_date as invoice_line_out_invoice_date,
invoice_line_out.orden as invoice_line_out_orden,
invoice_line_out.status as invoice_line_out_status,
invoice_line_out.supply_date as invoice_line_out_supply_date,
invoice_line_out.supply_quantity as invoice_line_out_supply_quantity,
invoice_line_out.tax_porcent as invoice_line_out_tax_porcent,
invoice_line_out.total as invoice_line_out_total,
invoice_line_out.total_cost as invoice_line_out_total_cost,
invoice_line_out.unit_cost as invoice_line_out_unit_cost,
comercial_document.id as comercial_document_id,comercial_document.pkey as comercial_document_pkey,comercial_document.pname as comercial_document_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,
stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,
stock_rack.id as stock_rack_id, stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname 
  FROM 
  invoice_line_out,
  comercial_document_in as comercial_document,
  contract_in as contract,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  user_lon as user_autor,
  comercial_document_type_in as comercial_document_type,
  product as product,
  product_type as product_type,
  stock_rack_product as stock_rack_product,
  stock_rack as stock_rack,
  work_space as work_space  
 WHERE 
 invoice_line_out.comercial_document_id = comercial_document.id
 AND comercial_document.contract_id = contract.id
 AND contract.program_base_time_period_id = program_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND comercial_document.user_autor_id = user_autor.id
 AND comercial_document.comercial_document_type_id = comercial_document_type.id
 AND invoice_line_out.product_id = product.id
 AND product.product_type_id = product_type.id
 AND invoice_line_out.stock_rack_product_id = stock_rack_product.id
 AND stock_rack_product.stock_rack_id = stock_rack.id
 AND stock_rack.work_space_id = work_space.id"
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
        
    dcModel.put("dc", "invoiceLineOut");

//ID 
    names.add("id");

    sortMapFields.put("id","invoice_line_out_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","invoice_line_out");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("invoice_line_out.invoice_line_out_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//askQuantity
    doFieldSort("askQuantity","ask_quantity","invoice_line_out");               

//Create property askQuantity       
    final JsonObject askQuantity = psBigDecimal("askQuantity",true);
 
    ps.add(askQuantity);
 
//createdDate
    doFieldSort("createdDate","created_date","invoice_line_out");               

//Create property createdDate       
    final JsonObject createdDate = psLocalDateTime("createdDate",true);
 
//Set by system
    createdDate.put("setBySys","now");  
 
    ps.add(createdDate);
 
//descount
    doFieldSort("descount","descount","invoice_line_out");               

//Create property descount       
    final JsonObject descount = psBigDecimal("descount",true);
 
    ps.add(descount);
 
//invoiceDate
    doFieldSort("invoiceDate","invoice_date","invoice_line_out");               

//Create property invoiceDate       
    final JsonObject invoiceDate = psLocalDateTime("invoiceDate",false);
 
    ps.add(invoiceDate);
 
//orden
    doFieldSort("orden","orden","invoice_line_out");               

//Create property orden       
    final JsonObject orden = psInteger("orden",true);
 
    ps.add(orden);
 
//status
    doField("status","status","invoice_line_out");               

//Create property status       
    final JsonObject status = psString("status",true);

    final JsonArray statusInList = new JsonArray();
                statusInList.add("PENDENT"); 
statusInList.add("SUPPLIED"); 
statusInList.add("CANCEL"); 
    status.put("inList",statusInList );                
 
    ps.add(status);
 
//supplyDate
    doFieldSort("supplyDate","supply_date","invoice_line_out");               

//Create property supplyDate       
    final JsonObject supplyDate = psLocalDateTime("supplyDate",false);
 
    ps.add(supplyDate);
 
//supplyQuantity
    doFieldSort("supplyQuantity","supply_quantity","invoice_line_out");               

//Create property supplyQuantity       
    final JsonObject supplyQuantity = psBigDecimal("supplyQuantity",true);
 
    ps.add(supplyQuantity);
 
//taxPorcent
    doFieldSort("taxPorcent","tax_porcent","invoice_line_out");               

//Create property taxPorcent       
    final JsonObject taxPorcent = psBigDecimal("taxPorcent",true);
 
    ps.add(taxPorcent);
 
//total
    doFieldSort("total","total","invoice_line_out");               

//Create property total       
    final JsonObject total = psBigDecimal("total",true);
 
    ps.add(total);
 
//totalCost
    doFieldSort("totalCost","total_cost","invoice_line_out");               

//Create property totalCost       
    final JsonObject totalCost = psBigDecimal("totalCost",true);
 
    ps.add(totalCost);
 
//unitCost
    doFieldSort("unitCost","unit_cost","invoice_line_out");               

//Create property unitCost       
    final JsonObject unitCost = psBigDecimal("unitCost",true);
 
    ps.add(unitCost);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  comercialDocument
    doFieldMT0("invoice_line_out","comercialDocument", "comercial_document");  

    final JsonObject comercialDocument =  doMto("comercialDocument","comercialDocumentIn");        
   
    names.add("comercialDocument_pname");
    sortMapFields.put( "comercialDocument_pname", "comercial_document_pname");                
    comercialDocument.put("pc","pname");          

    mto.add(comercialDocument);
        

    //1  comercial_document  -- comercial_document_id
    final ZtatUnitInfoLon zComercialDocument = new ZtatUnitInfoLon("comercial_document_id", "comercialDocument",  "comercial_document_in","pname","comercial_document");
    mz1.put("zComercialDocument", zComercialDocument);    

//(1)  product
    doFieldMT0("invoice_line_out","product", "product");  

    final JsonObject product =  doMto("product","product");        
   
    names.add("product_pname");
    sortMapFields.put( "product_pname", "product_pname");                
    product.put("pc","pname");          

    mto.add(product);
        

    //1  product  -- product_id
    final ZtatUnitInfoLon zProduct = new ZtatUnitInfoLon("product_id", "product",  "product","pname","product");
    mz1.put("zProduct", zProduct);    

//(1)  stockRackProduct
    doFieldMT0("invoice_line_out","stockRackProduct", "stock_rack_product");  

    final JsonObject stockRackProduct =  doMto("stockRackProduct","stockRackProduct");        
   
    names.add("stockRackProduct_pname");
    sortMapFields.put( "stockRackProduct_pname", "stock_rack_product_pname");                
    stockRackProduct.put("pc","pname");          

    mto.add(stockRackProduct);
        

    //1  stock_rack_product  -- stock_rack_product_id
    final ZtatUnitInfoLon zStockRackProduct = new ZtatUnitInfoLon("stock_rack_product_id", "stockRackProduct",  "stock_rack_product","pname","stock_rack_product");
    mz1.put("zStockRackProduct", zStockRackProduct);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  contract   contract  
    names.add("contract_id");          
    names.add("contract_pkey");

    final JsonObject contractFromComercialDocument =   doMto2("contract","contractIn","comercialDocument");        
   
    names.add("contract_pname");           
    sortMapFields.put( "contract_pname", "contract_pname");  
    contractFromComercialDocument.put("pc","pname");    
         
    mto2.add(contractFromComercialDocument);        

    final ZtatUnitInfoLon2 zContractFromComercialDocument = new ZtatUnitInfoLon2(zComercialDocument, "contract_id", "contract",  "contract_in","pname","contract");
    mz2.put("zContractFromComercialDocument",zContractFromComercialDocument);

//(2)  userAutor   userAutor  
    names.add("userAutor_id");          
    names.add("userAutor_pkey");

    final JsonObject userAutorFromComercialDocument =   doMto2("userAutor","userLon","comercialDocument");        
   
    names.add("userAutor_pname");           
    sortMapFields.put( "userAutor_pname", "user_autor_pname");  
    userAutorFromComercialDocument.put("pc","pname");    
         
    mto2.add(userAutorFromComercialDocument);        

    final ZtatUnitInfoLon2 zUserAutorFromComercialDocument = new ZtatUnitInfoLon2(zComercialDocument, "user_autor_id", "userAutor",  "user_lon","pname","user_autor");
    mz2.put("zUserAutorFromComercialDocument",zUserAutorFromComercialDocument);

//(2)  comercialDocumentType   comercialDocumentType  
    names.add("comercialDocumentType_id");          
    names.add("comercialDocumentType_pkey");

    final JsonObject comercialDocumentTypeFromComercialDocument =   doMto2("comercialDocumentType","comercialDocumentTypeIn","comercialDocument");        
   
    names.add("comercialDocumentType_pname");           
    sortMapFields.put( "comercialDocumentType_pname", "comercial_document_type_pname");  
    comercialDocumentTypeFromComercialDocument.put("pc","pname");    
         
    mto2.add(comercialDocumentTypeFromComercialDocument);        

    final ZtatUnitInfoLon2 zComercialDocumentTypeFromComercialDocument = new ZtatUnitInfoLon2(zComercialDocument, "comercial_document_type_id", "comercialDocumentType",  "comercial_document_type_in","pname","comercial_document_type");
    mz2.put("zComercialDocumentTypeFromComercialDocument",zComercialDocumentTypeFromComercialDocument);

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

//(2)  stockRack   stockRack  
    names.add("stockRack_id");          
    names.add("stockRack_pkey");

    final JsonObject stockRackFromStockRackProduct =   doMto2("stockRack","stockRack","stockRackProduct");        
   
    names.add("stockRack_pname");           
    sortMapFields.put( "stockRack_pname", "stock_rack_pname");  
    stockRackFromStockRackProduct.put("pc","pname");    
         
    mto2.add(stockRackFromStockRackProduct);        

    final ZtatUnitInfoLon2 zStockRackFromStockRackProduct = new ZtatUnitInfoLon2(zStockRackProduct, "stock_rack_id", "stockRack",  "stock_rack","pname","stock_rack");
    mz2.put("zStockRackFromStockRackProduct",zStockRackFromStockRackProduct);

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           

//(3)   programBaseTimePeriod   
    names.add("programBaseTimePeriod_id");          
    names.add("programBaseTimePeriod_pkey");

    final JsonObject programBaseTimePeriodFromContractFromComercialDocument =   doMto2("programBaseTimePeriod","programBaseTimePeriod","contract");        
         
    mto3.add(programBaseTimePeriodFromContractFromComercialDocument);        

     
    final ZtatUnitInfoLon3 zProgramBaseTimePeriodFromContractFromComercialDocument = new ZtatUnitInfoLon3(zContractFromComercialDocument, "program_base_time_period_id", "programBaseTimePeriod",  "program_base_time_period","null","program_base_time_period");
    mz3.put("zProgramBaseTimePeriodFromContractFromComercialDocument",zProgramBaseTimePeriodFromContractFromComercialDocument);    

//(3)   thirdPerson   
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPersonFromContractFromComercialDocument =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");            
    sortMapFields.put( "thirdPerson_pname", "third_person_pname"); 
    thirdPersonFromContractFromComercialDocument.put("pc","pname");     
         
    mto3.add(thirdPersonFromContractFromComercialDocument);        

     
    final ZtatUnitInfoLon3 zThirdPersonFromContractFromComercialDocument = new ZtatUnitInfoLon3(zContractFromComercialDocument, "third_person_id", "thirdPerson",  "third_person","pname","third_person");
    mz3.put("zThirdPersonFromContractFromComercialDocument",zThirdPersonFromContractFromComercialDocument);    

//(3)   workSpace   
    names.add("workSpace_id");          
    names.add("workSpace_pkey");

    final JsonObject workSpaceFromStockRackFromStockRackProduct =   doMto2("workSpace","workSpace","stockRack");        
   
    names.add("workSpace_pname");            
    sortMapFields.put( "workSpace_pname", "work_space_pname"); 
    workSpaceFromStockRackFromStockRackProduct.put("pc","pname");     
         
    mto3.add(workSpaceFromStockRackFromStockRackProduct);        

     
    final ZtatUnitInfoLon3 zWorkSpaceFromStockRackFromStockRackProduct = new ZtatUnitInfoLon3(zStockRackFromStockRackProduct, "work_space_id", "workSpace",  "work_space","pname","work_space");
    mz3.put("zWorkSpaceFromStockRackFromStockRackProduct",zWorkSpaceFromStockRackFromStockRackProduct);    

    dcModel.put("mto3",mto3);       
        
        
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
        jsa.add(r.getLong("invoice_line_out_id") );       
        jsa.add(r.getString("invoice_line_out_pkey") );       
        jsa.add(r.getBigDecimal("invoice_line_out_ask_quantity") );
        jsa.add(r.getLocalDate("invoice_line_out_created_date").toString() ); // undefined       
        jsa.add(r.getBigDecimal("invoice_line_out_descount") ); 
        asMaybeNullLocalDate(r,"invoice_line_out_invoice_date",jsa); //true       
        jsa.add(r.getInteger("invoice_line_out_orden") );       
        jsa.add(r.getString("invoice_line_out_status") ); 
        asMaybeNullLocalDate(r,"invoice_line_out_supply_date",jsa); //true       
        jsa.add(r.getBigDecimal("invoice_line_out_supply_quantity") );       
        jsa.add(r.getBigDecimal("invoice_line_out_tax_porcent") );       
        jsa.add(r.getBigDecimal("invoice_line_out_total") );       
        jsa.add(r.getBigDecimal("invoice_line_out_total_cost") );       
        jsa.add(r.getBigDecimal("invoice_line_out_unit_cost") );
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
    
    jsa.add(r.getLong("program_base_time_period_id"));
    jsa.add(r.getString("program_base_time_period_pkey"));
    
    jsa.add(r.getLong("third_person_id"));
    jsa.add(r.getString("third_person_pkey"));
    

    jsa.add(r.getString("third_person_pname"));
    
    jsa.add(r.getLong("work_space_id"));
    jsa.add(r.getString("work_space_pkey"));
    

    jsa.add(r.getString("work_space_pname"));
    
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
        m.put("invoiceLineOut_id",LONG);
    }        
//pkey    
    m.put("invoiceLineOut_pkey",STRING);              
//askQuantity    
    m.put("invoiceLineOut_askQuantity",BIGDECIMAL);              
//createdDate    
    m.put("invoiceLineOut_createdDate",LOCALDATETIME);              
//descount    
    m.put("invoiceLineOut_descount",BIGDECIMAL);              
//invoiceDate    
    m.put("invoiceLineOut_invoiceDate",LOCALDATETIME);              
//orden    
    m.put("invoiceLineOut_orden",INTEGER);              
//status    
    m.put("invoiceLineOut_status",STRING);              
//supplyDate    
    m.put("invoiceLineOut_supplyDate",LOCALDATETIME);              
//supplyQuantity    
    m.put("invoiceLineOut_supplyQuantity",BIGDECIMAL);              
//taxPorcent    
    m.put("invoiceLineOut_taxPorcent",BIGDECIMAL);              
//total    
    m.put("invoiceLineOut_total",BIGDECIMAL);              
//totalCost    
    m.put("invoiceLineOut_totalCost",BIGDECIMAL);              
//unitCost    
    m.put("invoiceLineOut_unitCost",BIGDECIMAL);          
    if(level<1){
        return m;    
    }       
// comercialDocument   comercialDocument
    if(withIds){
        m.put("comercialDocument_id",LONG);                       
    }
    m.put("comercialDocument_pkey",STRING);     
    m.put("comercialDocument_pname",STRING);   
// product   product
    if(withIds){
        m.put("product_id",LONG);                       
    }
    m.put("product_pkey",STRING);     
    m.put("product_pname",STRING);   
// stockRackProduct   stockRackProduct
    if(withIds){
        m.put("stockRackProduct_id",LONG);                       
    }
    m.put("stockRackProduct_pkey",STRING);     
    m.put("stockRackProduct_pname",STRING);  
//[2] contract --   contract
    if(withIds){
        m.put("contract_id",LONG);              
    }              
    m.put("contract_pkey",STRING);  
        
    m.put("contract_pname",STRING);  
//[2] userAutor --   userAutor
    if(withIds){
        m.put("userAutor_id",LONG);              
    }              
    m.put("userAutor_pkey",STRING);  
        
    m.put("userAutor_pname",STRING);  
//[2] comercialDocumentType --   comercialDocumentType
    if(withIds){
        m.put("comercialDocumentType_id",LONG);              
    }              
    m.put("comercialDocumentType_pkey",STRING);  
        
    m.put("comercialDocumentType_pname",STRING);  
//[2] productType --   productType
    if(withIds){
        m.put("productType_id",LONG);              
    }              
    m.put("productType_pkey",STRING);  
        
    m.put("productType_pname",STRING);  
//[2] stockRack --   stockRack
    if(withIds){
        m.put("stockRack_id",LONG);              
    }              
    m.put("stockRack_pkey",STRING);  
        
    m.put("stockRack_pname",STRING);  
//[3] programBaseTimePeriod --   programBaseTimePeriod
    if(withIds){
        m.put("programBaseTimePeriod_id",LONG);              
    }              
    m.put("programBaseTimePeriod_pkey",STRING);  
        
//[3] thirdPerson --   thirdPerson
    if(withIds){
        m.put("thirdPerson_id",LONG);              
    }              
    m.put("thirdPerson_pkey",STRING);  
        
    m.put("thirdPerson_pname",STRING);  
//[3] workSpace --   workSpace
    if(withIds){
        m.put("workSpace_id",LONG);              
    }              
    m.put("workSpace_pkey",STRING);  
        
    m.put("workSpace_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"invoice_line_out_id", nc++); 
    }            //pkey       
            sToCell(r, row,"invoice_line_out_pkey", nc++);     //askQuantity     
            bdToCell(r, row,"invoice_line_out_ask_quantity", nc++);     //createdDate            
            ldtToCell(r, row,"invoice_line_out_created_date", nc++);     //descount     
            bdToCell(r, row,"invoice_line_out_descount", nc++);     //invoiceDate            
            ldtToCell(r, row,"invoice_line_out_invoice_date", nc++);     //orden            
            ldToCell(r, row,"invoice_line_out_orden", nc++);     //status       
            sToCell(r, row,"invoice_line_out_status", nc++);     //supplyDate            
            ldtToCell(r, row,"invoice_line_out_supply_date", nc++);     //supplyQuantity     
            bdToCell(r, row,"invoice_line_out_supply_quantity", nc++);     //taxPorcent     
            bdToCell(r, row,"invoice_line_out_tax_porcent", nc++);     //total     
            bdToCell(r, row,"invoice_line_out_total", nc++);     //totalCost     
            bdToCell(r, row,"invoice_line_out_total_cost", nc++);     //unitCost     
            bdToCell(r, row,"invoice_line_out_unit_cost", nc++); 
//comercialDocument   comercial_document        
    if(withIds){
        lToCell(r, row,"comercial_document_id", nc++);
    }
    sToCell(r, row,"comercial_document_pkey", nc++);
    sToCell(r, row,"comercial_document_pname", nc++);
//product   product        
    if(withIds){
        lToCell(r, row,"product_id", nc++);
    }
    sToCell(r, row,"product_pkey", nc++);
    sToCell(r, row,"product_pname", nc++);
//stockRackProduct   stock_rack_product        
    if(withIds){
        lToCell(r, row,"stock_rack_product_id", nc++);
    }
    sToCell(r, row,"stock_rack_product_pkey", nc++);
    sToCell(r, row,"stock_rack_product_pname", nc++);
// contract  contract
    if(withIds){
       lToCell(r, row,"contract_id", nc++);
    }

    sToCell(r, row,"contract_pkey", nc++);

    sToCell(r, row,"contract_pname", nc++);
// userAutor  user_autor
    if(withIds){
       lToCell(r, row,"user_autor_id", nc++);
    }

    sToCell(r, row,"user_autor_pkey", nc++);

    sToCell(r, row,"user_autor_pname", nc++);
// comercialDocumentType  comercial_document_type
    if(withIds){
       lToCell(r, row,"comercial_document_type_id", nc++);
    }

    sToCell(r, row,"comercial_document_type_pkey", nc++);

    sToCell(r, row,"comercial_document_type_pname", nc++);
// productType  product_type
    if(withIds){
       lToCell(r, row,"product_type_id", nc++);
    }

    sToCell(r, row,"product_type_pkey", nc++);

    sToCell(r, row,"product_type_pname", nc++);
// stockRack  stock_rack
    if(withIds){
       lToCell(r, row,"stock_rack_id", nc++);
    }

    sToCell(r, row,"stock_rack_pkey", nc++);

    sToCell(r, row,"stock_rack_pname", nc++);
// programBaseTimePeriod  program_base_time_period
    if(withIds){
       lToCell(r, row,"program_base_time_period_id", nc++);
    }

    sToCell(r, row,"program_base_time_period_pkey", nc++);
// thirdPerson  third_person
    if(withIds){
       lToCell(r, row,"third_person_id", nc++);
    }

    sToCell(r, row,"third_person_pkey", nc++);

    sToCell(r, row,"third_person_pname", nc++);
// workSpace  work_space
    if(withIds){
       lToCell(r, row,"work_space_id", nc++);
    }

    sToCell(r, row,"work_space_pkey", nc++);

    sToCell(r, row,"work_space_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE invoice_line_out_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final InvoiceLineOut dc0, final Tuple t){
                
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
    public void fillTupleUpdate(final InvoiceLineOut dc0, final Tuple t){
        
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
    public InvoiceLineOut doFrom(final Row r){
        final InvoiceLineOut invoiceLineOut = new InvoiceLineOut();
         invoiceLineOut.setId(r.getLong("invoice_line_out_id"));         
                invoiceLineOut.setPkey(  r.getString("invoice_line_out_pkey"));                       
                invoiceLineOut.setAskQuantity(  r.getBigDecimal("invoice_line_out_ask_quantity"));                       
                invoiceLineOut.setCreatedDate(  r.getLocalDateTime("invoice_line_out_created_date"));                       
                invoiceLineOut.setDescount(  r.getBigDecimal("invoice_line_out_descount"));                       
                invoiceLineOut.setInvoiceDate(  r.getLocalDateTime("invoice_line_out_invoice_date"));                       
                invoiceLineOut.setOrden(  r.getInteger("invoice_line_out_orden"));                       
                invoiceLineOut.setStatus(  r.getString("invoice_line_out_status"));                       
                invoiceLineOut.setSupplyDate(  r.getLocalDateTime("invoice_line_out_supply_date"));                       
                invoiceLineOut.setSupplyQuantity(  r.getBigDecimal("invoice_line_out_supply_quantity"));                       
                invoiceLineOut.setTaxPorcent(  r.getBigDecimal("invoice_line_out_tax_porcent"));                       
                invoiceLineOut.setTotal(  r.getBigDecimal("invoice_line_out_total"));                       
                invoiceLineOut.setTotalCost(  r.getBigDecimal("invoice_line_out_total_cost"));                       
                invoiceLineOut.setUnitCost(  r.getBigDecimal("invoice_line_out_unit_cost"));              
        final ComercialDocumentIn comercialDocument = new ComercialDocumentIn();
        comercialDocument.setId(r.getLong("comercial_document_id"));
        comercialDocument.setPkey(r.getString("comercial_document_pkey"));
        
        comercialDocument.setPname(r.getString("comercial_document_pname"));
        invoiceLineOut.setComercialDocument(comercialDocument);
        
        final Product product = new Product();
        product.setId(r.getLong("product_id"));
        product.setPkey(r.getString("product_pkey"));
        
        product.setPname(r.getString("product_pname"));
        invoiceLineOut.setProduct(product);
        
        final StockRackProduct stockRackProduct = new StockRackProduct();
        stockRackProduct.setId(r.getLong("stock_rack_product_id"));
        stockRackProduct.setPkey(r.getString("stock_rack_product_pkey"));
        
        stockRackProduct.setPname(r.getString("stock_rack_product_pname"));
        invoiceLineOut.setStockRackProduct(stockRackProduct);
        
        final ContractIn contract = new ContractIn();
        contract.setId(r.getLong("contract_id"));
        contract.setPkey(r.getString("contract_pkey"));
        contract.setPname(r.getString("contract_pname"));
 
        comercialDocument.setContract(contract); 
        final UserLon userAutor = new UserLon();
        userAutor.setId(r.getLong("user_autor_id"));
        userAutor.setPkey(r.getString("user_autor_pkey"));
        userAutor.setPname(r.getString("user_autor_pname"));
 
        comercialDocument.setUserAutor(userAutor); 
        final ComercialDocumentTypeIn comercialDocumentType = new ComercialDocumentTypeIn();
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
        return invoiceLineOut;
    }
    
    @Override
    public InvoiceLineOut doFromJson(final JsonObject js){
        InvoiceLineOut invoiceLineOut = new InvoiceLineOut();
        invoiceLineOut.setId(js.getLong("id"));
        
                
                invoiceLineOut.setPkey(js.getString("pkey"));        
                invoiceLineOut.setAskQuantity(new BigDecimal(js.getString("askQuantity")));        
                invoiceLineOut.setCreatedDate(LocalDateTime.parse(js.getString("createdDate")));        
                invoiceLineOut.setDescount(new BigDecimal(js.getString("descount")));        
                invoiceLineOut.setInvoiceDate(LocalDateTime.parse(js.getString("invoiceDate")));        
                invoiceLineOut.setOrden(js.getInteger("orden"));        
                invoiceLineOut.setStatus(js.getString("status"));        
                invoiceLineOut.setSupplyDate(LocalDateTime.parse(js.getString("supplyDate")));        
                invoiceLineOut.setSupplyQuantity(new BigDecimal(js.getString("supplyQuantity")));        
                invoiceLineOut.setTaxPorcent(new BigDecimal(js.getString("taxPorcent")));        
                invoiceLineOut.setTotal(new BigDecimal(js.getString("total")));        
                invoiceLineOut.setTotalCost(new BigDecimal(js.getString("totalCost")));        
                invoiceLineOut.setUnitCost(new BigDecimal(js.getString("unitCost")));        
            invoiceLineOut.setId(js.getLong("comercialDocument_id"));        
            invoiceLineOut.setId(js.getLong("product_id"));        
            invoiceLineOut.setId(js.getLong("stockRackProduct_id"));
        return invoiceLineOut;
    }

    @Override
    public JsonObject toJson(final InvoiceLineOut o) {        
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

            final ComercialDocumentIn comercialDocument = o.getComercialDocument();
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
       slcb.doInLongCondition("id", "invoice_line_out_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "invoice_line_out_pkey");
    slcb.doEqInPSimple( "pkey", "invoice_line_out_pkey");   
    slcb.doGEPSimpleInt( "orden", "invoice_line_out_orden");
    slcb.doLTPSimpleInt( "orden", "invoice_line_out_orden");                
    slcb.doEqInPSimple( "status", "invoice_line_out_status");                    
        
        slcb.doIlPSimple2( "comercialDocument_pkey", "comercial_document_pkey");
        slcb.doEQPSimple2( "comercialDocument_pkey", "comercial_document_pkey");
        slcb.doInLongCondition("comercialDocument_id", "comercial_document_id");  
//ComercialDocumentIn 4        --
        slcb.doIlPSimple2( "comercialDocument_pname", "comercial_document_pname");    
        
        slcb.doIlPSimple2( "product_pkey", "product_pkey");
        slcb.doEQPSimple2( "product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");  
//Product 3        --
        slcb.doIlPSimple2( "product_pname", "product_pname");    
        
        slcb.doIlPSimple2( "stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doEQPSimple2( "stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doInLongCondition("stockRackProduct_id", "stock_rack_product_id");  
//StockRackProduct 1        --
        slcb.doIlPSimple2( "stockRackProduct_pname", "stock_rack_product_pname");    
        
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");
//ContractIn 1
        slcb.doIlPSimple2( "contract_pname", "contract_pname"); 
        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id"); 
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id"); 
        slcb.doIlPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");
//UserLon 4
        slcb.doIlPSimple2( "userAutor_pname", "user_autor_pname"); 
        slcb.doIlPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doEQPSimple2( "comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_id");
//ComercialDocumentTypeIn 2
        slcb.doIlPSimple2( "comercialDocumentType_pname", "comercial_document_type_pname"); 
        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");
//ProductType 5
        slcb.doIlPSimple2( "productType_pname", "product_type_pname"); 
        slcb.doIlPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doEQPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doInLongCondition("stockRack_id", "stock_rack_id");
//StockRack 2
        slcb.doIlPSimple2( "stockRack_pname", "stock_rack_pname"); 
        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"invoice_line_out");
        sz0.addField("COUNT(invoice_line_out.id) as c_invoice_line_out_id").addName("count");
        
    sz0.addField("sum(invoice_line_out.ask_quantity) as sum_invoice_line_out_ask_quantity").addName("sum_askQuantity"); 
    sz0.addField("sum(invoice_line_out.descount) as sum_invoice_line_out_descount").addName("sum_descount"); 
    sz0.addField("sum(invoice_line_out.orden) as sum_invoice_line_out_orden").addName("sum_orden"); 
    sz0.addField("sum(invoice_line_out.supply_quantity) as sum_invoice_line_out_supply_quantity").addName("sum_supplyQuantity"); 
    sz0.addField("sum(invoice_line_out.tax_porcent) as sum_invoice_line_out_tax_porcent").addName("sum_taxPorcent"); 
    sz0.addField("sum(invoice_line_out.total) as sum_invoice_line_out_total").addName("sum_total"); 
    sz0.addField("sum(invoice_line_out.total_cost) as sum_invoice_line_out_total_cost").addName("sum_totalCost"); 
    sz0.addField("sum(invoice_line_out.unit_cost) as sum_invoice_line_out_unit_cost").addName("sum_unitCost"); 
        
//level 1
             
    sz0.applyG1(mz1.get("zComercialDocument"))   ;               
    sz0.applyG1(mz1.get("zProduct"))   ;               
    sz0.applyG1(mz1.get("zStockRackProduct"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zContractFromComercialDocument"));                           
    sz0.applyG2(mz2.get("zUserAutorFromComercialDocument"));                           
    sz0.applyG2(mz2.get("zComercialDocumentTypeFromComercialDocument"));                           
    sz0.applyG2(mz2.get("zProductTypeFromProduct"));                           
    sz0.applyG2(mz2.get("zStockRackFromStockRackProduct"));                           
    sz0.applyG2(mz2.get("zProductFromStockRackProduct"));                           
//level 3    
        sz0.applyG3(mz3.get("zProgramBaseTimePeriodFromContractFromComercialDocument"));               
        sz0.applyG3(mz3.get("zThirdPersonFromContractFromComercialDocument"));               
        sz0.applyG3(mz3.get("zWorkSpaceFromStockRackFromStockRackProduct"));               
        sz0.applyG3(mz3.get("zProductTypeFromProductFromStockRackProduct"));               
        return sz0;
    }
}
    
        