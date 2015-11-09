import static org.junit.Assert.assertTrue;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import nc.gouv.ffaive.GenerateGitPropertiesTask;

public class PuginTest {

	@Test
	public void greeterPluginAddsGreetingTaskToProject() {
		Project project = ProjectBuilder.builder().build();
		project.getPluginManager().apply("org.samples.greeting");

		assertTrue(project.getTasks().getByName("GenerateGitProperties") instanceof GenerateGitPropertiesTask);
	}

}
