import fetch from '@/utils/fetch'
export const getModelTypes = () =>fetch('/config/modelType');
export const getAllModel = () => fetch('/config/models');
export const getModel = data => fetch('/config/model',data);
export const deleteModelByid = data => fetch('/config/deleteModel',data);
export const saveModel = data => fetch('/config/model',data,'POST');

