package dhruvanshtanwar.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getDataToJson() throws JsonMappingException, JsonProcessingException, IOException {

		// read data from Json File
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "src\\main\\java\\dhruvanshtanwar\\data\\PurchaseOrder.json"), 
				StandardCharsets.UTF_8 );
		
		//String to HashMap using Jackson DataBind
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = 
		objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}

}
