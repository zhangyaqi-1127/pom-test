package com.week3.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.solr.core.query.Field;

import com.week3.pojo.WtGoods;

public interface GoodsService {

	List<WtGoods> findByList(WtGoods wtGoods);

	void solrList();

	int del(Integer id);

}
