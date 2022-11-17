
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
 *   FormLonService 
 * 
 */
  
public class FormLonService extends AbstractServiceLon<FormLon>{

    private static final String SQLINSERT ="INSERT INTO form_lon(pkey,pname) VALUES ($1,$2) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE form_lon SET pname = $1 WHERE id = $2 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE form_lon SET pname = $1 WHERE pkey = $2 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM form_lon_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM form_lon_view";
    private static final String SQLKEYS = "SELECT form_lon_pkey FROM form_lon_view";
    private static final String SQLIDBYPKEY = "SELECT id from form_lon WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from form_lon WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM form_lon WHERE id = $1 returning id";
    private static final String TABLENAME ="form_lon";
    

    public FormLonService() {
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
    private static String sql00 = "SELECT form_lon.id as form_lon_id,
form_lon.pkey as form_lon_pkey,
form_lon.pname as form_lon_pname 
  FROM 
  form_lon "
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
        
    dcModel.put("dc", "formLon");

//ID 
    names.add("id");

    sortMapFields.put("id","form_lon_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","form_lon");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("form_lon.form_lon_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","form_lon");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"formLonFields","formLonField"); 
                

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
        jsa.add(r.getLong("form_lon_id") );       
        jsa.add(r.getString("form_lon_pkey") );       
        jsa.add(r.getString("form_lon_pname") );
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
        m.put("formLon_id",LONG);
    }        
//pkey    
    m.put("formLon_pkey",STRING);              
//pname    
    m.put("formLon_pname",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"form_lon_id", nc++); 
    }            //pkey       
            sToCell(r, row,"form_lon_pkey", nc++);     //pname       
            sToCell(r, row,"form_lon_pname", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE form_lon_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final FormLon dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());
    }

    @Override
    public void fillTupleUpdate(final FormLon dc0, final Tuple t){
        
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
    public FormLon doFrom(final Row r){
        final FormLon formLon = new FormLon();
         formLon.setId(r.getLong("form_lon_id"));         
                formLon.setPkey(  r.getString("form_lon_pkey"));                       
                formLon.setPname(  r.getString("form_lon_pname"));                
        return formLon;
    }
    
    @Override
    public FormLon doFromJson(final JsonObject js){
        FormLon formLon = new FormLon();
        formLon.setId(js.getLong("id"));
        
                
                formLon.setPkey(js.getString("pkey"));        
                formLon.setPname(js.getString("pname"));
        return formLon;
    }

    @Override
    public JsonObject toJson(final FormLon o) {        
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
       slcb.doInLongCondition("id", "form_lon_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "form_lon_pkey");
    slcb.doEqInPSimple( "pkey", "form_lon_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "form_lon_pname");
    slcb.doEqInPSimple( "pname", "form_lon_pname");            
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"form_lon");
        sz0.addField("COUNT(form_lon.id) as c_form_lon_id").addName("count");
        
        
        return sz0;
    }
}
    
        