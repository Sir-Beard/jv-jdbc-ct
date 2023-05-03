package mate.jdbc;

import mate.jdbc.dao.interfaces.ManufacturerDao;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Manufacturer;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) throws SQLException {
        ManufacturerDao manufacturerDao = (ManufacturerDao) injector.getInstance(ManufacturerDao.class);
        Manufacturer manufacturer = new Manufacturer();

        //CREATE
//        manufacturer.setName("Ford Australia");
//        manufacturer.setCountry("Australia");
//        manufacturerDao.create(manufacturer);
//        System.out.println(manufacturer);

        //UPDATE
//        manufacturer.setId(9L);
//        manufacturer.setName("Chery");
//        manufacturer.setCountry("China");
//        manufacturerDao.update(manufacturer);

        //GET ALL
        List<Manufacturer> manufacturerList = manufacturerDao.getAll();
        for (Manufacturer m : manufacturerList) {
            System.out.println(m);
        }

        //GET
//        manufacturerDao.get(1L);
//        System.out.println(manufacturerDao.get(1L));

        //DELETE
//        manufacturerDao.delete(7L);
    }
}
