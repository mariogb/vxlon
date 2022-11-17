
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
 *   ThirdPersonService 
 * 
 */
  
public class ThirdPersonService extends AbstractServiceLon<ThirdPerson>{

    private static final String SQLINSERT ="INSERT INTO third_person(pkey,pname,rfc,tipo) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE third_person SET pname = $1,rfc = $2,tipo = $3 WHERE id = $4 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE third_person SET pname = $1,rfc = $2,tipo = $3 WHERE pkey = $4 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM third_person_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM third_person_view";
    private static final String SQLKEYS = "SELECT third_person_pkey FROM third_person_view";
    private static final String SQLIDBYPKEY = "SELECT id from third_person WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from third_person WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM third_person WHERE id = $1 returning id";
    private static final String TABLENAME ="third_person";
    

    public ThirdPersonService() {
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
    private static String sql00 = "SELECT third_person.id as third_person_id,
third_person.pkey as third_person_pkey,
third_person.pname as third_person_pname,
third_person.rfc as third_person_rfc,
third_person.tipo as third_person_tipo 
  FROM 
  third_person "
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
        
    dcModel.put("dc", "thirdPerson");

//ID 
    names.add("id");

    sortMapFields.put("id","third_person_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","third_person");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("third_person.third_person_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//pname
    doFieldSort("pname","pname","third_person");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//rfc
    doFieldSort("rfc","rfc","third_person");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("third_person.third_person_uidx_rfc","rfc");  

//Create property rfc       
    final JsonObject rfc = psString("rfc",true);

// IS Unique     
    rfc.put("uq",true);                    

// hasIndex 
    rfc.put("withIndex",true);  
 
    ps.add(rfc);
 
//tipo
    doFieldSort("tipo","tipo","third_person");               

//Create property tipo       
    final JsonObject tipo = psString("tipo",true);

    final JsonArray tipoInList = new JsonArray();
                tipoInList.add("FISICA"); 
tipoInList.add("MORAL"); 
    tipo.put("inList",tipoInList );                
 
    ps.add(tipo);

//Add ps to model            
    dcModel.put("ps", ps);        
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"contractIns","contractIn"); 
                

        applyOtm(otm,"contractOuts","contractOut"); 
                

        applyOtm(otm,"userThirdPersons","userThirdPerson"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"comercialDocuments","comercialDocumentIn","contractIns","contract",null); 
        

        applyOtm2(otm2,"appointments","appointment","contractOuts",null,null); 
        

        applyOtm2(otm2,"comercialDocuments","comercialDocumentOut","contractOuts","contract",null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"invoiceLines","invoiceLineOut","comercialDocuments","comercialDocument","contract",null); 
        

        applyOtm3(otm3,"payments","paymentIn","comercialDocuments",null,"contract",null); 
        

        applyOtm3(otm3,"invoiceLines","invoiceLineIn","comercialDocuments","comercialDocument","contract",null); 
        

        applyOtm3(otm3,"payments","paymentOut","comercialDocuments",null,"contract",null); 
        

/** OTM 3  ON MODEL**/
        dcModel.put("otm3",otm3);
        
        
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
        jsa.add(r.getLong("third_person_id") );       
        jsa.add(r.getString("third_person_pkey") );       
        jsa.add(r.getString("third_person_pname") );       
        jsa.add(r.getString("third_person_rfc") );       
        jsa.add(r.getString("third_person_tipo") );
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
        m.put("thirdPerson_id",LONG);
    }        
//pkey    
    m.put("thirdPerson_pkey",STRING);              
//pname    
    m.put("thirdPerson_pname",STRING);              
//rfc    
    m.put("thirdPerson_rfc",STRING);              
//tipo    
    m.put("thirdPerson_tipo",STRING);          
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"third_person_id", nc++); 
    }            //pkey       
            sToCell(r, row,"third_person_pkey", nc++);     //pname       
            sToCell(r, row,"third_person_pname", nc++);     //rfc       
            sToCell(r, row,"third_person_rfc", nc++);     //tipo       
            sToCell(r, row,"third_person_tipo", nc++); 
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE third_person_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final ThirdPerson dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addString(dc0.getPname());        
    t.addString(dc0.getRfc());        
    t.addString(dc0.getTipo());
    }

    @Override
    public void fillTupleUpdate(final ThirdPerson dc0, final Tuple t){
        
    t.addString(dc0.getPname());
    t.addString(dc0.getRfc());
    t.addString(dc0.getTipo());
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);

    fTString("rfc", obj, t);

    fTString("tipo", obj, t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
        
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTString("pname", js, t);
    fTString("rfc", js, t);
    fTString("tipo", js, t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);
fTString("rfc", js, t);
fTString("tipo", js, t);
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
    public ThirdPerson doFrom(final Row r){
        final ThirdPerson thirdPerson = new ThirdPerson();
         thirdPerson.setId(r.getLong("third_person_id"));         
                thirdPerson.setPkey(  r.getString("third_person_pkey"));                       
                thirdPerson.setPname(  r.getString("third_person_pname"));                       
                thirdPerson.setRfc(  r.getString("third_person_rfc"));                       
                thirdPerson.setTipo(  r.getString("third_person_tipo"));                
        return thirdPerson;
    }
    
    @Override
    public ThirdPerson doFromJson(final JsonObject js){
        ThirdPerson thirdPerson = new ThirdPerson();
        thirdPerson.setId(js.getLong("id"));
        
                
                thirdPerson.setPkey(js.getString("pkey"));        
                thirdPerson.setPname(js.getString("pname"));        
                thirdPerson.setRfc(js.getString("rfc"));        
                thirdPerson.setTipo(js.getString("tipo"));
        return thirdPerson;
    }

    @Override
    public JsonObject toJson(final ThirdPerson o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );
        jso.put("rfc",  o.getRfc() );
        jso.put("tipo",  o.getTipo() );
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "third_person_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "third_person_pkey");
    slcb.doEqInPSimple( "pkey", "third_person_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "third_person_pname");
    slcb.doEqInPSimple( "pname", "third_person_pname");             
// withIndex true
    slcb.doIlPSimple2( "rfc", "third_person_rfc");
    slcb.doEqInPSimple( "rfc", "third_person_rfc");                    
    slcb.doEqInPSimple( "tipo", "third_person_tipo");                    
        

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"third_person");
        sz0.addField("COUNT(third_person.id) as c_third_person_id").addName("count");
        
        
        return sz0;
    }
}
    
        