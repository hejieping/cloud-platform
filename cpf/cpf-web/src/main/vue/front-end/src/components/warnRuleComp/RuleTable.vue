 <template>
 <div>
   <RuleAddDialog :data="ruleDialogData" @saveRule="saveRule"></RuleAddDialog>
   <el-row type="flex">
     <el-button-group >
  <el-button type="primary" icon="el-icon-plus" @click="openDialog">新增</el-button>
   <!-- <el-button type="primary" icon="el-icon-minus">批量删除</el-button> -->
</el-button-group>
   </el-row>
   <el-row>
            <el-table
      :data="tableData"
      style="width: 100%">
          <el-table-column
      type="selection"
      align='left'
      width="55">
    </el-table-column>
      <el-table-column
        align='left'
        prop="id"
        label="ID"
        width="150">
      </el-table-column>
      <el-table-column
      align='left'
        prop="name"
        label="名称"
        width="120">
      </el-table-column>
      <el-table-column
      align='left'
        prop="type"
        label="类型"
         width="300">
      </el-table-column>
      <el-table-column
      align='left'
        prop="modifyTime"
        label="修改日期"
         width="300">
      </el-table-column>
          <el-table-column
      label="操作"
      align='left'
      fixed="right"
      >
      <template slot-scope="scope">
        <el-button type="text" size="small" @click="editCol(scope.row)">编辑</el-button>
        <el-button type="text" size="small" @click="deleteRule(scope.row)">删除</el-button>
      </template>
    </el-table-column>
    </el-table>
   </el-row>
 </div>
</template>
<script>
import { mapState } from "vuex";
import RuleAddDialog from "@/components/warnRuleComp/RuleAddDialog.vue";
import { modifyRule, getAllRule,deleteRuleByid} from "@/api/getData";
import copy from "@/utils/copy"
export default {
  name: "RuleTable",
  data() {
    return {
      tableData: [],
      ruleDialogData: {
        title: "",
        data: {
          config:{}
        }
      }
    };
  },
  methods: {
    openDialog() {
      this.ruleDialogData.title = "新增监控规则";
      this.ruleDialogData.data = {};
      this.$store.commit("openRuleAddDialog");
    },
    editCol(row) {
      this.ruleDialogData.title = "修改监控规则";
      this.ruleDialogData.data = copy(row);
      this.$store.commit("openRuleAddDialog");
    },
    saveRule(target) {
      console.log(target);
      let index = this.tableData.findIndex(rule => rule.id == target.id);
      if (index != -1) {
         this.tableData[index].name = target.name;
         this.tableData[index].type = target.type;
         this.tableData[index].config = target.config;
      }else{
        this.tableData.push(target);
      }
    },
    async deleteRule(row){
      let params = {id : row.id};
      const response = await deleteRuleByid(params);
      if (response.success) {
        this.tableData.splice(this.tableData.findIndex(rule => rule.id == row.id), 1);
      } else {
        this.$message("删除监控规则失败");
      }
    },
    async initTableData() {
      const response = await getAllRule();
      if (response.success) {
        this.tableData = response.result;
      } else {
        this.$message("获取监控规则失败");
      }
    }
  },
  components: {
    RuleAddDialog: RuleAddDialog
  },
  created(){
    this.initTableData();
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
