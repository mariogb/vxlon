
package org.lonpe.model;            

            

         

public class PaymentInType extends AbstractDcLon implements IDcLon{

    public PaymentInType(){
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

    

    
      
}
        