<template>
    <div>
      <h1>City List</h1>
      <ul>
        <li v-for="city in cities" :key="city.id">
          <strong>{{ city.name }}</strong> (Parks: {{ city.parks }})
          <button @click="deleteCity(city.id)">Delete</button>
          <button @click="editCity(city)">Update</button>
        </li>
      </ul>
  
      <div v-if="isEditing">
        <h2>Update City</h2>
        <form @submit.prevent="updateCity">
          <label>City Name:</label>
          <input v-model="currentCity.name" placeholder="City Name" required />
          
          <label>Number of Parks:</label>
          <input v-model.number="currentCity.parks" type="number" placeholder="Number of Parks" required />
  
          <button type="submit">Save Changes</button>
          <button type="button" @click="cancelEdit">Cancel</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        cities: [],
        isEditing: false,
        currentCity: null, // Holds the city being edited
      };
    },
    methods: {
      fetchCities() {
        axios
          .get("/api/cities")
          .then((response) => {
            this.cities = response.data;
          })
          .catch((error) => {
            console.error("Error fetching cities:", error);
          });
      },
      deleteCity(id) {
        axios
          .delete(`/api/cities/${id}`)
          .then(() => {
            this.fetchCities();
          })
          .catch((error) => {
            console.error("Error deleting city:", error);
          });
      },
      editCity(city) {
        this.currentCity = { ...city }; // Clone the city object to avoid direct mutations
        this.isEditing = true;
      },
      updateCity() {
        axios
          .put(`/api/cities/${this.currentCity.id}`, this.currentCity)
          .then(() => {
            this.isEditing = false;
            this.currentCity = null;
            this.fetchCities();
          })
          .catch((error) => {
            console.error("Error updating city:", error);
          });
      },
      cancelEdit() {
        this.isEditing = false;
        this.currentCity = null;
      },
    },
    mounted() {
      this.fetchCities();
    },
  };
  </script>
  
  <style>
  li {
    margin: 10px 0;
  }
  button {
    margin-left: 10px;
    padding: 5px 10px;
    cursor: pointer;
  }
  form {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    gap: 10px;
  }
  </style>
  