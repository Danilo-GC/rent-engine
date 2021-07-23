package com.danilo.core.mapper

import com.danilo.core.model.Rent
import com.danilo.database.entity.RentEntity
import com.danilo.entrypoint.dto.RentDto
import kotlin.streams.toList

class RentConverter {
    companion object {
        fun rentEntityToRent(rentEntity: RentEntity) =
            Rent(rentEntity.id, rentEntity.price, rentEntity.vehicle, rentEntity.brand)

        fun rentEntityListToRentList(rentEntity: List<RentEntity>) =
            rentEntity.stream()
                .map { rent ->
                    Rent(rent.id, rent.price, rent.vehicle, rent.brand)
                }.toList()


        fun rentEntityToRentDto(rentEntity: RentEntity) =
            RentDto(rentEntity.id, rentEntity.price, rentEntity.vehicle, rentEntity.brand)


        fun rentEntityListToRentDtoList(rentEntityList: List<RentEntity>): List<RentDto> =
            rentEntityList.stream()
                .map { rent ->
                    RentDto(rent.id, rent.price, rent.vehicle, rent.brand)
                }.toList()

    }
}