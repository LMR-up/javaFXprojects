package com.lmr.service.Impl;
import com.lmr.common.Constant;
import com.lmr.dao.GoodsDao;
import com.lmr.dao.Impl.GoodsDaoImpl;
import com.lmr.pojo.Goods;
import com.lmr.service.GoodsService;
import com.lmr.vo.GoodsVo;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao =new GoodsDaoImpl();
    @Override
    public Goods findById(int id) {

        return goodsDao.findById(id);
    }
/*返回Boolean类型，再编辑或者新增保存之后，可以判断跳转到新的页面*/
    @Override
    public boolean saveOrUpdate(Goods goods) {
        if(goods != null && goods.getId() !=null){//判断编辑或者新增，如果没有goods对象则为新增
            return goodsDao.saveOrUpdate(goods);
        }

        return goodsDao.insert(goods)==1;
    }

    @Override
    public int insert(Goods goods) {
        return goodsDao.insert(goods);
    }

    @Override
    public int getPageCount(GoodsVo goodsVo) {
        int count= goodsDao.getPageCount(goodsVo);
        if(count==0){
            return 1;
        }
        return count % Constant.PAGE_SIZE ==0 ? count/Constant.PAGE_SIZE:count/Constant.PAGE_SIZE+1;
    }

    @Override
    public List<Goods> page(Integer currentPage, Integer pageSize, GoodsVo goodsVo) {
        return goodsDao.page(currentPage,pageSize,goodsVo);
    }

    @Override
    public boolean deleteById(int id) {
        return goodsDao.deleteById(id);
    }

    @Override
    public String findImagePath(int id) {
        return goodsDao.findImagePath(id);
    }
    @Override
    public String finByname(String name){
        return goodsDao.finByname(name);

    }
}
