<template>
  <div>
    <el-row>
          <el-breadcrumb separator="/">
        <el-breadcrumb-item >云平台</el-breadcrumb-item>
    <el-breadcrumb-item>监控</el-breadcrumb-item>
    <el-breadcrumb-item>报警信息</el-breadcrumb-item>
    </el-breadcrumb>
    </el-row>
    <el-row>
        <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="监控数据" align='left'>
            <span>{{ props.row.monitorData }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
    align='left'
      label="id"
      prop="id">
    </el-table-column>
    <el-table-column
    align='left'
      label="报警类型"
      prop="alarmType">
    </el-table-column>
    <el-table-column
    align='left'
      label="数据类型"
      prop="monitorType">
    </el-table-column>
      <el-table-column
      align='left'
      label="监控规则"
      prop="ruleName">
    </el-table-column>
      <el-table-column
      align='left'
      label="时间"
      prop="time">
    </el-table-column>    
  </el-table>
    </el-row>
  </div>
</template>
<script>
import { getAlarms } from "@/api/getData";
export default {
  name: 'SmartWarn',
  data () {
    return {
      tableData:[]
    }
  },
  methods:{
    async init(){
      const response = await getAlarms();
      if (response.success) {
        this.tableData = response.result;
      } else {
        this.$message("获取报警数据失败");
      }
    }
  },
  mounted(){
    this.init();
  }
}
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
