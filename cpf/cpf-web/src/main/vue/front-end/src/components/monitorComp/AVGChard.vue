<template>
    <el-card class="box-card" :body-style="{ padding: '0px'}" >
        <div slot="header" class="clearfix">
            <span>{{content.type}}</span>
        </div>
        <ul id="example-1">
            <li v-for="(val, key) in content.data" :key="key">
                {{key + ":" + val }}
            </li>
        </ul>
    </el-card>
</template>
<script>
export default {
  data() {
    return {
    };
  },
  props: {
    content: {}
  },
  created(){
  }
};
</script>
<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>