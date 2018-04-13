<template>
  <div>
      <el-dialog :title="title" :visible.sync="AggreModelAddDialogState" width="400px" >
  <el-form  align="left" :rules="rules">
    <el-form-item :model="aggremodel" ref="aggremodel" label="名称" :label-width="formLabelWidth" prop="name">
      <el-row>
        <el-col :span="17">
          <el-input v-model="aggremodel.name" placeholder="请输入名称" ></el-input>
        </el-col>
      </el-row>
    </el-form-item>
    <el-form-item label="场景" :label-width="formLabelWidth" prop="scene">
      <el-select v-model="aggremodel.scene" >
                <el-option
                v-for="scene in scenes"
                :key="scene.value"
                :label="scene.label"
                :value="scene.value">
                </el-option>
      </el-select>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer" >
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="submit(aggremodel)">确 定</el-button>
  </div>
</el-dialog>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { saveAggreModel} from "@/api/getData";
export default {
  name: "AlgorithmAddDialog",
  data() {
    return {
      formLabelWidth: "80px",
      scenes:[{
          value:'computer',
          label:'电脑'
      },{
          value:'print',
          label:'打印机'
      },{
          value:'projector',
          label:'投影仪'
      }],
      rules:{
        scene:[
            { required: true, message: '请选择应用场景', trigger: 'blur' }
          ],
        name:[
              { required: true, message: '请输入名称', trigger: 'blur' }
          ]
      }
    };

  },
  methods: {
    closeDialog() {
      this.$store.commit("closeAMAddDialog");
    },
    async submit(form){
      let result =  this.$refs['aggremodel'].validate((valid,obj) => {
          if (valid) {
          }else{
            return false;
          }
          });
      console.log(result);

      const  response = await saveAggreModel(form);
      if(response.success){
        this.$emit('saveAggreModel',response.result);
        this.$message('新建模型成功');
        this.closeDialog();
      }else{
        this.$message('新建模型失败');
      }
    }
  },
    props:{
    aggremodel:{},
    title:""
  },
  computed: {
    AggreModelAddDialogState: {
      get() {
        return this.$store.state.AggreModelAddDialogState;
      },
      //只有关闭按钮会调用该函数，所以默认设置为关闭方法
      set: function(newValue) {
        this.$store.commit("closeAMAddDialog");
      }
    }
  },
  created() {
  }
};
</script>
