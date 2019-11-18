package sludge.repository;

import sludge.entity.Menu;

import java.util.List;

/**
 * @Author Giggles
 * @Date 2019-09-26 11:00 AM
 */
public interface MenuRepository {
    List<Menu> findAll(int offset, int limit);

    int count();

    Menu findById(long id);

    void save(Menu menu);

    void update(Menu menu);

    void deleteById(long id);
}
