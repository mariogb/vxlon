
package org.lonpe.mapstore;            

            

import org.lonpe.model.FormLonField;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class FormLonFieldMapStore extends AbstractDCMapStore<FormLonField>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public FormLonFieldMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, FormLonField formLonField) {
        dBLon0.store00("formLonField", formLonField);
    }
    
    @Override
    public FormLonField load(String key) {
        return (FormLonField)dBLon1.load00("formLonField", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("formLonField");
    }

    @Override
    public Map<String, FormLonField> loadAll(Collection<String> keys) {
        Map<String, FormLonField> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        