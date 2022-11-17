
package org.lonpe.model;            

            
import java.util.Set;
         

public class Departament extends AbstractDcLon implements IDcLon{

    public Departament(){
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

    private String fastKey;

    /**
     *
     * @return fastKey
     */    
    public String getFastKey(){
        return this.fastKey;
    }

    /**
     *
     * @param fastKey
     */    
    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
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

    

    
    private Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods;

    /**
     *
     * @return departamentBaseTimePeriods
     */     
    public Set<DepartamentBaseTimePeriod> getDepartamentBaseTimePeriods(){
        return this.departamentBaseTimePeriods;
    }
    
    /**
     *
     * @param departamentBaseTimePeriods
     */       
    public void setDepartamentBaseTimePeriods(Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods){
        this.departamentBaseTimePeriods = departamentBaseTimePeriods;
    }
 

    private Set<DepartamentUserLon> departamentUserLons;

    /**
     *
     * @return departamentUserLons
     */     
    public Set<DepartamentUserLon> getDepartamentUserLons(){
        return this.departamentUserLons;
    }
    
    /**
     *
     * @param departamentUserLons
     */       
    public void setDepartamentUserLons(Set<DepartamentUserLon> departamentUserLons){
        this.departamentUserLons = departamentUserLons;
    }
 

    private Set<DepartamentJob> departamenJobs;

    /**
     *
     * @return departamenJobs
     */     
    public Set<DepartamentJob> getDepartamenJobs(){
        return this.departamenJobs;
    }
    
    /**
     *
     * @param departamenJobs
     */       
    public void setDepartamenJobs(Set<DepartamentJob> departamenJobs){
        this.departamenJobs = departamenJobs;
    }
 
      
}
        