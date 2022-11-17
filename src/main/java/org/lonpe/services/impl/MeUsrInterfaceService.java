
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
    

    public MeUsrInterfaceService() {
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
    private static String sql00 = "SELECT me_usr_interface.id as me_usr_interface_id,
me_usr_interface.pkey as me_usr_interface_pkey,
me_usr_interface.dc as me_usr_interface_dc,
me_usr_interface.label as me_usr_interface_label,
me_usr_interface.level as me_usr_interface_level 
  FROM 
  me_usr_interface "
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
        
    dcModel.put("dc", "meUsrInterface");

//ID 
    names.add("id");

    sortMapFields.put("id","me_usr_interface_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","me_usr_interface");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("me_usr_interface.me_usr_interface_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//dc
    doField("dc","dc","me_usr_interface");               

//Create property dc       
    final JsonObject dc = psString("dc",true);
 
    ps.add(dc);
 
//label
    doFieldSort("label","label","me_usr_interface");               

//Create property label       
    final JsonObject label = psString("label",true);
  
//PC
    dcModel.put("pc","label");  
 
    ps.add(label);
 
//level
    doFieldSort("level","level","me_usr_interface");               

//Create property level       
    final JsonObject level = psInteger("level",true);
 
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
    public int fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        return fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m = new LinkedHashMap<>();
    
    if(withIds){
        m.put("meUsrInterface_id",LONG);
    }        
//pkey    
    m.put("meUsrInterface_pkey",STRING);              
//dc    
    m.put("meUsrInterface_dc",STRING);              
//label    
    m.put("meUsrInterface_label",STRING);              
//level    
    m.put("meUsrInterface_level",INTEGER);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"me_usr_interface_id", nc++); 
    }            //pkey       
            sToCell(r, row,"me_usr_interface_pkey", nc++);     //dc       
            sToCell(r, row,"me_usr_interface_dc", nc++);     //label       
            sToCell(r, row,"me_usr_interface_label", nc++);     //level            
            ldToCell(r, row,"me_usr_interface_level", nc++); 
        return nc;
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
    
        