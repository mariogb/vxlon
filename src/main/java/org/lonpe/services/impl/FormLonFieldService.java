
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
 *   FormLonFieldService 
 * 
 */
   
  
public class FormLonFieldService extends AbstractServiceLon<FormLonField>{

    private static final String SQLINSERT ="INSERT INTO form_lon_field(pkey,pname,form_lon_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE form_lon_field SET pname = $1 WHERE id = $4 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE form_lon_field SET pname = $1 WHERE pkey = $4 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM form_lon_field_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM form_lon_field_view";
    private static final String SQLKEYS = "SELECT form_lon_field_pkey FROM form_lon_field_view";
    private static final String SQLIDBYPKEY = "SELECT id from form_lon_field WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from form_lon_field WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM form_lon_field WHERE id = $1 returning id";
    private static final String TABLENAME ="form_lon_field";
    
//1
    private static final ZtatUnitInfoLon zFormLon;
    
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
    
    private static String sql00 = "SELECT form_lon_field.id as form_lon_field_id,
form_lon_field.pkey as form_lon_field_pkey,
form_lon_field.pname as form_lon_field_pname,
form_lon.id as form_lon_id,form_lon.pkey as form_lon_pkey,form_lon.pname as form_lon_pname 
  FROM 
  form_lon_field,
  form_lon as form_lon  
 WHERE 
 form_lon_field.form_lon_id = form_lon.id; 
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

        
    dcModel.put("dc", "formLonField");

//ID 
    names.add("id");

    sortMapFields.put("id","form_lon_field_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("form_lon_field.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("form_lon_field.form_lon_field_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "form_lon_field_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("form_lon_field.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "form_lon_field_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  formLon --------------------
    names.add("formLon_id");      
    insertMapFields.put("form_lon_field.form_lon_id","formLon_id");    
       
    names.add("formLon_pkey");        
    sortMapFields.put( "formLon_pkey", "form_lon_pkey");        

    final JsonObject formLon =  doMto("formLon","formLon");        
   
    names.add("formLon_pname");
    sortMapFields.put( "formLon_pname", "form_lon_pname");         

    formLon.put("pc","pname");          

    mto.add(formLon);
        

    dcModel.put("mto",mto);     
        

        
//1  form_lon  -- form_lon_id
    zFormLon = new ZtatUnitInfoLon("form_lon_id", "formLon",  "form_lon","pname","form_lon");

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
        jsa.add(r.getLong("form_lon_field_id") );
        jsa.add(r.getString("form_lon_field_pkey") );
        jsa.add(r.getString("form_lon_field_pname") );
        jsa.add(r.getLong("form_lon_id"));
        jsa.add(r.getString("form_lon_pkey"));
        jsa.add(r.getString("form_lon_pname"));
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
                m_.put("formLonField_id","Long");
            }
            
    //pkey       
            m_.put("formLonField_pkey","String"); 
            
    //pname       
            m_.put("formLonField_pname","String"); 
            
if(level<1){
                return m_;    
            }
            
 // formLon
if(withIds){
            m_.put("formLon_id","Long");   
                    
            }

        m_.put("formLon_pkey","String");   
        

            m_.put("formLon_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"form_lon_field_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"form_lon_field_pkey", nc++); 
    //pname       
            sToCell(r, row,"form_lon_field_pname", nc++); 
 // formLon
if(withIds){
                    lToCell(r, row,"form_lon_id", nc++);
                 }
sToCell(r, row,"form_lon_pkey", nc++);
sToCell(r, row,"form_lon_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE form_lon_field_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final FormLonField dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
   
            if(dc0.getFormLon()!=null){
               t.addLong(dc0.getFormLon().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final FormLonField dc0, final Tuple t){
                t.addString(dc0.getPname());
   
//      if(dc0.getFormLon()!=null){
//            t.addLong(dc0.getFormLon().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);

    fTLong("formLon_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> formLon = mapParents.get("formLon");
            final Long formLon_id = formLon.get((String)obj.get("formLon_pkey"));
            obj.put("formLon_id", formLon_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("pname", js, t);
     
    fTLong("formLon_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);

            //     fTLong("formLon_id",js,t);
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
    public FormLonField doFrom(final Row r){
        final FormLonField formLonField = new FormLonField();
         formLonField.setId(r.getLong("form_lon_field_id"));
         
                formLonField.setPkey(  r.getString("form_lon_field_pkey"));
         
                formLonField.setPname(  r.getString("form_lon_field_pname"));

        final FormLon formLon = new FormLon();
        formLon.setId(r.getLong("form_lon_id"));
        formLon.setPkey(r.getString("form_lon_pkey"));
        formLon.setPname(r.getString("form_lon_pname"));
        formLonField.setFormLon(formLon);
          
        return formLonField;
    }
    
    @Override
    public FormLonField doFromJson(final JsonObject js){
        FormLonField formLonField = new FormLonField();
        formLonField.setId(js.getLong("id"));
        
                formLonField.setPkey(js.getString("pkey"));
        formLonField.setPname(js.getString("pname"));
        formLonField.setId(js.getLong("formLon_id"));
        return formLonField;
    }

    @Override
    public JsonObject toJson(final FormLonField o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );

            final FormLon formLon = o.getFormLon();
            if(formLon!=null){
                jso.put("formLon_id", formLon.getId());
                jso.put("formLon_pkey", formLon.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "form_lon_field_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "form_lon_field_pkey");
    slcb.doEqInPSimple( "pkey", "form_lon_field_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "form_lon_field_pname");
    slcb.doEqInPSimple( "pname", "form_lon_field_pname");            
        
        slcb.doIlPSimple2( "formLon_pkey", "form_lon_pkey");
        slcb.doEQPSimple2( "formLon_pkey", "form_lon_pkey");
        slcb.doInLongCondition("formLon_id", "form_lon_id");  
//FormLon 1        
        slcb.doIlPSimple2( "formLon_pname", "form_lon_pname");    

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"form_lon_field");
        sz0.addField("COUNT(form_lon_field.id) as c_form_lon_field_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zFormLon);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        