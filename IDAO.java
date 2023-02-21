package cn.rrl.mvc1.Dao;

import java.io.Serializable;
import java.util.List;
//显示层-->控制层-->业务层-->数据访问层-->数据库
//定义泛型接口
public interface IDAO<V> {
    /**
     * 统计数据量
     * @param kw
     * @return
     */
    int  selectCount(String kw) throws Exception;
    /**
     * 模糊分页查询
     * @param kw
     * @param Start
     * @param ls
     * @return
     */
    List<V> selectsplitAll(String kw, int Start, int ls)throws Exception;
    /**
     * 根据编号查询数据
     * @param id 要查询的数据的编号
     * @return有数据返回对象，没数据返回null
     */
    V selectById(Serializable id)throws Exception;
    /**
     * 根据编号删除数据
     * @param id
     * @return
     */

    int  delectById(Serializable id)throws Exception;
    /**
     * 根据编号修改数据
     * @param
     * @return
     */
    int updateById(V v)throws Exception;
    /**
     * 插入数据
     * @param
     * @return
     */
    int insert(V v)throws Exception;
}


