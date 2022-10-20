
package org.lonpe.mapstore;            

            

import org.lonpe.model.Role;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class RoleMapStore extends AbstractDCMapStore<Role>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public RoleMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Role role) {
        dBLon0.store00("role", role);
    }
    
    @Override
    public Role load(String key) {
        return (Role)dBLon1.load00("role", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("role");
    }

    @Override
    public Map<String, Role> loadAll(Collection<String> keys) {
        Map<String, Role> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        