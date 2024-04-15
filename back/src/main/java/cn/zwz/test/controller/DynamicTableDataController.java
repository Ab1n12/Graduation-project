package cn.zwz.test.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.NullUtils;
import cn.zwz.test.entity.DynamicTableData;
import cn.zwz.test.service.IDynamicTableDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "动态表格值管理接口")
@RequestMapping("/zwz/dynamicTableData")
@Transactional
public class DynamicTableDataController {

    @Autowired
    private IDynamicTableDataService iDynamicTableDataService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条动态表格值")
    public Result<DynamicTableData> get(@RequestParam String id){
        return new ResultUtil<DynamicTableData>().setData(iDynamicTableDataService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部动态表格值个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iDynamicTableDataService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部动态表格值")
    public Result<List<DynamicTableData>> getAll(){
        return new ResultUtil<List<DynamicTableData>>().setData(iDynamicTableDataService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询动态表格值")
    public Result<IPage<DynamicTableData>> getByPage(@ModelAttribute DynamicTableData dynamicTableData ,@ModelAttribute PageVo page){
        QueryWrapper<DynamicTableData> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(dynamicTableData.getCreateBy())) {
        }
        IPage<DynamicTableData> data = iDynamicTableDataService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<DynamicTableData>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改动态表格值")
    public Result<DynamicTableData> saveOrUpdate(DynamicTableData dynamicTableData){
        if(iDynamicTableDataService.saveOrUpdate(dynamicTableData)){
            return new ResultUtil<DynamicTableData>().setData(dynamicTableData);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增动态表格值")
    public Result<DynamicTableData> insert(DynamicTableData dynamicTableData){
        iDynamicTableDataService.saveOrUpdate(dynamicTableData);
        return new ResultUtil<DynamicTableData>().setData(dynamicTableData);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑动态表格值")
    public Result<DynamicTableData> update(DynamicTableData dynamicTableData){
        iDynamicTableDataService.saveOrUpdate(dynamicTableData);
        return new ResultUtil<DynamicTableData>().setData(dynamicTableData);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除动态表格值")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iDynamicTableDataService.removeById(id);
        }
        return ResultUtil.success();
    }
}
