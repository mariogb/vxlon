/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.mapstore;

import com.hazelcast.map.MapStore;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author l5
 */
public abstract class AbstractDCMapStore<T> implements MapStore<String, T> {

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(Collection<String> keys) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    @Override
    public void storeAll(Map<String, T> map) {
        map.entrySet().stream().forEach((entry) -> {
            store(entry.getKey(), entry.getValue());
        });

    }
    
    


}
