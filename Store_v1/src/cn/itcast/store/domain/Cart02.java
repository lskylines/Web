package cn.itcast.store.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart02 {
	//2个属性,3个方法
	//总计，积分, 添加到购物车，移除购物车，清空购物车
	private List<CarItem> list = new ArrayList();
	
	//添加购物项到购物车
	public void addCartItemToCart(CarItem carItem) {
		//判断是否购买过这类商品，如果没有买过，直接加入购物车
		//如果买过将原来的数量 + 现在的数量
		//默认设置为false,表示没有买过
		boolean flag = false;
		//遍历集合
		CarItem old = null;
		for(CarItem carItem2:list) {
			if(carItem2.getProduct().getPid().equals(carItem.getProduct().getPid())) {
				flag = true;
				old = carItem2;
			}
		}
		if(flag==false) {
			list.add(carItem);
		}else {
			old.setNum(old.getNum() + carItem.getNum());
		}
		
		
	}
	
	//移除购物车
	public void removeCartItem(String pid) {
		//遍历List,查看每个CartItem中的id是否与服务端获取的pid相等,相等可以移除
		
	}
	
	//清空购物车
	public void clearCart() {
		
	}
}
