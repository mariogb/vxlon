
package org.lonpe.model;            

            
import java.util.Set;
         

public class WorkSpace extends AbstractDcLon implements IDcLon{

    public WorkSpace(){
    }

    
    private Long capacity;

    public Long getCapacity(){
        return this.capacity;
    }

    public void setCapacity(Long capacity){
        this.capacity = capacity;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private String type;

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }        
    

    
    private WorkSpaceGroup workSpaceGroup;

    public WorkSpaceGroup getWorkSpaceGroup(){
        return this.workSpaceGroup;
    }
    
    public void setWorkSpaceGroup(WorkSpaceGroup workSpaceGroup){
        this.workSpaceGroup = workSpaceGroup;
    }
 

    
    private Set<Appointment> appointments;

    public Set<Appointment> getAppointments(){
        return this.appointments;
    }
    
    public void setAppointments(Set<Appointment> appointments){
        this.appointments = appointments;
    }
 


    private Set<StockRack> stockRack;

    public Set<StockRack> getStockRack(){
        return this.stockRack;
    }
    
    public void setStockRack(Set<StockRack> stockRack){
        this.stockRack = stockRack;
    }
 
      
}
        