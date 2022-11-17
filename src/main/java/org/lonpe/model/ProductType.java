
package org.lonpe.model;            

            
import java.util.Set;
         

public class ProductType extends AbstractDcLon implements IDcLon{

    public ProductType(){
        super();
    }

    
    private Boolean afectStock;

    /**
     *
     * @return afectStock
     */    
    public Boolean getAfectStock(){
        return this.afectStock;
    }

    /**
     *
     * @param afectStock
     */    
    public void setAfectStock(Boolean afectStock){
        this.afectStock = afectStock;
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

    private String fastKey;

    /**
     *
     * @return fastKey
     */    
    public String getFastKey(){
        return this.fastKey;
    }

    /**
     *
     * @param fastKey
     */    
    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
    }           

    private Boolean isService;

    /**
     *
     * @return isService
     */    
    public Boolean getIsService(){
        return this.isService;
    }

    /**
     *
     * @param isService
     */    
    public void setIsService(Boolean isService){
        this.isService = isService;
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

    private Boolean taxable;

    /**
     *
     * @return taxable
     */    
    public Boolean getTaxable(){
        return this.taxable;
    }

    /**
     *
     * @param taxable
     */    
    public void setTaxable(Boolean taxable){
        this.taxable = taxable;
    }           

    private Boolean withSerialNumber;

    /**
     *
     * @return withSerialNumber
     */    
    public Boolean getWithSerialNumber(){
        return this.withSerialNumber;
    }

    /**
     *
     * @param withSerialNumber
     */    
    public void setWithSerialNumber(Boolean withSerialNumber){
        this.withSerialNumber = withSerialNumber;
    }           

    

    
    private Set<Product> products;

    /**
     *
     * @return products
     */     
    public Set<Product> getProducts(){
        return this.products;
    }
    
    /**
     *
     * @param products
     */       
    public void setProducts(Set<Product> products){
        this.products = products;
    }
 
      
}
        