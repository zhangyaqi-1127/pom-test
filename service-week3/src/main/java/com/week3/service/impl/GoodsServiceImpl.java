package com.week3.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;

import com.alibaba.dubbo.config.annotation.Service;
import com.week3.mapper.WtGoodsMapper;
import com.week3.pojo.WtGoods;
import com.week3.pojo.WtGoodsExample;
import com.week3.pojo.WtGoodsExample.Criteria;
import com.week3.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService,MessageListener {

	@Resource
	private WtGoodsMapper wtGoodsMapper;
	@Resource
	private SolrTemplate solrTemplate;
	
	@Override
	public List<WtGoods> findByList(WtGoods goods) {
		// TODO Auto-generated method stub
		WtGoodsExample wtGoodsExample = new WtGoodsExample();
		Criteria createCriteria = wtGoodsExample.createCriteria();
		if(goods!=null){
			if(goods.getName()!=null){
				createCriteria.andNameLike("%"+goods.getName()+"%");
			}
			if(goods.getBrandName()!=null){
				createCriteria.andBrandNameLike("%"+goods.getBrandName()+"%");
			}
			List<WtGoods> selectByExample = wtGoodsMapper.selectByExample(wtGoodsExample);
			for (WtGoods wtGoods : selectByExample) {
				solrTemplate.saveBean(wtGoods);
				solrTemplate.commit();
			}
			return selectByExample;
		}
		List<WtGoods> selectByExample2 = wtGoodsMapper.selectByExample(null);
		for (WtGoods wtGoods : selectByExample2) {
			solrTemplate.saveBean(wtGoods);
			solrTemplate.commit();
		}
		return selectByExample2;
	}

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			Integer id = Integer.parseInt(text);
			WtGoods wtGoods = new WtGoods();
			wtGoods.setId(id);
			wtGoodsMapper.update(wtGoods);
			System.out.println("修改状态成功");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		WtGoods selectByPrimaryKey = wtGoodsMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey!=null){
			Query query = new SimpleQuery("*:*");
			solrTemplate.delete(query);
			solrTemplate.commit();
			List<WtGoods> selectByExample = wtGoodsMapper.selectByExample(null);
			for (WtGoods wtGoods : selectByExample) {
				solrTemplate.saveBean(wtGoods);
				solrTemplate.commit();
			}
			return wtGoodsMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}

	@Override
	public void solrList() {
		// TODO Auto-generated method stub
		
	}
}
