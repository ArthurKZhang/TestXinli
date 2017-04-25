import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by zhangyu on 16/02/2017.
 */
public class ActionDownload extends ActionSupport implements ServletRequestAware, ServletResponseAware {


    @Override
    public void setServletRequest(javax.servlet.http.HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(javax.servlet.http.HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    private HttpServletRequest request;
    private HttpServletResponse response;

    public void download() {

        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<String>() {
        }.getType();
        String result = null;
//        /Users/zhangyu/apache-tomcat-7.0.72/webapps/image/head.jpg
//        if (!request.getMethod().equalsIgnoreCase("get")){
//            System.out.println("request method used by client is not GET");
//            return;
//        }
//
        String filename = request.getParameter("filename");
        String fileUri = "/Users/zhangyu/apache-tomcat-7.0.72/webapps/image/" + filename;
        System.out.println("downloaded file uri:" + fileUri);
        File imageFile = new File(fileUri);
        if (!imageFile.exists()) {
            System.err.println("--file not exist--");
            return;
        }

//        private


        try {
            InputStream fis = new BufferedInputStream(new FileInputStream(imageFile));

            byte data[] = new byte[fis.available()];
//            long total = 0;
//            int len = 0;

            fis.read(data);

            response.setHeader("Content-Type", "application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + imageFile.getName());
            response.addHeader("Content-Length", "" + imageFile.length());

            response.getOutputStream().write(data);
//            FileInputStream fis = new FileInputStream(imageFile);

//            StringBuffer stringBuffer = new StringBuffer();
//            while ((len = fis.read(data)) != -1) {
//                System.out.println("____________________________________");
//                System.out.println(new String(data));
//                stringBuffer.append(new String(data, 0, len));
//                response.getOutputStream().write(data);
//            }
//            response.getWriter().write(stringBuffer.toString());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
