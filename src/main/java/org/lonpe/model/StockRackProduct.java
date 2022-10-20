
package org.lonpe.model;            

            
import java.util.Set;
         

public class StockRackProduct extends AbstractDcLon implements IDcLon{

    public StockRackProduct(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private Long quantity;

    public Long getQuantity(){
        return this.quantity;
    }

    public void setQuantity(Long quantity){
        this.quantity = quantity;
    }        
    

    private String serialNumber;

    public String getSerialNumber(){
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }        
    

    
    private StockRack stockRack;

    public StockRack getStockRack(){
        return this.stockRack;
    }
    
    public void setStockRack(StockRack stockRack){
        this.stockRack = stockRack;
    }
 

    private Product product;

    public Product getProduct(){
        return this.product;
    }
    
    public void setProduct(Product product){
        this.product = product;
    }
 

    
    private Set<InvoiceLineIn> InvoiceLineIns;

    public Set<InvoiceLineIn> getInvoiceLineIns(){
        return this.InvoiceLineIns;
    }
    
    public void setInvoiceLineIns(Set<InvoiceLineIn> InvoiceLineIns){
        this.InvoiceLineIns = InvoiceLineIns;
    }
 


    private Set<InvoiceLineOut> InvoiceLineOuts;

    public Set<InvoiceLineOut> getInvoiceLineOuts(){
        return this.InvoiceLineOuts;
    }
    
    public void setInvoiceLineOuts(Set<InvoiceLineOut> InvoiceLineOuts){
        this.InvoiceLineOuts = InvoiceLineOuts;
    }
 
      
}
        