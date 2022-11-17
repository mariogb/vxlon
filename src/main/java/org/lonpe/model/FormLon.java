
package org.lonpe.model;            

            
import java.util.Set;
         

public class FormLon extends AbstractDcLon implements IDcLon{

    public FormLon(){
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

    

    
    private Set<FormLonField> formLonFields;

    /**
     *
     * @return formLonFields
     */     
    public Set<FormLonField> getFormLonFields(){
        return this.formLonFields;
    }
    
    /**
     *
     * @param formLonFields
     */       
    public void setFormLonFields(Set<FormLonField> formLonFields){
        this.formLonFields = formLonFields;
    }
 
      
}
        