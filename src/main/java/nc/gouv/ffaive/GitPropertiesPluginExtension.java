package nc.gouv.ffaive;

import java.io.File;

class GitPropertiesPluginExtension {

	File gitPropertiesDir;
	File gitRepositoryRoot;

	public GitPropertiesPluginExtension() {
		super();
	}

	public GitPropertiesPluginExtension(File gitPropertiesDir, File gitRepositoryRoot) {
		super();
		this.gitPropertiesDir = gitPropertiesDir;
		this.gitRepositoryRoot = gitRepositoryRoot;
	}

	public File getGitPropertiesDir() {
		return gitPropertiesDir;
	}

	public void setGitPropertiesDir(File gitPropertiesDir) {
		this.gitPropertiesDir = gitPropertiesDir;
	}

	public File getGitRepositoryRoot() {
		return gitRepositoryRoot;
	}

	public void setGitRepositoryRoot(File gitRepositoryRoot) {
		this.gitRepositoryRoot = gitRepositoryRoot;
	}
}
