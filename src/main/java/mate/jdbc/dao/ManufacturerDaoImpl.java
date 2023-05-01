package mate.jdbc.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import mate.jdbc.lib.Dao;
import mate.jdbc.model.Manufacturer;
import mate.jdbc.util.ConnectionUtil;
import mate.jdbc.util.DataProcessingException;
import mate.jdbc.dao.interfaces.ManufacturerDao;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    private Connection connection;

    public ManufacturerDaoImpl() {
    }

    public ManufacturerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        connection = ConnectionUtil.getConnection();
        String queryCreate = "INSERT INTO manufacturers (`name`, `country`) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(queryCreate,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Long instertedId = resultSet.getLong(1);
                manufacturer.setId(instertedId);
            }
            manufacturer.setDeleted(false);
            connection.close();
        } catch (SQLException e) {
            throw new DataProcessingException("error in create method", e);
        }
        return manufacturer;
    }
    // створити приватний метод який буде перевіряти чи є таким самий запис в базі даних
    // типу sql запит: селект * фром бд WHERE name='BMW';

    @Override
    public Optional<Manufacturer> get(Long id) throws SQLException {
        connection = ConnectionUtil.getConnection();
        String queryGet = "SELECT * FROM manufacturers WHERE id = ? AND is_deleted != true";
        try (PreparedStatement statement = connection.prepareStatement(queryGet)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String country = resultSet.getString("country");
                    Manufacturer manufacturer = new Manufacturer(id, name, country);
                    manufacturer.setId(id);
                    connection.close();
                    return Optional.ofNullable(manufacturer);
                } else {
                    connection.close();
                    return Optional.empty();
                }
            } catch (SQLException e) {
                throw new DataProcessingException("error in get method", e);
            }
        }
        //return Optional.ofNullable(null); // SQL query, resultSet, parse into java object
    }

    @Override
    public List<Manufacturer> getAll() {
        connection = ConnectionUtil.getConnection();
        String queryGetAll = "SELECT * FROM manufacturers WHERE is_deleted != true";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queryGetAll);
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                Manufacturer manufacturer = new Manufacturer(id, name, country);
                manufacturer.setId(id);
                manufacturers.add(manufacturer);
            }
            connection.close();
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("error in getAll method", e);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        connection = ConnectionUtil.getConnection();
        String queryUpdate = "UPDATE manufacturers SET name = ?, country = ? WHERE id = ?  AND is_deleted != true";
        try (PreparedStatement statement = connection.prepareStatement(queryUpdate)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                connection.close();
                return manufacturer;
            } else {
                throw new RuntimeException("Failed to update manufacturer");
            }
        } catch (SQLException e) {
            throw new DataProcessingException("error in update method", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        connection = ConnectionUtil.getConnection();
        String quetyDelete = "UPDATE manufacturers SET `is_deleted` = 'true' WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(quetyDelete)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            connection.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("error in delete method", e);
        }
    }
}
