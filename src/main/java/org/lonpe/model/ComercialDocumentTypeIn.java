
package org.lonpe.model;            

            
import java.util.Set;
         

public class ComercialDocumentTypeIn extends AbstractDcLon implements IDcLon{

    public ComercialDocumentTypeIn(){
    }

    
    private String afectStock;

    public String getAfectStock(){
        return this.afectStock;
    }

    public void setAfectStock(String afectStock){
        this.afectStock = afectStock;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<ComercialDocumentIn> comercialDocuments;

    public Set<ComercialDocumentIn> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    public void setComercialDocuments(Set<ComercialDocumentIn> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        