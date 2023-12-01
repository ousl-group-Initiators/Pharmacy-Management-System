package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.DrugsBO;
import lk.ousl.initiators.pos.bo.custom.EmployeeBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.DrugsDAO;
import lk.ousl.initiators.pos.dao.custom.EmployeeDAO;
import lk.ousl.initiators.pos.dto.DrugsDTO;
import lk.ousl.initiators.pos.dto.EmployeeDTO;
import lk.ousl.initiators.pos.entity.Drugs;
import lk.ousl.initiators.pos.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class DrugsBOImpl implements DrugsBO {

    private final DrugsDAO drugsDAO = (DrugsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DRUGS);

    @Override
    public boolean saveDrugs(DrugsDTO dto) throws SQLException, ClassNotFoundException {
        return drugsDAO.save(new Drugs(
                dto.getDrug_id(),
                dto.getDrug_name(),
                dto.getBatch_number(),
                dto.getMFD(),
                dto.getEXD(),
                dto.getDrug_quantity(),
                dto.getUnit_price(),
                dto.getUnit_discount(),
                dto.getSupply_id(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean updateDrugs(DrugsDTO dto) throws SQLException, ClassNotFoundException {
        return drugsDAO.update(new Drugs(
                dto.getDrug_id(),
                dto.getDrug_name(),
                dto.getBatch_number(),
                dto.getMFD(),
                dto.getEXD(),
                dto.getDrug_quantity(),
                dto.getUnit_price(),
                dto.getUnit_discount(),
                dto.getSupply_id(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean deleteDrugs(String id) throws SQLException, ClassNotFoundException {
        return drugsDAO.delete(id);
    }

    @Override
    public ArrayList<DrugsDTO> getAllDrugs() throws SQLException, ClassNotFoundException {
        ArrayList<DrugsDTO> allDrugs = new ArrayList<>();
        ArrayList<Drugs> all = drugsDAO.getAll();
        for (Drugs drugs : all) {
            allDrugs.add(new DrugsDTO(
                    drugs.getDrug_id(),
                    drugs.getDrug_name(),
                    drugs.getBatch_number(),
                    drugs.getMFD(),
                    drugs.getEXD(),
                    drugs.getDrug_quantity(),
                    drugs.getUnit_price(),
                    drugs.getUnit_discount(),
                    drugs.getSupply_id(),
                    drugs.getDescription()
            ));
        }
        return allDrugs;
    }

    @Override
    public boolean ifDrugsExist(String id) throws SQLException, ClassNotFoundException {
        return drugsDAO.ifDrugsExist(id);
    }

    @Override
    public ArrayList<DrugsDTO> searchDrugs(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Drugs> entityCustomerList = drugsDAO.searchDrugs(searchText);
        ArrayList<DrugsDTO> customerDTOList = new ArrayList<>();

        for(Drugs drugs: entityCustomerList){
            DrugsDTO customerDTO= new DrugsDTO(
                    drugs.getDrug_id(),
                    drugs.getDrug_name(),
                    drugs.getBatch_number(),
                    drugs.getMFD(),
                    drugs.getEXD(),
                    drugs.getDrug_quantity(),
                    drugs.getUnit_price(),
                    drugs.getUnit_discount(),
                    drugs.getSupply_id(),
                    drugs.getDescription()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
