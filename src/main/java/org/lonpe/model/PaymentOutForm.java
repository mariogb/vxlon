
package org.lonpe.model;            

            

         

public class PaymentOutForm extends AbstractDcLon implements IDcLon{

    public PaymentOutForm(){
    }

    

    
    private MonetaryAccount monetaryAccount;

    public MonetaryAccount getMonetaryAccount(){
        return this.monetaryAccount;
    }
    
    public void setMonetaryAccount(MonetaryAccount monetaryAccount){
        this.monetaryAccount = monetaryAccount;
    }
 

    private PaymentOutType paymentOutType;

    public PaymentOutType getPaymentOutType(){
        return this.paymentOutType;
    }
    
    public void setPaymentOutType(PaymentOutType paymentOutType){
        this.paymentOutType = paymentOutType;
    }
 

    
      
}
        