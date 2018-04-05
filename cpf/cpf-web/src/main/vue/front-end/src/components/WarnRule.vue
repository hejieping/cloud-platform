<template>
  <div>
      <el-row>
        <el-breadcrumb separator="/">
        <el-breadcrumb-item>报警</el-breadcrumb-item>
        <el-breadcrumb-item>报警规则配置</el-breadcrumb-item>
        </el-breadcrumb>
      </el-row>
      <el-row>
        <RuleTable></RuleTable>
      </el-row>
  </div>
</template>
<script>
import RuleTable from '@/components/warnRuleComp/RuleTable.vue'
export default {
  name: 'WarnRule',
  data () {
    return {
    }
  },
  components:{
      'RuleTable': RuleTable
  }
}   
</script>
<style scoped>
.el-row {
  margin-bottom: 20px;
}
</style>