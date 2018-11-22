package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.domain.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import util.IdWorker;

import javax.persistence.Id;
import java.util.List;

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
}
