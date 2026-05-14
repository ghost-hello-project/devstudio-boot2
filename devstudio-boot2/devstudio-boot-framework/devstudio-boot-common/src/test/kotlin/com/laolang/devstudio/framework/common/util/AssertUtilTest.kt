package com.laolang.devstudio.framework.common.util

import com.laolang.devstudio.framework.common.exception.BusinessException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import io.mockk.confirmVerified

class AssertUtilTest : ShouldSpec({

    should("当对象不为空时，nonNull 不应抛出异常") {
        val obj = "I am not null"
        // 直接调用，不抛出异常即代表通过
        AssertUtil.nonNull(obj, "should not happen")
    }

    should("当对象为空时，nonNull 应该抛出 BusinessException") {
        val message = "对象不能为空"
        val exception = shouldThrow<BusinessException> {
            AssertUtil.nonNull(null, message)
        }

        // 校验异常信息
        exception.msg shouldBe message
    }

    should("当对象为空时，应该执行 logRunnable 并抛出异常") {
        // 使用 MockK 创建一个模拟的回调
        val logRunnable = mockk<Runnable>(relaxed = true)
        val message = "error message"

        shouldThrow<BusinessException> {
            AssertUtil.nonNull(null, message, logRunnable)
        }

        // 验证 logRunnable 的 run() 方法被调用了一次
        verify(exactly = 1) { logRunnable.run() }
        confirmVerified(logRunnable)
    }

    should("当对象不为空时，不应执行 logRunnable") {
        val logRunnable = mockk<Runnable>(relaxed = true)

        AssertUtil.nonNull("data", "message", logRunnable)

        // 验证 run() 没被调用
        verify(exactly = 0) { logRunnable.run() }
    }
})