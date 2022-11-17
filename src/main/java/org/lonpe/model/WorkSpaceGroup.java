
package org.lonpe.model;            

            
import java.util.Set;
         

public class WorkSpaceGroup extends AbstractDcLon implements IDcLon{

    public WorkSpaceGroup(){
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

    
    private Base base;

    /**
     *
     * @return base
     */  
    public Base getBase(){
        return this.base;
    }

    /**
     *
     * @param base
     */       
    public void setBase(Base base){
        this.base = base;
    }

    
    private Set<WorkSpace> workSpaces;

    /**
     *
     * @return workSpaces
     */     
    public Set<WorkSpace> getWorkSpaces(){
        return this.workSpaces;
    }
    
    /**
     *
     * @param workSpaces
     */       
    public void setWorkSpaces(Set<WorkSpace> workSpaces){
        this.workSpaces = workSpaces;
    }
 
      
}
        