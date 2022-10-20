
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.util.Set;
         

public class ComercialDocumentIn extends AbstractDcLon implements IDcLon{

    public ComercialDocumentIn(){
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
    

    
    private ContractIn contract;

    public ContractIn getContract(){
        return this.contract;
    }
    
    public void setContract(ContractIn contract){
        this.contract = contract;
    }
 

    private UserLon userAutor;

    public UserLon getUserAutor(){
        return this.userAutor;
    }
    
    public void setUserAutor(UserLon userAutor){
        this.userAutor = userAutor;
    }
 

    private ComercialDocumentTypeIn comercialDocumentType;

    public ComercialDocumentTypeIn getComercialDocumentType(){
        return this.comercialDocumentType;
    }
    
    public void setComercialDocumentType(ComercialDocumentTypeIn comercialDocumentType){
        this.comercialDocumentType = comercialDocumentType;
    }
 

    
    private Set<InvoiceLineOut> invoiceLines;

    public Set<InvoiceLineOut> getInvoiceLines(){
        return this.invoiceLines;
    }
    
    public void setInvoiceLines(Set<InvoiceLineOut> invoiceLines){
        this.invoiceLines = invoiceLines;
    }
 


    private Set<PaymentIn> payments;

    public Set<PaymentIn> getPayments(){
        return this.payments;
    }
    
    public void setPayments(Set<PaymentIn> payments){
        this.payments = payments;
    }
 
      
}
        