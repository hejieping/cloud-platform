import fetch from '@/utils/fetch'





export const getAllRule = () => fetch('/config/rules');
export const deleteRuleByid = data => fetch('/config/deleteRule',data);
export const saveRule = data => fetch('/config/rule',data,'POST');

export const getModelTypes = () =>fetch('/config/modelType');
export const getAllModel = () => fetch('/config/models');
export const getModel = data => fetch('/config/model',data);
export const deleteModelByid = data => fetch('/config/deleteModel',data);
export const saveModel = data => fetch('/config/model',data,'POST');

