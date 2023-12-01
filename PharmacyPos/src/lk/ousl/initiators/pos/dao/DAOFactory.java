package lk.ousl.initiators.pos.dao;


import lk.ousl.initiators.pos.dao.custom.impl.DrugsDAOImpl;
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
        EMPLOYEE,DRUGS
    }

    // factory method
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case DRUGS:
                return new DrugsDAOImpl();
            default:
                return null;
        }
    }
}
