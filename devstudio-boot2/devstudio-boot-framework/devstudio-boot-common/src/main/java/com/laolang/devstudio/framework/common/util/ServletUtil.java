package com.laolang.devstudio.framework.common.util;

import com.laolang.devstudio.framework.common.exception.BusinessException;
import javax.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.exception.ExceptionUtils;

@UtilityClass
public class ServletUtil {

    public static void renderJsonString(HttpServletResponse response, String json, Integer code) {
        try {
            response.setStatus(code);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
            response.getWriter().flush();
        }
        catch (Exception e) {
            throw new BusinessException(ExceptionUtils.getMessage(e));
        }
    }

}
