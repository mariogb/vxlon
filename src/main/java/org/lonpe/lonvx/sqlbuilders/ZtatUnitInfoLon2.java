/*
 * Copyright (C) 2022 mgb
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.lonpe.lonvx.sqlbuilders;

/**
 *
 * @author mgb
 */
public class ZtatUnitInfoLon2 extends ZtatUnitInfoLon {

    private ZtatUnitInfoLon ztatUnitInfoLon;

    public ZtatUnitInfoLon2(ZtatUnitInfoLon ztatUnitInfoLon, String foreingTableField, String dc, String table, String dcPc, String alias) {
        super(foreingTableField, dc, table, dcPc, alias);
        this.ztatUnitInfoLon = ztatUnitInfoLon;
    }

    /**
     * @return the ztatUnitInfoLon
     */
    public ZtatUnitInfoLon getZtatUnitInfoLon() {
        return ztatUnitInfoLon;
    }

    /**
     * @param ztatUnitInfoLon the ztatUnitInfoLon to set
     */
    public void setZtatUnitInfoLon(ZtatUnitInfoLon ztatUnitInfoLon) {
        this.ztatUnitInfoLon = ztatUnitInfoLon;
    }

}
