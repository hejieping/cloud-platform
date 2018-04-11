<template>
  <div>
      <el-row>
              <el-breadcrumb separator="/">
        <el-breadcrumb-item >云平台</el-breadcrumb-item>
    <el-breadcrumb-item>配置</el-breadcrumb-item>
    <el-breadcrumb-item>解决方案配置</el-breadcrumb-item>
    </el-breadcrumb>
      </el-row>
      <el-row>
          <SolutionTable></SolutionTable>
      </el-row>
  </div>
</template>
<script>
import  SolutionTable from '@/components/solutionComp/SolutionTable.vue'

export default {
  name: 'SolutionConfig',
  data () {
    return {
    }
  },
  components:{
      'SolutionTable':SolutionTable
  }
}
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
