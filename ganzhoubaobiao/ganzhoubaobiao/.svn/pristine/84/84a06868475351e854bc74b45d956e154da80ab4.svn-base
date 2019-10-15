package com.zklcsoftware.common.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import com.zklcsoftware.basic.util.StringUtil;
import com.zklcsoftware.common.dto.OperaResult;
import com.zklcsoftware.common.web.ExtBaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = {"", "/api"})
public class UploadFileController extends ExtBaseController
{
    @Value("${uploadfiledir.uploadPhotoFile}")
    private String uploadPhotoFile;//服务器路径
    @Value("${uploadfiledir.uploadFilePath}")
    private String uploadFilePath;//文件封存的http地址

    @ApiOperation(value = "上传文件功能", notes = "上传文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "附件", required = true, dataType = "MultipartFile", paramType = "query"),
            @ApiImplicitParam(name = "suffixs", value = "限制文件类型(多个用，分隔 例如(jpg,jpeg,png))", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(paramType = "query", name = "access_token") })
    @RequestMapping(value = "/common/uploadFile", method = { RequestMethod.POST })
    @ResponseBody
    public OperaResult uploadFile(@RequestParam("file") MultipartFile file, String suffixs, OperaResult result) throws Exception {
        //判断是否需要验证文件类型
        if(StringUtils.isNotBlank(suffixs)){
            //检验文件格式是否符合格式限制
            if(!StringUtil.checkFileType(file.getOriginalFilename(), suffixs)){throw new MyException("不支持的文件类型！");}
        }
        //如果不存在则创建
        StringUtil.createDirectory(uploadPhotoFile);
        //保存文件到服务器
        file.transferTo(new File(uploadPhotoFile+file.getOriginalFilename()));
        //返回url到客户端
        result.getContent().put("msg", uploadFilePath + "/ganzhoubaobiao/" + file.getOriginalFilename());
        result.getContent().put("name",file.getOriginalFilename());
        return result;
    }
    
    @ApiOperation(value = "显示文件", notes = "显示文件")
    @ResponseBody
    @RequestMapping(value = "/images/**", method = RequestMethod.GET)
    public FileSystemResource showImg(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        if (!StringUtils.isEmpty(path)) {
            String realpath = uploadPhotoFile + path.substring(path.lastIndexOf("/"));
            return new FileSystemResource(realpath);
        } else {
            return null;
        }
    }
    
    /**
     * 上传文件
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile", value = "文件name", required = true, dataType = "MultipartFile", paramType = "query"),
            @ApiImplicitParam(paramType = "query", name = "access_token")})
    @RequestMapping(value = "/web/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public OperaResult uploadFile(@RequestParam("file") MultipartFile multipartFile, OperaResult result) throws Exception {
        // 从session中读取孩子的id
        String saveurl = uploadFilePath;
        String realpath = uploadPhotoFile;
        String filename = multipartFile.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

        ArrayList<String> types = new ArrayList<String>();// 设置上传文件类型

//        if (!types.contains(fileType.toUpperCase())) {
//            result.getContent().put("uploadResult", "nottype");
//            return result;
//        }

        InputStream is = multipartFile.getInputStream();
        if (is.available() > Long.valueOf(50 * 1024 * 1024L)) {//上传大小为100M
            if (is != null) is.close();
            result.getContent().put("uploadResult", "tolarge");
            return result;
        }

        String videonewname = new Date().getTime() + "." + fileType;
        File savefile = new File(new File(realpath), videonewname);
        if (!savefile.getParentFile().exists()) {
            savefile.getParentFile().mkdirs();
        }
        File toFile = new File(realpath, videonewname);
        if (!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
        }
        // 创建一个输出流  
        OutputStream os = new FileOutputStream(toFile);
        //设置缓存  
        byte[] buffer = new byte[1024];

        int length = 0;

        //读取myFile文件输出到toFile文件中  
        while ((length = is.read(buffer)) != -1) {
            os.write(buffer, 0, length);
        }
        //关闭输入流  
        is.close();
        os.close();
		result.getContent().put("fileName", filename);
		result.getContent().put("fileUrl",uploadFilePath + File.separator + videonewname);
		return result;
    }
 
}
