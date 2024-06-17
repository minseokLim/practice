package com.minseoklim.designpattern.adapter

class WebSearchRequestHandler(
    private val searchService: SearchService
) {
    fun handleSearchRequest(keyword: String): SearchResult {
        return searchService.search(keyword)
    }
}
