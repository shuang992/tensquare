package com.tensquare.base.controller;

import com.tensquare.base.domain.Lable;
import com.tensquare.base.service.LableService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
      return  new Result(true,2000,"增加成功");
   }

    /**
     * 查询单个ID的结果
     * @param
     * @return
     */
  @GetMapping("")
    public Result findAll(){
      List<Lable> all = lableService.findAll();
       return new Result(true,2000,"查询成功",all);
  }
    /**
     * 修改更新
     */
  @PutMapping("/{lableId}")
  public Result  update(@PathVariable String lableId ,@RequestBody Lable  lable){
    lable.setId(lableId);
    lableService.update(lable);
      return new Result(true,2000,"修改成功");
  }
    /**
     * 删除指定id
     *
     */
    @DeleteMapping("/{lableId}")
    public Result deleteLable(@PathVariable String lableId){
        lableService.deleteById(lableId);
        return new Result(true,2000,"删除成功");
    }
}
