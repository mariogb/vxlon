
package org.lonpe.model;            

            

         

public class FormLonField extends AbstractDcLon implements IDcLon{

    public FormLonField(){
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

    
    private FormLon formLon;

    /**
     *
     * @return formLon
     */  
    public FormLon getFormLon(){
        return this.formLon;
    }

    /**
     *
     * @param formLon
     */       
    public void setFormLon(FormLon formLon){
        this.formLon = formLon;
    }

    
      
}
        