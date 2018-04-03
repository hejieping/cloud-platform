import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/Layout'
import ProcessMonitor from '@/components/ProcessMonitor'
import CpuMonitor from '@/components/CpuMonitor'
import Warn from '@/components/Warn'
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
        path: '/Warn',
        component: Warn,
      }]
    }
  ]
})
