
package org.lonpe.mapstore;            

            

import org.lonpe.model.UserThirdPerson;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class UserThirdPersonMapStore extends AbstractDCMapStore<UserThirdPerson>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public UserThirdPersonMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, UserThirdPerson userThirdPerson) {
        dBLon0.store00("userThirdPerson", userThirdPerson);
    }
    
    @Override
    public UserThirdPerson load(String key) {
        return (UserThirdPerson)dBLon1.load00("userThirdPerson", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("userThirdPerson");
    }

    @Override
    public Map<String, UserThirdPerson> loadAll(Collection<String> keys) {
        Map<String, UserThirdPerson> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        