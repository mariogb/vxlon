
package org.lonpe.mapstore;            

            

import org.lonpe.model.PaymentOut;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class PaymentOutMapStore extends AbstractDCMapStore<PaymentOut>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public PaymentOutMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, PaymentOut paymentOut) {
        dBLon0.store00("paymentOut", paymentOut);
    }
    
    @Override
    public PaymentOut load(String key) {
        return (PaymentOut)dBLon1.load00("paymentOut", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("paymentOut");
    }

    @Override
    public Map<String, PaymentOut> loadAll(Collection<String> keys) {
        Map<String, PaymentOut> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        