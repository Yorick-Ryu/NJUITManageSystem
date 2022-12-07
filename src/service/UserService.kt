package service

import entity.User
import util.SplitPage

interface UserService {
    // 登录
    fun login(userName: String, pwd: String): User

    // 增加用户
    fun addUser(user: User): Boolean

    // 修改用户信息
    fun updateUser(user: User): Boolean

    // 删除用户
    fun delUser(id: Int): Boolean

    // 根据ID查询用户
    fun queryUserByID(id: Int): User

    // 查询全部用户
    fun queryAllUser(): List<User>

    // 查询用户 数据分页
    fun queryUsers(sp: SplitPage): List<User>

    // 模糊查询，获取全部，实际用来查询用户数量
    fun queryUserByLike(dept: String, name: String): List<User>

    // 模糊查询，数据分页
    fun queryUserByLike(dept: String, name: String, sp: SplitPage): List<User>

    // 查询用户数量
    fun getRows(dept: String?, name: String?): Int

}