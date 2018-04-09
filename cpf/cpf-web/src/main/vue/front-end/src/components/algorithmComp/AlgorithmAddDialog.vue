<template>
  <div>
      <el-dialog title="选择算法模型" :visible.sync="AlgorithmAddDialogState" width="400px" >
  <el-form :model="form" align="left">
    <el-form-item label="算法模型" :label-width="formLabelWidth">
      <el-select v-model="form.option" >
                <el-option
                v-for="option in options"
                :key="option.value"
                :label="option.label"
                :value="option.value">
                </el-option>
      </el-select>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer" >
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="closeDialog">确 定</el-button>
  </div>
</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { modelType } from "@/api/getData";
export default {
  name: "AlgorithmAddDialog",
  data() {
    return {
      options: [],
      formLabelWidth: "80px",
      form: {
        option: ""
      }
    };
  },
  methods: {
    closeDialog() {
      this.$store.commit("closeAlgAddDialog");
    },
    async initData() {
      try {
        const options = await modelType();
        this.options = options;
      } catch (error) {
        console.log(error);
      }
    }
  },
  computed: {
    AlgorithmAddDialogState: {
      get() {
        return this.$store.state.AlgorithmAddDialogState;
      },
      //只有关闭按钮会调用该函数，所以默认设置为关闭方法
      set: function(newValue) {
        this.$store.commit("closeAlgAddDialog");
      }
    }
  },
  created() {
    this.initData();
  }
};
</script>
