
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
 *   ComercialDocumentTypeOutService 
 * 
 */
  
public class ComercialDocumentTypeOutService extends AbstractServiceLon<ComercialDocumentTypeOut>{

    private static final String SQLINSERT ="INSERT INTO comercial_document_type_out(pkey,afect_stock,pname) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE comercial_document_type_out SET afect_stock = $1,pname = $2 WHERE id = $3 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE comercial_document_type_out SET afect_stock = $1,pname = $2 WHERE pkey = $3 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM comercial_document_type_out_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM comercial_document_type_out_view";
    private static final String SQLKEYS = "SELECT comercial_document_type_out_pkey FROM comercial_document_type_out_view";
    private static final String SQLIDBYPKEY = "SELECT id from comercial_document_type_out WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from comercial_document_type_out WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM comercial_document_type_out WHERE id = $1 returning id";
    private static final String TABLENAME ="comercial_document_type_out";
    

    public ComercialDocumentTypeOutService() {
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
    private static String sql00 = "SELECT comercial_document_type_out.id as comercial_document_type_out_id,
comercial_document_type_out.pkey as comercial_document_type_out_pkey,
comercial_document_type_out.afect_stock as comercial_document_type_out_afect_stock,
comercial_document_type_out.pname as comercial_document_type_out_pname 
  FROM 
  comercial_document_type_out "
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
        
    dcModel.put("dc", "comercialDocumentTypeOut");

//ID 
    names.add("id");

    sortMapFields.put("id","comercial_document_type_out_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","comercial_document_type_out");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("comercial_document_type_out.comercial_document_type_out_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//afectStock
    doFieldSort("afectStock","afect_stock","comercial_document_type_out");               

//Create property afectStock       
    final JsonObject afectStock = psString("afectStock",true);

    final JsonArray afectStockInList = new JsonArray();
                afectStockInList.add("NO"); 
afectStockInList.add("RESERVE"); 
afectStockInList.add("YES"); 
    afectStock.put("inList",afectStockInList );                
 
    ps.add(afectStock);
 
//pname
    doFieldSort("pname","pname","comercial_document_type_out");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

//ON RELATION comercialDocumentType    
            applyOtm(otm,"comercialDocuments","comercialDocumentOut","comercialDocumentType"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"invoiceLines","invoiceLineIn","comercialDocuments","comercialDocument","comercialDocumentType"); 
        

        applyOtm2(otm2,"payments","paymentOut","comercialDocuments",null,"comercialDocumentType"); 
        

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
        jsa.add(r.getLong("comercial_document_type_out_id") );       
        jsa.add(r.getString("comercial_document_type_out_pkey") );       
        jsa.add(r.getString("comercial_document_type_out_afect_stock") );       
        jsa.add(r.getString("comercial_document_type_out_pname") );
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
        m.put("comercialDocumentTypeOut_id",LONG);
    }        
//pkey    
    m.put("comercialDocumentTypeOut_pkey",STRING);              
//afectStock    
    m.put("comercialDocumentTypeOut_afectStock",STRING);              
//pname    
    m.put("comercialDocumentTypeOut_pname",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"comercial_document_type_out_id", nc++); 
    }            //pkey       
            sToCell(r, row,"comercial_document_type_out_pkey", nc++);     //afectStock       
            sToCell(r, row,"comercial_document_type_out_afect_stock", nc++);     //pname       
            sToCell(r, row,"comercial_document_type_out_pname", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE comercial_document_type_out_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ComercialDocumentTypeOut dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getAfectStock());        
    t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final ComercialDocumentTypeOut dc0, final Tuple t){
        
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
    public ComercialDocumentTypeOut doFrom(final Row r){
        final ComercialDocumentTypeOut comercialDocumentTypeOut = new ComercialDocumentTypeOut();
         comercialDocumentTypeOut.setId(r.getLong("comercial_document_type_out_id"));         
                comercialDocumentTypeOut.setPkey(  r.getString("comercial_document_type_out_pkey"));                       
                comercialDocumentTypeOut.setAfectStock(  r.getString("comercial_document_type_out_afect_stock"));                       
                comercialDocumentTypeOut.setPname(  r.getString("comercial_document_type_out_pname"));                
        return comercialDocumentTypeOut;
    }
    
    @Override
    public ComercialDocumentTypeOut doFromJson(final JsonObject js){
        ComercialDocumentTypeOut comercialDocumentTypeOut = new ComercialDocumentTypeOut();
        comercialDocumentTypeOut.setId(js.getLong("id"));
        
                
                comercialDocumentTypeOut.setPkey(js.getString("pkey"));        
                comercialDocumentTypeOut.setAfectStock(js.getString("afectStock"));        
                comercialDocumentTypeOut.setPname(js.getString("pname"));
        return comercialDocumentTypeOut;
    }

    @Override
    public JsonObject toJson(final ComercialDocumentTypeOut o) {        
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
       slcb.doInLongCondition("id", "comercial_document_type_out_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "comercial_document_type_out_pkey");
    slcb.doEqInPSimple( "pkey", "comercial_document_type_out_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "comercial_document_type_out_pname");
    slcb.doEqInPSimple( "pname", "comercial_document_type_out_pname");            
    slcb.doEqInPSimple( "afectStock", "comercial_document_type_out_afect_stock");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"comercial_document_type_out");
        sz0.addField("COUNT(comercial_document_type_out.id) as c_comercial_document_type_out_id").addName("count");
        
        
        return sz0;
    }
}
    
        