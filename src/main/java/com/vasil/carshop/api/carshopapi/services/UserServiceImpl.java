package com.vasil.carshop.api.carshopapi.services;

import com.vasil.carshop.api.carshopapi.entities.*;
import com.vasil.carshop.api.carshopapi.requests.users.*;
import java.sql.*;
import java.util.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  private final JdbcTemplate jdbcTemplate;

  @Override
  public UserEntity getUserByEmail(String email)
  {

    final String userSql = "SELECT u.* FROM users u WHERE u.email = ?";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement userPstmt = conn.prepareStatement(userSql))
    {

      try
      {

//        conn.setAutoCommit(false);
        userPstmt.setString(1, email);

        userPstmt.execute();

        ResultSet userRset = userPstmt.executeQuery();

        if (userRset.next())
        {
          UserEntity user = new UserEntity();

          user.setUserId(userRset.getString("user_id"));
          user.setEmail(userRset.getString("email"));
          user.setPassword(userRset.getString("password"));
          user.setFirstName(userRset.getString("first_name"));
          user.setLastName(userRset.getString("last_name"));
          user.setCreatedOn(userRset.getTimestamp("created_on"));
          user.setEditedOn(userRset.getTimestamp("edited_on"));

//          conn.commit();
          return user;
        }

      }
      catch (SQLException ex)
      {
        log.error("Error getting user with email '" + email + "'", ex);
//        conn.rollback();
      }
      finally
      {
//        conn.setAutoCommit(true);
      }
    }
    catch (Exception e)
    {
      log.error("Error getting user with email '" + email + "'", e);
    }

    return null;
  }

  @Override
  public UserEntity createUser(UserCreateRequest ucr)
  {

    final String createUserSql = "INSERT INTO users (user_id, email, password, first_name, last_name) VALUES (?, ?, ?, ?, ?)";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement createUserPstmt = conn.prepareStatement(createUserSql))
    {

      try
      {

        conn.setAutoCommit(false);

        final String USER_ID = UUID.randomUUID().toString();

        createUserPstmt.setString(1, USER_ID);
        createUserPstmt.setString(2, ucr.getEmail());
        createUserPstmt.setString(3, ucr.getPassword());
        createUserPstmt.setString(4, ucr.getFirstName());

        if (ucr.getLastName() == null || ucr.getLastName().trim().isEmpty())
        {
          createUserPstmt.setString(5, null);
        }
        else
        {
          createUserPstmt.setString(5, ucr.getLastName());
        }

        createUserPstmt.executeUpdate();

        conn.commit();

        return getUserByEmail(ucr.getEmail());
      }
      catch (SQLException ex)
      {
        log.error("Error creating user", ex);
        conn.rollback();
      }
      finally
      {
        conn.setAutoCommit(true);
      }
    }
    catch (Exception e)
    {
      log.error("Error creating user", e);
    }

    return null;
  }
}
