package com.cpf;

import com.cpf.agentbase.manager.DO.MonitorDO;
import com.cpf.monitor.MonitorEngine;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private MonitorEngine monitorEngine;
	@Test
	public void test() {
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

}
