<template>
  <div>
  
    <el-card class="box-card" :body-style="{ padding: '0px'}">
  
      <schart :canvasId="canvasId" :type="type" :data="data" :options="options"></schart>
  
      <div style="">
  
        <div class="bottom clearfix">
  
          <el-button type="text" class="button">
  
            操作按钮
  
          </el-button>
  
        </div>
  
      </div>
  
    </el-card>
  
  
  
  </div>
</template>
<script>
import Schart from "vue-schart";

export default {
  data() {
    return {
      type: "line",

      options: {
        padding: 40, // canvas 内边距

        bgColor: "#FFFFFF", // 默认背景颜色

        title: "vue-schart互动演示案例", // 图表标题

        titleColor: "#000000", // 图表标题颜色

        titlePosition: "top", // 图表标题位置: top / bottom

        yEqual: 5, // y轴分成5等分

        fillColor: "#1E9FFF", // 默认填充颜色

        contentColor: "#eeeeee", // 内容横线颜色

        axisColor: "#666666" // 坐标轴颜色
      }
    };
  },

  props: {
    data: [],

    canvasId: ""
  },

  components: {
    Schart
  }
};
</script>