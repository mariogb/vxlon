
package org.lonpe.mapstore;            

            

import org.lonpe.model.StockRack;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class StockRackMapStore extends AbstractDCMapStore<StockRack>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public StockRackMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, StockRack stockRack) {
        dBLon0.store00("stockRack", stockRack);
    }
    
    @Override
    public StockRack load(String key) {
        return (StockRack)dBLon1.load00("stockRack", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("stockRack");
    }

    @Override
    public Map<String, StockRack> loadAll(Collection<String> keys) {
        Map<String, StockRack> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        