package com.tensquare.base.controller;

import com.tensquare.base.domain.Lable;
import com.tensquare.base.service.LableService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.hibernate.transform.TupleSubsetResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lable")
public class LableController {
   @Autowired
   private LableService lableService;

    /**
     * 新增标签
     * 新增使用post
     * @return
     */
 //  @RequestMapping(value = "",method = RequestMethod.POST)
   @PostMapping("")
   public Result save(@RequestBody Lable lable){
      lableService.save(lable);
      return  new Result(true, StatusCode.OK,"增加成功");
   }

    /**
     * 查询单个ID的结果
     * @param
     * @return
     */
  @GetMapping("")
    public Result findAll(){
      List<Lable> all = lableService.findAll();
       return new Result(true,StatusCode.OK,"查询成功",all);
  }
    /**
     * 查询单个ID的结果
     * @param
     * @return
     */
    @GetMapping("/{lableId}")
    public Result findOne(@PathVariable("lableId") String lableIdx){
        //int i=1/0;
        Lable lableOne = lableService.findOne(lableIdx);
        return new Result(true,StatusCode.OK,"查询成功",lableOne);
    }
    /**
     * 修改更新
     */
  @PutMapping("/{lableId}")
  public Result  update(@PathVariable String lableId ,@RequestBody Lable  lable){
    lable.setId(lableId);
    lableService.update(lable);
      return new Result(true,StatusCode.OK,"修改成功");
  }
    /**
     * 删除指定id
     *
     */
    @DeleteMapping("/{lableId}")
    public Result deleteLable(@PathVariable String lableId){
        lableService.deleteById(lableId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    @PostMapping("/search")
    public Result search(@RequestBody Map map){
        List<Lable> lables=lableService.search(map);
        return  new Result(true,StatusCode.OK,"查询成功",lables);
    }
    @PostMapping("/search/{page}/{size}")
    public Result search(@PathVariable int page,@PathVariable int size,@RequestBody Map map){
        //封装当前页,页面大小
        Pageable pageable= PageRequest.of(page-1,size);
        //page对象中查询到总记录数,当前页记录
        Page pageData=lableService.search(map,pageable);
        System.out.println("---------------"+pageData.getTotalElements());
        System.out.println("---------------"+pageData.getContent());
        PageResult<Lable> pageResult=new PageResult<>(pageData.getTotalElements(),pageData.getContent());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
