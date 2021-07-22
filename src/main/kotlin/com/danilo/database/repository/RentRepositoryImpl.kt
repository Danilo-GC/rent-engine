package com.danilo.database.repository

import com.danilo.core.ports.RentRepositoryPort
import com.danilo.database.entity.RentEntity
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import java.util.UUID
import javax.inject.Singleton

@Singleton
class RentRepositoryImp(private val cqlSession: CqlSession) : RentRepositoryPort {

    override fun findById(id: UUID): RentEntity? {
        val selectResult = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM rent.rent WHERE id = ?",
                    id
                )
        )
        return selectResult
            .map { rent ->
                RentEntity(
                    rent.getUuid("id")!!,
                    rent.getBigDecimal("price")!!,
                    rent.getString("vehicle")!!,
                    rent.getString("brand")!!
                )
            }.firstOrNull()
    }

    override fun findAll(): List<RentEntity> {
        val selectResult = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM rent.rent"
                )
        )
        return selectResult
            .map { rent ->
                RentEntity(
                    rent.getUuid("id")!!,
                    rent.getBigDecimal("price")!!,
                    rent.getString("vehicle")!!,
                    rent.getString("brand")!!
                )
            }.toList()
    }

}