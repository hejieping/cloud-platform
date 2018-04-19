<template>
<div>
    <el-form :rules="rules" :model="data.config" ref="ruleForm">
    <el-row>
      <el-col :span="20">
        <el-form-item label="名称" prop="name" >
          <el-input v-model="data.config.name" auto-complete="off" ></el-input>
        </el-form-item>
        <el-form-item label="持续时间" prop="time" >
          <el-input v-model="data.config.time" auto-complete="off" placeholder="单位为分钟，为空代表立即报警"></el-input>
        </el-form-item>
        <el-form-item label="Available_MB最大值" prop="Available_MB">
            <el-input v-model="data.config.Available_MB"  placeholder="单位为MB，为空条件不限制"></el-input>
        </el-form-item>
        <el-form-item label="Cache_Faults_persec最大值" prop="Cache_Faults_persec">
            <el-input v-model="data.config.Cache_Faults_persec" placeholder="正浮点数，为空条件不限制"></el-input>
        </el-form-item>    
        <el-form-item label="Demand_Zero_Faults_persec最大值" prop="Demand_Zero_Faults_persec">
            <el-input v-model="data.config.Demand_Zero_Faults_persec"  placeholder="正浮点数，为空条件不限制"></el-input>
        </el-form-item> 
        <el-form-item label="Page_Faults_persec最大值" prop="Page_Faults_persec">
            <el-input v-model="data.config.Page_Faults_persec"  placeholder="正浮点数，为空条件不限制"></el-input>
        </el-form-item> 
        <el-form-item label="Pages_persec最大值" prop="Pages_persec">
            <el-input v-model="data.config.Pages_persec"  placeholder="正浮点数，为空条件不限制"></el-input>
        </el-form-item>
        <el-form-item label="Pool_Nonpaged_MB最大值" prop="Pool_Nonpaged_MB">
            <el-input v-model="data.config.Pool_Nonpaged_MB"  placeholder="单位为MB，为空条件不限制"></el-input>
        </el-form-item> 
        <el-form-item label="Pool_Paged_MB最大值" prop="Pool_Paged_MB">
            <el-input v-model="data.config.Pool_Paged_MB"  placeholder="单位为MB，为空条件不限制"></el-input>
        </el-form-item>
        <el-form-item label="Standby_Cache_Core_MB最大值" prop="Standby_Cache_Core_MB">
            <el-input v-model="data.config.Standby_Cache_Core_MB"  placeholder="单位为MB，为空条件不限制"></el-input>
        </el-form-item>
        <el-form-item label="Standby_Cache_Normal_Priority_MB最大值" prop="Standby_Cache_Normal_Priority_MB">
            <el-input v-model="data.config.Standby_Cache_Normal_Priority_MB"  placeholder="单位为MB，为空条件不限制"></el-input>
        </el-form-item>
        <el-form-item label="Standby_Cache_Reserve_MB最大值" prop="Standby_Cache_Reserve_MB">
            <el-input v-model="data.config.Standby_Cache_Reserve_MB"  placeholder="单位为MB，为空条件不限制"></el-input>
        </el-form-item>
        <el-form-item label="Transition_Faults_persec最大值" prop="Transition_Faults_persec">
            <el-input v-model="data.config.Transition_Faults_persec"  placeholder="0~100，为空条件不限制"></el-input>
        </el-form-item>                                   
      </el-col>
    </el-row>
  </el-form>
    <div slot="footer" class="dialog-footer">
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </div>
</div>
</template>
<script>
import { mapState } from "vuex";
import {saveRule} from "@/api/getData";
import {checkPercent,checkInteger,checkDouble} from "@/utils/validate"
export default {
  name: "MemRule",
  data() {
    return {
      rules: {
        Available_MB: [{ validator: checkInteger, trigger: "blur" }],
        Cache_Faults_persec: [{ validator: checkDouble, trigger: "blur" }],
        Demand_Zero_Faults_persec: [{ validator: checkDouble, trigger: "blur" }],
        Page_Faults_persec: [{ validator: checkDouble, trigger: "blur" }],
        Pages_persec: [{ validator: checkDouble, trigger: "blur" }],
        Pool_Nonpaged_MB: [{ validator: checkInteger, trigger: "blur" }],
        Pool_Paged_MB: [{ validator: checkInteger, trigger: "blur" }],
        Standby_Cache_Core_MB: [{ validator: checkInteger, trigger: "blur" }],
        Standby_Cache_Normal_Priority_MB: [{ validator: checkInteger, trigger: "blur" }],
        Standby_Cache_Reserve_MB: [{ validator: checkInteger, trigger: "blur" }],
        Transition_Faults_persec: [{ validator: checkDouble, trigger: "blur" }],
        time:[{ validator: checkInteger, trigger: "blur" }],
        name:[{required:true,message:'不能为空', trigger: 'blur'}]
      }
    };
  },
  methods: {
     submit() {
      this.$refs.ruleForm.validate((valid)=>{
        if(valid){
          this.saveModel();
        }else{

        }
      });
    },
    closeDialog() {
      this.$store.commit("closeRuleAddDialog");
    },
    async saveModel() {
      this.data.name = this.data.config.name;
      this.data.time = this.data.config.time;
      const response = await saveRule(this.data);
      if(response.success){
        this.$emit("saveRule",response.result);
        this.$message('保存规则成功');
        this.closeDialog();
      }else{
        this.$message('保存规则失败');
      }
    }
  },
  props: {
    data: {}
  },
  created(){
    if(this.data.config == undefined){
      //elementUI 表单校验只能校验单层属性，所以将name放在config层
      this.data.config = {name:this.data.name};
    }
  }
};
</script>
