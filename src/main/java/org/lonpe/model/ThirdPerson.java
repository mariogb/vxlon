
package org.lonpe.model;            

            
import java.util.Set;
         

public class ThirdPerson extends AbstractDcLon implements IDcLon{

    public ThirdPerson(){
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

    private String rfc;

    /**
     *
     * @return rfc
     */    
    public String getRfc(){
        return this.rfc;
    }

    /**
     *
     * @param rfc
     */    
    public void setRfc(String rfc){
        this.rfc = rfc;
    }           

    private String tipo;

    /**
     *
     * @return tipo
     */    
    public String getTipo(){
        return this.tipo;
    }

    /**
     *
     * @param tipo
     */    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }           

    

    
    private Set<ContractIn> contractIns;

    /**
     *
     * @return contractIns
     */     
    public Set<ContractIn> getContractIns(){
        return this.contractIns;
    }
    
    /**
     *
     * @param contractIns
     */       
    public void setContractIns(Set<ContractIn> contractIns){
        this.contractIns = contractIns;
    }
 

    private Set<ContractOut> contractOuts;

    /**
     *
     * @return contractOuts
     */     
    public Set<ContractOut> getContractOuts(){
        return this.contractOuts;
    }
    
    /**
     *
     * @param contractOuts
     */       
    public void setContractOuts(Set<ContractOut> contractOuts){
        this.contractOuts = contractOuts;
    }
 

    private Set<UserThirdPerson> userThirdPersons;

    /**
     *
     * @return userThirdPersons
     */     
    public Set<UserThirdPerson> getUserThirdPersons(){
        return this.userThirdPersons;
    }
    
    /**
     *
     * @param userThirdPersons
     */       
    public void setUserThirdPersons(Set<UserThirdPerson> userThirdPersons){
        this.userThirdPersons = userThirdPersons;
    }
 
      
}
        