
package org.lonpe.mapstore;            

            

import org.lonpe.model.MeUsrInterface;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class MeUsrInterfaceMapStore extends AbstractDCMapStore<MeUsrInterface>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public MeUsrInterfaceMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, MeUsrInterface meUsrInterface) {
        dBLon0.store00("meUsrInterface", meUsrInterface);
    }
    
    @Override
    public MeUsrInterface load(String key) {
        return (MeUsrInterface)dBLon1.load00("meUsrInterface", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("meUsrInterface");
    }

    @Override
    public Map<String, MeUsrInterface> loadAll(Collection<String> keys) {
        Map<String, MeUsrInterface> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        