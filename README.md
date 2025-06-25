# 基于 SaaS 架构的短链接技术服务平台 | 实践项目

**时间**: 2025 年 01 月 — 2025 年 06 月  
**技术栈**:
![SpringBoot](https://img.shields.io/badge/SpringBoot-2.7.2-brightgreen)
![MyBatis](https://img.shields.io/badge/MyBatis-3.5.10-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0.31-orange)
![ShardingSphere](https://img.shields.io/badge/ShardingSphere-5.3.2-lightblue)
![Redis](https://img.shields.io/badge/Redis-6.2.12-red)

## 项目内容 🚀

实现了一个支持创建、分享、监控短链接的系统，提供以下主要功能：

- 🔐 **用户管理**: 注册、登录
- 🔗 **短链接生成**
- 🎯 **短链接重定向**
- ⚙️ **短链接管理**: 分组、禁用与启用、删除与恢复
- 📊 **监控日志和数据统计**: `UV`、`PV`、`UIP`

## 核心功能 ⚡

- 💾 **水平分表存储**: 通过 `ShardingSphere` 实现用户和短链接的水平分表存储。
- 🔒 **分布式读写锁**: 通过 `Redisson` 确保在大量访问场景下的数据修改安全。
- 🛡️ **缓存穿透解决方案**: 结合布隆过滤器 `Bloom Filter` 和空对象缓存，解决访问短链接时的缓存穿透问题。
- ⚡ **异步存储**: 通过 `Redis Stream` 削峰，保证非核心监控信息的异步存储，并利用 `Redis` 确保幂等消费。
- 👤 **用户信息存储**: 基于 `ThreadLocal` 封装线程隔离的全局上下文，实现用户信息的存储与判断。