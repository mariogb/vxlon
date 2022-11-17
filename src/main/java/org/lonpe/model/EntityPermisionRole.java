
package org.lonpe.model;            

            

         

public class EntityPermisionRole extends AbstractDcLon implements IDcLon{

    public EntityPermisionRole(){
        super();
    }

    
    private Boolean enabled;

    /**
     *
     * @return enabled
     */    
    public Boolean getEnabled(){
        return this.enabled;
    }

    /**
     *
     * @param enabled
     */    
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }           

    private String nombre;

    /**
     *
     * @return nombre
     */    
    public String getNombre(){
        return this.nombre;
    }

    /**
     *
     * @param nombre
     */    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }           

    private String permission;

    /**
     *
     * @return permission
     */    
    public String getPermission(){
        return this.permission;
    }

    /**
     *
     * @param permission
     */    
    public void setPermission(String permission){
        this.permission = permission;
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
        