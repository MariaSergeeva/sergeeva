package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SoapTests extends TestBase {
  @Test
  public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
    int issueId = app.soap().getIssueId();
    skipIfNotFixed(issueId);
    Set<Project> projects = app.soap().getProjecrs();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    int issueId = app.soap().getIssueId();
    skipIfNotFixed(issueId);
    Set<Project> projects = app.soap().getProjecrs();
    Issue issue = new Issue().withSummury("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummury(), created.getSummury());
      }
}

