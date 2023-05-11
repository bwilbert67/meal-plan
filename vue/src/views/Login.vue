<template>

  <div id="login">

    <form @submit.prevent="login">

      <h1 >Login</h1>

      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>

      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>

      <div class="form-input-group">
        <label for="username"></label>
        <input type="text" id="username" placeholder="username" v-model="user.username" required autofocus />
      </div>

      <div class="form-input-group">
        <label for="password"></label>
        <input type="password" id="password" placeholder="password" v-model="user.password" required />
      </div>

      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up</router-link></p>
      <div class="btn btn-one">
        
    <span v-on:click="login()">login</span>
  
  </div>
    
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      sound: null,
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false
    };
  },
  created() {
   let file = require("../sounds/tada.mp3")
this.sound = new Audio(file);


  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push({ name: 'myrecipes', params:  {id: this.$store.state.user.id} });
        this.sound.play();
       }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};

</script>

<style scoped>
h1{
  color: #344E41 !important;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

p {
  font-size: 15px;
}

#login{
background-color: rgba(90, 177, 90, 0.301);
 border: 2px solid #aa4db1;
 border-radius: 10px;
 width: 35%; 
height: 60%;
  margin-left: 33%;
  
}

#username{
    width: 25%;
    border-radius: 10px;
    border-color: white;
    line-height: 25px;
    font-size: 15px;
    text-align: center;
    transform: scale();
}
#password{
    width: 25%;
    border-radius: 10px;
    border-color: white;
    line-height: 25px;
    font-size: 15px;
    text-align: center;
    transform: scale();
}


.btn-one {
  
  color: rgb(254, 251, 255);
  transition: all 0.3s;
  position: relative;
  background-color: rgb(12, 71, 25);
  border: none;
  margin-top: 50px;
  line-height: 40px;
  border-radius: 40px;
  width: 15%;
  margin-left: 43%;
  

}

.btn-one span {
  margin-left: 3%;
  transition: all 0.3s;
  color:yellowgreen;
  
  
}

.btn-one:hover span {
  letter-spacing: 2px;
}
.btn-one:hover::before {
  opacity: 1; 
  transform: scale(1, 1); 
}

.btn-one:hover::after {
  opacity: 0; 
  transform: scale(0.1, 1);
}

</style>