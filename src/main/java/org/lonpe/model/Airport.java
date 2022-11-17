
package org.lonpe.model;            

            
import java.util.Set;
         

public class Airport extends AbstractDcLon implements IDcLon{

    public Airport(){
        super();
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

    

    
    private Set<Fligth> inCommingFligths;

    /**
     *
     * @return inCommingFligths
     */     
    public Set<Fligth> getInCommingFligths(){
        return this.inCommingFligths;
    }
    
    /**
     *
     * @param inCommingFligths
     */       
    public void setInCommingFligths(Set<Fligth> inCommingFligths){
        this.inCommingFligths = inCommingFligths;
    }
 

    private Set<Fligth> outCommingFligths;

    /**
     *
     * @return outCommingFligths
     */     
    public Set<Fligth> getOutCommingFligths(){
        return this.outCommingFligths;
    }
    
    /**
     *
     * @param outCommingFligths
     */       
    public void setOutCommingFligths(Set<Fligth> outCommingFligths){
        this.outCommingFligths = outCommingFligths;
    }
 
      
}
        