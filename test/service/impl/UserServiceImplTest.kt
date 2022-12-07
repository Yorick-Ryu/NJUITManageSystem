package service.impl

import entity.User
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import service.UserService
import util.SplitPage

internal class UserServiceImplTest {

    private val userService: UserService = UserServiceImpl()
    private val sp = SplitPage()
    private val user = User()

    @Test
    fun login() {
        println(userService.login("admin", "123"))
    }

    @Test
    fun queryUsers() {
        sp.setTotalRows(userService.getRows("", ""))
        println(userService.queryUsers(sp))
    }

    @Test
    fun queryUserByLike() {
        val dept = "1"
        val name = "四"
        sp.setTotalRows(userService.getRows(dept, name))
        println(userService.queryUserByLike(dept, name, sp))
    }

    @Test
    fun addUser() {
        user.apply {
            userName = "liuneng"
            pwd = "ln666"
            name = "刘能"
            dept = 2
        }
        println(userService.addUser(user))
    }

    @Test
    fun updateUser() {
        user.apply {
            userName = "liuneng"
            pwd = "lnnbclass"
            name = "刘能"
            dept = 3
        }
        println(userService.updateUser(user))
    }

    @Test
    fun delUser() {
    }

    @Test
    fun queryUserByID() {
        println(userService.queryUserByID(1))
    }

    @Test
    fun getRows() {
        println(userService.getRows("", ""))
    }
}