
package org.lonpe.model;            

            
import java.util.Set;
         

public class Plane extends AbstractDcLon implements IDcLon{

    public Plane(){
        super();
    }

    
    private String plate;

    /**
     *
     * @return plate
     */    
    public String getPlate(){
        return this.plate;
    }

    /**
     *
     * @param plate
     */    
    public void setPlate(String plate){
        this.plate = plate;
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

    private Integer seats;

    /**
     *
     * @return seats
     */    
    public Integer getSeats(){
        return this.seats;
    }

    /**
     *
     * @param seats
     */    
    public void setSeats(Integer seats){
        this.seats = seats;
    }           

    
    private AirCompany laCompania;

    /**
     *
     * @return laCompania
     */  
    public AirCompany getLaCompania(){
        return this.laCompania;
    }

    /**
     *
     * @param laCompania
     */       
    public void setLaCompania(AirCompany laCompania){
        this.laCompania = laCompania;
    }

    
    private Set<Fligth> fligths;

    /**
     *
     * @return fligths
     */     
    public Set<Fligth> getFligths(){
        return this.fligths;
    }
    
    /**
     *
     * @param fligths
     */       
    public void setFligths(Set<Fligth> fligths){
        this.fligths = fligths;
    }
 
      
}
        