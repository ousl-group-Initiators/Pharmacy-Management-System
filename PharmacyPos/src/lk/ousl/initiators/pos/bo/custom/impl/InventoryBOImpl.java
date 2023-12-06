package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.InventoryBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.DrugsDAO;
import lk.ousl.initiators.pos.dao.custom.OrderDetailsDAO;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.OrderDTO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {

    private final DrugsDAO drugsDAO = (DrugsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);

    @Override
    public ArrayList<DrugsDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<DrugsDTO> allDrugs = new ArrayList<>();
        ArrayList<Drugs> all = drugsDAO.getAll();
        for (Drugs drugs : all) {
            allDrugs.add(new DrugsDTO(
                    drugs.getDrug_id(),
                    drugs.getDrug_name(),
                    drugs.getBatch_number(),
                    drugs.getDrug_quantity(),
                    drugs.getUnit_price(),
                    drugs.getUnit_discount()));
        }
        return allDrugs;
    }

    @Override
    public ArrayList<DrugsDTO> searchOrder(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Drugs> entityCustomerList = drugsDAO.searchDrugs(searchText);
        ArrayList<DrugsDTO> drugsDTOS = new ArrayList<>();

        for(Drugs drugs: entityCustomerList){
            DrugsDTO drugsDTO= new DrugsDTO(
                    drugs.getDrug_id(),
                    drugs.getDrug_name(),
                    drugs.getBatch_number(),
                    drugs.getDrug_quantity(),
                    drugs.getUnit_price(),
                    drugs.getUnit_discount()
            );
            drugsDTOS.add(drugsDTO);
        }
        return drugsDTOS;
    }
}
