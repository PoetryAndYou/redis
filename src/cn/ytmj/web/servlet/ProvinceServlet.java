package cn.ytmj.web.servlet;

import cn.ytmj.domain.Province;
import cn.ytmj.service.ProvinceSevince;
import cn.ytmj.service.impl.ProvinceServinceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.net.idn.StringPrep;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rui
 * @create 2019-08-24 23:45
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*//调用service查询
        ProvinceSevince ps=new ProvinceServinceImpl();
        List<Province> provinces = ps.finAll();
        //准化为json
        ObjectMapper objectMapper=new ObjectMapper();
        String s = objectMapper.writeValueAsString(provinces);*/
        ProvinceSevince ps=new ProvinceServinceImpl();
        String s = ps.finAllJson();
        System.out.println(s);
        //相应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
