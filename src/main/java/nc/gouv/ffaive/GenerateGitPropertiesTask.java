package nc.gouv.ffaive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class GenerateGitPropertiesTask extends DefaultTask {

	@TaskAction
	void generate() throws IOException {

		GitPropertiesPluginExtension extension = getProject().getExtensions()
				.findByType(GitPropertiesPluginExtension.class);

		File dir = extension.getGitRepositoryRoot() != null ? extension.getGitRepositoryRoot()
				: getProject().getRootProject().file(".");

		Git git = Git.open(dir);
		Repository repo = git.getRepository();
		File dire = extension.getGitPropertiesDir() != null ? extension.getGitPropertiesDir()
				: new File(getProject().getBuildDir(), "resources/main");
		File file = new File(dire, "git.properties");

		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		RevWalk walk = new RevWalk(repo);
		Ref head = repo.getRef("refs/heads/master");

		RevCommit commit = walk.parseCommit(head.getObjectId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("git.branch", repo.getBranch());
		map.put("git.commit.id", commit.getId().name());
		map.put("git.commit.id.abbrev", String.valueOf(commit.getCommitterIdent().hashCode()));
		map.put("git.commit.user.name", commit.getCommitterIdent().getName());
		map.put("git.commit.user.email", commit.getCommitterIdent().getEmailAddress());
		map.put("git.commit.message.short", commit.getShortMessage());
		map.put("git.commit.message.full", commit.getFullMessage());
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochSecond(commit.getCommitTime()),
				ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		map.put("git.commit.time", ldt.format(formatter));

		Properties props = new Properties();
		props.putAll(map);
		props.store(new FileWriter(file), "");
	}
}
