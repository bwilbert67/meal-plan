BEGIN TRANSACTION;

DROP TABLE IF EXISTS recipe_ingredient, instruction, measurement, grocery_list, meal_plan, meal_entry, meal_recipe, meal, ingredient, recipe, users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE recipe (
    recipe_id SERIAL,
    recipe_name varchar(100) NOT NULL,
    recipe_description varchar(1000),
    recipe_type varchar(50),
    user_id int,
    img_file_name varchar(200),
    img_data bytea,
    CONSTRAINT pk_recipe PRIMARY KEY (recipe_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT recipe_needs_name CHECK (recipe_name <> '')
);

CREATE TABLE ingredient (
    ingredient_id SERIAL PRIMARY KEY,
    ingredient_name varchar (40) NOT NULL UNIQUE,
    CONSTRAINT ingredient_needs_name CHECK (ingredient_name <> '')
);

CREATE TABLE measurement (
    measurement_id SERIAL PRIMARY KEY,
    measurement_name varchar(20)
);

CREATE TABLE recipe_ingredient (
    recipe_id int,
    ingredient_id int,
    quantity decimal,
    measurement_id int,
    CONSTRAINT pk_recipe_ingredient PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
    CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient (ingredient_id),
    CONSTRAINT fk_measurement_id FOREIGN KEY (measurement_id) REFERENCES measurement (measurement_id)
);

CREATE TABLE instruction (
    instruction_id SERIAL PRIMARY KEY,
    recipe_id int,
    step_number int NOT NULL,
    instruction_text varchar(500) NOT NULL,
    CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
    CONSTRAINT instruction_needs_text CHECK (instruction_text <> ''),
    CONSTRAINT instruction_needs_step_num CHECK (step_number <> 0)
);

CREATE TABLE meal_plan (
    mp_id SERIAL PRIMARY KEY,
    mp_name varchar(100),
    mp_start date,
    mp_end date,
    user_id int,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE meal (
    meal_id SERIAL PRIMARY KEY,
    type varchar(50),
    meal_day int,
    mp_id int NOT NULL,
    CONSTRAINT fk_mp_id FOREIGN KEY (mp_id) REFERENCES meal_plan (mp_id)
);

CREATE TABLE meal_recipe (
    meal_id int,
    recipe_id int,
    CONSTRAINT pk_meal_recipe PRIMARY KEY (meal_id, recipe_id),
    CONSTRAINT fk_meal_id FOREIGN KEY (meal_id) REFERENCES meal (meal_id),
    CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id)
);


CREATE TABLE grocery_list (
    grocery_list_id SERIAL,
	grocery_list_name varchar(100) NOT NULL,
	user_id int,
	ingredient_id int,
	CONSTRAINT pk_grocery_list PRIMARY KEY (grocery_list_id),
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (ingredient_id)
);

COMMIT TRANSACTION;
ROLLBACK;
