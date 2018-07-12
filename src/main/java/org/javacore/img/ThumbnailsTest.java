package org.javacore.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailsTest {
	
	private static String  str = "D:/zimg/a.png";
    private static String  despath = "D:/zimg/desImg";
    private static File file = null;
    static{
        file = new File(despath);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	ThumbnailsTest thumbnailatorTest = new ThumbnailsTest();
        thumbnailatorTest.test1();
        thumbnailatorTest.test2();
        thumbnailatorTest.test3();
        thumbnailatorTest.test4();
        thumbnailatorTest.test5();
        thumbnailatorTest.test6();
        thumbnailatorTest.test7();
        thumbnailatorTest.test8();
        thumbnailatorTest.test9();
    }

    /**
     * 指定大小进行缩放
     *
     * @throws IOException
     */
    private void test1() throws IOException {
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of(str).size(200, 300).toFile(
                despath+"/a.png");
        Thumbnails.of(str).size(2560, 2048).toFile(
                despath+"/a1.png");
    }

    /**
     * 按照比例进行缩放
     *
     * @throws IOException
     */
    private void test2() throws IOException {
        /**
         * scale(比例)
         */
        Thumbnails.of(str).scale(0.25f)
                .toFile(despath+"/b.png");
        Thumbnails.of(str).scale(1.10f).toFile(
                despath+"/b1.png");
    }

    /**
     * 不按照比例，指定大小进行缩放
     *
     * @throws IOException
     */
    private void test3() throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of(str).size(120, 120).keepAspectRatio(false)
                .toFile(despath+"/c.png");
    }

    /**
     * 旋转
     *
     * @throws IOException
     */
    private void test4() throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针
         */
        Thumbnails.of(str).size(1280, 1024).rotate(90).toFile(
                despath+"/d.png");
        Thumbnails.of(str).size(1280, 1024).rotate(-90).toFile(
                despath+"/d1.png");
    }

    /**
     * 水印
     *
     * @throws IOException
     */
    private void test5() throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of(str).size(1280, 1024).watermark(
                Positions.BOTTOM_RIGHT,
                ImageIO.read(new File("D:/zimg/c.png")), 0.5f)
                .outputQuality(0.8f).toFile(
                        despath+"/e.png");
        Thumbnails.of(str).size(1280, 1024).watermark(
                Positions.CENTER,
                ImageIO.read(new File("D:/zimg/c.png")), 0.5f)
                .outputQuality(0.8f).toFile(despath+"/e1.png");
    }

    /**
     * 裁剪
     *
     * @throws IOException
     */
    private void test6() throws IOException {
        /**
         * 图片中心400*400的区域
         */
        Thumbnails.of(str).sourceRegion(Positions.CENTER, 400,
                400).size(200, 200).keepAspectRatio(false).toFile(
                        despath+"/f.png");
        /**
         * 图片右下400*400的区域
         */
        Thumbnails.of(str).sourceRegion(Positions.BOTTOM_RIGHT,
                400, 400).size(200, 200).keepAspectRatio(false).toFile(
                        despath+"/f1.png");
        /**
         * 指定坐标
         */
        Thumbnails.of(str).sourceRegion(600, 500, 400, 400).size(
                200, 200).keepAspectRatio(false).toFile(
                        despath+"/f2.png");
    }

    /**
     * 转化图像格式
     *
     * @throws IOException
     */
    private void test7() throws IOException {
        /**
         * outputFormat(图像格式)
         */
        Thumbnails.of(str).size(1280, 1024).outputFormat("jpeg")
                .toFile(despath+"/g.jpeg");
        Thumbnails.of(str).size(1280, 1024).outputFormat("gif")
                .toFile(despath+"/g1.gif");
        
        Thumbnails.of(str).size(1280, 1024).outputFormat("jpg")
                .toFile(despath+"/g2.jpg");
        Thumbnails.of(str).size(1280, 1024).outputFormat("bmp")
                .toFile(despath+"/g3.bmp");
    }

    /**
     * 输出到OutputStream
     *
     * @throws IOException
     */
    private void test8() throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream(
                despath+"/h.gif");
        Thumbnails.of(str).size(1280, 1024).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     *
     * @throws IOException
     */
    private void test9() throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of(str).size(1280,
                1024).asBufferedImage();
        
        ImageIO.write(thumbnail, "jpg", new File(
                despath+"/i.gif"));
    }

}
