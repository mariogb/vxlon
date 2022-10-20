
package org.lonpe.model;            

            
import java.util.Set;
         

public class DepartamentJob extends AbstractDcLon implements IDcLon{

    public DepartamentJob(){
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
    

    
    private Departament departament;

    public Departament getDepartament(){
        return this.departament;
    }
    
    public void setDepartament(Departament departament){
        this.departament = departament;
    }
 

    
    private Set<DepartamentJobInstance> departamentJobInstances;

    public Set<DepartamentJobInstance> getDepartamentJobInstances(){
        return this.departamentJobInstances;
    }
    
    public void setDepartamentJobInstances(Set<DepartamentJobInstance> departamentJobInstances){
        this.departamentJobInstances = departamentJobInstances;
    }
 


    private Set<ProgramJob> programJobs;

    public Set<ProgramJob> getProgramJobs(){
        return this.programJobs;
    }
    
    public void setProgramJobs(Set<ProgramJob> programJobs){
        this.programJobs = programJobs;
    }
 
      
}
        