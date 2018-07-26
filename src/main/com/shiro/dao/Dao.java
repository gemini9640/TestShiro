package com.shiro.dao;

import com.shiro.entity.RolesPermissions;
import com.shiro.entity.UserRoles;
import com.shiro.entity.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class Dao{
    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<RolesPermissions> queryAllRolePermissions() {
        String sql = "select id, role_name, permission, url from roles_permissions";
        List<RolesPermissions> list = jdbcTemplate.query(sql, new RowMapper<RolesPermissions>() {
            public RolesPermissions mapRow(ResultSet resultSet, int i) throws SQLException {
                RolesPermissions rp = new RolesPermissions();
                rp.setId(resultSet.getInt("id"));
                rp.setRole_name(resultSet.getString("role_name"));
                rp.setPermission(resultSet.getString("permission"));
                rp.setUrl(resultSet.getString("url"));
                return rp;
            }
        });
        return list;
    }

    public Users getUserByUsername(String username) {
        String sql = "select username, password, password_salt from users where username = ?";
        List<Users> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<Users>() {
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users user = new Users();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPasswrdSalt(resultSet.getString("password_salt"));
                return user;
            }
        });
        if (CollectionUtils.isEmpty(list))
            return null;
        else
            return list.get(0);
    }

    public List<String> queryRolesByUsername(String username) {
        String sql = "select role_name from user_roles where username = ?";
        return jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
    }

    public List<String> queryPermissionsByUsername(String username) {
        String sql = "select permission from roles_permissions where role_name in( select role_name from user_roles where username = ?) ";
        return jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("permission");
            }
        });
    }
}
