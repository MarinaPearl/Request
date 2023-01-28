package ru.demchuk.request.View

data class BIN(
    var number: Number,
    var scheme: String,
    var type: String,
    var brand: String,
    var prepaid: Boolean,
    var country : Country,
    var bank : Bank
)

data class Number(
    var length: Double,
    var luhn: Boolean
)

data class Country(
    var numeric: String,
    var alpha2: String,
    var name: String,
    var emoji: String,
    var currency: String,
    var latitude: Double,
    var longitude: Double
)

data class Bank(
    var name : String,
    var url : String,
    var phone : String,
    var city : String
)