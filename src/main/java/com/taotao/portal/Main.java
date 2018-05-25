package com.taotao.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * 【需求】
 * 1、产品搜索；
 * 2、广告查询；
 * 3、创建订单；
 * 4、购物车：展示购物车、添加购物车、删除购物车；
 * 5、商品展示：展示商品详情、展示商品描述、展示商品参数；
 *
 */
//http://172.17.203.121：8081/index
@SpringBootApplication
@DubboComponentScan(basePackages = "com.taotao.portal.service.impl")
public class Main {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Main.class, args);
	}	
}
