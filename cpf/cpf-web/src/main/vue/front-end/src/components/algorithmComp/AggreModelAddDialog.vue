<template>
  <div>
      <el-dialog :title="title" :visible.sync="AggreModelAddDialogState" width="400px" >
  <el-form  align="left" :rules="rules" :model="aggremodel" ref="aggremodel">
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
import { saveAggreModel, getScenes } from "@/api/getData";
export default {
  name: "AlgorithmAddDialog",
  data() {
    return {
      formLabelWidth: "80px",
      scenes: [],
      rules: {
        scene: [{ required: true, message: "请选择应用场景", trigger: "blur" }]
      }
    };
  },
  methods: {
    closeDialog() {
      this.$store.commit("closeAMAddDialog");
    },
    async submit(form) {
      let result = this.$refs["aggremodel"].validate(async (valid, obj) => {

        if (valid) {
          if (this.validateScene()) {
            const response = await saveAggreModel(form);
            if (response.success) {
              this.$emit("saveAggreModel", response.result);
              this.$message("新建模型成功");
              this.closeDialog();
            } else {
              this.$message("新建模型失败");
            }
          }else{
               this.$message("应用场景已经存在");
          }
        } else {
          return false;
        }
      });
    },
    async initScenes() {
      const response = await getScenes();
      if (response.success) {
        this.scenes = response.result;
      } else {
        this.$message("获取应用场景类型失败");
      }
    },
    validateScene() {
      //id无定义则为新增状态
      if (this.aggremodel.id == undefined) {
        let result = this.usedScenes.find(
          usedScene => usedScene == this.aggremodel.scene
        );
        if (result == undefined) {
          return true;
        } else {
          return false;
        }
      } else {
        return true;
      }
    }
  },

  props: {
    aggremodel: {},
    title: "",
    usedScenes: {}
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
  mounted() {
    this.initScenes();
  }
};
</script>
