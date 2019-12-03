package sludge.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sludge.utils.TempleWordUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("exportWord")
public class ExportWordController {

    @GetMapping("get")
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {



        Map<String, Object> params = new HashMap<String, Object>();
        params.put("${name}", "aaaa");
        params.put("${sex}", "bbbb");

        writeWord("test", "test", request, response, params);
    }


    private static String SUFFIX = ".docx";

    private static void writeWord(String template, String fileName, HttpServletRequest request, HttpServletResponse response, Map data) {

        String templatePath = ExportWordController.class.getClassLoader().getResource("word_template/" + template + SUFFIX).getPath();
        try {
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1") + SUFFIX;
            XWPFDocument doc;
            InputStream is = new FileInputStream(templatePath);
            //  is = getClass().getClassLoader().getResourceAsStream(templatePath);
            doc = new XWPFDocument(is);   //只能使用.docx的

            TempleWordUtil.replaceInPara(doc, data);
            //替换表格里面的变量
            TempleWordUtil.replaceInTable(doc, data);
            OutputStream os = response.getOutputStream();

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);

            doc.write(os);

            TempleWordUtil.close(os);
            TempleWordUtil.close(is);

            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void writeWord(File file, HttpServletRequest request, HttpServletResponse response, String aFileName) {
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//        try {
//            request.setCharacterEncoding("UTF-8");
//            String agent = request.getHeader("User-Agent").toUpperCase();
//            if ((agent.indexOf("MSIE") > 0) || ((agent.indexOf("RV") != -1) && (agent.indexOf("FIREFOX") == -1)))
//                aFileName = URLEncoder.encode(aFileName, "UTF-8");
//            else {
//                aFileName = new String(aFileName.getBytes("UTF-8"), "ISO8859-1");
//            }
//            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//            response.setHeader("Content-disposition", "attachment; filename=" + aFileName);
//            response.setHeader("Content-Length", String.valueOf(file.length()));
//            bis = new BufferedInputStream(new FileInputStream(file));
//            bos = new BufferedOutputStream(response.getOutputStream());
//            byte[] buff = new byte[2048];
//            int bytesRead;
//            while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
//                bos.write(buff, 0, bytesRead);
//            System.out.println("success");
//            bos.flush();
//        } catch (Exception e) {
//            System.out.println("下载文件失败！");
//        } finally {
//            try {
//                if (bis != null) {
//                    bis.close();
//                }
//                if (bos != null) {
//                    bos.close();
//                }
//                file.delete();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
