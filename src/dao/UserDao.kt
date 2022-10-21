package dao

import entity.User

interface UserDao {
    public abstract fun addUser(user: User): Boolean
    public abstract fun update(user: User): Boolean
    public abstract fun delUser(id: Int):Boolean
    public abstract fun queryUsers(): List<User>
    public abstract fun queryUser(userName: String, pwd: String): User
}