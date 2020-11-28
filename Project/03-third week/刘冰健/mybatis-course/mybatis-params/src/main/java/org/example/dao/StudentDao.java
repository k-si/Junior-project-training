package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Student;
import org.example.vo.QueryParam;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    /*
    * 简单类型：mybatis把java的基本数据类型和String都叫简单类型
    *
    * 在mapper文件获取简单类型的一个参数值，使用#{任意字符}
    * */
    public Student selectStudentById(Integer id);
    /*
    * 多个参数：命名参数，在形参定义的前面加入@Param("自定义参数名称")
    * */
    List<Student> selectMultiParam(@Param("myname") String name,
                                  @Param("myage") Integer age);
    /*
    * 多个参数，使用java对象作为接口中的方法的参数
    * */
    List<Student> selectMultiObject(QueryParam param);

    /*
    * 多个参数按位置传值
    * mybatis3.4之前使用#{0}，#{1};
    * 之后使用#{arg0}，#{arg1}
    * */
    List<Student> selectMultiPosition(String name,Integer age);
    /*
    * Map传参
    * */
    List<Student> selectMutiByMap(Map<String,Object> map);

    /*
    * 使用resultMap定义映射关系
    * */
    List<Student> selectAllStudents();
}
