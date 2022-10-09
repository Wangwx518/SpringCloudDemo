# SpringCloudDemo
自学springCloud


*********************************************************************服务注册******************************************************************************************


###########Eureka#################
服务端pom.xml
       <!--eureka-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
 客户端pom.xml     
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>    
        
  客户端主方法注解：@EnableEurekaClient
                  @EnableDiscoveryClient  //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
  服务端主方法注解：@EnableEurekaServer
        
        
单机版
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com        #eureka服务端的实例名称
  client:
       #  false表示不向注册中心注册自己。
     register-with-eureka: false
       #false表示自己端就是注册中心，我的职责就是雄护服务实例，并不需要去检察服务
     fetch-registry: false
     serviceUrl:
       defaultZone: http://eureka7001.com:7001/eureka


集群版：
修改host文件
在底部新增
127.0.0.1        eureka7001.com
127.0.0.1        eureka7002.com

application.yml
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com        #eureka服务端的实例名称
  client:
       #  false表示不向注册中心注册自己。
     register-with-eureka: false
       #false表示自己端就是注册中心，我的职责就是雄护服务实例，并不需要去检察服务
     fetch-registry: false
     serviceUrl:
       defaultZone: http://eureka7002.com:7002/eureka
#  server:
#    enable-self-preservation: false
#    eviction-interval-timer-in-ms: 2000
#       defaultZone: http://eureka7002.com:7002/eureka
application.yml
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com        #eureka服务端的实例名称
  client:
    #  false表示不向注册中心注册自己。
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是雄护服务实例，并不需要去检察服务
    fetch-registry: false
    serviceUrl:
      defaultZone: http://eureka7001.com:7001/eureka
      
      
      
      
      
      
###########zookeeper#################
消费者客户端pom
       <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper3.5.3-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.9版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.14</version>
        </dependency>
        <dependency>
        
        
        
        
        
        
        
        
application.yml
#服务别名---注册zookeeper.注册中心名称
spring:
  application:
    name: cloud-consumerzk-order
  cloud:
    zookeeper:
      connect-string: 腾讯云服务器外网IP:2181
      
      
      
      
      
      
 ##########consul##############
 
 consul自带ribbon,可以使用restTamplte调用接口
 
 public class OrderConsulController {
    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "consumer/payment/consul")
    public String payment(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return  result;
    }
}

application.yml
 spring:
  application:
    name: consul-consumer-order
  ##consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
