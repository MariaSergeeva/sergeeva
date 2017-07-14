package ru.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBaseRestAssured {
  @BeforeClass
  public void before(){
    initRA();
  }

  @Test
  public void testCreateIssue() throws IOException {
    int id = getIssueIdRA();
    skipIfNotFixedRA(id);
    Set<Issue> oldIssues = getIssuesRA();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssueRA(newIssue);
    Set<Issue> newIssues = getIssuesRA();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}
