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
        Employee,Supplier,Drugs,LOGIN,ORDER
    }


    public SuperBO getBo(BoTypes types){
        switch (types){
            case Employee:
                return new EmployeeBOImpl(); //SuperBO superBO = new EmployeeBoImpl();
            case Supplier:
                return new SupplierBOImpl(); //SuperBO superBO = new SupplierBoImpl();
            case Drugs:
                 return new DrugsBOImpl(); //SuperBO superBO = new DrugsBoImpl();
            case LOGIN:
                return new LoginBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
