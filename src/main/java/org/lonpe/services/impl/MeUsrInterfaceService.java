
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
 *   MeUsrInterfaceService 
 * 
 */
   
  
public class MeUsrInterfaceService extends AbstractServiceLon<MeUsrInterface>{

    private static final String SQLINSERT ="INSERT INTO me_usr_interface(pkey,dc,label,level) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE me_usr_interface SET dc = $1,label = $2,level = $3 WHERE id = $4 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE me_usr_interface SET dc = $1,label = $2,level = $3 WHERE pkey = $4 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM me_usr_interface_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM me_usr_interface_view";
    private static final String SQLKEYS = "SELECT me_usr_interface_pkey FROM me_usr_interface_view";
    private static final String SQLIDBYPKEY = "SELECT id from me_usr_interface WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from me_usr_interface WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM me_usr_interface WHERE id = $1 returning id";
    private static final String TABLENAME ="me_usr_interface";
    
    
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
    
    private static String sql00 = "SELECT me_usr_interface.id as me_usr_interface_id,
me_usr_interface.pkey as me_usr_interface_pkey,
me_usr_interface.dc as me_usr_interface_dc,
me_usr_interface.label as me_usr_interface_label,
me_usr_interface.level as me_usr_interface_level 
  FROM 
  me_usr_interface ; 
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

        
    dcModel.put("dc", "meUsrInterface");

//ID 
    names.add("id");

    sortMapFields.put("id","me_usr_interface_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("me_usr_interface.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("me_usr_interface.me_usr_interface_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "me_usr_interface_pkey");                   
 
    ps.add(pkey);
 
//dc
    names.add("dc");
    insertMapFields.put("me_usr_interface.dc","dc");  

//Create property dc       
    final JsonObject dc = ps00a("dc", "String",true);
 
    ps.add(dc);
 
//label
    names.add("label");
    insertMapFields.put("me_usr_interface.label","label");  

//Create property label       
    final JsonObject label = ps00a("label", "String",true);

    sortMapFields.put("label", "me_usr_interface_label");                   
  
//PC
    dcModel.put("pc","label");  
 
    ps.add(label);
 
//level
    names.add("level");
    insertMapFields.put("me_usr_interface.level","level");  

//Create property level       
    final JsonObject level = ps00a("level", "Integer",true);

    sortMapFields.put("level", "me_usr_interface_level");               
 
    ps.add(level);

//Add ps to model            
    dcModel.put("ps", ps);        
        

        

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
        jsa.add(r.getLong("me_usr_interface_id") );
        jsa.add(r.getString("me_usr_interface_pkey") );
        jsa.add(r.getString("me_usr_interface_dc") );
        jsa.add(r.getString("me_usr_interface_label") );
        jsa.add(r.getInteger("me_usr_interface_level") );
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
                m_.put("meUsrInterface_id","Long");
            }
            
    //pkey       
            m_.put("meUsrInterface_pkey","String"); 
            
    //dc       
            m_.put("meUsrInterface_dc","String"); 
            
    //label       
            m_.put("meUsrInterface_label","String"); 
            
    //level       
            m_.put("meUsrInterface_level","Integer"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"me_usr_interface_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"me_usr_interface_pkey", nc++); 
    //dc       
            sToCell(r, row,"me_usr_interface_dc", nc++); 
    //label       
            sToCell(r, row,"me_usr_interface_label", nc++); 
    //level            
            ldToCell(r, row,"me_usr_interface_level", nc++); 
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE me_usr_interface_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final MeUsrInterface dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDc());
        t.addString(dc0.getLabel());
        t.addInteger(dc0.getLevel());
    }

    @Override
    public void fillTupleUpdate(final MeUsrInterface dc0, final Tuple t){
                t.addString(dc0.getDc());
        t.addString(dc0.getLabel());
        t.addInteger(dc0.getLevel());

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("dc", obj, t);

    fTString("label", obj, t);

    fTInteger("level", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("dc", js, t);

    fTString("label", js, t);

    fTInteger("level", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("dc", js, t);
fTString("label", js, t);
fTInteger("level", js, t);
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
    public MeUsrInterface doFrom(final Row r){
        final MeUsrInterface meUsrInterface = new MeUsrInterface();
         meUsrInterface.setId(r.getLong("me_usr_interface_id"));
         
                meUsrInterface.setPkey(  r.getString("me_usr_interface_pkey"));
         
                meUsrInterface.setDc(  r.getString("me_usr_interface_dc"));
         
                meUsrInterface.setLabel(  r.getString("me_usr_interface_label"));
         
                meUsrInterface.setLevel(  r.getInteger("me_usr_interface_level"));  
        return meUsrInterface;
    }
    
    @Override
    public MeUsrInterface doFromJson(final JsonObject js){
        MeUsrInterface meUsrInterface = new MeUsrInterface();
        meUsrInterface.setId(js.getLong("id"));
        
                meUsrInterface.setPkey(js.getString("pkey"));
        meUsrInterface.setDc(js.getString("dc"));
        meUsrInterface.setLabel(js.getString("label"));
        meUsrInterface.setLevel(js.getInteger("level"));
        return meUsrInterface;
    }

    @Override
    public JsonObject toJson(final MeUsrInterface o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("dc",  o.getDc() );
        jso.put("label",  o.getLabel() );
        jso.put("level",  o.getLevel() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "me_usr_interface_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "me_usr_interface_pkey");
    slcb.doEqInPSimple( "pkey", "me_usr_interface_pkey");
//*---PC ---" + label
    slcb.doIlPSimple2( "label", "me_usr_interface_label");
    slcb.doEqInPSimple( "label", "me_usr_interface_label");               
    slcb.doGEPSimpleInt( "level", "me_usr_interface_level");
    slcb.doLTPSimpleInt( "level", "me_usr_interface_level");                
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"me_usr_interface");
        sz0.addField("COUNT(me_usr_interface.id) as c_me_usr_interface_id").addName("count");
        
    sz0.addField("sum(me_usr_interface.level) as sum_me_usr_interface_level").addName("sum_level");
        
        
        return sz0;
    }
}
    
        