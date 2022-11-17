
package org.lonpe.model;            

            
import java.util.Set;
         

public class Product extends AbstractDcLon implements IDcLon{

    public Product(){
        super();
    }

    
    private String description;

    /**
     *
     * @return description
     */    
    public String getDescription(){
        return this.description;
    }

    /**
     *
     * @param description
     */    
    public void setDescription(String description){
        this.description = description;
    }           

    private String fastKey;

    /**
     *
     * @return fastKey
     */    
    public String getFastKey(){
        return this.fastKey;
    }

    /**
     *
     * @param fastKey
     */    
    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
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

    private String sku;

    /**
     *
     * @return sku
     */    
    public String getSku(){
        return this.sku;
    }

    /**
     *
     * @param sku
     */    
    public void setSku(String sku){
        this.sku = sku;
    }           

    
    private ProductType productType;

    /**
     *
     * @return productType
     */  
    public ProductType getProductType(){
        return this.productType;
    }

    /**
     *
     * @param productType
     */       
    public void setProductType(ProductType productType){
        this.productType = productType;
    }

    
    private Set<InvoiceLineIn> invoiceLineIns;

    /**
     *
     * @return invoiceLineIns
     */     
    public Set<InvoiceLineIn> getInvoiceLineIns(){
        return this.invoiceLineIns;
    }
    
    /**
     *
     * @param invoiceLineIns
     */       
    public void setInvoiceLineIns(Set<InvoiceLineIn> invoiceLineIns){
        this.invoiceLineIns = invoiceLineIns;
    }
 

    private Set<InvoiceLineOut> invoiceLineOuts;

    /**
     *
     * @return invoiceLineOuts
     */     
    public Set<InvoiceLineOut> getInvoiceLineOuts(){
        return this.invoiceLineOuts;
    }
    
    /**
     *
     * @param invoiceLineOuts
     */       
    public void setInvoiceLineOuts(Set<InvoiceLineOut> invoiceLineOuts){
        this.invoiceLineOuts = invoiceLineOuts;
    }
 
      
}
        