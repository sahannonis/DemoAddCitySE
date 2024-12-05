<template>
    <div>
      <h1>Update City</h1>
      <form @submit.prevent="updateCity">
        <input v-model="id" type="number" placeholder="City ID" required />
        <input v-model="name" placeholder="New City Name" />
        <input v-model.number="parks" type="number" placeholder="New Number of Parks" />
        <button type="submit">Update City</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        id: "",
        name: "",
        parks: 0,
      };
    },
    methods: {
      updateCity() {
        const updatedCity = {
          name: this.name,
          parks: this.parks,
        };
        axios.put(`/api/cities/${this.id}`, updatedCity)
          .then(() => {
            this.id = "";
            this.name = "";
            this.parks = 0;
            alert("City updated successfully!");
          })
          .catch(error => {
            console.error("Error updating city:", error);
          });
      },
    },
  };
  </script>
  