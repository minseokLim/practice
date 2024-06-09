package com.minseoklim.toyproject2024.common.ui

import com.minseoklim.toyproject2024.common.dto.ErrorResponse
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.common.exception.UnauthorizedException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WebControllerAdvice {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse.of(e), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(e: UnauthorizedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse.of(e), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(e: BadCredentialsException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(HttpStatus.UNAUTHORIZED.name), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        // 여기에서 Sentry와 같은 모니터링 시스템 호출
        logger.error(e.message, e)
        return ResponseEntity(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object : KLogging()
}