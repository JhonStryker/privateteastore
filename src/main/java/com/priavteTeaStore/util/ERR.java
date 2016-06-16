package com.priavteTeaStore.util;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public enum ERR {
    req_msg_error("请求消息字段错误"),
    can_not_extract_product("无法私人订制提取商品: 没有激活商品或者商品数量不够"),
    system_error_for_unknown_reson("未知的系统错误"),
    delievery_order_status_err("自提订单状态错误"),
    already_has_active("茶庄已经激活"),
    has_already_paid("该订单已经支付了"),
    presentOrder_is_outOfDate("赠送订单已经过期"),
    no_such_user("该用户不存在"),
    invalid_check_code("验证码错误"),
    user_already_regist("用户已经注册"),
    already_add_teaProduct("已经添加该商品分类"),
    can_not_find_category_or_shop("找不到商品分类或者店铺"),
    no_such_order("订单不存在"),
    no_such_rank("没有该等级"),
    no_such_standard("没有该规格"),
    no_such_address("没有该创建地址"),
    no_such_spec("没有这个定制规格"), no_such_card("该茶庄卡不存在"),
    has_no_right("没有权限"), no_enough_acess_info("鉴权信息不全");


    private final String content;

    ERR(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
