package jdbclearning.jdbc3.pojo;

import java.util.Date;

/**
 * XXXX
 *
 * @author tc
 * @date 2021/1/27
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Date birthDay;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthDay=" + birthDay +
                '}';
    }
}
