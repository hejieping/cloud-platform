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
          <AlgorithmEdit :model="model"  @deleteModel="deleteModel"></AlgorithmEdit>
        </el-collapse-item>
      </el-collapse>
    </el-row>
  </div>
</template>
<script>
import { mapState } from "vuex";
import AlgorithmEdit from "@/components/algorithmComp/AlgorithmEdit.vue";
import AlgorithmAddDialog from "@/components/algorithmComp/AlgorithmAddDialog.vue";
import { getAllModel } from "@/api/getData";
export default {
  name: "AlgorithmConfig",
  data() {
    return {
      aggreModel: {
        models: []
      }
    };
  },
  methods: {
    calTitle(name, weight) {
      return name + "   权重：" + weight;
    },
    openDialog() {
      this.$store.commit("openAlgAddDialog");
    },
    addModel(model) {
      this.aggreModel.models.push(model);
    },
    deleteModel(id) {
      let models = this.aggreModel.models;
      models.splice(models.findIndex(model => model.id == id), 1);
    },
    async initModels() {
      const response = await getAllModel();
      if (response.success) {
        this.aggreModel.models = response.result;
      } else {
        this.$message("获取模型失败");
      }
    }
  },
  components: {
    AlgorithmEdit: AlgorithmEdit,
    AlgorithmAddDialog: AlgorithmAddDialog
  },
  created() {
    this.initModels();
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
