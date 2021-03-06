package com.kish2.hermitcrabapi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kish2.hermitcrabapi.enums.user.Grade;
import com.kish2.hermitcrabapi.enums.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBindInfo {
    /*
    JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.

    TABLE：使用一个特定的数据库表格来保存主键。
    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
    IDENTITY：主键由数据库自动生成（主要是自动增长型）
    AUTO：主键由程序控制。*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableId(value = "uid", type = IdType.INPUT)
    private long uid;

    @Column(length = 11, unique = true)
    private String mobile;

    @Column(length = 30, unique = true)
    private String email;

    private Grade grade;

    private UserType userType;

    private String department;

    /* 学号应该不会超过64位，节省空间 */
    @Column(length = 64, unique = true)
    private String studentId;
}
