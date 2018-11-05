package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.util.E3Result;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCatList(long parentId);
	E3Result addContentCatgory(long parentId,String name);
}
