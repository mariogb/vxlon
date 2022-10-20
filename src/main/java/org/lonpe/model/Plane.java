
package org.lonpe.model;            

            
import java.util.Set;
         

public class Plane extends AbstractDcLon implements IDcLon{

    public Plane(){
    }

    
    private String plate;

    public String getPlate(){
        return this.plate;
    }

    public void setPlate(String plate){
        this.plate = plate;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private Integer seats;

    public Integer getSeats(){
        return this.seats;
    }

    public void setSeats(Integer seats){
        this.seats = seats;
    }        
    

    
    private AirCompany laCompania;

    public AirCompany getLaCompania(){
        return this.laCompania;
    }
    
    public void setLaCompania(AirCompany laCompania){
        this.laCompania = laCompania;
    }
 

    
    private Set<Fligth> fligths;

    public Set<Fligth> getFligths(){
        return this.fligths;
    }
    
    public void setFligths(Set<Fligth> fligths){
        this.fligths = fligths;
    }
 
      
}
        