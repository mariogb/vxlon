
package org.lonpe.model;            

            

         

public class PaymentInForm extends AbstractDcLon implements IDcLon{

    public PaymentInForm(){
        super();
    }

    

    
    private MonetaryAccount monetaryAccount;

    /**
     *
     * @return monetaryAccount
     */  
    public MonetaryAccount getMonetaryAccount(){
        return this.monetaryAccount;
    }

    /**
     *
     * @param monetaryAccount
     */       
    public void setMonetaryAccount(MonetaryAccount monetaryAccount){
        this.monetaryAccount = monetaryAccount;
    }

    private PaymentInType paymentInType;

    /**
     *
     * @return paymentInType
     */  
    public PaymentInType getPaymentInType(){
        return this.paymentInType;
    }

    /**
     *
     * @param paymentInType
     */       
    public void setPaymentInType(PaymentInType paymentInType){
        this.paymentInType = paymentInType;
    }

    
      
}
        