
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
 *   ComercialDocumentTypeInService 
 * 
 */
   
  
public class ComercialDocumentTypeInService extends AbstractServiceLon<ComercialDocumentTypeIn>{

    private static final String SQLINSERT ="INSERT INTO comercial_document_type_in(pkey,afect_stock,pname) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE comercial_document_type_in SET afect_stock = $1,pname = $2 WHERE id = $3 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE comercial_document_type_in SET afect_stock = $1,pname = $2 WHERE pkey = $3 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM comercial_document_type_in_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM comercial_document_type_in_view";
    private static final String SQLKEYS = "SELECT comercial_document_type_in_pkey FROM comercial_document_type_in_view";
    private static final String SQLIDBYPKEY = "SELECT id from comercial_document_type_in WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from comercial_document_type_in WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM comercial_document_type_in WHERE id = $1 returning id";
    private static final String TABLENAME ="comercial_document_type_in";
    
    
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
    
    private static String sql00 = "SELECT comercial_document_type_in.id as comercial_document_type_in_id,
comercial_document_type_in.pkey as comercial_document_type_in_pkey,
comercial_document_type_in.afect_stock as comercial_document_type_in_afect_stock,
comercial_document_type_in.pname as comercial_document_type_in_pname 
  FROM 
  comercial_document_type_in ; 
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

        
    dcModel.put("dc", "comercialDocumentTypeIn");

//ID 
    names.add("id");

    sortMapFields.put("id","comercial_document_type_in_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("comercial_document_type_in.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("comercial_document_type_in.comercial_document_type_in_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "comercial_document_type_in_pkey");                   
 
    ps.add(pkey);
 
//afectStock
    names.add("afectStock");
    insertMapFields.put("comercial_document_type_in.afect_stock","afectStock");  

//Create property afectStock       
    final JsonObject afectStock = ps00a("afectStock", "String",true);

    sortMapFields.put("afectStock", "comercial_document_type_in_afect_stock");                   

    final JsonArray afectStockInList = new JsonArray();
                afectStockInList.add("NO"); 
afectStockInList.add("RESERVE"); 
afectStockInList.add("YES"); 
    afectStock.put("inList",afectStockInList );                
 
    ps.add(afectStock);
 
//pname
    names.add("pname");
    insertMapFields.put("comercial_document_type_in.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "comercial_document_type_in_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

//ON RELATION comercialDocumentType    
            applyOtm(otm,"comercialDocuments","comercialDocumentIn","comercialDocumentType"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"invoiceLines","invoiceLineOut","comercialDocuments","comercialDocument","comercialDocumentType"); 
        

        applyOtm2(otm2,"payments","paymentIn","comercialDocuments",null,"comercialDocumentType"); 
        

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
        jsa.add(r.getLong("comercial_document_type_in_id") );
        jsa.add(r.getString("comercial_document_type_in_pkey") );
        jsa.add(r.getString("comercial_document_type_in_afect_stock") );
        jsa.add(r.getString("comercial_document_type_in_pname") );
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
                m_.put("comercialDocumentTypeIn_id","Long");
            }
            
    //pkey       
            m_.put("comercialDocumentTypeIn_pkey","String"); 
            
    //afectStock       
            m_.put("comercialDocumentTypeIn_afectStock","String"); 
            
    //pname       
            m_.put("comercialDocumentTypeIn_pname","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"comercial_document_type_in_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"comercial_document_type_in_pkey", nc++); 
    //afectStock       
            sToCell(r, row,"comercial_document_type_in_afect_stock", nc++); 
    //pname       
            sToCell(r, row,"comercial_document_type_in_pname", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE comercial_document_type_in_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ComercialDocumentTypeIn dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getAfectStock());
        t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final ComercialDocumentTypeIn dc0, final Tuple t){
                t.addString(dc0.getAfectStock());
        t.addString(dc0.getPname());

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("afectStock", obj, t);

    fTString("pname", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("afectStock", js, t);

    fTString("pname", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("afectStock", js, t);
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
    public ComercialDocumentTypeIn doFrom(final Row r){
        final ComercialDocumentTypeIn comercialDocumentTypeIn = new ComercialDocumentTypeIn();
         comercialDocumentTypeIn.setId(r.getLong("comercial_document_type_in_id"));
         
                comercialDocumentTypeIn.setPkey(  r.getString("comercial_document_type_in_pkey"));
         
                comercialDocumentTypeIn.setAfectStock(  r.getString("comercial_document_type_in_afect_stock"));
         
                comercialDocumentTypeIn.setPname(  r.getString("comercial_document_type_in_pname"));  
        return comercialDocumentTypeIn;
    }
    
    @Override
    public ComercialDocumentTypeIn doFromJson(final JsonObject js){
        ComercialDocumentTypeIn comercialDocumentTypeIn = new ComercialDocumentTypeIn();
        comercialDocumentTypeIn.setId(js.getLong("id"));
        
                comercialDocumentTypeIn.setPkey(js.getString("pkey"));
        comercialDocumentTypeIn.setAfectStock(js.getString("afectStock"));
        comercialDocumentTypeIn.setPname(js.getString("pname"));
        return comercialDocumentTypeIn;
    }

    @Override
    public JsonObject toJson(final ComercialDocumentTypeIn o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("afectStock",  o.getAfectStock() );
        jso.put("pname",  o.getPname() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "comercial_document_type_in_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "comercial_document_type_in_pkey");
    slcb.doEqInPSimple( "pkey", "comercial_document_type_in_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "comercial_document_type_in_pname");
    slcb.doEqInPSimple( "pname", "comercial_document_type_in_pname");            
    slcb.doEqInPSimple( "afectStock", "comercial_document_type_in_afect_stock");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"comercial_document_type_in");
        sz0.addField("COUNT(comercial_document_type_in.id) as c_comercial_document_type_in_id").addName("count");
        
        
        return sz0;
    }
}
    
        