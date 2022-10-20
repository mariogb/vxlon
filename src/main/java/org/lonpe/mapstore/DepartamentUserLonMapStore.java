
package org.lonpe.mapstore;            

            

import org.lonpe.model.DepartamentUserLon;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class DepartamentUserLonMapStore extends AbstractDCMapStore<DepartamentUserLon>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public DepartamentUserLonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, DepartamentUserLon departamentUserLon) {
        dBLon0.store00("departamentUserLon", departamentUserLon);
    }
    
    @Override
    public DepartamentUserLon load(String key) {
        return (DepartamentUserLon)dBLon1.load00("departamentUserLon", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("departamentUserLon");
    }

    @Override
    public Map<String, DepartamentUserLon> loadAll(Collection<String> keys) {
        Map<String, DepartamentUserLon> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        