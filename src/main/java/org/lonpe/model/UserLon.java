
package org.lonpe.model;            

            
import java.util.Set;
         

public class UserLon extends AbstractDcLon implements IDcLon{

    public UserLon(){
    }

    
    private String email;

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }        
    

    private Boolean enabled;

    public Boolean getEnabled(){
        return this.enabled;
    }

    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }        
    

    private String password;

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private String typeLon;

    public String getTypeLon(){
        return this.typeLon;
    }

    public void setTypeLon(String typeLon){
        this.typeLon = typeLon;
    }        
    

    private String username;

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }        
    

    

    
    private Set<DepartamentUserLon> departamentUserLons;

    public Set<DepartamentUserLon> getDepartamentUserLons(){
        return this.departamentUserLons;
    }
    
    public void setDepartamentUserLons(Set<DepartamentUserLon> departamentUserLons){
        this.departamentUserLons = departamentUserLons;
    }
 


    private Set<ProgramUserLon> programUserLons;

    public Set<ProgramUserLon> getProgramUserLons(){
        return this.programUserLons;
    }
    
    public void setProgramUserLons(Set<ProgramUserLon> programUserLons){
        this.programUserLons = programUserLons;
    }
 


    private Set<BaseUserLon> baseUserLons;

    public Set<BaseUserLon> getBaseUserLons(){
        return this.baseUserLons;
    }
    
    public void setBaseUserLons(Set<BaseUserLon> baseUserLons){
        this.baseUserLons = baseUserLons;
    }
 


    private Set<ComercialDocumentIn> comercialDocumentIns;

    public Set<ComercialDocumentIn> getComercialDocumentIns(){
        return this.comercialDocumentIns;
    }
    
    public void setComercialDocumentIns(Set<ComercialDocumentIn> comercialDocumentIns){
        this.comercialDocumentIns = comercialDocumentIns;
    }
 


    private Set<ComercialDocumentOut> comercialDocumentOuts;

    public Set<ComercialDocumentOut> getComercialDocumentOuts(){
        return this.comercialDocumentOuts;
    }
    
    public void setComercialDocumentOuts(Set<ComercialDocumentOut> comercialDocumentOuts){
        this.comercialDocumentOuts = comercialDocumentOuts;
    }
 


    private Set<UserRole> userRoles;

    public Set<UserRole> getUserRoles(){
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles){
        this.userRoles = userRoles;
    }
 


    private Set<UserThirdPerson> userThirdPersons;

    public Set<UserThirdPerson> getUserThirdPersons(){
        return this.userThirdPersons;
    }
    
    public void setUserThirdPersons(Set<UserThirdPerson> userThirdPersons){
        this.userThirdPersons = userThirdPersons;
    }
 
      
}
        