# ViewCoder OpenApi Sdk

Open Api 文档：https://www.mojidoc.com/0fahe-aniz5bvfuzhrvcorcc64t6qftm-00b

## How to use

访问 https://viewcoder.qingque.cn/dashboard/team?tab=INTEGRATION 获取认证凭证

```java
// 初始化认证凭证
Credential credential = new Credential();
credential.setSecretKey(properties.getProperty("secretKey"));
credential.setKeyId(properties.getProperty("keyId"));

// 初始化面试客户端
InterviewClient interviewClient = new ViewCoderClient(credential).getInterviewClient();

// 获取面试列表
Result<Page<InterviewQueryResult>> result = interviewClient.getInterviewList(new InterviewQueryParam());
```

## Maven
```xml
<dependency>
    <groupId>cn.qingque.viewcoder</groupId>
    <artifactId>openapi-sdk</artifactId>
    <version>1.0.3</version>
</dependency>
```
