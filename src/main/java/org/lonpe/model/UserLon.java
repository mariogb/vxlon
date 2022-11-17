
package org.lonpe.model;            

            
import java.util.Set;
         

public class UserLon extends AbstractDcLon implements IDcLon{

    public UserLon(){
        super();
    }

    
    private String email;

    /**
     *
     * @return email
     */    
    public String getEmail(){
        return this.email;
    }

    /**
     *
     * @param email
     */    
    public void setEmail(String email){
        this.email = email;
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

    private String password;

    /**
     *
     * @return password
     */    
    public String getPassword(){
        return this.password;
    }

    /**
     *
     * @param password
     */    
    public void setPassword(String password){
        this.password = password;
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

    private String typeLon;

    /**
     *
     * @return typeLon
     */    
    public String getTypeLon(){
        return this.typeLon;
    }

    /**
     *
     * @param typeLon
     */    
    public void setTypeLon(String typeLon){
        this.typeLon = typeLon;
    }           

    private String username;

    /**
     *
     * @return username
     */    
    public String getUsername(){
        return this.username;
    }

    /**
     *
     * @param username
     */    
    public void setUsername(String username){
        this.username = username;
    }           

    

    
    private Set<DepartamentUserLon> departamentUserLons;

    /**
     *
     * @return departamentUserLons
     */     
    public Set<DepartamentUserLon> getDepartamentUserLons(){
        return this.departamentUserLons;
    }
    
    /**
     *
     * @param departamentUserLons
     */       
    public void setDepartamentUserLons(Set<DepartamentUserLon> departamentUserLons){
        this.departamentUserLons = departamentUserLons;
    }
 

    private Set<ProgramUserLon> programUserLons;

    /**
     *
     * @return programUserLons
     */     
    public Set<ProgramUserLon> getProgramUserLons(){
        return this.programUserLons;
    }
    
    /**
     *
     * @param programUserLons
     */       
    public void setProgramUserLons(Set<ProgramUserLon> programUserLons){
        this.programUserLons = programUserLons;
    }
 

    private Set<BaseUserLon> baseUserLons;

    /**
     *
     * @return baseUserLons
     */     
    public Set<BaseUserLon> getBaseUserLons(){
        return this.baseUserLons;
    }
    
    /**
     *
     * @param baseUserLons
     */       
    public void setBaseUserLons(Set<BaseUserLon> baseUserLons){
        this.baseUserLons = baseUserLons;
    }
 

    private Set<ComercialDocumentIn> comercialDocumentIns;

    /**
     *
     * @return comercialDocumentIns
     */     
    public Set<ComercialDocumentIn> getComercialDocumentIns(){
        return this.comercialDocumentIns;
    }
    
    /**
     *
     * @param comercialDocumentIns
     */       
    public void setComercialDocumentIns(Set<ComercialDocumentIn> comercialDocumentIns){
        this.comercialDocumentIns = comercialDocumentIns;
    }
 

    private Set<ComercialDocumentOut> comercialDocumentOuts;

    /**
     *
     * @return comercialDocumentOuts
     */     
    public Set<ComercialDocumentOut> getComercialDocumentOuts(){
        return this.comercialDocumentOuts;
    }
    
    /**
     *
     * @param comercialDocumentOuts
     */       
    public void setComercialDocumentOuts(Set<ComercialDocumentOut> comercialDocumentOuts){
        this.comercialDocumentOuts = comercialDocumentOuts;
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
 

    private Set<UserThirdPerson> userThirdPersons;

    /**
     *
     * @return userThirdPersons
     */     
    public Set<UserThirdPerson> getUserThirdPersons(){
        return this.userThirdPersons;
    }
    
    /**
     *
     * @param userThirdPersons
     */       
    public void setUserThirdPersons(Set<UserThirdPerson> userThirdPersons){
        this.userThirdPersons = userThirdPersons;
    }
 
      
}
        