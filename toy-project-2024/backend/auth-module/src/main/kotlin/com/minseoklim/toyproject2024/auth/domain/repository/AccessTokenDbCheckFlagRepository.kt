package com.minseoklim.toyproject2024.auth.domain.repository

import com.minseoklim.toyproject2024.auth.domain.model.AccessTokenDbCheckFlag
import org.springframework.data.repository.CrudRepository

interface AccessTokenDbCheckFlagRepository : CrudRepository<AccessTokenDbCheckFlag, Int>
