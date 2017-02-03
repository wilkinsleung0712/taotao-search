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
				document.addField("title", item.getTitle());
				document.addField("sell_point", item.getSell_point());
				document.addField("price", item.getPrice());
				document.addField("image", item.getImage());
				document.addField("category_name", item.getCategory_name());
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
