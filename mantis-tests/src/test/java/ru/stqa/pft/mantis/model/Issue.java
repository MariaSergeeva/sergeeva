package ru.stqa.pft.mantis.model;

public class Issue {
  private int id;
  private String summury;
  private String description;
  private Project project;

  public int getId() {
    return id;
  }

  public String getSummury() {
    return summury;
  }

  public String getDescription() {
    return description;
  }

  public Project getProject() {
    return project;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withSummury(String summury) {
    this.summury = summury;
    return this;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }
}
