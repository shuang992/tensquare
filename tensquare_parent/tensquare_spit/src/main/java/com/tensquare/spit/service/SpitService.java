package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.domain.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;
import java.util.Date;
import java.util.List;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitdao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;
    public void add(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setShare(0);
        spit.setState("1");
        spit.setVisits(0);
        spit.setComment(0);
        spit.setThumbup(0);
        //说明是上下级吐槽数据
        if (!StringUtils.isEmpty(spit.getParentid())){
            //上级吐槽回复加1
            Spit parentSpit= spitdao.findById(spit.getParentid()).get();
            parentSpit.setComment(parentSpit.getComment()+1);
            spitdao.save(parentSpit);
        }
        spitdao.save(spit);
    }
    public void update(Spit spit){
        spitdao.save(spit);
    }
    public void deleteById(String id){
        spitdao.deleteById(id);
    }
    public Spit findById(String spitId){
        return  spitdao.findById(spitId).get();
    }

    public List<Spit> findAll() {
        return spitdao.findAll();

    }

    public Page<Spit> findByParentid(String parentid, Pageable pageable) {
        return  spitdao.findByParentid(parentid,pageable);
    }

    /**
     * 对数据库中的thumbup加1
     * @param spitid
     */
    public void updateThumbup(String spitid) {
        //方式1
        //Spit spit = spitdao.findById(spitid).get();
        //spit.setThumbup(spit.getComment()+1);
        //spitdao.save(spit);
      //方式2
        Query query=new Query();//创建查询条件
        query.addCriteria(Criteria.where("_id").is(spitid));//查询id等于spitid的数据
        Update update=new Update();//创建更新条件
        update.inc("thumbup",1);//更新字段,设置增长步长
        mongoTemplate.updateFirst(query,update,"spit");
    }

}

