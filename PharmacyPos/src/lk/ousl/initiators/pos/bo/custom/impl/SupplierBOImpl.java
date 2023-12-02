package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.SupplierBO;
import lk.ousl.initiators.pos.dao.CrudDAO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.SupplierDAO;
import lk.ousl.initiators.pos.dto.SupplierDTO;
import lk.ousl.initiators.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                dto.getSupplier_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getDate_of_birth(),
                dto.getAge(),
                dto.getTelephone_number(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getDescription()
        ));

    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(
                dto.getSupplier_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getDate_of_birth(),
                dto.getAge(),
                dto.getTelephone_number(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSuppliers = new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier supplier : all) {
            allSuppliers.add(new SupplierDTO(
                    supplier.getSupplier_id(),
                    supplier.getFirst_name(),
                    supplier.getLast_name(),
                    supplier.getDate_of_birth(),
                    supplier.getAge(),
                    supplier.getTelephone_number(),
                    supplier.getAddress(),
                    supplier.getEmail(),
                    supplier.getDescription()
            ));
        }
        return allSuppliers;
    }

    @Override
    public boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.ifSupplierExist(id);
    }

    @Override
    public ArrayList<SupplierDTO> searchSupplier(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> entityCustomerList = supplierDAO.searchSupplier(searchText);
        ArrayList<SupplierDTO> customerDTOList = new ArrayList<>();

        for(Supplier supplier: entityCustomerList){
            SupplierDTO customerDTO= new SupplierDTO(
                    supplier.getSupplier_id(),
                    supplier.getFirst_name(),
                    supplier.getLast_name(),
                    supplier.getDate_of_birth(),
                    supplier.getAge(),
                    supplier.getTelephone_number(),
                    supplier.getAddress(),
                    supplier.getEmail(),
                    supplier.getDescription()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
