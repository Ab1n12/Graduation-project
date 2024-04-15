package cn.zwz.test.serviceimpl;

import cn.zwz.test.mapper.DynamicTableDataMapper;
import cn.zwz.test.entity.DynamicTableData;
import cn.zwz.test.service.IDynamicTableDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 动态表格值 服务层接口实现
 */
@Slf4j
@Service
@Transactional
public class IDynamicTableDataServiceImpl extends ServiceImpl<DynamicTableDataMapper, DynamicTableData> implements IDynamicTableDataService {

    @Autowired
    private DynamicTableDataMapper dynamicTableDataMapper;
}