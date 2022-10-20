
package org.lonpe.mapstore;            

            

import org.lonpe.model.WorkSpace;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class WorkSpaceMapStore extends AbstractDCMapStore<WorkSpace>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public WorkSpaceMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, WorkSpace workSpace) {
        dBLon0.store00("workSpace", workSpace);
    }
    
    @Override
    public WorkSpace load(String key) {
        return (WorkSpace)dBLon1.load00("workSpace", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("workSpace");
    }

    @Override
    public Map<String, WorkSpace> loadAll(Collection<String> keys) {
        Map<String, WorkSpace> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        