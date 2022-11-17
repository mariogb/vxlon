
package org.lonpe.model;            

            
import java.util.Set;
         

public class Role extends AbstractDcLon implements IDcLon{

    public Role(){
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

    

    
    private Set<UserRole> userRoles;

    /**
     *
     * @return userRoles
     */     
    public Set<UserRole> getUserRoles(){
        return this.userRoles;
    }
    
    /**
     *
     * @param userRoles
     */       
    public void setUserRoles(Set<UserRole> userRoles){
        this.userRoles = userRoles;
    }
 

    private Set<EntityPermisionRole> entityPermisionRoles;

    /**
     *
     * @return entityPermisionRoles
     */     
    public Set<EntityPermisionRole> getEntityPermisionRoles(){
        return this.entityPermisionRoles;
    }
    
    /**
     *
     * @param entityPermisionRoles
     */       
    public void setEntityPermisionRoles(Set<EntityPermisionRole> entityPermisionRoles){
        this.entityPermisionRoles = entityPermisionRoles;
    }
 
      
}
        