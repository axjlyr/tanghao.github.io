package com.wms.common;

import com.wms.entity.Homepage;
import com.wms.mapper.HomepageMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomepageUploadExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomepageUploadExcelUtil.class);
    @Autowired
    private HomepageMapper homepageMapper;

    /**
     * 首先判断文件扩展名
     * 其次获取不同sheet页的内容
     * 然后获取行数据
     * 最后获取单元格数据
     */
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        try {
            InputStream is = file.getInputStream();
            Workbook wb = null;
            /**
             * Excel2003以前的版本，扩展名是.xls,使用HSSFWorkbook()
             * Excel2007之后的版本，扩展名是.xlsx,使用XSSFWorkbook()
             */
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            // 获取excel的第n个sheet页
            for (int n=0;n<wb.getNumberOfSheets();n++){
                notNull=false;
                List<Homepage> homepagesList = new ArrayList<Homepage>();
                Sheet sheet = wb.getSheetAt(n);

                if (sheet != null) {
                    notNull = true;
                }

                //获取工作表的总行数
//                System.out.println(sheet.getLastRowNum());
                int rowLength = sheet.getLastRowNum() + 1;
//            System.out.println("总行数有多少行" + rowLength);

                //获取工作表第一行数据
                Row row = sheet.getRow(0);
                //获取工作表总列数的长度
                int colLength = row.getLastCellNum();

                // 创建一个单元格对象
                Cell cell = null;
                for (int i = 2; i < rowLength; i++) {
                    Homepage homepage = new Homepage();
                    try {
                        if(sheet.getRow(i).getCell(0)==null){
                            break;
                        }
                    }catch (NullPointerException e) {
                        break;
                    }
                    for (int j = 0; j < colLength; j++) {
                        String stringCellValue = null;
                        cell = sheet.getRow(i).getCell(j);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            stringCellValue = cell.getStringCellValue();
                        }else {
                            stringCellValue=null;
                        }

                        switch (j) {
                            case 0:
                                homepage.setSchool(stringCellValue);
                                break;
                            case 1:
                                homepage.setCity(stringCellValue);
                                break;
                            case 2:
                                homepage.setThisNumber(stringCellValue);
                                break;
                            case 3:
                                homepage.setLastNumber(stringCellValue);
                                break;
                            case 4:
                                homepage.setThisMin(stringCellValue);
                                break;
                            case 5:
                                homepage.setThisMinplace(stringCellValue);
                                break;
                            case 6:
                                homepage.setLastMin(stringCellValue);
                                break;
                            case 7:
                                homepage.setLastMinplace(stringCellValue);
                                break;
                            case 8:
                                homepage.setLlastMin(stringCellValue);
                                break;
                            case 9:
                                homepage.setLlastMinplace(stringCellValue);
                                break;
                        }
                    }
                    if(n==0){
                        homepage.setMajorBatch("1A");
                        homepage.setType("文史");
                    }else if (n==1){
                        homepage.setMajorBatch("1A1");
                        homepage.setType("文史");
                    }else if (n==2){
                        homepage.setMajorBatch("1B");
                        homepage.setType("文史");
                    } else if (n==3) {
                        homepage.setMajorBatch("2A");
                        homepage.setType("文史");
                    }else if (n==4){
                        homepage.setMajorBatch("2B");
                        homepage.setType("文史");
                    }else if (n==5){
                        homepage.setMajorBatch("1A");
                        homepage.setType("理工");
                    }else if (n==6){
                        homepage.setMajorBatch("1A1");
                        homepage.setType("理工");
                    } else if (n==7) {
                        homepage.setMajorBatch("1B");
                        homepage.setType("理工");
                    }else if (n==8){
                        homepage.setMajorBatch("2A");
                        homepage.setType("理工");
                    }else if (n==9){
                        homepage.setMajorBatch("2B");
                        homepage.setType("理工");
                    }
                    homepagesList.add(homepage);
                }

                for (Homepage homepageResord : homepagesList) {

                    homepageMapper.addHomepage(homepageResord);
                    System.out.println(" 插入 " + homepageResord);

                    //首先判断该姓名是否已经存在
//                    String name = homepageResord.getSchool();
//                    int cnt = 0;     //majorMapper.selectByName(name);
//                    if (cnt == 0) {
//                        //不存在即插入
//                        homepageMapper.addHomepage(homepageResord);
//                        System.out.println(" 插入 " + homepageResord);
//                    } else {
//                        //存在即更新
//                        homepageMapper.updateHomepage(homepageResord);
//                        System.out.println(" 更新 " + homepageResord);
//                    }
                }

            }

            is.close();
        }catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return notNull;
    }
}
