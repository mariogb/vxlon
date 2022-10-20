
package org.lonpe.mapstore;            

            

import org.lonpe.model.ComercialDocumentTypeOut;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ComercialDocumentTypeOutMapStore extends AbstractDCMapStore<ComercialDocumentTypeOut>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ComercialDocumentTypeOutMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ComercialDocumentTypeOut comercialDocumentTypeOut) {
        dBLon0.store00("comercialDocumentTypeOut", comercialDocumentTypeOut);
    }
    
    @Override
    public ComercialDocumentTypeOut load(String key) {
        return (ComercialDocumentTypeOut)dBLon1.load00("comercialDocumentTypeOut", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("comercialDocumentTypeOut");
    }

    @Override
    public Map<String, ComercialDocumentTypeOut> loadAll(Collection<String> keys) {
        Map<String, ComercialDocumentTypeOut> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        