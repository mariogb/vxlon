
package org.lonpe.model;            

            
import java.util.Set;
         

public class ComercialDocumentTypeOut extends AbstractDcLon implements IDcLon{

    public ComercialDocumentTypeOut(){
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
    

    

    
    private Set<ComercialDocumentOut> comercialDocuments;

    public Set<ComercialDocumentOut> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    public void setComercialDocuments(Set<ComercialDocumentOut> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        