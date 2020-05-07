package com.zhiyou.video.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.zhiyou.video.model.CallerModel;
import com.zhiyou.video.util.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhiyou.video.model.CourseModel;
import com.zhiyou.video.model.SpeakerModel;
import com.zhiyou.video.model.VideoModel;
import com.zhiyou.video.query.VideoListQuery;
import com.zhiyou.video.service.ICourseService;
import com.zhiyou.video.service.ISpeakerService;
import com.zhiyou.video.service.IVideoService;
import com.zhiyou.video.validator.VideoAddValidator;
import com.zhiyou.video.validator.VideoEditValidator;
import org.springframework.web.multipart.MultipartFile;

import static com.zhiyou.video.controller.AdminBaseController.DEFAULT_PAGE;
import static com.zhiyou.video.controller.AdminBaseController.DEFAULT_PAGE_SIZE;

/**
 * Descr:
 */
@Controller
@RequestMapping("/admin/video")
public class VideoController {
    @Autowired
    IVideoService videoService;
    @Autowired
    ICourseService courseService;
    @Autowired
    ISpeakerService speakerService;

    @RequestMapping("index.do")
    public String index(@RequestParam(required = false) Integer pageNum, @ModelAttribute("query") VideoListQuery query, Model model) {
        //根据主讲人名称和主讲人职位查询信息
        HashMap map = new HashMap();
        if (pageNum == null || pageNum < 0) pageNum = DEFAULT_PAGE;
        map.put("pageNum", pageNum);
        map.put("pageSize", DEFAULT_PAGE_SIZE);
        //添加分页相关操作
        PageInfo<VideoModel> list = videoService.queryVideoPageList(map);
        //和上边的index.do用的是同一个页面，所以封装数据的名字也必须一样
        model.addAttribute("pageInfo", list);


        List<VideoModel> results = videoService.queryVideoModels(query);
        model.addAttribute("results", results);

        //把公用的信息单独写一个方法
        querys(model);

        return "admin/video/index";
    }

    @RequestMapping(value = "add.do", method = RequestMethod.GET)
    public String addGet(Model model) {
        querys(model);

        return "admin/video/saveOrUpdate";
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String addPost(MultipartFile videoImage, @Validated(value = {VideoAddValidator.class}) VideoModel model, BindingResult result, Model mo) {
        System.out.println("binding result：" + result);
        System.out.println("saveOrUpdate得到的信息：" + model);
        String picName = videoImage.getOriginalFilename();
//		String VideoName = videoUr.getOriginalFilename();
        //获取文件名的后缀名
        String extension = FilenameUtils.getExtension(picName);
//		String VideoExtension = FilenameUtils.getExtension(VideoName);
        UUID uuid = UUID.randomUUID();
        //生成新的文件名 防止重写上传时重名出现异常
        picName = uuid + "." + extension;
//		VideoName = uuid + "." + VideoExtension;
        String picPath = "F:\\softPackage\\utils\\apache-tomcat-8.0.38\\webapps\\ROOT\\static\\img\\pro";
//		String VideoPath = "F:\\softPackage\\utils\\apache-tomcat-8.0.38\\webapps\\ROOT\\static\\video";
        String path = picPath + "/" + picName;
//		String vPath = VideoPath + "/" + VideoName;
        picName = "http://localhost:8080/static/img/pro/" + picName;
//		VideoName="http://localhost:8080/static/video/"+VideoName;
        model.setVideoImageUrl(picName);
//		model.setVideoUrl(VideoName);
//
        System.out.println("saveOrUpdate修改图片名称之后的信息：" + model);
        //将图片保存在程序本地
        //String picPath = request.getServletContext().getRealPath("/");
        try {
            videoImage.transferTo(new File(path));
            //videoUr.transferTo(new File(vPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.hasErrors()) {
            mo.addAttribute("message", result.getAllErrors());
            querys(mo);
            return "admin/video/saveOrUpdate";
        }
        return saveOrUpdate(model);
    }

    @RequestMapping(value = "edit.do", method = RequestMethod.GET)
    public String editGet(int id, Model model) {
        querys(model);
        VideoModel video = videoService.queryVideoById(id);
        model.addAttribute("video", video);
        return "admin/video/update";
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public String editPost(MultipartFile videoImg, @Validated(value = {VideoEditValidator.class}) VideoModel
            model, BindingResult result, Model mo) {
        System.out.println("binding result：" + result);
        System.out.println("saveOrUpdate得到的信息：" + model);
        System.out.println(videoImg.getName());
        String picName = videoImg.getOriginalFilename();
        //获取文件名的后缀名
        String extension = FilenameUtils.getExtension(picName);
        UUID uuid = UUID.randomUUID();
        //生成新的文件名 防止重写上传时重名出现异常
        picName = uuid + "." + extension;
        String picPath = "F:\\softPackage\\utils\\apache-tomcat-8.0.38\\webapps\\ROOT\\static\\img\\pro";
        String path = picPath + "/" + picName;
        picName = "http://localhost:8080/static/img/pro/" + picName;
        model.setVideoImageUrl(picName);

        System.out.println("saveOrUpdate修改图片名称之后的信息：" + model);
        //将图片保存在程序本地
        //String picPath = request.getServletContext().getRealPath("/");
        try {
            videoImg.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.hasErrors()) {
            mo.addAttribute("message", result.getAllErrors());
            querys(mo);
            //需要将信息查询出来返回
            VideoModel video = videoService.queryVideoById(model.getId());
            mo.addAttribute("video", video);
            return "admin/video/saveOrUpdate";
        }
        return saveOrUpdate(model);
    }

    //修改和添加合并在一起
    private String saveOrUpdate(VideoModel model) {
        System.out.println("saveOrUpdate得到的信息：" + model);
        //判断当前请求是添加信息或更新信息
        if (model.getId() > 0) {
            //更新请求
            model.setUpdateTime(new Date());
            //调用更新方法
            boolean b = videoService.updateVideoModel(model);
            System.out.println("更新视频信息是否成功：" + b);
        } else {
            //新增请求
            model.setInsertTime(new Date());
            model.setUpdateTime(new Date());
            //调用新增方法
            int id = videoService.addVideoModel(model);
            System.out.println("新增视频信息，id为" + id);
        }

        return "redirect:/admin/video/index.do";
    }

    private void querys(Model model) {
        //查询条件
        List<SpeakerModel> speakers = speakerService.querySpeakers();
        List<CourseModel> courses = courseService.queryCourseModels();

        model.addAttribute("speakers", speakers);
        model.addAttribute("courses", courses);
    }

    /**
     * 改为ajax
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete.do")
    public Map delete(int id) {
        boolean b = videoService.deleteById(id);
        //封装结果
        HashMap map = new HashMap();
        if (b) {
            //删除成功
            map.put("success", true);
        } else {
            map.put("success", false);
            map.put("message", "删除视频信息失败，请重试");
        }

        return map;
    }

    /**
     * 批量删除
     *
     * @return
     */
    @RequestMapping("batchDelete.do")
    public String batchDelete(int[] checkid) {
        System.out.println("批量删除");
        System.out.println(Arrays.toString(checkid));
        return "redirect:/admin/video/index.do";
    }

}
