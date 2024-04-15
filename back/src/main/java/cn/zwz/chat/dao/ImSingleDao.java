package cn.zwz.chat.dao;

import cn.zwz.chat.entity.ImSingle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ImSingleDao extends BaseMapper<ImSingle> {

	@Select("select * from im_single where (fromuser = #{fromUser} and touser = #{toUser}) or (fromuser = #{toUser} and touser = #{fromUser}) order by time asc ")
	List<ImSingle> findByUsername(String fromUser, String toUser);

	@Select("select * from im_single where touser = #{toUser} and readed = 0")
	List<ImSingle> findByToUsername(String toUser);
}
