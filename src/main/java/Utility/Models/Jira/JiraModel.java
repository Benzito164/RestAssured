package Utility.Models.Jira;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@Builder
public  class JiraModel {
	private BugFields fields;



	@Getter
	@Setter
	@Builder
    public static class BugFields {
		private HashMap<String,String> project;
		private String summary;
		private HashMap<String,String> issuetype;
	}
}
