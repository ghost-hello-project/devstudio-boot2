package com.laolang.devstudio.module.system.domain.res;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典分组信息
 */
@Accessors(chain = true)
@Data
public class SysDictTypeGroupInfoRes {

    /**
     * 字典分组编码
     */
    private String groupCode;

    /**
     * 字典分组名称
     */
    private String groupName;

}
