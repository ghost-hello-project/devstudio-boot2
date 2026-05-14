package com.laolang.devstudio.framework.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laolang.devstudio.framework.common.bizcode.CommonBizCode;
import com.laolang.devstudio.framework.common.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 统一响应结构
 *
 * @param <T> 接口返回类型
 */
@Accessors(chain = true)
@Data
public class R<T> {

    /**
     * 接口请求结果的业务状态吗.
     */
    private String code;

    /**
     * 判断接口请求是否成功的唯一标识.
     */
    private Boolean success;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 数据体.
     */
    private T body;

    /**
     * 扩充字段,正常情况下此字段为空，当此字段有值时，意味着当前接口结构不稳定，以后会修改,即保持 extra 为空.
     */
    private Map<String, Object> extra;

    /**
     * traceId
     */
    private String tid;

    /**
     * 接口返回时间
     */
    private LocalDateTime time;

    public static <T> R<T> build(String code, boolean success, String msg, T body) {
        R<T> ajax = new R<>();
        ajax.setCode(code);
        ajax.setSuccess(success);
        ajax.setMsg(msg);
        ajax.setBody(body);
        ajax.setExtra(null);
        ajax.setTid(getTraceId());
        ajax.time = LocalDateTime.now();

        return ajax;
    }

    private static String getTraceId() {
        String tl = MDC.get("tl");
        if (StringUtils.isBlank(tl)) {
            return "";
        }

        tl = StringUtils.replace(tl, "<", "");
        tl = StringUtils.replace(tl, ">", "");
        return tl;
    }

    @JsonIgnore
    public void setPropFromBusinessException(BusinessException e) {
        setMsg(e.getMsg());
        setCode(e.getCode());
        setSuccess(false);
    }

    public static <T> R<T> ok() {
        return ok(CommonBizCode.OK.getCode(), CommonBizCode.OK.getMsg());
    }

    public static <T> R<T> ok(String code, String msg) {
        return ok(code, msg, null);
    }

    public static <T> R<T> ok(String code, String msg, T body) {
        return build(code, true, msg, body);
    }

    public static <T> R<T> ok(T body) {
        return build(CommonBizCode.OK.getCode(), true, CommonBizCode.OK.getMsg(), body);
    }

    public static <T> R<T> failed() {
        return failed(CommonBizCode.FAILED.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return build(CommonBizCode.FAILED.getCode(), false, msg, null);
    }

    public static <T> R<T> error() {
        return error(CommonBizCode.ERROR.getMsg());
    }

    public static <T> R<T> error(String msg) {
        return error(CommonBizCode.ERROR.getCode(), msg);
    }

    public static <T> R<T> error(String code, String msg) {
        return build(code, false, msg, null);
    }

    public static <T> R<T> notFound() {
        return notFound(CommonBizCode.NOT_FOUND.getMsg());
    }

    public static <T> R<T> notFound(String msg) {
        return build(CommonBizCode.NOT_FOUND.getCode(), false, msg, null);
    }

    public static <T> R<T> unauthorized() {
        return build(CommonBizCode.UNAUTHORIZED.getCode(), false, CommonBizCode.UNAUTHORIZED.getMsg(), null);
    }

}
