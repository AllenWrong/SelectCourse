package com.course.bean;

/**
 * 用户信息表<br>
 * 用于处理登录和注册
 * @author Thingcor
 *
 */
public class UserInfo extends TableHead{
	/***/
	private int userID;
	/***/
	private String userName;
	/***/
	private String userNum;
	/***/
	private String userPwd;
	/***/
	private String userRole;
	
	public UserInfo() {
	}

	public UserInfo(int userID, String userName, String userNum, String userPwd, String userRole) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userNum = userNum;
		this.userPwd = userPwd;
		this.userRole = userRole;
		this.setTableName("UserInfo");
		this.setCount(5);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserInfo [userID=" + userID + ", userName=" + userName + ", userNum=" + userNum + ", userPwd=" + userPwd
				+ ", userRole=" + userRole + "]";
	}
}
