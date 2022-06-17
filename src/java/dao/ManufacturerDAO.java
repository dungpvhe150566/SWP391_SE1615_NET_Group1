/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Manufacturer;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface ManufacturerDAO {
    public Vector<Manufacturer> getManufacturerList() throws Exception;
}
