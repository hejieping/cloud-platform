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
     <el-row>
       <el-form :inline="true" align="left">
         <el-form-item label="日期范围">
                        <el-date-picker
      v-model="time"
      type="datetimerange"
      placeholder="选择日期时间"
      value-format="yyyy-MM-dd HH:mm:ss">
    </el-date-picker>
         </el-form-item>
         <el-form-item>
             <el-button @click="search">查询</el-button>
         </el-form-item>
       </el-form>
     </el-row>
     <el-row :gutter="20" v-for="(datas,index) in AVGData" :key="index"> 
      <el-col :span="8" v-for="(data) in datas" :key="data.type">
        <AVGChard :content="data"></AVGChard>
      </el-col> 
     </el-row> 
    </div> 
   </div> 
  </template> 
<script>
import { getAVGData } from "@/api/getData";
import AVGChard from "@/components/monitorComp/AVGChard.vue";
import ChartCard from "@/components/monitorComp/ChartCard.vue";
import search from "@/components/monitorComp/search.vue";
export default {
  name: "monitorChart",
  data() {
    return {
      chartData: [
        { name: "2014", value: 1342 },
        { name: "2015", value: 2123 },
        { name: "2016", value: 1654 },
        { name: "2017", value: 1795 }
      ],
      chartId1: "sad",
      chartId2: "gad",
      AVGData: [],
      time: ""
    };
  },
  methods: {
    async initData(start, end) {
      var params = {
        host: this.$route.params.hostname,
        startTime: start,
        endTime: end
      };
      let response = await getAVGData(params);
      if (response.success) {
        //将数组切分，每份三个数据
        this.AVGData = [];
        var size;
        var index = 0;
        if (response.result.length % 3 == 0) {
          size = response.result.length / 3;
        } else {
          size = response.result.length / 3 + 1;
        }
        for (let i = 0; i < size; i++) {
          let tempData = [];
          for (let j = 0; j < 3; j++) {
            tempData.push(response.result[index++]);
          }
          this.AVGData.push(tempData);
        }
      }
    },
    search() {
      if (this.time != "") {
        this.initData(this.time[0], this.time[1]);
      }
    }
  },
  components: {
    ChartCard: ChartCard,
    search: search,
    AVGChard: AVGChard
  },
  created() {
    this.initData("2017-10-11 10:00:00", "2017-10-12 15:00:00");
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
