
package org.lonpe.model;            

            
import java.util.Set;
         

public class ContractIn extends AbstractDcLon implements IDcLon{

    public ContractIn(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private ProgramBaseTimePeriod programBaseTimePeriod;

    public ProgramBaseTimePeriod getProgramBaseTimePeriod(){
        return this.programBaseTimePeriod;
    }
    
    public void setProgramBaseTimePeriod(ProgramBaseTimePeriod programBaseTimePeriod){
        this.programBaseTimePeriod = programBaseTimePeriod;
    }
 

    private ThirdPerson thirdPerson;

    public ThirdPerson getThirdPerson(){
        return this.thirdPerson;
    }
    
    public void setThirdPerson(ThirdPerson thirdPerson){
        this.thirdPerson = thirdPerson;
    }
 

    
    private Set<ComercialDocumentIn> comercialDocuments;

    public Set<ComercialDocumentIn> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    public void setComercialDocuments(Set<ComercialDocumentIn> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        