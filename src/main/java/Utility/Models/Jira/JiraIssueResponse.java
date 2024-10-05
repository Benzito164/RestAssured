package Utility.Models.Jira;

import lombok.Data;

@Data
public class JiraIssueResponse {
	private String id;
	private String key;
	private String self;
}
