package com.cpf;

import com.cpf.constants.RuleTypeEnum;
import com.cpf.influx.dao.CpuDAO;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.influx.manager.MonitorManager;
import com.cpf.monitor.MonitorEngine;
import com.cpf.task.TrainTask;
import com.cpf.utils.ModelUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private MonitorEngine monitorEngine;
	@Autowired
	private MonitorManager monitorManager;
	@Autowired
	private DefaultInfluxDBTemplate template;
	@Autowired
	private CpuDAO cpuDAO;
	@Autowired
	private TrainTask trainTask;
	@Test
	public void testMonitor() {
		MonitorDO monitorDO = new MonitorDO();
		monitorDO.setType("win_cpu");
		Map<String,String> data = Maps.newHashMap();
		data.put("Percent_DPC_Time","101");
		data.put("Percent_Idle_Time","101");
		data.put("Percent_Interrupt_Time","101");
		data.put("Percent_Privileged_Time","101");
		data.put("Percent_Processor_Time","101");
		data.put("Percent_User_Time","101");
		data.put("host","DESKTOP-HQ4VTVM");
		data.put("instance","0");
		data.put("objectname","Processor");
		monitorDO.setData(data);
		monitorEngine.monitor(monitorDO);
	}
	@Test
	public void testSelect(){
		cpuDAO.all();
	}
	@Test
	public void test2File() throws Exception {
//		Date date1 = new Date(1507653987000L);
//		Date date2 = new Date(1507740387000L);
//		List<MonitorDO> monitorList = monitorManager.queryDataByTime("win_cpu",null,date1,date2,30L).getResult();
//		Instances instances =  ModelUtil.monitorDOS2Instances(monitorList);
//		BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data.arff"));
//		writer.write(instances.toString());
//		writer.flush();
//		writer.close();
		//		NaiveBayes nb = new NaiveBayes();
//		nb.buildClassifier(instances);
//		Evaluation evaluation = new Evaluation(instances);
//		evaluation.crossValidateModel(nb,instances,10,new Random(1));
//		System.out.println(evaluation.toSummaryString());
	}



	@Test
	public void testInsert(){
		MonitorDO monitorDO = monitorManager.queryDataByTime(RuleTypeEnum.CPU.getType(),null,new Date(1507653987000L),new Date(1507740387000L),1L).getResult().get(0);
		monitorManager.addMonitor(monitorDO);
	}
	@Test
	public void moveData(){
		String sql = "select * from win_diskio ";
		int pageSize = 10000;
		int offset = 0;
		List<MonitorDO> list = Lists.newLinkedList();
		Query query = new Query(sql + " limit "+pageSize + " offset " + offset,"telegraf");
		Map<String,List<MonitorDO>> resultMap = monitorManager.parseQueryResult(template.query(query,TimeUnit.MILLISECONDS));
		for(List<MonitorDO> monitorDOList : resultMap.values()){
			list.addAll(monitorDOList);
		}
		InfluxDBProperties influxDBProperties = new InfluxDBProperties();
		influxDBProperties.setUrl("http://localhost:8086");
		influxDBProperties.setUsername("remote");
		influxDBProperties.setPassword("Qq123456");
		influxDBProperties.setRetentionPolicy("autogen");
		influxDBProperties.setDatabase("cpf");
		influxDBProperties.setWriteTimeout(120);
		influxDBProperties.setReadTimeout(120);
		DefaultInfluxDBTemplate template1 = new DefaultInfluxDBTemplate(new InfluxDBConnectionFactory(influxDBProperties));
		template1.getConnection();
		Random random = new Random();
		while (list.size() == pageSize){
			for(MonitorDO monitorDO : list){
				if(random.nextInt(10) <= 2){
					monitorDO.getData().put("danger","true");
				}else {
					monitorDO.getData().put("danger","false");

				}
			}
			List<Point> pointList = list.stream().map(monitorDO ->{return monitorManager.convert2Point(monitorDO);} ).collect(Collectors.toList());
			template1.write(pointList);
			System.out.println("insert "+offset);
			list = Lists.newLinkedList();
			offset+=pageSize;
			query = new Query(sql + " limit "+pageSize + " offset " + offset,"telegraf");
			resultMap = monitorManager.parseQueryResult(template.query(query,TimeUnit.MILLISECONDS));
			for(List<MonitorDO> monitorDOList : resultMap.values()){
				list.addAll(monitorDOList);
			}
		}
	}

	@Test
	public void trainTest(){
		File file = new File(ModelUtil.MODEL_PATH+144+ModelUtil.MODEL_SUFFIX);
		System.out.println(file.getAbsoluteFile());
	}

}
