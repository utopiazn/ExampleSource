package logic;

import java.util.List;

public class ShopImpl implements Shop {

	private ItemCatalog itemCatalog;
	
	
	
	@Override
	public List<Item> getItemList() {
		// TODO Auto-generated method stub
		return this.itemCatalog.getItemList();
	}



	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

}
