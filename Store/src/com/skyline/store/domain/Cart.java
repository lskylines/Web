package com.skyline.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private double total = 0;		// �ܼ�/����
	Map<String, CartItem> map = new HashMap<String, CartItem>();
	
	//�����Ʒ�����ﳵ
	public void addCartItemToCar(CartItem cartItem) {
		String pid = cartItem.getProduct().getPid();
		//�ж���Ʒ�Ƿ��Ѿ����빺�ﳵ��������룬��ԭ�ȵ��������ϱ��ε�����
		if(map.containsKey(pid)) {
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum() + cartItem.getNum());
		}else {
			map.put(pid, cartItem);
		}
	}
	
	//����map�е�����ֵ
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//�Ƴ�������
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	
	//��չ��ﳵ
	public void clearCart() {
		map.clear();
	}
	
	public double getTotal() {
		total = 0;
		//��ȡ��������ÿһ��
		Collection<CartItem> values = map.values();
		//����������
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
