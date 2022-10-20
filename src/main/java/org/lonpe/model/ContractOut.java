
package org.lonpe.model;            

            
import java.util.Set;
         

public class ContractOut extends AbstractDcLon implements IDcLon{

    public ContractOut(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private DepartamentBaseTimePeriod departamentBaseTimePeriod;

    public DepartamentBaseTimePeriod getDepartamentBaseTimePeriod(){
        return this.departamentBaseTimePeriod;
    }
    
    public void setDepartamentBaseTimePeriod(DepartamentBaseTimePeriod departamentBaseTimePeriod){
        this.departamentBaseTimePeriod = departamentBaseTimePeriod;
    }
 

    private ThirdPerson thirdPerson;

    public ThirdPerson getThirdPerson(){
        return this.thirdPerson;
    }
    
    public void setThirdPerson(ThirdPerson thirdPerson){
        this.thirdPerson = thirdPerson;
    }
 

    
    private Set<Appointment> appointments;

    public Set<Appointment> getAppointments(){
        return this.appointments;
    }
    
    public void setAppointments(Set<Appointment> appointments){
        this.appointments = appointments;
    }
 


    private Set<ComercialDocumentOut> comercialDocuments;

    public Set<ComercialDocumentOut> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    public void setComercialDocuments(Set<ComercialDocumentOut> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        