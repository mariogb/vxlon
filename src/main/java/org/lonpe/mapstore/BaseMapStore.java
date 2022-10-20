
package org.lonpe.mapstore;            

            

import org.lonpe.model.Base;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class BaseMapStore extends AbstractDCMapStore<Base>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public BaseMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Base base) {
        dBLon0.store00("base", base);
    }
    
    @Override
    public Base load(String key) {
        return (Base)dBLon1.load00("base", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("base");
    }

    @Override
    public Map<String, Base> loadAll(Collection<String> keys) {
        Map<String, Base> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        