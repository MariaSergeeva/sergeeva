package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by sergeevam on 14.07.2017.
 */
public class GithubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("a1cf767360b43c69455b7fafe587ab192fbebf6c");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("MariaSergeeva", "sergeeva")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}