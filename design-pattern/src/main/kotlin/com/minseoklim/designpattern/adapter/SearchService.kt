package com.minseoklim.designpattern.adapter

interface SearchService {
    fun search(keyword: String): SearchResult
}
