package com.danilo.core.service

import com.danilo.core.mapper.RentConverter
import com.danilo.core.model.Rent
import com.danilo.core.ports.RentRepositoryPort
import com.danilo.core.ports.RentServicePort
import java.util.UUID
import javax.inject.Singleton

@Singleton
class RentService(private val rentRepository: RentRepositoryPort) : RentServicePort {

    override fun findByRent(id: UUID): Rent =
        RentConverter.rentEntityToRent(rentRepository.findById(id)!!)


    override fun findRentList(): List<Rent> =
        RentConverter.rentEntityListToRentList(rentRepository.findAll())

}