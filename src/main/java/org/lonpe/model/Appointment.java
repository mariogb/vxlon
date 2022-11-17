
package org.lonpe.model;            

            

         

public class Appointment extends AbstractDcLon implements IDcLon{

    public Appointment(){
        super();
    }

    
    private Integer endHour;

    /**
     *
     * @return endHour
     */    
    public Integer getEndHour(){
        return this.endHour;
    }

    /**
     *
     * @param endHour
     */    
    public void setEndHour(Integer endHour){
        this.endHour = endHour;
    }           

    private Integer endMinute;

    /**
     *
     * @return endMinute
     */    
    public Integer getEndMinute(){
        return this.endMinute;
    }

    /**
     *
     * @param endMinute
     */    
    public void setEndMinute(Integer endMinute){
        this.endMinute = endMinute;
    }           

    private String pname;

    /**
     *
     * @return pname
     */    
    public String getPname(){
        return this.pname;
    }

    /**
     *
     * @param pname
     */    
    public void setPname(String pname){
        this.pname = pname;
    }           

    private Integer startHour;

    /**
     *
     * @return startHour
     */    
    public Integer getStartHour(){
        return this.startHour;
    }

    /**
     *
     * @param startHour
     */    
    public void setStartHour(Integer startHour){
        this.startHour = startHour;
    }           

    private Integer startMinute;

    /**
     *
     * @return startMinute
     */    
    public Integer getStartMinute(){
        return this.startMinute;
    }

    /**
     *
     * @param startMinute
     */    
    public void setStartMinute(Integer startMinute){
        this.startMinute = startMinute;
    }           

    private Integer weekDay;

    /**
     *
     * @return weekDay
     */    
    public Integer getWeekDay(){
        return this.weekDay;
    }

    /**
     *
     * @param weekDay
     */    
    public void setWeekDay(Integer weekDay){
        this.weekDay = weekDay;
    }           

    
    private ContractOut contract;

    /**
     *
     * @return contract
     */  
    public ContractOut getContract(){
        return this.contract;
    }

    /**
     *
     * @param contract
     */       
    public void setContract(ContractOut contract){
        this.contract = contract;
    }

    private WorkSpace workSpace;

    /**
     *
     * @return workSpace
     */  
    public WorkSpace getWorkSpace(){
        return this.workSpace;
    }

    /**
     *
     * @param workSpace
     */       
    public void setWorkSpace(WorkSpace workSpace){
        this.workSpace = workSpace;
    }

    private DepartamentJobInstance departamentJobInstance;

    /**
     *
     * @return departamentJobInstance
     */  
    public DepartamentJobInstance getDepartamentJobInstance(){
        return this.departamentJobInstance;
    }

    /**
     *
     * @param departamentJobInstance
     */       
    public void setDepartamentJobInstance(DepartamentJobInstance departamentJobInstance){
        this.departamentJobInstance = departamentJobInstance;
    }

    
      
}
        