
package org.lonpe.model;            

            

         

public class PaymentIn extends AbstractDcLon implements IDcLon{

    public PaymentIn(){
        super();
    }

    

    
    private PaymentInForm paymentInForm;

    /**
     *
     * @return paymentInForm
     */  
    public PaymentInForm getPaymentInForm(){
        return this.paymentInForm;
    }

    /**
     *
     * @param paymentInForm
     */       
    public void setPaymentInForm(PaymentInForm paymentInForm){
        this.paymentInForm = paymentInForm;
    }

    private ComercialDocumentIn comercialDocumentIn;

    /**
     *
     * @return comercialDocumentIn
     */  
    public ComercialDocumentIn getComercialDocumentIn(){
        return this.comercialDocumentIn;
    }

    /**
     *
     * @param comercialDocumentIn
     */       
    public void setComercialDocumentIn(ComercialDocumentIn comercialDocumentIn){
        this.comercialDocumentIn = comercialDocumentIn;
    }

    
      
}
        