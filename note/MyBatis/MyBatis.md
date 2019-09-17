### 参考链接

- <https://blog.csdn.net/gebitan505/article/details/54929287> 

### Mapper.xml

- 一对多查询

```xml
<!-- 一对多查询，查询用户及订单明细和商品信息 -->
<resultMap id="userOrdersAndItemsResultMap" type="user">
    <!-- 用户信息映射 -->
    <id column="id" property="id"/>
    <result column="username" property="username"/>
    <result column="sex" property="sex"/>
    <!-- 订单信息映射 -->
    <!-- 下边完成关联信息的映射
        collection：用于对关联信息映射到集合
        property：要将关联信息映射到User的哪个属性中
        ofType：关联信息映射到User的属性的类型，可以使用别名，不过全称方便确认
         -->
    <collection property="orderlist" ofType="com.emuii.mybatis.pojo.Orders">
        <!-- id：订单关联用户查询的唯一标识  -->
        <id column="id" property="id"/>
        <result column="user_id" property="userId"/>
        <result column="number" property="number"/>
        <result column="createtime" property="createtime"/>
        <result column="note" property="note"/>
        <!-- 订单详细信息映射 -->
        <collection property="orderdetails" ofType="com.emuii.mybatis.pojo.Orderdetail">
            <!-- id：关联信息订单明细的唯一标识
                property：Orderdetail的属性名
                  -->
            <id column="orderdetail_id" property="id"/>
            <result column="items_num" property="itemsNum"/>
            <result column="items_id" property="itemsId"/>
            <association property="items" javaType="com.emuii.mybatis.pojo.Items">
                <id column="id" property="id"/>
                <result column="items_name" property="name"/>
                <result column="items_detail" property="detail"/>
            </association>
        </collection>
    </collection>
</resultMap>

<!-- 一对多查询使用reusltMap完成
查询用户及订单和订单明细，关联商品，的信息
-->
<select id="findUserOrdersAndItems" resultMap="userOrdersAndItemsResultMap">
    SELECT
      orders.*,
      user.username,
      user.sex ,
      orderdetail.id orderdetail_id,
      orderdetail.items_num,
      orderdetail.items_id,
      items.name items_name,
      items.detail items_detail
    FROM
      orders,
      USER,
      orderdetail,
      items
    WHERE orders.user_id = user.id  AND orders.id = orderdetail.orders_id AND items.id = orderdetail.items_id
</select>
```





