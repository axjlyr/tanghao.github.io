package com.wms.common;

import com.wms.entity.Batch;
import com.wms.mapper.BatchMapper;
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
public class BatchUploadExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchUploadExcelUtil.class);
    @Autowired
    private BatchMapper batchMapper;

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
        if (fileName.matches(   "^.+\\.(?i)(xlsx)$")) {
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

            for (int n=0;n<wb.getNumberOfSheets();n++) {
                notNull=false;
                List<Batch> batchsList = new ArrayList<Batch>();
                // 获取excel的第n个sheet页
                Sheet sheet = wb.getSheetAt(n);
                if (sheet != null) {
                    notNull = true;
                }

                //获取工作表的总行数
                System.out.println(sheet.getLastRowNum());
                int rowLength = sheet.getLastRowNum() + 1;
//            System.out.println("总行数有多少行" + rowLength);

                //获取工作表第一行数据
                Row row = sheet.getRow(0);
                //获取工作表总列数的长度
                int colLength = row.getLastCellNum();

                // 创建一个单元格对象
                Cell cell = null;
                for (int i = 1; i < rowLength; i++) {
                    Batch batch = new Batch();
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
                                batch.setNo(stringCellValue);
                                break;
                            case 1:
                                batch.setNo(stringCellValue);
                                break;
                            case 2:
                                batch.setNumber(stringCellValue);
                                break;
                            case 3:
                                batch.setStrategy(stringCellValue);
                                break;
                            case 4:
                                batch.setDownload(stringCellValue);
                                break;
                        }
                    }
                    batchsList.add(batch);
                }

                for (Batch batchResord : batchsList) {

                    batchMapper.addBatch(batchResord);
                    System.out.println(" 插入 " + batchResord);
                    //首先判断该姓名是否已经存在
//                String name = batchResord.getSchool();
//                int cnt = 0;     //majorMapper.selectByName(name);
//                if (cnt == 0) {
//                    //不存在即插入
//                    batchMapper.addBatch(batchResord);
//                    System.out.println(" 插入 " + batchResord);
//                } else {
//                    //存在即更新
//                    batchMapper.updateBatch(batchResord);
//                    System.out.println(" 更新 " + batchResord);
//                }
                }
            }
            is.close();
        }catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return notNull;
    }
}

