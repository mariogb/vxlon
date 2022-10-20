
package org.lonpe.mapstore;            

            

import org.lonpe.model.TimePeriod;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class TimePeriodMapStore extends AbstractDCMapStore<TimePeriod>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public TimePeriodMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, TimePeriod timePeriod) {
        dBLon0.store00("timePeriod", timePeriod);
    }
    
    @Override
    public TimePeriod load(String key) {
        return (TimePeriod)dBLon1.load00("timePeriod", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("timePeriod");
    }

    @Override
    public Map<String, TimePeriod> loadAll(Collection<String> keys) {
        Map<String, TimePeriod> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        