package sludge.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sludge.entity.Menu;

import java.util.List;

/**
 * @Author Giggles
 * @Date 2019-09-27 10:22 AM
 */
@org.springframework.cloud.openfeign.FeignClient(value = "menu")
public interface MenuFeign {
    @GetMapping("menu/findAll/{offset}/{limit}")
    List<Menu> findAll(@PathVariable("offset")int offset,@PathVariable("limit")int index);
 }
