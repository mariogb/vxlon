
package org.lonpe.model;            

            

         

public class ProgramUserLon extends AbstractDcLon implements IDcLon{

    public ProgramUserLon(){
    }

    

    
    private Program program;

    public Program getProgram(){
        return this.program;
    }
    
    public void setProgram(Program program){
        this.program = program;
    }
 

    private UserLon userLon;

    public UserLon getUserLon(){
        return this.userLon;
    }
    
    public void setUserLon(UserLon userLon){
        this.userLon = userLon;
    }
 

    
      
}
        