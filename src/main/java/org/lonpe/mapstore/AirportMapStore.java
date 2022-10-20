
package org.lonpe.mapstore;            

            

import org.lonpe.model.Airport;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class AirportMapStore extends AbstractDCMapStore<Airport>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public AirportMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Airport airport) {
        dBLon0.store00("airport", airport);
    }
    
    @Override
    public Airport load(String key) {
        return (Airport)dBLon1.load00("airport", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("airport");
    }

    @Override
    public Map<String, Airport> loadAll(Collection<String> keys) {
        Map<String, Airport> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        