package mate.jdbc;

import mate.jdbc.dao.interfaces.ManufacturerDao;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Manufacturer;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
        ManufacturerDao manufacturerDao = (ManufacturerDao) injector.getInstance(ManufacturerDao.class);
        Manufacturer manufacturer = new Manufacturer();

        //CREATE
//        manufacturer.setName("Cadillac");
//        manufacturer.setCountry("United States");
//        manufacturerDao.create(manufacturer);
//        System.out.println(manufacturer);

        //UPDATE
//        manufacturer.setId(4L);
//        manufacturer.setName("McLaren");
//        manufacturer.setCountry("United Kingdom");
//        manufacturerDao.update(manufacturer);

        //GET ALL
//        List<Manufacturer> manufacturerList = manufacturerDao.getAll();
//        for (Manufacturer m : manufacturerList) {
//            System.out.println(m);
//        }

        //GET
//        manufacturerDao.get(1L);
//        System.out.println(manufacturerDao.get(1L));

        //DELETE
//        manufacturerDao.delete(7L);
    }
}
