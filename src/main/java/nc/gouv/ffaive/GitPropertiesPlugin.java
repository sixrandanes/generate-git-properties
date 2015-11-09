package nc.gouv.ffaive;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.JavaPlugin;

public class GitPropertiesPlugin implements Plugin<Project> {

	public GitPropertiesPlugin() {
		super();
	}

	public void apply(Project project) {

		project.getExtensions().create("gitProperties", GitPropertiesPluginExtension.class);
		Task task = project.getTasks().create("generateGitProperties", GenerateGitPropertiesTask.class);

		task.setGroup(BasePlugin.BUILD_GROUP);
		ensureTaskRunsOnJavaClassesTask(project, task);
	}

	private static void ensureTaskRunsOnJavaClassesTask(Project project, Task task) {
		project.getTasks().getByName(JavaPlugin.CLASSES_TASK_NAME).dependsOn(task);
	}
}
