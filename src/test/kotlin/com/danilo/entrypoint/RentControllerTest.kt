package com.danilo.entrypoint

import com.danilo.core.ports.RentRepositoryPort
import com.danilo.database.entity.RentEntity
import com.danilo.entrypoint.controller.RentController
import com.danilo.entrypoint.dto.RentDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentControllerTest : AnnotationSpec() {

    val rentRepositoryPort = mockk<RentRepositoryPort>()
    val rentController = RentController(rentRepositoryPort)

    lateinit var rentDto: RentDto
    lateinit var listOfRentDto: List<RentDto>
    lateinit var rentEntity: RentEntity
    lateinit var listOfRentEntity: List<RentEntity>

    @BeforeEach
    fun setUp() {
        rentDto = RentDto(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEntity = RentEntity(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")

        listOfRentDto = listOf(rentDto)
        listOfRentEntity = listOf(rentEntity)
    }

    @Test
    fun `should find all with success`() {
        every {
            rentRepositoryPort.findAll()
        } returns listOfRentEntity

        val result = rentController.findAll()
        result.status shouldBe HttpStatus.OK
    }

    @Test
    fun `should find by id with success`() {
        every {
            rentRepositoryPort.findById(any())
        } returns rentEntity

        val result =
            rentController.findById(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"))
        result.status shouldBe HttpStatus.OK
    }
}
