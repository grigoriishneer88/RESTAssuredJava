import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	
	public ArrayList getData(String test_case_name, String sheet_name) throws IOException {
		ArrayList a = new ArrayList();
		FileInputStream file = new FileInputStream("Book2.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0;i<sheets;i++) {
			//System.out.println(workbook.getSheetName(i));
			if(workbook.getSheetName(i).equalsIgnoreCase(sheet_name))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row first_row = rows.next();
				Iterator <Cell> cell = first_row.cellIterator();
				int k = 0;
				int column=0;
				while(cell.hasNext()) {
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcases")){
						column = k;
						
					}
					k++;
				}
				//System.out.println(column);
				while(rows.hasNext()) {
					Row row = rows.next();
					String row_value = row.getCell(column).getStringCellValue();
					if (row_value.equalsIgnoreCase(test_case_name)) {
						Iterator <Cell> cell_value = row.cellIterator();
						while(cell_value.hasNext()) {
							Cell c = cell_value.next();
							if(c.getCellType()==CellType.STRING) {
								//System.out.println(cell_value.next().getStringCellValue());
								a.add(c.getStringCellValue());
							}else{
								//System.out.println(cell_value.next().getStringCellValue());
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
			
		}
		return a;
	}
	
	public static void main(String[]args) throws IOException {
		
	}
	
}
