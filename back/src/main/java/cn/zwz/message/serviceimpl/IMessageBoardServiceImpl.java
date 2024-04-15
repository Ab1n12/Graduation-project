package cn.zwz.message.serviceimpl;

import cn.zwz.message.entity.MessageBoard;
import cn.zwz.message.mapper.MessageBoardMapper;
import cn.zwz.message.service.IMessageBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IMessageBoardServiceImpl extends ServiceImpl<MessageBoardMapper, MessageBoard> implements IMessageBoardService {

}