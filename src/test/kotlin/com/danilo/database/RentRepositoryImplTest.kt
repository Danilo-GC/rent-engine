package com.danilo.database

import com.danilo.database.entity.RentEntity
import com.danilo.database.repository.RentRepositoryImp
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentRepositoryImplTest : AnnotationSpec() {

    val cqlSession = mockk<CqlSession>(relaxed = true)
    val rentRepositoryImpl = RentRepositoryImp(cqlSession)

    lateinit var rentEntity: RentEntity
    lateinit var listOfRentEntity: List<RentEntity>

    @BeforeEach
    fun setUp() {
        rentEntity = RentEntity(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")

        listOfRentEntity = listOf(rentEntity)
    }

    @Test
    fun `should return list`() {
        val resultSet = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM rent.rent"
                )
        ).map { rent ->
            RentEntity(
                rent.getUuid("id")!!,
                rent.getBigDecimal("price")!!,
                rent.getString("vehicle")!!,
                rent.getString("brand")!!
            )
        }.toList()
        val result = rentRepositoryImpl.findAll()
        result shouldBe resultSet
    }

    @Test
    fun `should return by id`() {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM rent.rent WHERE id = ?",
                    UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6")
                )
        ).map { rent ->
            RentEntity(
                rent.getUuid("id")!!,
                rent.getBigDecimal("price")!!,
                rent.getString("vehicle")!!,
                rent.getString("brand")!!
            )
            val result = rentRepositoryImpl.findById(rentEntity.id!!)
            result shouldBe rentEntity
        }
    }
}
