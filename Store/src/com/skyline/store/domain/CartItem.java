package com.skyline.store.domain;

public class CartItem {
	private Product product; //Я��һЩ��Ʒ����(��Ʒ���ƣ���Ʒ�۸���Ʒ·��)
	private int num;		//��ǰ�����Ʒ����
	private double subTotal; //С��
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubTotal() {
		return product.getShop_price() * num;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
