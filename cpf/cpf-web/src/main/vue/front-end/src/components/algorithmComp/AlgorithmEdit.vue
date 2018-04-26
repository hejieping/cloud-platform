<template>
  <div>
      <el-form  :rules="rules" ref="AlgorithmEditForm"  label-width="100px" align="left" label-position='top' >
                          <el-row  >
                    <el-col :span="8" :offset="1">
            <div  v-for="option in formData" :key="option.key">
        <div v-if="option.valueType == 'BOOLEAN'">
          <el-form-item :label="option.desc" :prop="option.key" >
                            <el-switch v-model="option.value"></el-switch>
          </el-form-item>
         </div>
        <div v-else-if="option.valueType == 'ENUM'">
          <el-form-item :label="option.desc" :prop="option.key" >
            <el-select v-model="option.value" placeholder="请选择">
                <el-option
                v-for="(desc,key) in option.extension"
                :key="key"
                :label="desc"
                :value="key">
                </el-option>
            </el-select>
          </el-form-item>
         </div>
        <div v-else-if="option.valueType == 'INTEGER'">
          <el-form-item :label="option.desc" :prop="option.key" :rules="IntegerRule">
              <el-input v-model="option.value" placeholder="请输入正数" size="small"></el-input>
          </el-form-item>
         </div>
          <div v-else>
          <el-form-item :label="option.desc" :prop="option.key" :rules="DoubleRule">
              <el-input v-model="option.value" placeholder="请输入浮点数" size="small"></el-input>
          </el-form-item>
         </div>             
            </div>
            <el-form-item label="权重">
            <el-input v-model="model.weight" placeholder="请输入权重" size="small"></el-input>
            </el-form-item>  
            <el-form-item>
                <el-button type="primary" @click="editModel">确认修改</el-button>
                <el-button type="danger" @click="deleteModel">删除模型</el-button>
            </el-form-item>            
                    </el-col>
                </el-row>
      </el-form>
  </div>
</template>
<script>
import AlgorithmOption from "@/components/algorithmComp/AlgorithmOption.vue";
import { checkDouble, checkInteger } from "@/utils/validate";
import { saveModel, deleteModelByid } from "@/api/getData";
export default {
  name: "AlgorithmEdit",
  data() {
    return {
      formData: {},
      rules: {},
      IntegerRule: [{ validator: checkInteger, trigger: "blur" }],
      DoubleRule: [{ validator: checkDouble, trigger: "blur" }]
    };
  },
  props: {
    model: {}
  },
  methods: {
    async editModel() {
      this.model.config.options = this.formData;
      let response = await saveModel(this.model);
      if (response.success) {
        this.$message("保存配置成功");
      } else {
        this.$message("保存配置失败");
      }
    },
    async deleteModel() {
      let param = { id: this.model.id };
      let response = await deleteModelByid(param);
      if (response.success) {
        this.$emit("deleteModel", this.model.id);
        this.$message("删除配置成功");
      } else {
        this.$message("删除配置失败");
      }
    },
    initRule() {
      for (let option of this.model.config.options) {
        if (option.valueType == "INTEGER") {
          this.rules[option.key] = [
            { validator: checkInteger, trigger: "blur" }
          ];
        }
        if (option.valueType == "DOUBLE") {
          this.rules[option.key] = [
            { validator: checkDouble, trigger: "blur" }
          ];
        }
      }
    }
  },
  components: {
    AlgorithmOption: AlgorithmOption
  },
  created() {
    this.initRule();
    this.formData = this.model.config.options;
  }
};
</script>
