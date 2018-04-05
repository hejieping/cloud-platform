import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    RuleAddDialogState:false
  },
  mutations:{
    openDialog:state => state.RuleAddDialogState=true,
    closeDialog:state => state.RuleAddDialogState=false,
  }
})

