<template>

 <div>
<h1 class="outline">My Meal Planner</h1>
    <div class="centerBox">
	
	<div id="funbutt" class="categoryWrapper">
		<h1 id="createPlan" class="outline">Create A New Plan</h1>
		<button class="bottom-btn" @click="$router.push('MealPlanForm')">
			<span>
				<span>
					<span class="outline" data-attr-span="Start Planning!">
					</span>
				</span>
			</span>
		</button>
		
	</div>
	
</div>
 
  </div>
</template>

<script>

export default{

  components:{


  }
}
</script>

<style scoped>

h1{
	padding-top: 10%;
    padding-bottom: 13%;
    color: #AD7245;
    -webkit-text-stroke: 1px 04030F;
}


#funbutt{
    margin-bottom: 15%;
}

.boxdesign{
     border: 2px solid white;
    border-radius: 10px;
    font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
}
.bottom-btn{
   margin-top: 240px;
    border: 2px solid #BFABCB;
    border-radius: 15px;
    background-color: white;
    color:green;
    width: 150px;
    height: 35px;
}
body{
    height: 1vh;
  font-family: 'Rubik','Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 0;
    padding: 0;
    background: #3B3646;
}
*{
    box-sizing: border-box;
}
.centerBox{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50px
}
.categoryWrapper{
    height: 210px;
    width: 460px;
    background: rgba(241, 68, 233, 0.37);
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
}
.categoryWrapper:after{
    position: absolute;
    top:0;
    left: 0;
    right:0;
    bottom: 0;
background: #857F8B;
background: -moz-linear-gradient(-45deg, #A29CA9 0%, #95909B 99%); /* FF3.6-15 */
background: -webkit-linear-gradient(-45deg, #A29CA9 0%,#95909B 99%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(135deg, #A29CA9 0%,#95909B 99%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    content: '';
    opacity: 0;
        -webkit-transition: opacity 0.9s ease 0s;
}
.categoryWrapper:hover:after{
    opacity: 0.4;
}
.categoryWrapper h1{
    color:white;
    font-size: 50px;
    letter-spacing: 2px;
    -webkit-transition: all 0.15s ease 0s;
    position: relative;
    z-index: 10;
}
.categoryWrapper:hover h1{
    transform: translateY(-10px);
}
.categoryWrapper button{
    position: absolute;
    transform: translatey(60px);
    -webkit-appearance: none;
    border: none;
    background: none;
    color:white;
    width: 250px;
    height:50px;
    font-size: 20px;
    padding: 0;
    margin: 0;
    outline: none;
    z-index: 10;
}
.categoryWrapper button span{
    display: block;
    position: relative;
    line-height: 50px;
    height: 50px;
    cursor: pointer;
}
.categoryWrapper button > span:after{
    content:'';
    position: absolute;
    top:0;
    left: 50%;
    width: 20px;
    height: 0;
    border: 1px solid white;
    border-left: none;
    border-bottom: none;
    transition: height 0.15s ease-out, width 0.15s ease-out 0.15s;
}
.categoryWrapper:hover button > span:after{
    width: calc(50% - 1px);
    height: calc(100% - 2px);
    transition: width 0.15s ease-out, height 0.15s ease-out 0.15s;
}
.categoryWrapper button > span:before{
    content:'';
    position: absolute;
    top:0;
    right: 50%;
    width: 20px;
    height: 0;
    border: 1px solid white;
    border-right: none;
    border-bottom: none;
    transition: height 0.15s ease-out, width 0.15s ease-out 0.15s;
}
.categoryWrapper:hover button > span:before{
    width: calc(50% - 1px);
    height: calc(100% - 2px);
    transition: width 0.15s ease-out, height 0.15s ease-out 0.15s;
}
.categoryWrapper button > span > span:before{
    content:'';
    position: absolute;
    bottom:0;
    right: 0%;
    width: 1px;
    height: 1px;
    opacity: 0;
}
.categoryWrapper:hover button > span > span:before{
    opacity: 1;
    border-bottom: 1px solid white;
    width: calc(50%);
    height: 1px;
    transition: opacity 0s ease-out 0.29s, width 0.15s ease-out 0.3s;
}
.categoryWrapper button > span > span:after{
    content:'';
    position: absolute;
    bottom:0;
    left: 0%;
    width: 1px;
    height: 1px;
    opacity: 0;
}
.categoryWrapper:hover button > span > span:after{
    opacity: 1;
    border-bottom: 1px solid white;
    width: calc(50%);
    height: 1px;
    transition: opacity 0s ease-out 0.29s, width 0.15s ease-out 0.3s;
}
.categoryWrapper button > span > span > span{
    transition: color 0.15s ease-out 0.3s;
    color: transparent;
}
.categoryWrapper:hover button > span > span > span{
    color:white;
}
.categoryWrapper button > span > span > span:after{
    position: absolute;
    top:0;left:0;right:0;bottom:0;
    color:#1f2e4d;
    content: attr(data-attr-span);
    width: 0%;
    height: 100%;
    background:white;
    white-space: nowrap;
    text-align: center;
    margin: auto;
    overflow: hidden;
    display: flex;
    justify-content: center;
    transition: width 0.2s;
}
.categoryWrapper button:hover > span > span > span:after{
    width: 100%;
}
#createPlan{
    margin-bottom: 70px;
    color:#FCF8F5;
}
</style>