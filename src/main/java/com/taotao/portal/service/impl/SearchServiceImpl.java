package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 商品搜索Service
 * <p>Title: SearchServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月12日上午9:11:58
 * @version 1.0
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Reference
	com.taotao.search.service.SearchService searchService;
	
	@Override
	public SearchResult search(String queryString, int page) {
		
		SearchResult result = null;
		try {
			result = searchService.search("book", 1, 60);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("======================99999999===================" + result.getItemList().size());
		
		return result;
		
		// 调用taotao-search的服务
//		//查询参数
//		Map<String, String> param = new HashMap<>();
//		param.put("q", queryString);
//		param.put("page", page + "");
//		try {
//			//调用服务
//			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
//			//把字符串转换成java对象
//			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
//			if (taotaoResult.getStatus() == 200) {
//				SearchResult result = (SearchResult) taotaoResult.getData();
//				return result;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

}
