/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Blog;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface BlogDAO {
    public Vector<Blog> getBlogList() throws Exception;
}
