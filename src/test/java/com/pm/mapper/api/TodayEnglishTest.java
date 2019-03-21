package com.pm.mapper.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TodayEnglishTest {

    @Test
    public void getFileTest(){
        ClassPathResource cpr = new ClassPathResource("eng.xls");
        //파일을 읽기위해 엑셀파일을 가져온다
        HSSFWorkbook workbook= null;
        JSONArray jarr = new JSONArray();
        try {
            workbook = new HSSFWorkbook(cpr.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rowindex=0;
        int columnindex=0;
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        HSSFSheet sheet=workbook.getSheetAt(0);
        //행의 수
        int rows=sheet.getPhysicalNumberOfRows();
        for(rowindex=1;rowindex<rows;rowindex++){
            //행을 읽는다
            HSSFRow row=sheet.getRow(rowindex);
            JSONObject obj = new JSONObject();
            if(row !=null){
                //셀의 수
                int cells=row.getPhysicalNumberOfCells();
                for(columnindex=0;columnindex<=cells;columnindex++){
                    //셀값을 읽는다
                    HSSFCell cell=row.getCell(columnindex);
                    String value="";
                    //셀이 빈값일경우를 위한 널체크
                    if(cell==null){
                        continue;
                    }else{
                        //타입별로 내용 읽기
                        switch (cell.getCellTypeEnum()){
                            case FORMULA:
                                value=cell.getCellFormula();
                                break;
                            case NUMERIC:
                                obj.put("idx", (int)cell.getNumericCellValue());
                                value=(int)cell.getNumericCellValue() + "";
                                break;
                            case STRING:
                                value=cell.getStringCellValue().trim().trim();

                                if(value.charAt(1) >= 'A' && value.charAt(1) <= 'z') obj.put("eng", value);
                                else {
                                    obj.put("ko", value);
                                    jarr.add(obj);
                                    obj = new JSONObject();
                                }

                                break;
                            case BLANK:
                                value=cell.getBooleanCellValue()+"";
                                break;
                            case ERROR:
                                value=cell.getErrorCellValue()+"";
                                break;
                        }
                    }

                }
            }
        }

    }
}
