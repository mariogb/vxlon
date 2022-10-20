
package org.lonpe.model;            

            
import java.util.Set;
         

public class Base extends AbstractDcLon implements IDcLon{

    public Base(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<BaseTimePeriod> baseTimePeriods;

    public Set<BaseTimePeriod> getBaseTimePeriods(){
        return this.baseTimePeriods;
    }
    
    public void setBaseTimePeriods(Set<BaseTimePeriod> baseTimePeriods){
        this.baseTimePeriods = baseTimePeriods;
    }
 


    private Set<WorkSpaceGroup> workSpaceGroups;

    public Set<WorkSpaceGroup> getWorkSpaceGroups(){
        return this.workSpaceGroups;
    }
    
    public void setWorkSpaceGroups(Set<WorkSpaceGroup> workSpaceGroups){
        this.workSpaceGroups = workSpaceGroups;
    }
 


    private Set<BaseUserLon> baseUserLons;

    public Set<BaseUserLon> getBaseUserLons(){
        return this.baseUserLons;
    }
    
    public void setBaseUserLons(Set<BaseUserLon> baseUserLons){
        this.baseUserLons = baseUserLons;
    }
 
      
}
        