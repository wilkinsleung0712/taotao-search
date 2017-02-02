package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {
    
    /**
     * @param query
     * @return
     * @throws SolrServerException 
     * @throws Exception 
     */
    public SearchResult search(String query, Integer page, Integer rows) throws SolrServerException, Exception;
}
