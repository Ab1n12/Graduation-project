package cn.zwz.notice.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.NullUtils;
import cn.zwz.notice.entity.Notice;
import cn.zwz.notice.service.INoticeService;
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
@Api(tags = "公告管理接口")
@RequestMapping("/zwz/notice")
@Transactional
public class NoticeController {

    @Autowired
    private INoticeService iNoticeService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条公告")
    public Result<Notice> get(@RequestParam String id){
        return new ResultUtil<Notice>().setData(iNoticeService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部公告个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iNoticeService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部公告")
    public Result<List<Notice>> getAll(){
        return new ResultUtil<List<Notice>>().setData(iNoticeService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询公告")
    public Result<IPage<Notice>> getByPage(@ModelAttribute Notice notice ,@ModelAttribute PageVo page){
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(notice.getTitle())) {
            qw.like("title",notice.getTitle());
        }
        IPage<Notice> data = iNoticeService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Notice>>().setData(data);
    }

    @RequestMapping(value = "/changeOpenNotice", method = RequestMethod.POST)
    @ApiOperation(value = "公开公告")
    public Result<Notice> openNotice(@RequestParam String id){
        Notice notice = iNoticeService.getById(id);
        notice.setOpen(true);
        iNoticeService.saveOrUpdate(notice);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/changeCloseNotice", method = RequestMethod.POST)
    @ApiOperation(value = "隐藏公告")
    public Result<Notice> closeNotice(@RequestParam String id){
        Notice notice = iNoticeService.getById(id);
        notice.setOpen(false);
        iNoticeService.saveOrUpdate(notice);
        return ResultUtil.success();
    }


    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改公告")
    public Result<Notice> saveOrUpdate(Notice notice){
        if(iNoticeService.saveOrUpdate(notice)){
            return new ResultUtil<Notice>().setData(notice);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增公告")
    public Result<Notice> insert(Notice notice){
        iNoticeService.saveOrUpdate(notice);
        return new ResultUtil<Notice>().setData(notice);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑公告")
    public Result<Notice> update(Notice notice){
        iNoticeService.saveOrUpdate(notice);
        return new ResultUtil<Notice>().setData(notice);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除公告")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iNoticeService.removeById(id);
        }
        return ResultUtil.success();
    }


    @RequestMapping(value = "/getOnHome", method = RequestMethod.POST)
    @ApiOperation(value = "主页查询公告")
    public Result<List<Notice>> getOnHome(){
        QueryWrapper<Notice> qw = new QueryWrapper<Notice>().orderByDesc("id");
        qw.eq("open", "1");
        return new ResultUtil<List<Notice>>().setData(iNoticeService.list(qw));
    }

}
