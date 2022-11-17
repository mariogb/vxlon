
package org.lonpe.model;            

            
import java.util.Set;
         

public class MonetaryAccount extends AbstractDcLon implements IDcLon{

    public MonetaryAccount(){
        super();
    }

    
    private String pname;

    /**
     *
     * @return pname
     */    
    public String getPname(){
        return this.pname;
    }

    /**
     *
     * @param pname
     */    
    public void setPname(String pname){
        this.pname = pname;
    }           

    

    
    private Set<PaymentOut> paymentOuts;

    /**
     *
     * @return paymentOuts
     */     
    public Set<PaymentOut> getPaymentOuts(){
        return this.paymentOuts;
    }
    
    /**
     *
     * @param paymentOuts
     */       
    public void setPaymentOuts(Set<PaymentOut> paymentOuts){
        this.paymentOuts = paymentOuts;
    }
 

    private Set<PaymentIn> paymentIns;

    /**
     *
     * @return paymentIns
     */     
    public Set<PaymentIn> getPaymentIns(){
        return this.paymentIns;
    }
    
    /**
     *
     * @param paymentIns
     */       
    public void setPaymentIns(Set<PaymentIn> paymentIns){
        this.paymentIns = paymentIns;
    }
 
      
}
        