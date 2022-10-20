
package org.lonpe.model;            

            

         

public class BaseUserLon extends AbstractDcLon implements IDcLon{

    public BaseUserLon(){
    }

    

    
    private Base base;

    public Base getBase(){
        return this.base;
    }
    
    public void setBase(Base base){
        this.base = base;
    }
 

    private UserLon userLon;

    public UserLon getUserLon(){
        return this.userLon;
    }
    
    public void setUserLon(UserLon userLon){
        this.userLon = userLon;
    }
 

    
      
}
        