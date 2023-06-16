package com.example.product_manager.config

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import java.util.*

val logger: Logger = LoggerFactory.getLogger(ServiceLogAspect::class.java)
@Aspect
@Configuration
@EnableAspectJAutoProxy
class ServiceLogAspect {
    @Around("@within(org.springframework.stereotype.Service)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        val threadId = Thread.currentThread().id
        val start = System.currentTimeMillis()
        val className = joinPoint.signature.declaringType.simpleName
        val methodName = joinPoint.signature.name
        val arguments = joinPoint.args.joinToString(", ")
        logger.info("[Thread ${threadId}]: @Service ${className}.${methodName}(${arguments}) called")
        val result = joinPoint.proceed()
        val end = System.currentTimeMillis()
        logger.info("[Thread ${threadId}]: @Service ${className}.${methodName} executed in ${end - start}ms with result: $result")
        return result
    }
}