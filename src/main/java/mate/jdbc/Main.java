package mate.jdbc;

import mate.jdbc.dao.interfaces.ManufacturerDao;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Manufacturer;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
        ManufacturerDao manufacturerDao = (ManufacturerDao) injector.getInstance(ManufacturerDao.class);
        Manufacturer manufacturer = new Manufacturer();

        //CREATE
//        manufacturer.setName("Rimac");
//        manufacturer.setCountry("Croatia");
//        manufacturerDao.create(manufacturer);
//        System.out.println(manufacturer);

        //UPDATE
//        manufacturer.setId(10L);
//        manufacturer.setName("Zenvo");
//        manufacturer.setCountry("Denmark");
//        manufacturerDao.update(manufacturer);
//        System.out.println(manufacturer);

        //GET ALL
        List<Manufacturer> manufacturerList = manufacturerDao.getAll();
        for (Manufacturer m : manufacturerList) {
            System.out.println(m);
        }

        //GET
//        manufacturerDao.get(4L);
//        System.out.println(manufacturerDao.get(4L));

        //DELETE
//        manufacturerDao.delete(7L);
    }
}
