package lk.ousl.initiators.pos.dto;

import javafx.scene.control.Button;

import java.util.Date;
import java.awt.*;
public class SupplierDTO {
        private String supplier_id;
        private String first_name;
        private String last_name;
        private Date date_of_birth;
        private int age;
        private int telephone_number;
        private String address;
        private String email;
        private String description;
        private javafx.scene.control.Button button;

        public SupplierDTO() {
        }

        public SupplierDTO(String supplier_id, String first_name, String last_name, Date date_of_birth, int age, int telephone_number, String address, String email, String description) {
            this.supplier_id = supplier_id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.date_of_birth = date_of_birth;
            this.age = age;
            this.telephone_number = telephone_number;
            this.address = address;
            this.email = email;
            this.description = description;
        }

        public SupplierDTO(String supplier_id, String first_name, String last_name, Date date_of_birth, int age, int telephone_number, String address, String email, String description, Button button) {
            this.supplier_id = supplier_id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.date_of_birth = date_of_birth;
            this.age = age;
            this.telephone_number = telephone_number;
            this.address = address;
            this.email = email;
            this.description = description;
            this.button = button;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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


