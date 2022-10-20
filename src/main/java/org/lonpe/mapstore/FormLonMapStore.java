
package org.lonpe.mapstore;            

            

import org.lonpe.model.FormLon;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class FormLonMapStore extends AbstractDCMapStore<FormLon>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public FormLonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, FormLon formLon) {
        dBLon0.store00("formLon", formLon);
    }
    
    @Override
    public FormLon load(String key) {
        return (FormLon)dBLon1.load00("formLon", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("formLon");
    }

    @Override
    public Map<String, FormLon> loadAll(Collection<String> keys) {
        Map<String, FormLon> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        