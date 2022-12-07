package service.impl

import dao.UserDao
import dao.impl.UserDaoImpl
import entity.User
import service.UserService
import util.SplitPage

class UserServiceImpl : UserService {
    private val userDao: UserDao = UserDaoImpl()
    private var user: User = User()

    // 注册
    override fun login(userName: String, pwd: String): User {
        println("开始校验")
        if ("" == userName) {
            println("用户名不为空")
        }
        if ("" == pwd) {
            println("密码不为空")
        }
        val users = userDao.queryUser(userName, pwd)
        if (users.isNotEmpty()) {
            user = users[0]
        }
        return user
    }

    // 数据分页
    override fun queryUsers(sp: SplitPage): List<User> {
        return userDao.queryUsers(sp)
    }

    override fun queryUserByLike(dept: String, name: String): List<User> {
        return userDao.queryUserByLike(dept, name)
    }

    override fun queryUserByLike(dept: String, name: String, sp: SplitPage): List<User> {
        return userDao.queryUserByLike(dept, name, sp)
    }

    override fun addUser(user: User): Boolean {
        return userDao.addUser(user)
    }

    override fun updateUser(user: User): Boolean {
        return userDao.update(user)
    }

    override fun delUser(id: Int): Boolean {
        return userDao.delUser(id)
    }

    override fun queryUserByID(id: Int): User {
        user = User()
        val users = userDao.queryUserById(id)
        if (users.isNotEmpty()) {
            user = users[0]
        }
        return user
    }

    override fun queryAllUser(): List<User> {
        return userDao.queryAllUsers()
    }

    override fun getRows(dept: String?, name: String?): Int {
        return if (dept == null || dept == "" || name == null || name == "") userDao.queryAllUsers().size
        else queryUserByLike(dept, name).size
    }
}