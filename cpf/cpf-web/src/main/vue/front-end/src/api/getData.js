import fetch from '@/utils/fetch'





export const getAllRule = () => fetch('/config/rules');
export const deleteRuleByid = data => fetch('/config/rule',data,'DELETE');
export const saveRule = data => fetch('/config/rule',data,'POST');

export const getModelTypes = () =>fetch('/config/modelType');
export const getAllModel = () => fetch('/config/models');
export const getModel = data => fetch('/config/model',data);
export const deleteModelByid = data => fetch('/config/model',data,'DELETE');
export const saveModel = data => fetch('/config/model',data,'POST');

