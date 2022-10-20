
package org.lonpe.model;            

            

         

public class PaymentIn extends AbstractDcLon implements IDcLon{

    public PaymentIn(){
    }

    

    
    private PaymentInForm paymentInForm;

    public PaymentInForm getPaymentInForm(){
        return this.paymentInForm;
    }
    
    public void setPaymentInForm(PaymentInForm paymentInForm){
        this.paymentInForm = paymentInForm;
    }
 

    private ComercialDocumentIn comercialDocumentIn;

    public ComercialDocumentIn getComercialDocumentIn(){
        return this.comercialDocumentIn;
    }
    
    public void setComercialDocumentIn(ComercialDocumentIn comercialDocumentIn){
        this.comercialDocumentIn = comercialDocumentIn;
    }
 

    
      
}
        