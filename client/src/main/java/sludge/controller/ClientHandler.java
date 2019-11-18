package sludge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sludge.entity.Menu;
import sludge.feign.MenuFeign;

import java.util.List;

/**
 * @Author Giggles
 * @Date 2019-09-27 10:27 AM
 */
@Controller
@RequestMapping("client")
public class ClientHandler {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("redirect/{location}")
    public String index(@PathVariable("location") String location) {
        return location;
    }

    @GetMapping("findAll")
    @ResponseBody
    public List<Menu> findAll(int offset, int index) {
        return menuFeign.findAll(offset, index);
    }

}
