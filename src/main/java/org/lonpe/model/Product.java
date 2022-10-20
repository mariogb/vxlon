
package org.lonpe.model;            

            
import java.util.Set;
         

public class Product extends AbstractDcLon implements IDcLon{

    public Product(){
    }

    
    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }        
    

    private String fastKey;

    public String getFastKey(){
        return this.fastKey;
    }

    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private String sku;

    public String getSku(){
        return this.sku;
    }

    public void setSku(String sku){
        this.sku = sku;
    }        
    

    
    private ProductType productType;

    public ProductType getProductType(){
        return this.productType;
    }
    
    public void setProductType(ProductType productType){
        this.productType = productType;
    }
 

    
    private Set<InvoiceLineIn> invoiceLineIns;

    public Set<InvoiceLineIn> getInvoiceLineIns(){
        return this.invoiceLineIns;
    }
    
    public void setInvoiceLineIns(Set<InvoiceLineIn> invoiceLineIns){
        this.invoiceLineIns = invoiceLineIns;
    }
 


    private Set<InvoiceLineOut> invoiceLineOuts;

    public Set<InvoiceLineOut> getInvoiceLineOuts(){
        return this.invoiceLineOuts;
    }
    
    public void setInvoiceLineOuts(Set<InvoiceLineOut> invoiceLineOuts){
        this.invoiceLineOuts = invoiceLineOuts;
    }
 
      
}
        