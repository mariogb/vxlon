
package org.lonpe.mapstore;            

            

import org.lonpe.model.ComercialDocumentOut;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ComercialDocumentOutMapStore extends AbstractDCMapStore<ComercialDocumentOut>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ComercialDocumentOutMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ComercialDocumentOut comercialDocumentOut) {
        dBLon0.store00("comercialDocumentOut", comercialDocumentOut);
    }
    
    @Override
    public ComercialDocumentOut load(String key) {
        return (ComercialDocumentOut)dBLon1.load00("comercialDocumentOut", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("comercialDocumentOut");
    }

    @Override
    public Map<String, ComercialDocumentOut> loadAll(Collection<String> keys) {
        Map<String, ComercialDocumentOut> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        