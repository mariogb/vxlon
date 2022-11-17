/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.hz;

import org.lonpe.mapstore.MeUsrInterfaceMapStore;
import org.lonpe.services.DBLon0;
import org.lonpe.services.DBLon1;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.map.MapStore;
import org.lonpe.lonvx.ctes.CteLon;

/**
 *
 * @author l5
 */
public final class HzLonConfig {

    private HzLonConfig() {
        throw new IllegalStateException("Utility class");
    }

    public static Config doConfig(final DBLon0 dBLon0, final DBLon1 dBLon1) {
        final Config hzConfig = new Config();

        final MeUsrInterfaceMapStore meUsrInterfaceMapStore = new MeUsrInterfaceMapStore(dBLon1, dBLon0);
        doMapStoreConfigG(CteLon.MUIMAP, hzConfig, meUsrInterfaceMapStore);
        return hzConfig;
    }

    protected static void doMapStoreConfigG(final String listName, final Config hzConfig, final MapStore lonmapstore) {
        doMapStoreConfigG(listName, hzConfig, lonmapstore, 0);
    }

    protected static void doMapStoreConfigG(final String l_name, final Config hzConfig, final MapStore lonmapstore,
            Integer timeToLiveSeconds) {
        final MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setImplementation(lonmapstore).setEnabled(true);
        mapStoreConfig.setWriteDelaySeconds(10);

        hzConfig.getMapConfig(l_name).setMapStoreConfig(mapStoreConfig);
        if (timeToLiveSeconds > 0) {
            hzConfig.getMapConfig(l_name).setTimeToLiveSeconds(timeToLiveSeconds);
        }
    }

}
