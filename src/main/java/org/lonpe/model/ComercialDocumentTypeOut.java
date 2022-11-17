
package org.lonpe.model;            

            
import java.util.Set;
         

public class ComercialDocumentTypeOut extends AbstractDcLon implements IDcLon{

    public ComercialDocumentTypeOut(){
        super();
    }

    
    private String afectStock;

    /**
     *
     * @return afectStock
     */    
    public String getAfectStock(){
        return this.afectStock;
    }

    /**
     *
     * @param afectStock
     */    
    public void setAfectStock(String afectStock){
        this.afectStock = afectStock;
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

    

    
    private Set<ComercialDocumentOut> comercialDocuments;

    /**
     *
     * @return comercialDocuments
     */     
    public Set<ComercialDocumentOut> getComercialDocuments(){
        return this.comercialDocuments;
    }
    
    /**
     *
     * @param comercialDocuments
     */       
    public void setComercialDocuments(Set<ComercialDocumentOut> comercialDocuments){
        this.comercialDocuments = comercialDocuments;
    }
 
      
}
        