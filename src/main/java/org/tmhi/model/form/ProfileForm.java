package org.tmhi.model.form;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:       Hiei
 * Date:         2018/06/10
 * Description:  用户信息页面参数
 * Modified By:
 */
@Data
public class ProfileForm implements Serializable {

    /** 用户名 */
    private String userName;
    /** 用户邮箱 */
    private String mailAddress;
    /** 用户头像 */
    private String userAvatar;
    /** 当前状态 */
    private String nowAction;
}
