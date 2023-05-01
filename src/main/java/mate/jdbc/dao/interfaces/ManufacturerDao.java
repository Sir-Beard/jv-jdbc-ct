package mate.jdbc.dao.interfaces;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;
import mate.jdbc.model.Manufacturer;

public interface ManufacturerDao {
    Manufacturer create(Manufacturer manufacturer) throws SQLException;

    Optional<Manufacturer> get(Long id) throws SQLException;

    List<Manufacturer> getAll() throws SQLException;

    Manufacturer update(Manufacturer manufacturer) throws SQLException;

    boolean delete(Long id) throws SQLException;
}
