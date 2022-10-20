
package org.lonpe.mapstore;            

            

import org.lonpe.model.ProgramBaseTimePeriod;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ProgramBaseTimePeriodMapStore extends AbstractDCMapStore<ProgramBaseTimePeriod>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ProgramBaseTimePeriodMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, ProgramBaseTimePeriod programBaseTimePeriod) {
        dBLon0.store00("programBaseTimePeriod", programBaseTimePeriod);
    }
    
    @Override
    public ProgramBaseTimePeriod load(String key) {
        return (ProgramBaseTimePeriod)dBLon1.load00("programBaseTimePeriod", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("programBaseTimePeriod");
    }

    @Override
    public Map<String, ProgramBaseTimePeriod> loadAll(Collection<String> keys) {
        Map<String, ProgramBaseTimePeriod> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        