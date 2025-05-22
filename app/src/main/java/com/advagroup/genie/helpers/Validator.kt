package com.advagroup.genie.helpers

class Validator {
    companion object {

        @Volatile
        private var INSTANCE: Validator? = null

        fun getInstance(): Validator {
            var instance = INSTANCE

            synchronized(this) {
                if(instance == null) {
                    instance = Validator()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

    fun isStringEmpty(string: String) : Boolean {
        return string.isEmpty()
    }

    fun isPasswordSame(password: String, confirmPassword: String) : Boolean {
        return password == confirmPassword
    }

    fun textCount(password: String, characterCount: Int): Boolean {
        return password.length >= characterCount
    }
}