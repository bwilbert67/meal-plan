<template>
  <div class="container">
    <header>
        <div id="siteName">
            <router-link :to="{name: 'myrecipes', params: {id:$store.state.user.id}}">
                <span class="Teats"> T-Eats</span>
             </router-link>
        </div>
        <i id="logo" class="fa-solid fa-bowl-rice"></i>


        <div class="navbar">
            <div class="navbtn"> <router-link :to="{name: 'myrecipes', params: {id:$store.state.user.id}}"> My Recipes </router-link> </div>
            <div class="navbtn"> <router-link :to="{name: 'mymealplan', params: {id:$store.state.user.id}}"> My Meals </router-link> </div>
            <div class="navbtn"> <router-link :to="{name: 'groceries', params: {id:$store.state.user.id}}"> My Groceries </router-link> </div>
            <div id= "logout" class="navbtn"> <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> </div>
        </div>
    
    </header>
  <main>
      <div class="content">
          <!-- different views/pages are displayed/injected here -->
                
         
        <router-view></router-view>
      </div>
  </main>


  <footer>
      <div class="content">
          <!-- footer content -->
          <i class="fa-brands fa-instagram" style="color: white"></i>&nbsp;&nbsp;
          <i class="fa-brands fa-facebook" style="color:white"></i>&nbsp;&nbsp;
          <i class="fa-brands fa-twitter" style="color:white"></i>&nbsp;&nbsp;
          <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ"><i class="fa-brands fa-tiktok"></i>&nbsp;</a>
      </div>
  </footer>
  
  </div>
</template>


<script>
export default {
    created() {
        // force logout on arrival so user info is present
        this.$router.push({ name: 'logout'})
    }
  };
</script>

<style scoped>
    
  @import url('https://fonts.googleapis.com/css2?family=Rubik:wght@300&family=Yeseva+One&display=swap');

    body{

        margin: 0;
      
    }

    .container{
        display: grid;
        grid-template-areas:
            "header header header"
            "content content content"
            "footer footer footer";
        grid-template-columns: 200px 1fr 200px;
        grid-template-rows: 85px 1fr 85px;
        /* gap: 10px; */
        height: 100vh;
        cursor: url("./images/carCur.cur"), auto;
    }

    /*handle responsive behavior */
    @media (max-width: 768px) {
        .container{
            grid-template-areas:
                "header"
                "content"
                "footer";
            grid-template-columns: 1fr;
            grid-template-rows:
                auto /*Header */
                1fr /* content */
                auto; /* Footer */
        }
        header, footer, nav, main, aside {
            padding: 5px 0;
        }
        nav, aside {
        margin: 0;
        }
    }

    #logo{
        width: 50px;
        height: 60px;
        color: #86BBD8;
        margin-left: 10px;
    }

    /* #siteName {
        
    } */

    header {
    display: flex;
    flex-shrink: 1;
    grid-area: header;
    background-color: #fcf8f5;
    border-bottom: #aa4db1 solid;
    border-width: 2px;
    text-align: center;
    font-size: 80px !important;
    height: 80px;
    
    }

    #logo {
        position:absolute;
         top: 33px;
         left:310px;
    }

    span{
      position: absolute;
      margin-top: -35px;
      flex-shrink: 3;
      padding-top: 2%;
      margin-left: 15px;
      justify-content: left;
      color: yellowgreen;
      font-family: 'Yeseva One', Tahoma, Geneva, Verdana, sans-serif;
    }

    nav {
        grid-area: nav;
        margin-left: 0.5rem;
        background-color: #A3B18A;
        padding: 10%;
        font-weight: bold;  
        display: flex;  
    }

    main {
        grid-area: content;
        background-color: #fcf8f5
    }
   
    footer {
        grid-area: footer;
        background-color: #344E41;
    }
    header, footer {
        color: #dad7CD;
    }
    nav, main {
        color: #0e5c2e;
        font-family: 'Yeseva One', Tahoma, Geneva, Verdana, sans-serif;
    }
    header, footer, nav, main {
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        font-size: 24px;
        text-transform: uppercase;
    }
    div.content {
        
        text-align: center;
        padding-top: 20px;
        height: 100%;
        margin-top: -10px;
    }

    a {
      text-decoration: none;
    }
    .navbar{
      font-size: 20px;
      display: flex;
      flex-shrink: 3;
      flex-direction: row;
      align-self: flex-end;
      margin-left: 73%;
        
    }
    .navbtn{
        justify-self: end;
        margin-right: 20px;
        flex-shrink: 3; 
        color: #056396;
    }

   .navbtn, .navbtn :visited, .navbtn:link, .navbtn:focus, .navbtn:active{
        color: #056396 !important;
    }

    .Teats {
        -webkit-text-stroke: .25px black;
    }
    a{
        color: white;
    }
   

</style>