# java设计模式-外观模式

- 外观模式(门面模式):外观Facade为子系统的一组接口提供一个一致界面,使得这组子系统易于使用(通过引入一个新的外观角色降低原系统复杂度,同时降低客户类与子系统的耦合度) 
## 使用场景

- 购房:房屋中介、出租户
- 房屋中介为外观Facade，知道哪些子系统负责处理请求，将客户的请求代理给适当的子系统对象。
- 出租户为子系统集合：实现子系统功能，处理Facade对象指派的任务(子系统中没人任何Facade信息)
- 作用：
  - 可以防止向下转型调用子系统的公共方法。
  - 可以减少模块间依赖

![facade](https://ask.qcloudimg.com/http-save/yehe-1345101/iyfwg8un87.jpeg?imageView2/2/w/1620)

## 参考文档

- [java设计模式-外观模式](https://cloud.tencent.com/developer/article/1064454)

