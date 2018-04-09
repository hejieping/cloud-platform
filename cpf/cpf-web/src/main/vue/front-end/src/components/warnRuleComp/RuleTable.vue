 <template>
 <div>
   <RuleAddDialog ></RuleAddDialog>
   <el-row type="flex">
     <el-button-group >
  <el-button type="primary" icon="el-icon-plus" @click="openDialog">新增</el-button>
   <el-button type="primary" icon="el-icon-minus">批量删除</el-button>
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
        prop="date"
        label="日期"
        width="150">
      </el-table-column>
      <el-table-column
      align='left'
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
      align='left'
        prop="address"
        label="地址"
         width="300">
      </el-table-column>
          <el-table-column
      label="操作"
      align='left'
      >
      <template slot-scope="scope">
        <el-button type="text" size="small">编辑</el-button>
        <el-button type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
    </el-table>
   </el-row>
 </div>
</template>
<script>
import { mapState } from 'vuex'
import RuleAddDialog from '@/components/warnRuleComp/RuleAddDialog.vue'
export default {
  name: "RuleTable",
  data() {
    return {
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ],
    };
  },
  methods:{
        openDialog(){
          this.$store.commit('openRuleAddDialog')
        }
    },
  components:{
    'RuleAddDialog':RuleAddDialog
  }
};
</script>