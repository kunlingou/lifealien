package lifealien.webservice.server;

import java.util.Date;

import javax.jws.WebService;

@WebService(serviceName = "DemoService", // 与接口中指定的name一致
targetNamespace = "http://server.webservice.lifealien", // 与接口中的命名空间一致,一般是接口的包名倒
endpointInterface = "lifealien.webservice.server.DemoService"// 接口地址
)
public class DemoServiceImpl implements DemoService{

	@Override
	public String sayHello(String user) {
		return user+"，现在时间："+"("+new Date()+")"+"【"+Thread.currentThread().getStackTrace()[1].getClassName()+"】";
	}
	
}
