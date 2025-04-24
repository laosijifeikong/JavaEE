package bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private int id;

    private String name;

    private int age;

    private String sex;

    private String role;

    private String account;

    public User() {
    }

    public User(int age, int id, String name, String role, String sex) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.role = role;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
