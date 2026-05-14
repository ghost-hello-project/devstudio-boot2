package com.laolang.devstudio.framework.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("check")
    public String check() {
        // todo 构建后自动更新
        return "It's ok(003)!";
    }

}
