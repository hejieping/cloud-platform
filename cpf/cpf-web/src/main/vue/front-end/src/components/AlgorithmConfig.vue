<template>
  <div>
    <el-row>
    <el-breadcrumb separator="/">
    <el-breadcrumb-item >云平台</el-breadcrumb-item>
    <el-breadcrumb-item>配置</el-breadcrumb-item>
    <el-breadcrumb-item>预警算法配置</el-breadcrumb-item>
    </el-breadcrumb>
    </el-row>
      <algorithm-add-dialog @addModel="addModel"></algorithm-add-dialog>

  <el-row type="flex">
      <el-button-group >
        <el-button type="primary" icon="el-icon-plus" @click="openDialog">新增</el-button>
      </el-button-group>
   </el-row>
    <el-row >
      <el-collapse >
        <el-collapse-item v-for="model in aggreModel.models" :key="model.name" :title="calTitle(model.name,model.weight)" >
          <AlgorithmEdit :config="model.config.options"></AlgorithmEdit>
        </el-collapse-item>
      </el-collapse>
    </el-row>
  </div>
</template>
<script>
import { mapState } from "vuex";
import  AlgorithmEdit from '@/components/algorithmComp/AlgorithmEdit.vue'
import AlgorithmAddDialog from '@/components/algorithmComp/AlgorithmAddDialog.vue'
export default {
  name: "AlgorithmConfig",
  data() {
    return {
      aggreModel: {
        models: [
          {
            name: "svm",
            weight: 1,
            config: {
              options:[{
                key:"-p",
                desc: "使用核函数",
                value: false,
                valueType: "bool"
              },{
                key:"-c",
                desc: "迭代次数",
                value: 2,
                valueType: "integer"
              }, {
                key:"-d",
                desc: "高斯值",
                value: 0.001,
                valueType: "double"
              }, {
                key:"-e",
                desc: "指定模型",
                value: "",
                valueType: "enum",
                extension: ["asd", "grqv", "wer"]
              }
            ]
            }
          },
                   {
            name: "bayes",
            weight: 1,
            config: {
              options:[{
                key:"-p",
                desc: "使用核函数",
                value: false,
                valueType: "bool"
              },{
                key:"-c",
                desc: "迭代次数",
                value: 2,
                valueType: "integer"
              }, {
                key:"-d",
                desc: "高斯值",
                value: 0.001,
                valueType: "double"
              }, {
                key:"-e",
                desc: "指定模型",
                value: "",
                valueType: "enum",
                extension: ["asd", "grqv", "wer"]
              }
            ]
            }
          }
        ]
      }
    };
  },
  methods:{
    calTitle(name,weight){
      return name+ "   权重：" + weight;
    },
    openDialog(){
          this.$store.commit('openAlgAddDialog')
        },
    addModel(model){
      console.log("config")
      console.log(JSON.stringify(model))
      this.aggreModel.models.push(model);
    }
  },
  components:{
    'AlgorithmEdit':AlgorithmEdit,
    'AlgorithmAddDialog':AlgorithmAddDialog
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
