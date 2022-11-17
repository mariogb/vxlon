
package org.lonpe.model;            

            

         

public class BaseUserLon extends AbstractDcLon implements IDcLon{

    public BaseUserLon(){
        super();
    }

    

    
    private Base base;

    /**
     *
     * @return base
     */  
    public Base getBase(){
        return this.base;
    }

    /**
     *
     * @param base
     */       
    public void setBase(Base base){
        this.base = base;
    }

    private UserLon userLon;

    /**
     *
     * @return userLon
     */  
    public UserLon getUserLon(){
        return this.userLon;
    }

    /**
     *
     * @param userLon
     */       
    public void setUserLon(UserLon userLon){
        this.userLon = userLon;
    }

    
      
}
        