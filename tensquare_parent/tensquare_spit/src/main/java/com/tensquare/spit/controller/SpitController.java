package com.tensquare.spit.controller;

import com.tensquare.spit.domain.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    /**
     * 发布吐槽
     * 1.保存吐槽数据
     * 2.评论吐槽数据
     */
    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.add(spit);
        return  new Result(true, StatusCode.OK,"吐槽成功");
    }
    //查询所有
    @GetMapping
    public Result findAll(){
        List<Spit> spitList=spitService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",spitList);
    }
    //根据id查询吐槽
    @GetMapping("/{spitId}")
    public  Result findById(@PathVariable String spitId){
        Spit spit=spitService.findById(spitId);
        return  new Result(true,StatusCode.OK,"查询成功",spit);

    }
    //根据上级id查询吐槽内容
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Spit> pageData = spitService.findByParentid(parentid, pageable);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
    }
    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String  spitId){
        spitService.updateThumbup(spitId);
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}

