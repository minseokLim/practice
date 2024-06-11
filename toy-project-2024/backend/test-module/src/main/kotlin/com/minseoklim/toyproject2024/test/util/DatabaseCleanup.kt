package com.minseoklim.toyproject2024.test.util

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

@Service
@ActiveProfiles("test")
class DatabaseCleanup(
    dataSource: DataSource
) {
    private val jdbcTemplate = NamedParameterJdbcTemplate(dataSource)

    @Transactional
    fun execute() {
        val tableNames = extractTableNames()

        execute("SET REFERENTIAL_INTEGRITY FALSE")
        tableNames.forEach {
            execute("TRUNCATE TABLE $it")
        }
        execute("SET REFERENTIAL_INTEGRITY TRUE")
    }

    private fun extractTableNames(): List<String> {
        return jdbcTemplate.query("SHOW TABLES") { resultSet, _ -> resultSet.getString(1) }
    }

    private fun execute(query: String) {
        jdbcTemplate.update(query, emptyMap<String, Any>())
    }
}
