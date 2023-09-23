package lk.ousl.initiators.pos.dto;

import java.awt.*;
import java.util.Date;

public class EmployeeDTO {
    private String emp_id;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    private int age;
    private int telephone_number;
    private String address;
    private String job_role;
    private String description;
    private Button button;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String emp_id, String first_name, String last_name, Date date_of_birth, int age, int telephone_number, String address, String job_role, String description) {
        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.age = age;
        this.telephone_number = telephone_number;
        this.address = address;
        this.job_role = job_role;
        this.description = description;
    }

    public EmployeeDTO(String emp_id, String first_name, String last_name, Date date_of_birth, int age, int telephone_number, String address, String job_role, String description, Button button) {
        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.age = age;
        this.telephone_number = telephone_number;
        this.address = address;
        this.job_role = job_role;
        this.description = description;
        this.button = button;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(int telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
