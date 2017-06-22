package ru.apr.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;;
import ru.apr.service.Entity.Reference;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsitorApplicationTests {

	@Autowired
	ApplicationContext context;

	@Test
	public void applicationRunsTest() {
		String mainResponse = context.getBean(Reference.class).getReferenceManual();

		try {
			JSONObject responseObject = new JSONObject(mainResponse);

			Assert.assertNotNull(responseObject);
			Assert.assertEquals(responseObject.getString("info"), "reference");

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
