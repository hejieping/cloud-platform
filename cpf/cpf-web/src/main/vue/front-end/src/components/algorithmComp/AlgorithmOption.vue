<template>
    <div>
        <div v-if="option.valueType == 'bool'">
                <el-switch v-model="option.value"></el-switch>
         </div>
         <div v-else-if="option.valueType === 'enum'">
            <el-select v-model="option.value" placeholder="请选择">
                <el-option
                v-for="key in option.extension"
                :key="key"
                :label="key"
                :value="key">
                </el-option>
            </el-select>
         </div>
         <div v-else>
            <el-input v-model="option.value" placeholder="请输入内容" size="small"></el-input>
         </div>
    </div>
</template>
<script>
export default {
  name: 'AlgorithmOption',
  data () {
    return {
    }
  },
    props: {
        option:{
                key:"-p",
                desc: "使用核函数",
                value: false,
                valueType: "bool"
              }
  },
}
</script>
