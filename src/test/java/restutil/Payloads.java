package restutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.CreateUser;
import utilities.ExcelDataUtil;

import java.util.HashMap;

/**
 * The type Payloads.
 */
public class Payloads {

	/**
	 * The Datamap.
	 */
	public static HashMap<String, String> datamap = null;

	/**
	 * Create user string.
	 *
	 * @param testData the test data
	 * @return the string
	 */
	public static String createUser(String testData) {

		datamap = ExcelDataUtil.getTestDataWithTestCaseID("RestAPITestData", testData);

		CreateUser user = new CreateUser();
		user.setName(datamap.get("name"));
		user.setGender(datamap.get("gender"));
		user.setEmail(datamap.get("email"));
		user.setStatus(datamap.get("status"));

		return converJavaObjectToJsonString(user);

	}

	/**
	 * Conver java object to json string string.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String converJavaObjectToJsonString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

}
