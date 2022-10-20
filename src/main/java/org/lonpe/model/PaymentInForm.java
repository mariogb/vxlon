
package org.lonpe.model;            

            

         

public class PaymentInForm extends AbstractDcLon implements IDcLon{

    public PaymentInForm(){
    }

    

    
    private MonetaryAccount monetaryAccount;

    public MonetaryAccount getMonetaryAccount(){
        return this.monetaryAccount;
    }
    
    public void setMonetaryAccount(MonetaryAccount monetaryAccount){
        this.monetaryAccount = monetaryAccount;
    }
 

    private PaymentInType paymentInType;

    public PaymentInType getPaymentInType(){
        return this.paymentInType;
    }
    
    public void setPaymentInType(PaymentInType paymentInType){
        this.paymentInType = paymentInType;
    }
 

    
      
}
        