
package org.lonpe.model;            

            
import java.util.Set;
         

public class FormLon extends AbstractDcLon implements IDcLon{

    public FormLon(){
    }

    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    private Set<FormLonField> formLonFields;

    public Set<FormLonField> getFormLonFields(){
        return this.formLonFields;
    }
    
    public void setFormLonFields(Set<FormLonField> formLonFields){
        this.formLonFields = formLonFields;
    }
 
      
}
        