## 此demo用于blink 3.x版本的UDX开发
### 分支管理
- blink 3.3.0 版本 -- dev分支：v330
- blink 3.3.0 版本 -- release分支：v330-release-1.0 

### 打包命令

```shell
mvn assembly:assembly
```

### 使用方法
参考内部的语雀
```
https://yuque.antfin-inc.com/bigdata.pro/knowledge/ezzu53/edit?toc_node_uuid=7dXRizvFrRHYVJrE
```
ParseAvroUdf 函数使用说明，接受两个input参数：
- 参数1：被解析的mq消息体（varbinary）
- 参数2：解析mq消息体avro定义文件，通过资源方式引入（DICTIONARY），传入参数形式为 '/user.avsc'
返回值：
- 字符串，将avro格式数据解析成Json字符串，然后通过JSON_VALUE解析获取即可，注意JSON_VALUE返回值的类型转换需要手动进行。
- JSON_VALUE的使用方法参见：https://help.aliyun.com/document_detail/62535.html