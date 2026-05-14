package com.laolang.devstudio.framework.common.util.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laolang.devstudio.framework.common.exception.BusinessException;
import com.laolang.devstudio.framework.common.util.jackson.module.BigDecimalModule;
import com.laolang.devstudio.framework.common.util.jackson.module.Jdk8TimeModule;
import com.laolang.devstudio.framework.common.util.jackson.module.LongModule;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.exception.ExceptionUtils;

@UtilityClass
public class JsonUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModules(new BigDecimalModule(), new LongModule(), new Jdk8TimeModule());
    }

    public static String toJson(Object o) {
        return toJson(o, false);
    }

    public static String toJson(Object o, boolean beautiful) {
        if (Objects.isNull(o)) {
            return null;
        }
        try {
            return beautiful ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o)
                    : mapper.writeValueAsString(o);
        }
        catch (JsonProcessingException e) {
            throw new BusinessException(ExceptionUtils.getMessage(e));
        }
    }

}
