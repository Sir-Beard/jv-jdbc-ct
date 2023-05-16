package mate.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.jdbc.dao.interfaces.ManufacturerDao;
import mate.jdbc.exception.DataProcessingException;
import mate.jdbc.lib.Dao;
import mate.jdbc.model.Manufacturer;
import mate.jdbc.util.ConnectionUtil;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String queryCreate
                = "INSERT INTO manufacturers (`name`, `country`) VALUES (?, ?)";
        try (
                Connection connection
                        = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(queryCreate,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Long insertedId = resultSet.getObject(1, Long.class);
                manufacturer.setId(insertedId);
            }
            manufacturer.setDeleted(false);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create manufacturer: "
                    + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String queryGet
                = "SELECT * FROM manufacturers WHERE id = ? AND is_deleted = FALSE";
        try (
                Connection connection
                        = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(queryGet)
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Manufacturer manufacturer = new Manufacturer();
            if (resultSet.next()) {
                manufacturer = getManufacturer(resultSet);
            }
            return Optional.ofNullable(manufacturer);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get manufacturer, id = "
                    + id, e);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String queryGetAll
                = "SELECT * FROM manufacturers WHERE is_deleted = FALSE";
        try (
                Connection connection
                        = ConnectionUtil.getConnection();
                Statement statement
                        = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(queryGetAll);
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                Manufacturer manufacturer = getManufacturer(resultSet);
                manufacturers.add(manufacturer);
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all manufacturers.", e);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String queryUpdate
                = "UPDATE manufacturers SET name = ?, country = ? "
                + "WHERE id = ?  AND is_deleted = FALSE";
        try (
                Connection connection
                        = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(queryUpdate)
        ) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return manufacturer;
            } else {
                throw new RuntimeException("Failed to update manufacturer");
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update manufacturer: "
                    + manufacturer, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String queryDelete
                = "UPDATE manufacturers SET `is_deleted` = 'TRUE' WHERE id = ?";
        try (
                Connection connection
                        = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(queryDelete)
        ) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete manufacturer, id = "
                    + id, e);
        }
    }

    private Manufacturer getManufacturer(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        Boolean isDeleted = resultSet.getBoolean("is_deleted");
        return new Manufacturer(id, name, country, isDeleted);
    }
}
