
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
 *   WorkSpaceGroupService 
 * 
 */
   
  
public class WorkSpaceGroupService extends AbstractServiceLon<WorkSpaceGroup>{

    private static final String SQLINSERT ="INSERT INTO work_space_group(pkey,pname,type_lon,base_id) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE work_space_group SET pname = $1,type_lon = $2 WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE work_space_group SET pname = $1,type_lon = $2 WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM work_space_group_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM work_space_group_view";
    private static final String SQLKEYS = "SELECT work_space_group_pkey FROM work_space_group_view";
    private static final String SQLIDBYPKEY = "SELECT id from work_space_group WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from work_space_group WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM work_space_group WHERE id = $1 returning id";
    private static final String TABLENAME ="work_space_group";
    
//1
    private static final ZtatUnitInfoLon zBase;
    
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
    
    private static String sql00 = "SELECT work_space_group.id as work_space_group_id,
work_space_group.pkey as work_space_group_pkey,
work_space_group.pname as work_space_group_pname,
work_space_group.type_lon as work_space_group_type_lon,
base.id as base_id,base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  work_space_group,
  base as base  
 WHERE 
 work_space_group.base_id = base.id; 
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

        
    dcModel.put("dc", "workSpaceGroup");

//ID 
    names.add("id");

    sortMapFields.put("id","work_space_group_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("work_space_group.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("work_space_group.work_space_group_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "work_space_group_pkey");                   
 
    ps.add(pkey);
 
//pname
    names.add("pname");
    insertMapFields.put("work_space_group.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "work_space_group_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//typeLon
    names.add("typeLon");
    insertMapFields.put("work_space_group.type_lon","typeLon");  

//Create property typeLon       
    final JsonObject typeLon = ps00a("typeLon", "String",true);

    sortMapFields.put("typeLon", "work_space_group_type_lon");                   

    final JsonArray typeLonInList = new JsonArray();
                typeLonInList.add("BUILD"); 
typeLonInList.add("MOVIL"); 
    typeLon.put("inList",typeLonInList );                
 
    ps.add(typeLon);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  base --------------------
    names.add("base_id");      
    insertMapFields.put("work_space_group.base_id","base_id");    
       
    names.add("base_pkey");        
    sortMapFields.put( "base_pkey", "base_pkey");        

    final JsonObject base =  doMto("base","base");        
   
    names.add("base_pname");
    sortMapFields.put( "base_pname", "base_pname");         

    base.put("pc","pname");          

    mto.add(base);
        

    dcModel.put("mto",mto);     
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"workSpaces","workSpace"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"appointments","appointment","workSpaces",null,null); 
        

        applyOtm2(otm2,"stockRack","stockRack","workSpaces",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"stockRackProducts","stockRackProduct","stockRack",null,null,null); 
        

/** OTM 3  ON MODEL**/
        dcModel.put("otm3",otm3);
        

        
//1  base  -- base_id
    zBase = new ZtatUnitInfoLon("base_id", "base",  "base","pname","base");

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
        jsa.add(r.getLong("work_space_group_id") );
        jsa.add(r.getString("work_space_group_pkey") );
        jsa.add(r.getString("work_space_group_pname") );
        jsa.add(r.getString("work_space_group_type_lon") );
        jsa.add(r.getLong("base_id"));
        jsa.add(r.getString("base_pkey"));
        jsa.add(r.getString("base_pname"));
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
                m_.put("workSpaceGroup_id","Long");
            }
            
    //pkey       
            m_.put("workSpaceGroup_pkey","String"); 
            
    //pname       
            m_.put("workSpaceGroup_pname","String"); 
            
    //typeLon       
            m_.put("workSpaceGroup_typeLon","String"); 
            
if(level<1){
                return m_;    
            }
            
 // base
if(withIds){
            m_.put("base_id","Long");   
                    
            }

        m_.put("base_pkey","String");   
        

            m_.put("base_pname","String");   
            
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"work_space_group_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"work_space_group_pkey", nc++); 
    //pname       
            sToCell(r, row,"work_space_group_pname", nc++); 
    //typeLon       
            sToCell(r, row,"work_space_group_type_lon", nc++); 
 // base
if(withIds){
                    lToCell(r, row,"base_id", nc++);
                 }
sToCell(r, row,"base_pkey", nc++);
sToCell(r, row,"base_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE work_space_group_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final WorkSpaceGroup dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());
   
            if(dc0.getBase()!=null){
               t.addLong(dc0.getBase().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final WorkSpaceGroup dc0, final Tuple t){
                t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());
   
//      if(dc0.getBase()!=null){
//            t.addLong(dc0.getBase().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("pname", obj, t);

    fTString("typeLon", obj, t);

    fTLong("base_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> base = mapParents.get("base");
            final Long base_id = base.get((String)obj.get("base_pkey"));
            obj.put("base_id", base_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("pname", js, t);

    fTString("typeLon", js, t);
     
    fTLong("base_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("pname", js, t);
fTString("typeLon", js, t);

            //     fTLong("base_id",js,t);
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
    public WorkSpaceGroup doFrom(final Row r){
        final WorkSpaceGroup workSpaceGroup = new WorkSpaceGroup();
         workSpaceGroup.setId(r.getLong("work_space_group_id"));
         
                workSpaceGroup.setPkey(  r.getString("work_space_group_pkey"));
         
                workSpaceGroup.setPname(  r.getString("work_space_group_pname"));
         
                workSpaceGroup.setTypeLon(  r.getString("work_space_group_type_lon"));

        final Base base = new Base();
        base.setId(r.getLong("base_id"));
        base.setPkey(r.getString("base_pkey"));
        base.setPname(r.getString("base_pname"));
        workSpaceGroup.setBase(base);
          
        return workSpaceGroup;
    }
    
    @Override
    public WorkSpaceGroup doFromJson(final JsonObject js){
        WorkSpaceGroup workSpaceGroup = new WorkSpaceGroup();
        workSpaceGroup.setId(js.getLong("id"));
        
                workSpaceGroup.setPkey(js.getString("pkey"));
        workSpaceGroup.setPname(js.getString("pname"));
        workSpaceGroup.setTypeLon(js.getString("typeLon"));
        workSpaceGroup.setId(js.getLong("base_id"));
        return workSpaceGroup;
    }

    @Override
    public JsonObject toJson(final WorkSpaceGroup o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("pname",  o.getPname() );
        jso.put("typeLon",  o.getTypeLon() );

            final Base base = o.getBase();
            if(base!=null){
                jso.put("base_id", base.getId());
                jso.put("base_pkey", base.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "work_space_group_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "work_space_group_pkey");
    slcb.doEqInPSimple( "pkey", "work_space_group_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "work_space_group_pname");
    slcb.doEqInPSimple( "pname", "work_space_group_pname");            
    slcb.doEqInPSimple( "typeLon", "work_space_group_type_lon");                    
        
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");  
//Base 1        
        slcb.doIlPSimple2( "base_pname", "base_pname");    

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"work_space_group");
        sz0.addField("COUNT(work_space_group.id) as c_work_space_group_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zBase);      
    //level 2
    
    //level 3
    
        return sz0;
    }
}
    
        