
package org.lonpe.model;            

            

         

public class FormLonField extends AbstractDcLon implements IDcLon{

    public FormLonField(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private FormLon formLon;

    public FormLon getFormLon(){
        return this.formLon;
    }
    
    public void setFormLon(FormLon formLon){
        this.formLon = formLon;
    }
 

    
      
}
        