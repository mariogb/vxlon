
package org.lonpe.model;            

            
import java.util.Set;
         

public class ComercialDocumentTypeIn extends AbstractDcLon implements IDcLon{

    public ComercialDocumentTypeIn(){
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
        