package com.lmr.dao.Impl;

import com.lmr.common.UserInfo;
import com.lmr.dao.OrderDao;
import com.lmr.pojo.Goods;
import com.lmr.pojo.Order;
import com.lmr.utils.DButil;
import com.lmr.utils.StringUtils;
import com.lmr.vo.OrderVo;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    //删除当前订单
    @Override
    public boolean deleteById(int id) {
        boolean flag = false;
        String gname=null;
        int num = 0;
        int gid=0;
        try {
            Connection conn = DButil.getconct();

            // 删除的时候将商品的数量重新加1
            if (!flag) {
                String sql1="select goodsname  from u_order where oid = ? ";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1,id);
                ResultSet resultSet = ps1.executeQuery();
                if(resultSet.next()){
                    gname=resultSet.getString("goodsname");
                }

                String sql2="select id, goodnum from goods where goodsname = ?";
                PreparedStatement prepareStatement = conn.prepareStatement(sql2);
                prepareStatement.setString(1,gname);
                ResultSet resultSet1 = prepareStatement.executeQuery();
                if(resultSet1.next()){
                    num=resultSet1.getInt("goodnum");
                    gid=resultSet1.getInt("id");
                }

                /*根据订单表中名字，去匹配更新 goodnum*/
                String sql3="update goods set goodnum = ? where id = ? ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql3);
                preparedStatement.setInt(1,num+1);
                preparedStatement.setInt(2,gid);

                flag = preparedStatement.execute();

            }

            if(!flag){

                StringBuilder sql = new StringBuilder();
                sql.append("delete from u_order where oid = ?");
                PreparedStatement ps = conn.prepareStatement(sql.toString());
                ps.setInt(1, id);
                int i = ps.executeUpdate();
                if (i != 0) {
                    flag = true;
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    /*获取总条数，在service再进行判断页数*/
    @Override
    public int getPageCount(OrderVo orderVo) {
        int count = 0;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            //查询一共有多少条记录
            //where 1=1保证前段sql语句的正常执行
            sql.append("select count(*) as count from u_order where 1=1");
            sql.append(" and userid = " + UserInfo.user.getId() + "");

            /*根据传入的uservo参数值，拼接动态sql*/
            if (orderVo != null && StringUtils.isNotBlank(orderVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname like '%" + orderVo.getGoodsname() + "%'");
            }

            if (orderVo != null && StringUtils.isNotBlank(orderVo.getStartTime())) {
                sql.append(" and ordertime > '" + orderVo.getStartTime() + "'");
            }

            if (orderVo != null && StringUtils.isNotBlank(orderVo.getEndTime())) {
                sql.append(" and ordertime < '" + orderVo.getEndTime() + "'");
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


    /*分页查询数据*/
    @Override
    public List<Order> page(Integer currentPage, Integer pageSize, OrderVo orderVo) {

        List<Order> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            sql.append(" select oid,userid,goodsname,weight,price,addtime,ordertime,num from u_order where 1=1 ");
            sql.append(" and userid =  " + UserInfo.user.getId() + "");
            System.out.println(UserInfo.user.getId());

            //查询框判断语句
            /*根据传入的uservo参数值，拼接动态sql*/
            if (orderVo != null && StringUtils.isNotBlank(orderVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname like '%" + orderVo.getGoodsname() + "%'");
            }

            if (orderVo != null && StringUtils.isNotBlank(orderVo.getStartTime())) {
                sql.append(" and ordertime > '" + orderVo.getStartTime() + "'");
            }

            if (orderVo != null && StringUtils.isNotBlank(orderVo.getEndTime())) {
                sql.append(" and ordertime < '" + orderVo.getEndTime() + "'");
            }

            sql.append(" limit ?,? ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            System.out.println(sql);

            //limit (current - 1) * pageSize,pageSize
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setUserid(rs.getInt("userid"));
                order.setGoodsname(rs.getString("goodsname"));
                order.setWeight(rs.getDouble("weight"));
                order.setPrice(rs.getDouble("price"));
                order.setAddtime(rs.getTimestamp("addtime"));
                order.setOrdertime(rs.getTimestamp("ordertime"));
                order.setNum(rs.getInt("num"));

                list.add(order);
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
* 点击添加购物车，根据id从goods中寻找数据，在插入到order表中
* */
    @Override
    public boolean addShopCar(int id) {
        boolean flag = false;
        Connection getconct;
        try {
            getconct = DButil.getconct();
            String sql = "select goodsname,weight,price,addtime,imagepath,goodnum from goods where id = ?";
            PreparedStatement ps = getconct.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Goods goods = new Goods();
            if (rs.next()) {
                goods.setGoodsname(rs.getString("goodsname"));
                goods.setWeight(rs.getDouble("weight"));
                goods.setPrice(rs.getDouble("price"));
                goods.setAddtime(rs.getTimestamp("addtime"));
                goods.setImagepath(rs.getString("imagepath"));
                goods.setGoodnum(rs.getInt("goodnum"));
            }

/*对商品数量进行判断，如果商品数量小于1，则无法添加购物车*/
            if(goods.getGoodnum()>0){
                /*插入到购物车的时候将登录时记录的用户名也插入，在查询的时候可以判断用户*/
                int num = 1;
                String sql2 = "insert into u_order (oid,userid,username,goodsname,weight,price,addtime,ordertime,imagepath,num) values (null,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps2 = getconct.prepareStatement(sql2);
                ps2.setInt(1, UserInfo.user.getId());
                ps2.setString(2, UserInfo.user.getUsername());
                ps2.setString(3, goods.getGoodsname());
                ps2.setDouble(4, goods.getWeight());
                ps2.setDouble(5, goods.getPrice());
                ps2.setTimestamp(6, new Timestamp(goods.getAddtime().getTime()));
                ps2.setTimestamp(7, new Timestamp(new Date().getTime()));
                ps2.setString(8, goods.getImagepath());
                ps2.setInt(9, num);
                //  flag = ps2.execute();
                flag = ps2.execute();//false

            }
/*加入购物车之后，将goods表中的数量减一*/
            if (!flag && goods.getGoodnum()>0) {
                String sql3 = "update goods set goodnum= ? where id = ? ";
                PreparedStatement ps3 = getconct.prepareStatement(sql3);
                System.out.println(sql3);
                ps3.setInt(1, goods.getGoodnum() - 1);
                ps3.setInt(2, id);
                ps3.executeUpdate();

                flag = true;
            }

            if (goods.getGoodnum() < 1) {
                flag = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public String findImage(int id) {
        String path = null;
        PreparedStatement ps = null;
        try {
            Connection conn = DButil.getconct();
            String sql = "select imagepath from u_order where oid = ?";
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

    @Override
    public boolean PayOrder(int id) {
        Connection conn;
        boolean flag = false;
        try {
            conn = DButil.getconct();
            String sql = "select * from u_order where oid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Order order=new Order();
            if (rs.next()) {
                order.setOid(rs.getInt("oid"));
                order.setUserid(rs.getInt("userid"));
                order.setUsername(rs.getString("username"));
                order.setGoodsname(rs.getString("goodsname"));
                order.setWeight(rs.getDouble("weight"));
                order.setPrice(rs.getDouble("price"));
                order.setAddtime(rs.getTimestamp("addtime"));
                order.setImagepath(rs.getString("imagepath"));
                order.setOrdertime(rs.getTimestamp("ordertime"));
                order.setNum(rs.getInt("num"));
            }

            /*对商品数量进行判断，如果商品数量小于1，则无法添加购物车*/
            if(order.getUsername()!=null){
                /*插入到购物车的时候将登录时记录的用户名也插入，在查询的时候可以判断用户*/

                String sql2 = "insert into tran (tid,oid,uid,username,goodsname,weight,price,addtime,ordertime,imagepath,num) values (null,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, order.getOid());
                ps2.setInt(2, order.getUserid());
                ps2.setString(3,order.getUsername());
                ps2.setString(4,order.getGoodsname());
                ps2.setDouble(5,order.getWeight());
                ps2.setDouble(6,order.getPrice());
                ps2.setTimestamp(7,new Timestamp(order.getAddtime().getTime()));
                ps2.setTimestamp(8,new Timestamp(order.getOrdertime().getTime()));
                ps2.setString(9,order.getImagepath());
                ps2.setInt(10,order.getNum());
                //  flag = ps2.execute();
                 flag=ps2.execute();//false
                System.out.println(flag);



                String sql3 = "insert into u_tran (oid,uid,username,goodsname,weight,price,addtime,ordertime,imagepath,num) values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps3 = conn.prepareStatement(sql3);

                ps3.setInt(1, order.getOid());
                ps3.setInt(2, order.getUserid());
                ps3.setString(3,order.getUsername());
                ps3.setString(4,order.getGoodsname());
                ps3.setDouble(5,order.getWeight());
                ps3.setDouble(6,order.getPrice());
                ps3.setTimestamp(7,new Timestamp(order.getAddtime().getTime()));
                ps3.setTimestamp(8,new Timestamp(order.getOrdertime().getTime()));
                ps3.setString(9,order.getImagepath());
                ps3.setInt(10,order.getNum());
                //  flag = ps2.execute();
                flag=ps3.execute();//false
                System.out.println(flag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !flag;
    }

    @Override
    public boolean deleteWhenPay(int id) {
        Connection conn = null;
        boolean flag=false;
        try {
            conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from u_order where oid = ?");
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            System.out.println(i+"iiiiiiiiiiiiiiiiiiiii");
            if(i!=0){
                flag= true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


}
