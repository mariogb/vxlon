
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.math.BigDecimal;
         

public class InvoiceLineOut extends AbstractDcLon implements IDcLon{

    public InvoiceLineOut(){
        super();
    }

    
    private BigDecimal askQuantity;

    /**
     *
     * @return askQuantity
     */    
    public BigDecimal getAskQuantity(){
        return this.askQuantity;
    }

    /**
     *
     * @param askQuantity
     */    
    public void setAskQuantity(BigDecimal askQuantity){
        this.askQuantity = askQuantity;
    }           

    private LocalDateTime createdDate;

    /**
     *
     * @return createdDate
     */    
    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    /**
     *
     * @param createdDate
     */    
    public void setCreatedDate(LocalDateTime createdDate){
        this.createdDate = createdDate;
    }           

    private BigDecimal descount;

    /**
     *
     * @return descount
     */    
    public BigDecimal getDescount(){
        return this.descount;
    }

    /**
     *
     * @param descount
     */    
    public void setDescount(BigDecimal descount){
        this.descount = descount;
    }           

    private LocalDateTime invoiceDate;

    /**
     *
     * @return invoiceDate
     */    
    public LocalDateTime getInvoiceDate(){
        return this.invoiceDate;
    }

    /**
     *
     * @param invoiceDate
     */    
    public void setInvoiceDate(LocalDateTime invoiceDate){
        this.invoiceDate = invoiceDate;
    }           

    private Integer orden;

    /**
     *
     * @return orden
     */    
    public Integer getOrden(){
        return this.orden;
    }

    /**
     *
     * @param orden
     */    
    public void setOrden(Integer orden){
        this.orden = orden;
    }           

    private String status;

    /**
     *
     * @return status
     */    
    public String getStatus(){
        return this.status;
    }

    /**
     *
     * @param status
     */    
    public void setStatus(String status){
        this.status = status;
    }           

    private LocalDateTime supplyDate;

    /**
     *
     * @return supplyDate
     */    
    public LocalDateTime getSupplyDate(){
        return this.supplyDate;
    }

    /**
     *
     * @param supplyDate
     */    
    public void setSupplyDate(LocalDateTime supplyDate){
        this.supplyDate = supplyDate;
    }           

    private BigDecimal supplyQuantity;

    /**
     *
     * @return supplyQuantity
     */    
    public BigDecimal getSupplyQuantity(){
        return this.supplyQuantity;
    }

    /**
     *
     * @param supplyQuantity
     */    
    public void setSupplyQuantity(BigDecimal supplyQuantity){
        this.supplyQuantity = supplyQuantity;
    }           

    private BigDecimal taxPorcent;

    /**
     *
     * @return taxPorcent
     */    
    public BigDecimal getTaxPorcent(){
        return this.taxPorcent;
    }

    /**
     *
     * @param taxPorcent
     */    
    public void setTaxPorcent(BigDecimal taxPorcent){
        this.taxPorcent = taxPorcent;
    }           

    private BigDecimal total;

    /**
     *
     * @return total
     */    
    public BigDecimal getTotal(){
        return this.total;
    }

    /**
     *
     * @param total
     */    
    public void setTotal(BigDecimal total){
        this.total = total;
    }           

    private BigDecimal totalCost;

    /**
     *
     * @return totalCost
     */    
    public BigDecimal getTotalCost(){
        return this.totalCost;
    }

    /**
     *
     * @param totalCost
     */    
    public void setTotalCost(BigDecimal totalCost){
        this.totalCost = totalCost;
    }           

    private BigDecimal unitCost;

    /**
     *
     * @return unitCost
     */    
    public BigDecimal getUnitCost(){
        return this.unitCost;
    }

    /**
     *
     * @param unitCost
     */    
    public void setUnitCost(BigDecimal unitCost){
        this.unitCost = unitCost;
    }           

    
    private ComercialDocumentIn comercialDocument;

    /**
     *
     * @return comercialDocument
     */  
    public ComercialDocumentIn getComercialDocument(){
        return this.comercialDocument;
    }

    /**
     *
     * @param comercialDocument
     */       
    public void setComercialDocument(ComercialDocumentIn comercialDocument){
        this.comercialDocument = comercialDocument;
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

    private StockRackProduct stockRackProduct;

    /**
     *
     * @return stockRackProduct
     */  
    public StockRackProduct getStockRackProduct(){
        return this.stockRackProduct;
    }

    /**
     *
     * @param stockRackProduct
     */       
    public void setStockRackProduct(StockRackProduct stockRackProduct){
        this.stockRackProduct = stockRackProduct;
    }

    
      
}
        