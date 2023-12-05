package lk.ousl.initiators.pos.entity;

public class Registration {
    private String user_name;
    private String password;
    private String confirm_password;
    private String job_role;

    public Registration() {
    }

    public Registration(String user_name, String password, String confirm_password, String job_role) {
        this.user_name = user_name;
        this.password = password;
        this.confirm_password = confirm_password;
        this.job_role = job_role;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }
}
