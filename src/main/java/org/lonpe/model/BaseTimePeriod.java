
package org.lonpe.model;            

            
import java.util.Set;
         

public class BaseTimePeriod extends AbstractDcLon implements IDcLon{

    public BaseTimePeriod(){
        super();
    }

    

    
    private Base base;

    /**
     *
     * @return base
     */  
    public Base getBase(){
        return this.base;
    }

    /**
     *
     * @param base
     */       
    public void setBase(Base base){
        this.base = base;
    }

    private TimePeriod timePeriod;

    /**
     *
     * @return timePeriod
     */  
    public TimePeriod getTimePeriod(){
        return this.timePeriod;
    }

    /**
     *
     * @param timePeriod
     */       
    public void setTimePeriod(TimePeriod timePeriod){
        this.timePeriod = timePeriod;
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
 

    private Set<ProgramBaseTimePeriod> programBaseTimePeriods;

    /**
     *
     * @return programBaseTimePeriods
     */     
    public Set<ProgramBaseTimePeriod> getProgramBaseTimePeriods(){
        return this.programBaseTimePeriods;
    }
    
    /**
     *
     * @param programBaseTimePeriods
     */       
    public void setProgramBaseTimePeriods(Set<ProgramBaseTimePeriod> programBaseTimePeriods){
        this.programBaseTimePeriods = programBaseTimePeriods;
    }
 
      
}
        