-- related to ticket https://jira.onsemi.com/browse/CE-1013

ALTER TABLE ON_FAB_CONF ADD SECOND_LOTG_QUERY VARCHAR2(1) DEFAULT 'N' NOT NULL;
