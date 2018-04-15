<template> 
   <div id="CpuMonitor"> 
    <div> 
     <el-row>
       <router-view></router-view> 
     </el-row>
    </div> 
   </div> 
  </template> 
<<script>
  export default {
  name: 'Monitor',
  data () {
    return {
    }
  }
}
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
