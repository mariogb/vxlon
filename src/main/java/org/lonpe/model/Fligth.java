
package org.lonpe.model;            

            
import java.util.Set;
         

public class Fligth extends AbstractDcLon implements IDcLon{

    public Fligth(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private Airport fromAirport;

    public Airport getFromAirport(){
        return this.fromAirport;
    }
    
    public void setFromAirport(Airport fromAirport){
        this.fromAirport = fromAirport;
    }
 

    private Airport toAirport;

    public Airport getToAirport(){
        return this.toAirport;
    }
    
    public void setToAirport(Airport toAirport){
        this.toAirport = toAirport;
    }
 

    private Plane plane;

    public Plane getPlane(){
        return this.plane;
    }
    
    public void setPlane(Plane plane){
        this.plane = plane;
    }
 

    
    private Set<FligthInstance> fligthInstances;

    public Set<FligthInstance> getFligthInstances(){
        return this.fligthInstances;
    }
    
    public void setFligthInstances(Set<FligthInstance> fligthInstances){
        this.fligthInstances = fligthInstances;
    }
 
      
}
        