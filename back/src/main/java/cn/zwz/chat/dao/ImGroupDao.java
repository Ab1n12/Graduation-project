package cn.zwz.chat.dao;


import cn.zwz.chat.entity.ImGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ImGroupDao extends BaseMapper<ImGroup> {

	List<ImGroup> findBySearch(@Param("search") ImGroup search);

}
