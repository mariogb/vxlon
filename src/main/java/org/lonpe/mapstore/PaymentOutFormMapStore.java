
package org.lonpe.mapstore;            

            

import org.lonpe.model.PaymentOutForm;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class PaymentOutFormMapStore extends AbstractDCMapStore<PaymentOutForm>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public PaymentOutFormMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, PaymentOutForm paymentOutForm) {
        dBLon0.store00("paymentOutForm", paymentOutForm);
    }
    
    @Override
    public PaymentOutForm load(String key) {
        return (PaymentOutForm)dBLon1.load00("paymentOutForm", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("paymentOutForm");
    }

    @Override
    public Map<String, PaymentOutForm> loadAll(Collection<String> keys) {
        Map<String, PaymentOutForm> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        