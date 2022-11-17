
package org.lonpe.model;            

            

         

public class PaymentOut extends AbstractDcLon implements IDcLon{

    public PaymentOut(){
        super();
    }

    

    
    private PaymentOutForm paymentOutForm;

    /**
     *
     * @return paymentOutForm
     */  
    public PaymentOutForm getPaymentOutForm(){
        return this.paymentOutForm;
    }

    /**
     *
     * @param paymentOutForm
     */       
    public void setPaymentOutForm(PaymentOutForm paymentOutForm){
        this.paymentOutForm = paymentOutForm;
    }

    private ComercialDocumentOut comercialDocumentOut;

    /**
     *
     * @return comercialDocumentOut
     */  
    public ComercialDocumentOut getComercialDocumentOut(){
        return this.comercialDocumentOut;
    }

    /**
     *
     * @param comercialDocumentOut
     */       
    public void setComercialDocumentOut(ComercialDocumentOut comercialDocumentOut){
        this.comercialDocumentOut = comercialDocumentOut;
    }

    
      
}
        