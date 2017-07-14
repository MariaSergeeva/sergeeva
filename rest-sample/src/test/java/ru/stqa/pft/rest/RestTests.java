package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBaseRest{
  @Test
  public void testCreateIssue() throws IOException {
    int id = getIssueIdRest();
    skipIfNotFixedRest(id);
    Set<Issue> oldIssues = getIssuesRest();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssueRest(newIssue);
    Set<Issue> newIssues = getIssuesRest();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}
