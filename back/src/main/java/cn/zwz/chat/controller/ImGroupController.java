package cn.zwz.chat.controller;

import cn.zwz.chat.common.Result;
import cn.zwz.chat.entity.ImGroup;
import cn.zwz.chat.service.ImGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
*  描述：群组聊天相关接口
*/
@RestController
@RequestMapping(value = "/imGroup")
public class ImGroupController {

    @Resource
    private ImGroupService imGroupService;

    /**
     * 描述：新增
     */
    @PostMapping
    public Result add(@RequestBody ImGroup imGroup) {

        imGroupService.add(imGroup);
        return Result.success(imGroup);
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        imGroupService.delete(id);
        return Result.success();
    }

    /**
     * 描述：更新
     */
    @PutMapping
    public Result update(@RequestBody ImGroup imGroup) {

        imGroupService.update(imGroup);
        return Result.success();
    }

    /**
     * 描述：根据ID查询
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        ImGroup imGroup = imGroupService.findById(id);
        return Result.success(imGroup);
    }

    /**
     * 描述：查询所有
     */
    @GetMapping
    public Result all() {
        return Result.success(imGroupService.findAll());
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody ImGroup search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(imGroupService.findPage(search, pageNum, pageSize));
    }




}
