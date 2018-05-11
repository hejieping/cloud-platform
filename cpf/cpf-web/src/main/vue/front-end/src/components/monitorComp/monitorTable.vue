 <template>
 <div>
   <el-row type="flex">
             <el-breadcrumb separator="/"> 
       <el-breadcrumb-item>云平台</el-breadcrumb-item> 
       <el-breadcrumb-item>监控</el-breadcrumb-item> 
       <el-breadcrumb-item>设备监控</el-breadcrumb-item> 
      </el-breadcrumb> 
   </el-row>
   <el-row >
     <el-col :span="5">
       <el-input v-model="assetId" placeholder="请输入IP地址">
                 <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
       </el-input>
     </el-col>
   </el-row>
   <el-row>
            <el-table
       :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
      style="width: 100%">
      <el-table-column
        align='left'
        prop="id"
        label="id"
        width="350">
      </el-table-column>
      <el-table-column
      align='left'
        prop="aid"
        label="aid"
        width="350">
      </el-table-column>
            <el-table-column
      align='left'
        prop="ipaddr"
        label="ip"
        width="120">
      </el-table-column>
      <el-table-column
      align='left'
        prop="hostname"
        label="hostname"
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
   <el-row>
         <el-pagination  
         background
    layout="total,prev, pager, next,sizes"
    :page-size="pagesize"
    :page-sizes="[10, 20, 50, 100]"
    :current-page="currentPage"
    @current-change="handleCurrentChange"
    @size-change="handleSizeChange"
    :total="tableData.length">
  </el-pagination>
   </el-row>
 </div>
</template>
<script>
import { getAssets } from "@/api/getData";
import search from "@/components/monitorComp/search.vue";

export default {
  name: "monitorTable",
  data() {
    return {
      tableData: [],
      currentPage:1,
      pagesize:10,
      assetId:'',
      allData:[]
    };
  },
  methods: {
    async initTableData() {
      const response = await getAssets();
      if (response.success) {
        this.allData = response.result;
        this.tableData =  this.allData;
      } else {
        this.$message("获取监控数据失败");
      }
    },
    detail(row) {
      this.$router.push("/Monitor/" + row.hostname);
    },
    handleSizeChange(size){
       this.pagesize = size;
    },
    handleCurrentChange(currentPage){
       this.currentPage = currentPage;
    },
    search(){
      if(this.assetId==''){
         this.tableData = this.allData;
         return;
      }
      this.tableData = [];
      this.allData.forEach((data)=>{
        if(data.ipaddr == this.assetId){
          this.tableData.push(data);
        }
      })
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
<style scoped>
.el-row {
  margin-bottom: 20px;
}
</style>
