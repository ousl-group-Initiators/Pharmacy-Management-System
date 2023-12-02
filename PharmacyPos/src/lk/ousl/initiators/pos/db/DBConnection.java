package lk.ousl.initiators.pos.db;

import lk.ousl.initiators.pos.dto.JobRoleDTO;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "19990819");
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
        jobRoleDTOS.add(new JobRoleDTO(3,"Pharmacy Technician"));
        jobRoleDTOS.add(new JobRoleDTO(3,"Pharmacy Assistant"));
    }

}
