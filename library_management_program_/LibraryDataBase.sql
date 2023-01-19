/* 
     ?èÑ?ÑúÍ¥? Í¥?Î¶? ?îÑÎ°úÍ∑∏?û® DB
     
     * TABLE Î™©Î°ù
     
     - admins                 Í¥?Î¶¨Ïûê ÏßÅÏõê ?†ïÎ≥?
     - members                ?öå?õê ?†ïÎ≥?
     - books                  ?èÑ?Ñú?†ïÎ≥?
     - locations              ?èÑ?Ñú ?úÑÏπ? ?†ïÎ≥?
     - check_out_info         ??Ï∂? ?†ïÎ≥?
     - readingroom            ?ó¥?ûå?ã§ ?†ïÎ≥?
     - seat_use_details       ?ó¥?ûå?ã§ ?Ç¨?ö©?Ç¥?ó≠
     - image_information      ?îÑÎ°úÍ∑∏?û®?óê ?Ç¨?ö©?êò?äî ?ù¥ÎØ∏Ï? ?†ïÎ≥?
     
     * SEQUENCE
          
     - admin_num_seq          Í¥?Î¶¨Ïûê Î≤àÌò∏
     - mem_num_seq            ?öå?õê Î≤àÌò∏
     - book_id_seq            ?ì±Î°? Î≤àÌò∏
     - check_out_id_seq       ??Ï∂úÎÇ¥?ó≠ ?ïÑ?ù¥?îî 
     - user_detail_id_seq     ?ó¥?ûå?ã§ Ï¢åÏÑù ?Ç¨?ö©?Ç¥?ó≠ ?ïÑ?ù¥?îî
     
*/
SELECT * FROM tabs;
SELECT * FROM user_sequences;

-- Í¥?Î¶¨Ïûê ÏßÅÏõê ?†ïÎ≥? ?Öå?ù¥Î∏?
CREATE TABLE admins(
     admin_num      NUMBER(11)  CONSTRAINT ad_ad_num_pk  PRIMARY KEY,      -- ?Ç¨?õê Î≤àÌò∏  (PK)
     admin_name     VARCHAR2(255)  CONSTRAINT ad_ad_name_nn NOT NULL,      -- ?Ç¨?õê ?ù¥Î¶?
     admin_pw       VARCHAR2(255)  CONSTRAINT ad_ad_pw_nn NOT NULL,        -- ?Ç¨?õê ÎπÑÎ?Î≤àÌò∏
     admin_phone    VARCHAR2(255)  CONSTRAINT ad_ad_phone_uk UNIQUE,       -- ?Ç¨?õê ?ó∞?ùΩÏ≤? (UK)
     admin_email    VARCHAR2(255)  CONSTRAINT ad_ad_email_nn NOT NULL,     -- ?Ç¨?õê ?ù¥Î©îÏùº
     admin_address  VARCHAR2(255)  CONSTRAINT ad_ad_address_nn NOT NULL,   -- ?Ç¨?õê Ï£ºÏÜå
     admin_registrationdate  VARCHAR2(255)  DEFAULT sysdate,               -- ?Ç¨?õê ?ì±Î°ùÏùº
     admin_updatedate    VARCHAR2(255),                                     -- ?Ç¨?õê ?àò?†ï?ùº
     admin_note     VARCHAR2(255)                                          -- ÎπÑÍ≥†
);

-- ?öå?õê ?†ïÎ≥? ?Öå?ù¥Î∏?
CREATE TABLE members (
     mem_num        NUMBER(11) CONSTRAINT m_m_num_pk  PRIMARY KEY,    -- ?öå?õê Î≤àÌò∏  (PK)
     mem_name       VARCHAR2(255) CONSTRAINT m_m_name_nn NOT NULL,    -- ?öå?õê ?ù¥Î¶?
     mem_id         VARCHAR2(255) CONSTRAINT m_m_id_uk UNIQUE,        -- ?öå?õê ?ïÑ?ù¥?îî (UK)
     mem_pw         VARCHAR2(255) CONSTRAINT m_m_pw_nn NOT NULL,      -- ?öå?õê ÎπÑÎ?Î≤àÌò∏
     mem_birthday   CHAR(6) CONSTRAINT m_m_birthday_nn NOT NULL,      -- ?öå?õê ?Éù?ÖÑ?õî?ùº
     mem_sex        CHAR(1) CONSTRAINT m_m_sex_boolean CHECK(mem_sex IN ('0', '1')), -- ?öå?õê ?Ñ±Î≥? (0,1 Î°? Íµ¨Î∂Ñ)
     mem_phone      VARCHAR2(255) CONSTRAINT m_m_phone_uk UNIQUE,     -- ?öå?õê ?ó∞?ùΩÏ≤? (UK)
     mem_email      VARCHAR2(255) CONSTRAINT m_m_email_nn NOT NULL,   -- ?öå?õê ?ù¥Î©îÏùº
     mem_address    VARCHAR2(255) CONSTRAINT m_m_address_nn NOT NULL, -- ?öå?õê Ï£ºÏÜå
     mem_registrationdate VARCHAR2(255)     DEFAULT sysdate,          -- ?öå?õê ?ì±Î°ùÏùº
     mem_updatedate VARCHAR2(255)                                     -- ?öå?õê ?†ïÎ≥? ?àò?†ï?ùº
     mem_note       VARCHAR2(255)                                     -- ÎπÑÍ≥†
);

-- ?èÑ?Ñú ?†ïÎ≥? ?Öå?ù¥Î∏?
CREATE TABLE books(
     book_id        VARCHAR2(255)  CONSTRAINT b_b_id_pk  PRIMARY KEY,      -- ?èÑ?Ñú ?ì±Î°ùÎ≤à?ò∏ (PK)
     book_title     VARCHAR2(255)  CONSTRAINT b_b_title_nn NOT NULL,       -- ?èÑ?Ñú ?†úÎ™?
     book_author    VARCHAR2(255)  CONSTRAINT b_b_author_nn NOT NULL,      -- ?èÑ?Ñú ???ûê
     book_publisher VARCHAR2(255)  CONSTRAINT b_b_publisher_nn NOT NULL,   -- ?èÑ?Ñú Ï∂úÌåê?Ç¨
     book_isbn  VARCHAR2(255) CONSTRAINT b_b_isbn_uk UNIQUE,               -- ?èÑ?Ñú ISBN Î≤àÌò∏  (UK)
     book_bias      NUMBER(11) DEFAULT 1,                                  -- ?èÑ?Ñú ?é∏Í∂åÏ∞® (Í∏∞Î≥∏Í∞? 1)
     book_duplicates NUMBER(11) DEFAULT 1,                                 -- ?èÑ?Ñú Î≥µÍ∂å?àò (Í∏∞Î≥∏Í∞? 1)
     book_price     NUMBER(11),                                            -- ?èÑ?Ñú Í∞?Í≤?
     location_id    CHAR(1) CONSTRAINT b_loc_id_fk REFERENCES locations(location_id), -- ?èÑ?Ñú ?úÑÏπ? (FK)
     book_registrationdate   VARCHAR2(255) DEFAULT sysdate,               -- ?èÑ?Ñú ?ì±Î°ùÏùº
     book_updatedate     VARCHAR2(255)                                     -- ?èÑ?Ñú ?†ïÎ≥? 
     book_note      VARCHAR2(255)                                          -- ÎπÑÍ≥†
);

--CREATE TABLE callsign(
--     book_callsign  VARCHAR2(255)  CONSTRAINT c_b_cs_pk  PRIMARY KEY,
--     cs_classification_code VARCHAR2(255),
--     cs_sign           VARCHAR2(255),
--     cs_bias           NUMBER(11),
--     cs_duplicates     NUMBER(11)
--);

-- ?èÑ?Ñú Î∂ÑÎ•ò / ?úÑÏπ? ?Öå?ù¥Î∏?
CREATE TABLE locations(
     location_id    CHAR(1) CONSTRAINT l_l_id_pk  PRIMARY KEY,        -- ?èÑ?Ñú ?úÑÏπ? ?ïÑ?ù¥?îî   (PK)
     location_name  VARCHAR2(255) CONSTRAINT l_l_name_nn NOT NULL     -- ?èÑ?Ñú ?úÑÏπ? ?ù¥Î¶?
);

-- ??Ï∂? ?Ç¥?ó≠ ?Öå?ù¥Î∏?
CREATE TABLE check_out_info(
     check_out_id   NUMBER(11) CONSTRAINT coi_coi_id_pk PRIMARY KEY,                 -- ???ó¨ ?ïÑ?ù¥?îî (PK)
     book_id        VARCHAR2(255) CONSTRAINT coi_b_id_fk REFERENCES books(book_id),  -- ???ó¨ ?èÑ?Ñú   (FK)
     mem_num        NUMBER(11) CONSTRAINT coi_m_num_fk REFERENCES members(mem_num),  -- ?öå?õê Î≤àÌò∏   (FK)
     check_out_date VARCHAR2(255)           DEFAULT sysdate,                         -- ???ó¨ ?Ç†Ïß?
     expect_return_date VARCHAR2(255)       DEFAULT sysdate + (INTERVAL '7' DAY),    -- Î∞òÎÇ© ?òà?†ï ?Ç†Ïß?
     check_in_date  VARCHAR2(255)                                                    -- Î∞òÎÇ© ?Ç†Ïß?
);

-- ?ó¥?ûå?ã§ ?†ïÎ≥? ?Öå?ù¥Î∏?
CREATE TABLE readingroom(
     seat_num       NUMBER(11) CONSTRAINT r_s_num_pk PRIMARY KEY,     -- Ï¢åÏÑù Î≤àÌò∏  (PK)
     table_divider  CHAR(1),                                          -- Ïπ∏Îßâ?ù¥ ?ó¨Î∂?
);

-- Ï¢åÏÑù ?ù¥?ö© ?†ïÎ≥? ?Öå?ù¥Î∏?
CREATE TABLE seat_use_details(
     use_id     NUMBER(11) CONSTRAINT sud_u_id_pk PRIMARY KEY,                        -- ?ó¥?ûå?ã§ ?Ç¨?ö©?Ç¥?ó≠ ?ïÑ?ù¥?îî     (PK)
     mem_num    NUMBER(11) CONSTRAINT sud_m_num_fk REFERENCES members(mem_num),       -- ?öå?õê?†ïÎ≥?   (FK)
     seat_num   NUMBER(11) CONSTRAINT sud_s_num_fk REFERENCES readingroom(seat_num),  -- Ï¢åÏÑù Î≤àÌò∏  (FK)
     start_time VARCHAR2(255) DEFAULT to_char(sysdate, 'yyyy.mm.dd hh24:mi'),         -- ?Ç¨?ö© ?ãú?ûë ?ãúÍ∞?
     end_time   VARCHAR2(255)                                                         -- ?Ç¨?ö© Ï¢ÖÎ£å ?ãúÍ∞?
);

-- ?ù¥ÎØ∏Ï? ?†ïÎ≥? ?Öå?ù¥Î∏?
CREATE TABLE IMAGE_INFORMATION(
     image_id  NUMBER(11) CONSTRAINT i_i_id_pk PRIMARY KEY,                          -- ?ù¥ÎØ∏Ï? ?ïÑ?ù¥?îî     (PK)
     image_name     VARCHAR2(255) CONSTRAINT i_i_name_nn NOT NULL,                   -- ?ù¥ÎØ∏Ï? ?ù¥Î¶?
     image_byte_info      BLOB CONSTRAINT i_i_b_info_nn NOT NULL                     -- ?ù¥ÎØ∏Ï? byte ?†ïÎ≥?
);

-- ?öå?õê Î≤àÌò∏ ?ãú???ä§
CREATE SEQUENCE mem_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;
     
-- ?Ç¨?õê Î≤àÌò∏ ?ãú???ä§
CREATE SEQUENCE admin_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

-- ?èÑ?Ñú ?ì±Î°ùÎ≤à?ò∏ ?ãú???ä§
CREATE SEQUENCE book_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 999999999
     CYCLE
     NOCACHE;

-- ?ó¥?ûå?ã§ ?Ç¨?ö© ?Ç¥?ó≠ ?ïÑ?ù¥?îî ?ãú???ä§
CREATE SEQUENCE user_detail_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

-- ???ó¨ ?ïÑ?ù¥?îî ?ãú???ä§
CREATE SEQUENCE check_out_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;
