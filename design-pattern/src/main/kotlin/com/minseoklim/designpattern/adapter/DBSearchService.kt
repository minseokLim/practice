package com.minseoklim.designpattern.adapter

class DBSearchService(
    private val db: Set<String>
) : SearchService {
    override fun search(keyword: String): SearchResult {
        return SearchResult(db.filter { it.contains(keyword) })
    }
}
