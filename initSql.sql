

-- SEQUENCES ----------------------------------------

CREATE SEQUENCE user_lon_id_seq;
CREATE SEQUENCE role_id_seq;
CREATE SEQUENCE user_role_id_seq;
CREATE SEQUENCE user_third_person_id_seq;
CREATE SEQUENCE third_person_id_seq;
CREATE SEQUENCE entity_permision_role_id_seq;
CREATE SEQUENCE base_id_seq;
CREATE SEQUENCE time_period_id_seq;
CREATE SEQUENCE base_time_period_id_seq;
CREATE SEQUENCE work_space_group_id_seq;
CREATE SEQUENCE work_space_id_seq;
CREATE SEQUENCE departament_id_seq;
CREATE SEQUENCE departament_job_id_seq;
CREATE SEQUENCE departament_job_instance_id_seq;
CREATE SEQUENCE program_id_seq;
CREATE SEQUENCE program_job_id_seq;
CREATE SEQUENCE departament_user_lon_id_seq;
CREATE SEQUENCE program_user_lon_id_seq;
CREATE SEQUENCE base_user_lon_id_seq;
CREATE SEQUENCE departament_base_time_period_id_seq;
CREATE SEQUENCE program_base_time_period_id_seq;
CREATE SEQUENCE contract_out_id_seq;
CREATE SEQUENCE contract_in_id_seq;
CREATE SEQUENCE comercial_document_type_out_id_seq;
CREATE SEQUENCE comercial_document_type_in_id_seq;
CREATE SEQUENCE monetary_account_id_seq;
CREATE SEQUENCE payment_out_type_id_seq;
CREATE SEQUENCE payment_in_type_id_seq;
CREATE SEQUENCE payment_out_form_id_seq;
CREATE SEQUENCE payment_in_form_id_seq;
CREATE SEQUENCE payment_out_id_seq;
CREATE SEQUENCE payment_in_id_seq;
CREATE SEQUENCE comercial_document_out_id_seq;
CREATE SEQUENCE comercial_document_in_id_seq;
CREATE SEQUENCE product_type_id_seq;
CREATE SEQUENCE stock_rack_id_seq;
CREATE SEQUENCE stock_rack_product_id_seq;
CREATE SEQUENCE product_id_seq;
CREATE SEQUENCE invoice_line_in_id_seq;
CREATE SEQUENCE invoice_line_out_id_seq;
CREATE SEQUENCE appointment_id_seq;
CREATE SEQUENCE account_id_seq;
CREATE SEQUENCE airport_id_seq;
CREATE SEQUENCE fligth_id_seq;
CREATE SEQUENCE fligth_instance_id_seq;
CREATE SEQUENCE air_company_id_seq;
CREATE SEQUENCE plane_id_seq;
CREATE SEQUENCE me_usr_interface_id_seq;
CREATE SEQUENCE form_lon_id_seq;
CREATE SEQUENCE form_lon_field_id_seq;

-- TABLES ------------------------------------------


    
--- TABLE  UserLon 

CREATE TABLE user_lon  (  id bigint DEFAULT nextval('user_lon_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 email VARCHAR(128)  NOT NULL  ,
 enabled Boolean  NOT NULL   DEFAULT true ,
 password VARCHAR(128)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 type_lon VARCHAR(32)  NOT NULL  ,
 username VARCHAR(32)  NOT NULL    );      
CREATE UNIQUE INDEX user_lon_udix_id ON public.user_lon USING btree (id);


CREATE UNIQUE INDEX user_lon_uidx_pkey ON public.user_lon USING btree (pkey);
CREATE INDEX user_lon_idx_type_lon ON public.user_lon USING btree (type_lon);
CREATE INDEX user_lon_idx_email ON public.user_lon USING btree (email);
CREATE INDEX user_lon_idx_username ON public.user_lon USING btree (username);
-- INDEX FOR PC  pname 
CREATE INDEX user_lon_idx_pname ON public.user_lon USING btree (pname);
 

    
--- TABLE  Role 

CREATE TABLE role  (  id bigint DEFAULT nextval('role_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX role_udix_id ON public.role USING btree (id);


CREATE UNIQUE INDEX role_uidx_pkey ON public.role USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX role_idx_pname ON public.role USING btree (pname);
 

    
--- TABLE  UserRole 

CREATE TABLE user_role  (  id bigint DEFAULT nextval('user_role_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 user_lon_id bigint  NOT NULL,
 role_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX user_role_udix_id ON public.user_role USING btree (id);


CREATE UNIQUE INDEX user_role_uidx_pkey ON public.user_role USING btree (pkey);
 

    
--- TABLE  UserThirdPerson 

CREATE TABLE user_third_person  (  id bigint DEFAULT nextval('user_third_person_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 user_lon_id bigint  NOT NULL,
 third_person_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX user_third_person_udix_id ON public.user_third_person USING btree (id);


CREATE UNIQUE INDEX user_third_person_uidx_pkey ON public.user_third_person USING btree (pkey);
 

    
--- TABLE  ThirdPerson 

CREATE TABLE third_person  (  id bigint DEFAULT nextval('third_person_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 rfc VARCHAR(16)  NOT NULL  ,
 tipo VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX third_person_udix_id ON public.third_person USING btree (id);


CREATE UNIQUE INDEX third_person_uidx_pkey ON public.third_person USING btree (pkey);
CREATE UNIQUE INDEX third_person_uidx_rfc ON public.third_person USING btree (rfc);
CREATE INDEX third_person_idx_tipo ON public.third_person USING btree (tipo);
CREATE INDEX third_person_idx_rfc ON public.third_person USING btree (rfc);
-- INDEX FOR PC  pname 
CREATE INDEX third_person_idx_pname ON public.third_person USING btree (pname);
 

    
--- TABLE  EntityPermisionRole 

CREATE TABLE entity_permision_role  (  id bigint DEFAULT nextval('entity_permision_role_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 enabled Boolean  NOT NULL   DEFAULT true ,
 nombre VARCHAR(64)  NOT NULL  ,
 permission VARCHAR(16)  NOT NULL  ,
 role_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX entity_permision_role_udix_id ON public.entity_permision_role USING btree (id);


CREATE UNIQUE INDEX entity_permision_role_uidx_pkey ON public.entity_permision_role USING btree (pkey);
CREATE INDEX entity_permision_role_idx_permission ON public.entity_permision_role USING btree (permission);
 

    
--- TABLE  Base 

CREATE TABLE base  (  id bigint DEFAULT nextval('base_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX base_udix_id ON public.base USING btree (id);


CREATE UNIQUE INDEX base_uidx_pkey ON public.base USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX base_idx_pname ON public.base USING btree (pname);
 

    
--- TABLE  TimePeriod 

CREATE TABLE time_period  (  id bigint DEFAULT nextval('time_period_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 begin_date Date  NOT NULL  ,
 end_date Date  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 type_lon VARCHAR(16)  NOT NULL    );      
CREATE UNIQUE INDEX time_period_udix_id ON public.time_period USING btree (id);


CREATE UNIQUE INDEX time_period_uidx_pkey ON public.time_period USING btree (pkey);
CREATE INDEX time_period_idx_type_lon ON public.time_period USING btree (type_lon);
-- INDEX FOR PC  pname 
CREATE INDEX time_period_idx_pname ON public.time_period USING btree (pname);
 

    
--- TABLE  BaseTimePeriod 

CREATE TABLE base_time_period  (  id bigint DEFAULT nextval('base_time_period_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 base_id bigint  NOT NULL,
 time_period_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX base_time_period_udix_id ON public.base_time_period USING btree (id);


CREATE UNIQUE INDEX base_time_period_uidx_pkey ON public.base_time_period USING btree (pkey);
 

    
--- TABLE  WorkSpaceGroup 

CREATE TABLE work_space_group  (  id bigint DEFAULT nextval('work_space_group_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 type_lon VARCHAR(16)  NOT NULL  ,
 base_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX work_space_group_udix_id ON public.work_space_group USING btree (id);


CREATE UNIQUE INDEX work_space_group_uidx_pkey ON public.work_space_group USING btree (pkey);
CREATE INDEX work_space_group_idx_type_lon ON public.work_space_group USING btree (type_lon);
-- INDEX FOR PC  pname 
CREATE INDEX work_space_group_idx_pname ON public.work_space_group USING btree (pname);
 

    
--- TABLE  WorkSpace 

CREATE TABLE work_space  (  id bigint DEFAULT nextval('work_space_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 capacity bigint  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 type VARCHAR(256)  NOT NULL  ,
 work_space_group_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX work_space_udix_id ON public.work_space USING btree (id);


CREATE UNIQUE INDEX work_space_uidx_pkey ON public.work_space USING btree (pkey);
CREATE INDEX work_space_idx_type ON public.work_space USING btree (type);
-- INDEX FOR PC  pname 
CREATE INDEX work_space_idx_pname ON public.work_space USING btree (pname);
 

    
--- TABLE  Departament 

CREATE TABLE departament  (  id bigint DEFAULT nextval('departament_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(256)  ,
 fast_key VARCHAR(256)  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX departament_udix_id ON public.departament USING btree (id);


CREATE UNIQUE INDEX departament_uidx_pkey ON public.departament USING btree (pkey);
CREATE UNIQUE INDEX departament_uidx_pname ON public.departament USING btree (pname);
CREATE INDEX departament_idx_fast_key ON public.departament USING btree (fast_key);
-- INDEX FOR PC  pname 
CREATE INDEX departament_idx_pname ON public.departament USING btree (pname);
 

    
--- TABLE  DepartamentJob 

CREATE TABLE departament_job  (  id bigint DEFAULT nextval('departament_job_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(256)  ,
 fast_key VARCHAR(256)  ,
 nhoras Integer  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 departament_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX departament_job_udix_id ON public.departament_job USING btree (id);


CREATE UNIQUE INDEX departament_job_uidx_pkey ON public.departament_job USING btree (pkey);
CREATE UNIQUE INDEX departament_job_uidx_pname ON public.departament_job USING btree (pname);
CREATE INDEX departament_job_idx_fast_key ON public.departament_job USING btree (fast_key);
-- INDEX FOR PC  pname 
CREATE INDEX departament_job_idx_pname ON public.departament_job USING btree (pname);
 

    
--- TABLE  DepartamentJobInstance 

CREATE TABLE departament_job_instance  (  id bigint DEFAULT nextval('departament_job_instance_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(256)  ,
 nhoras Integer  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 departament_job_id bigint  NOT NULL,
 departament_base_time_period_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX departament_job_instance_udix_id ON public.departament_job_instance USING btree (id);


CREATE UNIQUE INDEX departament_job_instance_uidx_pkey ON public.departament_job_instance USING btree (pkey);
CREATE UNIQUE INDEX departament_job_instance_uidx_pname ON public.departament_job_instance USING btree (pname);
-- INDEX FOR PC  pname 
CREATE INDEX departament_job_instance_idx_pname ON public.departament_job_instance USING btree (pname);
 

    
--- TABLE  Program 

CREATE TABLE program  (  id bigint DEFAULT nextval('program_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(256)  ,
 fast_key VARCHAR(256)  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX program_udix_id ON public.program USING btree (id);


CREATE UNIQUE INDEX program_uidx_pkey ON public.program USING btree (pkey);
CREATE UNIQUE INDEX program_uidx_pname ON public.program USING btree (pname);
CREATE INDEX program_idx_fast_key ON public.program USING btree (fast_key);
-- INDEX FOR PC  pname 
CREATE INDEX program_idx_pname ON public.program USING btree (pname);
 

    
--- TABLE  ProgramJob 

CREATE TABLE program_job  (  id bigint DEFAULT nextval('program_job_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(256)  ,
 fast_key VARCHAR(256)  ,
 nhoras Integer  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 program_id bigint  NOT NULL,
 departament_job_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX program_job_udix_id ON public.program_job USING btree (id);


CREATE UNIQUE INDEX program_job_uidx_pkey ON public.program_job USING btree (pkey);
CREATE UNIQUE INDEX program_job_uidx_pname ON public.program_job USING btree (pname);
CREATE INDEX program_job_idx_fast_key ON public.program_job USING btree (fast_key);
-- INDEX FOR PC  pname 
CREATE INDEX program_job_idx_pname ON public.program_job USING btree (pname);
 

    
--- TABLE  DepartamentUserLon 

CREATE TABLE departament_user_lon  (  id bigint DEFAULT nextval('departament_user_lon_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 departament_id bigint  NOT NULL,
 user_lon_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX departament_user_lon_udix_id ON public.departament_user_lon USING btree (id);


CREATE UNIQUE INDEX departament_user_lon_uidx_pkey ON public.departament_user_lon USING btree (pkey);
 

    
--- TABLE  ProgramUserLon 

CREATE TABLE program_user_lon  (  id bigint DEFAULT nextval('program_user_lon_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 program_id bigint  NOT NULL,
 user_lon_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX program_user_lon_udix_id ON public.program_user_lon USING btree (id);


CREATE UNIQUE INDEX program_user_lon_uidx_pkey ON public.program_user_lon USING btree (pkey);
 

    
--- TABLE  BaseUserLon 

CREATE TABLE base_user_lon  (  id bigint DEFAULT nextval('base_user_lon_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 base_id bigint  NOT NULL,
 user_lon_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX base_user_lon_udix_id ON public.base_user_lon USING btree (id);


CREATE UNIQUE INDEX base_user_lon_uidx_pkey ON public.base_user_lon USING btree (pkey);
 

    
--- TABLE  DepartamentBaseTimePeriod 

CREATE TABLE departament_base_time_period  (  id bigint DEFAULT nextval('departament_base_time_period_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 base_time_period_id bigint  NOT NULL,
 departament_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX departament_base_time_period_udix_id ON public.departament_base_time_period USING btree (id);


CREATE UNIQUE INDEX departament_base_time_period_uidx_pkey ON public.departament_base_time_period USING btree (pkey);
 

    
--- TABLE  ProgramBaseTimePeriod 

CREATE TABLE program_base_time_period  (  id bigint DEFAULT nextval('program_base_time_period_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 base_time_period_id bigint  NOT NULL,
 program_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX program_base_time_period_udix_id ON public.program_base_time_period USING btree (id);


CREATE UNIQUE INDEX program_base_time_period_uidx_pkey ON public.program_base_time_period USING btree (pkey);
 

    
--- TABLE  ContractOut 

CREATE TABLE contract_out  (  id bigint DEFAULT nextval('contract_out_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 departament_base_time_period_id bigint  NOT NULL,
 third_person_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX contract_out_udix_id ON public.contract_out USING btree (id);


CREATE UNIQUE INDEX contract_out_uidx_pkey ON public.contract_out USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX contract_out_idx_pname ON public.contract_out USING btree (pname);
 

    
--- TABLE  ContractIn 

CREATE TABLE contract_in  (  id bigint DEFAULT nextval('contract_in_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 program_base_time_period_id bigint  NOT NULL,
 third_person_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX contract_in_udix_id ON public.contract_in USING btree (id);


CREATE UNIQUE INDEX contract_in_uidx_pkey ON public.contract_in USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX contract_in_idx_pname ON public.contract_in USING btree (pname);
 

    
--- TABLE  ComercialDocumentTypeOut 

CREATE TABLE comercial_document_type_out  (  id bigint DEFAULT nextval('comercial_document_type_out_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 afect_stock VARCHAR(8)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX comercial_document_type_out_udix_id ON public.comercial_document_type_out USING btree (id);


CREATE UNIQUE INDEX comercial_document_type_out_uidx_pkey ON public.comercial_document_type_out USING btree (pkey);
CREATE INDEX comercial_document_type_out_idx_afect_stock ON public.comercial_document_type_out USING btree (afect_stock);
-- INDEX FOR PC  pname 
CREATE INDEX comercial_document_type_out_idx_pname ON public.comercial_document_type_out USING btree (pname);
 

    
--- TABLE  ComercialDocumentTypeIn 

CREATE TABLE comercial_document_type_in  (  id bigint DEFAULT nextval('comercial_document_type_in_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 afect_stock VARCHAR(8)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX comercial_document_type_in_udix_id ON public.comercial_document_type_in USING btree (id);


CREATE UNIQUE INDEX comercial_document_type_in_uidx_pkey ON public.comercial_document_type_in USING btree (pkey);
CREATE INDEX comercial_document_type_in_idx_afect_stock ON public.comercial_document_type_in USING btree (afect_stock);
-- INDEX FOR PC  pname 
CREATE INDEX comercial_document_type_in_idx_pname ON public.comercial_document_type_in USING btree (pname);
 

    
--- TABLE  MonetaryAccount 

CREATE TABLE monetary_account  (  id bigint DEFAULT nextval('monetary_account_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX monetary_account_udix_id ON public.monetary_account USING btree (id);


CREATE UNIQUE INDEX monetary_account_uidx_pkey ON public.monetary_account USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX monetary_account_idx_pname ON public.monetary_account USING btree (pname);
 

    
--- TABLE  PaymentOutType 

CREATE TABLE payment_out_type  (  id bigint DEFAULT nextval('payment_out_type_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX payment_out_type_udix_id ON public.payment_out_type USING btree (id);


CREATE UNIQUE INDEX payment_out_type_uidx_pkey ON public.payment_out_type USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX payment_out_type_idx_pname ON public.payment_out_type USING btree (pname);
 

    
--- TABLE  PaymentInType 

CREATE TABLE payment_in_type  (  id bigint DEFAULT nextval('payment_in_type_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX payment_in_type_udix_id ON public.payment_in_type USING btree (id);


CREATE UNIQUE INDEX payment_in_type_uidx_pkey ON public.payment_in_type USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX payment_in_type_idx_pname ON public.payment_in_type USING btree (pname);
 

    
--- TABLE  PaymentOutForm 

CREATE TABLE payment_out_form  (  id bigint DEFAULT nextval('payment_out_form_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 monetary_account_id bigint  NOT NULL,
 payment_out_type_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX payment_out_form_udix_id ON public.payment_out_form USING btree (id);


CREATE UNIQUE INDEX payment_out_form_uidx_pkey ON public.payment_out_form USING btree (pkey);
 

    
--- TABLE  PaymentInForm 

CREATE TABLE payment_in_form  (  id bigint DEFAULT nextval('payment_in_form_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 monetary_account_id bigint  NOT NULL,
 payment_in_type_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX payment_in_form_udix_id ON public.payment_in_form USING btree (id);


CREATE UNIQUE INDEX payment_in_form_uidx_pkey ON public.payment_in_form USING btree (pkey);
 

    
--- TABLE  PaymentOut 

CREATE TABLE payment_out  (  id bigint DEFAULT nextval('payment_out_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 payment_out_form_id bigint  NOT NULL,
 comercial_document_out_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX payment_out_udix_id ON public.payment_out USING btree (id);


CREATE UNIQUE INDEX payment_out_uidx_pkey ON public.payment_out USING btree (pkey);
 

    
--- TABLE  PaymentIn 

CREATE TABLE payment_in  (  id bigint DEFAULT nextval('payment_in_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 payment_in_form_id bigint  NOT NULL,
 comercial_document_in_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX payment_in_udix_id ON public.payment_in USING btree (id);


CREATE UNIQUE INDEX payment_in_uidx_pkey ON public.payment_in USING btree (pkey);
 

    
--- TABLE  ComercialDocumentOut 

CREATE TABLE comercial_document_out  (  id bigint DEFAULT nextval('comercial_document_out_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 created_date timestamp  NOT NULL   DEFAULT now() ,
 document_date timestamp  ,
 folio VARCHAR(256)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 status VARCHAR(256)  NOT NULL  ,
 supply_date timestamp  ,
 contract_id bigint  NOT NULL,
 user_autor_id bigint  NOT NULL,
 comercial_document_type_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX comercial_document_out_udix_id ON public.comercial_document_out USING btree (id);


CREATE UNIQUE INDEX comercial_document_out_uidx_pkey ON public.comercial_document_out USING btree (pkey);
CREATE INDEX comercial_document_out_idx_status ON public.comercial_document_out USING btree (status);
-- INDEX FOR PC  pname 
CREATE INDEX comercial_document_out_idx_pname ON public.comercial_document_out USING btree (pname);
 

    
--- TABLE  ComercialDocumentIn 

CREATE TABLE comercial_document_in  (  id bigint DEFAULT nextval('comercial_document_in_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 created_date timestamp  NOT NULL   DEFAULT now() ,
 document_date timestamp  ,
 folio VARCHAR(256)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 status VARCHAR(256)  NOT NULL  ,
 supply_date timestamp  ,
 contract_id bigint  NOT NULL,
 user_autor_id bigint  NOT NULL,
 comercial_document_type_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX comercial_document_in_udix_id ON public.comercial_document_in USING btree (id);


CREATE UNIQUE INDEX comercial_document_in_uidx_pkey ON public.comercial_document_in USING btree (pkey);
CREATE INDEX comercial_document_in_idx_status ON public.comercial_document_in USING btree (status);
-- INDEX FOR PC  pname 
CREATE INDEX comercial_document_in_idx_pname ON public.comercial_document_in USING btree (pname);
 

    
--- TABLE  ProductType 

CREATE TABLE product_type  (  id bigint DEFAULT nextval('product_type_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 afect_stock Boolean  NOT NULL   DEFAULT true ,
 description VARCHAR(256)  ,
 fast_key VARCHAR(256)  ,
 is_service Boolean  NOT NULL   DEFAULT false ,
 pname VARCHAR(128)  NOT NULL  ,
 taxable Boolean  NOT NULL   DEFAULT true ,
 with_serial_number Boolean  NOT NULL   DEFAULT false   );      
CREATE UNIQUE INDEX product_type_udix_id ON public.product_type USING btree (id);


CREATE UNIQUE INDEX product_type_uidx_pkey ON public.product_type USING btree (pkey);
CREATE INDEX product_type_idx_fast_key ON public.product_type USING btree (fast_key);
-- INDEX FOR PC  pname 
CREATE INDEX product_type_idx_pname ON public.product_type USING btree (pname);
 

    
--- TABLE  StockRack 

CREATE TABLE stock_rack  (  id bigint DEFAULT nextval('stock_rack_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 fast_key VARCHAR(256)  ,
 pname VARCHAR(128)  NOT NULL  ,
 work_space_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX stock_rack_udix_id ON public.stock_rack USING btree (id);


CREATE UNIQUE INDEX stock_rack_uidx_pkey ON public.stock_rack USING btree (pkey);
CREATE INDEX stock_rack_idx_fast_key ON public.stock_rack USING btree (fast_key);
-- INDEX FOR PC  pname 
CREATE INDEX stock_rack_idx_pname ON public.stock_rack USING btree (pname);
 

    
--- TABLE  StockRackProduct 

CREATE TABLE stock_rack_product  (  id bigint DEFAULT nextval('stock_rack_product_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 quantity bigint  NOT NULL   DEFAULT 0 ,
 serial_number VARCHAR(256)  ,
 stock_rack_id bigint  NOT NULL,
 product_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX stock_rack_product_udix_id ON public.stock_rack_product USING btree (id);


CREATE UNIQUE INDEX stock_rack_product_uidx_pkey ON public.stock_rack_product USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX stock_rack_product_idx_pname ON public.stock_rack_product USING btree (pname);
 

    
--- TABLE  Product 

CREATE TABLE product  (  id bigint DEFAULT nextval('product_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(256)  ,
 fast_key VARCHAR(256)  ,
 pname VARCHAR(128)  NOT NULL  ,
 sku VARCHAR(256)  ,
 product_type_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX product_udix_id ON public.product USING btree (id);


CREATE UNIQUE INDEX product_uidx_pkey ON public.product USING btree (pkey);
CREATE INDEX product_idx_fast_key ON public.product USING btree (fast_key);
CREATE INDEX product_idx_sku ON public.product USING btree (sku);
-- INDEX FOR PC  pname 
CREATE INDEX product_idx_pname ON public.product USING btree (pname);
 

    
--- TABLE  InvoiceLineIn 

CREATE TABLE invoice_line_in  (  id bigint DEFAULT nextval('invoice_line_in_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 ask_quantity numeric(16,6)  NOT NULL  ,
 created_date timestamp  NOT NULL   DEFAULT now() ,
 descount numeric(16,6)  NOT NULL  ,
 invoice_date timestamp  ,
 orden Integer  NOT NULL   DEFAULT 1 ,
 status VARCHAR(256)  NOT NULL  ,
 supply_date timestamp  ,
 supply_quantity numeric(16,6)  NOT NULL  ,
 tax_porcent numeric(16,6)  NOT NULL  ,
 total numeric(16,6)  NOT NULL  ,
 total_cost numeric(16,6)  NOT NULL  ,
 unit_cost numeric(16,6)  NOT NULL  ,
 comercial_document_id bigint  NOT NULL,
 product_id bigint  NOT NULL,
 stock_rack_product_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX invoice_line_in_udix_id ON public.invoice_line_in USING btree (id);


CREATE UNIQUE INDEX invoice_line_in_uidx_pkey ON public.invoice_line_in USING btree (pkey);
CREATE INDEX invoice_line_in_idx_status ON public.invoice_line_in USING btree (status);
 

    
--- TABLE  InvoiceLineOut 

CREATE TABLE invoice_line_out  (  id bigint DEFAULT nextval('invoice_line_out_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 ask_quantity numeric(16,6)  NOT NULL  ,
 created_date timestamp  NOT NULL   DEFAULT now() ,
 descount numeric(16,6)  NOT NULL  ,
 invoice_date timestamp  ,
 orden Integer  NOT NULL   DEFAULT 1 ,
 status VARCHAR(256)  NOT NULL  ,
 supply_date timestamp  ,
 supply_quantity numeric(16,6)  NOT NULL  ,
 tax_porcent numeric(16,6)  NOT NULL  ,
 total numeric(16,6)  NOT NULL  ,
 total_cost numeric(16,6)  NOT NULL  ,
 unit_cost numeric(16,6)  NOT NULL  ,
 comercial_document_id bigint  NOT NULL,
 product_id bigint  NOT NULL,
 stock_rack_product_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX invoice_line_out_udix_id ON public.invoice_line_out USING btree (id);


CREATE UNIQUE INDEX invoice_line_out_uidx_pkey ON public.invoice_line_out USING btree (pkey);
CREATE INDEX invoice_line_out_idx_status ON public.invoice_line_out USING btree (status);
 

    
--- TABLE  Appointment 

CREATE TABLE appointment  (  id bigint DEFAULT nextval('appointment_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 end_hour Integer  NOT NULL  ,
 end_minute Integer  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 start_hour Integer  NOT NULL  ,
 start_minute Integer  NOT NULL  ,
 week_day Integer  NOT NULL  ,
 contract_id bigint  NOT NULL,
 work_space_id bigint  NOT NULL,
 departament_job_instance_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX appointment_udix_id ON public.appointment USING btree (id);


CREATE UNIQUE INDEX appointment_uidx_pkey ON public.appointment USING btree (pkey);
CREATE INDEX appointment_idx_week_day ON public.appointment USING btree (week_day);
-- INDEX FOR PC  pname 
CREATE INDEX appointment_idx_pname ON public.appointment USING btree (pname);
 

    
--- TABLE  Account 

CREATE TABLE account  (  id bigint DEFAULT nextval('account_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 description VARCHAR(512)  ,
 pname VARCHAR(128)  NOT NULL  ,
 type VARCHAR(256)  NOT NULL    );      
CREATE UNIQUE INDEX account_udix_id ON public.account USING btree (id);


CREATE UNIQUE INDEX account_uidx_pkey ON public.account USING btree (pkey);
CREATE INDEX account_idx_type ON public.account USING btree (type);
-- INDEX FOR PC  pname 
CREATE INDEX account_idx_pname ON public.account USING btree (pname);
 

    
--- TABLE  Airport 

CREATE TABLE airport  (  id bigint DEFAULT nextval('airport_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX airport_udix_id ON public.airport USING btree (id);


CREATE UNIQUE INDEX airport_uidx_pkey ON public.airport USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX airport_idx_pname ON public.airport USING btree (pname);
 

    
--- TABLE  Fligth 

CREATE TABLE fligth  (  id bigint DEFAULT nextval('fligth_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 from_airport_id bigint  NOT NULL,
 to_airport_id bigint  NOT NULL,
 plane_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX fligth_udix_id ON public.fligth USING btree (id);


CREATE UNIQUE INDEX fligth_uidx_pkey ON public.fligth USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX fligth_idx_pname ON public.fligth USING btree (pname);
 

    
--- TABLE  FligthInstance 

CREATE TABLE fligth_instance  (  id bigint DEFAULT nextval('fligth_instance_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 in_local_date_time timestamp  NOT NULL  ,
 out_local_date_time timestamp  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL  ,
 the_fligth_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX fligth_instance_udix_id ON public.fligth_instance USING btree (id);


CREATE UNIQUE INDEX fligth_instance_uidx_pkey ON public.fligth_instance USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX fligth_instance_idx_pname ON public.fligth_instance USING btree (pname);
 

    
--- TABLE  AirCompany 

CREATE TABLE air_company  (  id bigint DEFAULT nextval('air_company_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(128)  NOT NULL    );      
CREATE UNIQUE INDEX air_company_udix_id ON public.air_company USING btree (id);


CREATE UNIQUE INDEX air_company_uidx_pkey ON public.air_company USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX air_company_idx_pname ON public.air_company USING btree (pname);
 

    
--- TABLE  Plane 

CREATE TABLE plane  (  id bigint DEFAULT nextval('plane_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 plate VARCHAR(8)  NOT NULL  ,
 pname VARCHAR(64)  NOT NULL  ,
 seats Integer  NOT NULL  ,
 la_compania_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX plane_udix_id ON public.plane USING btree (id);


CREATE UNIQUE INDEX plane_uidx_pkey ON public.plane USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX plane_idx_pname ON public.plane USING btree (pname);
 

    
--- TABLE  MeUsrInterface 

CREATE TABLE me_usr_interface  (  id bigint DEFAULT nextval('me_usr_interface_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 dc VARCHAR(256)  NOT NULL  ,
 label VARCHAR(96)  NOT NULL  ,
 level Integer  NOT NULL    );      
CREATE UNIQUE INDEX me_usr_interface_udix_id ON public.me_usr_interface USING btree (id);


CREATE UNIQUE INDEX me_usr_interface_uidx_pkey ON public.me_usr_interface USING btree (pkey);
-- INDEX FOR PC  label 
CREATE INDEX me_usr_interface_idx_label ON public.me_usr_interface USING btree (label);
 

    
--- TABLE  FormLon 

CREATE TABLE form_lon  (  id bigint DEFAULT nextval('form_lon_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(64)  NOT NULL    );      
CREATE UNIQUE INDEX form_lon_udix_id ON public.form_lon USING btree (id);


CREATE UNIQUE INDEX form_lon_uidx_pkey ON public.form_lon USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX form_lon_idx_pname ON public.form_lon USING btree (pname);
 

    
--- TABLE  FormLonField 

CREATE TABLE form_lon_field  (  id bigint DEFAULT nextval('form_lon_field_id_seq'::regclass) NOT NULL ,
 pkey VARCHAR(32)  NOT NULL  ,
 pname VARCHAR(64)  NOT NULL  ,
 form_lon_id bigint  NOT NULL  );      
CREATE UNIQUE INDEX form_lon_field_udix_id ON public.form_lon_field USING btree (id);


CREATE UNIQUE INDEX form_lon_field_uidx_pkey ON public.form_lon_field USING btree (pkey);
-- INDEX FOR PC  pname 
CREATE INDEX form_lon_field_idx_pname ON public.form_lon_field USING btree (pname);
 

-- Foreign Keys -------------------------------------




-- Foreign keys for  UserRole  user_role   
    
    

--   To: userLon user_lon_id

ALTER TABLE "public"."user_role"
ADD CONSTRAINT fk_user_role_xx_user_lon_id
FOREIGN KEY (user_lon_id)
REFERENCES "public"."user_lon"(id);   

--   To: role role_id

ALTER TABLE "public"."user_role"
ADD CONSTRAINT fk_user_role_xx_role_id
FOREIGN KEY (role_id)
REFERENCES "public"."role"(id);   

-- Foreign keys for  UserThirdPerson  user_third_person   
    
    

--   To: userLon user_lon_id

ALTER TABLE "public"."user_third_person"
ADD CONSTRAINT fk_user_third_person_xx_user_lon_id
FOREIGN KEY (user_lon_id)
REFERENCES "public"."user_lon"(id);   

--   To: thirdPerson third_person_id

ALTER TABLE "public"."user_third_person"
ADD CONSTRAINT fk_user_third_person_xx_third_person_id
FOREIGN KEY (third_person_id)
REFERENCES "public"."third_person"(id);   


-- Foreign keys for  EntityPermisionRole  entity_permision_role   
    
    

--   To: role role_id

ALTER TABLE "public"."entity_permision_role"
ADD CONSTRAINT fk_entity_permision_role_xx_role_id
FOREIGN KEY (role_id)
REFERENCES "public"."role"(id);   



-- Foreign keys for  BaseTimePeriod  base_time_period   
    
    

--   To: base base_id

ALTER TABLE "public"."base_time_period"
ADD CONSTRAINT fk_base_time_period_xx_base_id
FOREIGN KEY (base_id)
REFERENCES "public"."base"(id);   

--   To: timePeriod time_period_id

ALTER TABLE "public"."base_time_period"
ADD CONSTRAINT fk_base_time_period_xx_time_period_id
FOREIGN KEY (time_period_id)
REFERENCES "public"."time_period"(id);   

CREATE UNIQUE INDEX idx_uq_base_w_timePeriod ON base_time_period( base_id,time_period_id); 
 

-- Foreign keys for  WorkSpaceGroup  work_space_group   
    
    

--   To: base base_id

ALTER TABLE "public"."work_space_group"
ADD CONSTRAINT fk_work_space_group_xx_base_id
FOREIGN KEY (base_id)
REFERENCES "public"."base"(id);   

-- Foreign keys for  WorkSpace  work_space   
    
    

--   To: workSpaceGroup work_space_group_id

ALTER TABLE "public"."work_space"
ADD CONSTRAINT fk_work_space_xx_work_space_group_id
FOREIGN KEY (work_space_group_id)
REFERENCES "public"."work_space_group"(id);   


-- Foreign keys for  DepartamentJob  departament_job   
    
    

--   To: departament departament_id

ALTER TABLE "public"."departament_job"
ADD CONSTRAINT fk_departament_job_xx_departament_id
FOREIGN KEY (departament_id)
REFERENCES "public"."departament"(id);   

-- Foreign keys for  DepartamentJobInstance  departament_job_instance   
    
    

--   To: departamentJob departament_job_id

ALTER TABLE "public"."departament_job_instance"
ADD CONSTRAINT fk_departament_job_instance_xx_departament_job_id
FOREIGN KEY (departament_job_id)
REFERENCES "public"."departament_job"(id);   

--   To: departamentBaseTimePeriod departament_base_time_period_id

ALTER TABLE "public"."departament_job_instance"
ADD CONSTRAINT fk_departament_job_instance_xx_departament_base_time_period_id
FOREIGN KEY (departament_base_time_period_id)
REFERENCES "public"."departament_base_time_period"(id);   


-- Foreign keys for  ProgramJob  program_job   
    
    

--   To: program program_id

ALTER TABLE "public"."program_job"
ADD CONSTRAINT fk_program_job_xx_program_id
FOREIGN KEY (program_id)
REFERENCES "public"."program"(id);   

--   To: departamentJob departament_job_id

ALTER TABLE "public"."program_job"
ADD CONSTRAINT fk_program_job_xx_departament_job_id
FOREIGN KEY (departament_job_id)
REFERENCES "public"."departament_job"(id);   

-- Foreign keys for  DepartamentUserLon  departament_user_lon   
    
    

--   To: departament departament_id

ALTER TABLE "public"."departament_user_lon"
ADD CONSTRAINT fk_departament_user_lon_xx_departament_id
FOREIGN KEY (departament_id)
REFERENCES "public"."departament"(id);   

--   To: userLon user_lon_id

ALTER TABLE "public"."departament_user_lon"
ADD CONSTRAINT fk_departament_user_lon_xx_user_lon_id
FOREIGN KEY (user_lon_id)
REFERENCES "public"."user_lon"(id);   

CREATE UNIQUE INDEX idx_uq_departament_w_userLon ON departament_user_lon( departament_id,user_lon_id); 
 

-- Foreign keys for  ProgramUserLon  program_user_lon   
    
    

--   To: program program_id

ALTER TABLE "public"."program_user_lon"
ADD CONSTRAINT fk_program_user_lon_xx_program_id
FOREIGN KEY (program_id)
REFERENCES "public"."program"(id);   

--   To: userLon user_lon_id

ALTER TABLE "public"."program_user_lon"
ADD CONSTRAINT fk_program_user_lon_xx_user_lon_id
FOREIGN KEY (user_lon_id)
REFERENCES "public"."user_lon"(id);   

CREATE UNIQUE INDEX idx_uq_program_w_userLon ON program_user_lon( program_id,user_lon_id); 
 

-- Foreign keys for  BaseUserLon  base_user_lon   
    
    

--   To: base base_id

ALTER TABLE "public"."base_user_lon"
ADD CONSTRAINT fk_base_user_lon_xx_base_id
FOREIGN KEY (base_id)
REFERENCES "public"."base"(id);   

--   To: userLon user_lon_id

ALTER TABLE "public"."base_user_lon"
ADD CONSTRAINT fk_base_user_lon_xx_user_lon_id
FOREIGN KEY (user_lon_id)
REFERENCES "public"."user_lon"(id);   

CREATE UNIQUE INDEX idx_uq_base_w_userLon ON base_user_lon( base_id,user_lon_id); 
 

-- Foreign keys for  DepartamentBaseTimePeriod  departament_base_time_period   
    
    

--   To: baseTimePeriod base_time_period_id

ALTER TABLE "public"."departament_base_time_period"
ADD CONSTRAINT fk_departament_base_time_period_xx_base_time_period_id
FOREIGN KEY (base_time_period_id)
REFERENCES "public"."base_time_period"(id);   

--   To: departament departament_id

ALTER TABLE "public"."departament_base_time_period"
ADD CONSTRAINT fk_departament_base_time_period_xx_departament_id
FOREIGN KEY (departament_id)
REFERENCES "public"."departament"(id);   

CREATE UNIQUE INDEX idx_uq_baseTimePeriod_w_departament ON departament_base_time_period( base_time_period_id,departament_id); 
 

-- Foreign keys for  ProgramBaseTimePeriod  program_base_time_period   
    
    

--   To: baseTimePeriod base_time_period_id

ALTER TABLE "public"."program_base_time_period"
ADD CONSTRAINT fk_program_base_time_period_xx_base_time_period_id
FOREIGN KEY (base_time_period_id)
REFERENCES "public"."base_time_period"(id);   

--   To: program program_id

ALTER TABLE "public"."program_base_time_period"
ADD CONSTRAINT fk_program_base_time_period_xx_program_id
FOREIGN KEY (program_id)
REFERENCES "public"."program"(id);   

CREATE UNIQUE INDEX idx_uq_baseTimePeriod_w_program ON program_base_time_period( base_time_period_id,program_id); 
 

-- Foreign keys for  ContractOut  contract_out   
    
    

--   To: departamentBaseTimePeriod departament_base_time_period_id

ALTER TABLE "public"."contract_out"
ADD CONSTRAINT fk_contract_out_xx_departament_base_time_period_id
FOREIGN KEY (departament_base_time_period_id)
REFERENCES "public"."departament_base_time_period"(id);   

--   To: thirdPerson third_person_id

ALTER TABLE "public"."contract_out"
ADD CONSTRAINT fk_contract_out_xx_third_person_id
FOREIGN KEY (third_person_id)
REFERENCES "public"."third_person"(id);   

-- Foreign keys for  ContractIn  contract_in   
    
    

--   To: programBaseTimePeriod program_base_time_period_id

ALTER TABLE "public"."contract_in"
ADD CONSTRAINT fk_contract_in_xx_program_base_time_period_id
FOREIGN KEY (program_base_time_period_id)
REFERENCES "public"."program_base_time_period"(id);   

--   To: thirdPerson third_person_id

ALTER TABLE "public"."contract_in"
ADD CONSTRAINT fk_contract_in_xx_third_person_id
FOREIGN KEY (third_person_id)
REFERENCES "public"."third_person"(id);   






-- Foreign keys for  PaymentOutForm  payment_out_form   
    
    

--   To: monetaryAccount monetary_account_id

ALTER TABLE "public"."payment_out_form"
ADD CONSTRAINT fk_payment_out_form_xx_monetary_account_id
FOREIGN KEY (monetary_account_id)
REFERENCES "public"."monetary_account"(id);   

--   To: paymentOutType payment_out_type_id

ALTER TABLE "public"."payment_out_form"
ADD CONSTRAINT fk_payment_out_form_xx_payment_out_type_id
FOREIGN KEY (payment_out_type_id)
REFERENCES "public"."payment_out_type"(id);   

-- Foreign keys for  PaymentInForm  payment_in_form   
    
    

--   To: monetaryAccount monetary_account_id

ALTER TABLE "public"."payment_in_form"
ADD CONSTRAINT fk_payment_in_form_xx_monetary_account_id
FOREIGN KEY (monetary_account_id)
REFERENCES "public"."monetary_account"(id);   

--   To: paymentInType payment_in_type_id

ALTER TABLE "public"."payment_in_form"
ADD CONSTRAINT fk_payment_in_form_xx_payment_in_type_id
FOREIGN KEY (payment_in_type_id)
REFERENCES "public"."payment_in_type"(id);   

-- Foreign keys for  PaymentOut  payment_out   
    
    

--   To: paymentOutForm payment_out_form_id

ALTER TABLE "public"."payment_out"
ADD CONSTRAINT fk_payment_out_xx_payment_out_form_id
FOREIGN KEY (payment_out_form_id)
REFERENCES "public"."payment_out_form"(id);   

--   To: comercialDocumentOut comercial_document_out_id

ALTER TABLE "public"."payment_out"
ADD CONSTRAINT fk_payment_out_xx_comercial_document_out_id
FOREIGN KEY (comercial_document_out_id)
REFERENCES "public"."comercial_document_out"(id);   

-- Foreign keys for  PaymentIn  payment_in   
    
    

--   To: paymentInForm payment_in_form_id

ALTER TABLE "public"."payment_in"
ADD CONSTRAINT fk_payment_in_xx_payment_in_form_id
FOREIGN KEY (payment_in_form_id)
REFERENCES "public"."payment_in_form"(id);   

--   To: comercialDocumentIn comercial_document_in_id

ALTER TABLE "public"."payment_in"
ADD CONSTRAINT fk_payment_in_xx_comercial_document_in_id
FOREIGN KEY (comercial_document_in_id)
REFERENCES "public"."comercial_document_in"(id);   

-- Foreign keys for  ComercialDocumentOut  comercial_document_out   
    
    

--   To: contractOut contract_id

ALTER TABLE "public"."comercial_document_out"
ADD CONSTRAINT fk_comercial_document_out_xx_contract_id
FOREIGN KEY (contract_id)
REFERENCES "public"."contract_out"(id);   

--   To: userLon user_autor_id

ALTER TABLE "public"."comercial_document_out"
ADD CONSTRAINT fk_comercial_document_out_xx_user_autor_id
FOREIGN KEY (user_autor_id)
REFERENCES "public"."user_lon"(id);   

--   To: comercialDocumentTypeOut comercial_document_type_id

ALTER TABLE "public"."comercial_document_out"
ADD CONSTRAINT fk_comercial_document_out_xx_comercial_document_type_id
FOREIGN KEY (comercial_document_type_id)
REFERENCES "public"."comercial_document_type_out"(id);   

-- Foreign keys for  ComercialDocumentIn  comercial_document_in   
    
    

--   To: contractIn contract_id

ALTER TABLE "public"."comercial_document_in"
ADD CONSTRAINT fk_comercial_document_in_xx_contract_id
FOREIGN KEY (contract_id)
REFERENCES "public"."contract_in"(id);   

--   To: userLon user_autor_id

ALTER TABLE "public"."comercial_document_in"
ADD CONSTRAINT fk_comercial_document_in_xx_user_autor_id
FOREIGN KEY (user_autor_id)
REFERENCES "public"."user_lon"(id);   

--   To: comercialDocumentTypeIn comercial_document_type_id

ALTER TABLE "public"."comercial_document_in"
ADD CONSTRAINT fk_comercial_document_in_xx_comercial_document_type_id
FOREIGN KEY (comercial_document_type_id)
REFERENCES "public"."comercial_document_type_in"(id);   


-- Foreign keys for  StockRack  stock_rack   
    
    

--   To: workSpace work_space_id

ALTER TABLE "public"."stock_rack"
ADD CONSTRAINT fk_stock_rack_xx_work_space_id
FOREIGN KEY (work_space_id)
REFERENCES "public"."work_space"(id);   

-- Foreign keys for  StockRackProduct  stock_rack_product   
    
    

--   To: stockRack stock_rack_id

ALTER TABLE "public"."stock_rack_product"
ADD CONSTRAINT fk_stock_rack_product_xx_stock_rack_id
FOREIGN KEY (stock_rack_id)
REFERENCES "public"."stock_rack"(id);   

--   To: product product_id

ALTER TABLE "public"."stock_rack_product"
ADD CONSTRAINT fk_stock_rack_product_xx_product_id
FOREIGN KEY (product_id)
REFERENCES "public"."product"(id);   

CREATE UNIQUE INDEX idx_uq_stockRack_w_product ON stock_rack_product( stock_rack_id,product_id); 
 

-- Foreign keys for  Product  product   
    
    

--   To: productType product_type_id

ALTER TABLE "public"."product"
ADD CONSTRAINT fk_product_xx_product_type_id
FOREIGN KEY (product_type_id)
REFERENCES "public"."product_type"(id);   

-- Foreign keys for  InvoiceLineIn  invoice_line_in   
    
    

--   To: comercialDocumentOut comercial_document_id

ALTER TABLE "public"."invoice_line_in"
ADD CONSTRAINT fk_invoice_line_in_xx_comercial_document_id
FOREIGN KEY (comercial_document_id)
REFERENCES "public"."comercial_document_out"(id);   

--   To: product product_id

ALTER TABLE "public"."invoice_line_in"
ADD CONSTRAINT fk_invoice_line_in_xx_product_id
FOREIGN KEY (product_id)
REFERENCES "public"."product"(id);   

--   To: stockRackProduct stock_rack_product_id

ALTER TABLE "public"."invoice_line_in"
ADD CONSTRAINT fk_invoice_line_in_xx_stock_rack_product_id
FOREIGN KEY (stock_rack_product_id)
REFERENCES "public"."stock_rack_product"(id);   

-- Foreign keys for  InvoiceLineOut  invoice_line_out   
    
    

--   To: comercialDocumentIn comercial_document_id

ALTER TABLE "public"."invoice_line_out"
ADD CONSTRAINT fk_invoice_line_out_xx_comercial_document_id
FOREIGN KEY (comercial_document_id)
REFERENCES "public"."comercial_document_in"(id);   

--   To: product product_id

ALTER TABLE "public"."invoice_line_out"
ADD CONSTRAINT fk_invoice_line_out_xx_product_id
FOREIGN KEY (product_id)
REFERENCES "public"."product"(id);   

--   To: stockRackProduct stock_rack_product_id

ALTER TABLE "public"."invoice_line_out"
ADD CONSTRAINT fk_invoice_line_out_xx_stock_rack_product_id
FOREIGN KEY (stock_rack_product_id)
REFERENCES "public"."stock_rack_product"(id);   

-- Foreign keys for  Appointment  appointment   
    
    

--   To: contractOut contract_id

ALTER TABLE "public"."appointment"
ADD CONSTRAINT fk_appointment_xx_contract_id
FOREIGN KEY (contract_id)
REFERENCES "public"."contract_out"(id);   

--   To: workSpace work_space_id

ALTER TABLE "public"."appointment"
ADD CONSTRAINT fk_appointment_xx_work_space_id
FOREIGN KEY (work_space_id)
REFERENCES "public"."work_space"(id);   

--   To: departamentJobInstance departament_job_instance_id

ALTER TABLE "public"."appointment"
ADD CONSTRAINT fk_appointment_xx_departament_job_instance_id
FOREIGN KEY (departament_job_instance_id)
REFERENCES "public"."departament_job_instance"(id);   



-- Foreign keys for  Fligth  fligth   
    
    

--   To: airport from_airport_id

ALTER TABLE "public"."fligth"
ADD CONSTRAINT fk_fligth_xx_from_airport_id
FOREIGN KEY (from_airport_id)
REFERENCES "public"."airport"(id);   

--   To: airport to_airport_id

ALTER TABLE "public"."fligth"
ADD CONSTRAINT fk_fligth_xx_to_airport_id
FOREIGN KEY (to_airport_id)
REFERENCES "public"."airport"(id);   

--   To: plane plane_id

ALTER TABLE "public"."fligth"
ADD CONSTRAINT fk_fligth_xx_plane_id
FOREIGN KEY (plane_id)
REFERENCES "public"."plane"(id);   

-- Foreign keys for  FligthInstance  fligth_instance   
    
    

--   To: fligth the_fligth_id

ALTER TABLE "public"."fligth_instance"
ADD CONSTRAINT fk_fligth_instance_xx_the_fligth_id
FOREIGN KEY (the_fligth_id)
REFERENCES "public"."fligth"(id);   


-- Foreign keys for  Plane  plane   
    
    

--   To: airCompany la_compania_id

ALTER TABLE "public"."plane"
ADD CONSTRAINT fk_plane_xx_la_compania_id
FOREIGN KEY (la_compania_id)
REFERENCES "public"."air_company"(id);   



-- Foreign keys for  FormLonField  form_lon_field   
    
    

--   To: formLon form_lon_id

ALTER TABLE "public"."form_lon_field"
ADD CONSTRAINT fk_form_lon_field_xx_form_lon_id
FOREIGN KEY (form_lon_id)
REFERENCES "public"."form_lon"(id);   

-- VIEWS --------------------------------------------


-- VIEW FOR   UserLon
Select '
Create view for UserLon' as dc;
CREATE OR REPLACE VIEW user_lon_view as 
SELECT user_lon.id as user_lon_id,
user_lon.pkey as user_lon_pkey,
user_lon.email as user_lon_email,
user_lon.enabled as user_lon_enabled,
user_lon.pname as user_lon_pname,
user_lon.type_lon as user_lon_type_lon,
user_lon.username as user_lon_username 
  FROM 
  user_lon ; 



-- VIEW FOR   Role
Select '
Create view for Role' as dc;
CREATE OR REPLACE VIEW role_view as 
SELECT role.id as role_id,
role.pkey as role_pkey,
role.pname as role_pname 
  FROM 
  role ; 



-- VIEW FOR   UserRole
Select '
Create view for UserRole' as dc;
CREATE OR REPLACE VIEW user_role_view as 
SELECT user_role.id as user_role_id,
user_role.pkey as user_role_pkey,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname,
role.id as role_id,role.pkey as role_pkey,role.pname as role_pname 
  FROM 
  user_role,
  user_lon as user_lon,
  role as role  
 WHERE 
 user_role.user_lon_id = user_lon.id
 AND user_role.role_id = role.id; 



-- VIEW FOR   UserThirdPerson
Select '
Create view for UserThirdPerson' as dc;
CREATE OR REPLACE VIEW user_third_person_view as 
SELECT user_third_person.id as user_third_person_id,
user_third_person.pkey as user_third_person_pkey,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname,
third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname 
  FROM 
  user_third_person,
  user_lon as user_lon,
  third_person as third_person  
 WHERE 
 user_third_person.user_lon_id = user_lon.id
 AND user_third_person.third_person_id = third_person.id; 



-- VIEW FOR   ThirdPerson
Select '
Create view for ThirdPerson' as dc;
CREATE OR REPLACE VIEW third_person_view as 
SELECT third_person.id as third_person_id,
third_person.pkey as third_person_pkey,
third_person.pname as third_person_pname,
third_person.rfc as third_person_rfc,
third_person.tipo as third_person_tipo 
  FROM 
  third_person ; 



-- VIEW FOR   EntityPermisionRole
Select '
Create view for EntityPermisionRole' as dc;
CREATE OR REPLACE VIEW entity_permision_role_view as 
SELECT entity_permision_role.id as entity_permision_role_id,
entity_permision_role.pkey as entity_permision_role_pkey,
entity_permision_role.enabled as entity_permision_role_enabled,
entity_permision_role.nombre as entity_permision_role_nombre,
entity_permision_role.permission as entity_permision_role_permission,
role.id as role_id,role.pkey as role_pkey,role.pname as role_pname 
  FROM 
  entity_permision_role,
  role as role  
 WHERE 
 entity_permision_role.role_id = role.id; 



-- VIEW FOR   Base
Select '
Create view for Base' as dc;
CREATE OR REPLACE VIEW base_view as 
SELECT base.id as base_id,
base.pkey as base_pkey,
base.pname as base_pname 
  FROM 
  base ; 



-- VIEW FOR   TimePeriod
Select '
Create view for TimePeriod' as dc;
CREATE OR REPLACE VIEW time_period_view as 
SELECT time_period.id as time_period_id,
time_period.pkey as time_period_pkey,
time_period.begin_date as time_period_begin_date,
time_period.end_date as time_period_end_date,
time_period.pname as time_period_pname,
time_period.type_lon as time_period_type_lon 
  FROM 
  time_period ; 



-- VIEW FOR   BaseTimePeriod
Select '
Create view for BaseTimePeriod' as dc;
CREATE OR REPLACE VIEW base_time_period_view as 
SELECT base_time_period.id as base_time_period_id,
base_time_period.pkey as base_time_period_pkey,
base.id as base_id,base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id,time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  base_time_period,
  base as base,
  time_period as time_period  
 WHERE 
 base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 



-- VIEW FOR   WorkSpaceGroup
Select '
Create view for WorkSpaceGroup' as dc;
CREATE OR REPLACE VIEW work_space_group_view as 
SELECT work_space_group.id as work_space_group_id,
work_space_group.pkey as work_space_group_pkey,
work_space_group.pname as work_space_group_pname,
work_space_group.type_lon as work_space_group_type_lon,
base.id as base_id,base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  work_space_group,
  base as base  
 WHERE 
 work_space_group.base_id = base.id; 



-- VIEW FOR   WorkSpace
Select '
Create view for WorkSpace' as dc;
CREATE OR REPLACE VIEW work_space_view as 
SELECT work_space.id as work_space_id,
work_space.pkey as work_space_pkey,
work_space.capacity as work_space_capacity,
work_space.pname as work_space_pname,
work_space.type as work_space_type,
work_space_group.id as work_space_group_id,work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  work_space,
  work_space_group as work_space_group,
  base as base  
 WHERE 
 work_space.work_space_group_id = work_space_group.id
 AND work_space_group.base_id = base.id; 



-- VIEW FOR   Departament
Select '
Create view for Departament' as dc;
CREATE OR REPLACE VIEW departament_view as 
SELECT departament.id as departament_id,
departament.pkey as departament_pkey,
departament.description as departament_description,
departament.fast_key as departament_fast_key,
departament.pname as departament_pname 
  FROM 
  departament ; 



-- VIEW FOR   DepartamentJob
Select '
Create view for DepartamentJob' as dc;
CREATE OR REPLACE VIEW departament_job_view as 
SELECT departament_job.id as departament_job_id,
departament_job.pkey as departament_job_pkey,
departament_job.description as departament_job_description,
departament_job.fast_key as departament_job_fast_key,
departament_job.nhoras as departament_job_nhoras,
departament_job.pname as departament_job_pname,
departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname 
  FROM 
  departament_job,
  departament as departament  
 WHERE 
 departament_job.departament_id = departament.id; 



-- VIEW FOR   DepartamentJobInstance
Select '
Create view for DepartamentJobInstance' as dc;
CREATE OR REPLACE VIEW departament_job_instance_view as 
SELECT departament_job_instance.id as departament_job_instance_id,
departament_job_instance.pkey as departament_job_instance_pkey,
departament_job_instance.description as departament_job_instance_description,
departament_job_instance.nhoras as departament_job_instance_nhoras,
departament_job_instance.pname as departament_job_instance_pname,
departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,
departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  departament_job_instance,
  departament_job as departament_job,
  departament_base_time_period as departament_base_time_period,
  departament as departament,
  base_time_period as base_time_period,
  base as base,
  time_period as time_period  
 WHERE 
 departament_job_instance.departament_job_id = departament_job.id
 AND departament_job_instance.departament_base_time_period_id = departament_base_time_period.id
 AND departament_job.departament_id = departament.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 



-- VIEW FOR   Program
Select '
Create view for Program' as dc;
CREATE OR REPLACE VIEW program_view as 
SELECT program.id as program_id,
program.pkey as program_pkey,
program.description as program_description,
program.fast_key as program_fast_key,
program.pname as program_pname 
  FROM 
  program ; 



-- VIEW FOR   ProgramJob
Select '
Create view for ProgramJob' as dc;
CREATE OR REPLACE VIEW program_job_view as 
SELECT program_job.id as program_job_id,
program_job.pkey as program_job_pkey,
program_job.description as program_job_description,
program_job.fast_key as program_job_fast_key,
program_job.nhoras as program_job_nhoras,
program_job.pname as program_job_pname,
program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,
departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname 
  FROM 
  program_job,
  program as program,
  departament_job as departament_job,
  departament as departament  
 WHERE 
 program_job.program_id = program.id
 AND program_job.departament_job_id = departament_job.id
 AND departament_job.departament_id = departament.id; 



-- VIEW FOR   DepartamentUserLon
Select '
Create view for DepartamentUserLon' as dc;
CREATE OR REPLACE VIEW departament_user_lon_view as 
SELECT departament_user_lon.id as departament_user_lon_id,
departament_user_lon.pkey as departament_user_lon_pkey,
departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname 
  FROM 
  departament_user_lon,
  departament as departament,
  user_lon as user_lon  
 WHERE 
 departament_user_lon.departament_id = departament.id
 AND departament_user_lon.user_lon_id = user_lon.id; 



-- VIEW FOR   ProgramUserLon
Select '
Create view for ProgramUserLon' as dc;
CREATE OR REPLACE VIEW program_user_lon_view as 
SELECT program_user_lon.id as program_user_lon_id,
program_user_lon.pkey as program_user_lon_pkey,
program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname 
  FROM 
  program_user_lon,
  program as program,
  user_lon as user_lon  
 WHERE 
 program_user_lon.program_id = program.id
 AND program_user_lon.user_lon_id = user_lon.id; 



-- VIEW FOR   BaseUserLon
Select '
Create view for BaseUserLon' as dc;
CREATE OR REPLACE VIEW base_user_lon_view as 
SELECT base_user_lon.id as base_user_lon_id,
base_user_lon.pkey as base_user_lon_pkey,
base.id as base_id,base.pkey as base_pkey,base.pname as base_pname,
user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname 
  FROM 
  base_user_lon,
  base as base,
  user_lon as user_lon  
 WHERE 
 base_user_lon.base_id = base.id
 AND base_user_lon.user_lon_id = user_lon.id; 



-- VIEW FOR   DepartamentBaseTimePeriod
Select '
Create view for DepartamentBaseTimePeriod' as dc;
CREATE OR REPLACE VIEW departament_base_time_period_view as 
SELECT departament_base_time_period.id as departament_base_time_period_id,
departament_base_time_period.pkey as departament_base_time_period_pkey,
base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,
departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  departament_base_time_period,
  base_time_period as base_time_period,
  departament as departament,
  base as base,
  time_period as time_period  
 WHERE 
 departament_base_time_period.base_time_period_id = base_time_period.id
 AND departament_base_time_period.departament_id = departament.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 



-- VIEW FOR   ProgramBaseTimePeriod
Select '
Create view for ProgramBaseTimePeriod' as dc;
CREATE OR REPLACE VIEW program_base_time_period_view as 
SELECT program_base_time_period.id as program_base_time_period_id,
program_base_time_period.pkey as program_base_time_period_pkey,
base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,
program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  program_base_time_period,
  base_time_period as base_time_period,
  program as program,
  base as base,
  time_period as time_period  
 WHERE 
 program_base_time_period.base_time_period_id = base_time_period.id
 AND program_base_time_period.program_id = program.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 



-- VIEW FOR   ContractOut
Select '
Create view for ContractOut' as dc;
CREATE OR REPLACE VIEW contract_out_view as 
SELECT contract_out.id as contract_out_id,
contract_out.pkey as contract_out_pkey,
contract_out.pname as contract_out_pname,
departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  contract_out,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  base_time_period as base_time_period,
  departament as departament,
  base as base,
  time_period as time_period  
 WHERE 
 contract_out.departament_base_time_period_id = departament_base_time_period.id
 AND contract_out.third_person_id = third_person.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND departament_base_time_period.departament_id = departament.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 



-- VIEW FOR   ContractIn
Select '
Create view for ContractIn' as dc;
CREATE OR REPLACE VIEW contract_in_view as 
SELECT contract_in.id as contract_in_id,
contract_in.pkey as contract_in_pkey,
contract_in.pname as contract_in_pname,
program_base_time_period.id as program_base_time_period_id,program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
program.id as program_id, program.pkey as program_pkey,program.pname as program_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,
time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname 
  FROM 
  contract_in,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  base_time_period as base_time_period,
  program as program,
  base as base,
  time_period as time_period  
 WHERE 
 contract_in.program_base_time_period_id = program_base_time_period.id
 AND contract_in.third_person_id = third_person.id
 AND program_base_time_period.base_time_period_id = base_time_period.id
 AND program_base_time_period.program_id = program.id
 AND base_time_period.base_id = base.id
 AND base_time_period.time_period_id = time_period.id; 



-- VIEW FOR   ComercialDocumentTypeOut
Select '
Create view for ComercialDocumentTypeOut' as dc;
CREATE OR REPLACE VIEW comercial_document_type_out_view as 
SELECT comercial_document_type_out.id as comercial_document_type_out_id,
comercial_document_type_out.pkey as comercial_document_type_out_pkey,
comercial_document_type_out.afect_stock as comercial_document_type_out_afect_stock,
comercial_document_type_out.pname as comercial_document_type_out_pname 
  FROM 
  comercial_document_type_out ; 



-- VIEW FOR   ComercialDocumentTypeIn
Select '
Create view for ComercialDocumentTypeIn' as dc;
CREATE OR REPLACE VIEW comercial_document_type_in_view as 
SELECT comercial_document_type_in.id as comercial_document_type_in_id,
comercial_document_type_in.pkey as comercial_document_type_in_pkey,
comercial_document_type_in.afect_stock as comercial_document_type_in_afect_stock,
comercial_document_type_in.pname as comercial_document_type_in_pname 
  FROM 
  comercial_document_type_in ; 



-- VIEW FOR   MonetaryAccount
Select '
Create view for MonetaryAccount' as dc;
CREATE OR REPLACE VIEW monetary_account_view as 
SELECT monetary_account.id as monetary_account_id,
monetary_account.pkey as monetary_account_pkey,
monetary_account.pname as monetary_account_pname 
  FROM 
  monetary_account ; 



-- VIEW FOR   PaymentOutType
Select '
Create view for PaymentOutType' as dc;
CREATE OR REPLACE VIEW payment_out_type_view as 
SELECT payment_out_type.id as payment_out_type_id,
payment_out_type.pkey as payment_out_type_pkey,
payment_out_type.pname as payment_out_type_pname 
  FROM 
  payment_out_type ; 



-- VIEW FOR   PaymentInType
Select '
Create view for PaymentInType' as dc;
CREATE OR REPLACE VIEW payment_in_type_view as 
SELECT payment_in_type.id as payment_in_type_id,
payment_in_type.pkey as payment_in_type_pkey,
payment_in_type.pname as payment_in_type_pname 
  FROM 
  payment_in_type ; 



-- VIEW FOR   PaymentOutForm
Select '
Create view for PaymentOutForm' as dc;
CREATE OR REPLACE VIEW payment_out_form_view as 
SELECT payment_out_form.id as payment_out_form_id,
payment_out_form.pkey as payment_out_form_pkey,
monetary_account.id as monetary_account_id,monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_out_type.id as payment_out_type_id,payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as payment_out_type_pname 
  FROM 
  payment_out_form,
  monetary_account as monetary_account,
  payment_out_type as payment_out_type  
 WHERE 
 payment_out_form.monetary_account_id = monetary_account.id
 AND payment_out_form.payment_out_type_id = payment_out_type.id; 



-- VIEW FOR   PaymentInForm
Select '
Create view for PaymentInForm' as dc;
CREATE OR REPLACE VIEW payment_in_form_view as 
SELECT payment_in_form.id as payment_in_form_id,
payment_in_form.pkey as payment_in_form_pkey,
monetary_account.id as monetary_account_id,monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_in_type.id as payment_in_type_id,payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname 
  FROM 
  payment_in_form,
  monetary_account as monetary_account,
  payment_in_type as payment_in_type  
 WHERE 
 payment_in_form.monetary_account_id = monetary_account.id
 AND payment_in_form.payment_in_type_id = payment_in_type.id; 



-- VIEW FOR   PaymentOut
Select '
Create view for PaymentOut' as dc;
CREATE OR REPLACE VIEW payment_out_view as 
SELECT payment_out.id as payment_out_id,
payment_out.pkey as payment_out_pkey,
payment_out_form.id as payment_out_form_id,payment_out_form.pkey as payment_out_form_pkey,
comercial_document_out.id as comercial_document_out_id,comercial_document_out.pkey as comercial_document_out_pkey,comercial_document_out.pname as comercial_document_out_pname,
monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_out_type.id as payment_out_type_id, payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as payment_out_type_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname 
  FROM 
  payment_out,
  payment_out_form as payment_out_form,
  comercial_document_out as comercial_document_out,
  monetary_account as monetary_account,
  payment_out_type as payment_out_type,
  contract_out as contract,
  user_lon as user_autor,
  comercial_document_type_out as comercial_document_type,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person  
 WHERE 
 payment_out.payment_out_form_id = payment_out_form.id
 AND payment_out.comercial_document_out_id = comercial_document_out.id
 AND payment_out_form.monetary_account_id = monetary_account.id
 AND payment_out_form.payment_out_type_id = payment_out_type.id
 AND comercial_document_out.contract_id = contract.id
 AND comercial_document_out.user_autor_id = user_autor.id
 AND comercial_document_out.comercial_document_type_id = comercial_document_type.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND contract.third_person_id = third_person.id; 



-- VIEW FOR   PaymentIn
Select '
Create view for PaymentIn' as dc;
CREATE OR REPLACE VIEW payment_in_view as 
SELECT payment_in.id as payment_in_id,
payment_in.pkey as payment_in_pkey,
payment_in_form.id as payment_in_form_id,payment_in_form.pkey as payment_in_form_pkey,
comercial_document_in.id as comercial_document_in_id,comercial_document_in.pkey as comercial_document_in_pkey,comercial_document_in.pname as comercial_document_in_pname,
monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,
payment_in_type.id as payment_in_type_id, payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname 
  FROM 
  payment_in,
  payment_in_form as payment_in_form,
  comercial_document_in as comercial_document_in,
  monetary_account as monetary_account,
  payment_in_type as payment_in_type,
  contract_in as contract,
  user_lon as user_autor,
  comercial_document_type_in as comercial_document_type,
  program_base_time_period as program_base_time_period,
  third_person as third_person  
 WHERE 
 payment_in.payment_in_form_id = payment_in_form.id
 AND payment_in.comercial_document_in_id = comercial_document_in.id
 AND payment_in_form.monetary_account_id = monetary_account.id
 AND payment_in_form.payment_in_type_id = payment_in_type.id
 AND comercial_document_in.contract_id = contract.id
 AND comercial_document_in.user_autor_id = user_autor.id
 AND comercial_document_in.comercial_document_type_id = comercial_document_type.id
 AND contract.program_base_time_period_id = program_base_time_period.id
 AND contract.third_person_id = third_person.id; 



-- VIEW FOR   ComercialDocumentOut
Select '
Create view for ComercialDocumentOut' as dc;
CREATE OR REPLACE VIEW comercial_document_out_view as 
SELECT comercial_document_out.id as comercial_document_out_id,
comercial_document_out.pkey as comercial_document_out_pkey,
comercial_document_out.created_date as comercial_document_out_created_date,
comercial_document_out.document_date as comercial_document_out_document_date,
comercial_document_out.folio as comercial_document_out_folio,
comercial_document_out.pname as comercial_document_out_pname,
comercial_document_out.status as comercial_document_out_status,
comercial_document_out.supply_date as comercial_document_out_supply_date,
contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id,comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname 
  FROM 
  comercial_document_out,
  contract_out as contract,
  user_lon as user_autor,
  comercial_document_type_out as comercial_document_type,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  base_time_period as base_time_period,
  departament as departament  
 WHERE 
 comercial_document_out.contract_id = contract.id
 AND comercial_document_out.user_autor_id = user_autor.id
 AND comercial_document_out.comercial_document_type_id = comercial_document_type.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND departament_base_time_period.departament_id = departament.id; 



-- VIEW FOR   ComercialDocumentIn
Select '
Create view for ComercialDocumentIn' as dc;
CREATE OR REPLACE VIEW comercial_document_in_view as 
SELECT comercial_document_in.id as comercial_document_in_id,
comercial_document_in.pkey as comercial_document_in_pkey,
comercial_document_in.created_date as comercial_document_in_created_date,
comercial_document_in.document_date as comercial_document_in_document_date,
comercial_document_in.folio as comercial_document_in_folio,
comercial_document_in.pname as comercial_document_in_pname,
comercial_document_in.status as comercial_document_in_status,
comercial_document_in.supply_date as comercial_document_in_supply_date,
contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id,comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
program.id as program_id, program.pkey as program_pkey,program.pname as program_pname 
  FROM 
  comercial_document_in,
  contract_in as contract,
  user_lon as user_autor,
  comercial_document_type_in as comercial_document_type,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  base_time_period as base_time_period,
  program as program  
 WHERE 
 comercial_document_in.contract_id = contract.id
 AND comercial_document_in.user_autor_id = user_autor.id
 AND comercial_document_in.comercial_document_type_id = comercial_document_type.id
 AND contract.program_base_time_period_id = program_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND program_base_time_period.base_time_period_id = base_time_period.id
 AND program_base_time_period.program_id = program.id; 



-- VIEW FOR   ProductType
Select '
Create view for ProductType' as dc;
CREATE OR REPLACE VIEW product_type_view as 
SELECT product_type.id as product_type_id,
product_type.pkey as product_type_pkey,
product_type.afect_stock as product_type_afect_stock,
product_type.description as product_type_description,
product_type.fast_key as product_type_fast_key,
product_type.is_service as product_type_is_service,
product_type.pname as product_type_pname,
product_type.taxable as product_type_taxable,
product_type.with_serial_number as product_type_with_serial_number 
  FROM 
  product_type ; 



-- VIEW FOR   StockRack
Select '
Create view for StockRack' as dc;
CREATE OR REPLACE VIEW stock_rack_view as 
SELECT stock_rack.id as stock_rack_id,
stock_rack.pkey as stock_rack_pkey,
stock_rack.fast_key as stock_rack_fast_key,
stock_rack.pname as stock_rack_pname,
work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  stock_rack,
  work_space as work_space,
  work_space_group as work_space_group,
  base as base  
 WHERE 
 stock_rack.work_space_id = work_space.id
 AND work_space.work_space_group_id = work_space_group.id
 AND work_space_group.base_id = base.id; 



-- VIEW FOR   StockRackProduct
Select '
Create view for StockRackProduct' as dc;
CREATE OR REPLACE VIEW stock_rack_product_view as 
SELECT stock_rack_product.id as stock_rack_product_id,
stock_rack_product.pkey as stock_rack_product_pkey,
stock_rack_product.pname as stock_rack_product_pname,
stock_rack_product.quantity as stock_rack_product_quantity,
stock_rack_product.serial_number as stock_rack_product_serial_number,
stock_rack.id as stock_rack_id,stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname 
  FROM 
  stock_rack_product,
  stock_rack as stock_rack,
  product as product,
  work_space as work_space,
  product_type as product_type,
  work_space_group as work_space_group  
 WHERE 
 stock_rack_product.stock_rack_id = stock_rack.id
 AND stock_rack_product.product_id = product.id
 AND stock_rack.work_space_id = work_space.id
 AND product.product_type_id = product_type.id
 AND work_space.work_space_group_id = work_space_group.id; 



-- VIEW FOR   Product
Select '
Create view for Product' as dc;
CREATE OR REPLACE VIEW product_view as 
SELECT product.id as product_id,
product.pkey as product_pkey,
product.description as product_description,
product.fast_key as product_fast_key,
product.pname as product_pname,
product.sku as product_sku,
product_type.id as product_type_id,product_type.pkey as product_type_pkey,product_type.pname as product_type_pname 
  FROM 
  product,
  product_type as product_type  
 WHERE 
 product.product_type_id = product_type.id; 



-- VIEW FOR   InvoiceLineIn
Select '
Create view for InvoiceLineIn' as dc;
CREATE OR REPLACE VIEW invoice_line_in_view as 
SELECT invoice_line_in.id as invoice_line_in_id,
invoice_line_in.pkey as invoice_line_in_pkey,
invoice_line_in.ask_quantity as invoice_line_in_ask_quantity,
invoice_line_in.created_date as invoice_line_in_created_date,
invoice_line_in.descount as invoice_line_in_descount,
invoice_line_in.invoice_date as invoice_line_in_invoice_date,
invoice_line_in.orden as invoice_line_in_orden,
invoice_line_in.status as invoice_line_in_status,
invoice_line_in.supply_date as invoice_line_in_supply_date,
invoice_line_in.supply_quantity as invoice_line_in_supply_quantity,
invoice_line_in.tax_porcent as invoice_line_in_tax_porcent,
invoice_line_in.total as invoice_line_in_total,
invoice_line_in.total_cost as invoice_line_in_total_cost,
invoice_line_in.unit_cost as invoice_line_in_unit_cost,
comercial_document.id as comercial_document_id,comercial_document.pkey as comercial_document_pkey,comercial_document.pname as comercial_document_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,
stock_rack.id as stock_rack_id, stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname 
  FROM 
  invoice_line_in,
  comercial_document_out as comercial_document,
  product as product,
  stock_rack_product as stock_rack_product,
  contract_out as contract,
  user_lon as user_autor,
  comercial_document_type_out as comercial_document_type,
  product_type as product_type,
  stock_rack as stock_rack,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  work_space as work_space  
 WHERE 
 invoice_line_in.comercial_document_id = comercial_document.id
 AND invoice_line_in.product_id = product.id
 AND invoice_line_in.stock_rack_product_id = stock_rack_product.id
 AND comercial_document.contract_id = contract.id
 AND comercial_document.user_autor_id = user_autor.id
 AND comercial_document.comercial_document_type_id = comercial_document_type.id
 AND product.product_type_id = product_type.id
 AND stock_rack_product.stock_rack_id = stock_rack.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND stock_rack.work_space_id = work_space.id; 



-- VIEW FOR   InvoiceLineOut
Select '
Create view for InvoiceLineOut' as dc;
CREATE OR REPLACE VIEW invoice_line_out_view as 
SELECT invoice_line_out.id as invoice_line_out_id,
invoice_line_out.pkey as invoice_line_out_pkey,
invoice_line_out.ask_quantity as invoice_line_out_ask_quantity,
invoice_line_out.created_date as invoice_line_out_created_date,
invoice_line_out.descount as invoice_line_out_descount,
invoice_line_out.invoice_date as invoice_line_out_invoice_date,
invoice_line_out.orden as invoice_line_out_orden,
invoice_line_out.status as invoice_line_out_status,
invoice_line_out.supply_date as invoice_line_out_supply_date,
invoice_line_out.supply_quantity as invoice_line_out_supply_quantity,
invoice_line_out.tax_porcent as invoice_line_out_tax_porcent,
invoice_line_out.total as invoice_line_out_total,
invoice_line_out.total_cost as invoice_line_out_total_cost,
invoice_line_out.unit_cost as invoice_line_out_unit_cost,
comercial_document.id as comercial_document_id,comercial_document.pkey as comercial_document_pkey,comercial_document.pname as comercial_document_pname,
product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,
stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,
contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,
user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,
comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,
product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,
stock_rack.id as stock_rack_id, stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,
program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname 
  FROM 
  invoice_line_out,
  comercial_document_in as comercial_document,
  product as product,
  stock_rack_product as stock_rack_product,
  contract_in as contract,
  user_lon as user_autor,
  comercial_document_type_in as comercial_document_type,
  product_type as product_type,
  stock_rack as stock_rack,
  program_base_time_period as program_base_time_period,
  third_person as third_person,
  work_space as work_space  
 WHERE 
 invoice_line_out.comercial_document_id = comercial_document.id
 AND invoice_line_out.product_id = product.id
 AND invoice_line_out.stock_rack_product_id = stock_rack_product.id
 AND comercial_document.contract_id = contract.id
 AND comercial_document.user_autor_id = user_autor.id
 AND comercial_document.comercial_document_type_id = comercial_document_type.id
 AND product.product_type_id = product_type.id
 AND stock_rack_product.stock_rack_id = stock_rack.id
 AND contract.program_base_time_period_id = program_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND stock_rack.work_space_id = work_space.id; 



-- VIEW FOR   Appointment
Select '
Create view for Appointment' as dc;
CREATE OR REPLACE VIEW appointment_view as 
SELECT appointment.id as appointment_id,
appointment.pkey as appointment_pkey,
appointment.end_hour as appointment_end_hour,
appointment.end_minute as appointment_end_minute,
appointment.pname as appointment_pname,
appointment.start_hour as appointment_start_hour,
appointment.start_minute as appointment_start_minute,
appointment.week_day as appointment_week_day,
contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,
work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,
departament_job_instance.id as departament_job_instance_id,departament_job_instance.pkey as departament_job_instance_pkey,departament_job_instance.pname as departament_job_instance_pname,
departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,
third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,
work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,
departament_job.id as departament_job_id, departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,
base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,
departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,
base.id as base_id, base.pkey as base_pkey,base.pname as base_pname 
  FROM 
  appointment,
  contract_out as contract,
  work_space as work_space,
  departament_job_instance as departament_job_instance,
  departament_base_time_period as departament_base_time_period,
  third_person as third_person,
  work_space_group as work_space_group,
  departament_job as departament_job,
  base_time_period as base_time_period,
  departament as departament,
  base as base  
 WHERE 
 appointment.contract_id = contract.id
 AND appointment.work_space_id = work_space.id
 AND appointment.departament_job_instance_id = departament_job_instance.id
 AND contract.departament_base_time_period_id = departament_base_time_period.id
 AND contract.third_person_id = third_person.id
 AND work_space.work_space_group_id = work_space_group.id
 AND departament_job_instance.departament_job_id = departament_job.id
 AND departament_base_time_period.base_time_period_id = base_time_period.id
 AND departament_base_time_period.departament_id = departament.id
 AND work_space_group.base_id = base.id; 



-- VIEW FOR   Account
Select '
Create view for Account' as dc;
CREATE OR REPLACE VIEW account_view as 
SELECT account.id as account_id,
account.pkey as account_pkey,
account.description as account_description,
account.pname as account_pname,
account.type as account_type 
  FROM 
  account ; 



-- VIEW FOR   Airport
Select '
Create view for Airport' as dc;
CREATE OR REPLACE VIEW airport_view as 
SELECT airport.id as airport_id,
airport.pkey as airport_pkey,
airport.pname as airport_pname 
  FROM 
  airport ; 



-- VIEW FOR   Fligth
Select '
Create view for Fligth' as dc;
CREATE OR REPLACE VIEW fligth_view as 
SELECT fligth.id as fligth_id,
fligth.pkey as fligth_pkey,
fligth.pname as fligth_pname,
from_airport.id as from_airport_id,from_airport.pkey as from_airport_pkey,from_airport.pname as from_airport_pname,
to_airport.id as to_airport_id,to_airport.pkey as to_airport_pkey,to_airport.pname as to_airport_pname,
plane.id as plane_id,plane.pkey as plane_pkey,plane.pname as plane_pname,
la_compania.id as la_compania_id, la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname 
  FROM 
  fligth,
  airport as from_airport,
  airport as to_airport,
  plane as plane,
  air_company as la_compania  
 WHERE 
 fligth.from_airport_id = from_airport.id
 AND fligth.to_airport_id = to_airport.id
 AND fligth.plane_id = plane.id
 AND plane.la_compania_id = la_compania.id; 



-- VIEW FOR   FligthInstance
Select '
Create view for FligthInstance' as dc;
CREATE OR REPLACE VIEW fligth_instance_view as 
SELECT fligth_instance.id as fligth_instance_id,
fligth_instance.pkey as fligth_instance_pkey,
fligth_instance.in_local_date_time as fligth_instance_in_local_date_time,
fligth_instance.out_local_date_time as fligth_instance_out_local_date_time,
fligth_instance.pname as fligth_instance_pname,
the_fligth.id as the_fligth_id,the_fligth.pkey as the_fligth_pkey,the_fligth.pname as the_fligth_pname,
from_airport.id as from_airport_id, from_airport.pkey as from_airport_pkey,from_airport.pname as from_airport_pname,
to_airport.id as to_airport_id, to_airport.pkey as to_airport_pkey,to_airport.pname as to_airport_pname,
plane.id as plane_id, plane.pkey as plane_pkey,plane.pname as plane_pname,
la_compania.id as la_compania_id, la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname 
  FROM 
  fligth_instance,
  fligth as the_fligth,
  airport as from_airport,
  airport as to_airport,
  plane as plane,
  air_company as la_compania  
 WHERE 
 fligth_instance.the_fligth_id = the_fligth.id
 AND the_fligth.from_airport_id = from_airport.id
 AND the_fligth.to_airport_id = to_airport.id
 AND the_fligth.plane_id = plane.id
 AND plane.la_compania_id = la_compania.id; 



-- VIEW FOR   AirCompany
Select '
Create view for AirCompany' as dc;
CREATE OR REPLACE VIEW air_company_view as 
SELECT air_company.id as air_company_id,
air_company.pkey as air_company_pkey,
air_company.pname as air_company_pname 
  FROM 
  air_company ; 



-- VIEW FOR   Plane
Select '
Create view for Plane' as dc;
CREATE OR REPLACE VIEW plane_view as 
SELECT plane.id as plane_id,
plane.pkey as plane_pkey,
plane.plate as plane_plate,
plane.pname as plane_pname,
plane.seats as plane_seats,
la_compania.id as la_compania_id,la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname 
  FROM 
  plane,
  air_company as la_compania  
 WHERE 
 plane.la_compania_id = la_compania.id; 



-- VIEW FOR   MeUsrInterface
Select '
Create view for MeUsrInterface' as dc;
CREATE OR REPLACE VIEW me_usr_interface_view as 
SELECT me_usr_interface.id as me_usr_interface_id,
me_usr_interface.pkey as me_usr_interface_pkey,
me_usr_interface.dc as me_usr_interface_dc,
me_usr_interface.label as me_usr_interface_label,
me_usr_interface.level as me_usr_interface_level 
  FROM 
  me_usr_interface ; 



-- VIEW FOR   FormLon
Select '
Create view for FormLon' as dc;
CREATE OR REPLACE VIEW form_lon_view as 
SELECT form_lon.id as form_lon_id,
form_lon.pkey as form_lon_pkey,
form_lon.pname as form_lon_pname 
  FROM 
  form_lon ; 



-- VIEW FOR   FormLonField
Select '
Create view for FormLonField' as dc;
CREATE OR REPLACE VIEW form_lon_field_view as 
SELECT form_lon_field.id as form_lon_field_id,
form_lon_field.pkey as form_lon_field_pkey,
form_lon_field.pname as form_lon_field_pname,
form_lon.id as form_lon_id,form_lon.pkey as form_lon_pkey,form_lon.pname as form_lon_pname 
  FROM 
  form_lon_field,
  form_lon as form_lon  
 WHERE 
 form_lon_field.form_lon_id = form_lon.id; 





    -----
    -- Add user admin to db
    INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('admin','admin','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','adm@gg.com','ADM','admin') returning id;

    ----
    -- Add many users type SUBADM db
    
-- 1
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('subadmin0','sadmin0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','sadm0@gg.com','SUBADM','sad0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_0x0','agent_0x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_0x0@gg0.com','AGENT','agent_0x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_0x0','third_0x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_0x0@t00h0.com','THIRD','third_0x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_0x1','agent_0x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_0x1@gg0.com','AGENT','agent_0x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_0x1','third_0x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_0x1@t1-2h0.com','THIRD','third_0x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_0x2','agent_0x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_0x2@gg0.com','AGENT','agent_0x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_0x2','third_0x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_0x2@t2-4h0.com','THIRD','third_0x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_0x3','agent_0x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_0x3@gg0.com','AGENT','agent_0x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_0x3','third_0x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_0x3@t3-6h0.com','THIRD','third_0x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_0x4','agent_0x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_0x4@gg0.com','AGENT','agent_0x4') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_0x4','third_0x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_0x4@t4-8h0.com','THIRD','third_0x4') returning id;
-- 2
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('subadmin1','sadmin1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','sadm1@gg.com','SUBADM','sad1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_1x0','agent_1x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_1x0@gg1.com','AGENT','agent_1x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_1x0','third_1x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_1x0@t05h1.com','THIRD','third_1x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_1x1','agent_1x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_1x1@gg1.com','AGENT','agent_1x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_1x1','third_1x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_1x1@t13h1.com','THIRD','third_1x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_1x2','agent_1x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_1x2@gg1.com','AGENT','agent_1x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_1x2','third_1x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_1x2@t21h1.com','THIRD','third_1x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_1x3','agent_1x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_1x3@gg1.com','AGENT','agent_1x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_1x3','third_1x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_1x3@t3-1h1.com','THIRD','third_1x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_1x4','agent_1x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_1x4@gg1.com','AGENT','agent_1x4') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_1x4','third_1x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_1x4@t4-3h1.com','THIRD','third_1x4') returning id;
-- 3
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('subadmin2','sadmin2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','sadm2@gg.com','SUBADM','sad2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_2x0','agent_2x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_2x0@gg2.com','AGENT','agent_2x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_2x0','third_2x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_2x0@t010h2.com','THIRD','third_2x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_2x1','agent_2x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_2x1@gg2.com','AGENT','agent_2x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_2x1','third_2x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_2x1@t18h2.com','THIRD','third_2x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_2x2','agent_2x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_2x2@gg2.com','AGENT','agent_2x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_2x2','third_2x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_2x2@t26h2.com','THIRD','third_2x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_2x3','agent_2x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_2x3@gg2.com','AGENT','agent_2x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_2x3','third_2x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_2x3@t34h2.com','THIRD','third_2x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_2x4','agent_2x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_2x4@gg2.com','AGENT','agent_2x4') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_2x4','third_2x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_2x4@t42h2.com','THIRD','third_2x4') returning id;
-- 4
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('subadmin3','sadmin3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','sadm3@gg.com','SUBADM','sad3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_3x0','agent_3x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_3x0@gg3.com','AGENT','agent_3x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_3x0','third_3x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_3x0@t015h3.com','THIRD','third_3x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_3x1','agent_3x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_3x1@gg3.com','AGENT','agent_3x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_3x1','third_3x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_3x1@t113h3.com','THIRD','third_3x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_3x2','agent_3x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_3x2@gg3.com','AGENT','agent_3x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_3x2','third_3x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_3x2@t211h3.com','THIRD','third_3x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_3x3','agent_3x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_3x3@gg3.com','AGENT','agent_3x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_3x3','third_3x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_3x3@t39h3.com','THIRD','third_3x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_3x4','agent_3x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_3x4@gg3.com','AGENT','agent_3x4') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_3x4','third_3x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_3x4@t47h3.com','THIRD','third_3x4') returning id;
-- 5
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('subadmin4','sadmin4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','sadm4@gg.com','SUBADM','sad4') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_4x0','agent_4x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_4x0@gg4.com','AGENT','agent_4x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_4x0','third_4x0','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_4x0@t020h4.com','THIRD','third_4x0') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_4x1','agent_4x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_4x1@gg4.com','AGENT','agent_4x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_4x1','third_4x1','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_4x1@t118h4.com','THIRD','third_4x1') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_4x2','agent_4x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_4x2@gg4.com','AGENT','agent_4x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_4x2','third_4x2','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_4x2@t216h4.com','THIRD','third_4x2') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_4x3','agent_4x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_4x3@gg4.com','AGENT','agent_4x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_4x3','third_4x3','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_4x3@t314h4.com','THIRD','third_4x3') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('agent_4x4','agent_4x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','agent_4x4@gg4.com','AGENT','agent_4x4') returning id;
INSERT INTO user_lon(pname,username,password,email,type_lon,pkey) VALUES ('third_4x4','third_4x4','$2a$10$HBZSukSb/GalARyzAV5p7u2gn/FBTEnrxROhaLdTMDNCA1kDHaJfe','third_4x4@t412h4.com','THIRD','third_4x4') returning id;

 INSERT INTO departament(pkey,description,fast_key,pname) 
 VALUES 
 ('CNTBLDD','Sin Descripción para Contabilidad',0,'Contabilidad') returning id;
        

 INSERT INTO departament(pkey,description,fast_key,pname) 
 VALUES 
 ('VNTS','Sin Descripción para Ventas',1,'Ventas') returning id;
        

 INSERT INTO departament(pkey,description,fast_key,pname) 
 VALUES 
 ('CMPRS','Sin Descripción para Compras',2,'Compras') returning id;
        

 INSERT INTO departament(pkey,description,fast_key,pname) 
 VALUES 
 ('PRCN','Sin Descripción para Operacion',3,'Operacion') returning id;
        

 INSERT INTO departament(pkey,description,fast_key,pname) 
 VALUES 
 ('SSTMS','Sin Descripción para Sistemas',4,'Sistemas') returning id;
        

    INSERT INTO base(pkey,pname) VALUES ('MTR','Matriz') returning id;


    INSERT INTO base(pkey,pname) VALUES ('SCRNT','Sucursal Nte') returning id;


    INSERT INTO base(pkey,pname) VALUES ('SCRSR','Sucursal Sur') returning id;


    INSERT INTO base(pkey,pname) VALUES ('ALM','Almacenes') returning id;


CREATE TABLE atomiclon(pkey varchar(64) unique, val int8);


INSERT INTO atomiclon(pkey,val) VALUES ('comercialDocumentOut_folio',0);
INSERT INTO atomiclon(pkey,val) VALUES ('comercialDocumentIn_folio',0);

