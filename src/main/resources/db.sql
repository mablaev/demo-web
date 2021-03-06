CREATE TABLE "ACCOUNT_DEMO"."DEMO_USER" (
  ID NUMBER NOT NULL,
  EMAIL VARCHAR2(255 CHAR) NOT NULL,
  PASSWORD VARCHAR2(255 CHAR) NOT NULL,
  REGISTRATION_DATE DATE DEFAULT SYSDATE  NOT NULL,
  ROLE VARCHAR2(12 CHAR) DEFAULT 'USER' NOT NULL,
  PRIMARY KEY (ID)
);
ALTER TABLE "ACCOUNT_DEMO"."DEMO_USER" ADD CONSTRAINT EMAIL_UQ UNIQUE (EMAIL);
ALTER TABLE "ACCOUNT_DEMO"."DEMO_USER" ADD CONSTRAINT "ROLE_CHECK" CHECK ("ROLE" IN ('USER','ADMIN'));

CREATE TABLE "ACCOUNT_DEMO"."USER_ACCOUNT" (
  ID NUMBER NOT NULL,
  USER_ID NUMBER NOT NULL,
  BALANCE NUMBER(*, 2),
  PRIMARY KEY (ID)
);

ALTER TABLE "ACCOUNT_DEMO"."USER_ACCOUNT" 
ADD CONSTRAINT "USR_BALANCE_USR_ID" FOREIGN KEY ("USER_ID")
REFERENCES "ACCOUNT_DEMO"."DEMO_USER" ("ID");

CREATE TABLE "ACCOUNT_DEMO"."TOPUP_LOG"(
  ID NUMBER NOT NULL,
  ADMIN_ID NUMBER NOT NULL,
  USER_ID NUMBER NOT NULL,
  TOPUP_DATE DATE DEFAULT SYSDATE NOT NULL,
  AMOUNT NUMBER(*, 2),
  PRIMARY KEY (ID)
);

ALTER TABLE "ACCOUNT_DEMO"."TOPUP_LOG" 
ADD CONSTRAINT "TOPUP_ADM_ID" FOREIGN KEY ("ADMIN_ID")
REFERENCES "ACCOUNT_DEMO"."DEMO_USER" ("ID");

ALTER TABLE "ACCOUNT_DEMO"."TOPUP_LOG" 
ADD CONSTRAINT "TOPUP_USR_ID" FOREIGN KEY ("USER_ID")
REFERENCES "ACCOUNT_DEMO"."DEMO_USER" ("ID");
