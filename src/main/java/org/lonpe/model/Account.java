
package org.lonpe.model;            

            

         

public class Account extends AbstractDcLon implements IDcLon{

    public Account(){
        super();
    }

    
    private String description;

    /**
     *
     * @return description
     */    
    public String getDescription(){
        return this.description;
    }

    /**
     *
     * @param description
     */    
    public void setDescription(String description){
        this.description = description;
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

    private String type;

    /**
     *
     * @return type
     */    
    public String getType(){
        return this.type;
    }

    /**
     *
     * @param type
     */    
    public void setType(String type){
        this.type = type;
    }           

    

    
      
}
        