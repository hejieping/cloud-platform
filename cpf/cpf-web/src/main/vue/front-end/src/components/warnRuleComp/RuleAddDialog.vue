<template>
  <div>
      <el-dialog title="收货地址" :visible.sync="RuleAddDialogState">
  <el-form :model="form">
    <el-form-item label="活动名称" :label-width="formLabelWidth">
      <el-input v-model="form.name" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="活动区域" :label-width="formLabelWidth">
      <el-select v-model="form.region" placeholder="请选择活动区域">
        <el-option label="区域一" value="shanghai"></el-option>
        <el-option label="区域二" value="beijing"></el-option>
      </el-select>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="closeDialog">确 定</el-button>
  </div>
</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "RuleAddDialog",
  data() {
    return {
      formLabelWidth: "120px",
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      },
      dialogFormVisible: false
    };
  },
  methods: {
    closeDialog() {
      this.$store.commit("closeRuleAddDialog");
    }
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
  }
};
</script>
