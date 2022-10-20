
package org.lonpe.model;            

            
import java.time.LocalDate;
import java.util.Set;
         

public class TimePeriod extends AbstractDcLon implements IDcLon{

    public TimePeriod(){
    }

    
    private LocalDate beginDate;

    public LocalDate getBeginDate(){
        return this.beginDate;
    }

    public void setBeginDate(LocalDate beginDate){
        this.beginDate = beginDate;
    }        
    

    private LocalDate endDate;

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private String typeLon;

    public String getTypeLon(){
        return this.typeLon;
    }

    public void setTypeLon(String typeLon){
        this.typeLon = typeLon;
    }        
    

    

    
    private Set<BaseTimePeriod> baseTimePeriods;

    public Set<BaseTimePeriod> getBaseTimePeriods(){
        return this.baseTimePeriods;
    }
    
    public void setBaseTimePeriods(Set<BaseTimePeriod> baseTimePeriods){
        this.baseTimePeriods = baseTimePeriods;
    }
 
      
}
        