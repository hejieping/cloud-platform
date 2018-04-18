<template>
  <div>
      <el-dialog :title="data.title" :visible.sync="RuleAddDialogState" width="30%">
      <el-form align="left">
                    <el-form-item label="类型">
                          <el-select v-model="data.data.type" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
        </el-form-item>
      </el-form>
      <div v-if="data.data.type == 'cpu'">
          <CpuRule :data="data.data"  @saveRule="saveRule"></CpuRule>
      </div>
      <div v-else-if="data.data.type == 'disk'">
          <DiskRule :data="data.data"  @saveRule="saveRule"></DiskRule>
      </div>
      <div v-else-if="data.data.type == 'diskio'">
          <DiskIORule :data="data.data"  @saveRule="saveRule"></DiskIORule>
      </div>      
      <div v-else></div>

</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
import copy from "@/utils/copy"
import CpuRule from "@/components/warnRuleComp/CpuRule.vue";
import DiskRule from "@/components/warnRuleComp/DiskRule.vue";
import DiskIORule from "@/components/warnRuleComp/DiskIORule.vue";
export default {
  name: "RuleAddDialog",
  data() {
    return {
      formLabelWidth: "120px",
      options: [{
          value: 'cpu',
          label: '处理器'
        }, {
          value: 'mem',
          label: '内存'
        }, {
          value: 'disk',
          label: '硬盘'
        }, {
          value: 'diskio',
          label: '硬盘IO'
        }, {
          value: 'system',
          label: '系统'
        },{
          value: 'neit',
          label: '网络'
        },{
          value: 'perf_counters',
          label: '性能计数器'
        },{
          value: 'swap',
          label: '交换区'
        },{
          value: 'system_days',
          label: '系统天数'
        }],
        validateResult:{}
    };
  },
  methods: {
    closeDialog() {
      this.$store.commit("closeRuleAddDialog");
    },
    saveRule(rule){
      this.$emit("saveRule",rule);
    }
  },
  props:{
    data:{}
  },
  computed: {
    RuleAddDialogState: {
      get() {
        return this.$store.state.RuleAddDialogState;
      },
      //只有关闭按钮会调用该函数，所以默认设置为关闭方法
      set: function(newValue) {
        this.$store.commit("closeRuleAddDialog");
      }
    }
  },
  created(){

  },
  components:{
    'CpuRule':CpuRule,
    'DiskRule':DiskRule,
    'DiskIORule':DiskIORule
  }

};
</script>
