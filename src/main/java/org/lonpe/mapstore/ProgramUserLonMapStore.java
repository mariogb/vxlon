
package org.lonpe.mapstore;            

            

import org.lonpe.model.ProgramUserLon;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ProgramUserLonMapStore extends AbstractDCMapStore<ProgramUserLon>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ProgramUserLonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ProgramUserLon programUserLon) {
        dBLon0.store00("programUserLon", programUserLon);
    }
    
    @Override
    public ProgramUserLon load(String key) {
        return (ProgramUserLon)dBLon1.load00("programUserLon", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("programUserLon");
    }

    @Override
    public Map<String, ProgramUserLon> loadAll(Collection<String> keys) {
        Map<String, ProgramUserLon> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        