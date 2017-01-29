package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.search.pojo.SearchResult;

public interface SearchDao {
    
    /**
     * @param query
     * @return
     * @throws SolrServerException 
     */
    public SearchResult search(SolrQuery query) throws SolrServerException;
}
