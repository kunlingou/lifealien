package lifealien.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class EhCacheTest {
	
	@Autowired
	Environment environment;
	
	@Test
    public void hello() {
        System.out.println("hello world");
        System.out.println(environment.getProperty("server.port"));
    }
}
