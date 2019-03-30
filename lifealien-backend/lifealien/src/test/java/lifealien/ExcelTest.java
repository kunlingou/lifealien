package lifealien;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kunlinr.excel.util.ExcelUtil;
import com.kunlinr.lifealien.po.MigrationRow;

public class ExcelTest {
	@Test
	public void Test() throws IOException {
		File file = new File("table_map.xlsx");
		long t1 = System.currentTimeMillis();
		List<MigrationRow> list = ExcelUtil.readExcel("", MigrationRow.class, file);
		long t2 = System.currentTimeMillis();
		System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
		list.forEach(b -> System.out.println(b.toString()));
	}
}
