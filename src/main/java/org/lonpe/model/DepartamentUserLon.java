
package org.lonpe.model;            

            

         

public class DepartamentUserLon extends AbstractDcLon implements IDcLon{

    public DepartamentUserLon(){
        super();
    }

    

    
    private Departament departament;

    /**
     *
     * @return departament
     */  
    public Departament getDepartament(){
        return this.departament;
    }

    /**
     *
     * @param departament
     */       
    public void setDepartament(Departament departament){
        this.departament = departament;
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

    
      
}
        