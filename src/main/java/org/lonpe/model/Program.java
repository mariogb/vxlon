
package org.lonpe.model;            

            
import java.util.Set;
         

public class Program extends AbstractDcLon implements IDcLon{

    public Program(){
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
    

    

    
    private Set<ProgramBaseTimePeriod> programBaseTimePeriods;

    public Set<ProgramBaseTimePeriod> getProgramBaseTimePeriods(){
        return this.programBaseTimePeriods;
    }
    
    public void setProgramBaseTimePeriods(Set<ProgramBaseTimePeriod> programBaseTimePeriods){
        this.programBaseTimePeriods = programBaseTimePeriods;
    }
 


    private Set<ProgramUserLon> programUserLons;

    public Set<ProgramUserLon> getProgramUserLons(){
        return this.programUserLons;
    }
    
    public void setProgramUserLons(Set<ProgramUserLon> programUserLons){
        this.programUserLons = programUserLons;
    }
 
      
}
        