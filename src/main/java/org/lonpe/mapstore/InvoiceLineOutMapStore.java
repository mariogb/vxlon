
package org.lonpe.mapstore;            

            

import org.lonpe.model.InvoiceLineOut;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class InvoiceLineOutMapStore extends AbstractDCMapStore<InvoiceLineOut>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public InvoiceLineOutMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, InvoiceLineOut invoiceLineOut) {
        dBLon0.store00("invoiceLineOut", invoiceLineOut);
    }
    
    @Override
    public InvoiceLineOut load(String key) {
        return (InvoiceLineOut)dBLon1.load00("invoiceLineOut", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("invoiceLineOut");
    }

    @Override
    public Map<String, InvoiceLineOut> loadAll(Collection<String> keys) {
        Map<String, InvoiceLineOut> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        