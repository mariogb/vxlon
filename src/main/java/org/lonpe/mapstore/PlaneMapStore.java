
package org.lonpe.mapstore;            

            

import org.lonpe.model.Plane;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class PlaneMapStore extends AbstractDCMapStore<Plane>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public PlaneMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Plane plane) {
        dBLon0.store00("plane", plane);
    }
    
    @Override
    public Plane load(String key) {
        return (Plane)dBLon1.load00("plane", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("plane");
    }

    @Override
    public Map<String, Plane> loadAll(Collection<String> keys) {
        Map<String, Plane> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        