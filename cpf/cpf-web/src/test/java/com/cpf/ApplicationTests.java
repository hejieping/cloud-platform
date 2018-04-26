package com.cpf;

import com.cpf.constants.RuleTypeEnum;
import com.cpf.influx.dao.CpuDAO;
import com.cpf.influx.manager.DO.MonitorDO;
import com.cpf.influx.manager.MonitorManager;
import com.cpf.monitor.MonitorEngine;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private MonitorEngine monitorEngine;
	@Autowired
	private MonitorManager monitorManager;
	@Autowired
	private CpuDAO cpuDAO;
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
		monitorManager.insertMonitor(monitorDO);
	}

}
