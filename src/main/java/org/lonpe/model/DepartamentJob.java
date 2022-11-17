
package org.lonpe.model;            

            
import java.util.Set;
         

public class DepartamentJob extends AbstractDcLon implements IDcLon{

    public DepartamentJob(){
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

    
    private Departament departament;

    /**
     *
     * @return departament
     */  
    public Departament getDepartament(){
        return this.departament;
    }

    /**
     *
     * @param departament
     */       
    public void setDepartament(Departament departament){
        this.departament = departament;
    }

    
    private Set<DepartamentJobInstance> departamentJobInstances;

    /**
     *
     * @return departamentJobInstances
     */     
    public Set<DepartamentJobInstance> getDepartamentJobInstances(){
        return this.departamentJobInstances;
    }
    
    /**
     *
     * @param departamentJobInstances
     */       
    public void setDepartamentJobInstances(Set<DepartamentJobInstance> departamentJobInstances){
        this.departamentJobInstances = departamentJobInstances;
    }
 

    private Set<ProgramJob> programJobs;

    /**
     *
     * @return programJobs
     */     
    public Set<ProgramJob> getProgramJobs(){
        return this.programJobs;
    }
    
    /**
     *
     * @param programJobs
     */       
    public void setProgramJobs(Set<ProgramJob> programJobs){
        this.programJobs = programJobs;
    }
 
      
}
        