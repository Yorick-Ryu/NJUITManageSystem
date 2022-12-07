package dao

import entity.User
import util.SplitPage

interface UserDao {
    fun addUser(user: User): Boolean
    fun update(user: User): Boolean
    fun delUser(id: Int): Boolean
    fun queryAllUsers(): List<User>
    fun queryUsers(sp: SplitPage): List<User>
    fun queryUser(userName: String, pwd: String): List<User>
    fun queryUserById(id: Int): List<User>
    fun queryUserByLike(dept: String, name: String): List<User>
    fun queryUserByLike(dept: String, name: String, sp: SplitPage): List<User>
}