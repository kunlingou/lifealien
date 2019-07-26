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

