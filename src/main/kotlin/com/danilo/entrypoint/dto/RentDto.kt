package com.danilo.entrypoint.dto

import java.math.BigDecimal
import java.util.UUID

data class RentDto(
    val id: UUID? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val vehicle: String? = "",
    val brand: String? = ""
    )
