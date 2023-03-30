package wang.diff.user.server.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：user_server.t_user
*/
@Table(name = "user_server.t_user")
public class TUser {
    /**
     * pk
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别 0未知 1男 2女
     */
    private Integer sex;

    private BigDecimal balance;

    /**
     * 1启用 0禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 获取pk
     *
     * @return id - pk
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置pk
     *
     * @param id pk
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取电话
     *
     * @return mobile - 电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话
     *
     * @param mobile 电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别 0未知 1男 2女
     *
     * @return sex - 性别 0未知 1男 2女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 0未知 1男 2女
     *
     * @param sex 性别 0未知 1男 2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取1启用 0禁用
     *
     * @return status - 1启用 0禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1启用 0禁用
     *
     * @param status 1启用 0禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return updateTime - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取用户名
     *
     * @return userName - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}