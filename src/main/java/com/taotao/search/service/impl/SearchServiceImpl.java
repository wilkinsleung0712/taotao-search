package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * @Date 10:13:08 am 31 Jan 2017
 * @author wilkins.liang
 *
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    private static String ITEM_TITLE = "item_title";

    @Override
    public SearchResult search(String query, Integer page, Integer rows) throws Exception {
        SearchResult result = new SearchResult();
        // 建造一个query类
        SolrQuery solrQuery = new SolrQuery(query);
        solrQuery.addHighlightField(ITEM_TITLE);
        solrQuery.set(CommonParams.DF, "item_keywords");
        // 默认应该是从第0条开始查询
        solrQuery.setStart((int) ((page-1) * rows));
        solrQuery.setRows(rows);
        // 执行查询
        result = searchDao.searchItem(solrQuery);
        long rowCount = result.getRecordCount();
        long pageCount = rowCount / rows;
        result.setPageCount(pageCount);
        // 默认应该为第一页数据
        result.setCurPage(page);
        return result;
    }

}
