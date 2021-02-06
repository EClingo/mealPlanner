BEGIN TRANSACTION;

DROP TABLE IF EXISTS ingredients_recipes;
DROP TABLE IF EXISTS meals_recipes;
DROP TABLE IF EXISTS meal_plans_meals;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS meal_plans;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS recipes;


CREATE TABLE recipes (
	recipe_id serial NOT NULL,
	user_id int NOT NULL,
	title varchar(45) NOT NULL,
	instructions text NOT NULL,
	CONSTRAINT pk_recipe_id PRIMARY KEY (recipe_id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users (user_id)
);

CREATE TABLE ingredients (
	ingredient_id serial NOT NULL,
	user_id int NOT NULL,
	name varchar(45) NOT NULL,
	is_on_grocery_list boolean NOT NULL,
	recipe_id int,
        quantity int,
        measurement varchar(45),
	CONSTRAINT pk_ingredient_id PRIMARY KEY (ingredient_id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users (user_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);

CREATE TABLE meals (
	meal_id serial NOT NULL,
	name varchar(45) NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT pk_meal_id PRIMARY KEY (meal_id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users (user_id)
);

CREATE TABLE meals_recipes (
	meal_recipe_id serial NOT NULL,
	meal_id int NOT NULL,
	recipe_id int NOT NULL,
	CONSTRAINT pk_meal_recipe_id PRIMARY KEY (meal_recipe_id),
	CONSTRAINT fk_meal_id FOREIGN KEY(meal_id) REFERENCES meals (meal_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipes (recipe_id)
);

CREATE TABLE meal_plans (
	meal_plan_id serial NOT NULL,
	name varchar(45) NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT pk_meal_plan_id PRIMARY KEY (meal_plan_id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users (user_id)
);

CREATE TABLE schedules (
	schedule_id serial NOT NULL,
	meal_plan_id int NOT NULL,
	meal_id int NOT NULL,
	day_of_week varchar(45) NOT NULL,
	time_of_day varchar (45) NOT NULL,
	CONSTRAINT pk_schedule_id PRIMARY KEY (schedule_id),
	CONSTRAINT fk_meal_plan_id FOREIGN KEY (meal_plan_id) REFERENCES meal_plans (meal_plan_id),
	CONSTRAINT fk_meal_id FOREIGN KEY (meal_id) REFERENCES meals (meal_id)
);

COMMIT TRANSACTION;
