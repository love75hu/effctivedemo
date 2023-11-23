package cn.mediinfo.grus.shujuzx.utils;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 导出工具
 */
public class ExportUtils {
    /**
     * 自定义获取分页总页数的方法
     * @param count 查询集合数量
     * @param pageSize 配置文件中设置的单文件存储最大条数
     * @return 总页数
     */
    public static int getPageCount(Integer count, Integer pageSize){
        int pageCount = 0;
        if(count.equals(0)){
            return pageCount;
        }
        pageCount = count/pageSize;
        if(count % pageSize != 0){
            pageCount++;
        }

        return pageCount;
    }

    /**
     * HSSFWorkbook转file
     * @param wb wb
     * @param name 文件名称
     * @return File
     */
    public static File xssfWorkbookToFile(Workbook wb, String name) throws IOException {
        File toFile = new File(name);
        OutputStream os = new FileOutputStream(toFile);
        wb.write(os);
        os.close();
        return toFile;
    }


    /**
     * 直接下载zip包
     * @param response response
     * @param excels wb集合
     * @param mingCheng 名称
     */
    public static void downFileByStream(HttpServletResponse response, List<ByteArrayOutputStream> excels, String mingCheng) throws IOException {
        String fileName = URLEncoder.encode(StringUtil.isBlank(mingCheng) ? "无" : mingCheng, StandardCharsets.UTF_8).replace("+", "%20");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".zip");
        response.setContentType("application/zip;charset=utf-8");
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        ZipOutputStream zipOutputStream = new ZipOutputStream(toClient);
        for (int i = 0; i < excels.size(); i++) {
            ZipEntry zipEntry = new ZipEntry("结果列表" + (i + 1) + ".xlsx");
            zipOutputStream.putNextEntry(zipEntry);
            // 将内存流写入Zip文件
            zipOutputStream.write(excels.get(i).toByteArray());
        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }
}
