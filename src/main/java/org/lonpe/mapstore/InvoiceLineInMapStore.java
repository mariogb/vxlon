
package org.lonpe.mapstore;            

            

import org.lonpe.model.InvoiceLineIn;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class InvoiceLineInMapStore extends AbstractDCMapStore<InvoiceLineIn>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public InvoiceLineInMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, InvoiceLineIn invoiceLineIn) {
        dBLon0.store00("invoiceLineIn", invoiceLineIn);
    }
    
    @Override
    public InvoiceLineIn load(String key) {
        return (InvoiceLineIn)dBLon1.load00("invoiceLineIn", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("invoiceLineIn");
    }

    @Override
    public Map<String, InvoiceLineIn> loadAll(Collection<String> keys) {
        Map<String, InvoiceLineIn> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        