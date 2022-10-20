
package org.lonpe.mapstore;            

            

import org.lonpe.model.Appointment;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class AppointmentMapStore extends AbstractDCMapStore<Appointment>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public AppointmentMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, Appointment appointment) {
        dBLon0.store00("appointment", appointment);
    }
    
    @Override
    public Appointment load(String key) {
        return (Appointment)dBLon1.load00("appointment", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("appointment");
    }

    @Override
    public Map<String, Appointment> loadAll(Collection<String> keys) {
        Map<String, Appointment> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        