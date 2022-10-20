
package org.lonpe.model;            

            
import java.util.Set;
         

public class DepartamentBaseTimePeriod extends AbstractDcLon implements IDcLon{

    public DepartamentBaseTimePeriod(){
    }

    

    
    private BaseTimePeriod baseTimePeriod;

    public BaseTimePeriod getBaseTimePeriod(){
        return this.baseTimePeriod;
    }
    
    public void setBaseTimePeriod(BaseTimePeriod baseTimePeriod){
        this.baseTimePeriod = baseTimePeriod;
    }
 

    private Departament departament;

    public Departament getDepartament(){
        return this.departament;
    }
    
    public void setDepartament(Departament departament){
        this.departament = departament;
    }
 

    
    private Set<ContractOut> contracts;

    public Set<ContractOut> getContracts(){
        return this.contracts;
    }
    
    public void setContracts(Set<ContractOut> contracts){
        this.contracts = contracts;
    }
 


    private Set<DepartamentJobInstance> departamentJobInstances;

    public Set<DepartamentJobInstance> getDepartamentJobInstances(){
        return this.departamentJobInstances;
    }
    
    public void setDepartamentJobInstances(Set<DepartamentJobInstance> departamentJobInstances){
        this.departamentJobInstances = departamentJobInstances;
    }
 
      
}
        