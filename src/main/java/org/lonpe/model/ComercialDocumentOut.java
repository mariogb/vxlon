
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.util.Set;
         

public class ComercialDocumentOut extends AbstractDcLon implements IDcLon{

    public ComercialDocumentOut(){
        super();
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

    private LocalDateTime documentDate;

    /**
     *
     * @return documentDate
     */    
    public LocalDateTime getDocumentDate(){
        return this.documentDate;
    }

    /**
     *
     * @param documentDate
     */    
    public void setDocumentDate(LocalDateTime documentDate){
        this.documentDate = documentDate;
    }           

    private String folio;

    /**
     *
     * @return folio
     */    
    public String getFolio(){
        return this.folio;
    }

    /**
     *
     * @param folio
     */    
    public void setFolio(String folio){
        this.folio = folio;
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

    
    private ContractOut contract;

    /**
     *
     * @return contract
     */  
    public ContractOut getContract(){
        return this.contract;
    }

    /**
     *
     * @param contract
     */       
    public void setContract(ContractOut contract){
        this.contract = contract;
    }

    private UserLon userAutor;

    /**
     *
     * @return userAutor
     */  
    public UserLon getUserAutor(){
        return this.userAutor;
    }

    /**
     *
     * @param userAutor
     */       
    public void setUserAutor(UserLon userAutor){
        this.userAutor = userAutor;
    }

    private ComercialDocumentTypeOut comercialDocumentType;

    /**
     *
     * @return comercialDocumentType
     */  
    public ComercialDocumentTypeOut getComercialDocumentType(){
        return this.comercialDocumentType;
    }

    /**
     *
     * @param comercialDocumentType
     */       
    public void setComercialDocumentType(ComercialDocumentTypeOut comercialDocumentType){
        this.comercialDocumentType = comercialDocumentType;
    }

    
    private Set<InvoiceLineIn> invoiceLines;

    /**
     *
     * @return invoiceLines
     */     
    public Set<InvoiceLineIn> getInvoiceLines(){
        return this.invoiceLines;
    }
    
    /**
     *
     * @param invoiceLines
     */       
    public void setInvoiceLines(Set<InvoiceLineIn> invoiceLines){
        this.invoiceLines = invoiceLines;
    }
 

    private Set<PaymentOut> payments;

    /**
     *
     * @return payments
     */     
    public Set<PaymentOut> getPayments(){
        return this.payments;
    }
    
    /**
     *
     * @param payments
     */       
    public void setPayments(Set<PaymentOut> payments){
        this.payments = payments;
    }
 
      
}
        