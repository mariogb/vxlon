
package org.lonpe.model;            

            
import java.util.Set;
         

public class StockRack extends AbstractDcLon implements IDcLon{

    public StockRack(){
        super();
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

    
    private WorkSpace workSpace;

    /**
     *
     * @return workSpace
     */  
    public WorkSpace getWorkSpace(){
        return this.workSpace;
    }

    /**
     *
     * @param workSpace
     */       
    public void setWorkSpace(WorkSpace workSpace){
        this.workSpace = workSpace;
    }

    
    private Set<StockRackProduct> stockRackProducts;

    /**
     *
     * @return stockRackProducts
     */     
    public Set<StockRackProduct> getStockRackProducts(){
        return this.stockRackProducts;
    }
    
    /**
     *
     * @param stockRackProducts
     */       
    public void setStockRackProducts(Set<StockRackProduct> stockRackProducts){
        this.stockRackProducts = stockRackProducts;
    }
 
      
}
        