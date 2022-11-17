
package org.lonpe.model;            

            

         

public class PaymentOutType extends AbstractDcLon implements IDcLon{

    public PaymentOutType(){
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
        