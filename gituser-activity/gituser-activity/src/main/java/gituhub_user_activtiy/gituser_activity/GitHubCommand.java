package gituhub_user_activtiy.gituser_activity;

import gituhub_user_activtiy.gituser_activity.GitHubService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GitHubCommand {
    private final GitHubService gitHubService;

    public GitHubCommand(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @ShellMethod(key = "github-activity", value = "Fetch recent GitHub activity of a user")
    public void fetchActivity(String username) {
        gitHubService.fetchUserActivity(username);
    }
}
