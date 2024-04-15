package cn.zwz.chat.service;

import cn.hutool.core.lang.Dict;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.chat.dao.ImSingleDao;
import cn.zwz.chat.entity.ImSingle;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImSingleService {
    @Resource
    private SecurityUtil securityUtil;

    @Resource
    private ImSingleDao imSingleDao;

    public ImSingle add(ImSingle imSingle) {
        imSingleDao.insert(imSingle);
        return imSingle;
    }
    public List<ImSingle> findByUsername(String fromUser, String toUser) {
        List<ImSingle> list = imSingleDao.findByUsername(fromUser, toUser);
        list.forEach(x -> {
            if (x.getTouser().equals(fromUser) && x.getFromuser().equals(toUser)) {
                x.setReaded(1);
                LambdaQueryWrapper<ImSingle> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
                objectLambdaQueryWrapper.eq(ImSingle::getId,x.getId());
                imSingleDao.update(x, objectLambdaQueryWrapper);
            }
        });
        return list;
    }

    public Dict findUnReadNums(String toUsername) {
        List<ImSingle> list = imSingleDao.findByToUsername(toUsername);
        Map<String, List<ImSingle>> collect = list.stream().collect(Collectors.groupingBy(ImSingle::getFromuser));
        Dict dict = Dict.create();
        collect.forEach((k,v) -> dict.set(k, v.size()));
        return dict;
    }
}