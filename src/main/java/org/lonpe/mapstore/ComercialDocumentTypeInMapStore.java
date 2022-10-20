
package org.lonpe.mapstore;            

            

import org.lonpe.model.ComercialDocumentTypeIn;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ComercialDocumentTypeInMapStore extends AbstractDCMapStore<ComercialDocumentTypeIn>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ComercialDocumentTypeInMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ComercialDocumentTypeIn comercialDocumentTypeIn) {
        dBLon0.store00("comercialDocumentTypeIn", comercialDocumentTypeIn);
    }
    
    @Override
    public ComercialDocumentTypeIn load(String key) {
        return (ComercialDocumentTypeIn)dBLon1.load00("comercialDocumentTypeIn", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("comercialDocumentTypeIn");
    }

    @Override
    public Map<String, ComercialDocumentTypeIn> loadAll(Collection<String> keys) {
        Map<String, ComercialDocumentTypeIn> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        