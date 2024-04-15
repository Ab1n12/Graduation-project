package cn.zwz.notice.serviceimpl;

import cn.zwz.notice.mapper.NoticeMapper;
import cn.zwz.notice.entity.Notice;
import cn.zwz.notice.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告服务层接口实现
 */
@Slf4j
@Service
@Transactional
public class INoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
}