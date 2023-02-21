package cn.rrl.mvc1.Dao.impl;

import cn.rrl.mvc.util.ConnetcionUtil;
import cn.rrl.mvc.vo.Emp;
import cn.rrl.mvc1.Dao.DAOAdapter;
import cn.rrl.mvc1.Dao.IEmpDao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoimpl extends DAOAdapter<Emp> implements IEmpDao {
    private Connection conn;
    //构造方法初始化连接

    public EmpDaoimpl(Connection conn) {
        this.conn =conn;
    }



    @Override
    public int selectCount(String kw) throws Exception{
        String sql = "SELECT COUNT(*) FROM emp WHERE LIKE ?";
        PreparedStatement pst = null;
        pst = this.conn.prepareStatement(sql);
        pst.setString(1, "%" + kw + "%");
        pst.executeUpdate();
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);


    }

    @Override
    public List<Emp> selectsplitAll(String kw, int Start, int ls) throws  Exception{
        //创建一个list集合
        ArrayList<Object> emps = new ArrayList<>();

        String sql = "SELECT empno,ename,job,sal,hiredate,mgr FROM emp WHERE ename LIKE ? LIMIT ?,?";
        PreparedStatement pst = null;


        pst = this.conn.prepareStatement(sql);
        pst.setString(1, "%" + kw + "%");
        pst.setInt(2, Start);
        pst.setInt(3, ls);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            //取得原始数据封装到vo中
            //public Emp(String empno, String ename, String job, String mgr, Date hiredate, Integer sal)
            emps.add(new Emp(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                    , rs.getDate(5), rs.getInt(6), rs.getInt(7)));
        }
    return  null;

    }

    @Override
    public Emp selectById(Serializable id) throws  Exception {
        String sql = "SELECT * FROM emp WHERE empno= ?";
        PreparedStatement pst = null;

            pst = this.conn.prepareStatement(sql);
            pst.setObject(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(("编号:" + rs.getString(1) + "\n" + "名字:" + rs.getString(2) + "\n" + "职位:" +
                        rs.getString(3) + "\n" + "领导编号:" + rs.getString(4) + "\n" + "入职日期:" + rs.getDate(5) + "\n" +
                        "薪水:" + rs.getInt(6)));
            }

        return null;
    }
    @Override
    public int delectById(Serializable id) throws  Exception{
        String sql = "DELETE FROM emp WHERE empno=?";
        PreparedStatement pst = null;

            pst = this.conn.prepareStatement(sql);
            pst.setObject(1, id);
            return pst.executeUpdate();

    }
    @Override

    public int updateById(Emp emp) throws  Exception{
        String sql = "UPDATE emp SET job=?,sal=?,deptno=? WHERE empno=?";
        PreparedStatement pst = null;
        pst = this.conn.prepareStatement(sql);
            pst.setString(1, emp.getJob());
            pst.setInt(2, emp.getSal());
            pst.setInt(3, emp.getDeptno());
            pst.setInt(4, emp.getEmpno());
            return pst.executeUpdate();





    }
    @Override

    public int insert(Emp emp) throws  Exception {
        String sql = "INSERT INTO emp (ename,job,sal) values(?,?,?)";
        PreparedStatement pst = null;

        //发送sql语句的时候明确指定需要自增长的主键值
        pst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, emp.getEname());
        pst.setString(2, emp.getJob());
        pst.setInt(3, emp.getSal());
        pst.executeUpdate();
        //取得自动增长的主键值
        ResultSet keys = pst.getGeneratedKeys();
        while (keys.next()) {
            System.out.println("员工编号为:" + keys.getInt(1) + "欢迎来到新星网络有限公司！");
        }


        return 0;
    }

}
