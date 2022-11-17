
package org.lonpe.model;            

            
import java.util.Set;
         

public class ContractIn extends AbstractDcLon implements IDcLon{

    public ContractIn(){
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

    
    private ProgramBaseTimePeriod programBaseTimePeriod;

    /**
     *
     * @return programBaseTimePeriod
     */  
    public ProgramBaseTimePeriod getProgramBaseTimePeriod(){
        return this.programBaseTimePeriod;
    }

    /**
     *
     * @param programBaseTimePeriod
     */       
    public void setProgramBaseTimePeriod(ProgramBaseTimePeriod programBaseTimePeriod){
        this.programBaseTimePeriod = programBaseTimePeriod;
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

    
    private Set<ComercialDocumentIn> comercialDocuments;

    /**
     *
     * @return comercialDocuments
     */     
    public Set<ComercialDocumentIn> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    /**
     *
     * @param comercialDocuments
     */       
    public void setComercialDocuments(Set<ComercialDocumentIn> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        