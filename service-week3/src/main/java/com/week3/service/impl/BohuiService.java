package com.week3.service.impl;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.dubbo.config.annotation.Service;
import com.week3.mapper.WtGoodsMapper;
import com.week3.pojo.WtGoods;

@Service
public class BohuiService implements MessageListener{
	
	@Resource
	private WtGoodsMapper wtGoodsMapper;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			Integer id = Integer.parseInt(text);
			WtGoods wtGoods = new WtGoods();
			wtGoods.setId(id);
			wtGoodsMapper.update2(wtGoods);
			System.out.println("修改状态成功");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
