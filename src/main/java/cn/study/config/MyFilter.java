//package cn.study.config;
//import cn.dev33.satoken.stp.StpUtil;
//import cn.study.constant.CommonConstants;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 过滤器，判断接口请求token的正确性
// * 正确则跳转到接口，错误返回到页面报错信息
// */
//@Component
//public class MyFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        Map<String, Object> map = new HashMap<>();
//        String url = ((HttpServletRequest) servletRequest).getRequestURI();
//        if (url != null) {
//            //登录请求直接放行
//            if ("/user/doLogin".equals(url)) {
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            } else {
//                //其他请求验证token
//                String token = ((HttpServletRequest) servletRequest).getHeader("token");
//                String loginId = (String) StpUtil.getLoginIdByToken(token);
//                if (StringUtils.isNotBlank(token)) {
//                    if (loginId!=null){
//                        long time = System.currentTimeMillis() - StpUtil.getSession().getCreateTime();
//                        long min = time % ( 1000 * 60 * 60 ) / ( 1000 * 60 );
//                        System.out.println(min);
//                        if (min>40 && min<90){ //大于40分小于90分
//                            StpUtil.renewTimeout(1800); //续签 （不知道好不好用）
//                        }
//                        filterChain.doFilter(servletRequest,servletResponse);
//                        return;
//                    }else{
//                        map.put("code",  CommonConstants.TOKEN_FAIL);
//                        map.put("msg", "Token已失效");
//                    }
//                } else {
//                    //token为空的返回
//                    map.put("code", CommonConstants.TOKEN_FAIL);
//                    map.put("msg", "未携带Token信息");
//                }
//            }
//            JSONObject jsonObject = new JSONObject(map);
//            servletResponse.setContentType("application/json");
//            //设置响应的编码
//            servletResponse.setCharacterEncoding("utf-8");
//            //响应
//            PrintWriter pw=servletResponse.getWriter();
//            pw.write(jsonObject.toString());
//            pw.flush();
//            pw.close();
//        }
//    }
//}
