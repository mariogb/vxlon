
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.math.BigDecimal;
         

public class InvoiceLineOut extends AbstractDcLon implements IDcLon{

    public InvoiceLineOut(){
    }

    
    private BigDecimal askQuantity;

    public BigDecimal getAskQuantity(){
        return this.askQuantity;
    }

    public void setAskQuantity(BigDecimal askQuantity){
        this.askQuantity = askQuantity;
    }        
    

    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate){
        this.createdDate = createdDate;
    }        
    

    private BigDecimal descount;

    public BigDecimal getDescount(){
        return this.descount;
    }

    public void setDescount(BigDecimal descount){
        this.descount = descount;
    }        
    

    private LocalDateTime invoiceDate;

    public LocalDateTime getInvoiceDate(){
        return this.invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate){
        this.invoiceDate = invoiceDate;
    }        
    

    private Integer orden;

    public Integer getOrden(){
        return this.orden;
    }

    public void setOrden(Integer orden){
        this.orden = orden;
    }        
    

    private String status;

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }        
    

    private LocalDateTime supplyDate;

    public LocalDateTime getSupplyDate(){
        return this.supplyDate;
    }

    public void setSupplyDate(LocalDateTime supplyDate){
        this.supplyDate = supplyDate;
    }        
    

    private BigDecimal supplyQuantity;

    public BigDecimal getSupplyQuantity(){
        return this.supplyQuantity;
    }

    public void setSupplyQuantity(BigDecimal supplyQuantity){
        this.supplyQuantity = supplyQuantity;
    }        
    

    private BigDecimal taxPorcent;

    public BigDecimal getTaxPorcent(){
        return this.taxPorcent;
    }

    public void setTaxPorcent(BigDecimal taxPorcent){
        this.taxPorcent = taxPorcent;
    }        
    

    private BigDecimal total;

    public BigDecimal getTotal(){
        return this.total;
    }

    public void setTotal(BigDecimal total){
        this.total = total;
    }        
    

    private BigDecimal totalCost;

    public BigDecimal getTotalCost(){
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost){
        this.totalCost = totalCost;
    }        
    

    private BigDecimal unitCost;

    public BigDecimal getUnitCost(){
        return this.unitCost;
    }

    public void setUnitCost(BigDecimal unitCost){
        this.unitCost = unitCost;
    }        
    

    
    private ComercialDocumentIn comercialDocument;

    public ComercialDocumentIn getComercialDocument(){
        return this.comercialDocument;
    }
    
    public void setComercialDocument(ComercialDocumentIn comercialDocument){
        this.comercialDocument = comercialDocument;
    }
 

    private Product product;

    public Product getProduct(){
        return this.product;
    }
    
    public void setProduct(Product product){
        this.product = product;
    }
 

    private StockRackProduct stockRackProduct;

    public StockRackProduct getStockRackProduct(){
        return this.stockRackProduct;
    }
    
    public void setStockRackProduct(StockRackProduct stockRackProduct){
        this.stockRackProduct = stockRackProduct;
    }
 

    
      
}
        