package lifealien.excel;

import java.io.File;
import java.util.List;

import com.kunlinr.lifealien.excel.reader.FileReaderBuilder;

public class mainTest {
	public static void main(String[] args) {
//		File file = new File("D:\\卡片.xlsx");
//		List<Card> readExcel = FileReaderBuilder.newExcelFileReader(file, Card.class).read();
//		System.out.println(readExcel);
		
		File file = new File("D:\\卡片.csv");
		List<Card> readCsv = FileReaderBuilder.newCsvFileReader(file, Card.class).read();
		System.out.println(readCsv);
	}
}
