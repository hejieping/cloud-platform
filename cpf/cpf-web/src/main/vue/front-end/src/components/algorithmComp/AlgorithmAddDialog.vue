<template>
  <div>
      <el-dialog title="选择算法模型" :visible.sync="AlgorithmAddDialogState" width="400px" >
  <el-form :model="form" align="left">
    <el-form-item label="模型名称" :label-width="formLabelWidth">
      <el-row>
        <el-col :span="17">
          <el-input v-model="form.name" placeholder="请输入名称" ></el-input>
        </el-col>
      </el-row>
    </el-form-item>
    <el-form-item label="算法模型" :label-width="formLabelWidth">
      <el-select v-model="form.modelType" >
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
    <el-button type="primary" @click="submit(form)">确 定</el-button>
  </div>
</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { getModelTypes,getModel} from "@/api/getData";
export default {
  name: "AlgorithmAddDialog",
  data() {
    return {
      options: [],
      formLabelWidth: "80px",
      form: {
        modelType: "",
        name:""
      }
    };
  },
  methods: {
    closeDialog() {
      this.$store.commit("closeAlgAddDialog");
    },
    
    async getModelData (form){
      const model = await getModel(form);
      return model;
    },
    async submit(form){
      this.closeDialog();
      const model = await this.getModelData(form);
      this.$emit('addModel',model);
      
    },
    async initData() {
      try {
        const options = await getModelTypes();
        this.options = options;
      } catch (error) {
        console.log(error);
      }
    },
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
