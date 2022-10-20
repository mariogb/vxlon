
package org.lonpe.mapstore;            

            

import org.lonpe.model.ThirdPerson;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ThirdPersonMapStore extends AbstractDCMapStore<ThirdPerson>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ThirdPersonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ThirdPerson thirdPerson) {
        dBLon0.store00("thirdPerson", thirdPerson);
    }
    
    @Override
    public ThirdPerson load(String key) {
        return (ThirdPerson)dBLon1.load00("thirdPerson", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("thirdPerson");
    }

    @Override
    public Map<String, ThirdPerson> loadAll(Collection<String> keys) {
        Map<String, ThirdPerson> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        