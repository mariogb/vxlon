
package org.lonpe.mapstore;            

            

import org.lonpe.model.DepartamentJob;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class DepartamentJobMapStore extends AbstractDCMapStore<DepartamentJob>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public DepartamentJobMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, DepartamentJob departamentJob) {
        dBLon0.store00("departamentJob", departamentJob);
    }
    
    @Override
    public DepartamentJob load(String key) {
        return (DepartamentJob)dBLon1.load00("departamentJob", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("departamentJob");
    }

    @Override
    public Map<String, DepartamentJob> loadAll(Collection<String> keys) {
        Map<String, DepartamentJob> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        