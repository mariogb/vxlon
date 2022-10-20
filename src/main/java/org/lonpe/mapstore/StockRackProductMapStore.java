
package org.lonpe.mapstore;            

            

import org.lonpe.model.StockRackProduct;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class StockRackProductMapStore extends AbstractDCMapStore<StockRackProduct>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public StockRackProductMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, StockRackProduct stockRackProduct) {
        dBLon0.store00("stockRackProduct", stockRackProduct);
    }
    
    @Override
    public StockRackProduct load(String key) {
        return (StockRackProduct)dBLon1.load00("stockRackProduct", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("stockRackProduct");
    }

    @Override
    public Map<String, StockRackProduct> loadAll(Collection<String> keys) {
        Map<String, StockRackProduct> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        