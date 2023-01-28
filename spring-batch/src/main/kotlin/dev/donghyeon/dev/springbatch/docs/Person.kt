package dev.donghyeon.dev.springbatch.docs

class Person {
    var lastName: String? = null
    var firstName: String? = null

    constructor()
    constructor(firstName: String?, lastName: String?) {
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun toString(): String {
        return "firstName: $firstName, lastName: $lastName"
    }
}
