<template>
  <div>
      <el-form  :rules="rules" ref="ruleForm" label-width="100px" align="left">
                          <el-row  >
                    <el-col :span="8">
          <el-form-item v-for="option in model.config.options" :key="option.key" :label="option.desc" :prop="option.key" >
                 <div>
        <div v-if="option.valueType == 'BOOLEAN'">
                <el-switch v-model="option.value"></el-switch>
         </div>
         <div v-else-if="option.valueType === 'ENUM'">
            <el-select v-model="option.value" placeholder="请选择">
                <el-option
                v-for="key in option.extension"
                :key="key"
                :label="key"
                :value="key">
                </el-option>
            </el-select>
         </div>
         <div v-else>
            <el-input v-model="option.value" placeholder="请输入内容" size="small"></el-input>
         </div>
    </div>
            </el-form-item>
            <el-form-item label="权重">
            <el-input v-model="model.weight" placeholder="请输入权重" size="small"></el-input>
            </el-form-item>  
            <el-form-item>
                <el-button type="primary" @click="editModel">确认修改</el-button>
            </el-form-item>            
                    </el-col>
                </el-row>

      </el-form>
  </div>
</template>
<script>
import AlgorithmOption from "@/components/algorithmComp/AlgorithmOption.vue";
import {saveModel} from "@/api/getData";
export default {
  name: "AlgorithmEdit",
  data() {
    return {
      formData: {},
      rules: {},
    };
  },
  props: {
    model: {}
  },
  methods: {
    checkDouble(rule, value, callback) {
      if (
        !/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/.test(
          value
        )
      ) {
        return callback(new Error("必须为浮点数"));
      }
      callback();
    },
    async editModel(){
        this.model.config.options = this.formData;
        let result = await saveModel(this.model);
        if(result==true){
            this.$message('保存配置成功');
        }else{
            this.$message('保存配置失败');
        }
    },
    initRule() {
      var checkInteger = (rule, value, callback, source, options) => {
        console.log(JSON.stringify(options));
        if (!/^\+?[1-9][0-9]*$/.test(value)) {
          return callback(new Error("必须为正整数"));
        }
        callback();
      };
    //   for (let option of this.config) {
    //     if (option.valueType == "INTEGER") {
    //       this.rules[option.key] = [
    //         {validator: checkInteger, trigger: "blur" }
    //       ];
    //     }
    //     if (option.valueType == "DOUBLE") {
    //       this.rules[option.key] = [
    //         { required: true, validator: this.checkDouble, trigger: "blur" }
    //       ];
    //     }
    //   }
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
