
package org.lonpe.model;            

            

         

public class PaymentOut extends AbstractDcLon implements IDcLon{

    public PaymentOut(){
    }

    

    
    private PaymentOutForm paymentOutForm;

    public PaymentOutForm getPaymentOutForm(){
        return this.paymentOutForm;
    }
    
    public void setPaymentOutForm(PaymentOutForm paymentOutForm){
        this.paymentOutForm = paymentOutForm;
    }
 

    private ComercialDocumentOut comercialDocumentOut;

    public ComercialDocumentOut getComercialDocumentOut(){
        return this.comercialDocumentOut;
    }
    
    public void setComercialDocumentOut(ComercialDocumentOut comercialDocumentOut){
        this.comercialDocumentOut = comercialDocumentOut;
    }
 

    
      
}
        