
package org.lonpe.mapstore;            

            

import org.lonpe.model.Departament;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class DepartamentMapStore extends AbstractDCMapStore<Departament>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public DepartamentMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Departament departament) {
        dBLon0.store00("departament", departament);
    }
    
    @Override
    public Departament load(String key) {
        return (Departament)dBLon1.load00("departament", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("departament");
    }

    @Override
    public Map<String, Departament> loadAll(Collection<String> keys) {
        Map<String, Departament> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        