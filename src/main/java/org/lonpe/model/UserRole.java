
package org.lonpe.model;            

            

         

public class UserRole extends AbstractDcLon implements IDcLon{

    public UserRole(){
        super();
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

    private Role role;

    /**
     *
     * @return role
     */  
    public Role getRole(){
        return this.role;
    }

    /**
     *
     * @param role
     */       
    public void setRole(Role role){
        this.role = role;
    }

    
      
}
        