package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.ContentService;
import com.taotao.portal.service.ItemService;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private ItemService itemService;
	
	@RequestMapping({"/index", "/"})
	public String showIndex(Model model, HttpServletRequest request) {
		String adJson = contentService.getContentList();
		List<TbItem> hotItems = itemService.getHotItems();
		model.addAttribute("ad1", adJson);
		model.addAttribute("user", (TbUser) request.getAttribute("user"));
		model.addAttribute("hotItems", hotItems);
		
		System.out.println("==============4444444444444444=================" + JsonUtils.objectToJson(hotItems));
		
		return "index";
	}
	
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST, 
			produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String testPost(String username, String password) {
		String result = "username:" + username + "\tpassword:" + password;
		System.out.println(result);
		return "username:" + username + ",password:" + password;
	}
}
