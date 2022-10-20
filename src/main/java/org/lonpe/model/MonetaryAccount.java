
package org.lonpe.model;            

            
import java.util.Set;
         

public class MonetaryAccount extends AbstractDcLon implements IDcLon{

    public MonetaryAccount(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<PaymentOut> paymentOuts;

    public Set<PaymentOut> getPaymentOuts(){
        return this.paymentOuts;
    }
    
    public void setPaymentOuts(Set<PaymentOut> paymentOuts){
        this.paymentOuts = paymentOuts;
    }
 


    private Set<PaymentIn> paymentIns;

    public Set<PaymentIn> getPaymentIns(){
        return this.paymentIns;
    }
    
    public void setPaymentIns(Set<PaymentIn> paymentIns){
        this.paymentIns = paymentIns;
    }
 
      
}
        