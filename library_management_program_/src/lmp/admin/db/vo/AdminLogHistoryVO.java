package lmp.admin.db.vo;

public class AdminLogHistoryVO {

	Integer log_id;
	AdminVO adminVO;
	String  loginTime;
	String  logoutTime;
	
	public AdminLogHistoryVO(Integer log_id, AdminVO adminVO, String loginTime, String logoutTime) {
		
		this.log_id = log_id;
		this.adminVO = adminVO;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		
	}

	public Integer getLog_id() {
		return log_id;
	}

	public AdminVO getAdminVO() {
		return adminVO;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}
	
	
	
}
