package com.taotao.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchDao;

/**
 * @Date 10:13:08 am 31 Jan 2017
 * @author wilkins.liang
 *
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    private static String ITEM_TITLE = "item_title";
    private static String ITEM_CATEGORY_NAME = "item_category_name";
    private static String ITEM_IMAGE = "item_image";
    private static String ITEM_SELL_POINT = "item_sell_point";
    private static String ITEM_PRICE = "item_price";

    @Override
    public SearchResult search(String queryString, Integer pages, Integer rows) throws SolrServerException {
        // 建造一个query类
        SolrQuery solrQuery = new SolrQuery(queryString);
        solrQuery.addHighlightField(ITEM_TITLE);
        solrQuery.set(CommonParams.DF, "item_keywords");
        solrQuery.setStart(pages * rows);
        solrQuery.setRows(rows);
        // 执行查询
        QueryResponse queryResponse = solrServer.query(solrQuery);
        SearchResult result = new SearchResult();
        // 提取所有查询结果
        SolrDocumentList results = queryResponse.getResults();
        // 新建一个返回pojo的List存放查询结果
        List<Item> resultItemList = new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        // 遍历每一个document类
        for (SolrDocument solrDocument : results) {
            Item item = new Item();
            item.setItem_id((String) solrDocument.get("id"));
            // 取出高亮
            List<String> itemTitlesList = highlighting.get(solrDocument.get("id")).get(ITEM_TITLE);
            String itemTitle = "";
            if (itemTitlesList != null && !itemTitlesList.isEmpty()) {
                itemTitle = itemTitlesList.get(0);
            } else {
                itemTitle = (String) solrDocument.get(ITEM_TITLE);
            }

            item.setItem_title(itemTitle);
            item.setItem_category_name((String) solrDocument.get(ITEM_CATEGORY_NAME));
            item.setItem_image((String) solrDocument.get(ITEM_IMAGE));
            item.setItem_sell_point((String) solrDocument.get(ITEM_SELL_POINT));
            item.setItem_price((Long) solrDocument.get(ITEM_PRICE));
            resultItemList.add(item);
        }
        // 得到一个itemlist之后需要构建一个返回pojo
        result.setRecordCount(results.getNumFound());
        result.setPageCount(results.getStart());
        result.setItemList(resultItemList);
        return result;
    }

}
