
package org.lonpe.mapstore;            

            

import org.lonpe.model.DepartamentBaseTimePeriod;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class DepartamentBaseTimePeriodMapStore extends AbstractDCMapStore<DepartamentBaseTimePeriod>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public DepartamentBaseTimePeriodMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, DepartamentBaseTimePeriod departamentBaseTimePeriod) {
        dBLon0.store00("departamentBaseTimePeriod", departamentBaseTimePeriod);
    }
    
    @Override
    public DepartamentBaseTimePeriod load(String key) {
        return (DepartamentBaseTimePeriod)dBLon1.load00("departamentBaseTimePeriod", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("departamentBaseTimePeriod");
    }

    @Override
    public Map<String, DepartamentBaseTimePeriod> loadAll(Collection<String> keys) {
        Map<String, DepartamentBaseTimePeriod> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        