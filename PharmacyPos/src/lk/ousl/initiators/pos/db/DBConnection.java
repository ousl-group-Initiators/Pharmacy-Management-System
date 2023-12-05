package lk.ousl.initiators.pos.db;

import lk.ousl.initiators.pos.dto.JobRoleDTO;
import lk.ousl.initiators.pos.dto.PaymentMethodDTO;
import lk.ousl.initiators.pos.dto.SupplyIdDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "Shihan@1998");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static DBConnection getDbConnection(){
        return (dbConnection == null)? dbConnection = new DBConnection(): dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }


    public static ArrayList<JobRoleDTO> jobRoleDTOS = new ArrayList<>();

    static {
        jobRoleDTOS.add(new JobRoleDTO(1,"Admin"));
        jobRoleDTOS.add(new JobRoleDTO(2,"Cashier"));
        jobRoleDTOS.add(new JobRoleDTO(3,"Pharmacist"));
        jobRoleDTOS.add(new JobRoleDTO(4,"Pharmacy Technician"));
        jobRoleDTOS.add(new JobRoleDTO(5,"Pharmacy Assistant"));
    }


    public static ArrayList<SupplyIdDTO> supplyIdDTOS = new ArrayList<>();

    static {
        supplyIdDTOS.add(new SupplyIdDTO(1,"S001"));
        supplyIdDTOS.add(new SupplyIdDTO(2,"S002"));
        supplyIdDTOS.add(new SupplyIdDTO(3,"S003"));
        supplyIdDTOS.add(new SupplyIdDTO(4,"S004"));
        supplyIdDTOS.add(new SupplyIdDTO(5,"S005"));
    }

    public static ArrayList<PaymentMethodDTO> paymentMethodDTOS = new ArrayList<>();

    static {
        paymentMethodDTOS.add(new PaymentMethodDTO(1,"Cash"));
        paymentMethodDTOS.add(new PaymentMethodDTO(2,"Credit Card"));
        paymentMethodDTOS.add(new PaymentMethodDTO(3,"Debit Card"));
    }

    public static ArrayList<JobRoleDTO> registrationDTOS = new ArrayList<>();

    static {
        registrationDTOS.add(new JobRoleDTO(1,"Admin"));
        registrationDTOS.add(new JobRoleDTO(2,"Cashier"));
    }
}
