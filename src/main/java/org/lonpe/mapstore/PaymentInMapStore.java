
package org.lonpe.mapstore;            

            

import org.lonpe.model.PaymentIn;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class PaymentInMapStore extends AbstractDCMapStore<PaymentIn>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public PaymentInMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, PaymentIn paymentIn) {
        dBLon0.store00("paymentIn", paymentIn);
    }
    
    @Override
    public PaymentIn load(String key) {
        return (PaymentIn)dBLon1.load00("paymentIn", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("paymentIn");
    }

    @Override
    public Map<String, PaymentIn> loadAll(Collection<String> keys) {
        Map<String, PaymentIn> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        