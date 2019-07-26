package com.xserver.modules.example;

import com.xserver.cache.ExampleCacheService;
import com.xserver.common.BaseController;
import com.xserver.common.Response;
import com.xserver.common.Session;
import com.xserver.vo.ExampleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * Created by lkk on 2019/5/12.
 */
@ApiIgnore
@RestController
@RequestMapping("/api/example")
@Api(tags = { "ExampleController" })
public class ExampleController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleController.class);
    @Autowired
    private ExampleService exampleService;
    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private ExampleCacheService exampleCacheService;

    @ApiOperation(value = "样例接口", notes = "hello world")
    @GetMapping("/get")
    public ExampleVo get(@RequestParam(value = "id", required = true) Long id) {
        return exampleService.findVoById(id);
    }

    @ApiOperation(value = "样例Response接口", notes = "test Response")
    @GetMapping("/resp")
    public Response<Boolean> getResp() {
        return success(true);
    }

    @ApiOperation(value = "样例Cache接口", notes = "test Cache")
    @GetMapping("/cache")
    public Response<String> getCache(HttpSession session) {
        // redis 缓存session 验证
        session.setAttribute("aa", "bb");
        return success(exampleCacheService.getString());
    }

    @GetMapping("/resp1")
    public Response<Boolean> getResp(HttpServletRequest request, Session session) {
        System.out.println(reloadMessageSource);
        System.out.println(reloadMessageSource.getMessage("example.test", null, Locale.CHINA));
        return success(true);
    }

    @ApiOperation(value = "样例Cache接口", notes = "test Cache")
    @GetMapping("/page")
    public Response<List<ExampleVo>> getPage(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        LOG.info("Hello,world");
        return exampleService.findByPage(pageNo, pageSize);
    }

    @ApiOperation(value = "样例http接口", notes = "test http")
    @GetMapping("/http")
    public Response<String> http() {
        String url = "http://wwww.baidu.com";
        final Request request = new Request.Builder().url(url).get()// 默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            okhttp3.Response response = call.execute();
            System.out.print(response.body().string());
        } catch (Exception e) {
        }
        return success();
    }

    @ApiOperation(value = "生成图片", notes = "test http")
    @GetMapping("/pic")
    public void pic() {
        String url = "http://wwww.baidu.com";
        final Request request = new Request.Builder().url(url).get()// 默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            okhttp3.Response response = call.execute();
            System.out.print(response.body().string());
        } catch (Exception e) {
        }
    }
}