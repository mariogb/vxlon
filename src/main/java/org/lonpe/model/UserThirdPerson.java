
package org.lonpe.model;            

            

         

public class UserThirdPerson extends AbstractDcLon implements IDcLon{

    public UserThirdPerson(){
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

    private ThirdPerson thirdPerson;

    /**
     *
     * @return thirdPerson
     */  
    public ThirdPerson getThirdPerson(){
        return this.thirdPerson;
    }

    /**
     *
     * @param thirdPerson
     */       
    public void setThirdPerson(ThirdPerson thirdPerson){
        this.thirdPerson = thirdPerson;
    }

    
      
}
        