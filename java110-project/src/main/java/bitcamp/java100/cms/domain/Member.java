package bitcamp.java100.cms.domain;

  public class Member {
     
        protected String name;
        protected String email;
        protected String password;


        // 인스턴스의 메모리를 다루는 operator=setter/getter=accessor=property=message
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }


