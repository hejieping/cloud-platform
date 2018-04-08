<template>
  <div>
      <el-form :model="formData"  ref="ruleForm" label-width="100px" align="left">
            <el-form-item v-for="option in config" :key="option.key" :label="option.desc" :prop="option.key" >
                <el-row  >
                    <el-col :span="8">
                        <AlgorithmOption :option="option"></AlgorithmOption>
                    </el-col>
                </el-row>
            </el-form-item>
      </el-form>
  </div>
</template>
<script>
import AlgorithmOption from '@/components/algorithmComp/AlgorithmOption.vue'
export default {
  name: 'AlgorithmEdit',
  data () {
    return {
        formData:{}
    }
  },
      props: {
        config:[]
  },
  components:{
      'AlgorithmOption':AlgorithmOption
  }
}
</script>
