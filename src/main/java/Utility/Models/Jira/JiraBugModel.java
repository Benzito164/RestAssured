package Utility.Models.Jira;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;


@Setter
@Getter
@Builder
public class JiraBugModel {
	private HashMap<String,String> project;
	private String summary;
	private HashMap<String,String> issuetype;

}
