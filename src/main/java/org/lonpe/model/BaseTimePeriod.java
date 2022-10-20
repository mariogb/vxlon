
package org.lonpe.model;            

            
import java.util.Set;
         

public class BaseTimePeriod extends AbstractDcLon implements IDcLon{

    public BaseTimePeriod(){
    }

    

    
    private Base base;

    public Base getBase(){
        return this.base;
    }
    
    public void setBase(Base base){
        this.base = base;
    }
 

    private TimePeriod timePeriod;

    public TimePeriod getTimePeriod(){
        return this.timePeriod;
    }
    
    public void setTimePeriod(TimePeriod timePeriod){
        this.timePeriod = timePeriod;
    }
 

    
    private Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods;

    public Set<DepartamentBaseTimePeriod> getDepartamentBaseTimePeriods(){
        return this.departamentBaseTimePeriods;
    }
    
    public void setDepartamentBaseTimePeriods(Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods){
        this.departamentBaseTimePeriods = departamentBaseTimePeriods;
    }
 


    private Set<ProgramBaseTimePeriod> programBaseTimePeriods;

    public Set<ProgramBaseTimePeriod> getProgramBaseTimePeriods(){
        return this.programBaseTimePeriods;
    }
    
    public void setProgramBaseTimePeriods(Set<ProgramBaseTimePeriod> programBaseTimePeriods){
        this.programBaseTimePeriods = programBaseTimePeriods;
    }
 
      
}
        