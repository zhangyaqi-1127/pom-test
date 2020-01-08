package com.week3.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.solr.core.query.Field;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.week3.pojo.WtGoods;
import com.week3.service.GoodsService;

import entity.Result;

@RequestMapping("/goods")
@RestController
public class GoodsController {

	@Reference
	private GoodsService service;
	@Resource
	private Destination queueSolrDestination;
	@Resource
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination bhqueueSolrDestination;
	
	//查询列表页面
	@RequestMapping("/list")
	public List<WtGoods> list(@RequestBody WtGoods wtGoods){
		List<WtGoods> list = service.findByList(wtGoods);
		return list;
	}
	//审核方法(通过)
	@RequestMapping("/shen")
	public Result shen(final Integer id){
		//通过jms监听进行审核
		jmsTemplate.send(queueSolrDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(id+"");
			}
		});
		//审核失败
		return new Result(false,"审核失败");
	}
	//审核方法(驳回)
	@RequestMapping("/shen2")
	public Result shen2(final Integer id){
		//通过jms监听进行审核
		jmsTemplate.send(bhqueueSolrDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(id+"");
			}
		});
		//审核失败
		return new Result(false,"审核失败");
	}
	//查询solr中的数据列表
	@RequestMapping("/solrList")
	public void solrList(){
		service.solrList();
	}
	//删除方法
	@RequestMapping("/del")
	public Result del(Integer id){
		if(service.del(id)!=0){
			//如果删除结果成功
			return new Result(true,"删除成功");
		}
		//如果删除结果失败
		return new Result(false,"删除失败");
	}
}
