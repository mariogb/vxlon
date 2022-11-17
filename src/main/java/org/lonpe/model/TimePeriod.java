
package org.lonpe.model;            

            
import java.time.LocalDate;
import java.util.Set;
         

public class TimePeriod extends AbstractDcLon implements IDcLon{

    public TimePeriod(){
        super();
    }

    
    private LocalDate beginDate;

    /**
     *
     * @return beginDate
     */    
    public LocalDate getBeginDate(){
        return this.beginDate;
    }

    /**
     *
     * @param beginDate
     */    
    public void setBeginDate(LocalDate beginDate){
        this.beginDate = beginDate;
    }           

    private LocalDate endDate;

    /**
     *
     * @return endDate
     */    
    public LocalDate getEndDate(){
        return this.endDate;
    }

    /**
     *
     * @param endDate
     */    
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
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

    private String typeLon;

    /**
     *
     * @return typeLon
     */    
    public String getTypeLon(){
        return this.typeLon;
    }

    /**
     *
     * @param typeLon
     */    
    public void setTypeLon(String typeLon){
        this.typeLon = typeLon;
    }           

    

    
    private Set<BaseTimePeriod> baseTimePeriods;

    /**
     *
     * @return baseTimePeriods
     */     
    public Set<BaseTimePeriod> getBaseTimePeriods(){
        return this.baseTimePeriods;
    }
    
    /**
     *
     * @param baseTimePeriods
     */       
    public void setBaseTimePeriods(Set<BaseTimePeriod> baseTimePeriods){
        this.baseTimePeriods = baseTimePeriods;
    }
 
      
}
        