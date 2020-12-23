package com.kish2.hermitcrabapi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kish2.hermitcrabapi.enums.user.Gender;
import com.kish2.hermitcrabapi.enums.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*
    JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.

    TABLE：使用一个特定的数据库表格来保存主键。
    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
    IDENTITY：主键由数据库自动生成（主要是自动增长型）
    AUTO：主键由程序控制。*/

    /* 主键，自增，唯一*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "uid", type = IdType.AUTO)
    private long uid;

    /* token唯一 */
    @Column(unique = true)
    private String token;

    /* 为解决高并发下的插入问题，设置唯一性 */
    @Column(length = 64, unique = true)
    private String username;

    @Column(length = 64)
    private String password;

    private Gender gender;

    private Date regDate;

    private UserStatus userStatus;

    private String avatarPath;
}
