package com.tensquare.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    //根据标签id查询最新问题列表

    /**
     *
     * @param
     * @param pageable
     * @return
     * 1.在pl表中查询于传入的labelid一致的problemid
     * 2.在problem表中查询p.id在pl表中查询出来的数据,返回一个问题实体集合,并按照创建时间排序
     */
    //通过Query注解指定执行语句（sql,jpql）
    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) order by p.createtime desc")
    Page<Problem> findNewList(String labelId, Pageable pageable);

    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) order by p.reply desc")
    Page findHotList(String labelId, Pageable pageable);

    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) and p.reply = 0 order by p.createtime desc ")
    Page findWaitList(String labelId, Pageable pageable);

	
}
