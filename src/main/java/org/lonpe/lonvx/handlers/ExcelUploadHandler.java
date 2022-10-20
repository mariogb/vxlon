/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.lonpe.lonvx.handlers.exceptions.ExcelImportException;
import org.lonpe.services.DBLon1;
import org.lonpe.services.impl.DcMapForServices;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgException;
import io.vertx.rxjava3.sqlclient.RowIterator;
import io.vertx.rxjava3.sqlclient.RowSet;
import io.vertx.rxjava3.sqlclient.Tuple;
import org.lonpe.model.IDcLon;
import org.lonpe.services.IServiceLon;

/**
 *
 * @author l5
 */
public class ExcelUploadHandler implements Handler<RoutingContext> {

    private static final String MESSAGE = "message";
    private final DcMapForServices dcMapForServices;
    private final DBLon1 dBLon1;

    public ExcelUploadHandler(DBLon1 dBLon1, DcMapForServices dcMapForServices) {
        this.dcMapForServices = dcMapForServices;
        this.dBLon1 = dBLon1;
    }

    private void handleTheErr(final RoutingContext rc, final Exception ex) {
        final JsonObject js = new JsonObject();
        js.put(MESSAGE, ex.getMessage());
        js.put("clazz", ex.getClass().toString());
        rc.response().setStatusCode(500).end(js.toBuffer());
    }

    private void handleTheErr(final RoutingContext rc, final Throwable t) {
        final JsonObject js = new JsonObject();
        js.put(MESSAGE, t.getMessage());
        js.put("clazz", t.getClass().toString());
        rc.response().setStatusCode(500).end(js.toBuffer());
    }

    @Override
    public void handle(final RoutingContext rc) {

        final String dc = rc.request().getParam("dc");

        final List<FileUpload> uploads = rc.fileUploads();
        final Optional<FileUpload> findFirst = uploads.stream().findFirst();
        if (findFirst.isEmpty()) {

            final JsonObject js = new JsonObject();
            js.put(MESSAGE, "firstIsEmpty");;
            rc.response().setStatusCode(500).end(js.toBuffer());
            return;
        }

        final FileUpload ff = findFirst.get();
        final String fn = ff.uploadedFileName();
        final IServiceLon<IDcLon> serviceLon = dcMapForServices.getServiceFor(dc);

        final JsonObject u0 = rc.get("u0");

        final DataFileExtractor mm = new DataFileExtractor(dc, fn, serviceLon.elModelo(), u0);

        try {
            final Map<String, Object> data = mm.getData2();

            final Map<String, HashSet<String>> parentsKey = (Map<String, HashSet<String>>) data.get("parentsKey");
            final List<Map<String, Object>> datos = (List<Map<String, Object>>) data.get("datos");
            final Single<Map<String, Map<String, Long>>> mapParents = doMapParents(serviceLon, parentsKey);

            Disposable subscribe = mapParents.flatMap((final Map<String, Map<String, Long>> mp2) -> {

                final Iterator<Map<String, Object>> iterator = datos.iterator();
                final List<Tuple> batch = new ArrayList<>();
                while (iterator.hasNext()) {
                    final Map<String, Object> obj = iterator.next();

                    serviceLon.populateParentsIds(obj, mp2);
                    final Tuple t = Tuple.tuple();
                    serviceLon.fillTupleInsert(obj, t.getDelegate());
                    batch.add(t);
                }
                return dBLon1.doBatch(serviceLon.getSqlInsert(), batch);
            }).flatMap((RowSet<io.vertx.rxjava3.sqlclient.Row> onBB) -> {
                Map<String, Object> ee = new HashMap();
                final RowIterator<io.vertx.rxjava3.sqlclient.Row> iterator = onBB.iterator();
                while (iterator.hasNext()) {
                    final io.vertx.rxjava3.sqlclient.Row row00 = iterator.next();
                    ee.put("t", row00.getLong(0));
                }
                return Single.just(ee);
            }).doOnError((Throwable t) -> {
                if (t instanceof PgException) {
                    UtilFns.doError(serviceLon, rc.response(), t.getMessage());
                    return;
                }
                handleTheErr(rc, t);
            }).subscribe((Map<String, Object> t) -> {
                final JsonObject r = new JsonObject().put("r", t);
                rc.response().putHeader("content-type", "application/json").end(r.toBuffer());
            });
            subscribe.dispose();
        } catch (IOException | ExcelImportException ex) {
            handleTheErr(rc, ex);
        }

    }

    public Single<Map<String, Map<String, Long>>> doMapParents(IServiceLon<IDcLon> serviceLon, final Map<String, HashSet<String>> parentsKey) {

        if (parentsKey.isEmpty()) {
            final Map<String, Map<String, Long>> m00 = new HashMap<>();
            return Single.just(m00);
        }

        final List<Observable<Map<String, Long>>> listObserbables = new ArrayList<>();

        final JsonObject elModelo = serviceLon.elModelo();
        final JsonArray mto = elModelo.getJsonArray("mto");
        if (mto == null) {
            final Map<String, Map<String, Long>> m00 = new HashMap<>();
            return Single.just(m00);
        }
        final int nmto = mto.size();
        for (int i = 0; i < nmto; i++) {
            final JsonObject jsMto = mto.getJsonObject(i);
            //serviceLon.getSqlKeyIn()
            //getParentSqlKey
            final IServiceLon<IDcLon> serviceLonParent = dcMapForServices.getServiceFor(jsMto.getString("t"));
            final String sql0 = "SELECT id,pkey from " + serviceLonParent.getTableName() + " WHERE pkey IN ";
            final var toObservable = doMPKID22(parentsKey, jsMto.getString("n"), sql0).toObservable();
            listObserbables.add(toObservable);
        }

        final Observable<Map<String, Long>> concat = Observable.concat(listObserbables);

        return concat.toList().map((final         var ldM) -> {
            final Map<String, Map<String, Long>> mapParents2 = new HashMap<>();
            for (int i = 0; i < nmto; i++) {
                final JsonObject jsMto = mto.getJsonObject(i);
                mapParents2.put(jsMto.getString("n"), ldM.get(i));
            }
            return mapParents2;

        });

    }

    protected Single<Map<String, Long>> doMPKID22(final Map<String, HashSet<String>> parentsKey, final String pn, final String sql) {
        final HashSet<String> parentDc1 = parentsKey.get(pn);
        final String sql2 = sql + "(" + parentDc1.stream().map(t -> "'" + t + "'").collect(Collectors.joining(",")) + ")";
        return dBLon1.doKV(sql2, Tuple.tuple());
    }

    public static String asText(final Cell cell) {

        final CellType cellType = cell.getCellType();
        if (cellType.equals(CellType.NUMERIC)) {
            return cell.getNumericCellValue() + "";
        }
        return cell.getRichStringCellValue().getString().replaceAll("'", "").trim();
    }

    public static BigDecimal asBigDecimal(final Cell cell) {

        final CellType cellType = cell.getCellType();
        if (cellType.equals(CellType.STRING)) {
            String v = cell.getRichStringCellValue().getString();
            return new BigDecimal(v);
        } else if (cellType.equals(CellType.NUMERIC)) {
            return new BigDecimal(cell.getNumericCellValue());
        }
        return null;

    }

    public static boolean asBoolean(final Cell cell) {
        final CellType cellType = cell.getCellType();
        if (cellType.equals(CellType.BOOLEAN)) {
            return cell.getBooleanCellValue();
        }
        if (cellType.equals(CellType.STRING)) {
            return cell.getStringCellValue().equalsIgnoreCase("true");
        } else if (cellType.equals(CellType.NUMERIC)) {
            return cell.getNumericCellValue() != 0d;
        }
        return false;
    }

    private final class DataFileExtractor {

        final String fileLocation;
        final JsonObject elModelo;
        final Map<String, Set<String>> mustBeUniqueValueMap = new HashMap<>();

        final List<Map<String, Object>> datos = new ArrayList<>();
        final Map<String, HashSet<String>> parentsKey = new HashMap<>();
        final JsonObject jsonWebToken;
        final String typeLon;
        final String dc;

        public DataFileExtractor(String dc, String fileLocation, JsonObject elModelo, JsonObject u0) {
            this.dc = dc;
            this.fileLocation = fileLocation;
            this.elModelo = elModelo;
            this.psArr = elModelo.getJsonArray("ps");
            this.nps = psArr.size();
            this.jsonWebToken = u0;
            typeLon = jsonWebToken.getString("typeLon");

        }

        final JsonArray psArr;
        final int nps;

        public Map<String, Object> getData2() throws IOException, ExcelImportException {
            final Map<String, Object> data = new HashMap<>();

            final FileInputStream file = new FileInputStream(new File(fileLocation));

            final Workbook workbook = new XSSFWorkbook(file);
            final Sheet sheet = workbook.getSheetAt(0);

            if (psArr != null) {
                for (int i = 0; i < nps; i++) {

                    final JsonObject p0 = psArr.getJsonObject(i);
                    if (Boolean.TRUE.equals(p0.getBoolean("uq"))) {//unique
                        mustBeUniqueValueMap.put(p0.getString("n"), new HashSet<>());
                    }
                }
            }

            for (final Row row : sheet) {
                final int i = row.getRowNum();
                if (i> 10_000 || (i > 0 && !processRow2(row))) {
                    break;
                }
            }
            data.put("datos", datos);
            data.put("parentsKey", parentsKey);

            return data;
        }

        protected boolean processRow2(final Row row) throws ExcelImportException {

            final Map<String, Object> objeto = new HashMap<>();
            int nc = 0;
            try {
                if (psArr != null) {
                    for (int i = 0; i < nps; i++) {

                        final JsonObject p0 = psArr.getJsonObject(i);
                        final Cell cell = row.getCell(nc);

                        if (nc == 0 && cell == null) {
                            return false;
                        }
                        ps02(p0, cell, objeto);
                        nc++;
                    }
                }

                if (!"ADM".equals(typeLon)) {

                }

                final JsonArray mto = elModelo.getJsonArray("mto");
                if (mto != null) {
                    final int nmto = mto.size();
                    for (int i = 0; i < nmto; i++) {
                        final JsonObject jsMto = mto.getJsonObject(i);
                        final String k = jsMto.getString("n");
                        final Cell cell = row.getCell(nc);
                        if (cell != null) {
                            final String key0 = asText(cell);

                            if (!parentsKey.containsKey(k)) {
                                parentsKey.put(k, new HashSet<>());
                            }
                            parentsKey.get(k).add(key0);
                            objeto.put(k + "_pkey", key0);
                            nc++;
                            if (jsMto.getString("pc") != null) {
                                nc++;
                            }

                        }
                    }
                }

            } catch (Exception e) {
                throw new ExcelImportException(e.getMessage(), row.getRowNum(), nc);
            }
            datos.add(objeto);
            return true;
        }

        private void ps02(final JsonObject p, final Cell cell, final Map<String, Object> objeto) throws Exception {

            final String t = p.getString("t");
            final String n = p.getString("n");
            if (t.equals("String")) {

                final String cval = asText(cell);
                //TODO No empty
                if (Boolean.TRUE.equals(p.getBoolean("rq"))) {
                    if (cval == null) {
                        throw new ExcelUploadFormatException("NULL VALUE {" + cval + "} FOR {" + n + "}");
                    }
                    if (cval.trim().length() < 2) {
                        throw new ExcelUploadFormatException("TOO LITTLE VALUE {" + cval + "} FOR {" + n + "}");
                    }
                }

                if (Boolean.TRUE.equals(p.getBoolean("uq"))) {
                    final Set<String> losVals = mustBeUniqueValueMap.get(n);
                    if (losVals.contains(cval)) {
                        throw new ExcelUploadFormatException("REPITING VALUE {" + cval + "} FOR {" + n + "}");
                    }
                    losVals.add(cval);
                }
                final JsonArray inList = p.getJsonArray("inList");
                if (inList != null) {

                    final int nInList = inList.size();
                    int nB = 0;
                    for (int i = 0; i < nInList; i++) {
                        if (inList.getString(i).equals(cval)) {
                            nB++;
                            break;
                        }
                    }
                    if (nB < 1) {
                        throw new ExcelUploadFormatException("NO ACCEPTING VALUE {" + cval + "} FOR {" + n + "}");
                    }
                }
                objeto.put(n, cval);
            } else if (t.equals("Date")) {
                objeto.put(n, cell.getDateCellValue());
            } else if (t.equalsIgnoreCase("Integer")) {
                final Double numericCellValue = cell.getNumericCellValue();
                final int intValue = numericCellValue.intValue();
                final Integer min = p.getInteger("min");
                if (min != null && min > intValue) {
                    throw new ExcelUploadFormatException("THE VALUE  {" + intValue + "} IS LESSER THAN MIN VALUE {" + min + "} IN {" + n + "}");
                }
                final Integer max = p.getInteger("max");
                if (max != null && max <= intValue) {
                    throw new ExcelUploadFormatException("THE VALUE  {" + intValue + "} IS GREATHER OR EQUAL THAN MAX VALUE {" + max + "} IN {" + n + "}");
                }
                objeto.put(n, intValue);
            } else if (t.equalsIgnoreCase("Long") || t.equalsIgnoreCase("Float")) {
                objeto.put(n, cell.getNumericCellValue());
            } else if (t.equalsIgnoreCase("boolean")) {
                objeto.put(n, asBoolean(cell));
            } else if (t.equals("BigDecimal")) {
                objeto.put(n, asBigDecimal(cell));
            }
            //return true;
        }

    }//end MM

    private class ExcelUploadFormatException extends Exception {

        public ExcelUploadFormatException(String msg) {
            super(msg);
        }

    }

// 
}
