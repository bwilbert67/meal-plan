<template>
  <div class="typeaheadsearch">
    <div class="search-container">
      
      <input
        type="text"
        id="search"
        class="searchField"
        v-model="searchString"
        autocomplete="off"
        @focus="getFocus"
        @blur="loseFocus"
        @keyup="handleKeyUp"
      />
    </div>
    <div class="results" v-show="show && results.length > 0">
      <div v-for="(result, index) in results" :key="index" class="result-item" @mousedown="useDefault(result.name, result.id)">
        {{ result.name }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "type-ahead-search",
  data() {
    return {
      searchString: "",
      clickedId: 0,
      waiting: false,
      show: false
    };
  },
  props: ['ingredientIndex'],
  computed: {
    results() {
      if (this.searchString != "") {
        return this.$store.state.ingredients.filter((ingredient) => {
          return ingredient.name
            .toLowerCase()
            .includes(this.searchString.toLowerCase());
        });
      } else {
        return [];
      }
    },
  },
  methods: {
    useDefault(text, id) {
      this.searchString = text;
      this.clickedId = id;
      this.loseFocus();
      this.handleKeyUp();
    },
    getFocus() {
      this.show = true;
    },
    loseFocus() {
      this.show = false;
    },
    handleKeyUp() {
      const updateData = {
        index: this.ingredientIndex,
        name: this.searchString,
        id: this.clickedId
      }
      this.$store.commit('UPDATE_INGREDIENT', updateData)
    }
  }
};
</script>

<style scoped>
.typeaheadsearch {
  display: inline-block;
  text-align: left;
  width: 400px;
}

.search-container {
  width: 100%;

  padding: 10px;
}

.searchField{
  border: 2px solid white;
  border-radius: 10px;
  font-family: "Rubik", "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
}

.results {
  /* height: 400px; */
  background-color: rgb(17, 172, 76);
  padding: 10px;
  margin-left: 40px;
  white-space: nowrap;
  box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
  width: 20%;
  position: absolute;
}

.result-item {
  padding: 5px;
  width: 100%;
  overflow: hidden;
}
.result-item:hover {
  background: lightgray;
  width: 100%;
  cursor: pointer;
}
</style>
