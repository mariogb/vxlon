
package org.lonpe.model;            

            
import java.util.Set;
         

public class ThirdPerson extends AbstractDcLon implements IDcLon{

    public ThirdPerson(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private String rfc;

    public String getRfc(){
        return this.rfc;
    }

    public void setRfc(String rfc){
        this.rfc = rfc;
    }        
    

    private String tipo;

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }        
    

    

    
    private Set<ContractIn> contractIns;

    public Set<ContractIn> getContractIns(){
        return this.contractIns;
    }
    
    public void setContractIns(Set<ContractIn> contractIns){
        this.contractIns = contractIns;
    }
 


    private Set<ContractOut> contractOuts;

    public Set<ContractOut> getContractOuts(){
        return this.contractOuts;
    }
    
    public void setContractOuts(Set<ContractOut> contractOuts){
        this.contractOuts = contractOuts;
    }
 


    private Set<UserThirdPerson> userThirdPersons;

    public Set<UserThirdPerson> getUserThirdPersons(){
        return this.userThirdPersons;
    }
    
    public void setUserThirdPersons(Set<UserThirdPerson> userThirdPersons){
        this.userThirdPersons = userThirdPersons;
    }
 
      
}
        