package com.vasil.carshop.api.carshopapi.services;

import com.vasil.carshop.api.carshopapi.requests.cars.CarCreateRequest;
import com.vasil.carshop.api.carshopapi.entities.cars.CarEntity;
import com.vasil.carshop.api.carshopapi.entities.*;
import com.vasil.carshop.api.carshopapi.entities.cars.*;
import java.sql.*;
import java.util.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService
{

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<CarEntity> getCars()
  {

    final String carsSql = "SELECT c.*, u.* FROM cars c JOIN users u ON c.owner_id = u.user_id ORDER BY c.added_on DESC";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement carsPstmt = conn.prepareStatement(carsSql))
    {

      try
      {

//        conn.setAutoCommit(false);

        List<CarEntity> cars = new ArrayList<>();

        ResultSet carsRset = carsPstmt.executeQuery();

        while (carsRset.next())
        {

          CarEntity car = getCarFromRSet(carsRset);

          cars.add(car);
        }

//          conn.commit();
        return cars;

      }
      catch (SQLException ex)
      {
        log.error("Error getting cars", ex);
//        conn.rollback();
      }
      finally
      {
//        conn.setAutoCommit(true);
      }
    }
    catch (Exception e)
    {
      log.error("Error getting cars", e);
    }

    return null;
  }

  @Override
  public CarEntity getCar(String carId)
  {

    final String carSql = "SELECT c.*, u.* FROM cars c JOIN users u ON c.owner_id = u.user_id WHERE c.car_id = ?";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement carPstmt = conn.prepareStatement(carSql))
    {

      try
      {

//        conn.setAutoCommit(false);
        carPstmt.setString(1, carId);

        ResultSet carRset = carPstmt.executeQuery();

        if (carRset.next())
        {

          CarEntity car = getCarFromRSet(carRset);

//          conn.commit();
          return car;
        }

      }
      catch (SQLException ex)
      {
        log.error("Error getting car with ID '" + carId + "'", ex);
//        conn.rollback();
      }
      finally
      {
//        conn.setAutoCommit(true);
      }
    }
    catch (Exception e)
    {
      log.error("Error getting car with ID '" + carId + "'", e);
    }

    return null;
  }

  @Override
  public CarEntity createCar(CarCreateRequest ccr)
  {

    final String createCarSql = "INSERT INTO cars (car_id, brand, model, year, horse_powers, price, category, engine_type, gearbox, mileage, mileage_unit, color, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement createCarPstmt = conn.prepareStatement(createCarSql))
    {

      try
      {

        conn.setAutoCommit(false);

        final String CAR_ID = UUID.randomUUID().toString();

        createCarPstmt.setString(1, CAR_ID);
        createCarPstmt.setString(2, ccr.getBrand());
        createCarPstmt.setString(3, ccr.getModel());
        createCarPstmt.setInt(4, ccr.getYear());
        createCarPstmt.setInt(5, ccr.getHorsePowers());
        createCarPstmt.setDouble(6, ccr.getPrice());
        createCarPstmt.setInt(7, ccr.getCategory());
        createCarPstmt.setInt(8, ccr.getEngineType());
        createCarPstmt.setInt(9, ccr.getGearbox());
        createCarPstmt.setDouble(10, ccr.getMileage().getMileage());

        if (ccr.getMileage().getUnit() == null || ccr.getMileage().getUnit().trim().isEmpty())
        {
          createCarPstmt.setString(11, "km");
        }
        else
        {
          createCarPstmt.setString(11, ccr.getMileage().getUnit().trim());
        }
        createCarPstmt.setString(12, ccr.getColor());
        createCarPstmt.setString(13, ccr.getUserId());

        createCarPstmt.executeUpdate();

        conn.commit();

        return getCar(CAR_ID);
      }
      catch (SQLException ex)
      {
        log.error("Error creating car", ex);
        conn.rollback();
      }
      finally
      {
        conn.setAutoCommit(true);
      }
    }
    catch (Exception e)
    {
      log.error("Error creating car", e);
    }

    return null;
  }
  
  @Override
  public CarEntity updateCar(String carId, CarCreateRequest ccr)
  {

    final String updateCarSql = "UPDATE cars SET brand = ?, model = ?, year = ?, horse_powers = ?, price = ?, category = ?, engine_type = ?, gearbox = ?, mileage = ?, mileage_unit = ?, color = ?, owner_id = ? WHERE car_id = ?";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement updateCarPstmt = conn.prepareStatement(updateCarSql))
    {

      try
      {

        conn.setAutoCommit(false);
        
        updateCarPstmt.setString(1, ccr.getBrand());
        updateCarPstmt.setString(2, ccr.getModel());
        updateCarPstmt.setInt(3, ccr.getYear());
        updateCarPstmt.setInt(4, ccr.getHorsePowers());
        updateCarPstmt.setDouble(5, ccr.getPrice());
        updateCarPstmt.setInt(6, ccr.getCategory());
        updateCarPstmt.setInt(7, ccr.getEngineType());
        updateCarPstmt.setInt(8, ccr.getGearbox());
        updateCarPstmt.setDouble(9, ccr.getMileage().getMileage());

        if (ccr.getMileage().getUnit() == null || ccr.getMileage().getUnit().trim().isEmpty())
        {
          updateCarPstmt.setString(10, "km");
        }
        else
        {
          updateCarPstmt.setString(10, ccr.getMileage().getUnit().trim());
        }
        updateCarPstmt.setString(11, ccr.getColor());
        updateCarPstmt.setString(12, ccr.getUserId());
        updateCarPstmt.setString(13, carId);

        updateCarPstmt.executeUpdate();

        conn.commit();

        return getCar(carId);
      }
      catch (SQLException ex)
      {
        log.error("Error updating car", ex);
        conn.rollback();
      }
      finally
      {
        conn.setAutoCommit(true);
      }
    }
    catch (Exception e)
    {
      log.error("Error updating car", e);
    }

    return null;
  }

  private CarEntity getCarFromRSet(ResultSet rset) throws SQLException
  {
    CarEntity car = new CarEntity();
    car.setCarId(rset.getString("car_id"));
    car.setBrand(rset.getString("brand"));
    car.setModel(rset.getString("model"));
    car.setYear(rset.getInt("year"));
    car.setHorsePowers(rset.getInt("horse_powers"));
    car.setPrice(rset.getDouble("price"));
    car.setCategory(rset.getInt("category"));
    car.setEngineType(rset.getInt("engine_type"));
    car.setGearbox(rset.getInt("gearbox"));

    MileageEntity mileage = new MileageEntity();
    mileage.setMileage(rset.getDouble("mileage"));
    mileage.setUnit(rset.getString("mileage_unit"));
    car.setMileage(mileage);

    car.setColor(rset.getString("color"));
    car.setAddedOn(rset.getTimestamp("added_on"));

    UserEntity owner = new UserEntity();
    owner.setUserId(rset.getString("user_id"));
    owner.setEmail(rset.getString("email"));
    owner.setPassword(rset.getString("password"));
    owner.setFirstName(rset.getString("first_name"));
    owner.setLastName(rset.getString("last_name"));
    owner.setCreatedOn(rset.getTimestamp("created_on"));
    owner.setEditedOn(rset.getTimestamp("edited_on"));
    car.setOwner(owner);

    return car;
  }
}
