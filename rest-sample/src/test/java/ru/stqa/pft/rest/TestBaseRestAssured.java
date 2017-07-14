package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;


public class TestBaseRestAssured {


  public Set<Issue> getIssuesRA() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssueRA(Issue newIssue) throws IOException {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public int getIssueIdRA() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
    JsonElement testIssue = issues.getAsJsonArray().get(0).getAsJsonObject();
    return Integer.parseInt(testIssue.getAsJsonObject().get("id").getAsString());
  }

  public boolean isIssueOpenRA(int issueId) throws IOException {
    String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
    JsonElement testIssue = issues.getAsJsonArray().get(0).getAsJsonObject();
    String issueStatus = testIssue.getAsJsonObject().get("state_name").getAsString();
    if (issueStatus.equals("Resolved") || issueStatus.equals("Closed") || issueStatus.equals("Deleted")) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixedRA(int issueId) throws IOException {
    if (isIssueOpenRA(issueId) == true) {
      throw new SkipException("Ignored because of issue " + issueId);
    } else {
      System.out.println("тест запущен");
    }
  }
  public void initRA(){
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }
}
