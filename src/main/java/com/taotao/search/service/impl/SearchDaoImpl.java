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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchDao;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws SolrServerException {
        // 执行查询
        QueryResponse queryResponse = solrServer.query(query);
        SearchResult result = new SearchResult();
        // 提取所有查询结果
        SolrDocumentList results = queryResponse.getResults();
        // 新建一个返回pojo的List存放查询结果
        List<Item> resultItemList = new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = queryResponse
                .getHighlighting();
        for (SolrDocument solrDocument : results) {
            Item item = new Item();
            item.setId((String) solrDocument.get("id"));
            // 取出高亮
            List<String> itemTitlesList = highlighting
                    .get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (itemTitlesList != null && !itemTitlesList.isEmpty()) {
                itemTitle = itemTitlesList
                        .get((int) solrDocument.get("item_title"));
            } else {
                // itemTitle = solrDocument.get("item_title");
            }

            // item.setItem_titile(itemTitlesList);
            item.setItem_category((String) solrDocument.get("item_category"));
            item.setItem_description(
                    (String) solrDocument.get("item_description"));
            item.setItem_pic((String) solrDocument.get("item_pic"));
            item.setItem_sellpoint(
                    (String) solrDocument.get("item_sell_point"));

            item.setPrice((Long) solrDocument.get("item_price"));
            resultItemList.add(item);
        }
        // 得到一个itemlist之后需要构建一个返回pojo
        result.setRecordCount(results.getNumFound());
        result.setPageCount(results.getStart());
        result.setItemList(resultItemList);
        return null;
    }

}
