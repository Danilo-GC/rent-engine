package com.danilo.core.ports

import com.danilo.database.entity.RentEntity
import java.util.UUID
import javax.inject.Singleton

@Singleton
interface RentRepositoryPort {
    fun findById(id: UUID): RentEntity?
    fun findAll(): List<RentEntity>
}