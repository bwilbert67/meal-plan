<template>
  <div id = "main">
    <h1>Grocery List</h1>
    <br />
    <div id="grocery-search">
      <br />

      <form
        id="add-new-ingredient-to-grocery-list"
        v-on:submit.prevent="createGroceryItem()"
      >
    
        <input class="soft" type="text" v-model="newItem" placeholder="Add new ingredient" />
        <button type="submit" class="btn-save">Save</button>
      </form>
      <!-- <img id = "avoman" src="../images/healthgif.gif"> -->
    </div>
    <div class="grocery-list-table">
      <table>

        <tr
          v-for="item in filteredItems"
          v-bind:key="item.name"
          v-bind:class="item.done ? 'checked' : 'unchecked'"
        >
          <input
          id="list"
            type="checkbox"
            v-model="item.done"
            @change="moveItem(item)"
          /><label>
            {{ item.name }}

            <span id="delete" v-on:click="deleteGroceryItem(item)">
              ×</span
            ></label
          >
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService.js";
export default {
  data() {
    return {
      filterItems: "",
      newItem: "",
      groceryItems: [],
    };
  },
  methods: {
    deleteGroceryItem(itemToDelete) {
      this.groceryItems = this.groceryItems.filter((item) => {
        return item !== itemToDelete;
      });
    },
    createGroceryItem() {
      const newItemObject = { name: this.newItem, done: false };
      this.groceryItems.unshift(newItemObject);
      this.newItem = "";
    },
    moveItem(item) {
      if (item.done === true) {
        this.deleteGroceryItem(item);
        this.groceryItems.push(item);
      } else {
        this.deleteGroceryItem(item);
        this.groceryItems.unshift(item);
      }
    },
  },
  computed: {
    filteredItems() {
      return this.groceryItems.filter((item) => {
        return item.name.includes(this.filterItems);
      });
    },
  },
  created() {
    mealPlanService
      .getIngredientsByMealPlanId(this.$route.params.mealPlanId)
      .then((ingredient) => {
        this.groceryItems = ingredient.data;
      });
  },
};
</script>

<style scoped>
#avoman{
  width: 20%;
  margin-left: -70%;
  margin-top: -25%;
}
h1{
  color: rgb(62, 161, 95);
 padding-top: 10%;
}
.btn-save {
     border: 2px solid white;
    border-radius: 10px;
    font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
    margin-left: 20px;
    color: rgb(29, 102, 29)
}
#list{
  display: flex;
}
#search-items {
  margin-right: 10px;
  background-color: #4192aa8e;
  color: white;
  padding-left: 10px;
}
#grocery-search {
  background-color: #a0ce84a2;
border-radius: 10px;
border: 2px solid rgba(85, 192, 90, 0.603);
  width: 500px;
  margin: 0 auto;
  padding-top: -20%;
}
.unchecked {
  text-align: left;
}
.checked {
  background-color: rgba(151, 165, 157, 0.493);
  text-decoration: line-through;
  text-align: left;
}
#delete {
  color: rgb(189, 101, 224);
  cursor: pointer;
  text-decoration: none;
  display: flex;
  justify-content: flex-end;
}
#add-new-ingredient-to-grocery-list {
  padding-bottom: 20px;
}
.grocery-list-table {
  width: 496px;
  margin: auto;
}
input[type="checkbox"] {
    margin-bottom: 10px;
  border: 2px solid white;
}
input[type="checkbox"] ~ label {
  padding-left: 1.4em;
  display: inline-block;
  border-radius: 15px;
  text-align: left;
  padding-right: 5px;
  padding: 5px;
  margin-left: 25px;
  margin-bottom: 10px;
  width:460px;
  text-align: center;
  font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 20px;
}
.soft{
  margin-bottom: 10px;
  border: 2px solid white;
  border-radius: 10px;
  font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
}
</style>