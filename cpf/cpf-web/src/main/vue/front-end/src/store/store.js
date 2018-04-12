import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    RuleAddDialogState:false,
    AlgorithmAddDialogState:false,
    AggreModelAddDialogState:false
  },
  mutations:{
    openRuleAddDialog:state => state.RuleAddDialogState=true,
    closeRuleAddDialog:state => state.RuleAddDialogState=false,
    openAlgAddDialog:state => state.AlgorithmAddDialogState=true,
    closeAlgAddDialog:state => state.AlgorithmAddDialogState=false,
    openAMAddDialog:state => state.AggreModelAddDialogState=true,
    closeAMAddDialog:state => state.AggreModelAddDialogState=false,

  }
})

