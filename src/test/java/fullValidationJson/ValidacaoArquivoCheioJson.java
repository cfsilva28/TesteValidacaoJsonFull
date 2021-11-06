package fullValidationJson;

import java.io.File;

import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidacaoArquivoCheioJson {

	@Test
	public void testeApiCorreiosValidaJson() throws Exception  {
		File file = new File("./src/test/java/fullValidationJson/apiCorreiosCep.json");
		
		Response response = 
				
				RestAssured.given()
						   .when()
						   		.get("http://viacep.com.br/ws/06415150/json/")
						   .then()
						   .contentType(ContentType.JSON)
						   .extract()
						   .response();

		String expectedJson = FileUtils.readFileToString(file, Charset.defaultCharset());
		
		String actualJson = response.body().asString();
		
		JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
	}

}