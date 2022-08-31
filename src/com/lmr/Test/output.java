package com.lmr.Test;

import java.io.FileOutputStream;
import java.util.List;

import com.lmr.dao.Impl.TranDaoImpl;
import com.lmr.dao.TranDao;
import com.lmr.pojo.Tran;
import com.lmr.vo.TranVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class output {
    public static void main(String[] args) {
        TranVo tranVo=new TranVo();
        //实例化一个dao类对象
        TranDao tranDao=new TranDaoImpl();
        List<Tran> page = tranDao.page(1, 20, tranVo);

        //标题
        String[] title = {"交易id","订单id","用户id","用户名","商品名","重量","价格","上架时间","订单时间","数量"};
        //创建一个SXSSFWorkbook工作簿  读取.xls 格式
        //自定义内存中数据的数量
        SXSSFWorkbook wb = new SXSSFWorkbook(10);
        //将SXSSFWorkbook工作簿添加到表
        Sheet sheet = wb.createSheet();
        //创建行，  0：代表第一个单元格
        Row row = sheet.createRow(0);
        //给单元格设置样式，添加到工作簿的样式表中
        CellStyle cellStyle = wb.createCellStyle();
        //创建一个新字体，并添加到工作簿的字体表中
        Font font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 12);
        //设置字体加粗
        font.setBold(true);
        //给字体设置样式
        cellStyle.setFont(font);
        //设置单元格背景颜色
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        //设置单元格填充样式（使用纯色背景颜色填充）
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //填充表头数据
        for(int i = 0;i<title.length;i++) {
            //创建新单元格
            Cell cell = row.createCell(i);
            //放入 标题单元格的数值
            cell.setCellValue(title[i]);
            //为单元格设置样式
            cell.setCellStyle(cellStyle);
            //设置列的宽度
            sheet.setColumnWidth(i, 60*50);
        }
        //填充数据库表数据
        for(int j = 0;j<page.size();j++) {
            //创建行
            Row r = sheet.createRow(j+1);
            Tran rep = page.get(j);

            //获取 编号的数值（第一个单元格）
            Cell cell = r.createCell(0);
            cell.setCellValue(rep.getTid());
            //获取 名字的数值
            Cell cell2 = r.createCell(1);
            cell2.setCellValue(rep.getOid());
            //获取 性别的数值
            Cell cell3 = r.createCell(2);
            cell3.setCellValue(rep.getUid());
            //获取 接种疫苗的数值
            Cell cell4 = r.createCell(3);
            cell4.setCellValue(rep.getUsername());
            //获取 结果的数值
            Cell cell5 = r.createCell(4);
            cell5.setCellValue(rep.getGoodsname());
            //获取 地址的数值
            Cell cell6 = r.createCell(5);
            cell6.setCellValue(rep.getWeight());
            Cell cell7 = r.createCell(6);
            cell7.setCellValue(rep.getPrice());

            Cell cell8 = r.createCell(7);
            cell8.setCellValue(rep.getAddtime());

            Cell cell9=r.createCell(8);
            cell9.setCellValue(rep.getOrdertime());
            Cell cell10 = r.createCell(9);
            cell10.setCellValue(rep.getNum());
        }
        //设置输出路径
        String filename = "E:\\睿智\\第一阶段\\商品交易信息.xls";
        try {
            // 输出到硬盘，所以要用到IO流，输出到fileName指定的路径上
            FileOutputStream fos = new FileOutputStream(filename);
            wb.write(fos);//写出
            wb.close();//关闭
            System.out.println("导出成功");
        } catch (Exception e) {
            //异常信息
            e.printStackTrace();
        }
    }
}