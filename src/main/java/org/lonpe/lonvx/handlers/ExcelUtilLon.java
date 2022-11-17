package org.lonpe.lonvx.handlers;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.vertx.rxjava3.sqlclient.Row;
import io.vertx.rxjava3.sqlclient.RowIterator;
import io.vertx.rxjava3.sqlclient.RowSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.lonpe.lonvx.ctes.CteLon;
import org.lonpe.model.IDcLon;
import org.lonpe.model.MeUsrInterface;

import org.lonpe.services.IServiceLon;

/**
 *
 * @author l5
 */
public class ExcelUtilLon {

    private final HazelcastInstance h;
    private final IMap<String, MeUsrInterface> mapMUI;

    public ExcelUtilLon(HazelcastInstance h) {
        this.h = h;
        mapMUI = h.getMap(CteLon.MUIMAP);
    }

    private String label(final String s) {
        final MeUsrInterface uidc = mapMUI.get(s);
        return uidc != null ? uidc.getLabel() : s;
    }

    public XSSFWorkbook doFile(final String dc, IServiceLon<IDcLon> s, final RowSet<Row> rs, final boolean withIds) {
        final XSSFWorkbook wb = new XSSFWorkbook();

        //Map<String, String> msgui = new HashMap<>();
        final String sheetName = label("sheet") + "_" + label(dc);// msgui.containsKey(dc_k_name) ? msgui.get(dc_k_name) : dc_k_name;
        final XSSFSheet sheet = wb.createSheet(sheetName);
        //final XSSFCellStyle csty1 = wb.createCellStyle();
        //csty1.setFont(Font.);
        //csty1.setFillBackgroundColor(Short.valueOf("2"));

        final XSSFRow rowt = sheet.createRow(0);
        int nh = 0;
//        final Iterator<String> iterator = s.getNames().iterator();
//        while (iterator.hasNext()) {
//            final String vn = iterator.next();
        final int level = rs == null ? 1 : 5;
        final Map<String, String> lXRowH = s.lXRowH(withIds, level);
        final Iterator<Map.Entry<String, String>> iterator = lXRowH.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, String> en = iterator.next();
            final XSSFCell cell = rowt.createCell(nh++);
            cell.setCellValue(label(en.getKey()) + " [" + en.getValue() + "]");
        }

        int nr = 1;
        if (rs != null) {
            final RowIterator<Row> iterator1 = rs.iterator();
            while (iterator1.hasNext()) {
                final Row r = iterator1.next();
                final XSSFRow row = sheet.createRow(nr++);
                s.fillXRow(r.getDelegate(), row, 0, withIds);
            }

        }

        return wb;

    }

}
