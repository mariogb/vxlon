
package org.lonpe.model;            

            

         

public class ProgramJob extends AbstractDcLon implements IDcLon{

    public ProgramJob(){
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
    

    
    private Program program;

    public Program getProgram(){
        return this.program;
    }
    
    public void setProgram(Program program){
        this.program = program;
    }
 

    private DepartamentJob departamentJob;

    public DepartamentJob getDepartamentJob(){
        return this.departamentJob;
    }
    
    public void setDepartamentJob(DepartamentJob departamentJob){
        this.departamentJob = departamentJob;
    }
 

    
      
}
        