
package org.lonpe.model;            

            
import java.util.Set;
         

public class AirCompany extends AbstractDcLon implements IDcLon{

    public AirCompany(){
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

    

    
    private Set<Plane> planes;

    /**
     *
     * @return planes
     */     
    public Set<Plane> getPlanes(){
        return this.planes;
    }
    
    /**
     *
     * @param planes
     */       
    public void setPlanes(Set<Plane> planes){
        this.planes = planes;
    }
 
      
}
        