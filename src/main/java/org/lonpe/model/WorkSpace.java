
package org.lonpe.model;            

            
import java.util.Set;
         

public class WorkSpace extends AbstractDcLon implements IDcLon{

    public WorkSpace(){
        super();
    }

    
    private Long capacity;

    /**
     *
     * @return capacity
     */    
    public Long getCapacity(){
        return this.capacity;
    }

    /**
     *
     * @param capacity
     */    
    public void setCapacity(Long capacity){
        this.capacity = capacity;
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

    private String type;

    /**
     *
     * @return type
     */    
    public String getType(){
        return this.type;
    }

    /**
     *
     * @param type
     */    
    public void setType(String type){
        this.type = type;
    }           

    
    private WorkSpaceGroup workSpaceGroup;

    /**
     *
     * @return workSpaceGroup
     */  
    public WorkSpaceGroup getWorkSpaceGroup(){
        return this.workSpaceGroup;
    }

    /**
     *
     * @param workSpaceGroup
     */       
    public void setWorkSpaceGroup(WorkSpaceGroup workSpaceGroup){
        this.workSpaceGroup = workSpaceGroup;
    }

    
    private Set<Appointment> appointments;

    /**
     *
     * @return appointments
     */     
    public Set<Appointment> getAppointments(){
        return this.appointments;
    }
    
    /**
     *
     * @param appointments
     */       
    public void setAppointments(Set<Appointment> appointments){
        this.appointments = appointments;
    }
 

    private Set<StockRack> stockRack;

    /**
     *
     * @return stockRack
     */     
    public Set<StockRack> getStockRack(){
        return this.stockRack;
    }
    
    /**
     *
     * @param stockRack
     */       
    public void setStockRack(Set<StockRack> stockRack){
        this.stockRack = stockRack;
    }
 
      
}
        