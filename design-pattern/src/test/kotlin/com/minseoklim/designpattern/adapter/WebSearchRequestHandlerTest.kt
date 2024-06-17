package com.minseoklim.designpattern.adapter

import org.junit.jupiter.api.Test

class WebSearchRequestHandlerTest {

    @Test
    fun handleSearchRequest() {
        // given
        val db = setOf("apple", "banana", "cherry")
        val webSearchRequestHandler1 = WebSearchRequestHandler(DBSearchService(db))

        // when
        val result1 = webSearchRequestHandler1.handleSearchRequest("a")

        // then
        assert(result1.contents.contains("apple"))
        assert(result1.contents.contains("banana"))
        assert(!result1.contents.contains("cherry"))

        // given
        val tolr = setOf("apple", "banana", "cherry")
        val webSearchRequestHandler2 = WebSearchRequestHandler(SearchServiceTolrAdapter(tolr))

        // when
        val result2 = webSearchRequestHandler2.handleSearchRequest("a")

        // then
        assert(result2.contents.contains("apple"))
        assert(result2.contents.contains("banana"))
        assert(!result2.contents.contains("cherry"))
    }
}
