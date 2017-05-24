package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<ItemSet> itemList = new ArrayList<ItemSet>();

	public List<ItemSet> getItemList() {
		return this.itemList;
	}
	
	
	public boolean isEmpty() {
		if (this.itemList == null || this.itemList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	public void push(ItemSet pushedItemSet){
		
	}
	
	public void clearAll() {
		this.itemList = new ArrayList<ItemSet>();
	}
}
