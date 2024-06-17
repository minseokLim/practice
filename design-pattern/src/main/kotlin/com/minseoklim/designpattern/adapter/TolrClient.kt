package com.minseoklim.designpattern.adapter

class TolrClient(
    private val tolr: Set<String>
) {
    fun query(query: TolrQuery): TolrResponse {
        return TolrResponse(tolr.filter { it.contains(query.keyword) }.toSet())
    }
}
