package lk.ousl.initiators.pos.dao;


import lk.ousl.initiators.pos.dao.custom.impl.EmployeeDAOImpl;
import lk.ousl.initiators.pos.dao.custom.impl.SupplierDAOImpl;
import lk.ousl.initiators.pos.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        EMPLOYEE,SUPPLIER,DRUGS,LOGIN,ORDER,ORDERDETAILS,REGISTRATION
    }

    // factory method
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case DRUGS:
                return new DrugsDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            default:
                return null;
        }
    }
}
