
package org.lonpe.model;            

            

         

public class PaymentOutForm extends AbstractDcLon implements IDcLon{

    public PaymentOutForm(){
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

    private PaymentOutType paymentOutType;

    /**
     *
     * @return paymentOutType
     */  
    public PaymentOutType getPaymentOutType(){
        return this.paymentOutType;
    }

    /**
     *
     * @param paymentOutType
     */       
    public void setPaymentOutType(PaymentOutType paymentOutType){
        this.paymentOutType = paymentOutType;
    }

    
      
}
        