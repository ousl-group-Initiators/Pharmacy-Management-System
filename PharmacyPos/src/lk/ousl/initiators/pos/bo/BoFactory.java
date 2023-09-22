package lk.ousl.initiators.pos.bo;

import lk.ousl.initiators.pos.bo.custom.impl.EmployeeBOImpl;

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
                return new EmployeeBOImpl();
            default:
                return null;
        }
    }
}
