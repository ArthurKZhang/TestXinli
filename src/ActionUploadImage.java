import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhangyu on 15/02/2017.
 */
public class ActionUploadImage extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
     * 获取Android端上传过来的信息
     *
     */
    public void upload() {
        Gson gson = new Gson();
        String result = null;
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<String>() {}.getType();

        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            System.out.println("获取Android端传过来的普通信息：");
            System.out.println("用户名：" + request.getParameter("username"));
            System.out.println("密码：" + request.getParameter("pwd"));
            System.out.println("年龄：" + request.getParameter("age"));
            System.out.println("获取Android端传过来的文件信息：");
            System.out.println("文件存放目录: " + getSavePath());
            System.out.println("文件名称: " + imageFileName);
            System.out.println("文件大小: " + image.length());
            System.out.println("文件类型: " + imageContentType);

            //TODO
            File cacheDir = new File(getSavePath());//设置目录参数
            cacheDir.mkdirs();//新建目录
            System.out.println("新建"+getSavePath()+"目录成功");
            String filename;

            File cacheFile = new File(cacheDir,getImageFileName());//设置参数
            cacheFile.createNewFile();//生成文件
            System.out.println("生成文件成功"+cacheFile.getName());
            //TODO

            fos = new FileOutputStream(getSavePath() + "/" + getImageFileName());
            fis = new FileInputStream(getImage());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            System.out.println("文件上传成功");

            String filePath = getSavePath() + "/" + getImageFileName();
            System.out.println("filePath:"+filePath);

            /**
             * 返回必要信息
             */
            response.setCharacterEncoding("utf-8");
            result = gson.toJson("||"+filePath+"||",type);
            System.out.println("result:"+result);
            response.getWriter().write(result);

        } catch (Exception e) {
            System.out.println("文件上传失败");
            e.printStackTrace();
        } finally {
            close(fos, fis);
        }
    }

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

    // 上传文件域
    private File image;
//private
    // 上传文件类型
    private String imageContentType;
    // 封装上传文件名
    private String imageFileName;
    // 接受依赖注入的属性
    private String savePath;

    /**
     * 文件存放目录
     *
     * @return
     */
    public String getSavePath() throws Exception {
        return ServletActionContext.getServletContext().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
                fis = null;
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
                fis = null;
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }
}
