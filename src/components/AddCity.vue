<template>
    <div>
      <h1>Add City</h1>
      <form @submit.prevent="addCity">
        <input v-model="name" placeholder="City Name" required />
        <input v-model.number="parks" type="number" placeholder="Number of Parks" required />
        <button type="submit">Add City</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        name: "",
        parks: 0,
      };
    },
    methods: {
      addCity() {
        const newCity = {
          name: this.name,
          parks: this.parks,
        };
        axios.post("/api/cities", newCity)
          .then(() => {
            this.name = "";
            this.parks = 0;
            alert("City added successfully!");
          })
          .catch(error => {
            console.error("Error adding city:", error);
          });
      },
    },
  };
  </script>
  