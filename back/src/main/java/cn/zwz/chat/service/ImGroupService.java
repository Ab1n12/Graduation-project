package cn.zwz.chat.service;

import cn.hutool.core.util.ObjectUtil;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.chat.dao.ImGroupDao;
import cn.zwz.chat.entity.ImGroup;
import cn.zwz.chat.exception.CustomException;
import cn.zwz.data.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImGroupService {

    @Autowired
    private SecurityUtil securityUtil;

    @Resource
    private ImGroupDao imGroupDao;

    public ImGroup add(ImGroup imGroup) {
        imGroupDao.insert(imGroup);
        return imGroup;
    }

    public void delete(Integer id) {
        imGroupDao.deleteById(id);
    }

    public void update(ImGroup imGroup) {
        imGroupDao.update(imGroup, new LambdaQueryWrapper<>());
    }

    public ImGroup findById(Integer id) {
        return imGroupDao.selectById(id);
    }

    public List<ImGroup> findAll() {
        return imGroupDao.findBySearch(null);
    }

    public IPage<ImGroup> findPage(ImGroup search, Integer pageNum, Integer pageSize) {
        User user = securityUtil.getCurrUser();
        if (ObjectUtil.isNull(user)) {
            throw new CustomException("token验证失效");
        }
        Page<ImGroup> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<ImGroup> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return imGroupDao.selectPage(page, objectLambdaQueryWrapper);
    }

    public List<ImGroup> findByCondition(User user, ImGroup search) {
        return imGroupDao.findBySearch(search);
    }
}