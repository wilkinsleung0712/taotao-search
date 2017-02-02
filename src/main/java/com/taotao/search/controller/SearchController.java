package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import com.taotao.util.ExceptionUtil;

/**
 * @Date 10:26:58 am 31 Jan 2017
 * @author wilkins.liang
 *
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchDao;

    @RequestMapping("/query")
    @ResponseBody
    public TaotaoResult search(@RequestParam(value = "q") String queryString,
            @RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "50") Integer rows)
            throws SolrServerException {

        if (StringUtils.isBlank(queryString)) {
            return TaotaoResult.build(400, "查询条件不能为空");
        }
        // 调用dao方法
        // 返回结果
        
        SearchResult searchResult = null;
        try {
            //中文字符串转码
            queryString = new String(queryString.getBytes("ISO-8859-1"),"UTF-8");
            searchResult = searchDao.search(queryString, pages, rows);
            return TaotaoResult.ok(searchResult);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

    }
}
