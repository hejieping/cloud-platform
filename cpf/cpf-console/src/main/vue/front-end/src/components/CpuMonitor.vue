<template> 
   <div id="CpuMonitor"> 
    <div> 
     <el-row> 
      <el-breadcrumb separator="/"> 
       <el-breadcrumb-item>
        云平台
       </el-breadcrumb-item> 
       <el-breadcrumb-item>
        监控
       </el-breadcrumb-item> 
       <el-breadcrumb-item>
        cpu监控
       </el-breadcrumb-item> 
      </el-breadcrumb> 
     </el-row>
     <el-row type="flex">
       <search></search>
     </el-row>
     <el-row :gutter="20"> 
      <el-col :span="12" > 
        <ChartCard :canvasId="chartId1" :data="chartData">
        </ChartCard>
      </el-col> 
            <el-col :span="12" > 
        <ChartCard :canvasId="chartId2" :data="chartData">
        </ChartCard>
      </el-col> 
     </el-row> 
    </div> 
   </div> 
  </template> 
<<script>
import ChartCard from '@/components/ChartCard.vue'
import  search from '@/components/monitorComp/search.vue'
  export default {
  name: 'CpuMonitor',
  data () {
    return {
            chartData: [
                {name: '2014', value: 1342},
                {name: '2015', value: 2123},
                {name: '2016', value: 1654},
                {name: '2017', value: 1795},
            ],
            chartId1:'sad',
            chartId2:'gad'
    }
  },
  components: {
    'ChartCard': ChartCard,
    'search':search
  }
}
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
