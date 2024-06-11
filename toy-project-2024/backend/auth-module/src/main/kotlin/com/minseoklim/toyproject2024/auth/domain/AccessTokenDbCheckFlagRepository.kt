package com.minseoklim.toyproject2024.auth.domain

import org.springframework.data.repository.CrudRepository

interface AccessTokenDbCheckFlagRepository : CrudRepository<AccessTokenDbCheckFlag, Int>
