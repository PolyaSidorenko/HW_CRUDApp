package org.example;

import java.util.List;

/**
 * Интерфейс для доступа к данным сущности
 * Определяет основные CRUD-операции (создание, чтение, обновление, удаление)
 */
public interface ProductDAO {

    /**
     * Сохраняет новый продукт в базе данных
     */
    void create(Product product);

    /**
     * Возвращает список всех продуктов из базы данных
     */
    List<Product> getAll();

    /**
     * Обновляет информацию о продукте в базе данных
     */
    void update(Product product);

    /**
     * Удаляет продукт из базы данных по его идентификатору
     */
    void delete(Long id);
}
