
package org.lonpe.mapstore;            

            

import org.lonpe.model.FligthInstance;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class FligthInstanceMapStore extends AbstractDCMapStore<FligthInstance>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public FligthInstanceMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, FligthInstance fligthInstance) {
        dBLon0.store00("fligthInstance", fligthInstance);
    }
    
    @Override
    public FligthInstance load(String key) {
        return (FligthInstance)dBLon1.load00("fligthInstance", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("fligthInstance");
    }

    @Override
    public Map<String, FligthInstance> loadAll(Collection<String> keys) {
        Map<String, FligthInstance> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        