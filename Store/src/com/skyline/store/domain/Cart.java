package com.skyline.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private double total = 0;		// 总计/积分
	Map<String, CartItem> map = new HashMap<String, CartItem>();
	
	//添加商品到购物车
	public void addCartItemToCar(CartItem cartItem) {
		String pid = cartItem.getProduct().getPid();
		//判断商品是否已经加入购物车，如果加入，则原先的数量加上本次的数量
		if(map.containsKey(pid)) {
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum() + cartItem.getNum());
		}else {
			map.put(pid, cartItem);
		}
	}
	
	//返回map中的所有值
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//移除购物项
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	
	//清空购物车
	public void clearCart() {
		map.clear();
	}
	
	public double getTotal() {
		total = 0;
		//获取购物项中每一项
		Collection<CartItem> values = map.values();
		//遍历购物项
		for(CartItem cartItem:values) {
			total += cartItem.getSubTotal();
		}
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	
	
	
}
