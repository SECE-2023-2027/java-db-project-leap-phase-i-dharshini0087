package UserAcc;
public class Admin {
        public int id;
        public String userName;
        public String password;
		public Admin(int id, String userName, String password) {
			super();
			this.id = id;
			this.userName = userName;
			this.password = password;
		}
		public Admin(){};
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}


public int checkLogin(String username,String password) {
	AdminDBClass adminDBCalls=new AdminDBClass();
	return adminDBCalls.checkLogin(username,password);
}
}