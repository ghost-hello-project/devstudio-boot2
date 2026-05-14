package com.laolang.devstudio.framework.common.util;

import com.laolang.devstudio.framework.common.exception.BusinessException;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class AssertUtil {

    public static void nonNull(Object obj, String message) {
        nonNull(obj, message, null);
    }

    public static void nonNull(Object obj, String message, Runnable logRunnable) {
        if (Objects.nonNull(obj)) {
            return;
        }

        if (Objects.nonNull(logRunnable)) {
            logRunnable.run();
        }

        throw new BusinessException(message);
    }

}
