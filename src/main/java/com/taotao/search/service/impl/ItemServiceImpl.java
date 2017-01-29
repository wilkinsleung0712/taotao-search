package com.taotao.search.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import com.taotao.util.ExceptionUtil;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult importAllItems() {
        // 1.读取数据库中需要存放solr的数据
        List<Item> allItems = itemMapper.getAllItems();
        // 2.把数据格式转化为solr文档格式
        Collection<SolrInputDocument> docs = new ArrayList<>();
        try {
            for (Item item : allItems) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", item.getId());
                document.addField("item_title", item.getItem_titile());
                document.addField("item_sell_point", item.getItem_sellpoint());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getItem_pic());
                document.addField("item_category_name",
                        item.getItem_category());
                document.setField("item_desc", item.getItem_description());
                docs.add(document);
            }
            solrServer.add(docs);
            // 3.存放solr服务器
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }

}
