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
      <div v-if="data.data.type == 'win_cpu'">
          <CpuRule :data="data.data"  @saveRule="saveRule"></CpuRule>
      </div>
      <div v-else-if="data.data.type == 'win_disk'">
          <DiskRule :data="data.data"  @saveRule="saveRule"></DiskRule>
      </div>
      <div v-else-if="data.data.type == 'win_diskio'">
          <DiskIORule :data="data.data"  @saveRule="saveRule"></DiskIORule>
      </div>
      <div v-else-if="data.data.type == 'win_mem'">
          <MemRule :data="data.data"  @saveRule="saveRule"></MemRule>
      </div>     
      <div v-else-if="data.data.type == 'win_net'">
          <NetRule :data="data.data"  @saveRule="saveRule"></NetRule>
      </div>
      <div v-else-if="data.data.type == 'win_perf_counters'">
          <PerfRule :data="data.data"  @saveRule="saveRule"></PerfRule>
      </div>    
      <div v-else-if="data.data.type == 'win_swap'">
          <SwapRule :data="data.data"  @saveRule="saveRule"></SwapRule>
      </div> 
      <div v-else-if="data.data.type == 'win_system'">
          <SystemRule :data="data.data"  @saveRule="saveRule"></SystemRule>
      </div> 
      <div v-else-if="data.data.type == 'win_system_days'">
          <SystemDaysRule :data="data.data"  @saveRule="saveRule"></SystemDaysRule>
      </div>
      <div v-else></div>

</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
import copy from "@/utils/copy"
import CpuRule from "@/components/warnRuleComp/CpuRule";
import DiskRule from "@/components/warnRuleComp/DiskRule";
import DiskIORule from "@/components/warnRuleComp/DiskIORule";
import MemRule from "@/components/warnRuleComp/MemRule";
import NetRule from "@/components/warnRuleComp/NetRule";
import PerfRule from "@/components/warnRuleComp/PerfRule";
import SwapRule from "@/components/warnRuleComp/SwapRule";
import SystemRule from "@/components/warnRuleComp/SystemRule";
import SystemDaysRule from "@/components/warnRuleComp/SystemDaysRule";

export default {
  name: "RuleAddDialog",
  data() {
    return {
      formLabelWidth: "120px",
      options: [{
          value: 'win_cpu',
          label: '处理器'
        }, {
          value: 'win_mem',
          label: '内存'
        }, {
          value: 'win_disk',
          label: '硬盘'
        }, {
          value: 'win_diskio',
          label: '硬盘IO'
        }, {
          value: 'win_system',
          label: '系统'
        },{
          value: 'win_net',
          label: '网络'
        },{
          value: 'win_perf_counters',
          label: '性能计数器'
        },{
          value: 'win_swap',
          label: '交换区'
        },{
          value: 'win_system_days',
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
    'DiskIORule':DiskIORule,
    'MemRule':MemRule,
    'NetRule':NetRule,
    'PerfRule':PerfRule,
    'SwapRule':SwapRule,
    'SystemRule':SystemRule,
    'SystemDaysRule':SystemDaysRule
  }

};
</script>
