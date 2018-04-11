<template>
<el-table :data="addPlanRoute" border style="width:100%" @cell-dblclick="tableDbEdit">
       <el-table-column property="order1" label="顺序"></el-table-column>
       <el-table-column property="order2" label="装车点">
           <template slot-scope="scope">
               <el-input v-model="scope.row.order2" placeholder="请输入内容" @focus="print(scopre)" ></el-input>
           </template>
       </el-table-column>
</el-table> 
</template>

<script>
export default{
    data(){
        return{
            addPlanRoute:[{
                order1:"asd",
                order2:"asd"
            }]
        }
    },
    methods:{
    tableDbEdit(row, column, cell, event) {
          console.log(row, column, cell, event);
          if (column.label != "顺序") {
            event.target.innerHTML = "";
            let cellInput = document.createElement("input");
            cellInput.value = "";
            cellInput.setAttribute("type", "text");
            cellInput.style.width = "80%";
            cell.appendChild(cellInput);
            cellInput.onblur = function() {
              cell.removeChild(cellInput);
              event.target.innerHTML = cellInput.value;
            };
        }
     },
     print(scope){
         console.log(JSON.stringify(scope));
     }
    }
}
</script>