
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.util.Set;
         

public class ComercialDocumentIn extends AbstractDcLon implements IDcLon{

    public ComercialDocumentIn(){
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

    
    private ContractIn contract;

    /**
     *
     * @return contract
     */  
    public ContractIn getContract(){
        return this.contract;
    }

    /**
     *
     * @param contract
     */       
    public void setContract(ContractIn contract){
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

    private ComercialDocumentTypeIn comercialDocumentType;

    /**
     *
     * @return comercialDocumentType
     */  
    public ComercialDocumentTypeIn getComercialDocumentType(){
        return this.comercialDocumentType;
    }

    /**
     *
     * @param comercialDocumentType
     */       
    public void setComercialDocumentType(ComercialDocumentTypeIn comercialDocumentType){
        this.comercialDocumentType = comercialDocumentType;
    }

    
    private Set<InvoiceLineOut> invoiceLines;

    /**
     *
     * @return invoiceLines
     */     
    public Set<InvoiceLineOut> getInvoiceLines(){
        return this.invoiceLines;
    }
    
    /**
     *
     * @param invoiceLines
     */       
    public void setInvoiceLines(Set<InvoiceLineOut> invoiceLines){
        this.invoiceLines = invoiceLines;
    }
 

    private Set<PaymentIn> payments;

    /**
     *
     * @return payments
     */     
    public Set<PaymentIn> getPayments(){
        return this.payments;
    }
    
    /**
     *
     * @param payments
     */       
    public void setPayments(Set<PaymentIn> payments){
        this.payments = payments;
    }
 
      
}
        