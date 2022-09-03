-- 카테고리
INSERT INTO category (name) VALUES ('상의');
INSERT INTO category (name) VALUES ('아우터');
INSERT INTO category (name) VALUES ('바지');

-- A 브랜드 제품
INSERT INTO product (name, price, category_id) VALUES ('a_상의', 11200,  1);
INSERT INTO product (name, price,   category_id) VALUES ('a_아우터', 5500,  2);
INSERT INTO product (name, price,   category_id) VALUES ('a_바지', 4200,  3);

-- B 브랜드 제품
INSERT INTO product (name, price,   category_id) VALUES ('b_상의', 10500,  1);
INSERT INTO product (name, price,   category_id) VALUES ('b_아우터', 5900,  2);
INSERT INTO product (name, price,   category_id) VALUES ('b_바지', 3800,  3);

-- C 브랜드 제품
INSERT INTO product (name, price,   category_id) VALUES ('c_상의', 10000,  1);
INSERT INTO product (name, price,   category_id) VALUES ('c_아우터', 6200,  2);
INSERT INTO product (name, price,   category_id) VALUES ('c_바지', 3300,  3);
INSERT INTO product (name, price,   category_id) VALUES ('c_바지2', 1300, 3);
