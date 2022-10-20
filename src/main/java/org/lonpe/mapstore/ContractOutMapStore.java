
package org.lonpe.mapstore;            

            

import org.lonpe.model.ContractOut;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ContractOutMapStore extends AbstractDCMapStore<ContractOut>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ContractOutMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ContractOut contractOut) {
        dBLon0.store00("contractOut", contractOut);
    }
    
    @Override
    public ContractOut load(String key) {
        return (ContractOut)dBLon1.load00("contractOut", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("contractOut");
    }

    @Override
    public Map<String, ContractOut> loadAll(Collection<String> keys) {
        Map<String, ContractOut> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        