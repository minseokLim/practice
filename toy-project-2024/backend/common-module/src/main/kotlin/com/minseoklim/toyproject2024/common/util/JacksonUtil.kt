package com.minseoklim.toyproject2024.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object JacksonUtil {
    private val objectMapper: ObjectMapper = ObjectMapper()
        .findAndRegisterModules()
        .registerKotlinModule()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .registerModules(
            JavaTimeModule()
                .addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME))
                .addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
                .addSerializer(LocalDate::class.java, LocalDateSerializer(DateTimeFormatter.ISO_DATE))
                .addDeserializer(LocalDate::class.java, LocalDateDeserializer(DateTimeFormatter.ISO_DATE))
                .addSerializer(LocalTime::class.java, LocalTimeSerializer(DateTimeFormatter.ISO_TIME))
                .addDeserializer(LocalTime::class.java, LocalTimeDeserializer(DateTimeFormatter.ISO_TIME))
        )

    val objectReader: ObjectReader = objectMapper.reader()
    val objectWriter: ObjectWriter = objectMapper.writer()
}
