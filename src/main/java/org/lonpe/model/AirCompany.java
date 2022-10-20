
package org.lonpe.model;            

            
import java.util.Set;
         

public class AirCompany extends AbstractDcLon implements IDcLon{

    public AirCompany(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<Plane> planes;

    public Set<Plane> getPlanes(){
        return this.planes;
    }
    
    public void setPlanes(Set<Plane> planes){
        this.planes = planes;
    }
 
      
}
        