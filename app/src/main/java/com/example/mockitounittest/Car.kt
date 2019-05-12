package com.example.mockitounittest

class Car {
    var direction: Direction = Direction.NORTH

    fun drive(direction: Direction) {
        this.direction = direction
    }

    fun toSouth(direction: Direction) {
        this.direction = direction
    }
}

enum class Direction {
    NORTH, SOUTH
}