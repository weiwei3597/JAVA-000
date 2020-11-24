学习笔记
### 设计表
t_user

| 列名          | 类型        | 注释                       |
| ------------- | ----------- | -------------------------- |
| id            | bigint(19)  | 主键                       |
| valid_status  | char(1)     | 有效状态(1:有效 0无效)     |
| delete_status | char(1)     | 删除状态(1:已删除 0未删除) |
| create_by     | bigint(19)  | 创建人id                   |
| create_time   | datetime    | 创建时间                   |
| update_by     | bigint(19)  | 更新人id                   |
| update_time   | datetime    | 更新时间                   |
| phone         | varchar(15) | 手机号                     |
| name          | varchar(20) | 用户名称                   |

t_goods

| 列名          | 类型          | 注释                        |
| ------------- | ------------- | --------------------------- |
| id            | bigint(19)    | 主键                        |
| valid_status  | char(1)       | 有效状态(1:有效 0无效)      |
| delete_status | char(1)       | 删除状态(1:已删除 0未删除)  |
| create_by     | bigint(19)    | 创建人id                    |
| create_time   | datetime      | 创建时间                    |
| update_by     | bigint(19)    | 更新人id                    |
| update_time   | datetime      | 更新时间                    |
| name          | varchar(40)   | 商品名称                    |
| price         | decimal(10,2) | 商品单价                    |
| img           | text          | 商品图片                    |
| status        | char(1)       | 售卖状态 0下架 1上架  默认0 |
| remark        | text          | 备注                        |

t_order

| 列名           | 类型          | 注释                                                         |
| -------------- | ------------- | ------------------------------------------------------------ |
| id             | bigint(19)    | 主键                                                         |
| valid_status   | char(1)       | 有效状态(1:有效 0无效)                                       |
| delete_status  | char(1)       | 删除状态(1:已删除 0未删除)                                   |
| create_by      | bigint(19)    | 创建人id                                                     |
| create_time    | datetime      | 创建时间                                                     |
| update_by      | bigint(19)    | 更新人id                                                     |
| update_time    | datetime      | 更新时间                                                     |
| order_no       | varchanr(20)  | 订单编号                                                     |
| user_id        | bigint(19)    | 下单用户id                                                   |
| pay_time       | datetime      | 支付时间                                                     |
| refund_time    | datetime      | 退款时间                                                     |
| order_status   | char(2)       | 订单状态(0未支付 1已取消 2已支付 3已发货 4 待评价 5已评价 6售后中 7已退款 8退款失败 ) |
| total_price    | decimal(10,2) | 订单总金额                                                   |
| discount_price | decimal(10,2) | 折扣金额                                                     |
| real_price     | decimal(10,2) | 实际支付金额                                                 |
| address        | varchar(200) | 收货地址                                                     |

t_order_goods

| 列名          | 类型          | 注释                       |
| ------------- | ------------- | -------------------------- |
| id            | bigint(19)    | 主键                       |
| valid_status  | char(1)       | 有效状态(1:有效 0无效)     |
| delete_status | char(1)       | 删除状态(1:已删除 0未删除) |
| create_by     | bigint(19)    | 创建人id                   |
| create_time   | datetime      | 创建时间                   |
| update_by     | bigint(19)    | 更新人id                   |
| update_time   | datetime      | 更新时间                   |
| order_id      | bigint(19)    | 订单id                     |
| goods_id      | bigint(19)    | 商品id                     |
| goods_name    | varchar(40)   | 商品名称                   |
| goods_price   | decimal(10,2) | 商品单价                   |
| goods_count   | int(10)       | 下单商品数量               |