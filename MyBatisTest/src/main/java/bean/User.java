package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String  name;
    private Integer age;
    private String sex;
    private String role;
    private String account;

    public User(String name, Integer age, String sex) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
