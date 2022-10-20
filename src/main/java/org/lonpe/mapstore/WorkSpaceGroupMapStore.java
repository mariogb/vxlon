
package org.lonpe.mapstore;            

            

import org.lonpe.model.WorkSpaceGroup;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class WorkSpaceGroupMapStore extends AbstractDCMapStore<WorkSpaceGroup>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public WorkSpaceGroupMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, WorkSpaceGroup workSpaceGroup) {
        dBLon0.store00("workSpaceGroup", workSpaceGroup);
    }
    
    @Override
    public WorkSpaceGroup load(String key) {
        return (WorkSpaceGroup)dBLon1.load00("workSpaceGroup", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("workSpaceGroup");
    }

    @Override
    public Map<String, WorkSpaceGroup> loadAll(Collection<String> keys) {
        Map<String, WorkSpaceGroup> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        