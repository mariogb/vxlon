
package org.lonpe.model;            

            
import java.util.Set;
         

public class Role extends AbstractDcLon implements IDcLon{

    public Role(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<UserRole> userRoles;

    public Set<UserRole> getUserRoles(){
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles){
        this.userRoles = userRoles;
    }
 


    private Set<EntityPermisionRole> entityPermisionRoles;

    public Set<EntityPermisionRole> getEntityPermisionRoles(){
        return this.entityPermisionRoles;
    }
    
    public void setEntityPermisionRoles(Set<EntityPermisionRole> entityPermisionRoles){
        this.entityPermisionRoles = entityPermisionRoles;
    }
 
      
}
        