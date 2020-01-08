package com.week3.mapper;

import com.week3.pojo.WtGoods;
import com.week3.pojo.WtGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface WtGoodsMapper {
    int countByExample(WtGoodsExample example);

    int deleteByExample(WtGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WtGoods record);

    int insertSelective(WtGoods record);

    List<WtGoods> selectByExample(WtGoodsExample example);

    WtGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WtGoods record, @Param("example") WtGoodsExample example);

    int updateByExample(@Param("record") WtGoods record, @Param("example") WtGoodsExample example);

    int updateByPrimaryKeySelective(WtGoods record);

    int updateByPrimaryKey(WtGoods record);

    @Update(value="update wt_goods set status=1 where id=#{id}")
	void update(WtGoods wtGoods);

    @Update(value="update wt_goods set status=2 where id=#{id}")
	void update2(WtGoods wtGoods);
}