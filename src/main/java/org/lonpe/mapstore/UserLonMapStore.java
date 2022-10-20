
package org.lonpe.mapstore;            

            

import org.lonpe.model.UserLon;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class UserLonMapStore extends AbstractDCMapStore<UserLon>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public UserLonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, UserLon userLon) {
        dBLon0.store00("userLon", userLon);
    }
    
    @Override
    public UserLon load(String key) {
        return (UserLon)dBLon1.load00("userLon", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("userLon");
    }

    @Override
    public Map<String, UserLon> loadAll(Collection<String> keys) {
        Map<String, UserLon> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        