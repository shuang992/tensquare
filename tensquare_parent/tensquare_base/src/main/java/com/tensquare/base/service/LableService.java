package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.domain.Lable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import util.IdWorker;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LableService {
    //涉及到增删改的代码的需要开启事务管理
    @Autowired
    private LableDao lableDao;
    @Autowired
    private IdWorker idWorker;
    public void save(Lable lable) {
        lable.setId(idWorker.nextId()+"");
        lableDao.save(lable);
    }

    public Lable findOne(String lableIdx) {
        return  lableDao.findById(lableIdx).get();
    }

    public List<Lable> findAll() {
        return lableDao.findAll();
    }

    public void update(Lable lable) {
       lableDao.save(lable);
    }

    public void deleteById(String lableId) {
        lableDao.deleteById(lableId);
    }
   /**
    * 条件查询
    **/
    public List<Lable> search(Map map) {
        Specification specification = getSpecification(map);
        List all = lableDao.findAll(specification);
        return all;
    }
    public Specification getSpecification(Map map){
        if (map.isEmpty()){
            return null;
        }
        return  new Specification() {
            @Nullable
            @Override
            /*
            * @root: 根实体 相当于数据库表名
            * @query:一般单表操作,可以设置sql from,where,group by,order by
            * @criteriaBuilder: Predicate实例工厂
            */
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                String labelname = (String) map.get("labelname");
                if (StringUtils.isNotBlank(labelname)){
                    //p1:查询实体中的属性;p2:查询具体值
                    Predicate p1 = criteriaBuilder.like(root.get("labelname").as(String.class),"%"+labelname+"%");
                    list.add(p1);
                }
                String state = (String) map.get("state");
                if (StringUtils.isNotBlank(state)){
                    Predicate p2 = criteriaBuilder.equal(root.get("state").as(String.class), state);
                    list.add(p2);
                }
                String recommend = (String) map.get("recommend");
                if (StringUtils.isNotBlank(recommend)){
                    Predicate p3 = criteriaBuilder.equal(root.get("recommend").as(String.class), recommend);
                    list.add(p3);
                }
                //条件使用and进行连接
                Predicate[] predicates=new Predicate[list.size()];
                //将集合转换成数组
                predicates=list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        };
    }

    public Page search(Map map, Pageable pageable) {
        Specification specification = getSpecification(map);

        return lableDao.findAll(specification,pageable);
    }

}
