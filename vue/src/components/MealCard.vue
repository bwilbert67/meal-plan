<template>

  <div class="allRecipes">    
    <div v-for="(mealPlan, index) in mealMap" v-bind:key="index">
    <div v-for="(meal, mealIndex) in  mealPlan[1]" :key="mealIndex">
  </div>

    <div class="flip-card" id="card">
    <div class="flip-card-inner">
    <div class="flip-card-front">
          <h2 class="recipe-name">{{ mealPlan[0] }}</h2>   
          <div id="imageFlex">
            <img id="foodpic" src="../images/bekfast.jpg" border="3px">
            <img id="foodpic" src="../images/lunch.jpg" border="3px">
            <img id="foodpic" src="../images/dinner.png" border="3px">
          </div>
    </div>
        
          <router-link :to="{name:'mealPlanDetails', params: {mealPlanId: mealPlan.id}}" class="icon" style="position:absolute;  left: 49%; top:90%; width:25px; height:25px">  <i class="fa-solid fa-utensils"></i></router-link>
    </div>
    </div>
         </div>
    </div>   
  
</template>

<script>
import MealPlanService from '../services/MealPlanService.js'
export default {
    name: 'meal-card',
    data() {
      return {
        mealPlans: [],
        mealMap: {},
      }
    },
    methods: {
      getImgSrc(recipe) {
        if (!recipe.imgData) {
          return require('../images/foodgen.jpg');
        } else {
            const dotIndex = recipe.imgFilename.lastIndexOf('.');
            const extension = recipe.imgFilename.substring(dotIndex + 1)
            return "data:image/" + extension + ";base64, " + recipe.imgData;
        }
      }
    },
    created() {
      // MealPlanService.getAllMealPlans(this.$store.state.user.id).then((response) => 
      //   this.mealPlans = response.data);
      MealPlanService.getAllMealPlans(this.$store.state.user.id).then((response) => {
        const allPlans = response.data;
        let meals = new Map();
        console.log(meals.length)
        for (let i = 0; i < allPlans.length; i++) {
          const name = allPlans[i].name;
          let planData = meals.get(name);
          if (!planData) {
            meals.set(name, [allPlans[i]]);
          } else {
            planData.push(allPlans[i]);
            meals.set(name, planData);
          }
        }
         this.mealMap = meals.entries();
      });
    }
}
   


</script>

<style scoped>
 @import url('https://fonts.googleapis.com/css2?family=Abel&family=Yeseva+One&display=swap');
    .allRecipes{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

   #card{
    /* border: 2px solid white; */
    border-radius: 10px;
    box-shadow: 2px 2px 5px rgba(116, 114, 114, 0.2),
            10px 10px 15px rgba(0,0,0,0.2),
            -1px -1px 30px rgba(0,0,0,0.2);;
    width: 750px;
    height: 400px;
    margin: 10px 20px 10px 10px;
    position: relative;
   }
   #imageFlex{
     padding-top: 4%;
     display: flex;
     justify-content: space-evenly;
     min-width: 100%;
   }

  #foodpic{
    height: 200px;
    width: 200px;
    object-fit: cover;
  }
    #desc{
        margin-top: 30px;
        padding-top: 20px;
        font-size: .70em;
        font-weight: lighter;
        padding-left: 15px;
        padding-right: 15px;
    }

   .recipe-name{
      font-size:40px;
      color: white;
      font-family: 'Segoe UI', "georgia", 'abel', 'Arial';
      margin-bottom: 15px !important;
      max-width: 85%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      padding-left: 50px !important;    
      padding-right: 50px !important; 
      -webkit-text-stroke: 1px 04030F;

   }

    li{
        list-style: none;
        font-size: 1rem;
    }

    .icon {
        color: #aa4db1;
        text-align: center;
    }
   
   h3{
       color: rgba(96, 177, 15, 0.534);
       position:absolute; top:250px; left:40px; width:75%; height:25px;
       font-size: 1rem;
       
   }
.flip-card {
  background-color: transparent;
  width: 300px;
  height: 300px;
  perspective: 1000px;
}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  /* transition: transform 0.6s;
  transform-style: preserve-3d; */
  /* box-shadow: 0 4px 8px 0 rgba(53, 66, 243, 0.616); */
}

.flip-card:hover .flip-card-inner {
  /* transform: rotateY(180deg); */
}

.flip-card-front, .flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.flip-card-front {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgba(128, 167, 226, 0.37);;
  color: black;
     /* border: 6px solid rgb(92, 150, 102); */
    border-radius: 10px;
    font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
}

.flip-card-back {
  background-color: white;
  color: rgb(92, 150, 102);
  transform: rotateY(180deg);
     border: 2px solid white;
    border-radius: 10px;
    font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
}
img {
  border: transparent;
  border-radius: 5%;
}
h2{
    color: #FCF8F5 !important;
}

   
</style>