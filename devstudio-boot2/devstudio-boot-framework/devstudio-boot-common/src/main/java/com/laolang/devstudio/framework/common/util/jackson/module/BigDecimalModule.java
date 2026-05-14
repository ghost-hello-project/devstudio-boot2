package com.laolang.devstudio.framework.common.util.jackson.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * jackson BigDecimal 序列化
 */
public class BigDecimalModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public BigDecimalModule() {
        super();
        addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                DecimalFormat format = new DecimalFormat("0.00");
                gen.writeString(format.format(value));
            }
        });
    }

}