
package org.lonpe.model;            

            
import java.time.LocalDateTime;
         

public class FligthInstance extends AbstractDcLon implements IDcLon{

    public FligthInstance(){
        super();
    }

    
    private LocalDateTime inLocalDateTime;

    /**
     *
     * @return inLocalDateTime
     */    
    public LocalDateTime getInLocalDateTime(){
        return this.inLocalDateTime;
    }

    /**
     *
     * @param inLocalDateTime
     */    
    public void setInLocalDateTime(LocalDateTime inLocalDateTime){
        this.inLocalDateTime = inLocalDateTime;
    }           

    private LocalDateTime outLocalDateTime;

    /**
     *
     * @return outLocalDateTime
     */    
    public LocalDateTime getOutLocalDateTime(){
        return this.outLocalDateTime;
    }

    /**
     *
     * @param outLocalDateTime
     */    
    public void setOutLocalDateTime(LocalDateTime outLocalDateTime){
        this.outLocalDateTime = outLocalDateTime;
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

    
    private Fligth theFligth;

    /**
     *
     * @return theFligth
     */  
    public Fligth getTheFligth(){
        return this.theFligth;
    }

    /**
     *
     * @param theFligth
     */       
    public void setTheFligth(Fligth theFligth){
        this.theFligth = theFligth;
    }

    
      
}
        