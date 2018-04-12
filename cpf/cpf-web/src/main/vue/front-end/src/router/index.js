import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/Layout'
import ProcessMonitor from '@/components/ProcessMonitor'
import CpuMonitor from '@/components/CpuMonitor'
import SmartWarn from '@/components/SmartWarn'
import WarnRule from '@/components/WarnRule'
import AlgorithmConfig from '@/components/AlgorithmConfig'
import SolutionConfig from '@/components/SolutionConfig'
import AlgorithmTable from '@/components/algorithmComp/AlgorithmTable'
import AlgorithmDetail from '@/components/algorithmComp/AlgorithmDetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout,
      children:[{
        path: '/ProcessMonitor',
        component: ProcessMonitor,
      },{
        path: '/CpuMonitor',
        component: CpuMonitor,
      },{
        path: '/SmartWarn',
        component: SmartWarn,
      },{
        path: '/WarnRule',
        component: WarnRule,
      },{
        path: '/AlgorithmConfig',
        component: AlgorithmConfig,
        children: [
          {
            path: '',
            component: AlgorithmTable
          },
          {
            path: 'detail',
            component: AlgorithmDetail
          },]
      },{
        path: '/SolutionConfig',
        component: SolutionConfig,
      }]
    }
  ]
})
