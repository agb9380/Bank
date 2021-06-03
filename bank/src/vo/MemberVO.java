package vo;

public class MemberVO {
	
	private String id;
	private String pwd;
	private String name;
	private String birth_date;
	private String enroll_date;
	private String recent_account_date;  // return값이 sysdate - recent_account_date
	
	public String getRecent_account_date() {
		return recent_account_date;
	}

	public void setRecent_account_date(String recent_account_date) {
		this.recent_account_date = recent_account_date;
	}

	public MemberVO() {
		
	}
	
	
	public MemberVO(String id, String pwd, String name, String birth_date) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth_date = birth_date;
	}
	
	public MemberVO(String id, String pwd, String name, String birth_date, String enroll_date, String recent_account_date) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth_date = birth_date;
		this.enroll_date = enroll_date;
		this.recent_account_date=recent_account_date;
	}
	
	public MemberVO(String recent_account_date) {
		this.recent_account_date=recent_account_date;
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getEnroll_date() {
		return enroll_date;
	}

	public void setEnroll_date(String enroll_date) {
		this.enroll_date = enroll_date;
	}

	
	
}
