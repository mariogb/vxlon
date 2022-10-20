
package org.lonpe.mapstore;            

            

import org.lonpe.model.Account;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class AccountMapStore extends AbstractDCMapStore<Account>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public AccountMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Account account) {
        dBLon0.store00("account", account);
    }
    
    @Override
    public Account load(String key) {
        return (Account)dBLon1.load00("account", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("account");
    }

    @Override
    public Map<String, Account> loadAll(Collection<String> keys) {
        Map<String, Account> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        