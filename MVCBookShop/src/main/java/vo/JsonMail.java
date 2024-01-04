package vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonMail {

	@JsonProperty("attachment")
	private Map<String, String> attachment;
	
	@JsonProperty("recipient")
    private Map<String, String> recipient;
	
	@JsonProperty("text")
    private String text;
	
	@JsonProperty("title")
    private String title;
}
