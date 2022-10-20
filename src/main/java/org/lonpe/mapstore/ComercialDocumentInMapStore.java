
package org.lonpe.mapstore;            

            

import org.lonpe.model.ComercialDocumentIn;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ComercialDocumentInMapStore extends AbstractDCMapStore<ComercialDocumentIn>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ComercialDocumentInMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ComercialDocumentIn comercialDocumentIn) {
        dBLon0.store00("comercialDocumentIn", comercialDocumentIn);
    }
    
    @Override
    public ComercialDocumentIn load(String key) {
        return (ComercialDocumentIn)dBLon1.load00("comercialDocumentIn", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("comercialDocumentIn");
    }

    @Override
    public Map<String, ComercialDocumentIn> loadAll(Collection<String> keys) {
        Map<String, ComercialDocumentIn> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        