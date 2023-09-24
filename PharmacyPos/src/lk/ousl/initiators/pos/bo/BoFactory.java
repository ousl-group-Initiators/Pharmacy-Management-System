package lk.ousl.initiators.pos.bo;

import lk.ousl.initiators.pos.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory(){
        if (boFactory == null){
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public enum BoTypes{
        Employee
    }

    public SuperBO getBo(BoTypes types){
        switch (types){
            case Employee:
                return new EmployeeBOImpl(); //SuperBO superBO = new EmployeeBoImpl();
            default:
                return null;
        }
    }
}
