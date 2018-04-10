import fetch from '@/utils/fetch'
export const getModelTypes = () => fetch('/config/modelType');
export const getModel = data => fetch('/config/model',data);

