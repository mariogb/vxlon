
package org.lonpe.model;            

            
import java.util.Set;
         

public class Airport extends AbstractDcLon implements IDcLon{

    public Airport(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<Fligth> inCommingFligths;

    public Set<Fligth> getInCommingFligths(){
        return this.inCommingFligths;
    }
    
    public void setInCommingFligths(Set<Fligth> inCommingFligths){
        this.inCommingFligths = inCommingFligths;
    }
 


    private Set<Fligth> outCommingFligths;

    public Set<Fligth> getOutCommingFligths(){
        return this.outCommingFligths;
    }
    
    public void setOutCommingFligths(Set<Fligth> outCommingFligths){
        this.outCommingFligths = outCommingFligths;
    }
 
      
}
        