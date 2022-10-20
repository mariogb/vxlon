
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
 *   StockRackService 
 * 
 */
   
  
public class StockRackService extends AbstractServiceLon<StockRack>{

    private static final String SQLINSERT ="INSERT INTO stock_rack(pkey,fast_key,pname,work_space_id) VALUES ($1,$2,$3,$4) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE stock_rack SET fast_key = $1,pname = $2 WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE stock_rack SET fast_key = $1,pname = $2 WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM stock_rack_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM stock_rack_view";
    private static final String SQLKEYS = "SELECT stock_rack_pkey FROM stock_rack_view";
    private static final String SQLIDBYPKEY = "SELECT id from stock_rack WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from stock_rack WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM stock_rack WHERE id = $1 returning id";
    private static final String TABLENAME ="stock_rack";
    
//1
    private static final ZtatUnitInfoLon zWorkSpace;

//2
    private static final ZtatUnitInfoLon zWorkSpaceGroup;

//3
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
    
    private static String sql00 = "SELECT stock_rack.id as stock_rack_id,
stock_rack.pkey as stock_rack_pkey,
stock_rack.fast_key as stock_rack_fast_key,
stock_rack.pname as stock_rack_pname,
work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  stock_rack,
  work_space as work_space,
  work_space_group as work_space_group,
  base as base  
 WHERE 
 stock_rack.work_space_id = work_space.id
 AND work_space.work_space_group_id = work_space_group.id
 AND work_space_group.base_id = base.id; 
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

        
    dcModel.put("dc", "stockRack");

//ID 
    names.add("id");

    sortMapFields.put("id","stock_rack_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("stock_rack.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("stock_rack.stock_rack_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "stock_rack_pkey");                   
 
    ps.add(pkey);
 
//fastKey
    names.add("fastKey");
    insertMapFields.put("stock_rack.fast_key","fastKey");  

//Create property fastKey       
    final JsonObject fastKey = ps00a("fastKey", "String",false);

// hasIndex 
    fastKey.put("withIndex",true);  
 
    ps.add(fastKey);
 
//pname
    names.add("pname");
    insertMapFields.put("stock_rack.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "stock_rack_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  workSpace --------------------
    names.add("workSpace_id");      
    insertMapFields.put("stock_rack.work_space_id","workSpace_id");    
       
    names.add("workSpace_pkey");        
    sortMapFields.put( "workSpace_pkey", "work_space_pkey");        

    final JsonObject workSpace =  doMto("workSpace","workSpace");        
   
    names.add("workSpace_pname");
    sortMapFields.put( "workSpace_pname", "work_space_pname");         

    workSpace.put("pc","pname");          

    mto.add(workSpace);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   workSpaceGroup 
        
    names.add("workSpaceGroup_id");          
    names.add("workSpaceGroup_pkey");

    final JsonObject workSpaceGroup =   doMto2("workSpaceGroup","workSpaceGroup","workSpace");        
   
    names.add("workSpaceGroup_pname");  
    workSpaceGroup.put("pc","pname");             
   
    sortMapFields.put( "workSpaceGroup_pname", "work_space_group_pname");            
         
    mto2.add(workSpaceGroup);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   base 
        
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject base =   doMto2("base","base","workSpaceGroup");        
   
    names.add("base_pname");  
    base.put("pc","pname");             
   
    sortMapFields.put( "base_pname", "base_pname");            
         
    mto3.add(base);        

    dcModel.put("mto3",mto3);       
        
        final JsonArray otm = new JsonArray();

        applyOtm(otm,"stockRackProducts","stockRackProduct"); 
                

/** OTM ON MODEL  **/
        dcModel.put("otm",otm);  

/** OTM 2  **/
        final JsonArray otm2 = new JsonArray();

        applyOtm2(otm2,"InvoiceLineIns","invoiceLineIn","stockRackProducts",null,null); 
        

        applyOtm2(otm2,"InvoiceLineOuts","invoiceLineOut","stockRackProducts",null,null); 
        

/** OTM 2  ON MODEL**/
        dcModel.put("otm2",otm2);
        

        
//1  work_space  -- work_space_id
    zWorkSpace = new ZtatUnitInfoLon("work_space_id", "workSpace",  "work_space","pname","work_space");

//2    
    zWorkSpaceGroup = new ZtatUnitInfoLon("work_space_group_id", "workSpaceGroup",  "work_space_group","pname","work_space_group");

//3
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
        jsa.add(r.getLong("stock_rack_id") );
        jsa.add(r.getString("stock_rack_pkey") );
        jsa.add(r.getString("stock_rack_fast_key") );
        jsa.add(r.getString("stock_rack_pname") );
        jsa.add(r.getLong("work_space_id"));
        jsa.add(r.getString("work_space_pkey"));
        jsa.add(r.getString("work_space_pname"));
        jsa.add(r.getLong("work_space_group_id"));
        jsa.add(r.getString("work_space_group_pkey"));
        jsa.add(r.getString("work_space_group_pname"));
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
                m_.put("stockRack_id","Long");
            }
            
    //pkey       
            m_.put("stockRack_pkey","String"); 
            
    //fastKey       
            m_.put("stockRack_fastKey","String"); 
            
    //pname       
            m_.put("stockRack_pname","String"); 
            
if(level<1){
                return m_;    
            }
            
 // workSpace
if(withIds){
            m_.put("workSpace_id","Long");   
                    
            }

        m_.put("workSpace_pkey","String");   
        

            m_.put("workSpace_pname","String");   
            
//[2] workSpaceGroup  

        if(level>1){
            if(withIds){
               m_.put("workSpaceGroup_id","Long");              
            }      
        
        m_.put("workSpaceGroup_pkey","String");  

            m_.put("workSpaceGroup_pname","String");    
 
                      }             
//[3] base  

        if(level>2){
            if(withIds){
               m_.put("base_id","Long");              
            }      
        
        m_.put("base_pkey","String");  

            m_.put("base_pname","String");    
 
                      }             
    
    return m_;
    
    }
    
    private void fillXRow0(final Row r, final XSSFRow row,int nc, boolean withIds){
        if(withIds){
        lToCell(r, row,"stock_rack_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"stock_rack_pkey", nc++); 
    //fastKey       
            sToCell(r, row,"stock_rack_fast_key", nc++); 
    //pname       
            sToCell(r, row,"stock_rack_pname", nc++); 
 // workSpace
if(withIds){
                    lToCell(r, row,"work_space_id", nc++);
                 }
sToCell(r, row,"work_space_pkey", nc++);
sToCell(r, row,"work_space_pname", nc++);
// workSpaceGroup
if(withIds){
            lToCell(r, row,"work_space_group_id", nc++);
        }
sToCell(r, row,"work_space_group_pkey", nc++);
sToCell(r, row,"work_space_group_pname", nc++);
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
        return SQLVIEW+ " WHERE stock_rack_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final StockRack dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());
   
            if(dc0.getWorkSpace()!=null){
               t.addLong(dc0.getWorkSpace().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final StockRack dc0, final Tuple t){
                t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());
   
//      if(dc0.getWorkSpace()!=null){
//            t.addLong(dc0.getWorkSpace().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTString("fastKey", obj, t);

    fTString("pname", obj, t);

    fTLong("workSpace_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> workSpace = mapParents.get("workSpace");
            final Long workSpace_id = workSpace.get((String)obj.get("workSpace_pkey"));
            obj.put("workSpace_id", workSpace_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTString("fastKey", js, t);

    fTString("pname", js, t);
     
    fTLong("workSpace_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTString("fastKey", js, t);
fTString("pname", js, t);

            //     fTLong("workSpace_id",js,t);
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
    public StockRack doFrom(final Row r){
        final StockRack stockRack = new StockRack();
         stockRack.setId(r.getLong("stock_rack_id"));
         
                stockRack.setPkey(  r.getString("stock_rack_pkey"));
         
                stockRack.setFastKey(  r.getString("stock_rack_fast_key"));
         
                stockRack.setPname(  r.getString("stock_rack_pname"));

        final WorkSpace workSpace = new WorkSpace();
        workSpace.setId(r.getLong("work_space_id"));
        workSpace.setPkey(r.getString("work_space_pkey"));
        workSpace.setPname(r.getString("work_space_pname"));
        stockRack.setWorkSpace(workSpace);
        

        final WorkSpaceGroup workSpaceGroup = new WorkSpaceGroup();
        workSpaceGroup.setId(r.getLong("work_space_group_id"));
        workSpaceGroup.setPkey(r.getString("work_space_group_pkey"));
        workSpaceGroup.setPname(r.getString("work_space_group_pname"));
 workSpace.setWorkSpaceGroup(workSpaceGroup);   
        return stockRack;
    }
    
    @Override
    public StockRack doFromJson(final JsonObject js){
        StockRack stockRack = new StockRack();
        stockRack.setId(js.getLong("id"));
        
                stockRack.setPkey(js.getString("pkey"));
        stockRack.setFastKey(js.getString("fastKey"));
        stockRack.setPname(js.getString("pname"));
        stockRack.setId(js.getLong("workSpace_id"));
        return stockRack;
    }

    @Override
    public JsonObject toJson(final StockRack o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("fastKey",  o.getFastKey() );
        jso.put("pname",  o.getPname() );

            final WorkSpace workSpace = o.getWorkSpace();
            if(workSpace!=null){
                jso.put("workSpace_id", workSpace.getId());
                jso.put("workSpace_pkey", workSpace.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "stock_rack_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "stock_rack_pkey");
    slcb.doEqInPSimple( "pkey", "stock_rack_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "stock_rack_pname");
    slcb.doEqInPSimple( "pname", "stock_rack_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "stock_rack_fast_key");
    slcb.doEqInPSimple( "fastKey", "stock_rack_fast_key");                    
        
        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");  
//WorkSpace 2        
        slcb.doIlPSimple2( "workSpace_pname", "work_space_pname");    

        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");//WorkSpaceGroup 1
        slcb.doIlPSimple2( "workSpaceGroup_pname", "work_space_group_pname"); 
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"stock_rack");
        sz0.addField("COUNT(stock_rack.id) as c_stock_rack_id").addName("count");
        
        
//level 1
             
    sz0.applyG1(zWorkSpace);      
    //level 2
    
    sz0.applyG2(zWorkSpace,zWorkSpaceGroup);                           
    //level 3
    
        sz0.applyG3(zWorkSpace,zWorkSpaceGroup,zBase);               
        return sz0;
    }
}
    
        