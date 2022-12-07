package dao.impl

import dao.DeptDao
import entity.Department
import util.BaseDao
import java.sql.SQLException

// 增加部门
class DeptDaoImpl : DeptDao, BaseDao() {
    override fun addDept(dept: Department): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "insert into department(dept_name,dept_loc,dept_man)values(?,?,?)"
            val count = conn?.prepareStatement(sql)?.run {
                setString(1, dept.deptName)
                setString(2, dept.deptLoc)
                setInt(3, dept.deptMan)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    // 更新部门信息
    override fun updateDept(dept: Department): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql =
                "update department set dept_name = ? ,dept_loc = ? ,dept_man = ? where dept_id = ?"
            val count = conn?.prepareStatement(sql)?.run {
                setString(1, dept.deptName)
                setString(2, dept.deptLoc)
                setInt(3, dept.deptMan)
                setInt(4, dept.deptId)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    // 删除部门
    override fun delDept(deptID: Int): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "delete from department where dept_id = ?"
            val count = conn?.prepareStatement(sql)?.run {
                setInt(1, deptID)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    // 查询部门
    override fun queryDept(deptName: String): Department {
        var dept = Department()
        val sql = "SELECT * FROM `department` WHERE `dept_name` = '$deptName'"
        val depts = query(sql)
        if (depts.isNotEmpty()) {
            dept = depts[0]
        }
        return dept
    }

    override fun queryDept(deptID: Int): Department {
        var dept = Department()
        val sql = "SELECT * FROM `department` WHERE `dept_id` = '$deptID'"
        val depts = query(sql)
        if (depts.isNotEmpty()) {
            dept = depts[0]
        }
        return dept
    }

    // 查询所有部门
    override fun queryAllDept(): List<Department> {
        val sql = "select * from department"
        return query(sql)
    }

    private fun query(sql: String): List<Department> {
        val deptList = mutableListOf<Department>()
        try {
            val conn = super.getConnection()
            val rs = conn?.prepareStatement(sql)?.run {
                executeQuery(sql)
            }
            if (rs != null) {
                while (rs.next()) {
                    val dept = Department().apply {
                        this.deptId = rs.getInt("dept_id")
                        this.deptName = rs.getString("dept_name")
                        this.deptLoc = rs.getString("dept_loc")
                        this.deptMan = rs.getInt("dept_man")
                    }
                    deptList += dept
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return deptList
    }
}