package com.taotao.portal.service;

import java.util.List;

import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {

	ItemInfo getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
	List<TbItem> getHotItems();
	
}
