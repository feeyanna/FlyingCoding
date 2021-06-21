
package com.laifeiyang.dev.micro.common.constant;

/**
 * 常量
 */
public interface CommonConstant {

    /**
     * 默认页码为0
     */
    Integer DEFAULT_PAGE_INDEX = 0;

    /**
     * 默认页大小为10
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 登录用户
     */
    String LOGIN_SYS_USER = "loginSysUser";

    /**
     * 登陆token
     */
    String JWT_DEFAULT_TOKEN_NAME = "token";

    /**
     * JWT用户名
     */
    String JWT_USERNAME = "username";

    /**
     * JWT刷新新token响应状态码
     */
    int JWT_REFRESH_TOKEN_CODE = 460;

    /**
     * JWT刷新新token响应状态码，
     * Redis中不存在，但jwt未过期，不生成新的token，返回361状态码
     */
    int JWT_INVALID_TOKEN_CODE = 461;

    /**
     * JWT Token默认密钥
     */
    String JWT_DEFAULT_SECRET = "666666";

    /**
     * JWT 默认过期时间，3600L，单位秒
     */
    Long JWT_DEFAULT_EXPIRE_SECOND = 3600L;

    /**
     * 初始密码
     */
    String INIT_PWD = "123456";

    /**
     * 默认头像
     */
    String DEFAULT_HEAD_URL = "";

    /**
     * 管理员角色名称
     */
    String ADMIN_ROLE_NAME = "管理员";

    String ADMIN_LOGIN = "adminLogin";

    /**
     * 验证码token
     */
    String VERIFY_TOKEN = "verifyToken";

    /**
     * 图片
     */
    String IMAGE = "image";

    /**
     * JPEG
     */
    String JPEG = "JPEG";

    /**
     * base64前缀
     */
    String BASE64_PREFIX = "data:image/png;base64,";

    /*
    * clickhouse
    * */
    String CLICKHOUSE = "clickhouse";

    /*
     * clickhouse的数据状态:0(无数据更新),1(批次1数据完成),2(批次2数据完成)
     * 若批次3数据完成后续更改状态为0
     * */
    String DATA_STATUS = "dataStatus";

    /*
     * clickhouse表名三表循环
     * */
    String TABLENAME = "tableName";

    /*
     * clickhouse表名三表循环user_label_A
     * */
    String USER_LABEL_A = "USER_LABEL_A";

    /*
     * clickhouse表名三表循环user_label_B
     * */
    String USER_LABEL_B = "USER_LABEL_B";

    /*
     * clickhouse表名三表循环user_label_C
     * */
    String USER_LABEL_C = "USER_LABEL_C";

}
