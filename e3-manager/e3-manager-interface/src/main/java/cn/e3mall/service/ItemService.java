package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {
	
	TbItem geTbItemById(long itemId);
	EasyUIDataGridResult getItemList(int page,int rows);
	E3Result addItem(TbItem item,String desc);
	TbItemDesc geTbItemDescById(long itemId);
}
