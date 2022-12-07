package service

import org.junit.jupiter.api.Test
import service.impl.UserServiceImpl
import util.SplitPage

class TestUserService {

    private val userService: UserService = UserServiceImpl()

    @Test
    fun testLogin() {
        println(userService.login("admin", "123"))
    }

    @Test
    fun testQueryUserById() {
        println(userService.queryUserByID(1))
    }

    @Test
    fun testGetRows(){
        println(userService.getRows("",""))
    }

    @Test
    fun testQueryUsers() {
        val sp = SplitPage()
        sp.setTotalRows(userService.getRows("",""))
        println(userService.queryUsers(sp))
    }
}