
package org.lonpe.mapstore;            

            

import org.lonpe.model.UserRole;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class UserRoleMapStore extends AbstractDCMapStore<UserRole>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public UserRoleMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, UserRole userRole) {
        dBLon0.store00("userRole", userRole);
    }
    
    @Override
    public UserRole load(String key) {
        return (UserRole)dBLon1.load00("userRole", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("userRole");
    }

    @Override
    public Map<String, UserRole> loadAll(Collection<String> keys) {
        Map<String, UserRole> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        