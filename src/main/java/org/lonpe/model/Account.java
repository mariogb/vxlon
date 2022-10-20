
package org.lonpe.model;            

            

         

public class Account extends AbstractDcLon implements IDcLon{

    public Account(){
    }

    
    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private String type;

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }        
    

    

    
      
}
        