
package org.lonpe.model;            

            
import java.util.Set;
         

public class StockRack extends AbstractDcLon implements IDcLon{

    public StockRack(){
    }

    
    private String fastKey;

    public String getFastKey(){
        return this.fastKey;
    }

    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
    }        
    

    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    
    private WorkSpace workSpace;

    public WorkSpace getWorkSpace(){
        return this.workSpace;
    }
    
    public void setWorkSpace(WorkSpace workSpace){
        this.workSpace = workSpace;
    }
 

    
    private Set<StockRackProduct> stockRackProducts;

    public Set<StockRackProduct> getStockRackProducts(){
        return this.stockRackProducts;
    }
    
    public void setStockRackProducts(Set<StockRackProduct> stockRackProducts){
        this.stockRackProducts = stockRackProducts;
    }
 
      
}
        