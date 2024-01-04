package vo;

public class FaqKeyword {

	private int fk_code;
	private int fk_fc_code;
	private String fk_value;
	
	private String fc_value;
	
	public FaqKeyword() {
		super();
	}
	
	
	
	public FaqKeyword(String fc_value, int fk_fc_code) {
		super();
		this.fk_fc_code = fk_fc_code;
		this.fc_value = fc_value;
	}

	public FaqKeyword(int fk_code, String fk_value) {
		super();
		this.fk_code = fk_code;
		this.fk_value = fk_value;
	}
	
	public FaqKeyword(int fk_fc_code, String fc_value, int fk_code, String fk_value) {
		super();
		this.fk_code = fk_code;
		this.fk_fc_code = fk_fc_code;
		this.fk_value = fk_value;
		this.fc_value = fc_value;
	}

	public int getFk_code() {
		return fk_code;
	}
	public void setFk_code(int fk_code) {
		this.fk_code = fk_code;
	}
	public int getFk_fc_code() {
		return fk_fc_code;
	}
	public void setFk_fc_code(int fk_fc_code) {
		this.fk_fc_code = fk_fc_code;
	}
	public String getFk_value() {
		return fk_value;
	}
	public void setFk_value(String fk_value) {
		this.fk_value = fk_value;
	}
	public String getFc_value() {
		return fc_value;
	}
	public void setFc_value(String fc_value) {
		this.fc_value = fc_value;
	}

	@Override
	public String toString() {
		return "FaqKeyword [fk_code=" + fk_code + ", fk_fc_code=" + fk_fc_code + ", fk_value=" + fk_value
				+ ", fc_value=" + fc_value + "]";
	}
	
}
