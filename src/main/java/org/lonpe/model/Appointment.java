
package org.lonpe.model;            

            

         

public class Appointment extends AbstractDcLon implements IDcLon{

    public Appointment(){
    }

    
    private Integer endHour;

    public Integer getEndHour(){
        return this.endHour;
    }

    public void setEndHour(Integer endHour){
        this.endHour = endHour;
    }        
    

    private Integer endMinute;

    public Integer getEndMinute(){
        return this.endMinute;
    }

    public void setEndMinute(Integer endMinute){
        this.endMinute = endMinute;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private Integer startHour;

    public Integer getStartHour(){
        return this.startHour;
    }

    public void setStartHour(Integer startHour){
        this.startHour = startHour;
    }        
    

    private Integer startMinute;

    public Integer getStartMinute(){
        return this.startMinute;
    }

    public void setStartMinute(Integer startMinute){
        this.startMinute = startMinute;
    }        
    

    private Integer weekDay;

    public Integer getWeekDay(){
        return this.weekDay;
    }

    public void setWeekDay(Integer weekDay){
        this.weekDay = weekDay;
    }        
    

    
    private ContractOut contract;

    public ContractOut getContract(){
        return this.contract;
    }
    
    public void setContract(ContractOut contract){
        this.contract = contract;
    }
 

    private WorkSpace workSpace;

    public WorkSpace getWorkSpace(){
        return this.workSpace;
    }
    
    public void setWorkSpace(WorkSpace workSpace){
        this.workSpace = workSpace;
    }
 

    private DepartamentJobInstance departamentJobInstance;

    public DepartamentJobInstance getDepartamentJobInstance(){
        return this.departamentJobInstance;
    }
    
    public void setDepartamentJobInstance(DepartamentJobInstance departamentJobInstance){
        this.departamentJobInstance = departamentJobInstance;
    }
 

    
      
}
        