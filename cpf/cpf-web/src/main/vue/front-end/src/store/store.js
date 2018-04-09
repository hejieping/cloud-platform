import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    RuleAddDialogState:false,
    AlgorithmAddDialogState:false
  },
  mutations:{
    openRuleAddDialog:state => state.RuleAddDialogState=true,
    closeRuleAddDialog:state => state.RuleAddDialogState=false,
    openAlgAddDialog:state => state.AlgorithmAddDialogState=true,
    closeAlgAddDialog:state => state.AlgorithmAddDialogState=false,
  }
})

