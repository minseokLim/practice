package com.minseoklim.designpattern.adapter

class SearchServiceTolrAdapter(
    tolr: Set<String>
) : SearchService {
    private val tolrClient = TolrClient(tolr)

    override fun search(keyword: String): SearchResult {
        val query = TolrQuery(keyword)
        val response = tolrClient.query(query)
        return SearchResult(response.contents.toList())
    }
}
