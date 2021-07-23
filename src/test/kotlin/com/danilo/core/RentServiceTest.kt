package com.danilo.core

import com.danilo.core.model.Rent
import com.danilo.core.ports.RentRepositoryPort
import com.danilo.core.service.RentService
import com.danilo.database.entity.RentEntity
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentServiceTest : AnnotationSpec() {

    val rentRepositoryPort = mockk<RentRepositoryPort>()
    val rentService = RentService(rentRepositoryPort)

    lateinit var rent: Rent
    lateinit var listOfRent: List<Rent>
    lateinit var rentEntity: RentEntity
    lateinit var listOfRentEntity: List<RentEntity>

    @BeforeEach
    fun setUp() {
        rent = Rent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEntity = RentEntity(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")

        listOfRent = listOf(rent)
        listOfRentEntity = listOf(rentEntity)
    }

    @Test
    fun `should return a list of rent with success`() {
        every {
            rentRepositoryPort.findAll()
        } returns listOfRentEntity

        val result = rentService.findRentList()
        result shouldBe listOfRent
    }

    @Test
    fun `should return rent by id`() {
        every {
            rentRepositoryPort.findById(any())!!
        } returns rentEntity

        val result = rentService.findByRent(rent.id!!)
        result shouldBe rent
    }
}