
package org.lonpe.model;            

            
import java.util.Set;
         

public class ContractOut extends AbstractDcLon implements IDcLon{

    public ContractOut(){
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

    
    private DepartamentBaseTimePeriod departamentBaseTimePeriod;

    /**
     *
     * @return departamentBaseTimePeriod
     */  
    public DepartamentBaseTimePeriod getDepartamentBaseTimePeriod(){
        return this.departamentBaseTimePeriod;
    }

    /**
     *
     * @param departamentBaseTimePeriod
     */       
    public void setDepartamentBaseTimePeriod(DepartamentBaseTimePeriod departamentBaseTimePeriod){
        this.departamentBaseTimePeriod = departamentBaseTimePeriod;
    }

    private ThirdPerson thirdPerson;

    /**
     *
     * @return thirdPerson
     */  
    public ThirdPerson getThirdPerson(){
        return this.thirdPerson;
    }

    /**
     *
     * @param thirdPerson
     */       
    public void setThirdPerson(ThirdPerson thirdPerson){
        this.thirdPerson = thirdPerson;
    }

    
    private Set<Appointment> appointments;

    /**
     *
     * @return appointments
     */     
    public Set<Appointment> getAppointments(){
        return this.appointments;
    }
    
    /**
     *
     * @param appointments
     */       
    public void setAppointments(Set<Appointment> appointments){
        this.appointments = appointments;
    }
 

    private Set<ComercialDocumentOut> comercialDocuments;

    /**
     *
     * @return comercialDocuments
     */     
    public Set<ComercialDocumentOut> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    /**
     *
     * @param comercialDocuments
     */       
    public void setComercialDocuments(Set<ComercialDocumentOut> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        