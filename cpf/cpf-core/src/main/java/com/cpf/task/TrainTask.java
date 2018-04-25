package com.cpf.task;

import com.alibaba.fastjson.JSON;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.influx.manager.MonitorManager;
import com.cpf.logger.BusinessLogger;
import com.cpf.ml.ModelHolder;
import com.cpf.mysql.manager.AggreModelManager;
import com.cpf.mysql.manager.DO.AggreModelDO;
import com.cpf.mysql.manager.DO.ModelDO;
import com.cpf.service.CallbackResult;
import com.cpf.service.ServiceExecuteTemplate;
import com.cpf.service.ServiceTemplate;
import com.cpf.utils.ModelUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jieping
 * @create 2018-04-25 19:56
 * @desc 定时训练任务
 **/
@Component
public class TrainTask extends ServiceTemplate  {
    private Logger logger = LoggerFactory.getLogger(TrainTask.class);
    /**
     * 定时训练间隔时间
     */
    private static Integer TRAIN_INTERVAL = 60*1000;
    /**
     * 错误样本占总训练样本的比例
     */
    private static final Double  DANGER_PROPORITION = 0.5D;

    @Autowired
    private AggreModelManager aggreModelManager;
    @Autowired
    private MonitorManager monitorManager;
    @Autowired
    private ModelHolder modelHolder;

    /**
     * 定时训练所有配置的算法，并保存算法模型
     * @return
     */
   public CallbackResult<Object> train(){
        Object result = execute(logger, "train", new ServiceExecuteTemplate() {
            @Override
            public CallbackResult<Object> checkParams() {
                return CallbackResult.success();
            }

            @Override
            public CallbackResult<Object> executeAction() {
                List<AggreModelDO> aggreModelDOList = aggreModelManager.all().getResult();
                Map<String,String> tagMap = Maps.newHashMap();
                for(AggreModelDO aggreModelDO : aggreModelDOList){
                    //查询有故障的监控数据
                    tagMap.put("danger","true");
                    List<MonitorDO> dangerMonitorDOList = monitorManager.queryDataByTime(aggreModelDO.getScene(),tagMap,null,null,null).getResult();
                    if(CollectionUtils.isEmpty(dangerMonitorDOList)){
                        BusinessLogger.errorLog("TrainTask.train",new String[]{JSON.toJSONString(aggreModelDO)},"HAVE_NOT_ERROR_SAMPLE","没有错误样本可以训练",logger);
                    }
                    //查询正常的监控数据
                    tagMap.put("danger","false");
                    Long limit = Math.round(dangerMonitorDOList.size()/DANGER_PROPORITION);
                    List<MonitorDO> safeMonitorDOList = monitorManager.queryDataByTime(aggreModelDO.getScene(),tagMap,null,null,null).getResult();
                    List<MonitorDO> trainMOnitorDOList = Lists.newArrayList();
                    trainMOnitorDOList.addAll(dangerMonitorDOList);
                    trainMOnitorDOList.addAll(safeMonitorDOList);
                    for(ModelDO modelDO : aggreModelDO.getModels()){
                        ModelUtil.train(modelDO,trainMOnitorDOList);
                    }
                }
                //算法模型更新
                modelHolder.refresh();
                return CallbackResult.success();
            }
        });
        return (CallbackResult<Object>)result;
    }
    public static void main(String[] args){
       Double a = 0.6D;
       Long b = Math.round(5/a);
       System.out.println(b);
    }
}
