<template> 
   <div class="hello"> 
    <el-container> 
     <el-header>
      <el-menu class="el-menu-demo" mode="horizontal" background-color="#324057" text-color="#fff" active-text-color="#ffffff">
        <el-menu-item index="1">
          云平台
        </el-menu-item>
      </el-menu>
     </el-header> 
     <el-container> 
      <el-aside width="200px"> 
       <el-row > 
        <el-col> 
         <el-menu default-active="2" class="el-menu-vertical" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router> 
          <el-submenu index="1"> 
           <template slot="title"> 
            <i class="el-icon-location"></i> 
            <span>监控</span> 
           </template> 
           <el-menu-item-group> 
            <el-menu-item index="CpuMonitor">
             CPU
            </el-menu-item> 
            <el-menu-item index="ProcessMonitor">
             进程
            </el-menu-item> 
           </el-menu-item-group> 
          </el-submenu> 
          <el-menu-item index="Warn"> 
           <i class="el-icon-menu"></i> 
           <span slot="title">预警</span> 
          </el-menu-item> 
         </el-menu> 
        </el-col> 
       </el-row> 
      </el-aside> 
      <el-main> 
       <router-view></router-view> 
      </el-main> 
     </el-container> 
    </el-container> 
   </div> 
</template> 
export default {
  name: 'Layout',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script> 
  <style scoped="">
  .el-header{
    background-color: #324057;
    color: #333;
    width: 100%
  }

  .el-aside {
    background-color: #545c64;
    color: #333;
    text-align: center;
    height: 1000px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 1000px;
  }

  
</style>