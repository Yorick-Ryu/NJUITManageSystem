package service.impl

import dao.UserDao
import dao.impl.UserDaoImpl
import entity.User
import service.UserService

class UserServiceImpl : UserService {
    private val userDao: UserDao = UserDaoImpl()
    override fun login(userName: String, pwd: String): User {
        println("开始校验")
        if ("" == userName) {
            println("用户名不为空")
        }
        if ("" == pwd) {
            println("密码不为空")
        }
        val user: User = userDao.queryUser(userName, pwd)
        println("结束")
        return user
    }
}