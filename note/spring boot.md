### 获取配置文件参数

```
@ConfigurationProperties(prefix="websocket")
@RequestMapping("/websocket")
public class WebsocketConfigController {
	private String remoteAddr;
}
```

```
@Value("${local.server.port}")
private String port ;
```

### 获取服务ip和端口号

```
/**
 * 1.相关信息：服务器的ip、端口号
 * @author goukunlin
 *
 */
@RestController
@RequestMapping("/datatrans/config")
public class DataTransConfigController implements InitializingBean{
	private String local_server_port = "local.server.port";
	@Autowired
	Environment environment;
	private Map<String,String> config;
	public DataTransConfigController(){
		this.config = new HashMap<String, String>();
	}
	@RequestMapping("/getConfig")
	public Response getConfig() {
		if(!config.containsKey(local_server_port)
				&& environment.containsProperty(local_server_port)) {
			config.put("port", environment.getProperty(local_server_port));
		}
		return Response.success(config);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		InetAddress localHost = Inet4Address.getLocalHost();
		config.put("ip", localHost.getHostAddress());
		if(environment.containsProperty(local_server_port)) {
			config.put("port", environment.getProperty(local_server_port));
		}
	}
}
```

### 配置文件

```
@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

    @Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${mail.from}")
spring boot 会自动注入mail.properties中的值

 

mail.properties:

mail.host=localhost
mail.port=25
mail.smtp.auth=false
mail.smtp.starttls-enable=false
mail.from=me@localhost
mail.username=
mail.password=


@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {

    @Resource
    private Environment env;

public void getValue() {

String name = env.getRequiredProperty("datasource.name");

}
datasource.properties:

datasource.username=root
```

