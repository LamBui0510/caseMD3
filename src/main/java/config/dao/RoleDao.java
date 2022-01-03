package config.dao;


import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    List<Role> roleList = new ArrayList<>();

    public List<Role> showRole() {
        String getRole = "select * from Role";
        Connection connection = connectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getRole);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                String code = resultset.getString("code");
                roleList.add(new Role(id, name, code));
            }
            return roleList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
