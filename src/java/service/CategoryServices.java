/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.MethodDao;
import dao.impl.CategoryDAOImpl;
import entity.Category;
import java.util.List;

/**
 *
 * @author Pham Van Trong
 */
public class CategoryServices implements MethodService<Category>{
     MethodDao<Category> md = (MethodDao<Category>) new CategoryDAOImpl();
    /**
     * 
     * @inheritDoc
     */
    @Override
    public List<Category> getAll() {
        return md.getAll();
    }

    @Override
    public Category getOne(int id) {
        return md.getOne(id);
    }

    @Override
    public boolean add(Category object) {
        return md.add(object);
    }

    @Override
    public boolean update(Category object, int id) {
        return md.update(object, id);
    }

    @Override
    public boolean remove(int id) {
        return md.remove(id);
    }
    
}
