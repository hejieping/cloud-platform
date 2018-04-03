<template>
  <div>
      <el-row>
        <el-breadcrumb separator="/">
        <el-breadcrumb-item>报警</el-breadcrumb-item>
        <el-breadcrumb-item>报警规则配置</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>
      <el-row>
        <ruleTable></ruleTable>
      </el-row>
  </div>
</template>
<script>
import ruleTable from '@/components/warnRuleComp/ruleTable.vue'
export default {
  name: 'WarnRule',
  data () {
    return {
    }
  },
  components:{
      'ruleTable': ruleTable
  }
}   
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>