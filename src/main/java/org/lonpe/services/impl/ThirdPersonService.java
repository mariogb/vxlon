
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
    
    private static String sql00 = "SELECT third_person.id as third_person_id,
third_person.pkey as third_person_pkey,
third_person.pname as third_person_pname,
third_person.rfc as third_person_rfc,
third_person.tipo as third_person_tipo 
  FROM 
  third_person ; 
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

        
    dcModel.put("dc", "thirdPerson");

//ID 
    names.add("id");

    sortMapFields.put("id","third_person_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("third_person.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("third_person.third_person_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "third_person_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("third_person.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "third_person_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//rfc
    names.add("rfc");
    insertMapFields.put("third_person.rfc","rfc");  

//Create property rfc       
    final JsonObject rfc = ps00a("rfc", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("third_person.third_person_uidx_rfc","rfc");  

// IS Unique     
    rfc.put("uq",true);                    

    sortMapFields.put("rfc", "third_person_rfc");                   

// hasIndex 
    rfc.put("withIndex",true);  
 
    ps.add(rfc);
 
//tipo
    names.add("tipo");
    insertMapFields.put("third_person.tipo","tipo");  

//Create property tipo       
    final JsonObject tipo = ps00a("tipo", "String",true);

    sortMapFields.put("tipo", "third_person_tipo");                   

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
    public void fillXRow(final Row r, final XSSFRow row, int nc,boolean withIds) {
        fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String,String> lXRowH(final boolean withIds, final int level) {        
        
    final  LinkedHashMap<String,String> m_ = new LinkedHashMap<>();
    if(withIds){
                m_.put("thirdPerson_id","Long");
            }
            
    //pkey       
            m_.put("thirdPerson_pkey","String"); 
            
    //pname       
            m_.put("thirdPerson_pname","String"); 
            
    //rfc       
            m_.put("thirdPerson_rfc","String"); 
            
    //tipo       
            m_.put("thirdPerson_tipo","String"); 
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"third_person_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"third_person_pkey", nc++); 
    //pname       
            sToCell(r, row,"third_person_pname", nc++); 
    //rfc       
            sToCell(r, row,"third_person_rfc", nc++); 
    //tipo       
            sToCell(r, row,"third_person_tipo", nc++); 
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
    
        