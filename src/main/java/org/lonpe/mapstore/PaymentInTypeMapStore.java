
package org.lonpe.mapstore;            

            

import org.lonpe.model.PaymentInType;
import org.lonpe.services.DBLon1;
import org.lonpe.services.DBLon0;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

    
public class PaymentInTypeMapStore extends AbstractDCMapStore<PaymentInType>{

    private DBLon1 dBLon1;
    private DBLon0 dBLon0;

    public PaymentInTypeMapStore(DBLon1 dBLon1,DBLon0 dBLon0) {
        this.dBLon1 = dBLon1;
        this.dBLon0 = dBLon0;
    }

    @Override
    public void store(String key, PaymentInType paymentInType) {
        dBLon0.store00("paymentInType", paymentInType);
    }
    
    @Override
    public PaymentInType load(String key) {
        return (PaymentInType)dBLon1.load00("paymentInType", key);         
    }

   
    @Override
    public Iterable<String> loadAllKeys() {
        return dBLon1.loadAllKeys00("paymentInType");
    }

    @Override
    public Map<String, PaymentInType> loadAll(Collection<String> keys) {
        Map<String, PaymentInType> m = new LinkedHashMap<>();
        keys.stream().forEach((String t) -> {
            m.put(t, load(t));
            
        });
        return m;
    }

}           
    
        