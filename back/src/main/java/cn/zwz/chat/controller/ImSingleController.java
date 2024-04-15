package cn.zwz.chat.controller;

import cn.hutool.core.lang.Dict;

import cn.zwz.chat.common.Result;
import cn.zwz.chat.entity.ImSingle;
import cn.zwz.chat.service.ImSingleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
*  描述：单人聊天相关接口
*/
@RestController
@RequestMapping(value = "/imsingle")
public class ImSingleController {

    @Resource
    private ImSingleService imSingleService;

    /**
     * 查询所有消息
     */
    @GetMapping
    public Result findByFromUsername(@RequestParam String fromUser, @RequestParam String toUser) {
        List<ImSingle> all = (List<ImSingle>) imSingleService.findByUsername(fromUser, toUser);
        return Result.success(all);
    }

    /**
     * 查询未读消息数量
     * @return 未读消息数量
     */
    @GetMapping("/unReadNums")
    public Result findUnReadNums(@RequestParam String toUsername) {
        Dict dict = imSingleService.findUnReadNums(toUsername);
        return Result.success(dict);
    }

}
