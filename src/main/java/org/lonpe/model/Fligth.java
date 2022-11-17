
package org.lonpe.model;            

            
import java.util.Set;
         

public class Fligth extends AbstractDcLon implements IDcLon{

    public Fligth(){
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

    
    private Airport fromAirport;

    /**
     *
     * @return fromAirport
     */  
    public Airport getFromAirport(){
        return this.fromAirport;
    }

    /**
     *
     * @param fromAirport
     */       
    public void setFromAirport(Airport fromAirport){
        this.fromAirport = fromAirport;
    }

    private Airport toAirport;

    /**
     *
     * @return toAirport
     */  
    public Airport getToAirport(){
        return this.toAirport;
    }

    /**
     *
     * @param toAirport
     */       
    public void setToAirport(Airport toAirport){
        this.toAirport = toAirport;
    }

    private Plane plane;

    /**
     *
     * @return plane
     */  
    public Plane getPlane(){
        return this.plane;
    }

    /**
     *
     * @param plane
     */       
    public void setPlane(Plane plane){
        this.plane = plane;
    }

    
    private Set<FligthInstance> fligthInstances;

    /**
     *
     * @return fligthInstances
     */     
    public Set<FligthInstance> getFligthInstances(){
        return this.fligthInstances;
    }
    
    /**
     *
     * @param fligthInstances
     */       
    public void setFligthInstances(Set<FligthInstance> fligthInstances){
        this.fligthInstances = fligthInstances;
    }
 
      
}
        