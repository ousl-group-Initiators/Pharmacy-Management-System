package lk.ousl.initiators.pos.db;

import lk.ousl.initiators.pos.dto.JobRoleDTO;
import lk.ousl.initiators.pos.dto.SupplierDTO;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "1234");
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
    public static ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

    static {
        jobRoleDTOS.add(new JobRoleDTO(1,"Admin"));
        jobRoleDTOS.add(new JobRoleDTO(2,"Cashier"));
        jobRoleDTOS.add(new JobRoleDTO(3,"Pharmacist"));
        jobRoleDTOS.add(new JobRoleDTO(3,"Pharmacy Technician"));
        jobRoleDTOS.add(new JobRoleDTO(3,"Pharmacy Assistant"));
    }
    static {
        supplierDTOS.add(new SupplierDTO(1,"S1001"));
        supplierDTOS.add(new SupplierDTO(2,"S1002"));
        supplierDTOS.add(new SupplierDTO(3,"S1003"));
        supplierDTOS.add(new SupplierDTO(4,"S1004"));
        supplierDTOS.add(new SupplierDTO(5,"S1005"));
    }

}
