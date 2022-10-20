
package org.lonpe.mapstore;            

            

import org.lonpe.model.BaseUserLon;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class BaseUserLonMapStore extends AbstractDCMapStore<BaseUserLon>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public BaseUserLonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, BaseUserLon baseUserLon) {
        dBLon0.store00("baseUserLon", baseUserLon);
    }
    
    @Override
    public BaseUserLon load(String key) {
        return (BaseUserLon)dBLon1.load00("baseUserLon", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("baseUserLon");
    }

    @Override
    public Map<String, BaseUserLon> loadAll(Collection<String> keys) {
        Map<String, BaseUserLon> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        