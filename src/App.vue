<template>
  <div id="app">
    <nav>
      <button @click="navigateTo('list')">City List</button>
      <button @click="navigateTo('add')">Add City</button>
      <!-- <button @click="navigateTo('update')">Update City</button> -->
    </nav>
    <main>
      <component :is="currentView" />
    </main>
  </div>
</template>

<script>
import CityList from './components/CityList.vue';
import AddCity from './components/AddCity.vue';
// import UpdateCity from './components/UpdateCity.vue';

export default {
  data() {
    return {
      currentView: 'CityList', // Default view
    };
  },
  components: {
    CityList,
    AddCity,
    // UpdateCity,
  },
  methods: {
    navigateTo(view) {
      const views = {
        list: 'CityList',
        add: 'AddCity',
        // update: 'UpdateCity',
      };
      this.currentView = views[view];
    },
  },
};
</script>

<style>
nav {
  margin-bottom: 20px;
}
button {
  margin: 5px;
  padding: 10px 15px;
  cursor: pointer;
}
main {
  padding: 20px;
  border: 1px solid #ddd;
}
</style>
