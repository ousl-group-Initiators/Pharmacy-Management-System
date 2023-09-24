package lk.ousl.initiators.pos.dto;

public class JobRoleDTO {
    private int id;
    private String jobRole;

    public JobRoleDTO() {
    }

    public JobRoleDTO(int id, String jobRole) {
        this.id = id;
        this.jobRole = jobRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
}
