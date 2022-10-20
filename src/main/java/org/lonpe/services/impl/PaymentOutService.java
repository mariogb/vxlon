package org.lonpe.services.impl;

import io.vertx.core.MultiMap;
import java.util.Map;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;
import io.vertx.core.json.JsonArray;
import org.lonpe.model.*;
import io.vertx.sqlclient.Row;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.lonpe.lonvx.sqlbuilders.SqlZtatBuilder;
import org.lonpe.services.AbstractServiceLon;
import org.lonpe.services.ConditionInfo;
import org.lonpe.lonvx.sqlbuilders.SqlLonConditionsBuilder;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.lonpe.lonvx.sqlbuilders.ZtatUnitInfoLon;

/**
 * PaymentOutService
 *
 */
public class PaymentOutService extends AbstractServiceLon<PaymentOut> {

    private static final String SQLINSERT = "INSERT INTO payment_out(pkey,payment_out_form_id,comercial_document_out_id) VALUES ($1,$2,$3) returning id,pkey";
    private static final String SQLUPDATE = "UPDATE payment_out SET  WHERE id = $5 returning id,pkey";
    private static final String SQLUPDATEPKEY = "UPDATE payment_out SET  WHERE pkey = $5 returning id,pkey";
    private static final String SQLVIEW = "SELECT * FROM payment_out_view";
    private static final String SQLCOUNT = "SELECT count(*) FROM payment_out_view";
    private static final String SQLKEYS = "SELECT payment_out_pkey FROM payment_out_view";
    private static final String SQLIDBYPKEY = "SELECT id from payment_out WHERE pkey = $1";
    private static final String SQLLKEYIN = "SELECT id,pkey from payment_out WHERE pkey in ($1)";
    private static final String SQLDELETE = "DELETE FROM payment_out WHERE id = $1 returning id";
    private static final String TABLENAME = "payment_out";

//1
    private static final ZtatUnitInfoLon zPaymentOutForm;

//1
    private static final ZtatUnitInfoLon zComercialDocumentOut;

//2
    private static final ZtatUnitInfoLon zMonetaryAccount;

//2
    private static final ZtatUnitInfoLon zPaymentOutType;

//2
    private static final ZtatUnitInfoLon zContract;

//2
    private static final ZtatUnitInfoLon zUserAutor;

//2
    private static final ZtatUnitInfoLon zComercialDocumentType;

//3
    private static final ZtatUnitInfoLon zDepartamentBaseTimePeriod;

//3
    private static final ZtatUnitInfoLon zThirdPerson;

    @Override
    public String getTableName() {
        return TABLENAME;
    }

    @Override
    public String getSqlDelete() {
        return SQLDELETE;
    }

    @Override
    public String getSqlKeyIn() {
        return SQLLKEYIN;
    }

    /**
     *
     * private static String sql00 = "SELECT payment_out.id as payment_out_id,
     * payment_out.pkey as payment_out_pkey, payment_out_form.id as
     * payment_out_form_id,payment_out_form.pkey as payment_out_form_pkey,
     * comercial_document_out.id as
     * comercial_document_out_id,comercial_document_out.pkey as
     * comercial_document_out_pkey,comercial_document_out.pname as
     * comercial_document_out_pname, monetary_account.id as monetary_account_id,
     * monetary_account.pkey as monetary_account_pkey,monetary_account.pname as
     * monetary_account_pname, payment_out_type.id as payment_out_type_id,
     * payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as
     * payment_out_type_pname, contract.id as contract_id, contract.pkey as
     * contract_pkey,contract.pname as contract_pname, user_autor.id as
     * user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as
     * user_autor_pname, comercial_document_type.id as
     * comercial_document_type_id, comercial_document_type.pkey as
     * comercial_document_type_pkey,comercial_document_type.pname as
     * comercial_document_type_pname, departament_base_time_period.id as
     * departament_base_time_period_id, departament_base_time_period.pkey as
     * departament_base_time_period_pkey, third_person.id as third_person_id,
     * third_person.pkey as third_person_pkey,third_person.pname as
     * third_person_pname FROM payment_out, payment_out_form as
     * payment_out_form, comercial_document_out as comercial_document_out,
     * monetary_account as monetary_account, payment_out_type as
     * payment_out_type, contract_out as contract, user_lon as user_autor,
     * comercial_document_type_out as comercial_document_type,
     * departament_base_time_period as departament_base_time_period,
     * third_person as third_person WHERE payment_out.payment_out_form_id =
     * payment_out_form.id AND payment_out.comercial_document_out_id =
     * comercial_document_out.id AND payment_out_form.monetary_account_id =
     * monetary_account.id AND payment_out_form.payment_out_type_id =
     * payment_out_type.id AND comercial_document_out.contract_id = contract.id
     * AND comercial_document_out.user_autor_id = user_autor.id AND
     * comercial_document_out.comercial_document_type_id =
     * comercial_document_type.id AND contract.departament_base_time_period_id =
     * departament_base_time_period.id AND contract.third_person_id =
     * third_person.id; "
     */
    @Override
    public String getSqlKeys() {
        return SQLKEYS;
    }

    @Override
    public String getSqlCount() {
        return SQLCOUNT;
    }

    @Override
    public String getSqlIdByPkey() {
        return SQLIDBYPKEY;
    }

    /**
     * sql select property alias field names
     */
    private static final LinkedHashSet<String> names;

    /**
     * Map field insert/update to property
     */
    private static final HashMap<String, String> insertMapFields;

    /**
     * Map property to field order
     */
    private static final HashMap<String, String> sortMapFields;

    private static final JsonObject dcModel;

    static {
        names = new LinkedHashSet<>();
        insertMapFields = new HashMap<>();
        sortMapFields = new HashMap<>();

        dcModel = new JsonObject();

        dcModel.put("dc", "paymentOut");

//ID 
        names.add("id");

        sortMapFields.put("id", "payment_out_id");

        final JsonArray ps = new JsonArray();

//pkey
        names.add("pkey");
        insertMapFields.put("payment_out.pkey", "pkey");

//Create property pkey       
        final JsonObject pkey = ps00a("pkey", "String", true);

//Used to map error on index to source property because IS Unique
        insertMapFields.put("payment_out.payment_out_uidx_pkey", "pkey");

// IS Unique     
        pkey.put("uq", true);

        sortMapFields.put("pkey", "payment_out_pkey");

        ps.add(pkey);

//Add ps to model            
        dcModel.put("ps", ps);

        final JsonArray mto = new JsonArray();

//(1)  paymentOutForm --------------------
        names.add("paymentOutForm_id");
        insertMapFields.put("payment_out.payment_out_form_id", "paymentOutForm_id");

        names.add("paymentOutForm_pkey");
        sortMapFields.put("paymentOutForm_pkey", "payment_out_form_pkey");

        final JsonObject paymentOutForm = doMto("paymentOutForm", "paymentOutForm");

        mto.add(paymentOutForm);

//(1)  comercialDocumentOut --------------------
        names.add("comercialDocumentOut_id");
        insertMapFields.put("payment_out.comercial_document_out_id", "comercialDocumentOut_id");

        names.add("comercialDocumentOut_pkey");
        sortMapFields.put("comercialDocumentOut_pkey", "comercial_document_out_pkey");

        final JsonObject comercialDocumentOut = doMto("comercialDocumentOut", "comercialDocumentOut");

        names.add("comercialDocumentOut_pname");
        sortMapFields.put("comercialDocumentOut_pname", "comercial_document_out_pname");

        comercialDocumentOut.put("pc", "pname");

        mto.add(comercialDocumentOut);

        dcModel.put("mto", mto);

        final JsonArray mto2 = new JsonArray();
//(2)   monetaryAccount 

        names.add("monetaryAccount_id");
        names.add("monetaryAccount_pkey");

        final JsonObject monetaryAccount = doMto2("monetaryAccount", "monetaryAccount", "paymentOutForm");

        names.add("monetaryAccount_pname");
        monetaryAccount.put("pc", "pname");

        sortMapFields.put("monetaryAccount_pname", "monetary_account_pname");

        mto2.add(monetaryAccount);
//(2)   paymentOutType 

        names.add("paymentOutType_id");
        names.add("paymentOutType_pkey");

        final JsonObject paymentOutType = doMto2("paymentOutType", "paymentOutType", "paymentOutForm");

        names.add("paymentOutType_pname");
        paymentOutType.put("pc", "pname");

        sortMapFields.put("paymentOutType_pname", "payment_out_type_pname");

        mto2.add(paymentOutType);
//(2)   contract 

        names.add("contract_id");
        names.add("contract_pkey");

        final JsonObject contract = doMto2("contract", "contractOut", "comercialDocumentOut");

        names.add("contract_pname");
        contract.put("pc", "pname");

        sortMapFields.put("contract_pname", "contract_pname");

        mto2.add(contract);
//(2)   userAutor 

        names.add("userAutor_id");
        names.add("userAutor_pkey");

        final JsonObject userAutor = doMto2("userAutor", "userLon", "comercialDocumentOut");

        names.add("userAutor_pname");
        userAutor.put("pc", "pname");

        sortMapFields.put("userAutor_pname", "user_autor_pname");

        mto2.add(userAutor);
//(2)   comercialDocumentType 

        names.add("comercialDocumentType_id");
        names.add("comercialDocumentType_pkey");

        final JsonObject comercialDocumentType = doMto2("comercialDocumentType", "comercialDocumentTypeOut", "comercialDocumentOut");

        names.add("comercialDocumentType_pname");
        comercialDocumentType.put("pc", "pname");

        sortMapFields.put("comercialDocumentType_pname", "comercial_document_type_pname");

        mto2.add(comercialDocumentType);

        dcModel.put("mto2", mto2);

        final JsonArray mto3 = new JsonArray();
//(3)   departamentBaseTimePeriod 

        names.add("departamentBaseTimePeriod_id");
        names.add("departamentBaseTimePeriod_pkey");

        final JsonObject departamentBaseTimePeriod = doMto2("departamentBaseTimePeriod", "departamentBaseTimePeriod", "contract");

        mto3.add(departamentBaseTimePeriod);
//(3)   thirdPerson 

        names.add("thirdPerson_id");
        names.add("thirdPerson_pkey");

        final JsonObject thirdPerson = doMto2("thirdPerson", "thirdPerson", "contract");

        names.add("thirdPerson_pname");
        thirdPerson.put("pc", "pname");

        sortMapFields.put("thirdPerson_pname", "third_person_pname");

        mto3.add(thirdPerson);

        dcModel.put("mto3", mto3);

//1  payment_out_form  -- payment_out_form_id
        zPaymentOutForm = new ZtatUnitInfoLon("payment_out_form_id", "paymentOutForm", "payment_out_form", "null", "payment_out_form");

//1  comercial_document_out  -- comercial_document_out_id
        zComercialDocumentOut = new ZtatUnitInfoLon("comercial_document_out_id", "comercialDocumentOut", "comercial_document_out", "pname", "comercial_document_out");

//2    
        zMonetaryAccount = new ZtatUnitInfoLon("monetary_account_id", "monetaryAccount", "monetary_account", "pname", "monetary_account");

//2    
        zPaymentOutType = new ZtatUnitInfoLon("payment_out_type_id", "paymentOutType", "payment_out_type", "pname", "payment_out_type");

//2    
        zContract = new ZtatUnitInfoLon("contract_id", "contract", "contract_out", "pname", "contract");

//2    
        zUserAutor = new ZtatUnitInfoLon("user_autor_id", "userAutor", "user_lon", "pname", "user_autor");

//2    
        zComercialDocumentType = new ZtatUnitInfoLon("comercial_document_type_id", "comercialDocumentType", "comercial_document_type_out", "pname", "comercial_document_type");

//3
        zDepartamentBaseTimePeriod = new ZtatUnitInfoLon("departament_base_time_period_id", "departamentBaseTimePeriod", "departament_base_time_period", "null", "departament_base_time_period");

//3
        zThirdPerson = new ZtatUnitInfoLon("third_person_id", "thirdPerson", "third_person", "pname", "third_person");

    }

    @Override
    public LinkedHashSet<String> getNames() {
        return names;
    }

    @Override
    public HashMap<String, String> getInsertMapFields() {
        return insertMapFields;
    }

    @Override
    public HashMap<String, String> getSortMapFields() {
        return sortMapFields;
    }

    @Override
    public JsonObject elModelo() {
        return dcModel;
    }

    @Override
    public JsonArray toJsonArray(final Row r) {
        final JsonArray jsa = new JsonArray();
        jsa.add(r.getLong("payment_out_id"));
        jsa.add(r.getString("payment_out_pkey"));
        jsa.add(r.getLong("payment_out_form_id"));
        jsa.add(r.getString("payment_out_form_pkey"));
        jsa.add(r.getLong("comercial_document_out_id"));
        jsa.add(r.getString("comercial_document_out_pkey"));
        jsa.add(r.getString("comercial_document_out_pname"));
        jsa.add(r.getLong("monetary_account_id"));
        jsa.add(r.getString("monetary_account_pkey"));
        jsa.add(r.getString("monetary_account_pname"));
        jsa.add(r.getLong("payment_out_type_id"));
        jsa.add(r.getString("payment_out_type_pkey"));
        jsa.add(r.getString("payment_out_type_pname"));
        jsa.add(r.getLong("contract_id"));
        jsa.add(r.getString("contract_pkey"));
        jsa.add(r.getString("contract_pname"));
        jsa.add(r.getLong("user_autor_id"));
        jsa.add(r.getString("user_autor_pkey"));
        jsa.add(r.getString("user_autor_pname"));
        jsa.add(r.getLong("comercial_document_type_id"));
        jsa.add(r.getString("comercial_document_type_pkey"));
        jsa.add(r.getString("comercial_document_type_pname"));
        jsa.add(r.getLong("departament_base_time_period_id"));
        jsa.add(r.getString("departament_base_time_period_pkey"));
        jsa.add(r.getLong("third_person_id"));
        jsa.add(r.getString("third_person_pkey"));
        jsa.add(r.getString("third_person_pname"));
        return jsa;
    }

    @Override
    public void fillXRow(final Row r, final XSSFRow row, int nc, boolean withIds) {
        fillXRow0(r, row, nc, withIds);
    }

    @Override
    public HashMap<String, String> lXRowH(final boolean withIds, final int level) {

        final LinkedHashMap<String, String> m_ = new LinkedHashMap<>();
        if (withIds) {
            m_.put("paymentOut_id", "Long");
        }

        //pkey       
        m_.put("paymentOut_pkey", "String");

        if (level < 1) {
            return m_;
        }

        // paymentOutForm
        if (withIds) {
            m_.put("paymentOutForm_id", "Long");

        }

        m_.put("paymentOutForm_pkey", "String");

        // comercialDocumentOut
        if (withIds) {
            m_.put("comercialDocumentOut_id", "Long");

        }

        m_.put("comercialDocumentOut_pkey", "String");

        m_.put("comercialDocumentOut_pname", "String");

//[2] monetaryAccount  
        if (level > 1) {
            if (withIds) {
                m_.put("monetaryAccount_id", "Long");
            }

            m_.put("monetaryAccount_pkey", "String");

            m_.put("monetaryAccount_pname", "String");

        }
//[2] paymentOutType  

        if (level > 1) {
            if (withIds) {
                m_.put("paymentOutType_id", "Long");
            }

            m_.put("paymentOutType_pkey", "String");

            m_.put("paymentOutType_pname", "String");

        }
//[2] contract  

        if (level > 1) {
            if (withIds) {
                m_.put("contract_id", "Long");
            }

            m_.put("contract_pkey", "String");

            m_.put("contract_pname", "String");

        }
//[2] userAutor  

        if (level > 1) {
            if (withIds) {
                m_.put("userAutor_id", "Long");
            }

            m_.put("userAutor_pkey", "String");

            m_.put("userAutor_pname", "String");

        }
//[2] comercialDocumentType  

        if (level > 1) {
            if (withIds) {
                m_.put("comercialDocumentType_id", "Long");
            }

            m_.put("comercialDocumentType_pkey", "String");

            m_.put("comercialDocumentType_pname", "String");

        }
//[3] departamentBaseTimePeriod  

        if (level > 2) {
            if (withIds) {
                m_.put("departamentBaseTimePeriod_id", "Long");
            }

            m_.put("departamentBaseTimePeriod_pkey", "String");

        }
//[3] thirdPerson  

        if (level > 2) {
            if (withIds) {
                m_.put("thirdPerson_id", "Long");
            }

            m_.put("thirdPerson_pkey", "String");

            m_.put("thirdPerson_pname", "String");

        }

        return m_;

    }

    private void fillXRow0(final Row r, final XSSFRow row, int nc, boolean withIds) {
        if (withIds) {
            lToCell(r, row, "payment_out_id", nc++);
        }

        //pkey       
        sToCell(r, row, "payment_out_pkey", nc++);
        // paymentOutForm
        if (withIds) {
            lToCell(r, row, "payment_out_form_id", nc++);
        }
        sToCell(r, row, "payment_out_form_pkey", nc++);
        // comercialDocumentOut
        if (withIds) {
            lToCell(r, row, "comercial_document_out_id", nc++);
        }
        sToCell(r, row, "comercial_document_out_pkey", nc++);
        sToCell(r, row, "comercial_document_out_pname", nc++);
// monetaryAccount
        if (withIds) {
            lToCell(r, row, "monetary_account_id", nc++);
        }
        sToCell(r, row, "monetary_account_pkey", nc++);
        sToCell(r, row, "monetary_account_pname", nc++);
// paymentOutType
        if (withIds) {
            lToCell(r, row, "payment_out_type_id", nc++);
        }
        sToCell(r, row, "payment_out_type_pkey", nc++);
        sToCell(r, row, "payment_out_type_pname", nc++);
// contract
        if (withIds) {
            lToCell(r, row, "contract_id", nc++);
        }
        sToCell(r, row, "contract_pkey", nc++);
        sToCell(r, row, "contract_pname", nc++);
// userAutor
        if (withIds) {
            lToCell(r, row, "user_autor_id", nc++);
        }
        sToCell(r, row, "user_autor_pkey", nc++);
        sToCell(r, row, "user_autor_pname", nc++);
// comercialDocumentType
        if (withIds) {
            lToCell(r, row, "comercial_document_type_id", nc++);
        }
        sToCell(r, row, "comercial_document_type_pkey", nc++);
        sToCell(r, row, "comercial_document_type_pname", nc++);
// departamentBaseTimePeriod
        if (withIds) {
            lToCell(r, row, "departament_base_time_period_id", nc++);
        }
        sToCell(r, row, "departament_base_time_period_pkey", nc++);
// thirdPerson
        if (withIds) {
            lToCell(r, row, "third_person_id", nc++);
        }
        sToCell(r, row, "third_person_pkey", nc++);
        sToCell(r, row, "third_person_pname", nc++);
    }

    @Override
    public String getSqlView() {
        return SQLVIEW;
    }

    @Override
    public String getSqlByKey() {
        return SQLVIEW + " WHERE payment_out_pkey =$1";
    }

    @Override
    public String getSqlInsert() {
        return SQLINSERT;
    }

    @Override
    public void fillTupleInsert(final PaymentOut dc0, final Tuple t) {
        t.addString(dc0.getPkey());

        if (dc0.getPaymentOutForm() != null) {
            t.addLong(dc0.getPaymentOutForm().getId());
        }

        if (dc0.getComercialDocumentOut() != null) {
            t.addLong(dc0.getComercialDocumentOut().getId());
        }
    }

    @Override
    public void fillTupleUpdate(final PaymentOut dc0, final Tuple t) {

//      if(dc0.getPaymentOutForm()!=null){
//            t.addLong(dc0.getPaymentOutForm().getId());
//      }
//      if(dc0.getComercialDocumentOut()!=null){
//            t.addLong(dc0.getComercialDocumentOut().getId());
//      }
        t.addLong(dc0.getId());

    }

    @Override
    public void fillTupleInsert(final Map<String, Object> obj, final Tuple t) {

        fTString("pkey", obj, t);

        fTLong("paymentOutForm_id", obj, t);

        fTLong("comercialDocumentOut_id", obj, t);
    }

    @Override
    public void populateParentsIds(final Map<String, Object> obj, final Map<String, Map<String, Long>> mapParents) {

        final Map<String, Long> paymentOutForm = mapParents.get("paymentOutForm");
        final Long paymentOutForm_id = paymentOutForm.get((String) obj.get("paymentOutForm_pkey"));
        obj.put("paymentOutForm_id", paymentOutForm_id);

        final Map<String, Long> comercialDocumentOut = mapParents.get("comercialDocumentOut");
        final Long comercialDocumentOut_id = comercialDocumentOut.get((String) obj.get("comercialDocumentOut_pkey"));
        obj.put("comercialDocumentOut_id", comercialDocumentOut_id);
    }

    @Override
    public void fillTupleInsert(final JsonObject js, final Tuple t) {

        fTString("pkey", js, t);

        fTLong("paymentOutForm_id", js, t);

        fTLong("comercialDocumentOut_id", js, t);
    }

    @Override
    public void fillTupleUpdate(JsonObject js, Tuple t) {

        //     fTLong("paymentOutForm_id",js,t);
        //     fTLong("comercialDocumentOut_id",js,t);
        fTLong("id", js, t);
    }

    @Override
    public String getSqlIUpdate() {
        return SQLUPDATE;
    }

    @Override
    public String getSqlIUpdatePkey() {
        return SQLUPDATEPKEY;
    }

    @Override
    public PaymentOut doFrom(final Row r) {
        final PaymentOut paymentOut = new PaymentOut();
        paymentOut.setId(r.getLong("payment_out_id"));

        paymentOut.setPkey(r.getString("payment_out_pkey"));

        final PaymentOutForm paymentOutForm = new PaymentOutForm();
        paymentOutForm.setId(r.getLong("payment_out_form_id"));
        paymentOutForm.setPkey(r.getString("payment_out_form_pkey"));

        paymentOut.setPaymentOutForm(paymentOutForm);

        final ComercialDocumentOut comercialDocumentOut = new ComercialDocumentOut();
        comercialDocumentOut.setId(r.getLong("comercial_document_out_id"));
        comercialDocumentOut.setPkey(r.getString("comercial_document_out_pkey"));
        comercialDocumentOut.setPname(r.getString("comercial_document_out_pname"));
        paymentOut.setComercialDocumentOut(comercialDocumentOut);

        final MonetaryAccount monetaryAccount = new MonetaryAccount();
        monetaryAccount.setId(r.getLong("monetary_account_id"));
        monetaryAccount.setPkey(r.getString("monetary_account_pkey"));
        monetaryAccount.setPname(r.getString("monetary_account_pname"));
        paymentOutForm.setMonetaryAccount(monetaryAccount);

        final PaymentOutType paymentOutType = new PaymentOutType();
        paymentOutType.setId(r.getLong("payment_out_type_id"));
        paymentOutType.setPkey(r.getString("payment_out_type_pkey"));
        paymentOutType.setPname(r.getString("payment_out_type_pname"));
        paymentOutForm.setPaymentOutType(paymentOutType);

        final ContractOut contract = new ContractOut();
        contract.setId(r.getLong("contract_id"));
        contract.setPkey(r.getString("contract_pkey"));
        contract.setPname(r.getString("contract_pname"));
        comercialDocumentOut.setContract(contract);

        final UserLon userAutor = new UserLon();
        userAutor.setId(r.getLong("user_autor_id"));
        userAutor.setPkey(r.getString("user_autor_pkey"));
        userAutor.setPname(r.getString("user_autor_pname"));
        comercialDocumentOut.setUserAutor(userAutor);

        final ComercialDocumentTypeOut comercialDocumentType = new ComercialDocumentTypeOut();
        comercialDocumentType.setId(r.getLong("comercial_document_type_id"));
        comercialDocumentType.setPkey(r.getString("comercial_document_type_pkey"));
        comercialDocumentType.setPname(r.getString("comercial_document_type_pname"));
        comercialDocumentOut.setComercialDocumentType(comercialDocumentType);
        return paymentOut;
    }

    @Override
    public PaymentOut doFromJson(final JsonObject js) {
        PaymentOut paymentOut = new PaymentOut();
        paymentOut.setId(js.getLong("id"));

        paymentOut.setPkey(js.getString("pkey"));
        paymentOut.setId(js.getLong("paymentOutForm_id"));
        paymentOut.setId(js.getLong("comercialDocumentOut_id"));
        return paymentOut;
    }

    @Override
    public JsonObject toJson(final PaymentOut o) {
        final JsonObject jso = new JsonObject();
        jso.put("id", o.getId());
        jso.put("pkey", o.getPkey());

        final PaymentOutForm paymentOutForm = o.getPaymentOutForm();
        if (paymentOutForm != null) {
            jso.put("paymentOutForm_id", paymentOutForm.getId());
            jso.put("paymentOutForm_pkey", paymentOutForm.getPkey());
        }

        final ComercialDocumentOut comercialDocumentOut = o.getComercialDocumentOut();
        if (comercialDocumentOut != null) {
            jso.put("comercialDocumentOut_id", comercialDocumentOut.getId());
            jso.put("comercialDocumentOut_pkey", comercialDocumentOut.getPkey());
        }

        return jso;
    }

    @Override
    public ConditionInfo doCondiciones(final MultiMap params, final Tuple tuple) {

        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, tuple);

        //Check Id      
        slcb.doInLongCondition("id", "payment_out_id");
        //*---PKEY ---       
        slcb.doIlPSimple2("pkey", "payment_out_pkey");
        slcb.doEqInPSimple("pkey", "payment_out_pkey");

        slcb.doIlPSimple2("paymentOutForm_pkey", "payment_out_form_pkey");
        slcb.doEQPSimple2("paymentOutForm_pkey", "payment_out_form_pkey");
        slcb.doInLongCondition("paymentOutForm_id", "payment_out_form_id");
//PaymentOutForm undefined        
        slcb.doIlPSimple2("comercialDocumentOut_pkey", "comercial_document_out_pkey");
        slcb.doEQPSimple2("comercialDocumentOut_pkey", "comercial_document_out_pkey");
        slcb.doInLongCondition("comercialDocumentOut_id", "comercial_document_out_id");
//ComercialDocumentOut 4        
        slcb.doIlPSimple2("comercialDocumentOut_pname", "comercial_document_out_pname");

        slcb.doIlPSimple2("monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2("monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");//MonetaryAccount 1
        slcb.doIlPSimple2("monetaryAccount_pname", "monetary_account_pname");

        slcb.doIlPSimple2("paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doEQPSimple2("paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doInLongCondition("paymentOutType_id", "payment_out_type_id");//PaymentOutType 1
        slcb.doIlPSimple2("paymentOutType_pname", "payment_out_type_pname");

        slcb.doIlPSimple2("contract_pkey", "contract_pkey");
        slcb.doEQPSimple2("contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");//ContractOut 1
        slcb.doIlPSimple2("contract_pname", "contract_pname");
        slcb.doIlPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");
        slcb.doIlPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");

        slcb.doIlPSimple2("userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2("userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");//UserLon 4
        slcb.doIlPSimple2("userAutor_pname", "user_autor_pname");

        slcb.doIlPSimple2("comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doEQPSimple2("comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_id");//ComercialDocumentTypeOut 2
        slcb.doIlPSimple2("comercialDocumentType_pname", "comercial_document_type_pname");

        slcb.doSQLORDEN(sortMapFields);

        return slcb.getConditionInfo();

    }

    @Override
    public SqlZtatBuilder doZtat(final MultiMap params) {
        final SqlZtatBuilder sz0 = new SqlZtatBuilder(params, "payment_out");
        sz0.addField("COUNT(payment_out.id) as c_payment_out_id").addName("count");

//level 1
        sz0.applyG1(zPaymentOutForm);
        sz0.applyG1(zComercialDocumentOut);
        //level 2

        sz0.applyG2(zPaymentOutForm, zMonetaryAccount);
        sz0.applyG2(zPaymentOutForm, zPaymentOutType);
        sz0.applyG2(zComercialDocumentOut, zContract);
        sz0.applyG2(zComercialDocumentOut, zUserAutor);
        sz0.applyG2(zComercialDocumentOut, zComercialDocumentType);
        //level 3

        sz0.applyG3(zComercialDocumentOut, zContract, zDepartamentBaseTimePeriod);
        sz0.applyG3(zComercialDocumentOut, zContract, zThirdPerson);
        return sz0;
    }
}
