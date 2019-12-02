package sludge.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DocUtil {

    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("ar_cp_name","月报");
        map.put("ar_dateTime","2018-5-28");
        map.put("ar_info","岁的法国大使馆的风格");
        getBuild("static/doc/ar_template.doc",map,"D:/aaa.doc");
    }

    public static void getBuild(String  tmpFile, Map<String, String> contentMap, String exportFile){

        InputStream inputStream = DocUtil.class.getClassLoader().getResourceAsStream(tmpFile);
//        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(tmpFile);
        HWPFDocument document = null;
        try {
            document = new HWPFDocument(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }

        //导出到文件
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write(byteArrayOutputStream);
            OutputStream outputStream = new FileOutputStream(exportFile);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
