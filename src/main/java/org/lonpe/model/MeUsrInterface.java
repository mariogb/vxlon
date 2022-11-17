
package org.lonpe.model;            

            

         

public class MeUsrInterface extends AbstractDcLon implements IDcLon{

    public MeUsrInterface(){
        super();
    }

    
    private String dc;

    /**
     *
     * @return dc
     */    
    public String getDc(){
        return this.dc;
    }

    /**
     *
     * @param dc
     */    
    public void setDc(String dc){
        this.dc = dc;
    }           

    private String label;

    /**
     *
     * @return label
     */    
    public String getLabel(){
        return this.label;
    }

    /**
     *
     * @param label
     */    
    public void setLabel(String label){
        this.label = label;
    }           

    private Integer level;

    /**
     *
     * @return level
     */    
    public Integer getLevel(){
        return this.level;
    }

    /**
     *
     * @param level
     */    
    public void setLevel(Integer level){
        this.level = level;
    }           

    

    
      
}
        