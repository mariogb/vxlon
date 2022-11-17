
package org.lonpe.model;            

            
import java.util.Set;
         

public class Base extends AbstractDcLon implements IDcLon{

    public Base(){
        super();
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

    

    
    private Set<BaseTimePeriod> baseTimePeriods;

    /**
     *
     * @return baseTimePeriods
     */     
    public Set<BaseTimePeriod> getBaseTimePeriods(){
        return this.baseTimePeriods;
    }
    
    /**
     *
     * @param baseTimePeriods
     */       
    public void setBaseTimePeriods(Set<BaseTimePeriod> baseTimePeriods){
        this.baseTimePeriods = baseTimePeriods;
    }
 

    private Set<WorkSpaceGroup> workSpaceGroups;

    /**
     *
     * @return workSpaceGroups
     */     
    public Set<WorkSpaceGroup> getWorkSpaceGroups(){
        return this.workSpaceGroups;
    }
    
    /**
     *
     * @param workSpaceGroups
     */       
    public void setWorkSpaceGroups(Set<WorkSpaceGroup> workSpaceGroups){
        this.workSpaceGroups = workSpaceGroups;
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
 
      
}
        