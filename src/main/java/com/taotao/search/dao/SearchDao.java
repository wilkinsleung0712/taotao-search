package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

/**
 * This class is main for changing data object in solr
 * @Date 11:22:37 am 2 Feb 2017
 * @author wilkins.liang
 *
 */
public interface SearchDao {
    /**
     * Provide structured SolrQuery for searching
     * @param solrQuery
     * @return
     * @throws Exception 
     */
    public SearchResult searchItem(SolrQuery solrQuery) throws Exception;
}
