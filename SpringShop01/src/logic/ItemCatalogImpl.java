package logic;

import java.util.List;

import dao.ItemDao;

public class ItemCatalogImpl implements ItemCatalog {

	private ItemDao itemDao;
	
	@Override
	public List<Item> getItemList() {
		// TODO Auto-generated method stub
		return this.itemDao.findAll();
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	

}
