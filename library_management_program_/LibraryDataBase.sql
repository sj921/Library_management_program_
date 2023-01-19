/* 
     ?��?���? �?�? ?��로그?�� DB
     
     * TABLE 목록
     
     - admins                 �?리자 직원 ?���?
     - members                ?��?�� ?���?
     - books                  ?��?��?���?
     - locations              ?��?�� ?���? ?���?
     - check_out_info         ??�? ?���?
     - readingroom            ?��?��?�� ?���?
     - seat_use_details       ?��?��?�� ?��?��?��?��
     - image_information      ?��로그?��?�� ?��?��?��?�� ?��미�? ?���?
     
     * SEQUENCE
          
     - admin_num_seq          �?리자 번호
     - mem_num_seq            ?��?�� 번호
     - book_id_seq            ?���? 번호
     - check_out_id_seq       ??출내?�� ?��?��?�� 
     - user_detail_id_seq     ?��?��?�� 좌석 ?��?��?��?�� ?��?��?��
     
*/
SELECT * FROM tabs;
SELECT * FROM user_sequences;

-- �?리자 직원 ?���? ?��?���?
CREATE TABLE admins(
     admin_num      NUMBER(11)  CONSTRAINT ad_ad_num_pk  PRIMARY KEY,      -- ?��?�� 번호  (PK)
     admin_name     VARCHAR2(255)  CONSTRAINT ad_ad_name_nn NOT NULL,      -- ?��?�� ?���?
     admin_pw       VARCHAR2(255)  CONSTRAINT ad_ad_pw_nn NOT NULL,        -- ?��?�� 비�?번호
     admin_phone    VARCHAR2(255)  CONSTRAINT ad_ad_phone_uk UNIQUE,       -- ?��?�� ?��?���? (UK)
     admin_email    VARCHAR2(255)  CONSTRAINT ad_ad_email_nn NOT NULL,     -- ?��?�� ?��메일
     admin_address  VARCHAR2(255)  CONSTRAINT ad_ad_address_nn NOT NULL,   -- ?��?�� 주소
     admin_registrationdate  VARCHAR2(255)  DEFAULT sysdate,               -- ?��?�� ?��록일
     admin_updatedate    VARCHAR2(255),                                     -- ?��?�� ?��?��?��
     admin_note     VARCHAR2(255)                                          -- 비고
);

-- ?��?�� ?���? ?��?���?
CREATE TABLE members (
     mem_num        NUMBER(11) CONSTRAINT m_m_num_pk  PRIMARY KEY,    -- ?��?�� 번호  (PK)
     mem_name       VARCHAR2(255) CONSTRAINT m_m_name_nn NOT NULL,    -- ?��?�� ?���?
     mem_id         VARCHAR2(255) CONSTRAINT m_m_id_uk UNIQUE,        -- ?��?�� ?��?��?�� (UK)
     mem_pw         VARCHAR2(255) CONSTRAINT m_m_pw_nn NOT NULL,      -- ?��?�� 비�?번호
     mem_birthday   CHAR(6) CONSTRAINT m_m_birthday_nn NOT NULL,      -- ?��?�� ?��?��?��?��
     mem_sex        CHAR(1) CONSTRAINT m_m_sex_boolean CHECK(mem_sex IN ('0', '1')), -- ?��?�� ?���? (0,1 �? 구분)
     mem_phone      VARCHAR2(255) CONSTRAINT m_m_phone_uk UNIQUE,     -- ?��?�� ?��?���? (UK)
     mem_email      VARCHAR2(255) CONSTRAINT m_m_email_nn NOT NULL,   -- ?��?�� ?��메일
     mem_address    VARCHAR2(255) CONSTRAINT m_m_address_nn NOT NULL, -- ?��?�� 주소
     mem_registrationdate VARCHAR2(255)     DEFAULT sysdate,          -- ?��?�� ?��록일
     mem_updatedate VARCHAR2(255)                                     -- ?��?�� ?���? ?��?��?��
     mem_note       VARCHAR2(255)                                     -- 비고
);

-- ?��?�� ?���? ?��?���?
CREATE TABLE books(
     book_id        VARCHAR2(255)  CONSTRAINT b_b_id_pk  PRIMARY KEY,      -- ?��?�� ?��록번?�� (PK)
     book_title     VARCHAR2(255)  CONSTRAINT b_b_title_nn NOT NULL,       -- ?��?�� ?���?
     book_author    VARCHAR2(255)  CONSTRAINT b_b_author_nn NOT NULL,      -- ?��?�� ???��
     book_publisher VARCHAR2(255)  CONSTRAINT b_b_publisher_nn NOT NULL,   -- ?��?�� 출판?��
     book_isbn  VARCHAR2(255) CONSTRAINT b_b_isbn_uk UNIQUE,               -- ?��?�� ISBN 번호  (UK)
     book_bias      NUMBER(11) DEFAULT 1,                                  -- ?��?�� ?��권차 (기본�? 1)
     book_duplicates NUMBER(11) DEFAULT 1,                                 -- ?��?�� 복권?�� (기본�? 1)
     book_price     NUMBER(11),                                            -- ?��?�� �?�?
     location_id    CHAR(1) CONSTRAINT b_loc_id_fk REFERENCES locations(location_id), -- ?��?�� ?���? (FK)
     book_registrationdate   VARCHAR2(255) DEFAULT sysdate,               -- ?��?�� ?��록일
     book_updatedate     VARCHAR2(255)                                     -- ?��?�� ?���? 
     book_note      VARCHAR2(255)                                          -- 비고
);

--CREATE TABLE callsign(
--     book_callsign  VARCHAR2(255)  CONSTRAINT c_b_cs_pk  PRIMARY KEY,
--     cs_classification_code VARCHAR2(255),
--     cs_sign           VARCHAR2(255),
--     cs_bias           NUMBER(11),
--     cs_duplicates     NUMBER(11)
--);

-- ?��?�� 분류 / ?���? ?��?���?
CREATE TABLE locations(
     location_id    CHAR(1) CONSTRAINT l_l_id_pk  PRIMARY KEY,        -- ?��?�� ?���? ?��?��?��   (PK)
     location_name  VARCHAR2(255) CONSTRAINT l_l_name_nn NOT NULL     -- ?��?�� ?���? ?���?
);

-- ??�? ?��?�� ?��?���?
CREATE TABLE check_out_info(
     check_out_id   NUMBER(11) CONSTRAINT coi_coi_id_pk PRIMARY KEY,                 -- ???�� ?��?��?�� (PK)
     book_id        VARCHAR2(255) CONSTRAINT coi_b_id_fk REFERENCES books(book_id),  -- ???�� ?��?��   (FK)
     mem_num        NUMBER(11) CONSTRAINT coi_m_num_fk REFERENCES members(mem_num),  -- ?��?�� 번호   (FK)
     check_out_date VARCHAR2(255)           DEFAULT sysdate,                         -- ???�� ?���?
     expect_return_date VARCHAR2(255)       DEFAULT sysdate + (INTERVAL '7' DAY),    -- 반납 ?��?�� ?���?
     check_in_date  VARCHAR2(255)                                                    -- 반납 ?���?
);

-- ?��?��?�� ?���? ?��?���?
CREATE TABLE readingroom(
     seat_num       NUMBER(11) CONSTRAINT r_s_num_pk PRIMARY KEY,     -- 좌석 번호  (PK)
     table_divider  CHAR(1),                                          -- 칸막?�� ?���?
);

-- 좌석 ?��?�� ?���? ?��?���?
CREATE TABLE seat_use_details(
     use_id     NUMBER(11) CONSTRAINT sud_u_id_pk PRIMARY KEY,                        -- ?��?��?�� ?��?��?��?�� ?��?��?��     (PK)
     mem_num    NUMBER(11) CONSTRAINT sud_m_num_fk REFERENCES members(mem_num),       -- ?��?��?���?   (FK)
     seat_num   NUMBER(11) CONSTRAINT sud_s_num_fk REFERENCES readingroom(seat_num),  -- 좌석 번호  (FK)
     start_time VARCHAR2(255) DEFAULT to_char(sysdate, 'yyyy.mm.dd hh24:mi'),         -- ?��?�� ?��?�� ?���?
     end_time   VARCHAR2(255)                                                         -- ?��?�� 종료 ?���?
);

-- ?��미�? ?���? ?��?���?
CREATE TABLE IMAGE_INFORMATION(
     image_id  NUMBER(11) CONSTRAINT i_i_id_pk PRIMARY KEY,                          -- ?��미�? ?��?��?��     (PK)
     image_name     VARCHAR2(255) CONSTRAINT i_i_name_nn NOT NULL,                   -- ?��미�? ?���?
     image_byte_info      BLOB CONSTRAINT i_i_b_info_nn NOT NULL                     -- ?��미�? byte ?���?
);

-- ?��?�� 번호 ?��???��
CREATE SEQUENCE mem_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;
     
-- ?��?�� 번호 ?��???��
CREATE SEQUENCE admin_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

-- ?��?�� ?��록번?�� ?��???��
CREATE SEQUENCE book_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 999999999
     CYCLE
     NOCACHE;

-- ?��?��?�� ?��?�� ?��?�� ?��?��?�� ?��???��
CREATE SEQUENCE user_detail_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

-- ???�� ?��?��?�� ?��???��
CREATE SEQUENCE check_out_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;
