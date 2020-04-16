package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Silverhand", null,  lastVisit = Date(), isOnline = true)

        println("$user $user2 $user3")
    }

    @Test
    fun test_factory() {
        var user = User.makeUser("John Cena")
        var user2 = User.makeUser("John John")
        var user3 = User.makeUser("John Wick")
        print(user3)
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user3 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent())
    }

    @Test
    fun data_mapping() {
        val user = User.makeUser("Женя Данилей")
        val newUser = user.copy(lastVisit = Date().add(-7, TimeUnits.HOUR))
        //println(user)
        val userView = newUser.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Женя Данилей")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }
}
