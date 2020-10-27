package com.crm.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.crm.cn.valid.anntation.SexValues;
import com.crm.cn.valid.group.AddGroup;
import com.crm.cn.valid.group.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @Null(groups = {AddGroup.class},message = "添加时ID必须为空")
    @NotNull(groups = {UpdateGroup.class},message = "修改时id不能为空")
    private Long userId;

    /**
     * 用户账号   数字字母下划线组成  以字母开头  长度6-8
     */
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_]{5,7}",message = "用户账号 数字字母下划线组成  以字母开头  长度6-8",groups = {AddGroup.class,UpdateGroup.class})
    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String userName;

    /**
     * 用户昵称
     */
    @NotBlank(message = "你丫的没有给值",groups = {AddGroup.class,UpdateGroup.class})
    private String nickName;

    /**
     * 用户邮箱
     */
    @Email(message = "你输入的必须是个邮箱类型",groups = {AddGroup.class,UpdateGroup.class})
    @NotBlank(message = "邮箱不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String email;

    /**
     * 手机号码
     */

    @Pattern(regexp = "[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}",message = "输入的必须是手机号",groups = {AddGroup.class,UpdateGroup.class})
    @NotBlank(message = "手机号不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @SexValues(values = {"0","1"},message = "只能传0或1",groups = {AddGroup.class,UpdateGroup.class})
    private String sex;

    /**
     * 头像地址
     */
    @URL(groups = {AddGroup.class,UpdateGroup.class},message = "请传递头像有效地址")
    @NotBlank(message = "请上传文件",groups = {AddGroup.class,UpdateGroup.class})
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（1正常 0停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户的角色id
     */
    private transient String roleIds;


}
