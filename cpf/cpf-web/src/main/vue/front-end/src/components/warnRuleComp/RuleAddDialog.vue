<template>
  <div>
      <el-dialog :title="data.title" :visible.sync="RuleAddDialogState" width="30%">
  <el-form  >
    <el-row>
      <el-col :span="20">
              <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="data.data.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="cpu最大使用率" :label-width="formLabelWidth">
            <el-input-number v-model="data.data.cpuMax" :min="0" :max="100"></el-input-number>
        </el-form-item>  
      </el-col>
    </el-row>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="saveModel">确 定</el-button>
  </div>
</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
import {saveRule} from "@/api/getData";
import copy from "@/utils/copy"
export default {
  name: "RuleAddDialog",
  data() {
    return {
      formLabelWidth: "120px",
    };
  },
  methods: {
    closeDialog() {
      this.$store.commit("closeRuleAddDialog");
    },
    async saveModel(){
      const response = await saveRule(this.data.data);
      if(response.success){
        this.$emit("saveRule",response.result);
        this.$message('修改规则成功');
        this.closeDialog();
      }else{
        this.$message('修改规则失败');
      }
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
  }

};
</script>
