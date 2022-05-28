package com.minseoklim.jooqpractice

import com.minseoklim.jooqpractice.tables.references.AUTHOR
import com.minseoklim.jooqpractice.tables.references.AUTHOR_BOOK
import com.minseoklim.jooqpractice.tables.references.BOOK
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataAccessException
import org.springframework.test.annotation.DirtiesContext

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class JooqDslTest {

    @Autowired
    private lateinit var dsl: DSLContext

    @Test
    fun insert() {
        dsl.insertInto(AUTHOR)
            .set(AUTHOR.ID, 4)
            .set(AUTHOR.FIRST_NAME, "minseok")
            .set(AUTHOR.LAST_NAME, "lim")
            .execute()
        dsl.insertInto(BOOK)
            .set(BOOK.ID, 4)
            .set(BOOK.TITLE, "A Beginner's Guide")
            .execute()
        dsl.insertInto(AUTHOR_BOOK)
            .set(AUTHOR_BOOK.AUTHOR_ID, 4)
            .set(AUTHOR_BOOK.BOOK_ID, 4)
            .execute()

        val result = dsl.select(AUTHOR.ID, AUTHOR.LAST_NAME, DSL.count())
            .from(AUTHOR)
            .join(AUTHOR_BOOK)
            .on(AUTHOR.ID.equal(AUTHOR_BOOK.AUTHOR_ID))
            .join(BOOK)
            .on(AUTHOR_BOOK.BOOK_ID.equal(BOOK.ID))
            .groupBy(AUTHOR.ID, AUTHOR.LAST_NAME)
            .fetch()

        assertThat(result).isNotEmpty
    }

    @Test
    fun givenInvalidData_whenInserting_thenFail() {
        assertThatThrownBy {
            dsl.insertInto(AUTHOR_BOOK)
                .set(AUTHOR_BOOK.AUTHOR_ID, 2)
                .set(AUTHOR_BOOK.BOOK_ID, 5)
                .execute()
        }.isInstanceOf(DataAccessException::class.java)
    }

    @Test
    fun update() {
        dsl.update(AUTHOR)
            .set(AUTHOR.LAST_NAME, "Baeldung")
            .where(AUTHOR.ID.equal(3))
            .execute()
        dsl.update(BOOK)
            .set(BOOK.TITLE, "Building your REST API with Spring")
            .where(BOOK.ID.equal(3))
            .execute()
        dsl.insertInto(AUTHOR_BOOK)
            .set(AUTHOR_BOOK.AUTHOR_ID, 3)
            .set(AUTHOR_BOOK.BOOK_ID, 3)
            .execute()

        val result = dsl.select(AUTHOR.ID, AUTHOR.LAST_NAME, BOOK.TITLE)
            .from(AUTHOR)
            .join(AUTHOR_BOOK)
            .on(AUTHOR.ID.equal(AUTHOR_BOOK.AUTHOR_ID))
            .join(BOOK)
            .on(AUTHOR_BOOK.BOOK_ID.equal(BOOK.ID))
            .where(BOOK.ID.equal(3))
            .fetch()

        assertThat(result).isNotEmpty
    }

    @Test
    fun givenInvalidData_whenUpdating_thenFail() {
        assertThatThrownBy {
            dsl.update(AUTHOR_BOOK)
                .set(AUTHOR_BOOK.AUTHOR_ID, 3)
                .set(AUTHOR_BOOK.BOOK_ID, 5)
                .where(AUTHOR_BOOK.AUTHOR_ID.equal(2))
                .execute()
        }.isInstanceOf(DataAccessException::class.java)
    }

    @Test
    fun delete() {
        dsl.delete(AUTHOR)
            .where(AUTHOR.ID.lt(100))
            .execute()

        val result = dsl.select(AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
            .from(AUTHOR)
            .fetch()

        assertThat(result).isEmpty()
    }

    @Test
    fun givenInvalidData_whenDeleting_thenFail() {
        assertThatThrownBy {
            dsl.delete(BOOK)
                .where(BOOK.ID.equal(1))
                .execute()
        }.isInstanceOf(DataAccessException::class.java)
    }
}
