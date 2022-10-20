
package org.lonpe.model;            

            
import java.util.Set;
         

public class DepartamentJobInstance extends AbstractDcLon implements IDcLon{

    public DepartamentJobInstance(){
    }

    
    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }        
    

    private Integer nhoras;

    public Integer getNhoras(){
        return this.nhoras;
    }

    public void setNhoras(Integer nhoras){
        this.nhoras = nhoras;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private DepartamentJob departamentJob;

    public DepartamentJob getDepartamentJob(){
        return this.departamentJob;
    }
    
    public void setDepartamentJob(DepartamentJob departamentJob){
        this.departamentJob = departamentJob;
    }
 

    private DepartamentBaseTimePeriod departamentBaseTimePeriod;

    public DepartamentBaseTimePeriod getDepartamentBaseTimePeriod(){
        return this.departamentBaseTimePeriod;
    }
    
    public void setDepartamentBaseTimePeriod(DepartamentBaseTimePeriod departamentBaseTimePeriod){
        this.departamentBaseTimePeriod = departamentBaseTimePeriod;
    }
 

    
    private Set<Appointment> appointmens;

    public Set<Appointment> getAppointmens(){
        return this.appointmens;
    }
    
    public void setAppointmens(Set<Appointment> appointmens){
        this.appointmens = appointmens;
    }
 
      
}
        