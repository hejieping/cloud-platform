<template>
<div >
    <el-form :inline="true" :model="searchForm" class="search-form">
  <el-form-item label="学校">
    <el-select v-model="searchForm.selectedSchools" placeholder="七宝中学">
    <el-option
      v-for="school in schools"
      :key="school.value"
      :label="school.label"
      :value="school.value">
    </el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="区域">
    <el-select v-model="searchForm.selectedRegions" placeholder="区域">
        <el-option
        v-for="region in regions"
        :key="region.value"
        :label="region.label"
        :value="region.value">
        </el-option>
    </el-select>
  </el-form-item>
    <el-form-item label="设备类型">
    <el-select v-model="searchForm.selectedTypes" placeholder="设备类型">
        <el-option
        v-for="typ in types"
        :key="typ.value"
        :label="typ.label"
        :value="typ.value">
        </el-option>
    </el-select>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">查询</el-button>
  </el-form-item>
</el-form>
  </div>
</template>
<script>
export default {
  name: 'search',
    data() {
      return {
        searchForm: {
          user: '',
          region: '',
          selectedSchools:[],
          selectedRegions:[],
          selectedTypes:[]
        },
        schools:[{
            value:123,
            label:'七宝中学'
        },{
            value:131,
            label:'同济附属中学'
        },{
            value:5123,
            label:'复旦附属中学'
        }],
        regions:[{
            value:123,
            label:'103教室'
        },{
            value:131,
            label:'102教室'
        },{
            value:5123,
            label:'101教室'
        }],
        types:[{
            value:123,
            label:'电脑'
        },{
            value:131,
            label:'投影仪'
        },{
            value:5123,
            label:'打印机'
        }],
        selectdSchools:[]
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
}
</script>
