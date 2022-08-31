package com.lmr.dao.Impl;

import com.lmr.dao.TranDao;
import com.lmr.pojo.Tran;
import com.lmr.utils.DButil;
import com.lmr.utils.StringUtils;
import com.lmr.vo.TranVo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranDaoImpl implements TranDao {


    /*导出所有数据*/
    public static List<Tran> getAll() {
        List<Tran> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            /*没有判断条件的话就把所有的数据都搜索出来，在表格分页展示*/
            sql.append("select tid,oid,uid,username,goodsname,weight,price,addtime,imagepath,ordertime,num from tran ");


            PreparedStatement ps = conn.prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tran tran = new Tran();
                tran.setTid(rs.getInt("tid"));
                tran.setOid(rs.getInt("oid"));
                tran.setUid(rs.getInt("uid"));
                tran.setUsername(rs.getString("username"));
                tran.setGoodsname(rs.getString("goodsname"));
                tran.setWeight(rs.getDouble("weight"));
                tran.setPrice(rs.getDouble("price"));
                tran.setAddtime(rs.getDate("addtime"));
                tran.setImagepath(rs.getString("imagepath"));
                tran.setOrdertime(rs.getDate("ordertime"));
                tran.setNum(rs.getInt("num"));
                list.add(tran);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*此方法用于商品图表显示*/
    public static Map<String, Integer> all() {
        Map<String, Integer> map = new HashMap<>();
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();

            sql.append("select goodsname ,price*count(goodsname) as xsd from tran group by goodsname; ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
            DButil.closeAll(conn, ps, rs);

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    @Override
    public int getPageCount(TranVo tranVo) {
        int count = 0;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            //查询一共有多少条记录
            //where 1=1保证前段sql语句的正常执行
            sql.append("select count(*) as count from tran where 1=1");

            if (tranVo != null && StringUtils.isNotBlank(tranVo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username = '" + tranVo.getUsername() + "'");
            }

            /*根据传入的uservo参数值，拼接动态sql*/
            if (tranVo != null && StringUtils.isNotBlank(tranVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname = '" + tranVo.getGoodsname() + "'");
            }

            if (tranVo != null && StringUtils.isNotBlank(tranVo.getStartTime())) {
                sql.append(" and ordertime > '" + tranVo.getStartTime() + "'");
            }

            if (tranVo != null && StringUtils.isNotBlank(tranVo.getEndTime())) {
                sql.append(" and ordertime < '" + tranVo.getEndTime() + "'");
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

    @Override
    public List<Tran> page(Integer currentPage, Integer pageSize, TranVo tranVo) {
        List<Tran> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            /*没有判断条件的话就把所有的数据都搜索出来，在表格分页展示*/
            sql.append("select tid,oid,uid,username,goodsname,weight,price,addtime,imagepath,ordertime,num from tran where 1=1 ");

            if (tranVo != null && StringUtils.isNotBlank(tranVo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username like '%" + tranVo.getUsername() + "%'");
            }

            /*根据传入的uservo参数值，拼接动态sql*/
            if (tranVo != null && StringUtils.isNotBlank(tranVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname like '%" + tranVo.getGoodsname() + "%'");
            }

            if (tranVo != null && StringUtils.isNotBlank(tranVo.getStartTime())) {
                sql.append(" and ordertime > '" + tranVo.getStartTime() + "'");
            }

            if (tranVo != null && StringUtils.isNotBlank(tranVo.getEndTime())) {
                sql.append(" and ordertime < '" + tranVo.getEndTime() + "'");
            }

            sql.append(" limit ?,? ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            //limit (current - 1) * pageSize,pageSize
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Tran tran = new Tran();
                tran.setTid(rs.getInt("tid"));
                tran.setOid(rs.getInt("oid"));
                tran.setUid(rs.getInt("uid"));
                tran.setUsername(rs.getString("username"));
                tran.setGoodsname(rs.getString("goodsname"));
                tran.setWeight(rs.getDouble("weight"));
                tran.setPrice(rs.getDouble("price"));
                tran.setAddtime(rs.getTimestamp("addtime"));
                tran.setImagepath(rs.getString("imagepath"));
                tran.setOrdertime(rs.getTimestamp("ordertime"));
                tran.setNum(rs.getInt("num"));
                list.add(tran);
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
        Connection conn;
        try {
            conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from tran where tid = ?");
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 1) {
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
    public String findImagePath(int id) {
        String path = null;
        PreparedStatement ps = null;
        try {
            Connection conn = DButil.getconct();
            String sql = "select imagepath from tran where tid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                path = rs.getString("imagepath");
            }
            DButil.closeAll(conn, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
