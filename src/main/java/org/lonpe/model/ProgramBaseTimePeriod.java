
package org.lonpe.model;            

            
import java.util.Set;
         

public class ProgramBaseTimePeriod extends AbstractDcLon implements IDcLon{

    public ProgramBaseTimePeriod(){
    }

    

    
    private BaseTimePeriod baseTimePeriod;

    public BaseTimePeriod getBaseTimePeriod(){
        return this.baseTimePeriod;
    }
    
    public void setBaseTimePeriod(BaseTimePeriod baseTimePeriod){
        this.baseTimePeriod = baseTimePeriod;
    }
 

    private Program program;

    public Program getProgram(){
        return this.program;
    }
    
    public void setProgram(Program program){
        this.program = program;
    }
 

    
    private Set<ContractIn> contracts;

    public Set<ContractIn> getContracts(){
        return this.contracts;
    }
    
    public void setContracts(Set<ContractIn> contracts){
        this.contracts = contracts;
    }
 
      
}
        