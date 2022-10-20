
package org.lonpe.model;            

            

         

public class EntityPermisionRole extends AbstractDcLon implements IDcLon{

    public EntityPermisionRole(){
    }

    
    private Boolean enabled;

    public Boolean getEnabled(){
        return this.enabled;
    }

    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }        
    

    private String nombre;

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }        
    

    private String permission;

    public String getPermission(){
        return this.permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }        
    

    
    private Role role;

    public Role getRole(){
        return this.role;
    }
    
    public void setRole(Role role){
        this.role = role;
    }
 

    
      
}
        