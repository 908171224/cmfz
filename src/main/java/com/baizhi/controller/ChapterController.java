package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("chapterFindByPage")
    public Map<String, Object> chapterFindByPage(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = chapterService.chapterFindByPage(page, rows, albumId);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Chapter chapter, String oper, String albumId, String[] id) {
        if (oper.equals("add")) {
            String s = chapterService.save(chapter, albumId);
            return s;
        } else if (oper.equals("del")) {
            chapterService.deleteChapter(id, albumId);
        } else {
            chapterService.update(chapter.getTitle(), chapter.getId());
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile filepath, String chapterId, HttpSession session) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        //获取音频存储位置
        String realPath = session.getServletContext().getRealPath("/mp3");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = filepath.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            filepath.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        chapterService.updatePath(chapterId, newFileName);

        //获取文件位置
        String realPath1 = session.getServletContext().getRealPath("/mp3/" + newFileName);
        File file1 = new File(realPath1);
        //获取文件大小  单位是字节 byte
        long length = file1.length();
        //获取音频时长 单位是秒      AudioFile类需要引入额外依赖 jaudiotagger
        AudioFile read = AudioFileIO.read(file1);
        AudioHeader header = read.getAudioHeader();
        int trackLength = header.getTrackLength();
        //获取分钟数
        Integer m = trackLength / 60;
        //获取秒秒数
        Integer s = trackLength % 60;
        System.out.println(m + "分" + s + "秒");
        String a = m + "分" + s + "秒";
        //将文件大小强转成double类型
        double size = (double) length;
        System.out.println(length + "length");
        System.out.println(size / 1024 / 1024);
        //获取文件大小 单位是MB
        double ll = size / 1024 / 1024;
        //取double小数点后两位  四舍五入
        BigDecimal bg = new BigDecimal(ll).setScale(2, RoundingMode.UP);
        System.out.println(bg + "MB");
        String b = bg + "MB";
        System.out.println(b);

        chapterService.updateSzieAndLong(a, b, chapterId);
    }

    @RequestMapping("download")
    public void download(String mp3Name, HttpSession session, HttpServletResponse response) throws IOException {
        String realPath2 = session.getServletContext().getRealPath("/mp3");
        File file2 = new File(realPath2, mp3Name);
        String s = mp3Name.split("_")[1];

        String encode = URLEncoder.encode(s, "UTF-8");
        String replace = encode.replace("+", "%20");
        System.out.println(replace);
        System.out.println(encode);
        response.setHeader("content-disposition", "attachment;filename=" + replace);
        FileUtils.copyFile(file2, response.getOutputStream());
    }
}
