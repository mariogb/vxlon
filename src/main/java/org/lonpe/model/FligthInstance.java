
package org.lonpe.model;            

            
import java.time.LocalDateTime;
         

public class FligthInstance extends AbstractDcLon implements IDcLon{

    public FligthInstance(){
    }

    
    private LocalDateTime inLocalDateTime;

    public LocalDateTime getInLocalDateTime(){
        return this.inLocalDateTime;
    }

    public void setInLocalDateTime(LocalDateTime inLocalDateTime){
        this.inLocalDateTime = inLocalDateTime;
    }        
    

    private LocalDateTime outLocalDateTime;

    public LocalDateTime getOutLocalDateTime(){
        return this.outLocalDateTime;
    }

    public void setOutLocalDateTime(LocalDateTime outLocalDateTime){
        this.outLocalDateTime = outLocalDateTime;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private Fligth theFligth;

    public Fligth getTheFligth(){
        return this.theFligth;
    }
    
    public void setTheFligth(Fligth theFligth){
        this.theFligth = theFligth;
    }
 

    
      
}
        