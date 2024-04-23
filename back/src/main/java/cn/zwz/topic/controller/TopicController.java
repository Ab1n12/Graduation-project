package cn.zwz.topic.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IRoleService;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.NullUtils;
import cn.zwz.topic.entity.Topic;
import cn.zwz.topic.service.ITopicService;
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
@Api(tags = "毕业设计课题管理接口")
@RequestMapping("/zwz/topic")
@Transactional
public class TopicController {

    @Autowired
    private ITopicService iTopicService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条毕业设计课题")
    public Result<Topic> get(@RequestParam String id){
        return new ResultUtil<Topic>().setData(iTopicService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部毕业设计课题个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iTopicService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部毕业设计课题")
    public Result<List<Topic>> getAll(){
        return new ResultUtil<List<Topic>>().setData(iTopicService.list());
    }


    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部用户")
    public Result<List<User>> getAllUser(){
        return new ResultUtil<List<User>>().setData(iUserService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询我发布的毕业设计课题")
    public Result<IPage<Topic>> getByPage(@ModelAttribute Topic topic ,@ModelAttribute PageVo page){
        QueryWrapper<Topic> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(topic.getTitle())) {
            qw.like("title",topic.getTitle());
        }
        if(!NullUtils.isNull(topic.getType())) {
            qw.eq("type",topic.getType());
        }
        if(!NullUtils.isNull(topic.getLevel())) {
            qw.eq("level",topic.getLevel());
        }
        qw.eq("tea_id",securityUtil.getCurrUser().getId());
        IPage<Topic> data = iTopicService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Topic>>().setData(data);
    }

    @RequestMapping(value = "/getByPageForAudit", method = RequestMethod.GET)
    @ApiOperation(value = "查询我发布的毕业设计课题")
    public Result<IPage<Topic>> getByPageForAudit(@ModelAttribute Topic topic ,@ModelAttribute PageVo page){
        QueryWrapper<Topic> qw = new QueryWrapper<>();
        qw.eq("tea_id",securityUtil.getCurrUser().getId());
        IPage<Topic> data = iTopicService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Topic>>().setData(data);
    }


    @RequestMapping(value = "/getByAdminPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有毕业设计课题")
    public Result<IPage<Topic>> getByAdminPage(@ModelAttribute Topic topic ,@ModelAttribute PageVo page){
        QueryWrapper<Topic> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(topic.getTitle())) {
            qw.like("title",topic.getTitle());
        }
        if(!NullUtils.isNull(topic.getType())) {
            qw.eq("type",topic.getType());
        }
        if(!NullUtils.isNull(topic.getRemark())) {
            qw.like("remark",topic.getRemark());
        }
        if(!NullUtils.isNull(topic.getLevel())) {
            qw.eq("level",topic.getLevel());
        }
        qw.eq("pass", topic.getPass());
        IPage<Topic> data = iTopicService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Topic>>().setData(data);
    }

    @RequestMapping(value = "/getByUserPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询我发布的毕业设计课题")
    public Result<IPage<Topic>> getByUserPage(@ModelAttribute Topic topic ,@ModelAttribute PageVo page){
        QueryWrapper<Topic> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(topic.getTitle())) {
            qw.like("title",topic.getTitle());
        }
        if(!NullUtils.isNull(topic.getType())) {
            qw.eq("type",topic.getType());
        }
        if(!NullUtils.isNull(topic.getLevel())) {
            qw.eq("level",topic.getLevel());
        }
        qw.eq("pass",true);

        IPage<Topic> data = iTopicService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Topic>>().setData(data);
    }

    @RequestMapping(value = "/getByMyPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询我选的毕业设计课题")
    public Result<IPage<Topic>> getByMyPage(@ModelAttribute Topic topic ,@ModelAttribute PageVo page){
        QueryWrapper<Topic> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(topic.getTitle())) {
            qw.like("title",topic.getTitle());
        }
        if(!NullUtils.isNull(topic.getType())) {
            qw.eq("type",topic.getType());
        }
        if(!NullUtils.isNull(topic.getLevel())) {
            qw.eq("level",topic.getLevel());
        }
        qw.eq("id",securityUtil.getCurrUser().getTopicId());

        IPage<Topic> data = iTopicService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Topic>>().setData(data);
    }

    @RequestMapping(value = "/getByMyPageUser", method = RequestMethod.GET)
    @ApiOperation(value = "查询我选的毕业设计课题")
    public Result<IPage<User>> getByMyPageUser(@ModelAttribute User user ,@ModelAttribute PageVo page){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("check_flag", "1");
        qw.eq("id",securityUtil.getCurrUser().getId());
        IPage<User> data = iUserService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<User>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改毕业设计课题")
    public Result<Topic> saveOrUpdate(Topic topic){
        if(iTopicService.saveOrUpdate(topic)){
            return new ResultUtil<Topic>().setData(topic);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/getMyStudent", method = RequestMethod.GET)
    @ApiOperation(value = "查询选择我发布的毕业设计课题的学生")
    public Result<IPage<User>> getMyStudent(@ModelAttribute User user ,@ModelAttribute PageVo page){
        QueryWrapper<User> qw = new QueryWrapper<>();
        if(!NullUtils.isNull(user.getNickname())) {
            qw.eq("nickname",user.getNickname());
        }
        if(!NullUtils.isNull(user.getDepartmentTitle())) {
            qw.eq("department_title",user.getDepartmentTitle());
        }
        qw.eq("my_tea_id",securityUtil.getCurrUser().getNickname());
        IPage<User> data = iUserService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<User>>().setData(data);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增毕业设计课题")
    public Result<Topic> insert(Topic topic){
        User currUser = securityUtil.getCurrUser();
        topic.setTeaId(currUser.getId());
        topic.setTeaName(currUser.getNickname());
        iTopicService.saveOrUpdate(topic);
        return new ResultUtil<Topic>().setData(topic);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑毕业设计课题")
    public Result<Topic> update(Topic topic){
        iTopicService.saveOrUpdate(topic);
        return new ResultUtil<Topic>().setData(topic);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除毕业设计课题")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iTopicService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/checkTopic", method = RequestMethod.POST)
    @ApiOperation(value = "选择课题")
    public Result<Object> checkTopic(@RequestParam String id){
        Topic topic = iTopicService.getById(id);
        User currUser = securityUtil.getCurrUser();
        if(topic == null) {
            return ResultUtil.error("课题不存在");
        }
        if(!NullUtils.isNull(currUser.getMyTeaId()) && !NullUtils.isNull(currUser.getTopicName())){
            return ResultUtil.error("您已有选题");
        }
        if(topic.getQuota() == 0) {
            topic.setCheckFlag(true);
            return ResultUtil.error("选题人数已达上限");
        }
        //迫不得已之举。。。
        currUser.setCheckFlag(true);
        currUser.setTopicId(topic.getId());
        currUser.setMyTeaId(topic.getTeaName());
        currUser.setTopicName(topic.getTitle());

        currUser.setTopicType(topic.getType());
        currUser.setTopicRemark(topic.getRemark());
        currUser.setTopicLevel(topic.getLevel());
        currUser.setTopicTaskFile(topic.getTaskFile());
//        topic.setCheckFlag(true);
        if(topic.getCheckId() != null){
            topic.setCheckId(topic.getCheckId() + " " + "'" + currUser.getId() + "'" + " ");
            topic.setCheckName(topic.getCheckName() + " " + currUser.getNickname());
        } else {
            topic.setCheckId("'" + currUser.getId() + "'");
            topic.setCheckName(" " + currUser.getNickname());
        }
        topic.setQuota(topic.getQuota() - 1);
        //每次选择完之后都要判断一下是否还有剩余，不能等出发选择时再进行判断和更改
        if(topic.getQuota() == 0) {
            topic.setCheckFlag(true);
        }
        iTopicService.saveOrUpdate(topic);
        iUserService.saveOrUpdate(currUser);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/checkNotTopic", method = RequestMethod.POST)
    @ApiOperation(value = "取消选择课题")
    public Result<Object> checkNotTopic(@RequestParam String id){
        User currUser = securityUtil.getCurrUser();
        Topic topic = iTopicService.getById(currUser.getTopicId());
        if(topic == null) {
            return ResultUtil.error("课题不存在");
        }
        currUser.setCheckFlag(false);
        currUser.setTopicId("");
        currUser.setMyTeaId("");
        currUser.setAudit(false);
        currUser.setTopicName("");

        currUser.setTopicType("");
        currUser.setTopicRemark("");
        currUser.setTopicLevel("");
        currUser.setTopicTaskFile("");
        topic.setCheckFlag(false);
        topic.setCheckId(topic.getCheckId().replace(" " + "'" + currUser.getId() + "'", ""));
        topic.setCheckName(topic.getCheckName().replace(currUser.getNickname(), ""));
        //topic.setAuditFlag(false);
        topic.setQuota(topic.getQuota() + 1); //名额加一
        iTopicService.saveOrUpdate(topic);
        iUserService.saveOrUpdate(currUser);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/checkNotTopicByTea", method = RequestMethod.POST)
    @ApiOperation(value = "教师驳回课题课题")
    public Result<Object> checkNotTopicByTea(@RequestParam String id){
        User user = iUserService.getById(id);
        Topic topic = iTopicService.getById(user.getTopicId());
        user.setAudit(false);
        user.setMyTeaId("");
        user.setTopicId("");
        user.setTopicName("");
        user.setCheckFlag(false);

        user.setTopicType("");
        user.setTopicLevel("");
        user.setTopicRemark("");
        user.setTopicTaskFile("");
        topic.setCheckId(topic.getCheckId().replace(" " + "'" + user.getId() + "'" + " ", ""));
        topic.setCheckName(topic.getCheckName().replace(user.getNickname(), ""));
        topic.setQuota(topic.getQuota() + 1); //名额加一
        iTopicService.saveOrUpdate(topic);
        iUserService.saveOrUpdate(user);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/auditTopic", method = RequestMethod.POST)
    @ApiOperation(value = "教师审核课题")
    public Result<Object> auditTopic(@RequestParam String id){
        User user = iUserService.getById(id);
//        if(topic == null) {
//            return ResultUtil.error("课题不存在");
//        }
        if(user == null) {
            return ResultUtil.error("用户不存在");
        }
        user.setAudit(true);
//        topic.setAuditFlag(true);
//        iTopicService.saveOrUpdate(topic);
        iUserService.saveOrUpdate(user);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ApiOperation(value = "上传任务书")
    public Result<Object> uploadFile(@RequestParam String id,@RequestParam String file){
        Topic topic = iTopicService.getById(id);
        if(topic == null) {
            return ResultUtil.error("课题不存在");
        }
        topic.setTaskFile(file);
        iTopicService.saveOrUpdate(topic);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/passTopic", method = RequestMethod.POST)
    @ApiOperation(value = "管理员审核课题")
    public Result<Object> passTopic(@RequestParam String id){
        Topic topic = iTopicService.getById(id);
        if(topic == null) {
            return ResultUtil.error("课题不存在");
        }
        topic.setPass(true);
        iTopicService.saveOrUpdate(topic);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/notPassTopic", method = RequestMethod.POST)
    @ApiOperation(value = "管理员驳回课题")
    public Result<Object> notPassTopic(@RequestParam String id){
        Topic topic = iTopicService.getById(id);
        if(topic == null) {
            return ResultUtil.error("课题不存在");
        }
        topic.setPass(false);
        iTopicService.saveOrUpdate(topic);
        return ResultUtil.success();
    }
}
