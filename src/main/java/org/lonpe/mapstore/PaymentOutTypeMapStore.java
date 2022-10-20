
package org.lonpe.mapstore;            

            

import org.lonpe.model.PaymentOutType;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class PaymentOutTypeMapStore extends AbstractDCMapStore<PaymentOutType>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public PaymentOutTypeMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, PaymentOutType paymentOutType) {
        dBLon0.store00("paymentOutType", paymentOutType);
    }
    
    @Override
    public PaymentOutType load(String key) {
        return (PaymentOutType)dBLon1.load00("paymentOutType", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("paymentOutType");
    }

    @Override
    public Map<String, PaymentOutType> loadAll(Collection<String> keys) {
        Map<String, PaymentOutType> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        