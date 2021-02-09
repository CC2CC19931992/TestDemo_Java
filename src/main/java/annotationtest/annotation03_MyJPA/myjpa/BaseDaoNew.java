package annotationtest.annotation03_MyJPA.myjpa;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/21
 */
public class BaseDaoNew<T> {
    private static BasicDataSource datasource = new BasicDataSource();

    //静态代码块,设置连接数据库的参数
    static{
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/test");
        datasource.setUsername("root");
        datasource.setPassword("123456");
    }

    //得到jdbcTemplate
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
    //泛型参数的Class对象
    private Class<T> beanClass;

    public BaseDaoNew() {
        //得到泛型参数的Class对象，假设是User.class
        beanClass = (Class) ((ParameterizedType) this.getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public void add(T bean) {
        //得到User对象的所有字段
        Field[] declaredFields = beanClass.getDeclaredFields();

        //拼接sql语句，【表名从User类Table注解中获取】
        String sql = "insert into "
                + beanClass.getAnnotation(Table.class).value()
                + " values(";
        for (int i = 0; i < declaredFields.length; i++) {
            sql += "?";
            if (i < declaredFields.length - 1) {
                sql += ",";
            }
        }
        sql += ")";

        //获得bean字段的值（要插入的记录）
        ArrayList<Object> paramList = new ArrayList<>();
        for (int i = 0; i < declaredFields.length; i++) {
            try {
                declaredFields[i].setAccessible(true);
                Object o = declaredFields[i].get(bean);
                paramList.add(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int size = paramList.size();
        Object[] params = paramList.toArray(new Object[size]);

        //传入sql语句模板和模板所需的参数，插入User
        int num = jdbcTemplate.update(sql, params);
        System.out.println(num);
    }
}
