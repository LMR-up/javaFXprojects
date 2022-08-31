package com.lmr.dao.Impl;

import com.lmr.dao.UserDao;
import com.lmr.pojo.User;
import com.lmr.utils.DButil;
import com.lmr.utils.StringUtils;
import com.lmr.utils.jdbctemplate;
import com.lmr.vo.UserVo;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /*导出所有用户数据*/
    public List<User> getALL() {

        List<User> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            sql.append("select id,username,password,telenum,createtime from t_user ");
            
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTelenum(rs.getString("telenum"));
                user.setCreatetime(rs.getTimestamp("createtime"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }
    /*
     * 登录
     * */
    @Override
    public User finByName(String name) {
        StringBuilder sql = new StringBuilder();//通过拼接来完成sql
        sql.append(" select id,username,password ,telenum,createtime from t_user where username= ?");
        System.out.println(sql);
        User user = null;
        try {
            Connection getcon = DButil.getconct();

            //获得预编译执行
            PreparedStatement ps = getcon.prepareStatement(sql.toString());
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {//如果有查到用户才赋值给对象
                user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setTelenum(rs.getString(4));
                user.setCreatetime(rs.getTimestamp(5));
            }
            DButil.closeAll(getcon, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByNameAndPassword(String name, String pwd) {
        StringBuilder sql = new StringBuilder();//通过拼接来完成sql
        sql.append(" select id,username,password ,telenum,createtime from t_user where username= ? and password= ?  ");
        System.out.println(sql);
        User user = null;
        try {
            Connection getcon = DButil.getconct();

            //获得预编译执行
            PreparedStatement ps = getcon.prepareStatement(sql.toString());
            ps.setString(1, name);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            user = new User();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setTelenum(rs.getString(4));
                user.setCreatetime(rs.getTimestamp(5));
            }
            DButil.closeAll(getcon, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findById(int id) {
        StringBuilder sql = new StringBuilder();//通过拼接来完成sql
        sql.append(" select id,username,password,telenum,createtime from t_user where id= ? ");
        System.out.println(sql);
        User user = null;
        try {
            Connection getcon = DButil.getconct();

            //获得预编译执行
            PreparedStatement ps = getcon.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            user = new User();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setTelenum(rs.getString(4));
                user.setCreatetime(rs.getTimestamp(5));
            }
            DButil.closeAll(getcon, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override//注册用户时插入用户
    public int insert(User user) {
        int i = 0;
        try {
            Connection conn = DButil.getconct();
            String sql = "insert into t_user (id,username,password,telenum,createtime) values (null,?,?,?,?)";

            // jdbctemplate.zsg(sql.toString(), user.getUsername(), user.getPassword(), user.getCreatetime());//如果数据库是存储的是时间戳类型
            //则需要转换成时间戳的类型 new Timestamp(loginInfo.getLogintime().getTime())
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getTelenum());
            ps.setTimestamp(4, new Timestamp(user.getCreatetime().getTime()));
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;

    }

    @Override
    public boolean saveOrUpdate(User user) {
        boolean flag = false;
        int coun = 0;
        Connection conn = null;
        try {
            conn = DButil.getconct();
            String sql = " update t_user set  username= ?, password = ?,telenum= ?, createtime = ? where id = ? ";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getTelenum());
            ps.setTimestamp(4, new Timestamp(user.getCreatetime().getTime()));
            ps.setInt(5, user.getId());

            System.out.println(sql);
            coun = ps.executeUpdate();
            if (coun != 0) {
                flag = true;
            }
            DButil.closeAll(conn, ps, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }


    /*
     * 统计总数*/
    @Override
    public int count(UserVo userVo) {
        int count = 0;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            //查询一共有多少条记录
            //where 1=1保证前段sql语句的正常执行
            sql.append("select count(*) as count from t_user where 1=1");

            /*根据传入的uservo参数值，拼接动态sql*/
            if (userVo != null && StringUtils.isNotBlank(userVo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username like '%" + userVo.getUsername() + "%'");
            }

            if (userVo != null && StringUtils.isNotBlank(userVo.getStartTime())) {
                sql.append(" and createtime > '" + userVo.getStartTime() + "'");
            }

            if (userVo != null && StringUtils.isNotBlank(userVo.getEndTime())) {
                sql.append(" and createtime < '" + userVo.getEndTime() + "'");
            }

            //预编译sql
            PreparedStatement ps = conn.prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                count = rs.getInt("count");
            }
            DButil.closeAll(conn, ps, rs);

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * -- select * from t_employee limit (current - 1) * pageSize,pageSize
     * -- m:从0开始的位置
     * -- n:返回多少条
     * -- pageSize 每页10条
     * -- 第一页
     * select * from t_employee limit (1 -1 ) * 10,10
     * -- 第二页
     * select * from t_employee limit (2 -1 ) * 10,10
     * -- 第三页
     * select * from t_employee limit (3 - 1) * 10,10
     *
     * @param
     * @param pageSize 每页多少条数据
     * @return
     */
    @Override
    public List<User> page(Integer currentPage, Integer pageSize, UserVo userVo) {

        List<User> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            sql.append("select id,username,password,telenum,createtime from t_user where 1=1 ");

            /*根据传入的uservo参数值，拼接动态sql*/
            if (userVo != null && StringUtils.isNotBlank(userVo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username like '%" + userVo.getUsername() + "%'");
            }

            if (userVo != null && StringUtils.isNotBlank(userVo.getStartTime())) {
                sql.append(" and createtime > '" + userVo.getStartTime() + "'");
            }

            if (userVo != null && StringUtils.isNotBlank(userVo.getEndTime())) {
                sql.append(" and createtime < '" + userVo.getEndTime() + "'");
            }

            sql.append(" limit ?,? ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            //limit (current - 1) * pageSize,pageSize
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTelenum(rs.getString("telenum"));
                user.setCreatetime(rs.getTimestamp("createtime"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public boolean deleteById(int id) {
        boolean flag = false;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from t_user where id = ?");
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i != 0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int findbyname(String name) {
        int id = 0;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            sql.append("select id from t_user where username = ?");
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<String> findByName(int id) {
        List<String> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();
            String sql = "select username from t_user where id != ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("username"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findByNameAndTele(String name, String tele) {
        StringBuilder sql = new StringBuilder();//通过拼接来完成sql
        sql.append(" select password from t_user where username = ?   ");
        if (finByName(name) != null) {
            sql.append(" and telenum = '" + tele + "'");
        }
        System.out.println(sql);
        User user = new User();
        try {
            Connection getcon = DButil.getconct();
            //获得预编译执行
            PreparedStatement ps = getcon.prepareStatement(sql.toString());
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setPassword(rs.getString("password"));
                System.out.println(user.getPassword());
            }
            DButil.closeAll(getcon, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("123");
        user.setPassword("1231");
        user.setId(38);
        user.setCreatetime(new Date());
        Connection conn = null;
        try {
            conn = DButil.getconct();
            String sql = " update t_user set  username= ?, password =?, createtime = ? where id = ? ";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setObject(3, user.getCreatetime());
            ps.setInt(4, user.getId());

            System.out.println(sql);
            int i = ps.executeUpdate();
            System.out.println(i);
            DButil.closeAll(conn, ps, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
