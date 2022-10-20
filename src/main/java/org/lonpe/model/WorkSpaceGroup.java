
package org.lonpe.model;            

            
import java.util.Set;
         

public class WorkSpaceGroup extends AbstractDcLon implements IDcLon{

    public WorkSpaceGroup(){
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
    

    
    private Base base;

    public Base getBase(){
        return this.base;
    }
    
    public void setBase(Base base){
        this.base = base;
    }
 

    
    private Set<WorkSpace> workSpaces;

    public Set<WorkSpace> getWorkSpaces(){
        return this.workSpaces;
    }
    
    public void setWorkSpaces(Set<WorkSpace> workSpaces){
        this.workSpaces = workSpaces;
    }
 
      
}
        