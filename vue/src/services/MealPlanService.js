import axios from 'axios';

//This is broken, axios is pre-configured somehow
// const http = axios.create({
//     baseURL: "http://localhost:9000"
// });


export default {

    addImageToRecipe(fileData, recipeId) {
        const options = {
            headers: {
            'Content-Type': 'multipart/form-data'
            }
        };
        let formData = new FormData();
        formData.append('imgFile', fileData); 
        return axios.put(`/recipes/${recipeId}`, formData, options)
    },
    getIngredients() {
        return axios.get("/ingredients")
    },
    addIngredient(ingredient) {
        console.log(ingredient.name)
        return axios.post("/ingredients", ingredient)
    },
    addIngredients(ingredients) {
        // console.log(ingredient.name)
        return axios.post("/ingredients", ingredients)
    },
    getRecipesByUsername() {
        return axios.get("/users/recipes")

    },
    addRecipe(recipe) {
        return axios.post('/users/recipes', recipe)
        .catch((err) => {
            alert(err.response.data.message)
        });
    },
    getAllMealPlans(userId) {
        return axios.get(`users/${userId}/mealplans`);
    },
    getMealPlanByName(mealPlanName) {
        return axios.get('/my-meal-plan', { params: { mealPlanName } });
    },
    getMealPlanById(mealPlanId) {
        return axios.get(`/meal-plans/${mealPlanId}`);
    },
    getIngredientsByMealPlanId(mealPlanId) {
        return axios.get(`/grocery-list/${mealPlanId}`);
    },
    newMealPlan(mealPlan) {
        return axios.post('users/mealplan', mealPlan);
    },
    newFullMealPlan(mealPlanDays, userId) {
        return axios.post(`users/${userId}/mealplans`, mealPlanDays);
    },
    updateMealPlan(mealPlanId, mealPlan) {
        return axios.put(`/update-meal-plan/${mealPlanId}`, mealPlan);
    },
    deleteMealPlan(mealPlanId) {
        return axios.delete(`/delete-meal-plan/${mealPlanId}`);
    },
    deleteRecipe(recipeId){
        return axios.delete(`/recipes/${recipeId}`);
    }
}