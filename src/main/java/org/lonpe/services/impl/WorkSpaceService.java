
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



import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon2;
import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon3;

/**
 *   WorkSpaceService 
 * 
 */
  
public class WorkSpaceService extends AbstractServiceLon<WorkSpace>{

    private static final String SQLINSERT ="INSERT INTO work_space(pkey,capacity,pname,type,work_space_group_id) VALUES ($1,$2,$3,$4,$5) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE work_space SET capacity = $1,pname = $2,type = $3 WHERE id = $6 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE work_space SET capacity = $1,pname = $2,type = $3 WHERE pkey = $6 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM work_space_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM work_space_view";
    private static final String SQLKEYS = "SELECT work_space_pkey FROM work_space_view";
    private static final String SQLIDBYPKEY = "SELECT id from work_space WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from work_space WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM work_space WHERE id = $1 returning id";
    private static final String TABLENAME ="work_space";
    

    public WorkSpaceService() {
        init0();
    }

    
    private static final Map<String, ZtatUnitInfoLon> mz1 = new HashMap<>(6);                       
    private static final Map<String, ZtatUnitInfoLon2> mz2 = new HashMap<>(6);                       

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
    private static String sql00 = "SELECT work_space.id as work_space_id,
work_space.pkey as work_space_pkey,
work_space.capacity as work_space_capacity,
work_space.pname as work_space_pname,
work_space.type as work_space_type,
work_space_group.id as work_space_group_id,work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  work_space,
  work_space_group as work_space_group,
  base as base  
 WHERE 
 work_space.work_space_group_id = work_space_group.id
 AND work_space_group.base_id = base.id"
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
        
    dcModel.put("dc", "workSpace");

//ID 
    names.add("id");

    sortMapFields.put("id","work_space_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    doFieldSort("pkey","pkey","work_space");               
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("work_space.work_space_uidx_pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = psString("pkey",true);

// IS Unique     
    pkey.put("uq",true);                    
 
    ps.add(pkey);
 
//capacity
    doFieldSort("capacity","capacity","work_space");               

//Create property capacity       
    final JsonObject capacity = psLong("capacity",true);
 
    ps.add(capacity);
 
//pname
    doFieldSort("pname","pname","work_space");               

//Create property pname       
    final JsonObject pname = psString("pname",true);
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//type
    doField("type","type","work_space");               

//Create property type       
    final JsonObject type = psString("type",true);

    final JsonArray typeInList = new JsonArray();
                typeInList.add("Caja"); 
typeInList.add("Bodega"); 
typeInList.add("Oficina"); 
typeInList.add("Aula"); 
typeInList.add("Transporte"); 
    type.put("inList",typeInList );                
 
    ps.add(type);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  workSpaceGroup
    doFieldMT0("work_space","workSpaceGroup", "work_space_group");  

    final JsonObject workSpaceGroup =  doMto("workSpaceGroup","workSpaceGroup");        
   
    names.add("workSpaceGroup_pname");
    sortMapFields.put( "workSpaceGroup_pname", "work_space_group_pname");                
    workSpaceGroup.put("pc","pname");          

    mto.add(workSpaceGroup);
        

    //1  work_space_group  -- work_space_group_id
    final ZtatUnitInfoLon zWorkSpaceGroup = new ZtatUnitInfoLon("work_space_group_id", "workSpaceGroup",  "work_space_group","pname","work_space_group");
    mz1.put("zWorkSpaceGroup", zWorkSpaceGroup);    

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        

//(2)  base   base  
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject baseFromWorkSpaceGroup =   doMto2("base","base","workSpaceGroup");        
   
    names.add("base_pname");           
    sortMapFields.put( "base_pname", "base_pname");  
    baseFromWorkSpaceGroup.put("pc","pname");    
         
    mto2.add(baseFromWorkSpaceGroup);        

    final ZtatUnitInfoLon2 zBaseFromWorkSpaceGroup = new ZtatUnitInfoLon2(zWorkSpaceGroup, "base_id", "base",  "base","pname","base");
    mz2.put("zBaseFromWorkSpaceGroup",zBaseFromWorkSpaceGroup);

    dcModel.put("mto2",mto2);    
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"appointments","appointment"); 
                

        applyOtm(otm,"stockRack","stockRack"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"stockRackProducts","stockRackProduct","stockRack",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

/** OTM 3  **/
        final JsonArray otm3 = new JsonArray();

        applyOtm3(otm3,"InvoiceLineIns","invoiceLineIn","stockRackProducts",null,null,null); 
        

        applyOtm3(otm3,"InvoiceLineOuts","invoiceLineOut","stockRackProducts",null,null,null); 
        

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
        jsa.add(r.getLong("work_space_id") );       
        jsa.add(r.getString("work_space_pkey") );       
        jsa.add(r.getLong("work_space_capacity") );       
        jsa.add(r.getString("work_space_pname") );       
        jsa.add(r.getString("work_space_type") );
    jsa.add(r.getLong("work_space_group_id"));
    jsa.add(r.getString("work_space_group_pkey"));   
    
        
    jsa.add(r.getString("work_space_group_pname"));
    jsa.add(r.getLong("base_id"));
    jsa.add(r.getString("base_pkey"));
    

    jsa.add(r.getString("base_pname"));
    
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
        m.put("workSpace_id",LONG);
    }        
//pkey    
    m.put("workSpace_pkey",STRING);              
//capacity    
    m.put("workSpace_capacity",LONG);              
//pname    
    m.put("workSpace_pname",STRING);              
//type    
    m.put("workSpace_type",STRING);          
    if(level<1){
        return m;    
    }       
// workSpaceGroup   workSpaceGroup
    if(withIds){
        m.put("workSpaceGroup_id",LONG);                       
    }
    m.put("workSpaceGroup_pkey",STRING);     
    m.put("workSpaceGroup_pname",STRING);  
//[2] base --   base
    if(withIds){
        m.put("base_id",LONG);              
    }              
    m.put("base_pkey",STRING);  
        
    m.put("base_pname",STRING);  
    
    return m;
    
    }
    
    private int fillXRow0(final Row r, final XSSFRow row,int nc, final boolean withIds){
        
    if(withIds){
        lToCell(r, row,"work_space_id", nc++); 
    }            //pkey       
            sToCell(r, row,"work_space_pkey", nc++);     //capacity            
            ldToCell(r, row,"work_space_capacity", nc++);     //pname       
            sToCell(r, row,"work_space_pname", nc++);     //type       
            sToCell(r, row,"work_space_type", nc++); 
//workSpaceGroup   work_space_group        
    if(withIds){
        lToCell(r, row,"work_space_group_id", nc++);
    }
    sToCell(r, row,"work_space_group_pkey", nc++);
    sToCell(r, row,"work_space_group_pname", nc++);
// base  base
    if(withIds){
       lToCell(r, row,"base_id", nc++);
    }

    sToCell(r, row,"base_pkey", nc++);

    sToCell(r, row,"base_pname", nc++);
        return nc;
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW+ " WHERE work_space_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final WorkSpace dc0, final Tuple t){
                
    t.addString(dc0.getPkey());        
    t.addLong(dc0.getCapacity());        
    t.addString(dc0.getPname());        
    t.addString(dc0.getType());   
    if(dc0.getWorkSpaceGroup()!=null){
       t.addLong(dc0.getWorkSpaceGroup().getId());
    }
    }

    @Override
    public void fillTupleUpdate(final WorkSpace dc0, final Tuple t){
        
    t.addLong(dc0.getCapacity());
    t.addString(dc0.getPname());
    t.addString(dc0.getType());   
//      if(dc0.getWorkSpaceGroup()!=null){
//            t.addLong(dc0.getWorkSpaceGroup().getId());
//      }
    t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTLong("capacity", obj, t);

    fTString("pname", obj, t);

    fTString("type", obj, t);

    fTLong("workSpaceGroup_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> workSpaceGroup = mapParents.get("workSpaceGroup");
            final Long workSpaceGroup_id = workSpaceGroup.get((String)obj.get("workSpaceGroup_pkey"));
            obj.put("workSpaceGroup_id", workSpaceGroup_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);
    fTLong("capacity", js, t);
    fTString("pname", js, t);
    fTString("type", js, t);     
    fTLong("workSpaceGroup_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTLong("capacity", js, t);
fTString("pname", js, t);
fTString("type", js, t);

            //     fTLong("workSpaceGroup_id",js,t);
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
    public WorkSpace doFrom(final Row r){
        final WorkSpace workSpace = new WorkSpace();
         workSpace.setId(r.getLong("work_space_id"));         
                workSpace.setPkey(  r.getString("work_space_pkey"));                       
                workSpace.setCapacity(  r.getLong("work_space_capacity"));                       
                workSpace.setPname(  r.getString("work_space_pname"));                       
                workSpace.setType(  r.getString("work_space_type"));              
        final WorkSpaceGroup workSpaceGroup = new WorkSpaceGroup();
        workSpaceGroup.setId(r.getLong("work_space_group_id"));
        workSpaceGroup.setPkey(r.getString("work_space_group_pkey"));
        
        workSpaceGroup.setPname(r.getString("work_space_group_pname"));
        workSpace.setWorkSpaceGroup(workSpaceGroup);
        
        final Base base = new Base();
        base.setId(r.getLong("base_id"));
        base.setPkey(r.getString("base_pkey"));
        base.setPname(r.getString("base_pname"));
 
        workSpaceGroup.setBase(base);   
        return workSpace;
    }
    
    @Override
    public WorkSpace doFromJson(final JsonObject js){
        WorkSpace workSpace = new WorkSpace();
        workSpace.setId(js.getLong("id"));
        
                
                workSpace.setPkey(js.getString("pkey"));        
                workSpace.setCapacity(js.getLong("capacity"));        
                workSpace.setPname(js.getString("pname"));        
                workSpace.setType(js.getString("type"));        
            workSpace.setId(js.getLong("workSpaceGroup_id"));
        return workSpace;
    }

    @Override
    public JsonObject toJson(final WorkSpace o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("capacity",  o.getCapacity() );
        jso.put("pname",  o.getPname() );
        jso.put("type",  o.getType() );

            final WorkSpaceGroup workSpaceGroup = o.getWorkSpaceGroup();
            if(workSpaceGroup!=null){
                jso.put("workSpaceGroup_id", workSpaceGroup.getId());
                jso.put("workSpaceGroup_pkey", workSpaceGroup.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "work_space_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "work_space_pkey");
    slcb.doEqInPSimple( "pkey", "work_space_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "work_space_pname");
    slcb.doEqInPSimple( "pname", "work_space_pname");               
    slcb.doGEPSimpleLong( "capacity", "work_space_capacity");
    slcb.doLPSimpleLong( "capacity", "work_space_capacity");                
    slcb.doEqInPSimple( "type", "work_space_type");                    
        
        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");  
//WorkSpaceGroup 1        --
        slcb.doIlPSimple2( "workSpaceGroup_pname", "work_space_group_pname");    
        
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
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"work_space");
        sz0.addField("COUNT(work_space.id) as c_work_space_id").addName("count");
        
    sz0.addField("sum(work_space.capacity) as sum_work_space_capacity").addName("sum_capacity"); 
        
//level 1
             
    sz0.applyG1(mz1.get("zWorkSpaceGroup"))   ;      
//level 2    
    sz0.applyG2(mz2.get("zBaseFromWorkSpaceGroup"));                           
//level 3    
        return sz0;
    }
}
    
        