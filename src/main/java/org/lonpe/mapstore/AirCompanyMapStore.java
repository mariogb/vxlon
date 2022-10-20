
package org.lonpe.mapstore;            

            

import org.lonpe.model.AirCompany;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class AirCompanyMapStore extends AbstractDCMapStore<AirCompany>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public AirCompanyMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, AirCompany airCompany) {
        dBLon0.store00("airCompany", airCompany);
    }
    
    @Override
    public AirCompany load(String key) {
        return (AirCompany)dBLon1.load00("airCompany", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("airCompany");
    }

    @Override
    public Map<String, AirCompany> loadAll(Collection<String> keys) {
        Map<String, AirCompany> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        