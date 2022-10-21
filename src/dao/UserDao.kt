package dao

import entity.User
import java.sql.SQLException

fun main() {
    val user = User().apply {
        userName = "lucy"
        pwd = "123321"
    }
//    UserDao().addUser(user)
//    UserDao().delUser(5)
//    UserDao().update(user)
    val newUser = UserDao().queryUser("lucy", "123321")
    println(newUser)

    val list = UserDao().queryUsers()
    println(list)
}

class UserDao : BaseDao() {
    fun addUser(user: User): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "insert into user(userName,pwd)values(?,?)"
            val count = conn?.prepareStatement(sql)?.run {
                setString(1, user.userName)
                setString(2, user.pwd)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    fun update(user: User): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "update user set pwd = ? where userName = ?"
            val count = conn?.prepareStatement(sql)?.run {
                setString(1, user.pwd)
                setString(2, user.userName)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    fun delUser(id: Int): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "delete from user where id = ?"
            val count = conn?.prepareStatement(sql)?.run {
                setInt(1, id)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    fun queryUsers(): List<User> {
        val userList = mutableListOf<User>()
        try {
            val conn = super.getConnection()
            val sql = "select * from user"
            val rs = conn?.prepareStatement(sql)?.run {
                executeQuery(sql)
            }
            if (rs != null) {
                while (rs.next()) {
                    val user = User().apply {
                        id = rs.getInt("id")
                        userName = rs.getString("userName")
                        pwd = rs.getString("pwd")
                    }
                    userList += user
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return userList
    }

    fun queryUser(userName: String, pwd: String): User {
        var user = User()
        try {
            val conn = super.getConnection()
            val sql = "SELECT * FROM `USER` WHERE `userName` = '$userName' AND `pwd` = '$pwd' "
            println(sql)
            val rs = conn?.prepareStatement(sql)?.run {
                executeQuery(sql)
            }
            if (rs != null) {
                if (rs.next()) {
                    user = User().apply {
                        id = rs.getInt("id")
                        this.userName = rs.getString("userName")
                        this.pwd = rs.getString("pwd")
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return user
    }
}