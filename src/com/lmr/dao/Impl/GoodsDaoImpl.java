package com.lmr.dao.Impl;

import com.lmr.dao.GoodsDao;
import com.lmr.pojo.Goods;
import com.lmr.utils.DButil;
import com.lmr.utils.StringUtils;
import com.lmr.vo.GoodsVo;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public Goods findById(int id) {
        StringBuilder sql = new StringBuilder();//通过拼接来完成sql
        sql.append(" select id,goodsname,weight ,price ,addtime ,goodnum ,imagepath from goods where id=?");
        System.out.println(sql);
        Goods goods = null;
        try {
            Connection getcon = DButil.getconct();

            //获得预编译执行
            PreparedStatement ps = getcon.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            goods = new Goods();
            if (rs.next()) {
                goods.setId(id);
                goods.setGoodsname(rs.getString("goodsname"));
                goods.setWeight(rs.getDouble("weight"));
                goods.setPrice(rs.getDouble("price"));
                goods.setAddtime(rs.getTimestamp("addtime"));
                goods.setGoodnum(rs.getInt("goodnum"));
                goods.setImagepath(rs.getString("imagepath"));
            }
            DButil.closeAll(getcon, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public String finByname(String name) {
        String gname = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select goodsname from goods where 1=1 ").append(" and goodsname = ? ");
            Connection conn = DButil.getconct();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gname = rs.getString("goodsname");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gname;
    }

    @Override
    public boolean saveOrUpdate(Goods goods) {
        boolean flag = false;
        int coun = 0;
        Connection conn = null;
        try {
            conn = DButil.getconct();
            String sql = " update goods set  goodsname= ?, weight =?, price = ? ,addtime = ? ,goodnum = ? , imagepath = ? where id = ? ";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsname());
            ps.setDouble(2, goods.getWeight());
            ps.setDouble(3, goods.getPrice());
            ps.setTimestamp(4, new Timestamp(goods.getAddtime().getTime()));
            ps.setInt(5, goods.getGoodnum());
            ps.setString(6, goods.getImagepath());
            ps.setInt(7, goods.getId());

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

    @Override
    public int insert(Goods goods) {
        int i = 0;
        try {
            Connection conn = DButil.getconct();
            String sql = "insert into goods (id,goodsname,weight,price,addtime,goodnum,imagepath) values (null,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsname());
            ps.setDouble(2, goods.getWeight());
            ps.setDouble(3, goods.getPrice());
            ps.setTimestamp(4, new Timestamp(goods.getAddtime().getTime()));
            ps.setInt(5, goods.getGoodnum());
            ps.setString(6, goods.getImagepath());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int getPageCount(GoodsVo goodsVo) {
        int count = 0;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            //查询一共有多少条记录
            //where 1=1保证前段sql语句的正常执行
            sql.append("select count(*) as count from goods where 1=1");

            /*根据传入的uservo参数值，拼接动态sql*/
            if (goodsVo != null && StringUtils.isNotBlank(goodsVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname like '%" + goodsVo.getGoodsname() + "%'");
            }

            if (goodsVo != null && StringUtils.isNotBlank(goodsVo.getStartTime())) {
                sql.append(" and addtime > '" + goodsVo.getStartTime() + "'");
            }

            if (goodsVo != null && StringUtils.isNotBlank(goodsVo.getStartTime())) {
                sql.append(" and addtime < '" + goodsVo.getEndTime() + "'");
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
    public List<Goods> page(Integer currentPage, Integer pageSize, GoodsVo goodsVo) {
        List<Goods> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            /*没有判断条件的话就把所有的数据都搜索出来，在表格分页展示*/
            sql.append("select id,goodsname,weight,price,addtime,goodnum from goods where 1=1 ");

            /*根据传入的uservo参数值，拼接动态sql*/
            if (goodsVo != null && StringUtils.isNotBlank(goodsVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname like '%" + goodsVo.getGoodsname() + "%'");
            }

            if (goodsVo != null && StringUtils.isNotBlank(goodsVo.getStartTime())) {
                sql.append(" and addtime > '" + goodsVo.getStartTime() + "'");
            }

            if (goodsVo != null && StringUtils.isNotBlank(goodsVo.getStartTime())) {
                sql.append(" and addtime < '" + goodsVo.getEndTime() + "'");
            }

            sql.append(" limit ?,? ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            //limit (current - 1) * pageSize,pageSize
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setGoodsname(rs.getString("goodsname"));
                goods.setWeight(rs.getDouble("weight"));
                goods.setPrice(rs.getDouble("price"));
                goods.setAddtime(rs.getTimestamp("addtime"));
                goods.setGoodnum(rs.getInt("goodnum"));
                list.add(goods);
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
            sql.append("delete from goods where id = ?");
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if(i==1){
                flag=true;
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
            String sql = "select imagepath from goods where id = ?";
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
