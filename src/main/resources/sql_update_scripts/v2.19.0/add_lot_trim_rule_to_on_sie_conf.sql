-- Add LOT_TRIM_RULE column to ON_SITE_CONF

-- Oracle: add column only if it does not already exist
DECLARE
v_count NUMBER := 0;
BEGIN
SELECT COUNT(*) INTO v_count FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'ON_SITE_CONF' AND COLUMN_NAME = 'LOT_TRIM_RULE';
IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE ON_SITE_CONF ADD (LOT_TRIM_RULE VARCHAR2(255))';
END IF;
END;
/

-- Optionally set a sensible default for test/dev sites (uncomment and adjust as needed):
-- UPDATE ON_SITE_CONF SET LOT_TRIM_RULE = 'regex:^L.{4,}A$' WHERE SITE = 'BK' AND (LOT_TRIM_RULE IS NULL OR TRIM(LOT_TRIM_RULE) = '');
--
--update on_site_conf make mes_type field nullable
ALTER TABLE on_site_conf MODIFY mes_type NULL;
