
package org.lonpe.model;            

            
import java.util.Set;
         

public class ProductType extends AbstractDcLon implements IDcLon{

    public ProductType(){
    }

    
    private Boolean afectStock;

    public Boolean getAfectStock(){
        return this.afectStock;
    }

    public void setAfectStock(Boolean afectStock){
        this.afectStock = afectStock;
    }        
    

    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }        
    

    private String fastKey;

    public String getFastKey(){
        return this.fastKey;
    }

    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
    }        
    

    private Boolean isService;

    public Boolean getIsService(){
        return this.isService;
    }

    public void setIsService(Boolean isService){
        this.isService = isService;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    private Boolean taxable;

    public Boolean getTaxable(){
        return this.taxable;
    }

    public void setTaxable(Boolean taxable){
        this.taxable = taxable;
    }        
    

    private Boolean withSerialNumber;

    public Boolean getWithSerialNumber(){
        return this.withSerialNumber;
    }

    public void setWithSerialNumber(Boolean withSerialNumber){
        this.withSerialNumber = withSerialNumber;
    }        
    

    

    
    private Set<Product> products;

    public Set<Product> getProducts(){
        return this.products;
    }
    
    public void setProducts(Set<Product> products){
        this.products = products;
    }
 
      
}
        