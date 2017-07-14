package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBaseRest {

  public Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  public Set<Issue> getIssuesRest() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssueRest(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public int getIssueIdRest() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
    JsonElement testIssue = issues.getAsJsonArray().get(0).getAsJsonObject();
    return Integer.parseInt(testIssue.getAsJsonObject().get("id").getAsString());
  }

  public boolean isIssueOpenRest(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId))).returnContent().asString();
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

  public void skipIfNotFixedRest(int issueId) throws IOException {
    if (isIssueOpenRest(issueId) == true) {
      throw new SkipException("Ignored because of issue " + issueId);
    } else {
      System.out.println("тест запущен");
    }
  }
}
