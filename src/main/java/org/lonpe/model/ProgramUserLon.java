
package org.lonpe.model;            

            

         

public class ProgramUserLon extends AbstractDcLon implements IDcLon{

    public ProgramUserLon(){
        super();
    }

    

    
    private Program program;

    /**
     *
     * @return program
     */  
    public Program getProgram(){
        return this.program;
    }

    /**
     *
     * @param program
     */       
    public void setProgram(Program program){
        this.program = program;
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
        