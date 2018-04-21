<template> 
   <div > 
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
        <AVGChard :content="AVGData"></AVGChard>
      </el-col> 
     </el-row> 
    </div> 
   </div> 
  </template> 
<<script>
import {getAVGData} from "@/api/getData";
import AVGChard from '@/components/monitorComp/AVGChard.vue'
import ChartCard from '@/components/monitorComp/ChartCard.vue'
import search from "@/components/monitorComp/search.vue";
  export default {
  name: 'monitorChart',
  data () {
    return {
            chartData: [
                {name: '2014', value: 1342},
                {name: '2015', value: 2123},
                {name: '2016', value: 1654},
                {name: '2017', value: 1795},
            ],
            chartId1:'sad',
            chartId2:'gad',
            AVGData:{}
    }
  },
    methods:{
    async initData(){
      var params = {host:'DESKTOP-HQ4VTVM',startTime:'2017-10-11 10:00:00',endTime:'2017-10-12 15:00:00'};
      let response = await getAVGData(params);
      if(response.success){
        this.AVGData = response.result[0];
      }
      
    }
  },
  components: {
    'ChartCard': ChartCard,
    'search':search,
    'AVGChard':AVGChard
  },
  created(){
    this.initData();
  }
}
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
