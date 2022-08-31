package com.lmr.dao.Impl;

import com.lmr.common.UserInfo;
import com.lmr.dao.UserTranDao;
import com.lmr.pojo.UserTran;
import com.lmr.utils.DButil;
import com.lmr.utils.StringUtils;
import com.lmr.vo.UserTranVo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTranDaoImpl implements UserTranDao {
    @Override
    public int getPageCount(UserTranVo userTranVo) {
        int count = 0;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql = new StringBuilder();
            //查询一共有多少条记录
            //where 1=1保证前段sql语句的正常执行
            sql.append("select count(*) as count from u_tran where 1=1");
            sql.append(" and uid = "+ UserInfo.user.getId() +"");//不同用户对应不同界面

            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username = '" + userTranVo.getUsername() + "'");
            }

            /*根据传入的uservo参数值，拼接动态sql*/
            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname = '" + userTranVo.getGoodsname() + "'");
            }

            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getStartTime())) {
                sql.append(" and ordertime > '" + userTranVo.getStartTime() + "'");
            }

            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getStartTime())) {
                sql.append(" and ordertime < '" + userTranVo.getEndTime() + "'");
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
    public List<UserTran> page(Integer currentPage, Integer pageSize, UserTranVo userTranVo) {
        List<UserTran> list = new ArrayList<>();
        try {
            Connection conn = DButil.getconct();

            StringBuilder sql = new StringBuilder();
            /*没有判断条件的话就把所有的数据都搜索出来，在表格分页展示*/
            sql.append("select oid,uid,username,goodsname,weight,price,addtime,imagepath,ordertime,num from u_tran where 1=1 ");
            sql.append(" and uid = "+ UserInfo.user.getId() +"");
            System.out.println(UserInfo.user.getId());
            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username like '%" + userTranVo.getUsername() + "%'");
            }

            /*根据传入的uservo参数值，拼接动态sql*/
            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getGoodsname())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and goodsname like '%" + userTranVo.getGoodsname() + "%'");
            }

            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getStartTime())) {
                sql.append(" and ordertime > '" + userTranVo.getStartTime() + "'");
            }

            if (userTranVo != null && StringUtils.isNotBlank(userTranVo.getEndTime())) {
                sql.append(" and ordertime < '" + userTranVo.getEndTime() + "'");
            }

            sql.append(" limit ?,? ");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            //limit (current - 1) * pageSize,pageSize
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                UserTran usertran=new UserTran();
                usertran.setOid(rs.getInt("oid"));
                usertran.setUid(rs.getInt("uid"));
                usertran.setUsername(rs.getString("username"));
                usertran.setGoodsname(rs.getString("goodsname"));
                usertran.setWeight(rs.getDouble("weight"));
                usertran.setPrice(rs.getDouble("price"));
                usertran.setAddtime(rs.getTimestamp("addtime"));
                usertran.setImagepath(rs.getString("imagepath"));
                usertran.setOrdertime(rs.getTimestamp("ordertime"));
                usertran.setNum(rs.getInt("num"));
                list.add(usertran);
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
            sql.append("delete from u_tran where oid = ?");
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
            String sql = "select imagepath from u_tran where oid = ?";
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
