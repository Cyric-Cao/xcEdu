package com.xuecheng.test.freemarker.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Student {
    private String name;//姓名
    private int age;//年龄
    private Date birthday;//生日
    private Float money;//钱包
    private List<Student> friends;//朋友列表
    private Student bestFriend;//最好的朋友

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Float getMoney() {
        return money;
    }

    public List<Student> getFriends() {
        return friends;
    }

    public Student getBestFriend() {
        return bestFriend;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public void setFriends(List<Student> friends) {
        this.friends = friends;
    }

    public void setBestFriend(Student bestFriend) {
        this.bestFriend = bestFriend;
    }
}
