
package org.lonpe.model;            

            
import java.util.Set;
         

public class StockRackProduct extends AbstractDcLon implements IDcLon{

    public StockRackProduct(){
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

    private Long quantity;

    /**
     *
     * @return quantity
     */    
    public Long getQuantity(){
        return this.quantity;
    }

    /**
     *
     * @param quantity
     */    
    public void setQuantity(Long quantity){
        this.quantity = quantity;
    }           

    private String serialNumber;

    /**
     *
     * @return serialNumber
     */    
    public String getSerialNumber(){
        return this.serialNumber;
    }

    /**
     *
     * @param serialNumber
     */    
    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }           

    
    private StockRack stockRack;

    /**
     *
     * @return stockRack
     */  
    public StockRack getStockRack(){
        return this.stockRack;
    }

    /**
     *
     * @param stockRack
     */       
    public void setStockRack(StockRack stockRack){
        this.stockRack = stockRack;
    }

    private Product product;

    /**
     *
     * @return product
     */  
    public Product getProduct(){
        return this.product;
    }

    /**
     *
     * @param product
     */       
    public void setProduct(Product product){
        this.product = product;
    }

    
    private Set<InvoiceLineIn> InvoiceLineIns;

    /**
     *
     * @return InvoiceLineIns
     */     
    public Set<InvoiceLineIn> getInvoiceLineIns(){
        return this.InvoiceLineIns;
    }
    
    /**
     *
     * @param InvoiceLineIns
     */       
    public void setInvoiceLineIns(Set<InvoiceLineIn> InvoiceLineIns){
        this.InvoiceLineIns = InvoiceLineIns;
    }
 

    private Set<InvoiceLineOut> InvoiceLineOuts;

    /**
     *
     * @return InvoiceLineOuts
     */     
    public Set<InvoiceLineOut> getInvoiceLineOuts(){
        return this.InvoiceLineOuts;
    }
    
    /**
     *
     * @param InvoiceLineOuts
     */       
    public void setInvoiceLineOuts(Set<InvoiceLineOut> InvoiceLineOuts){
        this.InvoiceLineOuts = InvoiceLineOuts;
    }
 
      
}
        