package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    //查询状态为2 根据查询的结果中的日期进行倒叙的排序
	public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
	//创建插叙方法,返回查询的前12条记录,按创建日期进行降序排列
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
