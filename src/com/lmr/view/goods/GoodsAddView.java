package com.lmr.view.goods;

import com.lmr.App.AppRun;
import com.lmr.pojo.Goods;
import com.lmr.service.GoodsService;
import com.lmr.service.Impl.GoodsServiceImpl;
import com.lmr.utils.DateUtils;
import com.lmr.utils.StringUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class GoodsAddView {
    public static void add(int id) {
        GoodsService goodsService=new GoodsServiceImpl();
        Goods goods=null;
        if(id!=0){
            goods=goodsService.findById(id);
        }

        GridPane gridPane=new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        Text addText = new Text("商品 编辑 新增");
        addText.setFont(Font.font("宋体", FontWeight.BOLD,20));
        addText.setFill(Color.BLACK);
      /*  child – 添加到网格窗格的节点
        columnIndex – 网格窗格中子项的列索引位置，从 0 开始计数
        rowIndex – 网格窗格中子项的行索引位置，从 0 开始计数
        colspan – 子布局区域应跨越的列数
        rowspan – 子布局区域应跨越的行数*/
        gridPane.add(addText,0,0,2,1);

        Label nameLable=new Label("商品名称");
        gridPane.add(nameLable,0,1);
        TextField nameField=new TextField();
        if(goods!=null){
            nameField.setText(goods.getGoodsname());
        }
        gridPane.add(nameField,1,1);

        Label weightLable=new Label("商品重量");
        gridPane.add(weightLable,0,2);
        TextField weightField=new TextField();
        if(goods!=null){
            weightField.setText(goods.getWeight().toString());
        }
        gridPane.add(weightField,1,2);

        Label priceLable=new Label("商品价格");
        gridPane.add(priceLable,0,3);
        TextField pricetField=new TextField();
        if(goods!=null){
            pricetField.setText(goods.getPrice().toString());
        }
        gridPane.add(pricetField,1,3);

        Label addtimeLabel = new Label("商品上架时间");
        //child – 添加到网格窗格的节点
        //columnIndex – 网格窗格中子项的列索引位置，从 0 开始计数
        //rowIndex – 网格窗格中子项的行索引位置，从 0 开始计数
        gridPane.add(addtimeLabel, 0, 4);

        DatePicker addtimePicker = new DatePicker();
        //如果editEmp不为空
        if(goods!=null) {
            addtimePicker.setValue(DateUtils.DateToLocalDate(goods.getAddtime()));
        }
        gridPane.add(addtimePicker, 1, 4);


        Label numLabel=new Label("商品数量");
        gridPane.add(numLabel,0,5);
        TextField numtField=new TextField();
        if(goods!=null){
            numtField.setText(goods.getGoodnum().toString());
        }
        gridPane.add(numtField,1,5);

        Button btchoseFile=new Button("选择商品图片");
        TextField pathField=new TextField();
        if(goods!=null){
            pathField.setText(goods.getImagepath());
        }
        gridPane.add(btchoseFile,0,6);
        gridPane.add(pathField,1,6);

        /*文件选择框的使用*/
        btchoseFile.setOnAction(e->{
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("选择图片文件");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("image Files", "*.jpg","*jpeg"),
                   new FileChooser.ExtensionFilter("all file" ,"*.*"));

            File file = fileChooser.showOpenDialog(new Stage());
            if(file!=null){
                File absoluteFile = file.getAbsoluteFile();
                String path = absoluteFile.toString();
                pathField.setText(path);
            }
        });

       /* Label imagePathLabel=new Label("图片路径");
        gridPane.add(imagePathLabel,0,6);
        TextField pathField=new TextField();
        if(goods!=null){
            pathField.setText(goods.getImagepath());
        }
        gridPane.add(pathField,1,6);
*/


        Button sava=new Button("保存");
        Button back=new Button("返回");
        sava.setStyle("-fx-background-color: #9ACD32;-fx-text-fill: black");
        back.setStyle("-fx-background-color: #9ACD32;-fx-text-fill: black");
        back.setOnAction(event -> GoodsInfoView.info());
        HBox hbtn=new HBox(10);
        hbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbtn.getChildren().addAll(sava,back);
        gridPane.add(hbtn,1,7);

        final Text errorMsg=new Text();//错误提示框
        gridPane.add(errorMsg,1,8);


        /*在执行保存的时候判断输入框的状态*/
        sava.setOnAction(event -> {
            errorMsg.setFill(Color.RED);

            Goods goodsadd=new Goods();

            String gname = nameField.getText();
            String weight = weightField.getText();
            String price = pricetField.getText();
            LocalDate addtime = addtimePicker.getValue();
            String goodnum = numtField.getText();
            String imgePath=pathField.getText();

            boolean flag=true;

            /*判断商品名字是否相同
            * 如果id==0,说明初始状态为null,为新增操作，才进行name的判断
            * 如果不是id==0 则说明本来文本就有值，为编辑操作不需要判断*/
            if(id==0 && gname!=null){//为0的时候，说明是新增，才需要进行是否有商品的判断
                String s = goodsService.finByname(gname);
                    if(gname.equals(s)){//s要放在后面，不然如果S查出来为null的话，会出现空指针异常
                        errorMsg.setText("已存在商品");
                        flag=false;
                    }
                }


            if(StringUtils.isBlank(gname)){
                errorMsg.setText("未填写商品名称");
                flag=false;
            }
            if(StringUtils.isBlank(weight)){
                errorMsg.setText("未填写商品重量");
                flag=false;
            }

            if(StringUtils.isBlank(price)){
                errorMsg.setText("未填写商品价格");
                flag=false;
            }

            if(addtime==null){
                errorMsg.setText("未填写商品上架时间");
                flag=false;
            }

            if(StringUtils.isBlank(goodnum)){
                errorMsg.setText("未填写商品数量");
                flag=false;
            }

            if(StringUtils.isBlank(imgePath)){
                errorMsg.setText("未填写商品图片路径");
                flag=false;
            }

            if(flag){
                if(id!=0){
                    goodsadd.setId(id);
                }
                goodsadd.setGoodsname(gname);
                goodsadd.setWeight(Double.parseDouble(weight));
                goodsadd.setPrice(Double.parseDouble(price));//将String类型转换成double类型，插入类型不匹配
                goodsadd.setAddtime(DateUtils.LocalDateToDate(addtime));
                goodsadd.setGoodnum(Integer.parseInt(goodnum));
                goodsadd.setImagepath(imgePath);

                boolean addFlag = goodsService.saveOrUpdate(goodsadd);

                if (addFlag){
                    GoodsInfoView.info();
                }
            }

        });

        Scene scene=new Scene(gridPane,500,400);
        AppRun.setScene(scene);

    }
    }

