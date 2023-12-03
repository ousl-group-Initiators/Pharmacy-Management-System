package lk.ousl.initiators.pos.bo.custom.impl;

import lk.ousl.initiators.pos.bo.custom.ItemBO;
import lk.ousl.initiators.pos.dao.DAOFactory;
import lk.ousl.initiators.pos.dao.custom.ItemDAO;
import lk.ousl.initiators.pos.dto.ItemDTO;
import lk.ousl.initiators.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                dto.getItem_id(),
                dto.getItem_name(),
                dto.getItem_code(),
                dto.getManufacture_date(),
                dto.getExpire_date(),
                dto.getItem_qty(),
                dto.getUnit_price(),
                dto.getUnit_discount(),
                dto.getSupplier_id(),
                dto.getItem_description()
        ));
    }
    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                dto.getItem_id(),
                dto.getItem_name(),
                dto.getItem_code(),
                dto.getManufacture_date(),
                dto.getExpire_date(),
                dto.getItem_qty(),
                dto.getUnit_price(),
                dto.getUnit_discount(),
                dto.getSupplier_id(),
                dto.getItem_description()
        ));
    }
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(
                    item.getItem_id(),
                    item.getItem_name(),
                    item.getItem_code(),
                    item.getManufacture_date(),
                    item.getExpire_date(),
                    item.getItem_qty(),
                    item.getUnit_price(),
                    item.getUnit_discount(),
                    item.getSupplier_id(),
                    item.getItem_description()
            ));
        }
        return allItems;
    }
    @Override
    public boolean ifItemExist(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(id);
    }
    @Override
    public ArrayList<ItemDTO> searchItem(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Item> entityCustomerList = itemDAO.searchItem(searchText);
        ArrayList<ItemDTO> customerDTOList = new ArrayList<>();

        for(Item item: entityCustomerList){
            ItemDTO customerDTO= new ItemDTO(
                    item.getItem_id(),
                    item.getItem_name(),
                    item.getItem_code(),
                    item.getManufacture_date(),
                    item.getExpire_date(),
                    item.getItem_qty(),
                    item.getUnit_price(),
                    item.getUnit_discount(),
                    item.getSupplier_id(),
                    item.getItem_description()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}