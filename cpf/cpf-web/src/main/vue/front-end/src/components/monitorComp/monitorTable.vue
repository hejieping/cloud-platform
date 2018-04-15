 <template>
 <div>
   <el-row type="flex">
             <el-breadcrumb separator="/"> 
       <el-breadcrumb-item>
        云平台
       </el-breadcrumb-item> 
       <el-breadcrumb-item>
        监控
       </el-breadcrumb-item> 
       <el-breadcrumb-item>
        设备监控
       </el-breadcrumb-item> 
      </el-breadcrumb> 
   </el-row>
   <el-row type="flex">
    <search></search>
   </el-row>
   <el-row>
            <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        align='left'
        prop="id"
        label="ID"
        width="150">
      </el-table-column>
      <el-table-column
      align='left'
        prop="uniCode"
        label="设备编码"
        width="120">
      </el-table-column>
      <el-table-column
      align='left'
        prop="cpu"
        label="cpu利用率"
         width="300">
      </el-table-column>
      <el-table-column
      label="操作"
      align='left'
      fixed="right"
      >
      <template slot-scope="scope">
        <el-button type="text" size="small" @click="detail(scope.row)">详情</el-button>
      </template>
    </el-table-column>
    </el-table>
   </el-row>
 </div>
</template>
<script>
import { getPerformance } from "@/api/getData";
import search from "@/components/monitorComp/search.vue";

export default {
  name: "monitorTable",
  data() {
    return {
      tableData: []
    };
  },
  methods: {
    async initTableData() {
      const response = await getPerformance();
      if (response.success) {
        this.tableData = response.result;
      } else {
        this.$message("获取监控数据失败");
      }
    },
    detail(row) {
      this.$router.push("/Monitor/" + row.id);
    }
  },
  created() {
    this.initTableData();
  },
  components: {
    search: search
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
</style>
