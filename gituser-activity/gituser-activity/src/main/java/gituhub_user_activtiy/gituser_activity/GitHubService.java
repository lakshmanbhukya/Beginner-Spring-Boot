package gituhub_user_activtiy.gituser_activity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class GitHubService {
    private static final String GITHUB_EVENTS_URL = "https://api.github.com/users/{username}/events";

    public void fetchUserActivity(String username) {
        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> events = restTemplate.getForObject(GITHUB_EVENTS_URL, List.class, username);

        if (events == null || events.isEmpty()) {
            System.out.println("No recent activity found for user: " + username);
            return;
        }

        System.out.println("Recent activity for " + username + ":");
        for (Map<String, Object> event : events) {
            String type = (String) event.get("type");
            Map<String, Object> repo = (Map<String, Object>) event.get("repo");
            String repoName = (repo != null) ? (String) repo.get("name") : "Unknown Repository";

            System.out.println("- " + formatEvent(type) + " in " + repoName);
        }
    }

    private String formatEvent(String eventType) {
        return switch (eventType) {
            case "PushEvent" -> "Pushed commits";
            case "IssuesEvent" -> "Opened an issue";
            case "WatchEvent" -> "Starred a repository";
            case "ForkEvent" -> "Forked a repository";
            default -> "Performed an action";
        };
    }
}
