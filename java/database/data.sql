BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('bigben','$2a$10$e37GY2xPV.ZPJOCNJJmATeo0.LLPoOSCyEzRvs4Lc9fWF4kCrR3Mi','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('mike','$2a$10$wWscwqSGFLayXXXxpcut0uFcC9I3Z4OL901M7y12GIRMbaU0ccjH6','ROLE_USER');


INSERT INTO recipe VALUES (DEFAULT, 'Carrot Cake', 'Carrot cake: because who says dessert cant be healthy? Loaded with vitamin A-rich carrots, this cake is practically a salad. But dont worry, the cream cheese frosting makes it feel indulgent.', 'Dessert', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Pancakes', 'These pancakes are so light and fluffy, youll feel like youre eating a bite of heaven! Made with buttermilk and a touch of vanilla, theyre the perfect canvas for all your favorite toppings, from fresh berries to melted chocolate.', 'Breakfast', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Bens Boiled Brussels', 'I think Im going to be sick!', 'Side Dish', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Vegetarian Chili', 'This dish is like a party in your mouth! With a bold blend of spices, colorful veggies, and protein-packed beans, you wont even miss the meat. Top it off with a dollop of creamy avocado or a sprinkle of crunchy tortilla chips for an extra kick.', 'Lunch/Dinner', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Breezys Tofu Stir Fry', 'Who needs meat when you have a tofu stir fry this good? With a hearty dose of protein-packed tofu and an array of colorful veggies, its a dish thats both filling and flavorful. Whip it up for a satisfying and nutritious meal thatll leave you feeling energized.', 'Lunch/Dinner', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Simple Chickpea Salad', 'This salad is the perfect mix of sweet and savory flavors, with juicy strawberries, creamy avocado, and protein-packed chickpeas. Toss it all together with a zesty dressing and youve got a salad thats sure to satisfy your taste buds and your hunger.', 'Lunch/Dinner', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Chicken Piccata', 'This classic Italian dish is a flavor explosion! Tender chicken cutlets smothered in a tangy lemon-caper sauce, its a dish thats sure to transport you straight to the streets of Italy. Serve it up with a side of pasta and a glass of wine for a meal thatll have you saying "mamma mia!" with every bite.', 'Dinner', 3);
INSERT INTO recipe VALUES (DEFAULT, 'Kale Salad', 'Looking for a meal thats as nutritious as it is delicious? Look no further than this kale salad! With a mix of nutrient-dense ingredients like kale, quinoa, and avocado, its a dish thatll leave you feeling nourished and satisfied. Plus, the zesty dressing adds a burst of flavor thatll have you coming back for more.', 'Lunch', 3);


INSERT INTO ingredient VALUES (DEFAULT, 'carrot');
INSERT INTO ingredient VALUES (DEFAULT, 'sugar');
INSERT INTO ingredient VALUES (DEFAULT, 'ap flour');
INSERT INTO ingredient VALUES (DEFAULT, 'egg');
INSERT INTO ingredient VALUES (DEFAULT, 'milk');
INSERT INTO ingredient VALUES (DEFAULT, 'hot sauce');
INSERT INTO ingredient VALUES (DEFAULT, 'maple syrup');
INSERT INTO ingredient VALUES (DEFAULT, 'butter');
INSERT INTO ingredient VALUES (DEFAULT, 'oil');
INSERT INTO ingredient VALUES (DEFAULT, 'celery');
INSERT INTO ingredient VALUES (DEFAULT, 'heavy cream');
INSERT INTO ingredient VALUES (DEFAULT, 'red onion');
INSERT INTO ingredient VALUES (DEFAULT, 'salt');
INSERT INTO ingredient VALUES (DEFAULT, 'pepper');
INSERT INTO ingredient VALUES (DEFAULT, 'rice');
INSERT INTO ingredient VALUES (DEFAULT, 'banana');
INSERT INTO ingredient VALUES (DEFAULT, 'soy sauce');
INSERT INTO ingredient VALUES (DEFAULT, 'chicken');
INSERT INTO ingredient VALUES (DEFAULT, 'mayonnaise');
INSERT INTO ingredient VALUES (DEFAULT, 'bread crumb');
INSERT INTO ingredient VALUES (DEFAULT, 'baking soda');
INSERT INTO ingredient VALUES (DEFAULT, 'cinnamon');
INSERT INTO ingredient VALUES (DEFAULT, 'vegetable oil');
INSERT INTO ingredient VALUES (DEFAULT, 'brown sugar');
INSERT INTO ingredient VALUES (DEFAULT, 'vanilla extract');
INSERT INTO ingredient VALUES (DEFAULT, 'pecan');
INSERT INTO ingredient VALUES (DEFAULT, 'cream cheese');
INSERT INTO ingredient VALUES (DEFAULT, 'powdered sugar');
INSERT INTO ingredient VALUES (DEFAULT, 'whipping cream');
INSERT INTO ingredient VALUES (DEFAULT, 'baking powder');
INSERT INTO ingredient VALUES (DEFAULT, 'brussel sprout');
INSERT INTO ingredient VALUES (DEFAULT, 'garlic clove');
INSERT INTO ingredient VALUES (DEFAULT, 'red bell pepper');
INSERT INTO ingredient VALUES (DEFAULT, 'yellow bell pepper');
INSERT INTO ingredient VALUES (DEFAULT, 'jalapeno pepper');
INSERT INTO ingredient VALUES (DEFAULT, 'chili powder');
INSERT INTO ingredient VALUES (DEFAULT, 'cumin');
INSERT INTO ingredient VALUES (DEFAULT, 'paprika');
INSERT INTO ingredient VALUES (DEFAULT, 'cayenne pepper');
INSERT INTO ingredient VALUES (DEFAULT, 'diced tomato');
INSERT INTO ingredient VALUES (DEFAULT, 'black bean');
INSERT INTO ingredient VALUES (DEFAULT, 'kidney bean');
INSERT INTO ingredient VALUES (DEFAULT, 'corn');
INSERT INTO ingredient VALUES (DEFAULT, 'yellow onion');
INSERT INTO ingredient VALUES (DEFAULT, 'tofu');
INSERT INTO ingredient VALUES (DEFAULT, 'grated ginger');
INSERT INTO ingredient VALUES (DEFAULT, 'toasted sesame seed');
INSERT INTO ingredient VALUES (DEFAULT, 'chick pea');
INSERT INTO ingredient VALUES (DEFAULT, 'avocado');
INSERT INTO ingredient VALUES (DEFAULT, 'strawberry');
INSERT INTO ingredient VALUES (DEFAULT, 'balsamic vinegar');
INSERT INTO ingredient VALUES (DEFAULT, 'parsley');


INSERT into measurement VALUES (DEFAULT, 'tablespoon');
INSERT into measurement VALUES (DEFAULT, 'teaspoon');
INSERT into measurement VALUES (DEFAULT, 'cup');
INSERT into measurement VALUES (DEFAULT, 'dash');
INSERT into measurement VALUES (DEFAULT, 'bunch');
INSERT into measurement VALUES (DEFAULT, 'small pinch');
INSERT into measurement VALUES (DEFAULT, 'large pinch');
INSERT into measurement VALUES (DEFAULT, 'pint');
INSERT into measurement VALUES (DEFAULT, 'quart');
INSERT into measurement VALUES (DEFAULT, 'oz');
INSERT into measurement VALUES (DEFAULT, 'lb');
INSERT into measurement VALUES (DEFAULT, 'g');
INSERT into measurement VALUES (DEFAULT, 'mL');
INSERT INTO measurement VALUES (DEFAULT, 'can (15 oz)');
INSERT INTO measurement VALUES (DEFAULT, 'can (28 oz)');
INSERT INTO measurement VALUES (DEFAULT, 'none');


INSERT INTO recipe_ingredient VALUES (1, 3, 2, 3);
INSERT INTO recipe_ingredient VALUES (1, 21, 2, 2);
INSERT INTO recipe_ingredient VALUES (1, 22, 1.5, 2);
INSERT INTO recipe_ingredient VALUES (1, 23, 1.25, 3);
INSERT INTO recipe_ingredient VALUES (1, 2, 1, 3);
INSERT INTO recipe_ingredient VALUES (1, 24, 1, 3);
INSERT INTO recipe_ingredient VALUES (1, 25, 1, 2);
INSERT INTO recipe_ingredient VALUES (1, 4, 4, 16);
INSERT INTO recipe_ingredient VALUES (1, 1, 3, 3);
INSERT INTO recipe_ingredient VALUES (1, 26, 1.5, 3);
INSERT INTO recipe_ingredient VALUES (1, 27, 8, 2);
INSERT INTO recipe_ingredient VALUES (1, 28, 1.25, 3);
INSERT INTO recipe_ingredient VALUES (1, 29, 0.33, 3);

INSERT INTO recipe_ingredient VALUES (2, 3, 1, 3);
INSERT INTO recipe_ingredient VALUES (2, 30, 2, 1);
INSERT INTO recipe_ingredient VALUES (2, 13, 0.5, 2);
INSERT INTO recipe_ingredient VALUES (2, 5, 1, 3);
INSERT INTO recipe_ingredient VALUES (2, 4, 1, 16);
INSERT INTO recipe_ingredient VALUES (2, 8, 2, 1);
INSERT INTO recipe_ingredient VALUES (2, 25, 1, 2);

INSERT INTO recipe_ingredient VALUES (3, 31, 2, 3);

INSERT INTO recipe_ingredient VALUES (4, 9, 1, 1);
INSERT INTO recipe_ingredient VALUES (4, 44, 1, 16);
INSERT INTO recipe_ingredient VALUES (4, 32, 3, 16);
INSERT INTO recipe_ingredient VALUES (4, 33, 1, 16);
INSERT INTO recipe_ingredient VALUES (4, 34, 1, 16);
INSERT INTO recipe_ingredient VALUES (4, 35, 1, 1);
INSERT INTO recipe_ingredient VALUES (4, 37, 1, 2);
INSERT INTO recipe_ingredient VALUES (4, 38, 1, 2);
INSERT INTO recipe_ingredient VALUES (4, 39, 0.25, 2);
INSERT INTO recipe_ingredient VALUES (4, 40, 1, 15);
INSERT INTO recipe_ingredient VALUES (4, 41, 1, 14);
INSERT INTO recipe_ingredient VALUES (4, 42, 1, 14);
INSERT INTO recipe_ingredient VALUES (4, 43, 2, 14);

INSERT INTO recipe_ingredient VALUES (5, 45, 1, 3);
INSERT INTO recipe_ingredient VALUES (5, 17, 1, 1);
INSERT INTO recipe_ingredient VALUES (5, 9, 1, 3);
INSERT INTO recipe_ingredient VALUES (5, 33, 1, 3);
INSERT INTO recipe_ingredient VALUES (5, 46, 1, 1);
INSERT INTO recipe_ingredient VALUES (5, 47, 2, 1);

--(recipe_id, ingredient_id, quantity, measurement_id)
INSERT INTO recipe_ingredient VALUES (6, 48, 1, 3);
INSERT INTO recipe_ingredient VALUES (6, 49, 0.5, 3);
INSERT INTO recipe_ingredient VALUES (6, 50, 0.5, 3);
INSERT INTO recipe_ingredient VALUES (6, 9, 2, 1);
INSERT INTO recipe_ingredient VALUES (6, 51, 2, 1);
INSERT INTO recipe_ingredient VALUES (6, 52, 1, 1);

--INSERT INTO recipe_ingredient VALUES (7, 52, 1, 1);
--INSERT INTO recipe_ingredient VALUES (7, 52, 1, 1);
--INSERT INTO recipe_ingredient VALUES (7, 52, 1, 1);
--INSERT INTO recipe_ingredient VALUES (7, 52, 1, 1);
--INSERT INTO recipe_ingredient VALUES (7, 52, 1, 1);
--INSERT INTO recipe_ingredient VALUES (7, 52, 1, 1);




INSERT INTO instruction VALUES (DEFAULT, 1, 1, 'Position a rack in the middle of the oven. Grease two 9-inch round cake pans, line the bottom with parchment paper and then grease the top. Or grease and flour the bottom and sides of both pans.');
INSERT INTO instruction VALUES (DEFAULT, 1, 2, 'Heat the oven to 350 degrees Fahrenheit (176C).');
INSERT INTO instruction VALUES (DEFAULT, 1, 3, 'Whisk flour, baking soda, salt, and cinnamon in a medium bowl until very well blended.');
INSERT INTO instruction VALUES (DEFAULT, 1, 4, 'In a separate bowl, whisk the oil, granulated sugar, brown sugar, and vanilla.');
INSERT INTO instruction VALUES (DEFAULT, 1, 5, 'Add the eggs, one at a time, whisking after each one.');
INSERT INTO instruction VALUES (DEFAULT, 1, 6, 'Switch to a large rubber spatula. Scrape the sides and bottom of the bowl, then add the dry ingredients in three parts, gently stirring until they disappear and the batter is smooth.');
INSERT INTO instruction VALUES (DEFAULT, 1, 7, 'Stir in the carrots and nuts.');
INSERT INTO instruction VALUES (DEFAULT, 1, 8, 'Divide the cake batter between the prepared cake pans.');
INSERT INTO instruction VALUES (DEFAULT, 1, 9, 'Bake until the tops of the cake layers are springy when touched and when a toothpick inserted into the center of the cake comes out clean, 35 to 45 minutes.');
INSERT INTO instruction VALUES (DEFAULT, 1, 10, 'Cool cakes in the pans for 15 minutes, then carefully turn the cake layers out onto cooling racks. Remove the parchment paper and cool completely. If you find that a cake layer is sticking to the bottom of the pan, leave the cake pan upside down and allow gravity to do its thing.');
INSERT INTO instruction VALUES (DEFAULT, 1, 11, 'In a large bowl, beat the cream cheese with a handheld mixer on medium speed until creamy, about 1 minute.');
INSERT INTO instruction VALUES (DEFAULT, 1, 12, 'Beat in the powdered sugar, a 1/4 cup at a time, until fluffy.');
INSERT INTO instruction VALUES (DEFAULT, 1, 13, 'Pour in the whipping cream. Beat on medium speed for 2 to 3 minutes, or until the frosting is whipped and creamy. This frosting resembles the texture of whipped cream. Chill covered until ready to frost the cake.');
INSERT INTO instruction VALUES (DEFAULT, 1, 14, 'When the cake layers are completely cool, frost the top of one cake layer, and place the second cake layer on top.');
INSERT INTO instruction VALUES (DEFAULT, 1, 15, 'Add the remaining frosting to the top of the carrot cake and use a butter knife or small spatula to swirl the frosting around. Leave the sides of the cake unfrosted. Finish with a handful of nuts on top.');

INSERT INTO instruction VALUES (DEFAULT, 2, 1, 'In a medium bowl, whisk together the flour, sugar, baking powder, and salt.');
INSERT INTO instruction VALUES (DEFAULT, 2, 2, 'In a separate bowl, whisk together the milk, egg, melted butter, and vanilla extract.');
INSERT INTO instruction VALUES (DEFAULT, 2, 3, 'Add the wet ingredients to the dry ingredients and stir until just combined. The batter should be lumpy.');
INSERT INTO instruction VALUES (DEFAULT, 2, 4, 'Heat a skillet or griddle over medium heat. Once hot, grease with butter or oil.');
INSERT INTO instruction VALUES (DEFAULT, 2, 5, 'Using a 1/4-cup measure, pour the batter onto the skillet. Cook until the edges start to dry and the surface of the pancake is bubbly, about 2-3 minutes. Flip the pancake and cook until golden brown, another 1-2 minutes.');
INSERT INTO instruction VALUES (DEFAULT, 2, 6, 'Transfer the pancake to a plate and repeat with the remaining batter.');




INSERT INTO instruction VALUES (DEFAULT, 3, 1, 'Bring a pot of water to a boil.');
INSERT INTO instruction VALUES (DEFAULT, 3, 2, 'Drop in all of your little baddies');
INSERT INTO instruction VALUES (DEFAULT, 3, 3, 'Boil for 4 minutes');
INSERT INTO instruction VALUES (DEFAULT, 3, 4, 'Yum!');

INSERT INTO instruction VALUES (DEFAULT, 4, 1, 'Heat olive oil in a large pot over medium heat. Add onions and garlic and sauté until fragrant and tender.');
INSERT INTO instruction VALUES (DEFAULT, 4, 2, 'Add in diced bell peppers and jalapeño pepper and sauté for a few minutes until they start to soften.');
INSERT INTO instruction VALUES (DEFAULT, 4, 3, 'Add chili powder, cumin, paprika, and cayenne pepper, and stir to combine.');
INSERT INTO instruction VALUES (DEFAULT, 4, 4, 'Pour in the diced tomatoes, black beans, kidney beans, and corn. Stir everything together and bring to a simmer.');
INSERT INTO instruction VALUES (DEFAULT, 4, 5, 'Reduce heat to low and let the chili simmer for about 30 minutes, stirring occasionally.');
INSERT INTO instruction VALUES (DEFAULT, 4, 6, 'Season with salt and pepper to taste.');
INSERT INTO instruction VALUES (DEFAULT, 4, 7, 'Serve hot, topped with your favorite garnishes.');

INSERT INTO instruction VALUES (DEFAULT, 5, 1, 'Slice tofu into cubes and place into a large bowl and season with 1 tablespoon soy sauce.');
INSERT INTO instruction VALUES (DEFAULT, 5, 2, 'In a large nonstick skillet or wok, add 1 tablespoon oil and heat to medium-high.');
INSERT INTO instruction VALUES (DEFAULT, 5, 3, 'Add the tofu and let it get crispy without touching it or flipping for 3-4 minutes, then repeat on all sides.');
INSERT INTO instruction VALUES (DEFAULT, 5, 4, 'Add Bell Pepper Ginger into pan and sauteed until you smile.');

INSERT INTO instruction VALUES (DEFAULT, 6, 1, 'Drain chickpeas and place in a salad bowl.');
INSERT INTO instruction VALUES (DEFAULT, 5, 2, 'Slice avocado into cubes, slice strawberries, chop parsley, and add to the bowl.');
INSERT INTO instruction VALUES (DEFAULT, 5, 3, 'Toss with olive oil and balsamic vinegar until you heart goes boom!');




COMMIT TRANSACTION;
ROLLBACK;

