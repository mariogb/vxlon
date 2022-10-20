
package org.lonpe.mapstore;            

            

import org.lonpe.model.Program;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class ProgramMapStore extends AbstractDCMapStore<Program>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public ProgramMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Program program) {
        dBLon0.store00("program", program);
    }
    
    @Override
    public Program load(String key) {
        return (Program)dBLon1.load00("program", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("program");
    }

    @Override
    public Map<String, Program> loadAll(Collection<String> keys) {
        Map<String, Program> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        