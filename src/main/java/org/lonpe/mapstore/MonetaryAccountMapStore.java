
package org.lonpe.mapstore;            

            

import org.lonpe.model.MonetaryAccount;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class MonetaryAccountMapStore extends AbstractDCMapStore<MonetaryAccount>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public MonetaryAccountMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, MonetaryAccount monetaryAccount) {
        dBLon0.store00("monetaryAccount", monetaryAccount);
    }
    
    @Override
    public MonetaryAccount load(String key) {
        return (MonetaryAccount)dBLon1.load00("monetaryAccount", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("monetaryAccount");
    }

    @Override
    public Map<String, MonetaryAccount> loadAll(Collection<String> keys) {
        Map<String, MonetaryAccount> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        