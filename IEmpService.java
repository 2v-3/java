package cn.rrl.mvc1.service;

import cn.rrl.mvc1.vo.Emp;

import java.util.Map;

public interface IEmpService {

    /**
     * 实现数据的增加，调用dao层的insert（）方法
     * @param emp 包含了要增加的数据的vo对象
     * @return 成功返回true,否则返回false
     * @throws Exception
     */
    boolean addEmp(Emp emp) throws Exception;
    /**
     *实现数据的修改，根据编号进行修改数据
     * @param emp 保存了要修改的数据的对象
     * @return
     * @throws Exception
     */
    boolean editEmp(Emp emp) throws Exception;

    /**
     * 删除数据，根据编号删除数据
     * @param id 保存了要删除的数据的编号
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    boolean removeEmpByid(Integer id)throws  Exception;

    /**
     * 实现根据编号查询数据
     * @param id 要查询数据的编号
     * @return 如果有数据返回对象，否则返回null
     * @throws Exception
     */
    Emp findEmpByid(Integer id) throws  Exception;

    /**
     * 实现模糊分页查询，调用dao层的，selectSplitAll() 和 selectCount();
     * @param kw 模糊查询的关键值
     * @param cp 当前页
     * @param ls 每页显示的数据量
     * @return
     * @throws Exception
     */
    Map<String,Object> findAllSplit(String kw,Integer cp,Integer ls) throws  Exception;
}
