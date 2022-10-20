
package org.lonpe.services.impl;
    
import io.vertx.core.json.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.lonpe.services.AbstractServiceLon;
    
    public class DcMapForServices {
    
        final Map<String, AbstractServiceLon> m;
    
        public DcMapForServices() {
            this.m = new HashMap<>();
            
                    m.put("account", new AccountService());
        m.put("airCompany", new AirCompanyService());
        m.put("airport", new AirportService());
        m.put("appointment", new AppointmentService());
        m.put("base", new BaseService());
        m.put("baseTimePeriod", new BaseTimePeriodService());
        m.put("baseUserLon", new BaseUserLonService());
        m.put("comercialDocumentIn", new ComercialDocumentInService());
        m.put("comercialDocumentOut", new ComercialDocumentOutService());
        m.put("comercialDocumentTypeIn", new ComercialDocumentTypeInService());
        m.put("comercialDocumentTypeOut", new ComercialDocumentTypeOutService());
        m.put("contractIn", new ContractInService());
        m.put("contractOut", new ContractOutService());
        m.put("departament", new DepartamentService());
        m.put("departamentBaseTimePeriod", new DepartamentBaseTimePeriodService());
        m.put("departamentJob", new DepartamentJobService());
        m.put("departamentJobInstance", new DepartamentJobInstanceService());
        m.put("departamentUserLon", new DepartamentUserLonService());
        m.put("entityPermisionRole", new EntityPermisionRoleService());
        m.put("fligth", new FligthService());
        m.put("fligthInstance", new FligthInstanceService());
        m.put("formLon", new FormLonService());
        m.put("formLonField", new FormLonFieldService());
        m.put("invoiceLineIn", new InvoiceLineInService());
        m.put("invoiceLineOut", new InvoiceLineOutService());
        m.put("meUsrInterface", new MeUsrInterfaceService());
        m.put("monetaryAccount", new MonetaryAccountService());
        m.put("paymentIn", new PaymentInService());
        m.put("paymentInForm", new PaymentInFormService());
        m.put("paymentInType", new PaymentInTypeService());
        m.put("paymentOut", new PaymentOutService());
        m.put("paymentOutForm", new PaymentOutFormService());
        m.put("paymentOutType", new PaymentOutTypeService());
        m.put("plane", new PlaneService());
        m.put("product", new ProductService());
        m.put("productType", new ProductTypeService());
        m.put("program", new ProgramService());
        m.put("programBaseTimePeriod", new ProgramBaseTimePeriodService());
        m.put("programJob", new ProgramJobService());
        m.put("programUserLon", new ProgramUserLonService());
        m.put("role", new RoleService());
        m.put("stockRack", new StockRackService());
        m.put("stockRackProduct", new StockRackProductService());
        m.put("thirdPerson", new ThirdPersonService());
        m.put("timePeriod", new TimePeriodService());
        m.put("userLon", new UserLonService());
        m.put("userRole", new UserRoleService());
        m.put("userThirdPerson", new UserThirdPersonService());
        m.put("workSpace", new WorkSpaceService());
        m.put("workSpaceGroup", new WorkSpaceGroupService());
            
        }
    
        public JsonObject model() {
    
            final JsonObject jsm = new JsonObject();
            m.forEach((String t, AbstractServiceLon u) -> {
               jsm.put(t, u.elModelo());
            });
                    
            return new JsonObject().put("m_dcmodel",jsm);
    
        }
    
        public AbstractServiceLon getServiceFor(final String dc) {
            return m.get(dc);
        }
    
        public JsonObject modelFiltered(List<String> l_exclude) {
            final JsonObject jsm = new JsonObject();
            m.forEach((String t, AbstractServiceLon u) -> {
                if(!l_exclude.contains(t)){
                    jsm.put(t, u.elModelo());
                }
            }); 
            return  jsm;                  
        }
    }        
        