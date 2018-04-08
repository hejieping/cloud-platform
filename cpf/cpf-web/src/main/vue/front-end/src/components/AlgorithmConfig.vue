<template>
  <div>
    <el-row>
          <el-breadcrumb separator="/">
    <el-breadcrumb-item >云平台</el-breadcrumb-item>
    <el-breadcrumb-item>配置</el-breadcrumb-item>
    <el-breadcrumb-item>预警算法配置</el-breadcrumb-item>
    </el-breadcrumb>
    </el-row>
    <el-row>
      <el-collapse >
        <el-collapse-item v-for="model in aggreModel.models" :key="model.name" :title="calTitle(model.name,model.weight)" :name="activeIndex++">
          <AlgorithmEdit :config="model.config"></AlgorithmEdit>
        </el-collapse-item>
      </el-collapse>
    </el-row>
  </div>
</template>
<script>
import  AlgorithmEdit from '@/components/algorithmComp/AlgorithmEdit.vue'
export default {
  name: "AlgorithmConfig",
  data() {
    return {
      activeIndex:1,
      aggreModel: {
        models: [
          {
            name: "svm",
            weight: 1,
            config: [{
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
          },
                   {
            name: "bayes",
            weight: 1,
            config: [{
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
        ]
      }
    };
  },
  methods:{
    calTitle(name,weight){
      return name+ "   权重：" + weight;
    }
  },
  components:{
    'AlgorithmEdit':AlgorithmEdit
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
