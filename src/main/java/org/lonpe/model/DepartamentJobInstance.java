
package org.lonpe.model;            

            
import java.util.Set;
         

public class DepartamentJobInstance extends AbstractDcLon implements IDcLon{

    public DepartamentJobInstance(){
        super();
    }

    
    private String description;

    /**
     *
     * @return description
     */    
    public String getDescription(){
        return this.description;
    }

    /**
     *
     * @param description
     */    
    public void setDescription(String description){
        this.description = description;
    }           

    private Integer nhoras;

    /**
     *
     * @return nhoras
     */    
    public Integer getNhoras(){
        return this.nhoras;
    }

    /**
     *
     * @param nhoras
     */    
    public void setNhoras(Integer nhoras){
        this.nhoras = nhoras;
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

    
    private DepartamentJob departamentJob;

    /**
     *
     * @return departamentJob
     */  
    public DepartamentJob getDepartamentJob(){
        return this.departamentJob;
    }

    /**
     *
     * @param departamentJob
     */       
    public void setDepartamentJob(DepartamentJob departamentJob){
        this.departamentJob = departamentJob;
    }

    private DepartamentBaseTimePeriod departamentBaseTimePeriod;

    /**
     *
     * @return departamentBaseTimePeriod
     */  
    public DepartamentBaseTimePeriod getDepartamentBaseTimePeriod(){
        return this.departamentBaseTimePeriod;
    }

    /**
     *
     * @param departamentBaseTimePeriod
     */       
    public void setDepartamentBaseTimePeriod(DepartamentBaseTimePeriod departamentBaseTimePeriod){
        this.departamentBaseTimePeriod = departamentBaseTimePeriod;
    }

    
    private Set<Appointment> appointmens;

    /**
     *
     * @return appointmens
     */     
    public Set<Appointment> getAppointmens(){
        return this.appointmens;
    }
    
    /**
     *
     * @param appointmens
     */       
    public void setAppointmens(Set<Appointment> appointmens){
        this.appointmens = appointmens;
    }
 
      
}
        