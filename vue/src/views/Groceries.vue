<template>
<div id="main">
   <GroceryList/>
</div>
</template>

<script>
import GroceryList from "../components/GroceryList.vue";
export default {
    name:"groceries",
  components: { GroceryList },
};
</script>

<style scoped>
  #main {
      background-image: url("../images/BackgroundFood.webp");
      background-repeat: no-repeat;
      background-size: 100% auto;
      min-height: 1000px;
      margin-top: -32px;
    }
</style>