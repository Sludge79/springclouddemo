package sludge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sludge.entity.Menu;
import sludge.repository.MenuRepository;

import java.util.List;

/**
 * @Author Giggles
 * @Date 2019-09-26 10:04 AM
 */
@RestController
@RequestMapping("menu")
public class MenuHandler {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Value("${server.port}")
    private String port;

    @GetMapping("index")
    public String port(){
        return "menu的端口："+this.port;
    }

    @GetMapping("findAll/{offset}/{limit}")
    public List<Menu> findAll(@PathVariable("offset")Integer offset,@PathVariable("limit")Integer limit){
        return menuRepository.findAll(offset,limit);
    }

    @GetMapping("getRedis")
    public String getRedis(){
        return redisTemplate.keys("*").toString();
    }

//
//    public String findById(){
//
//    }
}
