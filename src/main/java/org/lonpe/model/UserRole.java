
package org.lonpe.model;            

            

         

public class UserRole extends AbstractDcLon implements IDcLon{

    public UserRole(){
    }

    

    
    private UserLon userLon;

    public UserLon getUserLon(){
        return this.userLon;
    }
    
    public void setUserLon(UserLon userLon){
        this.userLon = userLon;
    }
 

    private Role role;

    public Role getRole(){
        return this.role;
    }
    
    public void setRole(Role role){
        this.role = role;
    }
 

    
      
}
        