package com.mentoting_1;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.API_Calls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterviewTask {
    private String expectedCapital = "Kyiv";
    private String actualCapital="";

@Test
    public void findCountry(){
    RestAssured.baseURI="https://restcountries.com";
    RestAssured.basePath="v3.1/all";
    Response response = RestAssured.given().accept(ContentType.JSON)
            .when().get().then().statusCode(200).extract().response();

List<Map<String,Object>> parseResponse= response.as(new TypeRef<List<Map<String, Object>>>() {});
List<String>capital= new ArrayList<>();
    for (int i = 0; i < parseResponse.size(); i++) {
        Map<String,Object>outerMap=parseResponse.get(i);
        Map<String,Object>innerMap =( Map<String,Object>) outerMap.get("name");
        if(innerMap.get("common").equals("Ukraine")){
            System.out.println(outerMap.get("capital"));
            capital=(List<String>) outerMap.get("capital");
            actualCapital = capital.get(0);

            break;
        }

    }
    Assert.assertEquals(expectedCapital,actualCapital);

}
@Test
    public void validateBreakingBadQuotes(){
    RestAssured.baseURI="https://api.breakingbadquotes.xyz";
    RestAssured.basePath="v1/quotes";
    Response response= API_Calls.Get("/10");
    List<Map<String,Object>> parsed=response.as(new TypeRef<List<Map<String, Object>>>() {
    });
    for (int i = 0; i < parsed.size(); i++) {
        Map<String,Object>map=parsed.get(i);
        if(map.get("author").equals("Jesse Pinkman")){
            System.out.println(map.get("quote"));
            System.out.println(map.get("author"));
        }

    }
}
}
