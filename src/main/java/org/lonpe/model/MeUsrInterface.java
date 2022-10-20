
package org.lonpe.model;            

            

         

public class MeUsrInterface extends AbstractDcLon implements IDcLon{

    public MeUsrInterface(){
    }

    
    private String dc;

    public String getDc(){
        return this.dc;
    }

    public void setDc(String dc){
        this.dc = dc;
    }        
    

    private String label;

    public String getLabel(){
        return this.label;
    }

    public void setLabel(String label){
        this.label = label;
    }        
    

    private Integer level;

    public Integer getLevel(){
        return this.level;
    }

    public void setLevel(Integer level){
        this.level = level;
    }        
    

    

    
      
}
        