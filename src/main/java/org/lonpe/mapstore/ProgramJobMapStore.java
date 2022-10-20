
package org.lonpe.mapstore;            

            

import org.lonpe.model.ProgramJob;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ProgramJobMapStore extends AbstractDCMapStore<ProgramJob>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ProgramJobMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ProgramJob programJob) {
        dBLon0.store00("programJob", programJob);
    }
    
    @Override
    public ProgramJob load(String key) {
        return (ProgramJob)dBLon1.load00("programJob", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("programJob");
    }

    @Override
    public Map<String, ProgramJob> loadAll(Collection<String> keys) {
        Map<String, ProgramJob> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        