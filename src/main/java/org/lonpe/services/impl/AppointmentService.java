
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
 *   AppointmentService 
 * 
 */
   
  
public class AppointmentService extends AbstractServiceLon<Appointment>{

    private static final String SQLINSERT ="INSERT INTO appointment(pkey,end_hour,end_minute,pname,start_hour,start_minute,week_day,contract_id,work_space_id,departament_job_instance_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE appointment SET end_hour = $1,end_minute = $2,pname = $3,start_hour = $4,start_minute = $5,week_day = $6 WHERE id = $13 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE appointment SET end_hour = $1,end_minute = $2,pname = $3,start_hour = $4,start_minute = $5,week_day = $6 WHERE pkey = $13 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM appointment_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM appointment_view";
    private static final String SQLKEYS = "SELECT appointment_pkey FROM appointment_view";
    private static final String SQLIDBYPKEY = "SELECT id from appointment WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from appointment WHERE pkey in ($1)"; 
    private static final String SQLDELETE = "DELETE FROM appointment WHERE id = $1 returning id";
    private static final String TABLENAME ="appointment";
    
//1
    private static final ZtatUnitInfoLon zContract;

//1
    private static final ZtatUnitInfoLon zWorkSpace;

//1
    private static final ZtatUnitInfoLon zDepartamentJobInstance;

//2
    private static final ZtatUnitInfoLon zDepartamentBaseTimePeriod;

//2
    private static final ZtatUnitInfoLon zThirdPerson;

//2
    private static final ZtatUnitInfoLon zWorkSpaceGroup;

//2
    private static final ZtatUnitInfoLon zDepartamentJob;

//3
    private static final ZtatUnitInfoLon zBaseTimePeriod;

//3
    private static final ZtatUnitInfoLon zDepartament;

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
    
    private static String sql00 = "SELECT appointment.id as appointment_id,
appointment.pkey as appointment_pkey,
appointment.end_hour as appointment_end_hour,
appointment.end_minute as appointment_end_minute,
appointment.pname as appointment_pname,
appointment.start_hour as appointment_start_hour,
appointment.start_minute as appointment_start_minute,
appointment.week_day as appointment_week_day,
contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,
work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
departament_job_instance.id as departament_job_instance_id,departament_job_instance.pkey as departament_job_instance_pkey,departament_job_instance.pname as departament_job_instance_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
departament_job.id as departament_job_id, departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  appointment,
  contract_out as contract,
  work_space as work_space,
  departament_job_instance as departament_job_instance,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  work_space_group as work_space_group,
  departament_job as departament_job,
  base_time_period as base_time_period,
  departament as departament,
  base as base  
 WHERE 
 appointment.contract_id = contract.id
 AND appointment.work_space_id = work_space.id
 AND appointment.departament_job_instance_id = departament_job_instance.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND work_space.work_space_group_id = work_space_group.id
 AND departament_job_instance.departament_job_id = departament_job.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND departament_base_time_period.departament_id = departament.id
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

        
    dcModel.put("dc", "appointment");

//ID 
    names.add("id");

    sortMapFields.put("id","appointment_id");

    final JsonArray ps = new JsonArray();   
 
//pkey
    names.add("pkey");
    insertMapFields.put("appointment.pkey","pkey");  

//Create property pkey       
    final JsonObject pkey = ps00a("pkey", "String",true);
   
//Used to map error on index to source property because IS Unique
    insertMapFields.put("appointment.appointment_uidx_pkey","pkey");  

// IS Unique     
    pkey.put("uq",true);                    

    sortMapFields.put("pkey", "appointment_pkey");                   
 
    ps.add(pkey);
 
//endHour
    names.add("endHour");
    insertMapFields.put("appointment.end_hour","endHour");  

//Create property endHour       
    final JsonObject endHour = ps00a("endHour", "Integer",true);

    sortMapFields.put("endHour", "appointment_end_hour");               
 
    endHour.put("min", 0); 
 
    endHour.put("max", 23); 
 
    ps.add(endHour);
 
//endMinute
    names.add("endMinute");
    insertMapFields.put("appointment.end_minute","endMinute");  

//Create property endMinute       
    final JsonObject endMinute = ps00a("endMinute", "Integer",true);

    sortMapFields.put("endMinute", "appointment_end_minute");               
 
    endMinute.put("min", 0); 
 
    endMinute.put("max", 59); 
 
    ps.add(endMinute);
 
//pname
    names.add("pname");
    insertMapFields.put("appointment.pname","pname");  

//Create property pname       
    final JsonObject pname = ps00a("pname", "String",true);

    sortMapFields.put("pname", "appointment_pname");                   
  
//PC
    dcModel.put("pc","pname");  
 
    ps.add(pname);
 
//startHour
    names.add("startHour");
    insertMapFields.put("appointment.start_hour","startHour");  

//Create property startHour       
    final JsonObject startHour = ps00a("startHour", "Integer",true);

    sortMapFields.put("startHour", "appointment_start_hour");               
 
    startHour.put("min", 0); 
 
    startHour.put("max", 23); 
 
    ps.add(startHour);
 
//startMinute
    names.add("startMinute");
    insertMapFields.put("appointment.start_minute","startMinute");  

//Create property startMinute       
    final JsonObject startMinute = ps00a("startMinute", "Integer",true);

    sortMapFields.put("startMinute", "appointment_start_minute");               
 
    startMinute.put("min", 0); 
 
    startMinute.put("max", 59); 
 
    ps.add(startMinute);
 
//weekDay
    names.add("weekDay");
    insertMapFields.put("appointment.week_day","weekDay");  

//Create property weekDay       
    final JsonObject weekDay = ps00a("weekDay", "Integer",true);

    sortMapFields.put("weekDay", "appointment_week_day");               

    final JsonArray weekDayInList = new JsonArray();
                weekDayInList.add("0"); 
weekDayInList.add("1"); 
weekDayInList.add("2"); 
weekDayInList.add("3"); 
weekDayInList.add("4"); 
weekDayInList.add("5"); 
weekDayInList.add("6"); 
    weekDay.put("inList",weekDayInList );                
 
    ps.add(weekDay);

//Add ps to model            
    dcModel.put("ps", ps);        

    final JsonArray mto = new JsonArray();      

//(1)  contract --------------------
    names.add("contract_id");      
    insertMapFields.put("appointment.contract_id","contract_id");    
       
    names.add("contract_pkey");        
    sortMapFields.put( "contract_pkey", "contract_pkey");        

    final JsonObject contract =  doMto("contract","contractOut");        
   
    names.add("contract_pname");
    sortMapFields.put( "contract_pname", "contract_pname");         

    contract.put("pc","pname");          

    mto.add(contract);
        

//(1)  workSpace --------------------
    names.add("workSpace_id");      
    insertMapFields.put("appointment.work_space_id","workSpace_id");    
       
    names.add("workSpace_pkey");        
    sortMapFields.put( "workSpace_pkey", "work_space_pkey");        

    final JsonObject workSpace =  doMto("workSpace","workSpace");        
   
    names.add("workSpace_pname");
    sortMapFields.put( "workSpace_pname", "work_space_pname");         

    workSpace.put("pc","pname");          

    mto.add(workSpace);
        

//(1)  departamentJobInstance --------------------
    names.add("departamentJobInstance_id");      
    insertMapFields.put("appointment.departament_job_instance_id","departamentJobInstance_id");    
       
    names.add("departamentJobInstance_pkey");        
    sortMapFields.put( "departamentJobInstance_pkey", "departament_job_instance_pkey");        

    final JsonObject departamentJobInstance =  doMto("departamentJobInstance","departamentJobInstance");        
   
    names.add("departamentJobInstance_pname");
    sortMapFields.put( "departamentJobInstance_pname", "departament_job_instance_pname");         

    departamentJobInstance.put("pc","pname");          

    mto.add(departamentJobInstance);
        

    dcModel.put("mto",mto);     

    final JsonArray mto2 = new JsonArray();        
//(2)   departamentBaseTimePeriod 
        
    names.add("departamentBaseTimePeriod_id");          
    names.add("departamentBaseTimePeriod_pkey");

    final JsonObject departamentBaseTimePeriod =   doMto2("departamentBaseTimePeriod","departamentBaseTimePeriod","contract");        
         
    mto2.add(departamentBaseTimePeriod);        
//(2)   thirdPerson 
        
    names.add("thirdPerson_id");          
    names.add("thirdPerson_pkey");

    final JsonObject thirdPerson =   doMto2("thirdPerson","thirdPerson","contract");        
   
    names.add("thirdPerson_pname");  
    thirdPerson.put("pc","pname");             
   
    sortMapFields.put( "thirdPerson_pname", "third_person_pname");            
         
    mto2.add(thirdPerson);        
//(2)   workSpaceGroup 
        
    names.add("workSpaceGroup_id");          
    names.add("workSpaceGroup_pkey");

    final JsonObject workSpaceGroup =   doMto2("workSpaceGroup","workSpaceGroup","workSpace");        
   
    names.add("workSpaceGroup_pname");  
    workSpaceGroup.put("pc","pname");             
   
    sortMapFields.put( "workSpaceGroup_pname", "work_space_group_pname");            
         
    mto2.add(workSpaceGroup);        
//(2)   departamentJob 
        
    names.add("departamentJob_id");          
    names.add("departamentJob_pkey");

    final JsonObject departamentJob =   doMto2("departamentJob","departamentJob","departamentJobInstance");        
   
    names.add("departamentJob_pname");  
    departamentJob.put("pc","pname");             
   
    sortMapFields.put( "departamentJob_pname", "departament_job_pname");            
         
    mto2.add(departamentJob);        

    dcModel.put("mto2",mto2);    

    final JsonArray mto3 = new JsonArray();           
//(3)   baseTimePeriod 
        
    names.add("baseTimePeriod_id");          
    names.add("baseTimePeriod_pkey");

    final JsonObject baseTimePeriod =   doMto2("baseTimePeriod","baseTimePeriod","departamentBaseTimePeriod");        
         
    mto3.add(baseTimePeriod);        
//(3)   departament 
        
    names.add("departament_id");          
    names.add("departament_pkey");

    final JsonObject departament =   doMto2("departament","departament","departamentBaseTimePeriod");        
   
    names.add("departament_pname");  
    departament.put("pc","pname");             
   
    sortMapFields.put( "departament_pname", "departament_pname");            
         
    mto3.add(departament);        
//(3)   base 
        
    names.add("base_id");          
    names.add("base_pkey");

    final JsonObject base =   doMto2("base","base","workSpaceGroup");        
   
    names.add("base_pname");  
    base.put("pc","pname");             
   
    sortMapFields.put( "base_pname", "base_pname");            
         
    mto3.add(base);        

    dcModel.put("mto3",mto3);       
        

        
//1  contract  -- contract_id
    zContract = new ZtatUnitInfoLon("contract_id", "contract",  "contract_out","pname","contract");

//1  work_space  -- work_space_id
    zWorkSpace = new ZtatUnitInfoLon("work_space_id", "workSpace",  "work_space","pname","work_space");

//1  departament_job_instance  -- departament_job_instance_id
    zDepartamentJobInstance = new ZtatUnitInfoLon("departament_job_instance_id", "departamentJobInstance",  "departament_job_instance","pname","departament_job_instance");

//2    
    zDepartamentBaseTimePeriod = new ZtatUnitInfoLon("departament_base_time_period_id", "departamentBaseTimePeriod",  "departament_base_time_period","null","departament_base_time_period");

//2    
    zThirdPerson = new ZtatUnitInfoLon("third_person_id", "thirdPerson",  "third_person","pname","third_person");

//2    
    zWorkSpaceGroup = new ZtatUnitInfoLon("work_space_group_id", "workSpaceGroup",  "work_space_group","pname","work_space_group");

//2    
    zDepartamentJob = new ZtatUnitInfoLon("departament_job_id", "departamentJob",  "departament_job","pname","departament_job");

//3
    zBaseTimePeriod = new ZtatUnitInfoLon("base_time_period_id", "baseTimePeriod",  "base_time_period","null","base_time_period");

//3
    zDepartament = new ZtatUnitInfoLon("departament_id", "departament",  "departament","pname","departament");

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
        jsa.add(r.getLong("appointment_id") );
        jsa.add(r.getString("appointment_pkey") );
        jsa.add(r.getInteger("appointment_end_hour") );
        jsa.add(r.getInteger("appointment_end_minute") );
        jsa.add(r.getString("appointment_pname") );
        jsa.add(r.getInteger("appointment_start_hour") );
        jsa.add(r.getInteger("appointment_start_minute") );
        jsa.add(r.getInteger("appointment_week_day") );
        jsa.add(r.getLong("contract_id"));
        jsa.add(r.getString("contract_pkey"));
        jsa.add(r.getString("contract_pname"));
        jsa.add(r.getLong("work_space_id"));
        jsa.add(r.getString("work_space_pkey"));
        jsa.add(r.getString("work_space_pname"));
        jsa.add(r.getLong("departament_job_instance_id"));
        jsa.add(r.getString("departament_job_instance_pkey"));
        jsa.add(r.getString("departament_job_instance_pname"));
        jsa.add(r.getLong("departament_base_time_period_id"));
        jsa.add(r.getString("departament_base_time_period_pkey"));
        jsa.add(r.getLong("third_person_id"));
        jsa.add(r.getString("third_person_pkey"));
        jsa.add(r.getString("third_person_pname"));
        jsa.add(r.getLong("work_space_group_id"));
        jsa.add(r.getString("work_space_group_pkey"));
        jsa.add(r.getString("work_space_group_pname"));
        jsa.add(r.getLong("departament_job_id"));
        jsa.add(r.getString("departament_job_pkey"));
        jsa.add(r.getString("departament_job_pname"));
        jsa.add(r.getLong("base_time_period_id"));
        jsa.add(r.getString("base_time_period_pkey"));
        jsa.add(r.getLong("departament_id"));
        jsa.add(r.getString("departament_pkey"));
        jsa.add(r.getString("departament_pname"));
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
                m_.put("appointment_id","Long");
            }
            
    //pkey       
            m_.put("appointment_pkey","String"); 
            
    //endHour       
            m_.put("appointment_endHour","Integer"); 
            
    //endMinute       
            m_.put("appointment_endMinute","Integer"); 
            
    //pname       
            m_.put("appointment_pname","String"); 
            
    //startHour       
            m_.put("appointment_startHour","Integer"); 
            
    //startMinute       
            m_.put("appointment_startMinute","Integer"); 
            
    //weekDay       
            m_.put("appointment_weekDay","Integer"); 
            
if(level<1){
                return m_;    
            }
            
 // contract
if(withIds){
            m_.put("contract_id","Long");   
                    
            }

        m_.put("contract_pkey","String");   
        

            m_.put("contract_pname","String");   
            
 // workSpace
if(withIds){
            m_.put("workSpace_id","Long");   
                    
            }

        m_.put("workSpace_pkey","String");   
        

            m_.put("workSpace_pname","String");   
            
 // departamentJobInstance
if(withIds){
            m_.put("departamentJobInstance_id","Long");   
                    
            }

        m_.put("departamentJobInstance_pkey","String");   
        

            m_.put("departamentJobInstance_pname","String");   
            
//[2] departamentBaseTimePeriod  

        if(level>1){
            if(withIds){
               m_.put("departamentBaseTimePeriod_id","Long");              
            }      
        
        m_.put("departamentBaseTimePeriod_pkey","String");  
 
                      }             
//[2] thirdPerson  

        if(level>1){
            if(withIds){
               m_.put("thirdPerson_id","Long");              
            }      
        
        m_.put("thirdPerson_pkey","String");  

            m_.put("thirdPerson_pname","String");    
 
                      }             
//[2] workSpaceGroup  

        if(level>1){
            if(withIds){
               m_.put("workSpaceGroup_id","Long");              
            }      
        
        m_.put("workSpaceGroup_pkey","String");  

            m_.put("workSpaceGroup_pname","String");    
 
                      }             
//[2] departamentJob  

        if(level>1){
            if(withIds){
               m_.put("departamentJob_id","Long");              
            }      
        
        m_.put("departamentJob_pkey","String");  

            m_.put("departamentJob_pname","String");    
 
                      }             
//[3] baseTimePeriod  

        if(level>2){
            if(withIds){
               m_.put("baseTimePeriod_id","Long");              
            }      
        
        m_.put("baseTimePeriod_pkey","String");  
 
                      }             
//[3] departament  

        if(level>2){
            if(withIds){
               m_.put("departament_id","Long");              
            }      
        
        m_.put("departament_pkey","String");  

            m_.put("departament_pname","String");    
 
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
        lToCell(r, row,"appointment_id", nc++); 
        }
        
    //pkey       
            sToCell(r, row,"appointment_pkey", nc++); 
    //endHour            
            ldToCell(r, row,"appointment_end_hour", nc++); 
    //endMinute            
            ldToCell(r, row,"appointment_end_minute", nc++); 
    //pname       
            sToCell(r, row,"appointment_pname", nc++); 
    //startHour            
            ldToCell(r, row,"appointment_start_hour", nc++); 
    //startMinute            
            ldToCell(r, row,"appointment_start_minute", nc++); 
    //weekDay            
            ldToCell(r, row,"appointment_week_day", nc++); 
 // contract
if(withIds){
                    lToCell(r, row,"contract_id", nc++);
                 }
sToCell(r, row,"contract_pkey", nc++);
sToCell(r, row,"contract_pname", nc++);
 // workSpace
if(withIds){
                    lToCell(r, row,"work_space_id", nc++);
                 }
sToCell(r, row,"work_space_pkey", nc++);
sToCell(r, row,"work_space_pname", nc++);
 // departamentJobInstance
if(withIds){
                    lToCell(r, row,"departament_job_instance_id", nc++);
                 }
sToCell(r, row,"departament_job_instance_pkey", nc++);
sToCell(r, row,"departament_job_instance_pname", nc++);
// departamentBaseTimePeriod
if(withIds){
            lToCell(r, row,"departament_base_time_period_id", nc++);
        }
sToCell(r, row,"departament_base_time_period_pkey", nc++);
// thirdPerson
if(withIds){
            lToCell(r, row,"third_person_id", nc++);
        }
sToCell(r, row,"third_person_pkey", nc++);
sToCell(r, row,"third_person_pname", nc++);
// workSpaceGroup
if(withIds){
            lToCell(r, row,"work_space_group_id", nc++);
        }
sToCell(r, row,"work_space_group_pkey", nc++);
sToCell(r, row,"work_space_group_pname", nc++);
// departamentJob
if(withIds){
            lToCell(r, row,"departament_job_id", nc++);
        }
sToCell(r, row,"departament_job_pkey", nc++);
sToCell(r, row,"departament_job_pname", nc++);
// baseTimePeriod
if(withIds){
            lToCell(r, row,"base_time_period_id", nc++);
        }
sToCell(r, row,"base_time_period_pkey", nc++);
// departament
if(withIds){
            lToCell(r, row,"departament_id", nc++);
        }
sToCell(r, row,"departament_pkey", nc++);
sToCell(r, row,"departament_pname", nc++);
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
        return SQLVIEW+ " WHERE appointment_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final Appointment dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addInteger(dc0.getEndHour());
        t.addInteger(dc0.getEndMinute());
        t.addString(dc0.getPname());
        t.addInteger(dc0.getStartHour());
        t.addInteger(dc0.getStartMinute());
        t.addInteger(dc0.getWeekDay());
   
            if(dc0.getContract()!=null){
               t.addLong(dc0.getContract().getId());
            }
   
            if(dc0.getWorkSpace()!=null){
               t.addLong(dc0.getWorkSpace().getId());
            }
   
            if(dc0.getDepartamentJobInstance()!=null){
               t.addLong(dc0.getDepartamentJobInstance().getId());
            }
    }

    @Override
    public void fillTupleUpdate(final Appointment dc0, final Tuple t){
                t.addInteger(dc0.getEndHour());
        t.addInteger(dc0.getEndMinute());
        t.addString(dc0.getPname());
        t.addInteger(dc0.getStartHour());
        t.addInteger(dc0.getStartMinute());
        t.addInteger(dc0.getWeekDay());
   
//      if(dc0.getContract()!=null){
//            t.addLong(dc0.getContract().getId());
//      }
   
//      if(dc0.getWorkSpace()!=null){
//            t.addLong(dc0.getWorkSpace().getId());
//      }
   
//      if(dc0.getDepartamentJobInstance()!=null){
//            t.addLong(dc0.getDepartamentJobInstance().getId());
//      }

        t.addLong(dc0.getId());
            
    }    

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {
        
    fTString("pkey", obj, t);

    fTInteger("endHour", obj, t);

    fTInteger("endMinute", obj, t);

    fTString("pname", obj, t);

    fTInteger("startHour", obj, t);

    fTInteger("startMinute", obj, t);

    fTInteger("weekDay", obj, t);

    fTLong("contract_id",obj,t);

    fTLong("workSpace_id",obj,t);

    fTLong("departamentJobInstance_id",obj,t);
    }    

    @Override
    public void populateParentsIds(final Map<String, Object> obj,final Map<String,Map<String, Long>> mapParents){
              
            final Map<String, Long> contract = mapParents.get("contract");
            final Long contract_id = contract.get((String)obj.get("contract_pkey"));
            obj.put("contract_id", contract_id);
      
            final Map<String, Long> workSpace = mapParents.get("workSpace");
            final Long workSpace_id = workSpace.get((String)obj.get("workSpace_pkey"));
            obj.put("workSpace_id", workSpace_id);
      
            final Map<String, Long> departamentJobInstance = mapParents.get("departamentJobInstance");
            final Long departamentJobInstance_id = departamentJobInstance.get((String)obj.get("departamentJobInstance_pkey"));
            obj.put("departamentJobInstance_id", departamentJobInstance_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js,final Tuple t){       
        
    fTString("pkey", js, t);

    fTInteger("endHour", js, t);

    fTInteger("endMinute", js, t);

    fTString("pname", js, t);

    fTInteger("startHour", js, t);

    fTInteger("startMinute", js, t);

    fTInteger("weekDay", js, t);
     
    fTLong("contract_id",js,t);
     
    fTLong("workSpace_id",js,t);
     
    fTLong("departamentJobInstance_id",js,t);       
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {
        fTInteger("endHour", js, t);
fTInteger("endMinute", js, t);
fTString("pname", js, t);
fTInteger("startHour", js, t);
fTInteger("startMinute", js, t);
fTInteger("weekDay", js, t);

            //     fTLong("contract_id",js,t);

            //     fTLong("workSpace_id",js,t);

            //     fTLong("departamentJobInstance_id",js,t);
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
    public Appointment doFrom(final Row r){
        final Appointment appointment = new Appointment();
         appointment.setId(r.getLong("appointment_id"));
         
                appointment.setPkey(  r.getString("appointment_pkey"));
         
                appointment.setEndHour(  r.getInteger("appointment_end_hour"));
         
                appointment.setEndMinute(  r.getInteger("appointment_end_minute"));
         
                appointment.setPname(  r.getString("appointment_pname"));
         
                appointment.setStartHour(  r.getInteger("appointment_start_hour"));
         
                appointment.setStartMinute(  r.getInteger("appointment_start_minute"));
         
                appointment.setWeekDay(  r.getInteger("appointment_week_day"));

        final ContractOut contract = new ContractOut();
        contract.setId(r.getLong("contract_id"));
        contract.setPkey(r.getString("contract_pkey"));
        contract.setPname(r.getString("contract_pname"));
        appointment.setContract(contract);
        

        final WorkSpace workSpace = new WorkSpace();
        workSpace.setId(r.getLong("work_space_id"));
        workSpace.setPkey(r.getString("work_space_pkey"));
        workSpace.setPname(r.getString("work_space_pname"));
        appointment.setWorkSpace(workSpace);
        

        final DepartamentJobInstance departamentJobInstance = new DepartamentJobInstance();
        departamentJobInstance.setId(r.getLong("departament_job_instance_id"));
        departamentJobInstance.setPkey(r.getString("departament_job_instance_pkey"));
        departamentJobInstance.setPname(r.getString("departament_job_instance_pname"));
        appointment.setDepartamentJobInstance(departamentJobInstance);
        

        final DepartamentBaseTimePeriod departamentBaseTimePeriod = new DepartamentBaseTimePeriod();
        departamentBaseTimePeriod.setId(r.getLong("departament_base_time_period_id"));
        departamentBaseTimePeriod.setPkey(r.getString("departament_base_time_period_pkey"));
         contract.setDepartamentBaseTimePeriod(departamentBaseTimePeriod); 

        final ThirdPerson thirdPerson = new ThirdPerson();
        thirdPerson.setId(r.getLong("third_person_id"));
        thirdPerson.setPkey(r.getString("third_person_pkey"));
        thirdPerson.setPname(r.getString("third_person_pname"));
 contract.setThirdPerson(thirdPerson); 

        final WorkSpaceGroup workSpaceGroup = new WorkSpaceGroup();
        workSpaceGroup.setId(r.getLong("work_space_group_id"));
        workSpaceGroup.setPkey(r.getString("work_space_group_pkey"));
        workSpaceGroup.setPname(r.getString("work_space_group_pname"));
 workSpace.setWorkSpaceGroup(workSpaceGroup); 

        final DepartamentJob departamentJob = new DepartamentJob();
        departamentJob.setId(r.getLong("departament_job_id"));
        departamentJob.setPkey(r.getString("departament_job_pkey"));
        departamentJob.setPname(r.getString("departament_job_pname"));
 departamentJobInstance.setDepartamentJob(departamentJob);   
        return appointment;
    }
    
    @Override
    public Appointment doFromJson(final JsonObject js){
        Appointment appointment = new Appointment();
        appointment.setId(js.getLong("id"));
        
                appointment.setPkey(js.getString("pkey"));
        appointment.setEndHour(js.getInteger("endHour"));
        appointment.setEndMinute(js.getInteger("endMinute"));
        appointment.setPname(js.getString("pname"));
        appointment.setStartHour(js.getInteger("startHour"));
        appointment.setStartMinute(js.getInteger("startMinute"));
        appointment.setWeekDay(js.getInteger("weekDay"));
        appointment.setId(js.getLong("contract_id"));
        appointment.setId(js.getLong("workSpace_id"));
        appointment.setId(js.getLong("departamentJobInstance_id"));
        return appointment;
    }

    @Override
    public JsonObject toJson(final Appointment o) {        
        final JsonObject jso = new JsonObject();
        jso.put("id",o.getId() );
        jso.put("pkey",  o.getPkey() );
        jso.put("endHour",  o.getEndHour() );
        jso.put("endMinute",  o.getEndMinute() );
        jso.put("pname",  o.getPname() );
        jso.put("startHour",  o.getStartHour() );
        jso.put("startMinute",  o.getStartMinute() );
        jso.put("weekDay",  o.getWeekDay() );

            final ContractOut contract = o.getContract();
            if(contract!=null){
                jso.put("contract_id", contract.getId());
                jso.put("contract_pkey", contract.getPkey());
            }
            

            final WorkSpace workSpace = o.getWorkSpace();
            if(workSpace!=null){
                jso.put("workSpace_id", workSpace.getId());
                jso.put("workSpace_pkey", workSpace.getPkey());
            }
            

            final DepartamentJobInstance departamentJobInstance = o.getDepartamentJobInstance();
            if(departamentJobInstance!=null){
                jso.put("departamentJobInstance_id", departamentJobInstance.getId());
                jso.put("departamentJobInstance_pkey", departamentJobInstance.getPkey());
            }
            
        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple){

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params,tuple);

       //Check Id      
       slcb.doInLongCondition("id", "appointment_id");
        //*---PKEY ---       
    slcb.doIlPSimple2( "pkey", "appointment_pkey");
    slcb.doEqInPSimple( "pkey", "appointment_pkey");
//*---PC ---" + pname
    slcb.doIlPSimple2( "pname", "appointment_pname");
    slcb.doEqInPSimple( "pname", "appointment_pname");               
    slcb.doGEPSimpleInt( "endHour", "appointment_end_hour");
    slcb.doLTPSimpleInt( "endHour", "appointment_end_hour");                   
    slcb.doGEPSimpleInt( "endMinute", "appointment_end_minute");
    slcb.doLTPSimpleInt( "endMinute", "appointment_end_minute");                   
    slcb.doGEPSimpleInt( "startHour", "appointment_start_hour");
    slcb.doLTPSimpleInt( "startHour", "appointment_start_hour");                   
    slcb.doGEPSimpleInt( "startMinute", "appointment_start_minute");
    slcb.doLTPSimpleInt( "startMinute", "appointment_start_minute");                   
    slcb.doGEPSimpleInt( "weekDay", "appointment_week_day");
    slcb.doLTPSimpleInt( "weekDay", "appointment_week_day");                
        
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");  
//ContractOut 1        
        slcb.doIlPSimple2( "contract_pname", "contract_pname");    
        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");  
//WorkSpace 2        
        slcb.doIlPSimple2( "workSpace_pname", "work_space_pname");    
        slcb.doIlPSimple2( "departamentJobInstance_pkey", "departament_job_instance_pkey");
        slcb.doEQPSimple2( "departamentJobInstance_pkey", "departament_job_instance_pkey");
        slcb.doInLongCondition("departamentJobInstance_id", "departament_job_instance_id");  
//DepartamentJobInstance 3        
        slcb.doIlPSimple2( "departamentJobInstance_pname", "departament_job_instance_pname");    

        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");//DepartamentBaseTimePeriod undefined
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id"); 
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id"); 

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");//ThirdPerson 1
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname"); 

        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");//WorkSpaceGroup 1
        slcb.doIlPSimple2( "workSpaceGroup_pname", "work_space_group_pname"); 
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id"); 

        slcb.doIlPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doEQPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doInLongCondition("departamentJob_id", "departament_job_id");//DepartamentJob 4
        slcb.doIlPSimple2( "departamentJob_pname", "departament_job_pname"); 

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }


   
    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params,"appointment");
        sz0.addField("COUNT(appointment.id) as c_appointment_id").addName("count");
        
    sz0.addField("sum(appointment.end_hour) as sum_appointment_end_hour").addName("sum_endHour");
        
    sz0.addField("sum(appointment.end_minute) as sum_appointment_end_minute").addName("sum_endMinute");
        
    sz0.addField("sum(appointment.start_hour) as sum_appointment_start_hour").addName("sum_startHour");
        
    sz0.addField("sum(appointment.start_minute) as sum_appointment_start_minute").addName("sum_startMinute");
        
    sz0.addField("sum(appointment.week_day) as sum_appointment_week_day").addName("sum_weekDay");
        
        
//level 1
             
    sz0.applyG1(zContract);               
    sz0.applyG1(zWorkSpace);               
    sz0.applyG1(zDepartamentJobInstance);      
    //level 2
    
    sz0.applyG2(zContract,zDepartamentBaseTimePeriod);                           
    sz0.applyG2(zContract,zThirdPerson);                           
    sz0.applyG2(zWorkSpace,zWorkSpaceGroup);                           
    sz0.applyG2(zDepartamentJobInstance,zDepartamentJob);                           
    //level 3
    
        sz0.applyG3(zContract,zDepartamentBaseTimePeriod,zBaseTimePeriod);               
        sz0.applyG3(zContract,zDepartamentBaseTimePeriod,zDepartament);               
        sz0.applyG3(zWorkSpace,zWorkSpaceGroup,zBase);               
        return sz0;
    }
}
    
        