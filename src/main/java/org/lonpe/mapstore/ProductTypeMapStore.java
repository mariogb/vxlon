
package org.lonpe.mapstore;            

            

import org.lonpe.model.ProductType;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ProductTypeMapStore extends AbstractDCMapStore<ProductType>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ProductTypeMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ProductType productType) {
        dBLon0.store00("productType", productType);
    }
    
    @Override
    public ProductType load(String key) {
        return (ProductType)dBLon1.load00("productType", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("productType");
    }

    @Override
    public Map<String, ProductType> loadAll(Collection<String> keys) {
        Map<String, ProductType> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        