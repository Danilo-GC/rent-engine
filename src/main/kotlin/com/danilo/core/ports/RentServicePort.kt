package com.danilo.core.ports

import com.danilo.core.model.Rent
import java.util.UUID
import javax.inject.Singleton

@Singleton
interface RentServicePort {
    fun findByRent(id: UUID): Rent
    fun findRentList(): List<Rent>
}