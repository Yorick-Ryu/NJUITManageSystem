package service.impl

import dao.DeptDao
import dao.impl.DeptDaoImpl
import entity.Department
import service.DeptService

class DeptServiceImpl : DeptService {
    private val deptDao: DeptDao = DeptDaoImpl()
    override fun addDept(dept: Department): Boolean {
        return deptDao.addDept(dept)
    }

    override fun updateDept(dept: Department): Boolean {
        return deptDao.updateDept(dept)
    }

    override fun delDept(deptId: Int): Boolean {
        return deptDao.delDept(deptId)
    }

    override fun queryAllDept(): List<Department> {
        return deptDao.queryAllDept()
    }

    override fun queryDept(deptId: Int): Department {
        return deptDao.queryDept(deptId)
    }

}