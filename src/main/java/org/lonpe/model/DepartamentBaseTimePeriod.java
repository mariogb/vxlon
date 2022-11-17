
package org.lonpe.model;            

            
import java.util.Set;
         

public class DepartamentBaseTimePeriod extends AbstractDcLon implements IDcLon{

    public DepartamentBaseTimePeriod(){
        super();
    }

    

    
    private BaseTimePeriod baseTimePeriod;

    /**
     *
     * @return baseTimePeriod
     */  
    public BaseTimePeriod getBaseTimePeriod(){
        return this.baseTimePeriod;
    }

    /**
     *
     * @param baseTimePeriod
     */       
    public void setBaseTimePeriod(BaseTimePeriod baseTimePeriod){
        this.baseTimePeriod = baseTimePeriod;
    }

    private Departament departament;

    /**
     *
     * @return departament
     */  
    public Departament getDepartament(){
        return this.departament;
    }

    /**
     *
     * @param departament
     */       
    public void setDepartament(Departament departament){
        this.departament = departament;
    }

    
    private Set<ContractOut> contracts;

    /**
     *
     * @return contracts
     */     
    public Set<ContractOut> getContracts(){
        return this.contracts;
    }
    
    /**
     *
     * @param contracts
     */       
    public void setContracts(Set<ContractOut> contracts){
        this.contracts = contracts;
    }
 

    private Set<DepartamentJobInstance> departamentJobInstances;

    /**
     *
     * @return departamentJobInstances
     */     
    public Set<DepartamentJobInstance> getDepartamentJobInstances(){
        return this.departamentJobInstances;
    }
    
    /**
     *
     * @param departamentJobInstances
     */       
    public void setDepartamentJobInstances(Set<DepartamentJobInstance> departamentJobInstances){
        this.departamentJobInstances = departamentJobInstances;
    }
 
      
}
        