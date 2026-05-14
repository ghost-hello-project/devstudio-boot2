package com.laolang.devstudio.framework.web.advice;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.laolang.devstudio.framework.common.domain.R;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    final ObjectMapper objectMapper;

    List<String> ignoreWrapperUrls;

    @PostConstruct
    public void postConstruct() {
        ignoreWrapperUrls = Lists.newArrayList("/v3", "/doc.html");
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        if (inIgnoreWrapperUrls(request)) {
            return body;
        }

        if (body instanceof String) {
            String json = objectMapper.writeValueAsString(R.ok(body));
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return json;
        }

        if (body instanceof R) {
            return body;
        }

        return R.ok(body);
    }

    boolean inIgnoreWrapperUrls(ServerHttpRequest request) {
        if (CollUtil.isEmpty(ignoreWrapperUrls)) {
            return false;
        }

        String uri = request.getURI().getPath();

        return ignoreWrapperUrls.stream().anyMatch(s -> StringUtils.startsWith(uri, s));
    }

}