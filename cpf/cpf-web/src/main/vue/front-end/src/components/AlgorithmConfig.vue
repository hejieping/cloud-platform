<template>
  <div>
    <el-row>
    <el-breadcrumb separator="/">
    <el-breadcrumb-item >云平台</el-breadcrumb-item>
    <el-breadcrumb-item>配置</el-breadcrumb-item>
    <el-breadcrumb-item>预警算法配置</el-breadcrumb-item>
    </el-breadcrumb>
    </el-row>
    <router-view @goDetail="goDetail"></router-view> 
  </div>
</template>
<script>

export default {
  name: "AlgorithmConfig",
  data() {
    return {
    };
  },
  methods:{
    goDetail(id){
      this.$router.push('/AlgorithmConfig/'+id);
    }
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
