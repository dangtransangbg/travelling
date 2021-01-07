
CREATE TABLE blog (
    id bigint NOT NULL auto_increment,
    content varchar(255),
    created_by varchar(255),
    created_date timestamp,
    keyword varchar(255),
    modified_by varchar(255),
    modified_date timestamp,
    status varchar(255),
    thumbnail varchar(255),
    title varchar(255),
    url varchar(255),
    user_id bigint,
    description varchar(255)
);

CREATE TABLE role (
    id bigint NOT NULL auto_increment,
    description varchar(255),
    role_name varchar(255)
);



CREATE TABLE tour (
    id bigint NOT NULL auto_increment,
    albums varchar(255),
    content varchar(255),
    created_by varchar(255),
    created_date timestamp,
    is_hot tinyint,
    keyword varchar(255),
    modified_by varchar(255),
    modified_date timestamp,
    name varchar(255),
    price double ,
    status varchar(255),
    thumbnail varchar(255),
    title varchar(255),
    tour_type integer,
    tour_category_id bigint,
    description text,
    destination_place varchar(255),
    policy text,
    regions integer,
    start_place varchar(255),
    "time" varchar(255),
    vehicle varchar(255)
);

CREATE TABLE tour_category (
    id bigint NOT NULL auto_increment,
    name varchar(255),
    category_type integer,
    continents integer
);

CREATE TABLE tour_detail (
    id bigint NOT NULL auto_increment,
    identity varchar(255),
    price bigint,
    price_baby bigint,
    price_children bigint,
    tour_id bigint,
    start_time timestamp
);


CREATE TABLE user (
    id bigint  NOT NULL auto_increment,
    address varchar(255),
    created_by varchar(255),
    created_date timestamp,
    email varchar(255),
    modified_by varchar(255),
    modified_date timestamp,
    name varchar(255),
    password varchar(255),
    phone varchar(255),
    sex varchar(255),
    status varchar(255),
    user_name varchar(255)
);

ALTER TABLE  blog
    ADD CONSTRAINT blog_pkey PRIMARY KEY (id);

ALTER TABLE permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (user_id, role_id);


ALTER TABLE role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);

ALTER TABLE tour_category
    ADD CONSTRAINT tour_category_pkey PRIMARY KEY (id);


ALTER TABLE tour_detail
    ADD CONSTRAINT tour_detail_pkey PRIMARY KEY (id);

ALTER TABLE tour
    ADD CONSTRAINT tour_pkey PRIMARY KEY (id);


ALTER TABLE user
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


ALTER TABLE user
    ADD CONSTRAINT uk_du5v5sr43g5bfnji4vb8hg5s3 UNIQUE (phone);


ALTER TABLE user
    ADD CONSTRAINT uk_k8d0f2n7n88w1a16yhua64onx UNIQUE (user_name);

ALTER TABLE user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

ALTER TABLE tour
    ADD CONSTRAINT fkffi2kohi26u77g2yfh2eg2m13 FOREIGN KEY (tour_category_id) REFERENCES tour_category(id);

ALTER TABLE tour_detail
    ADD CONSTRAINT fkgn4ymgxgryomlfwexh57rgfrp FOREIGN KEY (tour_id) REFERENCES tour(id);


ALTER TABLE blog
    ADD CONSTRAINT fkkr2fy24puc3x3sdnla4r1iok1 FOREIGN KEY (user_id) REFERENCES user(id);


ALTER TABLE permission
    ADD CONSTRAINT fkrvhjnns4bvlh4m1n97vb7vbar FOREIGN KEY (role_id) REFERENCES role(id);


ALTER TABLE permission
    ADD CONSTRAINT fktpvwdvckg86mpuk9o2j1h6t15 FOREIGN KEY (user_id) REFERENCES user(id);


INSERT INTO users(user_name, password, address, email, name, status, sex) VALUES ('sondx', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', 'Nam Định', 'daoxuansonuet@gmail.com', 'Đào Xuân Sơn', 'ACTIVE', 'MALE');
INSERT INTO role(description, role_name) VALUES ('Quản trị hệ thống', 'ROOT_ADMIN');
INSERT INTO role(description, role_name) VALUES ('Admin hệ thống', 'ADMIN');
INSERT INTO role(description, role_name) VALUES ('Người dùng', 'USER');

INSERT INTO permission(user_id, role_id) VALUES (1,1);
INSERT INTO permission(user_id, role_id) VALUES (1,2);
INSERT INTO permission(user_id, role_id) VALUES (1,3);