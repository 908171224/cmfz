package com.baizhi;

/*import com.aliyuncs.exceptions.ClientException;*/

import com.baizhi.ArticleRepsitory.ArticleRepsitory;
import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleRepsitory articleRepsitory;

    @Test
    public void contextLoads()/* throws ClientException */ {
        //设置超时时间-可自行调整
        /*System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAI9S3VF9yEohcg";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "qb7TlqGJnbLzsHNKlWjlAynMZ2Izdb";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("15035429369");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("Healer");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_164275709");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"wq250\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }*/
    }

    @Test
    public void name() {
        Article article = new Article();
        article.setId("001");
        article.setAuthor("杜甫");
        article.setTitle("大风歌");
        article.setContent("且将新火试新茶，诗酒趁年华");
        article.setCreateDate(new Date());
        article.setStatus("y");
        articleRepsitory.save(article);
    }

    @Test
    public void name2() {
        List<Article> all = articleMapper.findAll();
        for (Article a : all) {
            /*article.setId(a.getId());
            article.setAuthor(a.getAuthor());
            article.setTitle(a.getTitle());
            article.setContent(a.getContent());
            article.setCreateDate(a.getCreateDate());
            article.setStatus(a.getStatus());*/
            articleRepsitory.save(a);
        }
    }
}
