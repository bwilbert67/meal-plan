<template>

  <div class="allRecipes">    
    <div v-for="recipe in $store.state.recipes" v-bind:key="recipe.name">

    <div class="flip-card" id="card">
    <div class="flip-card-inner">
    <div class="flip-card-front">
          <h2 class="recipe-name">{{recipe.recipeName}}</h2>   
          <img id="foodpic" :src ="getImgSrc(recipe)" border="3px">
    </div>

          <router-link :to="{name: 'recipedetails', params:  {id: $route.params.id, recipeId: recipe.recipeId} }" v-if="$store.state.token != ''" class="icon" style="position:absolute; top:300px; left:140px; width:25px; height:25px">
            <i class="fa-solid fa-utensils"></i></router-link>
            <div class="flip-card-back">
      <h1 id='desc'>{{ recipe.recipeDescription }}</h1>

        <router-link :to="{name: 'recipedetails', params:  {id: $route.params.id, recipeId: recipe.recipeId} }" v-if="$store.state.token != ''" class="icon" style="position:absolute; top:300px; left:140px; width:25px; height:25px">
          <i class="fa-solid fa-utensils"></i></router-link>
    </div>
           
         
    </div>   
  </div>
  
  
  </div>
  </div>
  
</template>

<script>
export default {
    name: 'recipe-card',
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
    border: 2px solid white;
    border-radius: 10px;
    box-shadow: 2px 2px 5px rgba(116, 114, 114, 0.2),
            10px 10px 15px rgba(0,0,0,0.2),
            -1px -1px 30px rgba(0,0,0,0.2);;
    width: 300px;
    height: 350px;
    margin: 10px 20px 10px 10px;
    position: relative;
   }

  #foodpic{
    display: flex;
    height: 200px;
    width: 200px;
    object-fit: cover;
    border-radius: 50%;
    border-color: #aa4db1
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
      font-size:30px;
      -webkit-text-stroke: 0.5px 04030F;
      color: yellowgreen;
      font-family: 'abel', 'Arial';
      margin-bottom: 15px !important;
      max-width: 85%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      padding-left: 50px !important;    
      padding-right: 50px !important;    

   }

    li{
        list-style: none;
        font-size: 1rem;
    }

    .icon {
        color: #aa4db1
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
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 0 rgba(53, 66, 243, 0.616);
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
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
  background-color: #fdfbf9;
  color: black;
     border: 2px solid white;
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
   
</style>