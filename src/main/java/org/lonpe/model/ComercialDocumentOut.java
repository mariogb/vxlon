
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.util.Set;
         

public class ComercialDocumentOut extends AbstractDcLon implements IDcLon{

    public ComercialDocumentOut(){
    }

    
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate){
        this.createdDate = createdDate;
    }        
    

    private LocalDateTime documentDate;

    public LocalDateTime getDocumentDate(){
        return this.documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate){
        this.documentDate = documentDate;
    }        
    

    private String folio;

    public String getFolio(){
        return this.folio;
    }

    public void setFolio(String folio){
        this.folio = folio;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
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
    

    
    private ContractOut contract;

    public ContractOut getContract(){
        return this.contract;
    }
    
    public void setContract(ContractOut contract){
        this.contract = contract;
    }
 

    private UserLon userAutor;

    public UserLon getUserAutor(){
        return this.userAutor;
    }
    
    public void setUserAutor(UserLon userAutor){
        this.userAutor = userAutor;
    }
 

    private ComercialDocumentTypeOut comercialDocumentType;

    public ComercialDocumentTypeOut getComercialDocumentType(){
        return this.comercialDocumentType;
    }
    
    public void setComercialDocumentType(ComercialDocumentTypeOut comercialDocumentType){
        this.comercialDocumentType = comercialDocumentType;
    }
 

    
    private Set<InvoiceLineIn> invoiceLines;

    public Set<InvoiceLineIn> getInvoiceLines(){
        return this.invoiceLines;
    }
    
    public void setInvoiceLines(Set<InvoiceLineIn> invoiceLines){
        this.invoiceLines = invoiceLines;
    }
 


    private Set<PaymentOut> payments;

    public Set<PaymentOut> getPayments(){
        return this.payments;
    }
    
    public void setPayments(Set<PaymentOut> payments){
        this.payments = payments;
    }
 
      
}
        