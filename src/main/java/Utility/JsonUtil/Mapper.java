package Utility.JsonUtil;

import Utility.Models.RegisterModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.File;

@Slf4j
public class Mapper {

  private static  ObjectMapper mapper;


	@SneakyThrows
	public static <T>String ConvertClassToJson(T t){
		log.info(String.format("parameter details passed [%s]",t));
		return initMapper().writeValueAsString(t);
	}

	@SneakyThrows
	public static <T>Object ConvertJsonToClassObject(String fileName,Object object){
		val filepath = String.format("src/test/java/Utility/JsonUtil/StaticJsonData/%s.json", fileName);
		return initMapper().readValue(new File(filepath), RegisterModel.class);
	}


	private static  ObjectMapper initMapper(){
		if(mapper == null) {
			mapper = new ObjectMapper();
			log.info("Mapper Object was null-new mapper successfully initialized");
		}
		else {
			log.info("Mapper Object already exists - using existing instance");
		}
		return mapper;
	}

}
