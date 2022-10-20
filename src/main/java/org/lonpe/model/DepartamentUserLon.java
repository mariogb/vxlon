
package org.lonpe.model;            

            

         

public class DepartamentUserLon extends AbstractDcLon implements IDcLon{

    public DepartamentUserLon(){
    }

    

    
    private Departament departament;

    public Departament getDepartament(){
        return this.departament;
    }
    
    public void setDepartament(Departament departament){
        this.departament = departament;
    }
 

    private UserLon userLon;

    public UserLon getUserLon(){
        return this.userLon;
    }
    
    public void setUserLon(UserLon userLon){
        this.userLon = userLon;
    }
 

    
      
}
        