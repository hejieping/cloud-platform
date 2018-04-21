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
      <el-row>
      <el-date-picker
      v-model="startTime"
      type="datetime"
      placeholder="选择日期时间"
      value-format="yyyy-MM-dd HH:mm:ss">
    </el-date-picker>
      </el-row>
      <el-row>
        <el-button type="primary" @click="click">主要按钮</el-button>
      </el-row>
  </div>
</template>
<script>
import  SolutionTable from '@/components/solutionComp/SolutionTable.vue'

export default {
  name: 'SolutionConfig',
  data () {
    return {
      startTime:''
    }
  },
  methods:{
    click(){
      console.log(this.startTime)
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
