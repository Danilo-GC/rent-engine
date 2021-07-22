package com.danilo.entrypoint.controller

import com.danilo.core.mapper.RentConverter
import com.danilo.core.ports.RentRepositoryPort
import com.danilo.entrypoint.dto.RentDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.UUID

@Controller(value = "iupp/v1/rent")
class RentController(private val rentRepositoryPort: RentRepositoryPort) {

    @Get(value = "/{id}")
    fun findById(@PathVariable id: UUID): HttpResponse<RentDto> {
        return HttpResponse.ok(RentConverter.rentEntityToRentDto
            (rentRepositoryPort.findById(id)!!))
    }

    @Get
    fun findAll(): HttpResponse<List<RentDto>> {
        return HttpResponse.ok(RentConverter.rentEntityListToRentDtoList
            (rentRepositoryPort.findAll()))
    }
}