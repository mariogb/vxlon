
package org.lonpe.mapstore;            

            

import org.lonpe.model.Fligth;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class FligthMapStore extends AbstractDCMapStore<Fligth>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public FligthMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Fligth fligth) {
        dBLon0.store00("fligth", fligth);
    }
    
    @Override
    public Fligth load(String key) {
        return (Fligth)dBLon1.load00("fligth", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("fligth");
    }

    @Override
    public Map<String, Fligth> loadAll(Collection<String> keys) {
        Map<String, Fligth> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        