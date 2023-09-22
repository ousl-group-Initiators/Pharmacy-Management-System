package lk.ousl.initiators.pos.dao;


import lk.ousl.initiators.pos.dao.custom.impl.EmployeeDAOImpl;

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
        EMPLOYEE
    }

    // factory method
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            default:
                return null;
        }
    }
}
