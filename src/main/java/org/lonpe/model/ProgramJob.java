
package org.lonpe.model;            

            

         

public class ProgramJob extends AbstractDcLon implements IDcLon{

    public ProgramJob(){
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

    
    private Program program;

    /**
     *
     * @return program
     */  
    public Program getProgram(){
        return this.program;
    }

    /**
     *
     * @param program
     */       
    public void setProgram(Program program){
        this.program = program;
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

    
      
}
        