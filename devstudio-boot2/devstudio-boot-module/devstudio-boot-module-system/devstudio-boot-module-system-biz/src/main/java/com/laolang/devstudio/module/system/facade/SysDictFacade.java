package com.laolang.devstudio.module.system.facade;

import com.google.common.collect.Lists;
import com.laolang.devstudio.module.system.domain.res.SysDictTypeGroupInfoRes;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysDictFacade {

    public List<SysDictTypeGroupInfoRes> typeGroupInfo() {
        return Lists.newArrayList(new SysDictTypeGroupInfoRes().setGroupCode("system").setGroupName("系统字典"));
    }

}
