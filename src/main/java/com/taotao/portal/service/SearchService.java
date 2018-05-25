package com.taotao.portal.service;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
