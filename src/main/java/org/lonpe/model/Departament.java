
package org.lonpe.model;            

            
import java.util.Set;
         

public class Departament extends AbstractDcLon implements IDcLon{

    public Departament(){
    }

    
    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }        
    

    private String fastKey;

    public String getFastKey(){
        return this.fastKey;
    }

    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods;

    public Set<DepartamentBaseTimePeriod> getDepartamentBaseTimePeriods(){
        return this.departamentBaseTimePeriods;
    }
    
    public void setDepartamentBaseTimePeriods(Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods){
        this.departamentBaseTimePeriods = departamentBaseTimePeriods;
    }
 


    private Set<DepartamentUserLon> departamentUserLons;

    public Set<DepartamentUserLon> getDepartamentUserLons(){
        return this.departamentUserLons;
    }
    
    public void setDepartamentUserLons(Set<DepartamentUserLon> departamentUserLons){
        this.departamentUserLons = departamentUserLons;
    }
 


    private Set<DepartamentJob> departamenJobs;

    public Set<DepartamentJob> getDepartamenJobs(){
        return this.departamenJobs;
    }
    
    public void setDepartamenJobs(Set<DepartamentJob> departamenJobs){
        this.departamenJobs = departamenJobs;
    }
 
      
}
        