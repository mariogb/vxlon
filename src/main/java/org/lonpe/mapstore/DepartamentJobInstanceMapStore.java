
package org.lonpe.mapstore;            

            

import org.lonpe.model.DepartamentJobInstance;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class DepartamentJobInstanceMapStore extends AbstractDCMapStore<DepartamentJobInstance>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public DepartamentJobInstanceMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, DepartamentJobInstance departamentJobInstance) {
        dBLon0.store00("departamentJobInstance", departamentJobInstance);
    }
    
    @Override
    public DepartamentJobInstance load(String key) {
        return (DepartamentJobInstance)dBLon1.load00("departamentJobInstance", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("departamentJobInstance");
    }

    @Override
    public Map<String, DepartamentJobInstance> loadAll(Collection<String> keys) {
        Map<String, DepartamentJobInstance> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        