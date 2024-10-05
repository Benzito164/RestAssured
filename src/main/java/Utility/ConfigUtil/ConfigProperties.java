package Utility.ConfigUtil;
import static Utility.ConfigUtil.ConfigReader.GetConfigInformation;

public final class ConfigProperties {
	private ConfigProperties(){}

	public static final String BASE_URL = "BASE_URL";
	public static final String REGISTER_URL = "REGISTER_URL_PATH";
	public static final String USERS_URL = "GET_USERS_URL_PATH";
	public static final String JIRA_BASE_URL = GetConfigInformation("JIRA_URL");
	public static final String OAUTH_BASE_URL = GetConfigInformation("OAUTH_BASE_URL");
	public static final String JIRA_RESOURCE_PATH = GetConfigInformation("JIRA_ISSUE_PATH");
	public static final String JIRA_BASE_64_ENCODE = GetConfigInformation("BASE_64_ENCODING");
	public static final String OAUTH_CLIENT_ID = GetConfigInformation("OATH_CL_ID");
	public static final String OAUTH_CLIENT_SECRET = GetConfigInformation("OAUTH_CL_SECRET");
	public static final String OAUTH_TOKEN_ENDPOINT = GetConfigInformation("OAUTH_TOKEN_ENDPOINT");
	public static final String COURSES_ENDPOINT =GetConfigInformation("COURSES_END_POINT");
	public static final String ECOM_BASE_URL = GetConfigInformation("ECOM_BASE_URL");
	public static final String ECOM_USERNAME = GetConfigInformation("ECOM_USERNAME");
	public static final String ECOM_PASSWORD = GetConfigInformation("ECOM_PASSWORD");
	public static final String ECOM_LOGIN_PATH = GetConfigInformation("ECOM_PATH_URL");
	public static final String ECOM_CREATE_ORDER_PATH = GetConfigInformation("ECOM_CREATE_ORDER");
    public static final String ECOM_ADD_PRODUCT = GetConfigInformation("ECOM_ADD_PRODUCT");
}
