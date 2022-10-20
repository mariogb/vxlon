
package org.lonpe.mapstore;            

            

import org.lonpe.model.EntityPermisionRole;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class EntityPermisionRoleMapStore extends AbstractDCMapStore<EntityPermisionRole>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public EntityPermisionRoleMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, EntityPermisionRole entityPermisionRole) {
        dBLon0.store00("entityPermisionRole", entityPermisionRole);
    }
    
    @Override
    public EntityPermisionRole load(String key) {
        return (EntityPermisionRole)dBLon1.load00("entityPermisionRole", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("entityPermisionRole");
    }

    @Override
    public Map<String, EntityPermisionRole> loadAll(Collection<String> keys) {
        Map<String, EntityPermisionRole> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        