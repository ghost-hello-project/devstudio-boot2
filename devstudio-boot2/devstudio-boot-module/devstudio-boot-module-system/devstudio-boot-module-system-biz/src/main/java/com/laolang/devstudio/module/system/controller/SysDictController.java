package com.laolang.devstudio.module.system.controller;

import com.laolang.devstudio.module.system.domain.res.SysDictTypeGroupInfoRes;
import com.laolang.devstudio.module.system.facade.SysDictFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("system/dict")
@RestController
public class SysDictController {

    final SysDictFacade sysDictFacade;

    @PostMapping("type/groupInfo")
    public List<SysDictTypeGroupInfoRes> typeGroupInfo() {
        return sysDictFacade.typeGroupInfo();
    }

}
