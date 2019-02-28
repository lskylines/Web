package cn.itcast.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//2个属性,3个方法
		//总计，积分, 添加到购物车，移除购物车，清空购物车
	private double total = 0;	
	private Map<String, CarItem> map  = new HashMap<String, CarItem>();
		
	
	
	//添加到购物车
	public void addCartItem(CarItem  carItem) {
		String pid = carItem.getProduct().getPid();
		if(map.containsKey(pid)) {
			CarItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum()+ carItem.getNum());
		}else {
			map.put(pid, carItem);
		}
	}
	
	public double getTotal() {
		//获取map里面的所有购物项
		Collection<CarItem> values = map.values();
		
		for(CarItem carItem:values) {
			total += carItem.getSubTotal();
		}
		return total;
	}

	

	public Map<String, CarItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CarItem> map) {
		this.map = map;
	}

	//返回map中所有的值
	public Collection getCartItems() {
		return map.values();
	}
	
	//移除购物车
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	
	//清除购物车
	public void clearCartItem() {
		map.clear();
	}
}
